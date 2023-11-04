
import math
import os
import random
from anyio import sleep
import minizinc
from colorama import Fore, Back, Style, init
from typing import List
import webcolors

from clases.Piloto import Piloto
from clases.Escuderia import Escuderia
from clases.Circuito import Circuito
from clases.Posicion import Posicion

def calc_current_pos(ranking : list[tuple[Piloto, float]], idPiloto):
    i = 1
    for pos in ranking:
        if pos[0].id == idPiloto:
            return i
        i += 1
        
def load_model(filename, params):
    with open(filename, "r") as f:
        model_str = f.read()
    
    model = minizinc.Model()
    model.add_string(model_str)
    
    solver = minizinc.Solver.lookup("gecode")
    instance = minizinc.Instance(solver, model)
    
    for key, value in params.items():
        instance[key] = value

    return instance

def mostrar_ranking(ranking, ranking_lock):
    with ranking_lock:
        ranking.sort(key=lambda x: x[1])
        os.system("cls" if os.name == "nt" else "clear")
        print("Ranking actualizado:")
        for i, (piloto, tiempo) in enumerate(ranking, start=1):
            print(f"{i}. {piloto}: {tiempo:.3f} segundos")

def calcular_tiempo_vuelta(piloto : Piloto, circuito : Circuito, ranking, ranking_lock, FAST_MODE=True):
    if not FAST_MODE: sleep(random.uniform(0,10))
    tiempo_vuelta = circuito.metros_totales / (circuito.velocidad_media * 1000 / 3600)

    # Ajustar tiempo basado en habilidad del piloto
    min_original, max_original = 0.7, 0.95
    min_deseado, max_deseado = 0.993, 1.007
    scale_factor = (max_deseado - min_deseado) / (max_original - min_original)
    habilidad_parseada = (piloto.habilidad - min_original) * scale_factor + min_deseado
    tiempo_vuelta *= round(1 / random.uniform(habilidad_parseada-0.005, habilidad_parseada+0.005), 3)

    # Ajustar tiempo basado los puntos de la escuderías
    min_original, max_original = 1, 166
    min_deseado, max_deseado = 0.99, 1.01
    scale_factor = (max_deseado - min_deseado) / (max_original - min_original)
    habilidad_parseada = (piloto.escuderia.points - min_original) * scale_factor + min_deseado
    tiempo_vuelta *= round(1 / random.uniform(habilidad_parseada-0.003, habilidad_parseada+0.003), 3)
    
    if not FAST_MODE: sleep(tiempo_vuelta / 10)
    
    with ranking_lock:
        ranking.append((piloto, tiempo_vuelta))

    if not FAST_MODE: mostrar_ranking(ranking, ranking_lock)

    return (piloto, tiempo_vuelta)

def get_estrategia_pre(params : dict):
    pre_race_instance = load_model("../cerebro-pre/main.mzn", params)
    pre_race_result = pre_race_instance.solve()
    
    return pre_race_result

def get_estrategia(params : dict):
        in_race_instance = load_model("../cerebro-race/main.mzn", params)
        in_race_result = in_race_instance.solve()
        
        return in_race_result

def sort_ranking(ranking : list[Posicion]) -> list[Posicion]:
    ranking.sort(key=lambda x: x.time_total, reverse=False)
    return ranking

init(autoreset=True)

def hex_to_ansi(hex_color):
    rgb_color = webcolors.hex_to_rgb(hex_color)
    return f"\033[38;2;{rgb_color.red};{rgb_color.green};{rgb_color.blue}m"

def show_ranking(ranking: List[Posicion]):
    print(Style.BRIGHT + "Ranking de Pilotos:")
    print("--------------------")
    
    for i, posicion in enumerate(ranking, start=1):
        neumatico_str = {1: "Soft", 2: "Mid", 3: "Hard"}.get(posicion.neumatico, "Desconocido")
        conduccion_str = {2: "Normal", 3: "Agresiva", 1: "Conservadora"}.get(posicion.conduccion, "Desconocido")
        
        
        piloto_color = hex_to_ansi(posicion.piloto.color)
        escuderia_color = hex_to_ansi(posicion.piloto.escuderia.color)
        
        print(f"{Fore.YELLOW}{i}. {piloto_color}{posicion.piloto.nickname}{Fore.RESET}: {Fore.CYAN}{(posicion.time_total - ranking[0].time_total):.3f}s{Fore.RESET}, {conduccion_str} - {neumatico_str} ({posicion.degradacion}%)")
        print(Style.DIM + "-"*40)

    print(Style.RESET_ALL)
        
        
def calc_degradacion(neumatico: int, current_degradation: int, weather_index: int, meters: int, circuit_type: int, circuit_condition: int, driving_style: int) -> int:
    neumatico_factor = {1: 0.85, 2: 0.59, 3: 0.4}.get(neumatico, 1)
    weather_factor = {1: 1.3, 2: 1.6}.get(weather_index, 1)
    circuit_type_factor = {0: 1.1, 1: 1.0, 2: 0.9}.get(circuit_type, 1)
    circuit_condition_factor = {1: 1.2, 2: 1.5}.get(circuit_condition, 1)
    driving_style_factor = {1: 0.9, 2: 1.0, 3: 1.1}.get(driving_style, 1)

    # Calcular la degradación base
    base_degradation = (meters / 1000) * neumatico_factor * weather_factor * circuit_type_factor * circuit_condition_factor

    # Añadir el factor de conducción
    base_degradation *= driving_style_factor

    # Añadir el factor de degradación de la vuelta
    lap_degradation_factor = 1 + (0.7 * current_degradation / 100) ** 3
    base_degradation *= lap_degradation_factor

    # Calcular la nueva degradación
    new_degradation_percentage = current_degradation + base_degradation / 2

    # Ajustar la degradación a los límites [0, 100]
    new_degradation_percentage = max(0, min(100, new_degradation_percentage))

    return int(math.ceil(new_degradation_percentage))


def calc_speed(degradation: int, average_speed: float, neumatico: int, driving_style: int, circuit_type: int) -> float:
    # Ajustar la velocidad en función del neumático
    tire_speed_boost = {1: 1.02, 2: 1, 3: 0.98}
    speed_reduction_factor = tire_speed_boost.get(neumatico, 1)

    # Ajustar la velocidad en función del estilo de conducción
    driving_style_speed_adjustment = {1: 0.99, 2: 1, 3: 1.01}
    speed_reduction_factor *= driving_style_speed_adjustment.get(driving_style, 1)

    '''
    # Ajustar la velocidad en función del tipo de circuito
    circuit_speed_adjustment = {0: 0.995, 1: 1, 2: 1.005}
    speed_reduction_factor *= circuit_speed_adjustment.get(circuit_type, 1)
    '''

    # Ajustar la velocidad en función de la degradación
    degradation_speed_reduction = 1 - ((degradation / 100) ** 5)
    speed_reduction_factor *= degradation_speed_reduction

    # Calcular la velocidad de la vuelta
    lap_speed_kmh = average_speed * speed_reduction_factor

    # Añadir un factor aleatorio entre [-1%, 1%]
    random_factor = random.uniform(-0.005, 0.005)
    lap_speed_kmh *= 1 + random_factor

    # Convertir la velocidad de vuelta a m/s
    lap_speed_ms = lap_speed_kmh / 3.6

    return lap_speed_ms

def adjust_time(lap_time: float, position: int, time_ahead: float, time_behind: float, driving_style: int, driving_style_ahead: int, driving_style_behind: int, tire_compound: int, tire_compound_ahead: int, tire_compound_behind: int, tire_degradation: int, tire_degradation_ahead: int, tire_degradation_behind: int, drs_enabled : bool) -> float:
    
    if position > 1:
        # Sumar el tiempo que le cuesta adelantar
        if time_ahead < 0.5 and time_behind > 1 and driving_style - driving_style_ahead > 0:
            lap_time  += 1
        
        # Ajustar por DRS
        if time_ahead < 1 and drs_enabled:
            lap_time *= 0.99

    return lap_time



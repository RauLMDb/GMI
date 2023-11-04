import math
import random

from clases.Circuito import Circuito
from clases.Piloto import Piloto
from clases.Posicion import Posicion
from utils.funcs import calc_degradacion, calc_speed, get_estrategia, get_estrategia_pre, sort_ranking, show_ranking, adjust_time
from data.constantes import WEATHER, NEUMATICOS, CONDUCCION, CIRCUITO, CIRCUITO_ESTADO

def simulate_first_lap(clasificacion, circuito : Circuito, weather_index : int) -> list[Posicion]:
    print(f"--- LAP 1 ---")
    
    next_ranking : list[Posicion] = []
    
    # Inicializamos el ranking
    for i, (piloto, tiempo_vuelta) in enumerate(clasificacion):
        # --- Inicio de vuelta ---
        position = Posicion(piloto)
        params = {
            "total_laps": 51,
            "weather_index": weather_index,
            "pilot_hability": int(position.piloto.habilidad * 100),
            "min_speed": position.piloto.escuderia.min_speed,
            "med_speed": position.piloto.escuderia.mean_speed,
            "max_speed": position.piloto.escuderia.max_speed,
            "pit_stop_time": position.piloto.escuderia.time_pit,
            "posicion": i + 1,
        }
        #print(params)
        estrategia = get_estrategia_pre(params)
        #print(estrategia["neumaticos_index"])
        position.neumatico = estrategia["neumaticos_index"][0]
        position.conduccion = estrategia["conduccion_index"]
        position.neumaticos_usados[position.neumatico - 1] += 1
        next_ranking.append(position)
        
    neu_prev = [p.neumatico for p in next_ranking]
    cond_prev = [p.conduccion for p in next_ranking]
    
    for i, position in enumerate(next_ranking):
        # --- Durante la vuelta ----
        degradation = calc_degradacion(position.neumatico, position.degradacion, weather_index, circuito.metros_totales, circuito.tipo, circuito.estado, position.conduccion)
        lap_speed = calc_speed(degradation, position.piloto.escuderia.mean_speed, position.neumatico, position.conduccion, circuito.tipo)
        tiempo = (circuito.metros_totales + i*100) / lap_speed # Tiempo que tarda en dar una vuelta (teniendo en cuenta parrilla de salida)
        tiempo = adjust_time(tiempo, 
                             i, 
                             0.5, 
                             0.5, 
                             cond_prev[i], 
                             cond_prev[i-1] if i > 1 else -1, 
                             cond_prev[i+1] if i < len(next_ranking) - 1 else -1, 
                             neu_prev[i], 
                             neu_prev[i-1] if i > 1 else -1, 
                             neu_prev[i+1] if i < len(next_ranking) - 1 else -1, 
                             0, 
                             0, 
                             0, 
                             False
                             )
        
        # --- Final de vuelta ----
        position.time_total += tiempo
        
    sort_ranking(next_ranking)
    show_ranking(next_ranking)
    
    print()
    
    return next_ranking


def simulate_lap(ranking : list[Posicion], circuito : Circuito, weather_index : int, lap : int) -> list[Posicion]:
    print(f"--- LAP {lap + 1} ---")
    
    # Cambiamos los tiempos dependiendo de la estrategia
    for i, position in enumerate(ranking):
        # --- Inicio de vuelta ---
        params = {
            "remaining_laps":  51 - lap if 51-lap > 0 else 0,
            "vueltas_sigiente_parada": 26 - lap,
            "current_position": i+1,
            "weather_index": weather_index,
            "neumaticos_actual": [p.neumatico for p in ranking],
            "conduccion_actual": [p.conduccion for p in ranking],
            "estado_circuito": circuito.estado,
            "degradacion": [p.degradacion for p in ranking],
            "seconds_between": [abs(int((position.time_total - p.time_total)*1000)) for p in ranking],
            "habilidad_piloto": [int(p.piloto.habilidad * 100) for p in ranking],
            "velocidad_minima": [p.piloto.escuderia.min_speed for p in ranking],
            "velocidad_maxima": [p.piloto.escuderia.max_speed for p in ranking],
            "velocidad_media": [p.piloto.escuderia.mean_speed for p in ranking],
            "equipo": [p.piloto.escuderia.id for p in ranking], 
            "tiempo_pitstop": [p.piloto.escuderia.time_pit for p in ranking],
            "n_paradas": position.stops,
            "neumaticos_usados": position.neumaticos_usados,
        }
        estrategia = get_estrategia(params)
        
        # Cambio de neumatico
        if estrategia['neumatico_next_lap_index'] != 0:
            position.stops += 1
            position.degradacion = 0
            position.time_total += position.piloto.escuderia.time_pit / 1000
            position.neumatico = estrategia['neumatico_next_lap_index']
            position.neumaticos_usados[position.neumatico - 1] += 1
        
        position.conduccion = estrategia['conduccion_next_lap_index'] 
        
        # --- Durante la vuelta ----
        degradation = calc_degradacion(position.neumatico, position.degradacion, weather_index, circuito.metros_totales, circuito.tipo, circuito.estado, position.conduccion)
        lap_speed = calc_speed(degradation, position.piloto.escuderia.mean_speed, position.neumatico, position.conduccion, circuito.tipo)
        tiempo = circuito.metros_totales / (lap_speed / 3.6) # Tiempo que tarda en dar una vuelta (teniendo en cuenta parrilla de salida)
        
        # --- Final de vuelta ----
        position.degradacion = degradation
        position.time_total += tiempo
        
        
    sort_ranking(ranking)
    show_ranking(ranking)
    
    print()
    
    return ranking



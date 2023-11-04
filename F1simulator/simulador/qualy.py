from concurrent.futures import ThreadPoolExecutor
import threading

from clases.Circuito import Circuito
from clases.Piloto import Piloto
from utils.funcs import calcular_tiempo_vuelta

ranking_lock = threading.Lock()

def simulate_qualy(pilotos : list[Piloto], circuito : Circuito, FAST_MODE=True):
    # TODO : Tener en cuenta neumáticos
    # TODO : Agregar posibilidades de accidentes/fallos
    # Calcular tiempo de vuelta de cada piloto
    tiempos_vuelta = []
    with ThreadPoolExecutor() as executor:
        tiempos_vuelta = []
        ranking = []
        with ThreadPoolExecutor() as executor:
            futures = {executor.submit(calcular_tiempo_vuelta, piloto, circuito, ranking, ranking_lock, FAST_MODE) for piloto in pilotos}
            tiempos_vuelta = [future.result() for future in futures]
    
    # Ordenar pilotos por tiempo de vuelta (menor tiempo primero)
    tiempos_vuelta.sort(key=lambda x: x[1])
    
    return tiempos_vuelta

def qualy(circuito_race, pilotos : list[Piloto]):
    FAST_MODE = True
    
    print(f"Gran Premio de {circuito_race}")
    input("Presiona enter para empezar la clasificación...\n")

    print("Clasificación:")
    clasificacion = simulate_qualy(pilotos, circuito_race, FAST_MODE)
    i = 0
    for piloto, tiempo_vuelta in clasificacion:
        i = i+1
        print(f"{i}º {piloto} ({tiempo_vuelta:.3f} segundos)")
    
    input("Presiona enter para empezar la carrera...\n")
    
    return clasificacion
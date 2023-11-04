from concurrent.futures import ThreadPoolExecutor
import os
import threading
from time import sleep
from typing import List
import minizinc
import random
import json

from qualy import qualy
from clases.Piloto import Piloto
from clases.Escuderia import Escuderia
from clases.Circuito import Circuito
from clases.Posicion import Posicion
from clases.funcs import load_circuitos, load_escuderias, load_pilotos
from utils.funcs import calc_current_pos, calcular_tiempo_vuelta, get_estrategia_pre
from utils.simulations import simulate_first_lap, simulate_lap
from data.constantes import WEATHER, NEUMATICOS, CONDUCCION, CIRCUITO, CIRCUITO_ESTADO

ranking_lock = threading.Lock()

weather_actual = WEATHER["CLEAR"]
neumaticos = []
ranking = [] # Ranking respecto al primero
total_laps = 51


def main():
    ranking = []
    
    # Inicializar escuderías y pilotos
    circuitos = load_circuitos()
    escuderias = load_escuderias()
    pilotos = load_pilotos(escuderias)
    escuderias = escuderias.values()

    circuito_race = circuitos[3] # Circuito de Azerbaijan
    weather = WEATHER["CLEAR"]
    
    # --- CLASIFICACIÓN ---
    clasificacion = qualy(circuito_race, pilotos)
    
    # --- CARRERA ---
    ranking : list[Posicion] = simulate_first_lap(clasificacion, circuito_race, weather)
    for lap in range(1, total_laps + 1):
        ranking = simulate_lap(ranking, circuito_race, weather, lap)

if __name__ == "__main__":
    main()
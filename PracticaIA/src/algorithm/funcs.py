import math
import random

from src.classes.arista_class import Arista
from src.classes.nodo_class import Nodo


# En cada iteración que necesitemos un nuevo coste, lo calculamos y lo guardamos
# de forma que lo mantenemos constante en futuras iteraciones

# El coste de cada arista no es más que el tiempo que nos ha estimado la API de Google Maps
# que se tarda de llegar del origen hasta el destino yendo en transporte público
def calc_coste(edge: Arista, nodes: dict[int: Nodo]):
    return edge.coste


# Para calcular la distancia aérea entre dos coordenadas geográficas usamos la Fórmula de Harversine
def calc_heuristica(origen: Nodo, destino: Nodo):
    origen_x_rad = origen.coord.x * math.pi / 180
    origen_y_rad = origen.coord.y * math.pi / 180
    destino_x_rad = destino.coord.x * math.pi / 180
    destino_y_rad = destino.coord.y * math.pi / 180

    lat = origen_x_rad - destino_x_rad
    long = origen_y_rad - destino_y_rad
    h = (math.sin(lat / 2) ** 2) + math.cos(origen_x_rad) * math.cos(destino_x_rad) * (math.sin(long / 2) ** 2)
    R = 6371  # Radio de la tierra en KM
    return 2 * R * math.asin(math.sqrt(h))

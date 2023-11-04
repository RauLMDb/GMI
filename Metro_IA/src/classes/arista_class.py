from math import sqrt

from src.classes.nodo_class import Nodo


class Arista:
    def __init__(self, origen: Nodo, destino: Nodo, coste: int):
        self.origen = origen
        self.destino = destino
        self.coste = coste

    def __str__(self):
        return f"{self.origen} -> {self.destino} : c = {self.coord.coste}, h = {self.coord.heuristica}"

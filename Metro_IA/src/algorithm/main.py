# En esta carpeta ejecutaremos lo referente al algoritmo A*
# de forma que `main.py` es el coordinador del directorio
import math

from src.algorithm.funcs import calc_coste, calc_heuristica
from src.classes.arista_class import Arista
from src.classes.nodo_class import Nodo


# Usamos una clase auxiliar en la que apuntaremos el nodo y sus atributos
class NodoParam:
    def __init__(self, nodo: Nodo, g: int, h: int, father: Nodo):
        self.nodo = nodo;
        self.g = g
        self.h = h
        self.f = g + h
        self.father = father


def run_algorithm(nodo_inicial: Nodo, nodo_final: Nodo, nodos: dict[int, Nodo], aristas: dict[int, dict[int, Arista]]) \
        -> tuple[list[int], dict[int, NodoParam]]:

    abiertos, cerrados, vecinos, path, params = [], [], [], [], {}
    if nodo_inicial.id == nodo_final.id:
        return path, params

    nodo_actual = nodo_inicial
    abiertos.append(nodo_inicial.id)
    params[nodo_inicial.id] = NodoParam(nodo_inicial, 0, calc_heuristica(nodo_inicial, nodo_final), None)

    while nodo_final.id not in cerrados:
        for nodoID in aristas[nodo_actual.id]:
            heuristica = calc_heuristica(nodos[nodoID], nodo_final)
            coste = calc_coste(aristas[nodo_actual.id][nodoID], nodos)
            vecinos.append(nodoID)

        for nodoID in vecinos:
            nodo_param = NodoParam(nodos[nodoID], coste, heuristica + params[nodo_actual.id].f, nodo_actual)

            if (nodoID not in cerrados) and (nodoID not in abiertos):
                abiertos.append(nodoID)
                params[nodoID] = nodo_param
            else:
                if nodoID in abiertos:
                    if nodo_param.f < params[nodoID].f:
                        params[nodoID] = nodo_param

        vecinos = []

        min_nodo_id = abiertos[0]
        for nodoID in abiertos:
            if params[nodoID].f < params[min_nodo_id].f:
                min_nodo_id = nodoID

        abiertos.remove(min_nodo_id)
        cerrados.append(min_nodo_id)

        nodo_actual = nodos[min_nodo_id]

    cerrados.reverse()
    nodo_actual_param = params[cerrados[0]]
    while nodo_actual_param.father is not None:
        path.append(nodo_actual_param.nodo)
        nodo_actual_param = params[nodo_actual_param.father.id]

    path.append(nodo_actual_param.nodo)
    path.reverse()
    return path, params

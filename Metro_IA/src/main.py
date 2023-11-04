from src.algorithm.main import run_algorithm
from src.utils.data import *

def run(origen, destino, nodos, aristas):
    s = ""
    path, params = run_algorithm(nodos[origen], nodos[destino], nodos, aristas)
    for nodo in path:
        if nodo.id is not destino:
            s += f"{nodo.name} -> "
        else:
            s += f"{nodo.name}"
    return path, params,s

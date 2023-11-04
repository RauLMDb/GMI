from src.algorithm.funcs import calc_heuristica
from src.main import run
from src.utils.data import getNodes, getAristas

nodos = getNodes()
aristas = getAristas(nodos)

if __name__ == '__main__':
    ORIGEN = 1
    DESTINO = 29
    path, params, s= run(ORIGEN, DESTINO, nodos, aristas)

    print(f"La ruta óptima desde {nodos[ORIGEN]} hasta {nodos[DESTINO]} es:\n"+s)
    print(f"La distancia total recorrida será de {params[DESTINO].f} km")

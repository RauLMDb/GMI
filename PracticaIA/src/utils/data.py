# Importamos bases de datos y clases
from database.nodos import nodos
from database.aristas import aristas
from src.classes.nodo_class import Nodo
from src.classes.arista_class import Arista


# Lee la base de datos y exporta el grafo
def getNodes():
    dump = {}

    for node in nodos:
        dump[node["id"]] = Nodo(node["id"], node["nombre"], node["coords"]["x"], node["coords"]["y"], node["linea"])

    return dump


def getAristas(nodos: dict):
    dump = {}

    for edge in aristas:

        if edge["nodoA"] not in dump:
            dump[edge["nodoA"]] = {}
        if edge["nodoB"] not in dump:
            dump[edge["nodoB"]] = {}

        dump[edge["nodoA"]][edge["nodoB"]] = Arista(nodos[edge["nodoA"]], nodos[edge["nodoB"]], edge["coste"])
        dump[edge["nodoB"]][edge["nodoA"]] = Arista(nodos[edge["nodoB"]], nodos[edge["nodoA"]], edge["coste"])

    return dump

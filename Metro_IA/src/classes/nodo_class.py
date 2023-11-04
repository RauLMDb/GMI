import math

class Coord:
    def __init__(self, x, y):
        self.x = x
        self.y = y

class Nodo:
    def __init__(self, id, name, x, y, linea):
        self.id = id
        self.name = name
        self.coord = Coord(x,y)
        self.linea = linea

    def __str__(self):
        return f"[{self.id}] \"{self.name}\" ({self.coord.x}, {self.coord.y}) "

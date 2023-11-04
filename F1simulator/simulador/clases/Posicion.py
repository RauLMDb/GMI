from clases.Piloto import Piloto

class Posicion:
    def __init__(self, piloto : Piloto):
        self.piloto            : Piloto = piloto
        self.degradacion       : int = 0
        self.conduccion        : int = 0
        self.neumatico         : int = 0
        self.time_total        : float = 0.0
        self.stops             : int = 0
        self.neumaticos_usados : list[int] = [0,0,0,0,0]
    
    def __str__(self):
        neumatico_str = {1: "Blando", 2: "Medio", 3: "Duro"}.get(self.neumatico, "No definido")
        conduccion_str = {1: "Conservador", 2: "Normal", 3: "Agresivo"}.get(self.conduccion, "No definido")
        
        return f"{self.piloto.nickname} ({self.time_total:.3f}s) - {neumatico_str} - {conduccion_str} - {self.degradacion}%"
    
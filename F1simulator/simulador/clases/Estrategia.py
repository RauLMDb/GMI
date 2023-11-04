from clases.Piloto import Piloto

class Estrategia:
    def __init__(self):
        self.neumaticos = [0 for x in range(60)]
    
    def __str__(self):
        neumatico_str = {1: "Blando", 2: "Medio", 3: "Duro"}.get(self.neumatico, "No definido")
        conduccion_str = {1: "Conservador", 2: "Normal", 3: "Agresivo"}.get(self.conduccion, "No definido")
        
        return f"{self.piloto.nickname} ({self.time_total:.3f}s) - {neumatico_str} - {conduccion_str} - {self.degradacion}%"
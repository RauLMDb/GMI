from clases.Escuderia import Escuderia

class Piloto:
    def __init__(self, id, first_name, last_name, escuderia : Escuderia, color, dorsal, country, contract_until, habilidad, nickname):
        self.id = id
        self.first_name = first_name
        self.last_name = last_name
        self.nickname = nickname
        self.escuderia = escuderia
        self.color = color
        self.dorsal = dorsal
        self.country = country
        self.contract_until = contract_until
        self.habilidad = habilidad

    def __str__(self):
        return f"{self.first_name} {self.last_name} ({self.escuderia.team_name}, Dorsal {self.dorsal}) - {self.habilidad}"
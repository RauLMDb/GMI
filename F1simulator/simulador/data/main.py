import json

class Piloto:
    def __init__(self, id, first_name, last_name, escuderia, color, dorsal, country, contract_until):
        self.id = id
        self.first_name = first_name
        self.last_name = last_name
        self.escuderia = escuderia
        self.color = color
        self.dorsal = dorsal
        self.country = country
        self.contract_until = contract_until

    def __str__(self):
        return f"{self.first_name} {self.last_name} ({self.escuderia.team_name}, Dorsal {self.dorsal})"

class Escuderia:
    def __init__(self, id, team_name, color, country, headquarters, team_principal, car, engine, debut):
        self.id = id
        self.team_name = team_name
        self.color = color
        self.country = country
        self.headquarters = headquarters
        self.team_principal = team_principal
        self.car = car
        self.engine = engine
        self.debut = debut
        self.pilotos = []

    def add_piloto(self, piloto):
        self.pilotos.append(piloto)
        
    def __str__(self):
        return f"{self.team_name} (ID {self.id}, {self.country})"

class Circuito:
    def __init__(self, nombre, pais, velocidad_media, metros_totales, num_curvas, tipo, degradacion, zonas_drs):
        self.nombre = nombre
        self.pais = pais
        self.velocidad_media = velocidad_media
        self.metros_totales = metros_totales
        self.num_curvas = num_curvas
        self.tipo = tipo
        self.degradacion = degradacion
        self.longitud_drs = sum([zona["longitud"] for zona in zonas_drs])
    
    def __str__(self):
        return f"{self.nombre} ({self.pais}) - {self.metros_totales} metros - {self.num_curvas} curvas - {self.tipo} - {self.degradacion} degradacion - {self.longitud_drs} metros DRS"

with open('circuitos.json') as f:
    circuitos_json = json.load(f)

def load_json_file(file_name):
    with open(file_name, 'r') as file:
        data = json.load(file)
    return data

def load_escuderias():
    escuderias_json = load_json_file('escuderias.json')
    escuderias = [Escuderia(**escuderia_data) for escuderia_data in escuderias_json]
    return {escuderia.id: escuderia for escuderia in escuderias}

def load_pilotos(escuderias):
    pilotos_json = load_json_file('pilotos.json')
    pilotos = [Piloto(escuderia=escuderias[piloto_data['team_id']], **{k: v for k, v in piloto_data.items() if k != 'team_id'}) for piloto_data in pilotos_json]
    for piloto in pilotos:
        piloto.escuderia.add_piloto(piloto)
    return pilotos

def load_circuitos():
    circuitos_json = load_json_file('circuitos.json')
    circuitos = [Circuito(circuito['nombre'], circuito['pais'], circuito['velocidad_media'], circuito['metros_totales'], circuito['num_curvas'], circuito['tipo'], circuito['degradacion'], circuito['zonas_drs']) for circuito in circuitos_json]
    return circuitos

if __name__ == "__main__":
    circuitos = load_circuitos()
    escuderias = load_escuderias()
    pilotos = load_pilotos(escuderias)
    escuderias = escuderias.values()

    for circuito in circuitos:
        print(circuito)

    for escuderia in escuderias:
        print(escuderia)
        for piloto in escuderia.pilotos:
            print(f" - {piloto}")

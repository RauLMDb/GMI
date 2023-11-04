import json

from clases.Piloto import Piloto
from clases.Escuderia import Escuderia
from clases.Circuito import Circuito

def load_json_file(file_name):
    with open(file_name, 'r') as file:
        data = json.load(file)
    return data

def load_escuderias():
    escuderias_json = load_json_file('./data/escuderias.json')
    escuderias = [Escuderia(**escuderia_data) for escuderia_data in escuderias_json]
    return {escuderia.id: escuderia for escuderia in escuderias}

def load_pilotos(escuderias):
    pilotos_json = load_json_file('./data/pilotos.json')
    pilotos = [Piloto(escuderia=escuderias[piloto_data['team_id']], **{k: v for k, v in piloto_data.items() if k != 'team_id'}) for piloto_data in pilotos_json]
    for piloto in pilotos:
        piloto.escuderia.add_piloto(piloto)
    return pilotos

def load_circuitos():
    circuitos_json = load_json_file('./data/circuitos.json')
    circuitos = [Circuito(circuito['nombre'], circuito['pais'], circuito['velocidad_media'], circuito['metros_totales'], circuito['num_curvas'], circuito['tipo'], circuito['degradacion'], circuito['zonas_drs']) for circuito in circuitos_json]
    return circuitos
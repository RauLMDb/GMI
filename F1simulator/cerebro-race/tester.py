import minizinc

def load_model(filename, params):
    model = minizinc.Model()
    model.add_file(filename)
    
    solver = minizinc.Solver.lookup("gecode")
    instance = minizinc.Instance(solver, model)
    
    for key, value in params.items():
        print(key, value)
        instance[key] = value

    return instance

def execute_model():
    
    
    params = {
        'remaining_laps': 50, 
        'vueltas_sigiente_parada': 25, 
        'current_position': 2, 
        'weather_index': 0, 
        'neumaticos_actual': [2, 2, 2, 2, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1], 
        'conduccion_actual': [3, 1, 3, 1, 3, 3, 3, 3, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3], 
        'estado_circuito': 0, 
        'degradacion': [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0], 
        'seconds_between': [0, 246, 3130, 4529, 5428, 5593, 6958, 8655, 10294, 10598, 12900, 13071, 17163, 17762, 19533, 20041, 21826, 24738, 25047, 27652], 
        'habilidad_piloto': [86, 93, 86, 95, 90, 83, 85, 87, 91, 85, 89, 80, 79, 82, 78, 75, 83, 77, 70, 70], 
        'velocidad_minima': [73, 73, 73, 71, 73, 70, 69, 70, 70, 71, 72, 71, 69, 72, 69, 70, 71, 72, 72, 69], 
        'velocidad_maxima': [342, 342, 334, 336, 334, 326, 336, 327, 326, 336, 332, 336, 336, 335, 338, 327, 336, 332, 335, 338], 
        'velocidad_media': [214, 214, 214, 212, 214, 212, 208, 211, 212, 211, 209, 212, 208, 210, 209, 211, 211, 209, 210, 209], 
        'equipo': [1, 1, 2, 3, 2, 7, 8, 5, 7, 4, 6, 3, 8, 10, 9, 5, 4, 6, 10, 9], 
        'tiempo_pitstop': [20620, 20620, 21460, 20700, 21460, 23150, 27810, 20660, 23150, 20600, 21670, 20700, 27810, 21070, 20480, 20660, 20600, 21670, 21070, 20480],
        'n_paradas_restantes': 0,
        'neumaticos_usados': [1,0,0,0,0]
    }

    instance = load_model("main.mzn", params)
    result = instance.solve()
    return result

def main():
    result = execute_model()
    
    print(result.statistics)

    print(f"Neumático a utilizar en la siguiente vuelta: {result['neumatico_next_lap_index']}")
    print(f"Tipo de conducción a utilizar en la siguiente vuelta: {result['conduccion_next_lap_index']}")

if __name__ == "__main__":
    main()

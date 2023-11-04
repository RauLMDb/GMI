import minizinc

def load_model(filename, params):
    with open(filename, "r") as f:
        model_str = f.read()
    
    model = minizinc.Model()
    model.add_string(model_str)
    
    solver = minizinc.Solver.lookup("gecode")
    instance = minizinc.Instance(solver, model)
    
    for key, value in params.items():
        instance[key] = value

    return instance

def main():

    total_laps = 51
    pilot_hability = 86
    weather_index = 1
    min_speed = 73
    med_speed = 214
    max_speed = 342
    pit_stop_time = 20620
    posicion = 2

    params = {
        "total_laps": total_laps,
        "weather_index": weather_index,
        "pilot_hability": pilot_hability,
        "min_speed": min_speed,
        "med_speed": med_speed,
        "max_speed": max_speed,
        "pit_stop_time": pit_stop_time,
        "posicion": posicion,
    }

    instance = load_model("main.mzn", params)
    result = instance.solve()

    
    print(f"Neumatico a utilizar en cada vuelta: {result['neumaticos_index']}")
    print(f"Tipo de conduccion a lo largo de la primera vuelta: {result['conduccion_index']}")
    print(f"Tiempo de cada vuelta: {result['time_per_lap']}")

if __name__ == "__main__":
    main()

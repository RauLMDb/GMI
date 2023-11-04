import matplotlib.pyplot as plt
import random

from utils.funcs import calc_degradacion, calc_speed
# Configurar el experimento
num_variations = 5
num_laps = 60
average_speed_ms = 59.51  # Velocidad promedio en m/s basada en el tiempo de vuelta más rápida en Azerbaiyán
circuit_length_m = 6070

# Generar variaciones de parámetros
random.seed(42)
variations = [
    {
        "neumatico": random.choice([1, 2, 3]),
        "weather_index": random.choice([0, 1, 2]),
        "circuit_type": random.choice([0, 1, 2]),
        "circuit_condition": random.choice([0, 1, 2]),
        "driving_style": random.choice([1, 2, 3]),
    }
    for _ in range(num_variations)
]

# Preparar subgráficos
fig, (ax1, ax2) = plt.subplots(2, 1, figsize=(10, 15))

# Simular las vueltas para cada variación
for i, variation in enumerate(variations):
    degradation = [0]
    speeds = [calc_speed(0, average_speed_ms, neumatico=variation['neumatico'], driving_style=variation['driving_style'], circuit_type=variation['circuit_type'])]

    for lap in range(1, num_laps):
        new_degradation = calc_degradacion(current_degradation=degradation[-1], meters=circuit_length_m, **variation)
        degradation.append(new_degradation)

        lap_speed = calc_speed(new_degradation, average_speed_ms, neumatico=variation['neumatico'], driving_style=variation['driving_style'], circuit_type=variation['circuit_type'])
        speeds.append(lap_speed)

    # Plot degradation and speeds
    ax1.plot(range(1, num_laps + 1), degradation, label=f"Degradación Variación {i + 1}")
    ax1.plot(range(1, num_laps + 1), speeds, label=f"Velocidad Variación {i + 1}", linestyle="--")

# Plot degradation and speeds by tire type
tire_types = {1: "Soft", 2: "Medium", 3: "Hard"}
colors = {1: "r", 2: "g", 3: "b"}

for neumatico, color in colors.items():
    degradation = [0]
    speeds = [calc_speed(0, average_speed_ms, neumatico=neumatico, driving_style=2, circuit_type=0)]

    for lap in range(1, num_laps):
        new_degradation = calc_degradacion(current_degradation=degradation[-1], meters=circuit_length_m, neumatico=neumatico, weather_index=0, circuit_type=0, circuit_condition=0, driving_style=2)
        degradation.append(new_degradation)

        lap_speed = calc_speed(new_degradation, average_speed_ms, neumatico=neumatico, driving_style=2, circuit_type=0)
        speeds.append(lap_speed)

    ax2.plot(range(1, num_laps + 1), degradation, label=f"Degradación {tire_types[neumatico]}", color=color)
    ax2.plot(range(1, num_laps + 1), speeds, label=f"Velocidad {tire_types[neumatico]}", linestyle="--", color=color)

ax1.set_xlabel("Vueltas")
ax1.set_ylabel("Degradación (%) y Velocidad (m/s)")
ax1.set_title("Degradación de neumáticos y velocidad esperada por vuelta (variaciones)")
ax1.legend()

ax2.set_xlabel("Vueltas")
ax2.set_ylabel("Degradación (%) y Velocidad (m/s)")
ax2.set_title("Degradación de neumáticos y velocidad esperada por vuelta (por tipo de neumático)")
ax2.legend()

plt.tight_layout()
plt.show()

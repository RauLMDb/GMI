
class Escuderia:
    def __init__(self, id, team_name, color, country, headquarters, team_principal, car, engine, debut, mean_speed, max_speed, min_speed, time_pit, points):
        self.id = id
        self.team_name = team_name
        self.color = color
        self.country = country
        self.headquarters = headquarters
        self.team_principal = team_principal
        self.car = car
        self.engine = engine
        self.debut = debut
        self.mean_speed = mean_speed
        self.max_speed = max_speed
        self.min_speed = min_speed
        self.time_pit = time_pit
        self.points = points
        self.pilotos = []

    def add_piloto(self, piloto):
        self.pilotos.append(piloto)
        
    def __str__(self):
        return f"{self.team_name} (ID {self.id}, {self.country})"
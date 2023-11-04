
class Circuito:
    def __init__(self, nombre, pais, velocidad_media, metros_totales, num_curvas, tipo, degradacion, zonas_drs):
        self.nombre = nombre
        self.pais = pais
        self.velocidad_media = velocidad_media # km por hora
        self.metros_totales = metros_totales
        self.num_curvas = num_curvas
        self.tipo = tipo
        self.degradacion = degradacion
        self.longitud_drs = sum([zona["longitud"] for zona in zonas_drs])
        self.estado = 0
    
    def __str__(self):
        return f"{self.nombre} ({self.pais}) - {self.metros_totales} metros - {self.num_curvas} curvas - {self.tipo} - {self.degradacion} degradacion - {self.longitud_drs} metros DRS"
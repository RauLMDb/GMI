import requests
from bs4 import BeautifulSoup
import pandas as pd

# URL de la página web
url = "http://www.coie.upm.es/#estudiante/practicas-curriculares/todas/116753/ver"

# Obtener el contenido de la página web
response = requests.get(url)

# Parsear el contenido con BeautifulSoup
soup = BeautifulSoup(response.content, "html.parser")

# Encontrar los elementos que contienen la información que deseas
titulo = soup.find("h1").text
descripcion = soup.find(text="Descripción:").find_next("p").text
requisitos = soup.find(text="Requisitos:").find_next("ul").text
entidad_colaboradora = soup.find(text="Entidad colaboradora:").find_next("p").text
ayuda_estudio = soup.find(text="Ayuda al estudio:").find_next("p").text
horas_totales = soup.find(text="Horas totales:").find_next("p").text

# Crear un diccionario con los datos extraídos
datos = {
    "Titulo": titulo,
    "Descripcion": descripcion,
    "Requisitos": requisitos,
    "Entidad colaboradora": entidad_colaboradora,
    "Ayuda al estudio": ayuda_estudio,
    "Horas totales": horas_totales
}

# Crear un DataFrame de pandas con los datos
df = pd.DataFrame([datos])

# Guardar el DataFrame en un archivo de Excel
df.to_excel("datos_practicas.xlsx", index=False)
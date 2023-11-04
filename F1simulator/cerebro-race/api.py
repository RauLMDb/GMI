from flask import Flask

from tester import execute_model

app = Flask(__name__)

@app.route('/')
def index():
    resultado = execute_model()
    obj = {
        "neumatico_next_lap_index" : resultado['neumatico_next_lap_index'],
        "conduccion_next_lap_index" : resultado['conduccion_next_lap_index'],
    }
    return obj

if __name__ == '__main__':
    app.run(debug=True)

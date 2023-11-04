import { actualizar_TS, buscar_Index_TS, buscar_TS, create_TS, destruir_TS, name_TS } from "./semantico/semantico.js"
import { outputParse, outputTokens, outputTS } from "./utils/functions.js";
import { sintactico } from "./sintactico/AnalizadorSintactico.js"

function main() {
    // Procesamos el archivo
    sintactico()

    // Volcamos los tokens y la tabla de símbolos
    outputTokens()
    outputTS()
    outputParse()
}

main()
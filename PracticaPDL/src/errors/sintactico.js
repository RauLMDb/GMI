import chalk from "chalk";
import { errorsVars } from "./errors.js";
import { vars } from "../lexico/lexico.js";

function _traductorTipo(tipo) {
    switch (tipo) {
        case "SUM": return "Signo de suma ´+´"
        case "MUL": return "Signo de multiplicación ´*´"
        case "AND": return "Operador AND ´&&´"
        case "OR": return "Operador OR ´||´"
        case "CAD": return "Comillas simples ´'´"
        case "INT": return "Declarador de número entero ´int´"
        case "STRING": return "Declarador de cadenas ´string´"
        case "ASIGN": return "Asignación ´=´"
        case "PARA": return "Paréntesis de apertura ´(´"
        case "PARC": return "Paréntesis de cierre ´)´"
        case "BRKA": return "Corchete de apertura ´{´"
        case "BRKC": return "Corchete de cierre ´}´"
        case "ASIGNSUB": return "Asignación con decremento ´-=´"
        case "SCOL": return "Punto y coma ´;´"
        case "COMMA": return "Coma ´,´"
        case "GRT": return "Operador de comparación mayor ´>´"
        case "LOW": return "Operador de comparación menor ´<´"
        case "ID": return "Identificador"
        case "BOOL": return "Palabra de definición de bool ´boolean´"
        case "FUNC": return "Palabra de definición de funciones ´function´"
        case "PRNT": return "Print ´print´"
        case "RET": return "Retorno de la función ´return´"
        case "INT": return "Input ´input´"
        case "LET": return "Declarador de variables ´let´"
        case "DO": return "Declarador do-while ´do´"
        case "WHILE": return "Declarador de bucles ´while´"
        case "INTEGER": return "Número entero"
        case "TRUE": return "Constante lógica verdadero ´true´"
        case "FALSE": return "Constante lógica falso ´false´"
        default : console.log(tipo) ; return "";
    }
}

export function errorSintactico(obj) {
    console.log(chalk.bgRed(chalk.bold("[ERROR SINTÁCTICO]")), chalk.red("Caracter inesperado '" + vars.anteriorCharLeido + "' en la linea " + vars.linea + ":" + vars.actualLinea + "."))
    if (obj.arr != undefined) {
        if (obj.arr.length > 0) {
            let msg = ""
            obj.arr.forEach((e, i) => {
                if (i == 0 && i < obj.arr.length - 1) msg += _traductorTipo(e) + ", "
                else if (i < obj.arr.length - 1) msg += _traductorTipo(e).toLowerCase() + ", "
                else msg += _traductorTipo(e).toLowerCase()
            })
            console.log(chalk.gray("Es posible que falte algún elemento de los siguientes:", msg + "."))
        }
    }
    if (errorsVars.posible != null) console.log(chalk.grey(errorsVars.posible))
    process.exit(0)
}


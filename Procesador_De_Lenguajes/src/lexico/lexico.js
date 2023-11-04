import { readFileSync } from "fs"
import { throwError } from "../errors/errors.js";
import { accion } from "./accionesSemanticas.js";
import { matrizTransiciones } from "./matrizTransiciones.js";
import { translateChar } from "./traductorChar.js";
import { _errorLexico } from "../errors/lexico.js";

export let vars = {
    length: 0,
    actual : 0,
    actualLinea : 0,
    charLeido: null,
    anteriorCharLeido: null,
    char : null,
    state : "0",
    encontrado : false,
    token : {
        "id": null,
        "atr": null
    },
    linea : 0,
    tokenEncontrado : {
        "id": null,
        "atr": null
    }
}

export const lexico = () => {
    const inputFile = readFileSync(process.cwd() + "/input.txt")
    const input = inputFile.toString()
    vars.length = input.length
    vars.char = input[vars.actual];


    while (vars.token.id == null && vars.actual < vars.length && !vars.encontrado) {
        vars.anteriorCharLeido = vars.charLeido != " " && vars.charLeido != "\n" ? vars.charLeido : vars.anteriorCharLeido
        vars.charLeido = vars.char
        vars.linea = vars.char == "\n" || vars.char == "\r" ? vars.linea + 1 : vars.linea

        // Tratamos las posibles excepciones del char
        translateChar()

        // Si a pesar de haber tratado el char en el switch sigue sin concordar, entonces es otro caracter (o.c.)
        if (matrizTransiciones[vars.state][vars.char] == undefined) vars.char = "o.c."

        // Depuración
        // console.log(vars.state, "=>", vars.charLeido, "=>", matrizTransiciones[vars.state][vars.char])

        // Vemos si tenemos algún error
        if (matrizTransiciones[vars.state][vars.char] == "error") {
            _errorLexico(vars.state)
            throwError("100");
        }

        // Realizamos las acciones semánticas pertinente
        accion(vars.state, matrizTransiciones[vars.state][vars.char])

        // Vemos si ya hemos terminado
        if (vars.state == "end") {
            vars.state = "0"
            break;
        }
    }

    // Vemos si nos hemos quedado un token
    if (!vars.encontrado) { vars.tokenEncontrado = {id: null, atr: null} }
    vars.encontrado = false;

    //console.log(tablaSimbolos[0].name, ":", tablaSimbolos[0].tabla)
    return vars.tokenEncontrado
}
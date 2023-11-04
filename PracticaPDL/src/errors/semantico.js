import chalk from "chalk";
import { errorsVars } from "./errors.js";
import { vars } from "../lexico/lexico.js";

function _traductorTipo(tipo) {
    switch (tipo) {
        case "BOOLEANO": return "lógico (boolean)"
        case "ENTERO": return "entero (int)"
        case "CADENA": return "cadena de caracteres (string)"
        case "FUNC": return "función (function)"
        case "ASIGN": return "asignación (=)"
        case "SUB_ASIGN": return "asignación con decrementación (-=)"
    }
}


/*
La función errorSemantico recibe como parámetro un objeto ´obj´
obj esta fórmado por:
    - Un código ´code´ que puede ser:
        - 0: Error de tipos y viene acompañado con ´tipos´ que es un array de los posibles tipos para arreglarlo
        - 1: Error debido a que no está asegurado que una función retorne siempre algo
        - 2: El tipo del return no es el que pide la función y viene acompañado con ´esperado´ y ´devuelto´
        - 3: Se declaran dos veces la misma variable
        - 4: Se usa una variable no declarada
        - 5: Una de las funciones no tiene los argumentos de los tipos que debería
        - 6: Se ha puesto return fuera de una función
        - 7: Se ha llamado a una función con distinto número de parametros
*/
export function errorSemantico(obj) {
    console.log(chalk.bgRed(chalk.bold("[ERROR SEMÁNTICO]")), chalk.red("Caracter inesperado '" + vars.anteriorCharLeido + "' en la linea " + vars.linea + ":" + vars.actualLinea + "."))

    if (obj != undefined) {
        switch(obj.code) {
            case 0: {
                if (obj.tipos.includes('ASIGN') || obj.tipos.includes('SUB_ASIGN')) {
                    if (obj.devuelto == "ENTERO" || obj.devuelto != obj.esperado) {
                        console.log(chalk.gray("Parece que se ha intentado asignar un elemento de tipo ´" + _traductorTipo(obj.devuelto) + "´ a un elemento de tipo `" + _traductorTipo(obj.esperado) + "´."))
                    } else {
                        console.log(chalk.gray("Parece que se ha intentado realizar una asignación con decremento a un elemento de tipo `" + _traductorTipo(obj.esperado) + "´."))
                    }
                } else {
                    let msg = ""
                    obj.tipos.forEach((e, i) => {
                        if (i == 0 && i < obj.tipos.length - 1) msg += _traductorTipo(e)
                        else if (i < obj.tipos.length - 1) msg += ", " + _traductorTipo(e).toLowerCase()
                        else msg += _traductorTipo(e).toLowerCase()
                    })
                    console.log(chalk.gray("Parece que hay un error de que los tipos no concuerdan se ha dado un tipo ´" + _traductorTipo(obj.devuelto) + "´ mientras que se esperaba uno de los siguientes tipos:", msg + "."))
                }
            }; break;
            case 1: {
                console.log(chalk.gray("Parece que hay un error debido a que existe el riesgo de que la función no devuelva nada a pesar de no ser de tipo void."))
            }; break;
            case 2: {
                console.log(chalk.gray("Parece que hay un error debido a que la función esperaba devolver un tipo `" + _traductorTipo(obj.esperado) + "` y en cambio se ha devuelto un tipo `" + _traductorTipo(obj.devuelto) + "`."))
            }; break;
            case 3: {
                console.log(chalk.gray("Parece que se ha declarado dos veces una misma variable."))
            }; break;
            case 4: {
                console.log(chalk.gray("Parece que se ha intentando usar una variable que aún no ha sido declarada."))
            }; break;
            case 5: {
                console.log(chalk.gray("Parece que se ha llamado a una función con argumentos de tipos distintos a los que debería."))
            }; break;
            case 6: {
                console.log(chalk.gray("Parece que se ha intentado poner ´return´ en el programa global, es decir, fuera de las funciones."))
            }; break;
            case 7: {
                console.log(chalk.gray("Parece que se ha intentado llamar a una función que necesita", obj.esperado, "argumentos y en cambio se le ha llamado con", obj.devuelto, "argumentos."))
            }; break;
        }
    }
    
    if (errorsVars.posible != null) console.log(chalk.grey(errorsVars.posible))
    process.exit(0)
}
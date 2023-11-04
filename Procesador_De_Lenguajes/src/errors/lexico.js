import chalk from "chalk";
import { errorsVars } from "./errors.js";
import { vars } from "../lexico/lexico.js";

export function _errorLexico(state) {
    switch (state) {
        case "13":
            errorsVars.posible = "¿Es posible que quisiese poner `-=`?"
            break;
        case "22": case "23": case "24":
            errorsVars.posible = "¿Es posible que tenga mal escritos los signos de comentarios: /* , */?"
            break;
        case "26":
            errorsVars.posible = "Recuerde que detras de `\\` en una cadena tiene que haber `'` o bien `\\`"
            break;
        case "12":
            errorsVars.posible = "¿Es posible que quisiese poner `&&`?"
            break;
        case "13":
            errorsVars.posible = "¿Es posible que quisiese poner `||`?"
            break;
    }
}

export function errorLexico() {
    console.log(chalk.bgRed(chalk.bold("[ERROR LÉXICO]")), chalk.red("Caracter inesperado '" + vars.anteriorCharLeido + "' en la linea " + vars.linea + ":" + vars.actualLinea + "."))
    if (errorsVars.posible != null) console.log(chalk.grey(errorsVars.posible))
    process.exit(0)
}
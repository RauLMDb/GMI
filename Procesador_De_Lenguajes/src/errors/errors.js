import { vars } from "../lexico/lexico.js"
import { errorLexico } from "./lexico.js";
import { errorSemantico } from "./semantico.js";
import { errorSintactico } from "./sintactico.js";

export const errorsVars = {
    posible: null,
    regla: null
}

/* --- LEYENDA ------
Primer número:
- 1 => Error léxico
- 2 => Error sintático
- 3 => Error semántico
Segundo número - Orden (00 to 99)
------ FIN LEYENDA */
export const throwError = (code, obj = null) => {
    switch (code.charAt(0)) {
        case "1":
            errorLexico()
            break;
        case "2":
            errorSintactico(obj)
            break;
        case "3":
            errorSemantico(obj)
            break;
        default:
            throw new Error("[ERROR] Error no identificado")
    }
}

import { readFileSync } from "fs"

import { vars } from "./lexico.js";

export function translateChar() {
    const inputFile = readFileSync(process.cwd() + "/input.txt")
    const input = inputFile.toString()
    let delBool = vars.char == " " || vars.char == "\n" || vars.char == "\r" || vars.char == "\t"

    // Tratamos las posibles excepciones del char
    switch (vars.state) {
        //Casos con excepciones que se deben tratar
        case "0" : {
            if (delBool) vars.char = "del"
            if ((65 <= input.charCodeAt(vars.actual) && input.charCodeAt(vars.actual) <= 90)
            || (97 <= input.charCodeAt(vars.actual) && input.charCodeAt(vars.actual) <= 122)
            || vars.charLeido == "_") vars.char = "l"
            if (48 <= input.charCodeAt(vars.actual) && input.charCodeAt(vars.actual) <= 57) vars.char = "d"
        }; break;

        case "2" : {
            vars.char = vars.char == "&" ? vars.char : "o.c."
        }; break;

        case "3" : {
            vars.char = vars.char == "|" ? vars.char : "o.c."
        }; break;

        case "6" : {
            vars.char = (vars.char == "'" || vars.char == "\\" ) ? vars.char : "c1"
            if(vars.char != "c1" && vars.char != "\\" && vars.char != "'") vars.char = "o.c."
        }; break;

        case "7" : {
            if (delBool) vars.char = "del"
            if (48 <= input.charCodeAt(vars.actual) && input.charCodeAt(vars.actual) <= 57) vars.char = "d"
            if(vars.char != "d" && vars.char != "del") vars.char = "o.c."
        }; break;

        case "13" : {
            vars.char = vars.char == "=" ? vars.char : "o.c."
        }; break;

        case "19" : {
            if (!delBool 
                && (("_" == vars.charLeido)
                || (48 <= input.charCodeAt(vars.actual) && input.charCodeAt(vars.actual) <= 57) 
                || (65 <= input.charCodeAt(vars.actual) && input.charCodeAt(vars.actual) <= 90)
                || (97 <= input.charCodeAt(vars.actual) && input.charCodeAt(vars.actual) <= 122))) vars.char = "c4"
            if (vars.char != "c4") vars.char = "c6"
        }; break;
                
        case "22" : vars.char = vars.char == "*" ? vars.char : "o.c"
 
        case "23" : {
            vars.char = vars.char == "*" ? vars.char : "c2"
            if(vars.char != "c2" && vars.char != "*") vars.char = "o.c."
        }; break;   

        case "24" : {
            vars.char = vars.char == "*" || vars.char == "/" ? vars.char : "c3"
            if(vars.char != "c3" && vars.char != "*" && vars.char != "/") vars.char = "o.c."
        }; break;

        case "25" : {
            vars.char = (vars.char == "\\" || vars.char == "'") ? "c5" : "o.c."
        }; break;

        //Casos que inician de nuevo, llevan al "end"
        default : {
            vars.char = "o.c."
        }; break;
    }
}
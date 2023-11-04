import { readFileSync } from "fs"

import { IdToken } from "../databases/data.js";
import { genToken } from "./functions.js";
import { vars } from "./lexico.js"
import { matrizTransiciones } from "./matrizTransiciones.js";
import { errorLexico } from "../errors/lexico.js";
import { errorsVars } from "../errors/errors.js";

export let lexema = null;

// stateFin = Estado final
export function accion(stateIni, stateFin) {
    vars.state = matrizTransiciones[vars.state][vars.char]

    // Alternador de acciones semánticas
    switch (stateIni) {
        // primer nivel
        case "0" : { 
            switch (stateFin) {
                case "0" :case"2" : case "3" : case "13" : case "22" :{
                    leer()
                }; break;
                case "1" : {
                    leer(),
                    genToken( IdToken.SUM )
                }; break;
                case "4" : {
                    leer(),
                    genToken( IdToken.MUL )
                }; break;
                case "6" : {
                    lexema = ""
                   leer()
                }; break;
                case "7" : { 
                    lexema = parseInt(vars.charLeido)
                    leer()
                }; break;
                case "8" : {
                    leer(),
                    genToken( IdToken.ASIGN )
                }; break;
                case "9" : {
                    leer(),
                    genToken( IdToken.PARA )
                }; break;
                case "10" : {
                    leer(),
                    genToken( IdToken.PARC )
                }; break;
                case "11" : {
                    leer(),
                    genToken( IdToken.BRKA )
                }; break;
                case "12" : {
                    leer(),
                    genToken( IdToken.GRT )
                }; break;
                case "15" : {
                    leer(),
                    genToken( IdToken.SCOL )
                }; break;
                case "16" : {
                    leer(),
                    genToken( IdToken.BRKC )
                }; break;
                case "27" : {
                    leer(),
                    genToken( IdToken.COMMA )
                }; break;
                case "17" : {
                    leer(),
                    genToken( IdToken.LOW )
                }; break;
                case "19" : {
                    lexema = vars.charLeido
                    leer()
                }; break;
            }
        } break;
        // segundo nivel
        case "2" :{
            if (stateFin == "5") {
                leer(),
                genToken( IdToken.AND )
            }
        } break;
        case "3" :{
            if (stateFin == "18") {
                leer(),
                genToken( IdToken.OR )
            }
        } break;
        case "13" :{
            if (stateFin == "14") {
                leer(),
                genToken( IdToken.ASIGNSUB )
            }
        } break;
        case "7" :{
            switch (stateFin) {
                case "7" : {
                    valorA(vars.charLeido)
                    leer()
                }; break;
                case "26" : {
                    genToken( IdToken.INTEGER, lexema )
                }; break;
            }
        } break;
        case "6" :{
            switch (stateFin) {
                case "6": case "25" : {
                    concatenar(vars.charLeido)   
                    leer()   
                }; break;
                case "21" : {
                    leer(),
                    genToken( IdToken.CAD, lexema )
                };break;
            }
        } break;
        case "19" :{
            switch (stateFin) {
                case "19" : {
                    concatenar(vars.charLeido) 
                    leer()     
                }; break;
                case "20" :{
                    switch (lexema) {
                        case "boolean" : {
                            genToken( IdToken.BOOL )
                        }; break;
                        case "function" :{
                            genToken( IdToken.FUNC )
                        }; break;
                        case "print" :{
                            genToken( IdToken.PRNT )
                        }; break;
                        case "return" :{
                            genToken( IdToken.RET )
                        }; break;
                        case "input" :{
                            genToken( IdToken.IN )
                        }; break;
                        case "let" :{
                            genToken( IdToken.LET )
                        }; break;
                        case "do" :{
                            genToken( IdToken.DO )
                        }; break;
                        case "while" :{
                            genToken( IdToken.WHILE )
                        }; break;
                        case "int" :{
                            genToken( IdToken.INT )
                        }; break;
                        case "string" :{
                            genToken( IdToken.STRING )
                        }; break;
                        case "false" :{
                            genToken( IdToken.FALSE )
                        }; break;
                        case "true" :{
                            genToken( IdToken.TRUE )
                        }; break;
                        case "if" :{
                            genToken( IdToken.IF )
                        }; break;
                        default: {
                            // Posicion en la tabla de símbolos
                            genToken( IdToken.ID, lexema )
                        }; break;// no es palabra reservada y por tanto lanza otra opcion
                    }
                };break;
            }
        } break;
        case "22" :{
            if (stateFin == "23")
                leer()
        } break;
        // tercer nivel
        case "23" :{
            if (stateFin == "23" || stateFin == "24")
                leer()
        } break;
        case "24" :{
            if (stateFin == "23" || stateFin == "24" || stateFin == "0")
                leer()
        } break;
        case "25" :{
            if (stateFin == "6")
                leer()
        }
        default : leer();break;
    }
}

function leer() {
    const inputFile = readFileSync(process.cwd() + "/input.txt")
    const input = inputFile.toString()
    vars.actual++
    vars.actualLinea++
    if (vars.charLeido == '\n') vars.actualLinea = 0
    vars.charLeido = input[vars.actual]
    vars.char = input[vars.actual]
    return vars.charLeido
}

function valorA(d) {
    if (lexema == null) lexema = 0
    lexema = lexema*10 + parseInt(d)
    if (lexema > 32767 || lexema < -32767) {
        errorsVars.posible = "Recuerde que los números enteros deben que ser menores que 32767"
        errorLexico("100")
    }
}

function concatenar(l) {
    if (lexema == null) lexema = "";
    lexema = lexema + l
    if (lexema.length > 64) {
        errorsVars.posible = "Recuerde que las cadenas deben tener una longitud menor que 64 caracteres"
        errorLexico("100")
    }
}
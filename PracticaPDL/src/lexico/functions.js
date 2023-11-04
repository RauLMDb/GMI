import { IdToken, tablaSimbolos, tokens, globalVars, zonaDecl } from "../databases/data.js"
import { throwError } from "../errors/errors.js"
import { _tipo2despl } from "../semantico/semantico.js"
import { lexema } from "./accionesSemanticas.js"
import { vars } from "./lexico.js"

export function genToken(id, atr = null) {
    if (id == null || id == undefined) throw new Error('No se ha declarado el nombre del identificador')
    if (IdToken[id] == undefined) throw new Error('No existe token con ese identificador')

    const tk = {
        "id": id,
        "atr": atr,
        "ts": null
    }

    let ts = insertTS(tk)

    if (tk.id == IdToken.ID) tk.ts = ts
    if (tk.id == IdToken.ID) tk.atr = posTS(tk.atr, ts)
    if (tk.id == IdToken.CAD) tk.atr = "\"" + tk.atr + "\""

    tokens.push(tk)
    vars.tokenEncontrado = tk

    // restart
    vars.encontrado = true
    vars.state = "0"
}

export function posTS(lexema, ts = null) {
    let pos1
    if (ts == null) pos1 = tablaSimbolos[globalVars.tsActual].tabla.findIndex((row) => row.lexema == lexema) // Buscamos en la TSActual
    else pos1 = tablaSimbolos[ts].tabla.findIndex((row) => row.lexema == lexema)
    let pos2 = tablaSimbolos[0].tabla.findIndex((row) => row.lexema == lexema) // Si no lo encontramos, buscamos en la global

    if (pos1 == -1) return pos2
    return pos1
}

export function insertTS(token) {
    let isCreated;
    let entrada = {
        "nTS" : tablaSimbolos[globalVars.tsActual].tabla.length,
        "id" : token.id,
        "lexema" : token.atr,
        "tipo" : null,
        "despl" : null,
        "numParams" : null,
        "params" : [],
        "tipoRetorno" : null,
        "etiqRetorno" : null,
        "param" : null
    }

    if (token.id == IdToken.ID){
        if (token.ts == null) {
            let isCreated1 = tablaSimbolos[globalVars.tsActual].tabla.find(( tk ) => tk.lexema == entrada.lexema ) != undefined ? true : false;
            let isCreated2 = tablaSimbolos[0].tabla.find(( tk ) => tk.lexema == entrada.lexema ) != undefined ? true : false;

            if (globalVars.zonaDecl) {
                if (isCreated1) throwError("300", {code: 3})
                else {
                    tablaSimbolos[globalVars.tsActual].tabla.push(entrada)
                    return globalVars.tsActual
                }
            } else {
                if (isCreated1) return globalVars.tsActual
                else if (isCreated2) return 0
                else {
                    entrada.tipo = "ENTERO"
                    entrada.despl = tablaSimbolos[0].despl
                    tablaSimbolos[0].tabla.push(entrada)
                    tablaSimbolos[0].despl += _tipo2despl("ENTERO")
                    return 0
                }
            }
        } else {
            let isCreated1 = tablaSimbolos[token.ts].tabla.find(( tk ) => tk.lexema == entrada.lexema ) != undefined ? true : false;
            let isCreated2 = tablaSimbolos[0].tabla.find(( tk ) => tk.lexema == entrada.lexema ) != undefined ? true : false;

            if (zonaDecl) {
                if (isCreated1) throwError("300", {code: 3})
                else {
                    tablaSimbolos[token.ts].tabla.push(entrada)
                    return token.ts
                }
            } else {
                if (isCreated1) return token.ts
                else if (isCreated2) return 0
                else {
                    entrada.tipo = "ENTERO"
                    entrada.despl = tablaSimbolos[0].despl
                    tablaSimbolos[0].tabla.push(entrada)
                    tablaSimbolos[0].despl += _tipo2despl(object.tipo)
                    return 0
                }
            }
        }
    }

    return -1
}
import { globalVars, parse, tablaSimbolos, zonaDecl } from "../databases/data.js"
import { throwError } from "../errors/errors.js"
import { lexema } from "../lexico/accionesSemanticas.js"
import { posTS } from "../lexico/functions.js"
import { lexico, vars } from "../lexico/lexico.js"
import { actualizar_TS, buscar_Index_TS, buscar_TS, create_TS, destruir_TS, name_TS } from "../semantico/semantico.js"

export let tkActual = {id: null, atr: null, ts: null}

export let tipos = {
    Tipo_ok: "tipo_ok",
    Tipo_error: "tipo_error",
    ENTERO: "ENTERO",
    CADENA: "CADENA",
    BOOLEANO: "BOOLEANO",
    NULL: "NULL",
    VOID: "VOID",
    FUNC: "FUNC",
    SUB_ASIGN: "SUB_ASIGN",
    ASIGN: "ASIGN"
}

export function p1(){
    // Primera ejecución del léxico
    tkActual = lexico()

    let f = ["DO","ID","IF","IN","LET","PRNT","RET","FUNC"]

    if (f.includes(tkActual.id) || tkActual.id == null){
        parse.push(72)
        globalVars.despl = 0
        globalVars.tsActual = (buscar_Index_TS("GLOBAL"))
        let P = p()
        destruir_TS(globalVars.tsActual)
    }

    // Vemos si hay errores en el último token
    if (tkActual.id == null) return;
    else throwError("200")
}

function p(){
    let f1 = ["DO","ID","IF","IN","LET","PRNT","RET"]
    let f2 = ["FUNC"]
    if (f1.includes(tkActual.id)){
        parse.push(1)
        let Q = q()
        let P = p()
    }else if (f2.includes(tkActual.id)){
        parse.push(2)
        let F = f()
        let P = p()
    }else if (tkActual.id == null){
        parse.push(3)
    }
    
}
function o1(){
    let f4 = ["PARA","CAD","INTEGER","ID","FALSE","TRUE"]

    // Se crea variable res que contiene los atributos 
    let res = {
        varTipo: null,
        tipo: null
    }

    if(f4.includes(tkActual.id)){
        parse.push(4)
        let O2 = o2()

        let O1O1 = o1o1({varTipo: O2.varTipo})

        //Regla Semántica Definida
        if (O1O1.varTipo == tipos.NULL) {
            res.varTipo = O2.varTipo
        } else {
            res.varTipo = O1O1.varTipo
        }

        if(O2.tipo == tipos.Tipo_ok && O1O1.tipo == tipos.Tipo_ok) {
            res.tipo = tipos.Tipo_ok
        } else {
            res.tipo = tipos.Tipo_error
            throwError("300")
        }
    } else throwError("200", {arr: [...f4]})

    return res    
}
function o1o1(obj){
    let f5 = ["OR"]
    let f6 = ["PARC","COMMA","SCOL"]
    // Variable res
    let res = {
        varTipo: null,
        tipo: null
    }
    if (f5.includes(tkActual.id)){
        parse.push(5)
        tkActual = lexico()
        let O2 = o2()

        //Regla Semántica Definida
        if(O2.varTipo == obj.varTipo && obj.varTipo == tipos.BOOLEANO) {
            res.varTipo = tipos.BOOLEANO
            res.tipo = tipos.Tipo_ok
        } else {
            res.varTipo = tipos.Tipo_error
            res.tipo = tipos.Tipo_error
            //Gestionar error
            throwError("300", {code: 0, tipos: ["BOOOLEANO"], devuelto: O2.varTipo})
        }

        let O1O1 = o1o1({varTipo: res.varTipo})

    }else if (f6.includes(tkActual.id)){
        parse.push(6)
        //lambda
        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.varTipo = tipos.NULL
    }else throwError("200", {arr: [...f5, ...f6]})
        return res
    
}
function o2(){
    let f7 = ["PARA","CAD","INTEGER","ID","FALSE","TRUE"]
    //Variable res
    let res = {
        varTipo: null,
        tipo: null
    }
    if (f7.includes(tkActual.id)){
        parse.push(7)

        let O3 = o3()

        let O2O2 = o2o2({varTipo: O3.varTipo})

        //Regla Semántica Definida
        if (O2O2.varTipo == tipos.NULL) {
            res.varTipo = O3.varTipo
        } else {
            res.varTipo = O2O2.varTipo
        }

        if(O3.tipo = tipos.Tipo_ok && O2O2.tipo == tipos.Tipo_ok) {
            res.tipo = tipos.Tipo_ok
        } else {
            res.tipo = tipos.Tipo_error
            //Gestionar error
            throwError("300")
        }
    }else throwError("200", {arr: [...f7]})
        return res
}
function o2o2(obj){
    let f9 = ["AND"]
    let f10 = ["PARC","COMMA","SCOL","OR"]
    //Variable res
    let res = {
        varTipo: null,
        tipo: null
    }
    if(f9.includes(tkActual.id)){
        parse.push(8)
        tkActual = lexico()
        let O3 = o3()

        //Regla Semántica Definida
        if(O3.varTipo == obj.varTipo && obj.varTipo == tipos.BOOLEANO) {
            res.varTipo = tipos.BOOLEANO
            res.tipo = tipos.Tipo_ok
        } else {
            res.varTipo = tipos.Tipo_error
            res.tipo = tipos.Tipo_error
            //Gestionar error
            throwError("300")
        }

        let O2O2 = o2o2({varTipo: res.varTipo})
    }else if (f10.includes(tkActual.id)){
        parse.push(9)
        //lambda
        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.varTipo = tipos.NULL
    }else throwError("200", {arr: [...f9, ...f10]})
        return res
    
}

function o3(){
    let f11 = ["PARA","CAD","INTEGER","ID","FALSE","TRUE"]
    //Varaible res
    let res = {
        varTipo: null,
        tipo: null
    }
    if(f11.includes(tkActual.id)){
        parse.push(10)
        let O4 = o4()

        let O3O3 = o3o3({varTipo: O4.varTipo})

        //Regla Semántica Definida
        if (O3O3.varTipo == tipos.NULL) {
            res.varTipo = O4.varTipo
        } else {
            res.varTipo = O3O3.varTipo
        }

        if(O4.tipo == tipos.Tipo_ok && O3O3.tipo == tipos.Tipo_ok) {
            res.tipo = tipos.Tipo_ok
        } else {
            res.tipo = tipos.Tipo_error
            //Gestionar error
            throwError("300")
        }
    }else throwError("200", {arr: [...f11]})
        return res
    
}

function o3o3(obj){
    let f12 = ["GRT"]
    let f13 = ["AND","PARC","COMMA","SCOL","OR"]
    //Variable res
    let res = {
        varTipo: null,
        tipo: null
    }
    if(f12.includes(tkActual.id)){
        parse.push(11)
        tkActual = lexico()
        let O4 = o4()

        //Regla Semántica Definida
        if(O4.varTipo == obj.varTipo && obj.varTipo == tipos.ENTERO) {
            res.varTipo = tipos.BOOLEANO
            res.tipo = tipos.Tipo_ok
        } else {
            res.varTipo = tipos.Tipo_error
            res.tipo = tipos.Tipo_error
            //Gestionar error
            if (obj.varTipo != tipos.ENTERO) {
                throwError("300", {code: 0, tipos: ["ENTERO"], devuelto: obj.varTipo})
            } else {
                throwError("300", {code: 0, tipos: ["ENTERO"], devuelto: O4.varTipo})
            }
        }
        
        let O3O3 = o3o3({varTipo: res.varTipo})
    }else if (f13.includes(tkActual.id)){
        parse.push(12)
        //lambda
        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.varTipo = tipos.NULL
    }else throwError("200", {arr: [...f12, ...f13]})
        return res
    
}

function o4(){
    let f14 = ["PARA","CAD","INTEGER","ID","FALSE","TRUE"]
    //Variable res
    let res = {
        varTipo: null,
        tipo: null
    }
    if(f14.includes(tkActual.id)){
        parse.push(13)
        let O5 = o5()

        let O4O4 = o4o4({varTipo: O5.varTipo})

        //Regla Semántica Definida
        if (O4O4.varTipo == tipos.NULL) {
            res.varTipo = O5.varTipo
        } else {
            res.varTipo = O4O4.varTipo
        }

        if(O5.tipo == tipos.Tipo_ok && O4O4.tipo == tipos.Tipo_ok) {
            res.tipo = tipos.Tipo_ok
        } else {
            res.tipo = tipos.Tipo_error
            //Gestionar error
            throwError("300")
        }
    }else throwError("200", {arr: [...f14]})
        return res
}

function o4o4(obj){
    let f15 = ["LOW"]
    let f16 = ["AND","GRT","PARC","COMMA","SCOL","OR"]
    //Variable res
    let res = {
        varTipo: null,
        tipo: null
    }
    if(f15.includes(tkActual.id)){
        parse.push(14)
        tkActual = lexico()
        let O5 = o5()

        //Regla Semántica Definida
        if(O5.varTipo == obj.varTipo && obj.varTipo == tipos.ENTERO) {
            res.varTipo = tipos.BOOLEANO
            res.tipo = tipos.Tipo_ok
        } else {
            res.varTipo = tipos.Tipo_error
            res.tipo = tipos.Tipo_error
            //Gestionar error
            if (obj.varTipo != tipos.ENTERO) {
                throwError("300", {code: 0, tipos: ["ENTERO"], devuelto: obj.varTipo})
            } else {
                throwError("300", {code: 0, tipos: ["ENTERO"], devuelto: O5.varTipo})
            }
        }

        let O4O4 = o4o4({varTipo: res.varTipo})
    }else if (f16.includes(tkActual.id)){
        parse.push(15)
        //lambda
        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.varTipo = tipos.NULL
    }else throwError("200", {arr: [...f15, ...f16]})
        return res    
}

function o5(){
    let f17 = ["PARA","CAD","INTEGER","ID","FALSE","TRUE"]
    //Variable res
    let res = {
        varTipo: null,
        tipo: null
    }
    if(f17.includes(tkActual.id)){
        parse.push(16)
        let O6 = o6()

        let O5O5 = o5O5({varTipo: O6.varTipo})

        //Regla Semántica Definida
        if (O5O5.varTipo == tipos.NULL) {
            res.varTipo = O6.varTipo
        } else {
            res.varTipo = O5O5.varTipo
        }

        if(O6.tipo == tipos.Tipo_ok && O5O5.tipo == tipos.Tipo_ok){
            res.tipo = tipos.Tipo_ok
        }else{
            res.tipo = tipos.Tipo_error
            //Gestionar error
            throwError("300")
        }
    }else throwError("200", {arr: [...f17]})
        return res
}

function o5O5(obj){
    let f18 = ["SUM"]
    let f19 = ["AND","LOW","GRT","PARC","COMMA","SCOL","OR"]
    //Variable res
    let res = {
        varTipo: null,
        tipo: null
    }
    if(f18.includes(tkActual.id)){
        parse.push(17)
        tkActual = lexico()
        let O6 = o6()

        //Regla Semántica Definida
        if(O6.varTipo == obj.varTipo && obj.varTipo == tipos.ENTERO){
            res.varTipo = tipos.ENTERO
            res.tipo = tipos.Tipo_ok
        }else{
            res.varTipo = tipos.Tipo_error
            res.tipo = tipos.Tipo_error
            //Gestionar error
            if (obj.varTipo != tipos.ENTERO) {
                throwError("300", {code: 0, tipos: ["ENTERO"], devuelto: obj.varTipo})
            } else {
                throwError("300", {code: 0, tipos: ["ENTERO"], devuelto: O6.varTipo})
            }
        }

        let O5O5 = o5O5({varTipo: res.varTipo})
    }else if (f19.includes(tkActual.id)){
        parse.push(18)
        //lambda
        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.varTipo = tipos.NULL
    }else throwError("200", {arr: [...f18, ...f19]})
        return res
}

function o6(){
    let f20 = ["PARA","CAD","INTEGER","ID","FALSE","TRUE"]
    //Variable res
    let res = {
        varTipo: null,
        tipo: null
    }
    if(f20.includes(tkActual.id)){
        parse.push(19)
        let E = e()

        let O6O6 = o6o6({varTipo: E.varTipo})

        //Regla Semántica Definida
        if (O6O6.varTipo == tipos.NULL) {
            res.varTipo = E.varTipo
        } else {
            res.varTipo = O6O6.varTipo
        }

        if(E.tipo == tipos.Tipo_ok && O6O6.tipo == tipos.Tipo_ok){
            res.tipo = tipos.Tipo_ok
        }else{
            res.tipo = tipos.Tipo_error
            //Gestionar error
            throwError("300")
        }
    }else throwError("200", {arr: [...f20]})
        return res
}
function o6o6(obj){
    let f21 = ["MUL"]
    let f22 = ["AND","SUM","LOW","GRT","PARC","COMMA","SCOL","OR"]
    //Variable res
    let res = {
        varTipo: null,
        tipo: null
    }
    if(f21.includes(tkActual.id)){
        parse.push(20)
        tkActual = lexico()
        let E = e()

        //Regla Semántica Definida
        if(E.varTipo == obj.varTipo && obj.varTipo == tipos.ENTERO){
            res.varTipo = tipos.ENTERO
            res.tipo = tipos.Tipo_ok
        }else{
            res.varTipo = tipos.Tipo_error
            res.tipo = tipos.Tipo_error
            if (obj.varTipo != tipos.ENTERO) {
                throwError("300", {code: 0, tipos: ["ENTERO"], devuelto: obj.varTipo})
            } else {
                throwError("300", {code: 0, tipos: ["ENTERO"], devuelto: E.varTipo})
            }
        }
        
        let O6O6 = o6o6({varTipo: res.varTipo})
    }else if (f22.includes(tkActual.id)){
        parse.push(21)
        //lambda
        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.varTipo = tipos.NULL
    }else throwError("200", {arr: [...f21, ...f22]})
        return res
}

function e(){
    let f23 = ["ID"]
    let f24 = ["PARA"]
    let f25 = ["INTEGER"]
    let f26 = ["CAD"]
    let f27 = ["TRUE"]
    let f28 = ["FALSE"]
    //Variable res
    let res = {
        tipo: null,
        varTipo: null
    }

    if(f23.includes(tkActual.id)){
        parse.push(22)

        let EE_obj = {
            varTipo: undefined, 
            numParams: undefined, 
            params: undefined
        }

        let tkActualID = buscar_TS(tkActual.ts, tkActual.atr)
        EE_obj.varTipo = tkActualID.tipo
        if(tkActualID.tipo == tipos.FUNC) {
            EE_obj.numParams = tkActualID.numParams
            EE_obj.params = tkActualID.params
        }else{
            EE_obj.numParams = tipos.NULL
            EE_obj.params = tipos.NULL
        }

        tkActual = lexico()
        let EE = ee(EE_obj)

        //Regla Semántica Definida
        if(EE_obj.varTipo == tipos.FUNC) {
            res.varTipo = tkActualID.tipoRetorno
        }else {
            res.varTipo = EE_obj.varTipo
        }
        res.tipo = EE.tipo

    }else if (f24.includes(tkActual.id)){
        parse.push(23)
        tkActual = lexico()
        let O1 = o1()
        if (tkActual.id == "PARC"){
            tkActual = lexico()
        }else throwError("200", {arr: ["PARC"]})

        //Regla Semántica Definida
        res.varTipo = O1.varTipo
        res.tipo = O1.tipo
    }else if (f25.includes(tkActual.id)){
        parse.push(24)
        tkActual = lexico()

        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.varTipo = tipos.ENTERO
    }else if (f26.includes(tkActual.id)){
        parse.push(25)
        tkActual = lexico()

        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.varTipo = tipos.CADENA
    }else if (f27.includes(tkActual.id)){
        parse.push(26)
        tkActual = lexico()

        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.varTipo = tipos.BOOLEANO
    }else if (f28.includes(tkActual.id)){
        parse.push(27)
        tkActual = lexico()

        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.varTipo = tipos.BOOLEANO
    }else throwError("200", {arr: [...f23, ...f24, ...f25, ...f26, ...f27, ...f28]})
        return res
}
function ee(obj){
    let f29 = ["PARA"]
    let f30 = ["AND","SUM","LOW","GRT","PARC","COMMA","SCOL","OR","MUL"]
    let res = {
        numParams: 0,
        tipo: null,
        params: []
    }
    if(f29.includes(tkActual.id)){
        parse.push(28)
        
        tkActual = lexico()
        let E1 = e1()

        //Regla Semántica Definida
        obj.numParams = obj.numParams == null ? 0 : obj.numParams
        if(E1.numParams == obj.numParams && JSON.stringify(obj.params) == JSON.stringify(E1.params)) {
            res.tipo = tipos.Tipo_ok
        } else if (E1.numParams != obj.numParams) {
            res.tipo = E1.tipo
            throwError("300", {code: 7, esperado: obj.numParams, devuelto: E1.numParams})
        } else throwError("300", {code: 5, esperado: obj.params, devuelto: E1.params})

        if(tkActual.id == "PARC"){
            tkActual = lexico()
        }else throwError("200", {arr: ["PARC"]})

    }else if (f30.includes(tkActual.id)){
        parse.push(29)
        //lambda

        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok

    }else throwError("200", {arr: [...f30, ...f29]})
        return res
    
}
function e1(){
    let f31 = ["PARA","CAD","INTEGER","ID","FALSE","TRUE"]
    let f32 = ["PARC"]
    let res = {
        numParams: 0,
        tipo: null,
        params: []
    }
    if(f31.includes(tkActual.id)){
        parse.push(30)
        let O1 = o1()
        let E2 = e2()

        //Regla Semántica Definida
        if(O1.tipo == tipos.Tipo_ok && E2.tipo == tipos.Tipo_ok) {
            res.tipo = tipos.Tipo_ok
        }else{
            res.tipo = tipos.Tipo_error
            throwError("300")
        }

        res.numParams = 1 + E2.numParams
        res.params = [O1.varTipo, ...E2.params]
    }else if (f32.includes(tkActual.id)){
        parse.push(31)
        //lambda

        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.numParams = 0
        res.params = []
    }else throwError("200", {arr: [...f31, ...f32]})
        return res
}
function e2(){
    let f33 = ["COMMA"]
    let f70 = ["PARC"]
    let res = {
        tipo: null,
        numParams: 0,
        params: []
    }
    if(f33.includes(tkActual.id)){
        parse.push(32)
        tkActual = lexico()
        let O1 = o1()
        let E2 = e2()

        //Regla Semántica Definida
        if(O1.tipo == tipos.Tipo_ok && E2.tipo == tipos.Tipo_ok){
            res.tipo = tipos.Tipo_ok
        }else{
            res.tipo = tipos.Tipo_error
            throwError("300")
        }
        res.numParams = 1 + E2.numParams
        res.params = [O1.varTipo, ...E2.params]
    }else if (f70.includes(tkActual.id)){
        parse.push(70)
        //lambda

        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.numParams = 0
        res.params = []
    }else throwError("200", {arr: [...f33, ...f70]})
    
    return res
}

function s1(){
    let f34 = ["ID"]
    let res = {
        tipo: null
    }
    if(f34.includes(tkActual.id)){
        parse.push(33)
        let tkActualATR = tkActual.atr
        let tkActualid = buscar_TS(tkActual.ts, tkActualATR)

        tkActual = lexico()

        let S2 = s2({atr: tkActualATR, tk: tkActualid})

        if(S2.tipo == tipos.FUNC) {
            tkActualid = buscar_TS(0, tkActualATR)
            if(tkActualid.tipo == tipos.FUNC) {
                res.tipo = tipos.Tipo_ok
            } else {
                res.tipo = tipos.Tipo_error
                throwError("300", {code: 0, tipos: ["FUNC"], devuelto: tkActualid.tipo})
            }
        } else if (S2.tipo == tipos.ASIGN) {
            if(tkActualid.tipo == S2.varTipo) {
                res.tipo = tipos.Tipo_ok
            } else {
                res.tipo = tipos.Tipo_error
                throwError("300", {code: 0, tipos: ["ASIGN"], devuelto: S2.varTipo, esperado: tkActualid.tipo})
            }
        } else if (S2.tipo == tipos.SUB_ASIGN) {
            if(tkActualid.tipo == tipos.ENTERO && S2.varTipo == tipos.ENTERO) {
                res.tipo = tipos.Tipo_ok
            } else {
                res.tipo = tipos.Tipo_error
                throwError("300", {code: 0, tipos: ["SUB_ASIGN"], devuelto: S2.varTipo, esperado: tkActualid.tipo})
            }
        }
    }else throwError("200", {arr: [...f34]})
    
    return res
}

function s2(obj){
    let f35 = ["ASIGN"]
    let f36 = ["PARA"]
    let f71 = ["ASIGNSUB"]
    let res = {
        tipo: null,
        varTipo: null,
        numParams: 0,
        params: []
    }

    if(f35.includes(tkActual.id)){
        parse.push(34)
        tkActual = lexico()
        let O1 = o1()

        if(tkActual.id == "SCOL"){
            tkActual = lexico()
        }else throwError("200", {arr: ["SCOL"]})

        //Regla Semántica Definida
        if (O1.varTipo == obj.tk.tipo){
            res.tipo = tipos.ASIGN
            res.varTipo = O1.varTipo
        } else throwError("300", {code: 0, tipos: ["ASIGN"], devuelto: O1.varTipo, esperado: obj.tk.tipo})
    }else if (f36.includes(tkActual.id)) {
        parse.push(35)
        tkActual = lexico()
        let E1 = e1()
        if(tkActual.id == "PARC"){
            tkActual = lexico()
        }else throwError("200", {arr: ["PARC"]})
        if(tkActual.id == "SCOL"){
            tkActual = lexico()
        }else throwError("200", {arr: ["SCOL"]})

        let tkActualID = buscar_TS(0, obj.atr)

        //Regla Semántica Definida
        tkActualID.numParams = tkActualID.numParams == null ? 0 : tkActualID.numParams
        if(tkActualID.numParams == E1.numParams && JSON.stringify(tkActualID.params) == JSON.stringify(E1.params)) {
            res.tipo = tipos.FUNC
        } else if (tkActualID.numParams != E1.numParams) {
            res.tipo = tipos.Tipo_error
            throwError("300", {code: 7, esperado: tkActualID.numParams, devuelto: E1.numParams})
        } else throwError("300", {code: 5, esperado: tkActualID.params, devuelto: E1.params})
        res.varTipo = tipos.Tipo_ok

    }else if(f71.includes(tkActual.id)){
        parse.push(71)
        tkActual = lexico()
        let O1 = o1()
        if(tkActual.id == "SCOL"){
            tkActual = lexico()
        }else throwError("200", {arr: ["SCOL"]})

        //Regla Semántica Definida
        res.tipo = tipos.SUB_ASIGN
        res.varTipo = O1.varTipo
    }else throwError("200", {arr: [...f35, ...f36, ...f71]})
        return res
    
}
function s3(){
    let f37 = ["PRNT"]
    let res = {
        tipo: null
    }
    if(f37.includes(tkActual.id)){
        parse.push(36)
        tkActual = lexico()
        let O1 = o1()
        if(tkActual.id == "SCOL"){
            tkActual = lexico()
        }else throwError("200", {arr: ["SCOL"]})

        //Regla Semántica Definida
        if(O1.varTipo == tipos.ENTERO || O1.varTipo == tipos.CADENA) {
            res.tipo = tipos.Tipo_ok
        }else{
            res.tipo = tipos.Tipo_error
            throwError("300", {code: 0, tipos: ["CADENA", "ENTERO"], devuelto: O1.varTipo})
        }
    }else throwError("200", {arr: [...f37]})
        return res
    
}

function s4(){
    let f38 = ["IN"]
    let res = {
        tipo: null
    }
    if(f38.includes(tkActual.id)){
        parse.push(37)
        tkActual = lexico()
        let tkActualID

        if(tkActual.id == "ID"){
            tkActualID = buscar_TS(globalVars.tsActual,tkActual.atr)
            tkActual = lexico()
        }else throwError("200", {arr: ["ID"]})

        if(tkActual.id == "SCOL"){
            tkActual = lexico()
        }else throwError("200", {arr: ["SCOL"]})

        //Regla Semántica Definido
        if(tkActualID.tipo == tipos.ENTERO || tkActualID.tipo == tipos.CADENA) {
            res.tipo = tipos.Tipo_ok
        } else {
            res.tipo = tipos.Tipo_error
            throwError("300", {code: 0, tipos: ["CADENA", "ENTERO"], devuelto: tkActualID.varTipo})
        }
    }else throwError("200", {arr: [...f38]})
        return res
    
}

function s5(){
    let f39 = ["RET"]
    let res = {
        tipo: null,
        func: null
    }
    if(f39.includes(tkActual.id)){
        parse.push(38)
        tkActual = lexico()

        let S6 = s6()
        
        if(tkActual.id == "SCOL"){
            tkActual = lexico()
        }else throwError("200", {arr: ["SCOL"]})
        
        //Regla Semántica Definida
        res.func = name_TS(globalVars.tsActual)
        if(res.func != "GLOBAL") {
            if(buscar_TS(0, posTS(res.func, 0)).tipoRetorno == S6.varTipo){
                res.tipo = tipos.Tipo_ok
            } else {
                res.tipo = tipos.Tipo_error
                throwError("300", {code: 2, esperado: buscar_TS(0, posTS(res.func, 0)).tipoRetorno, devuelto: S6.varTipo})
            }
        }else throwError("300", {code: 6})
    }else throwError("200", {arr: [...f39]})
    
    return res
}
function s6(){
    let f40 = ["PARA","CAD","INTEGER","ID","OR","FALSE","TRUE"]
    let f = ["SCOL"]
    let res = {
        tipo: null,
        varTipo: null
    }
    if(f40.includes(tkActual.id)){
        parse.push(39)
        let O1 = o1()

        //Regla Semántica Definida
        res.tipo = O1.tipo
        res.varTipo = O1.varTipo
    }else if (f.includes(tkActual.id)){
        //lambda
        parse.push(73)

        //Regla Semántica Definida
        res.tipo = tipos.VOID
    }else throwError("200", {arr: [...f40,]})
        return res
    
}

function s(){
    let f41 = ["ID"]
    let f42 = ["PRNT"]
    let f43 = ["IN"]
    let f44 = ["RET"]
    let res = {
        tipo: null,
        return: null
    }

    if(f41.includes(tkActual.id)){
        parse.push(40)
        let S1 = s1()

        //Regla Semántica Definida
        res.tipo = S1.tipo
        res.return = false
    }else if (f42.includes(tkActual.id)){
        parse.push(41)
        let S3 = s3()

        //Regla Semántica Definida
        res.tipo = S3.tipo
        res.return = false
    }else if (f43.includes(tkActual.id)){
        parse.push(42)
        let S4 = s4()

        //Regla Semántica Definida
        res.tipo = S4.tipo
        res.return = false
    }else if (f44.includes(tkActual.id)){
        parse.push(43)
        let S5 = s5()
        //Regla Semántica Definida
        res.tipo = S5.tipo
        res.return = true
    }else throwError("200", {arr: [...f41, ...f42, ...f43, ...f44]})
        return res
    
}
function q1(){
    let f45 = ["IF"]
    let res = {
        tipo: null
    }
    if(f45.includes(tkActual.id)){
        parse.push(44)
        tkActual = lexico()
        if(tkActual.id == "PARA"){
            tkActual = lexico()
        }else throwError("200", {arr: ["PARA"]})

        let O1 = o1()
        if(tkActual.id == "PARC"){
            tkActual = lexico()
        }else throwError("200", {arr: ["PARC"]})
        let Q1Q1 = q1q1()

        //Regla Semántica Definida
        if(O1.varTipo == tipos.BOOLEANO){
            res.tipo = Q1Q1.tipo
        }else{
            res.tipo = tipos.Tipo_error
            throwError("300", {code: 0, tipos: ["BOOLEANO"], devuelto: O1.varTipo})
        }

        Q1Q1.funcLex = tipos.NULL
    }else throwError("200", {arr: [...f45]})
        return res
   
}
function q1q1(){
    let f46 = ["ID","IN","PRNT","RET"]
    let f47 = ["BRKA"]
    let res = {
        tipo: null,
        return: null
    }
    if(f46.includes(tkActual.id)){
        parse.push(45)
        let S = s()

        //Regla Semántica Definida
        res.tipo = S.tipo
        res.return = S.return
    } else if (f47.includes(tkActual.id)){
        parse.push(46)
        let Q1Q1Q1 = q1q1q1()
        let Q1Q1Q1Q1 = q1q1q1q1()

        //Regla Semántica Definida
        if(Q1Q1Q1.tipo == tipos.Tipo_ok && Q1Q1Q1Q1.tipo == tipos.Tipo_ok){
            res.tipo = tipos.Tipo_ok
        }else{
            res.tipo = tipos.Tipo_error
            throwError("300")
        }
        res.return = Q1Q1Q1.return && Q1Q1Q1Q1.return
    } else throwError("200", {arr: [...f46, ...f47]})
    
    return res
}
function q1q1q1q1(){
    let f48 = ["ELSE"]
    let f49 = ["DO","FUNC","ID","IF","IN","LET","PRNT","RET","BRKC",null]
    let res = {
        tipo: null,
        return: null
    }
    if(f48.includes(tkActual.id)){
        parse.push(47)
        tkActual = lexico()
        let Q1Q1Q1 = q1q1q1()

        //Regla Semántica Definida
        res.tipo = Q1Q1Q1.tipo
        res.return = Q1Q1Q1.return
    }else if (f49.includes(tkActual.id)){
        parse.push(48)
        //lambda
        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.return = false
    }else throwError("200", {arr: [...f48, ...f49]})
    
    return res
}
function q1q1q1(){
    let f50 = ["BRKA"]

    let res = {
        tipo: null,
        return: null
    }

    if(f50.includes(tkActual.id)){
        parse.push(49)
        tkActual = lexico()
        let C = c()
        if(tkActual.id == "BRKC"){
            tkActual = lexico()
        }else throwError("200", {arr: ["BRKC"]})

        //Regla Semántica Definida
        res.tipo = C.tipo
        res.return = C.return
    }else throwError("200", {arr: [...f50]})
        
    return res   
}

function q2(){
    let f51 = ["LET"]
    let res = {
        tipo: null
    }

    if(f51.includes(tkActual.id)){
        parse.push(50)

        globalVars.zonaDecl = true
        tkActual = lexico()
        globalVars.zonaDecl = false
        
        let tkActualID;

        if(tkActual.id == "ID"){
            tkActualID = buscar_TS(globalVars.tsActual, tkActual.atr)
            tkActual = lexico()
        }else throwError("200", {arr: ["ID"]})

        let T = t()

        if(tkActual.id == "SCOL"){
            tkActual = lexico()
        }else throwError("200", {arr: ["SCOL"]})

        //Regla Semántica Definida
        actualizar_TS(globalVars.tsActual, tkActualID.nTS, {tipo: T.tipo})
    }else throwError("200", {arr: [...f51]})
    
    return res
}
function q3(){
    let res = {
        tipo: null,
        return: null
    }
    let f52 = ["ID","IN","PRNT","RET"]
    
    if(f52.includes(tkActual.id)){
        parse.push(51)
        let S = s()

        //Regla Semántica Definida
        res.return = S.return
        res.tipo = S.tipo
    }else throwError("200", {arr: [...f52]})
    
    return res
}

function q4(){
    let f53 = ["DO"]
    let res = {
        tipo: null
    }
    if(f53.includes(tkActual.id)){
        parse.push(52)
        let Q5 = q5()

        //Regla Semántica Definida
        res.tipo = Q5.tipo
    }else throwError("200", {arr: [...f53]})
        return res  
}
function t(){
    let f54 = ["INT"]
    let f55 = ["STRING"]
    let f56 = ["BOOL"]
    let res = {
        tipo: null
    }
    if(f54.includes(tkActual.id)){
        parse.push(54)
        tkActual = lexico()
        
        //Regla Semántica Definida
        res.tipo = tipos.ENTERO
    }else if (f55.includes(tkActual.id)){
        parse.push(53)
        tkActual = lexico()
        
        //Regla Semántica Definida
        res.tipo = tipos.CADENA
    }else if(f56.includes(tkActual.id)){
        parse.push(55)
        tkActual = lexico()

        //Regla Semántica Definida
        res.tipo = tipos.BOOLEANO
    }else throwError("200", {arr: [...f54, ...f55, ...f56]})
        return res
}

function q5(){
    let f57 = ["DO"]
    let res = {
        tipo: null,
        return: null
    }
    if(f57.includes(tkActual.id)){
        parse.push(56)

        tkActual = lexico()

        let Q1Q1Q1 = q1q1q1()

        if(tkActual.id == "WHILE"){
            tkActual = lexico()
        }else throwError("200", {arr: ["WHILE"]})

        if(tkActual.id == "PARA"){
            tkActual = lexico()
        }else throwError("200", {arr: ["PARA"]})

        let O1 = o1()

        if ( O1.varTipo != tipos.BOOLEANO ) throwError("300", {code: 0, devuelto: O1.varTipo, esperado: tipos.BOOLEANO})

        if(tkActual.id == "PARC"){
            tkActual = lexico()
        }else throwError("200", {arr: ["PARC"]})

        if(tkActual.id == "SCOL"){
            tkActual = lexico()
        }else throwError("200", {arr: ["SCOL"]})

        //Regla Semántica Definida
        if(Q1Q1Q1.tipo == tipos.Tipo_ok && O1.tipo == tipos.Tipo_ok) {
            res.tipo = tipos.Tipo_ok
        }else{
            res.tipo = tipos.Tipo_error
            throwError("300")
        }
        res.return = Q1Q1Q1.return
    }else throwError("200", {arr: [...f57]})
        return res
    
}
function q(){
    let f58 = ["IF"]
    let f59 = ["LET"]
    let f60 = ["ID","IN","PRNT","RET"]
    let f61 = ["DO"]
    let res = {
        tipo: null,
        return: null
    }
    if(f58.includes(tkActual.id)){
        parse.push(57)
        let Q1 = q1()

        //Regla Semántica Definida
        res.tipo = Q1.tipo
        res.return = Q1.return
    }else if (f59.includes(tkActual.id)){
        parse.push(58)
        let Q2 = q2()

        //Regla Semántica Definida
        res.tipo = Q2.tipo
    }else if (f60.includes(tkActual.id)){
        parse.push(59)
        let Q3 = q3()

        //Regla Semántica Definida
        res.return = Q3.return
        res.tipo = Q3.tipo
    }else if (f61.includes(tkActual.id)){
        parse.push(60)
        let Q4 = q4()

        //Regla Semántica Definida
        res.tipo = Q4.tipo
    }else throwError("200", {arr: [...f58, ...f59, ...f60, ...f61]})
    
    return res
}

function f(){
    let f62 = ["FUNC"]
    let res = {
        tipo: null,
        lexema: null
    }
    if(f62.includes(tkActual.id)){
        parse.push(61)

        globalVars.zonaDecl = true
        tkActual = lexico()
        let tkActualID;

        if(tkActual.id == "ID"){
            tkActualID = buscar_TS(globalVars.tsActual,tkActual.atr)
            tkActual = lexico()
        }else throwError("200", {arr: ["ID"]})

        globalVars.tsActual = create_TS(tkActualID.lexema)
        let FF = ff(tkActualID)

        //Regla Semántica Definida
        res.tipo = tipos.FUNC

        if(FF.tipo == tipos.Tipo_ok){
            res.tipo = tipos.FUNC
        }else{
            res.tipo = tipos.Tipo_error
            throwError("300")
        }

    }else throwError("200", {arr: [...f62]})
    
    return res
    
}

function ff(obj){
    let f63 = ["INT", "STRING","BOOL"]
    let f64 = ["PARA"]
    let res = {
        tipo: null,
        lexema: null
    }
    if(f63.includes(tkActual.id)){
        parse.push(62)

        let T = t()
        if(tkActual.id == "PARA"){
            tkActual = lexico()
        }else throwError("200", {arr: ["PARA"]})

        let K1 = k1()

        //Se procede a actualizar la TS
        actualizar_TS(0, obj.nTS, {
            tipo: tipos.FUNC,
            numParams: K1.numParams,
            params: K1.params,
            tipoRetorno: T.tipo,
            etiqRetorno: "Et_" + obj.lexema
        })
        
        if(tkActual.id == "PARC"){
            tkActual = lexico()
        }else throwError("200", {arr: ["PARC"]})

        globalVars.zonaDecl = false
        let Q1Q1Q1 = q1q1q1()

        //Regla Semántica Definida
        if(Q1Q1Q1.return) {
            res.tipo = tipos.Tipo_ok
        } else {
            res.tipo = tipos.Tipo_error
            throwError("300", {code: 1})
        }

    }else if (f64.includes(tkActual.id)){
        parse.push(63)
        tkActual = lexico()
        let K1 = k1()

        //Se actualiza la TS
        actualizar_TS(0, obj.nTS, {
            tipo: tipos.FUNC,
            numParams: K1.numParams,
            params: K1.params,
            tipoRetorno: tipos.VOID,
            etiqRetorno: "Et_" + obj.lexema
        })

        if(tkActual.id == "PARC"){
            tkActual = lexico()
        }else throwError("200", {arr: ["PARC"]})

        globalVars.zonaDecl = false
        let Q1Q1Q1 = q1q1q1()

        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
    }else throwError("200", {arr: [...f63, ...f64]})

    destruir_TS(globalVars.tsActual)
    return res;
}
function k1(){
    let f65 = ["INT","STRING","BOOL"]
    let f66 = ["PARC"]
    let res = {
        numParams: 0,
        params: []
    }
    if(f65.includes(tkActual.id)){
        parse.push(64)
        
        let tkActualID
        let T = t()

        if(tkActual.id == "ID"){
            tkActualID = buscar_TS(globalVars.tsActual, tkActual.atr)
            tkActual = lexico()
        }else throwError("200", {arr: ["ID"]})

        let K2 = k2()

        //Regla Semántica Definida
        actualizar_TS(globalVars.tsActual, tkActualID.nTS, {tipo: T.tipo})
        res.numParams = 1 + K2.numParams
        res.params = [T.tipo, ...K2.params]
    }else if (f66.includes(tkActual.id)){
        parse.push(65)
        //lambda
        //Regla Semántica Definida
        res.numParams = 0
        res.params = []
    }else throwError("200", {arr: [...f65, ...f66]})
    
    return res
}
function k2(){
    let f67 = ["COMMA"]
    let f68 = ["PARC"]
    let res = {
        numParams: 0,
        params: []
    }
    if(f67.includes(tkActual.id)){
        parse.push(66)
        
        tkActual = lexico()
        let tkActualID
        let T = t()
        
        if(tkActual.id == "ID"){
            tkActualID = buscar_TS(globalVars.tsActual, tkActual.atr)
            tkActual = lexico()
        }else throwError("200", {arr: ["ID"]})

        let K2 = k2()

        //Regla Semántica Definida
        actualizar_TS(globalVars.tsActual, tkActualID.nTS, {tipo: T.tipo})
        res.numParams = 1 + K2.numParams
        res.params = [T.tipo, ...K2.params]
    }else if (f68.includes(tkActual.id)){
        parse.push(67)
        //lambda
        //Regla Semántica Definida
        res.numParams = 0
        res.params = []
    }else throwError("200", {arr: [...f67, ...f68]})
        return res
}

function c(){
    let f69 = ["DO","ID","IF","IN","LET","PRNT","RET"]
    let f70 = ["BRKC"]
    let res = {
        return: null,
        tipo: null
    }
    if(f69.includes(tkActual.id)){
        parse.push(68)
        let Q = q()
        let C = c() 

        res.tipo = tipos.Tipo_ok
        res.return = Q.return || C.return
    }else if (f70.includes(tkActual.id)){
        parse.push(69)
        //lambda
        //Regla Semántica Definida
        res.tipo = tipos.Tipo_ok
        res.return = false
    }else throwError("200", {arr: [...f69, ...f70]})
    
    return res 
}

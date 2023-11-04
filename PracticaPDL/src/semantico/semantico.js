import { globalVars, tablaSimbolos, } from "../databases/data.js";

/*  
    IDEA: Destruye la tabla de símbolos que está en INDICE_TS y no devuelve nada

    CÓMO: se usa el metodo splice que lo que hace es eliminar o sustituir lo que haya en en la tabla del 
    indice por el objeto deseado. Empieza en el indice indice_TS y elimina 1 elemento.

*/

export function destruir_TS(indice_TS) {
    globalVars.tsActual = 0
}

/*  
    IDEA: Crea una nueva Tabla de Símbolos con el nombre NOMBRE_TS y devuelve el índice

    CÓMO: Se crea una nueva tabla de simbolos utilizando la variable tablaSimbolos
    La idea es crear en "tabla" la nueva tabla 

*/

export function create_TS(name_TS) {
    let newTS = {
        "name": name_TS,
        "tabla": [],
        "despl": 0
    }

    tablaSimbolos.push(newTS)
    globalVars.tsActual = tablaSimbolos.length - 1

    return globalVars.tsActual
}

/*  
    IDEA: Buscar una TS por su nombre y devuelve el índice de la tabla

    CÓMO: Buscamos el indice de la tabla basandonos en el nombre de la TS
    La idea es buscar en el array de tablas una que se llame asi y 
    devolver el indice necesarioe
*/

export function buscar_Index_TS(name_TS) {
    let indexRes = (tablaSimbolos.findIndex(ts => ts.name === name_TS))
    return indexRes
}

/*  
    IDEA: Busca en la TS en la posición 
    INDICE_TS una fila por su lexema y devuelve toda la fila

    CÓMO: La idea ha sido buscar en la tabla de simbolos 
    deseada con el indice y el lexema pedidos la fila resultante
    
    DUDA: 
    no sé si seria return tablaSimbolos[index_TS].tabla[index] o return fila directamente
*/

export function buscar_TS(index_TS, atr) {
    let tk1 = tablaSimbolos[index_TS].tabla[atr] // Token dentro de la tabla buscada
    let tk2 = tablaSimbolos[0].tabla[atr] // Si no, buscamos en la global
    if (tk1 != undefined) { return tk1 } else return tk2
}


/*  
    IDEA: Devuelve el nombre de la TS que está en INDICE_TS
*/

export function name_TS(index_TS) {
    return tablaSimbolos[index_TS].name
}

/*  
    IDEA: Busca en la TS en la posición 
    INDICE_TS una fila por su lexema y devuelve toda la fila

    CÓMO: La idea de este codigo es que veamos 
    1 si contiene el lexema del que se desea cambiar la fila
*/

export function actualizar_TS(index_TS, atr, object) {
    if (object.tipo == "FUNC") tablaSimbolos[index_TS].tabla[atr] = {...tablaSimbolos[index_TS].tabla[atr], ...object}
    else tablaSimbolos[index_TS].tabla[atr] = {...tablaSimbolos[index_TS].tabla[atr], ...object, despl: tablaSimbolos[index_TS].despl}
    tablaSimbolos[index_TS].despl += _tipo2despl(object.tipo)
}

export function _tipo2despl(tipo) {
    switch (tipo) {
        case "ENTERO" : return 1;
        case "BOOLEANO" : return 1;
        case "CADENA" : return 64;
        case "FUNC" : return 0;
    }
}
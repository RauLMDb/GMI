export const IdToken = {
    SUM : "SUM", // Suma (+)
    MUL : "MUL", // Multiplicación (*)
    AND : "AND", // Operador lógico AND (&&)
    OR : "OR", // Operador lógico OR (||)
    CAD : "CAD", // Cadena de caracteres ('')
    INTEGER : "INTEGER", // Número entero
    ASIGN : "ASIGN", // Asignación (=)
    PARA : "PARA", // Paréntesis de apertura (()
    PARC : "PARC", // Paréntesis de cierre ())
    BRKA : "BRKA", // Corchete de apertura ({)
    BRKC : "BRKC", // Corchete de cierre (})
    END : "END", // Fin de fichero
    ASIGNSUB : "ASIGNSUB", // Asignación con decremento (-=)
    SCOL : "SCOL", // Fin de línea
    GRT : "GRT", // Mayor que (>)
    LOW : "LOW", // Menor que (<)
    BOOL : "BOOL", // Booleano (0 = true, 1 = false)
    FUNC : "FUNC", // Función
    PRNT : "PRNT", // Print
    RET : "RET", // Return
    IN : "IN", // Input
    LET : "LET", // Let
    DO : "DO", // do
    WHILE : "WHILE", // While
    ID : "ID", // Identificador
    IF : "IF", // If
    INT : "INT", // Int
    STRING : "STRING",// String
    COMMA : "COMMA", // Coma
    EOF : "EOF", // Fin de fichero
    TRUE: "TRUE", // true
    FALSE : "FALSE" // False
}

export const tokens = [];
export const parse = [];
export const tablaSimbolos = [
    {
        "name" : "GLOBAL",
        "tabla" : [],
        "despl": 0
    }
]
export let globalVars = {
    tsActual: 0,
    despl: 0,
    zonaDecl: false
}
export let despl = 0;
export var zonaDecl = false;
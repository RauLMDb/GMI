module PF_Practica2 (Expresion(Var, Numero, Logico, Suma, If), Tipos (Entero, Booleano, General), Valor (Error, OkB, OkI), Entorno (Entorno), Funcion (Funcion), aplicar, tipoCorrecto, tipo, calcular, mi_lista) where
-- import Distribution.ModuleName (main)

------------------------------------------------------------------------
-- Aquí se indican los ejercicios realizados.
-- Modificar la lista acorde a lo que se entrega
------------------------------------------------------------------------
mi_lista :: [Int]
mi_lista = [0, 1, 2, 3]

------------------------------------------------------------------------
-- Tipos indicados en el enunciado. Añadir los deriving  o instance necesarios
-------------------------------------------------------------------------

data Expresion = Var String | Numero Integer | Logico Bool | Suma Expresion Expresion | If Expresion Expresion Expresion deriving (Eq, Show)

data Tipos = Entero | Booleano | General deriving (Eq, Show)

data Valor = Error | OkB Bool | OkI Integer deriving (Eq, Show)

data Funcion = Funcion String Expresion deriving (Eq, Show)

data Entorno = Entorno [(String, Valor)] deriving (Eq, Show)


------------------------------------------------------------------------
-- Aquí va el código de las funciones. Sustituir por vuestro
-- código aquellas que queráis probar
-------------------------------------------------------------------------



-- Ejercicio 0: que devuelve True si la expresión tiene un tipo correcto y False en caso contrario. Se puede 
--asumir que las variables aparecen como mucho una única vez en la expresión

tipoCorrecto :: Expresion -> Bool
-- POST: Decide si la expresión tiene un tipo correcto

--si recibe una variable, es correcto
tipoCorrecto (Var _) = True
--si recibe un numero, es correcto
tipoCorrecto (Numero _) = True
--si recibe un booleano, es correcto
tipoCorrecto (Logico _) = True
--si recibe una suma, es correcto si los dos operandos son enteros y correctos
tipoCorrecto (Suma e1 e2) = (tipoCorrecto e1 && tipoCorrecto e2) && (mismoTipo (tipoExpresion e1) (tipoExpresion e2) && esEntero (tipoExpresion e1))
--si recibe un if, es correcto si los dos operandos son del mismo tipo y correctos y el condicional es booleano
tipoCorrecto (If e1 e2 e3) = (tipoCorrecto e1 && tipoCorrecto e2 && tipoCorrecto e3) && (esBooleano (tipoExpresion e1) && mismoTipo (tipoExpresion e2) (tipoExpresion e3))
    


-- Ejercicio 1: que devuelve Nothing en caso de que el tipo de la expresión no sea correcto, y Just 
-- Entero o Just Booleano si la expresión tiene un tipo correcto entero o booleano. Se 
-- puede asumir que las variables aparecen como mucho una única vez en la expresión
tipo :: Expresion -> Maybe Tipos
-- POST: Devuelve el tipo de la expresión si es correcto, Nothing en caso contrario
--si la expresion es correccta, devuelve el tipo de la expresion, si no lo es, devuelve Nothing
tipo e | tipoCorrecto e = tipoExpresion e
       | otherwise = Nothing
                                      
-- Ejercicio 2 : devuelve Just v, con v el valor de la expresión con las asignaciones de las variables 
-- dadas en el Entorno o Nothing si la expresión tiene un tipo incorrecto o no se ha asignado 
-- un valor a alguna variable
calcular' :: Expresion -> Entorno -> Valor
-- POST: Devuelve el valor de la expresión con las asignaciones de las variables dadas en el Entorno o Nothing si la expresión tiene un tipo incorrecto o no se ha asignado un valor a alguna variable
--si la expresion es una variable, devuelve el valor de la variable en el entorno, si no lo encuentra, devuelve error
calcular' (Var name) (Entorno []) = Error
calcular' (Var name) ent = if name == varname then value else calcular' (Var name) (Entorno (tail ent'))
                                where   (Entorno ent') = ent
                                        (varname, value) = head ent'   
--si la expresion es un numero, devuelve el numero                                                                           
calcular' (Numero n) _ = OkI n
--si la expresion es un booleano, devuelve el booleano
calcular' (Logico b) _ = OkB b
--si la expresion es una suma, devuelve la suma de los operandos, si alguno de los operandos no es un entero, devuelve error
calcular' (Suma e1 e2) ent = let v1 = calcular' e1 ent
                                 v2 = calcular' e2 ent
                              in if v1 == Error || v2 == Error || v1 == OkB True || v2 == OkB True || v2 == OkB False || v1 == OkB False then Error
                              else let (OkI n1) = v1
                                       (OkI n2) = v2
                                    in OkI (n1 + n2)
--si la expresion es un if, devuelve el valor del operando 2 si el condicional es true, el valor del operando 3 si el condicional es false, si el condicional no es un booleano, devuelve error                                    
calcular' (If e1 e2 e3) ent = let v1 = calcular' e1 ent
                                  v2 = calcular' e2 ent
                                  v3 = calcular' e3 ent
                               in if v1 /= OkB True && v1 /= OkB False then Error
                               else let (OkB b) = v1
                                    in if b then v2 else v3
--si la expresion es correcta, devuelve el valor de la expresion, si no lo es, devuelve error
calcular :: Expresion -> Entorno -> Valor
calcular e ent =  if tipoCorrecto e then calcular' e ent else Error

-- Ejercicio 3: que calcula en valor de la expresión que define la Función del primer argumento 
-- sustituyendo las apariciones de la variable (segundo argumento de función) que se da como 
-- segundo argumento de aplicar. Devolverá Nothing si hay algún error (tipo incorrecto, 
-- aparición de una variable diferente a la indicada en Función en la expresión, etc.).
aplicar :: Funcion -> Integer -> Maybe Integer
-- POST: Devuelve el valor de la expresión que define la Función del primer argumento sustituyendo las apariciones de la variable (segundo argumento de función) que se da como segundo argumento de aplicar. 
-- Devolverá Nothing si hay algún error (tipo incorrecto, aparición de una variable diferente a la indicada en Función en la expresión, etc.)
-- crea un entorno con el name y n y llama a calcular, si la expresion es correcta, devuelve el valor de la expresion, si no lo es, devuelve Nothing
aplicar (Funcion name e) n = let ent = Entorno [(name, OkI n)]
                                 v = calcular e ent
                              in if v == Error then Nothing
                              else let (OkI n) = v
                                   in Just n                                   


------------------------------------------------------------------------
-- Funciones auxiliares que se pueden usar (o no).
-------------------------------------------------------------------------

mismoTipo :: Maybe Tipos -> Maybe Tipos -> Bool
-- POST: Decide si los tipos t1 y t2 son compatibles
mismoTipo Nothing _ = False
mismoTipo _ Nothing = False
mismoTipo (Just t1) (Just t2) = (t1 == t2)  || (t1 == General) || (t2 == General)

masGeneral :: Maybe Tipos -> Maybe Tipos -> Maybe Tipos
-- POST: Devuelve el tipo más general entre dos tipos y Nothing si son incompatibles
masGeneral (Just General) t2 = t2
masGeneral t1 (Just General) = t1
masGeneral t1 t2 = t1

compatible :: Tipos -> Tipos -> Bool
compatible x y  |  (x == General) || (y == General)         = True
                       |  (x == Booleano) || (y == Booleano)     = (x /= Entero) && (y /= Entero)
                       |  (x == Entero) || (y == Entero)             = (x /= Booleano) && (y /= Booleano)
                       |  otherwise                                         = False                          

ambosEnteros :: Maybe Tipos -> Maybe Tipos -> Bool
-- POST: Decide si los dos tipos son compatibles con Entero
ambosEnteros Nothing _ = False
ambosEnteros _ Nothing = False
ambosEnteros (Just t1) (Just t2) = (t1 == Entero) && (t2 == Entero)   || 
                                                   (t1 == General) && (t2 /= Booleano)|| 
                                                   (t2 == General) && (t1/= Booleano)

esBooleano :: Maybe Tipos -> Bool
-- POST: Decide si el tipo es compatible con Booleano
esBooleano (Just Entero) = False
esBooleano Nothing = False
esBooleano _ = True

esEntero:: Maybe Tipos -> Bool
-- POST: Decide si el tipo es compatible con Entero
esEntero (Just Booleano) = False
esEntero Nothing = False
esEntero _ = True

tipoExpresion :: Expresion -> Maybe Tipos
-- POST: Devuelve el tipo de la expresión, no comprueba que sea correcto
--si la expresion es una variable, devuelve general
tipoExpresion (Var _) = Just General
--si la expresion es un numero, devuelve entero
tipoExpresion (Numero _) = Just Entero
--si la expresion es un booleano, devuelve booleano
tipoExpresion (Logico _) = Just Booleano
--si la expresion es una suma, devuelve entero
tipoExpresion (Suma e1 e2) = Just Entero
--si la expresion es un if, devuelve el tipo mas concreto de los operandos, por ejemplo, si los operandos son booleano y general, devuelve booleano, si los operandos son general y entero, devuelve entero
tipoExpresion (If e1 e2 e3) | tipoExpresion e2 == Just Booleano || tipoExpresion e3 == Just Booleano = Just Booleano
                            | tipoExpresion e2 == Just Entero || tipoExpresion e3 == Just Entero = Just Entero
                            | otherwise = Just General
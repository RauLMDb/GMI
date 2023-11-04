{-# OPTIONS_GHC -Wno-missing-fields #-}
module PF_Practica2 (Expresion(Var, Numero, Logico, Suma, If), Tipos (Entero, Booleano, General), Valor (Error, OkB, OkI), Entorno (Entorno), Funcion (Funcion), aplicar, tipoCorrecto, tipo, calcular, mi_lista) where
import GHC.CmmToAsm.AArch64.Instr (_d)

------------------------------------------------------------------------
-- Aquí se indican los ejercicios realizados.
-- Modificar la lista acorde a lo que se entrega
------------------------------------------------------------------------
mi_lista :: [Int]
mi_lista = [0, 1, 2, 3]

------------------------------------------------------------------------
-- Tipos indicados en el enunciado. Añadir los deriving  o instance necesarios
-------------------------------------------------------------------------

data Expresion = Var String |
                          Numero Integer |
                          Logico Bool |
                          Suma Expresion Expresion |
                          If Expresion Expresion Expresion deriving(Eq,Show)

data Tipos = Entero |
                   Booleano |
                   General deriving(Eq,Show)


data Valor = Error |
                   OkB Bool |
                   OkI Integer deriving(Eq,Show)

data Entorno = Entorno [(String, Valor)] deriving(Show)

data Funcion = Funcion String Expresion deriving(Show)


------------------------------------------------------------------------
-- Aquí va el código de las funciones. Sustituir por vuestro
-- código aquellas que queráis probar
-------------------------------------------------------------------------

tipoExpresion :: Expresion -> Tipos
tipoExpresion (Var _) = General
tipoExpresion (Numero _) = Entero
tipoExpresion (Logico _) = Booleano
tipoExpresion (Suma _ _) = Entero
tipoExpresion (If _ e1 e2) = t where Just t = masGeneral (Just (tipoExpresion e1)) (Just (tipoExpresion e2))

tipoValor :: Valor -> Maybe Tipos
tipoValor (OkB _) =Just Booleano
tipoValor (OkI _) = Just Entero
tipoValor Error = Nothing

-- Ejercicio 0
tipoCorrecto :: Expresion -> Bool
tipoCorrecto (Var _) = True
tipoCorrecto (Numero _) = True
tipoCorrecto (Logico _) = True
tipoCorrecto (Suma e1 e2) = esEntero (Just (tipoExpresion e1)) && compatible (tipoExpresion e1) (tipoExpresion e2) && all tipoCorrecto [e1,e2]
tipoCorrecto (If e1 e2 e3) = esBooleano (Just (tipoExpresion e1)) && compatible (tipoExpresion e3) (tipoExpresion e2) && all tipoCorrecto [e1,e2,e3]


-- Ejercicio 1
tipo :: Expresion -> Maybe Tipos
tipo (Var _) = Just General
tipo (Logico _) = Just Booleano
tipo (Numero _) = Just Entero
tipo exp@(Suma _ _) | tipoCorrecto exp = Just Entero
                    | otherwise = Nothing
tipo exp@(If _ e2 e1)| tipoCorrecto exp = masGeneral (tipo e1) (tipo e2)
                    | otherwise = Nothing

calcular :: Expresion -> Entorno -> Valor
calcular exp ent | tipoCorrecto exp = calcular' exp ent
                 | otherwise = Error

calcular' :: Expresion -> Entorno -> Valor
calcular' _ (Entorno []) = Error
calcular' (Var name) (Entorno ((var,value):ent')) | name == var = value 
                                        | otherwise = calcular' (Var name) (Entorno ent')
calcular' (Numero n) _ = OkI n
calcular' (Logico l) _ = OkB l
calcular' (Suma e1 e2) ent | map tipoValor [v, w] == [Just Entero, Just Entero] = OkI (n + m)
                           | otherwise = Error
                           where (v,w) = (calcular e1 ent, calcular e2 ent)
                                 (OkI n,OkI m) = (v,w)                           
calcular' (If cond e1 e2) ent | calcular cond ent == OkB True = calcular e1 ent 
                              | calcular cond ent == OkB False = calcular e2 ent 
                              | otherwise = Error


-- Ejercicio 3
aplicar :: Funcion -> Integer -> Maybe Integer
aplicar (Funcion name exp) n | tipoCorrecto exp && Just Entero == tipoValor valor = let (OkI result) = valor in Just result 
                             | otherwise = Nothing
                              where valor = calcular exp (Entorno [(name,OkI n)])

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
esBooleano _ = True

esEntero:: Maybe Tipos -> Bool
-- POST: Decide si el tipo es compatible con Entero
esEntero (Just Booleano) = False
esEntero _ = True


main :: IO()
main = do
  let x = Entero
  let y = Booleano
  print ((x == General) || (y == General))
  print ((x /= Booleano) && (y /= Booleano))





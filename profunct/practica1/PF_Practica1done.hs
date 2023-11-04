module PF_Practica1 (mi_lista, frecuenciasVocales, escaleras, intercalar, JugadasRugby (Ensayo, Transformacion, Drop), resultadoRugby, aproxPi) where


-- Ejercicio 0
-- actualiza el valor de la lista mientras recorre el string s y va encontrando las vocales
frecuenciasVocalesAux s i lista = 
    if i < length s then
        if s !! i == 'a' then frecuenciasVocalesAux s (i + 1) (head lista + 1 : drop 1 lista)
        else if s !! i == 'e' then frecuenciasVocalesAux s (i + 1) (take 1 lista ++ [lista !! 1 + 1] ++ drop 2 lista)
        else if s !! i == 'i' then frecuenciasVocalesAux s (i + 1) (take 2 lista ++ [lista !! 2 + 1] ++ drop 3 lista)
        else if s !! i == 'o' then frecuenciasVocalesAux s (i + 1) (take 3 lista ++ [lista !! 3 + 1] ++ drop 4 lista)
        else if s !! i == 'u' then frecuenciasVocalesAux s (i + 1) (take 4 lista ++ [lista !! 4 + 1])
        else frecuenciasVocalesAux s (i + 1) lista
    else lista

frecuenciasVocales :: String -> [Int]
frecuenciasVocales "" = [0,0,0,0,0]
frecuenciasVocales s = frecuenciasVocalesAux s 0 [0,0,0,0,0]

-- Ejercicio 1   :  Escaleras: Definir una función que dado un número n calcule la lista con todas 
-- las cuentas atrás descendentes de n:
-- escaleras :: Integer -> [[Integer]] 
-- escaleras n = 
--  [[n,n-1,n-2,...,1,0], [n-1,n-2,...,1,0],..., [1,0], [0]]   

--Genera una lista descendente de n a 0
generarDescendente :: Int -> [Int]
generarDescendente 0 = [0]
generarDescendente n = n : generarDescendente (n-1)

escaleras :: Int -> [[Int]] 
escaleras 0 = [[0]]
escaleras n = generarDescendente n : escaleras (n-1)

-- Ejercicio 2 :  Definir una función intercalar que mezcle dos listas colocando en el 
-- resultado un elemento de cada uno de sus argumentos en el orden en el que 
-- aparecen. Si una lista tiene más elementos que la otra, ese sufijo aparecerá al 
-- final de la lista
-- intercalar:: [a] -> [a] -> [a]
{- intercalar [iz1,iz2,iz3,…] [der1, der2, der3,…]
 POST: resultado = [iz1, der1,iz2, der2, … ] 
-}
intercalar:: [a] -> [a] -> [a]
intercalar iz [] = iz
intercalar [] dr = dr
intercalar iz dr = head iz : head dr : intercalar (tail iz) (tail dr)


-- Ejercicio 3 :Definir una función que reciba una lista de pares (i, j), donde i puede ser 0 o 1 y 
-- que se corresponde con el equipo de casa (0) o el visitante (1) y j es una jugada 
-- de tanteo de rugby (ensayo, transformación o drop), y devuelva un par con el 
-- tanteo total del partido, esto es el par (puntos del equipo anfitrión, puntos de 
-- equipo visitante).
-- data JugadasRugby = Ensayo | Transformacion | Drop
-- resultadoRugby :: [(Int, JugadasRugby)] -> (Int, Int)
-- > resultadoRugby 
--  [(0,Drop),(1,Ensayo),(1, Transformacion),(0,Drop)]
-- > (6,7)
-- Recordatorio: Para aquellos profanos en el noble deporte del Rugby se 
-- recuerda que el Ensayo suma 5 puntos, su Transformación 2 y un Drop 3.
data JugadasRugby = Ensayo | Transformacion | Drop deriving (Show, Eq, Enum)

-- recibe una lista de pares (i, j) y un par (casa, visitante) y devuelve el resultado de la jugada segun el par (i, j) y el par (casa, visitante)
resultadoRugby' :: [(Int, JugadasRugby)] -> (Int, Int) -> (Int, Int)
resultadoRugby' [(0,Ensayo)] (casa,visitante) = (casa + 5, visitante)
resultadoRugby' [(0,Transformacion)] (casa,visitante) = (casa + 2, visitante)
resultadoRugby' [(0,Drop)] (casa,visitante) = (casa + 3, visitante)
resultadoRugby' [(1,Ensayo)] (casa,visitante) = (casa, visitante + 5)
resultadoRugby' [(1,Transformacion)] (casa,visitante) = (casa, visitante + 2)
resultadoRugby' [(1,Drop)] (casa,visitante) = (casa, visitante + 3)
resultadoRugby' jugadas (casa,visitante) = resultadoRugby' (tail jugadas) (resultadoRugby' [head jugadas] (casa,visitante))

resultadoRugby :: [(Int, JugadasRugby)] -> (Int, Int)
resultadoRugby l = resultadoRugby' l (0,0)


-- Ejercicio 4 Definir aproxPi (n) que calcula una aproximación de π con n términos del 
-- producto de Wallis.

productorioWalis :: [Float] -> Float
productorioWalis [] = 1
productorioWalis (n:lista) = (((2*n)/(2*n-1))*((2*n)/(2*n+1))) * productorioWalis lista
aproxPi :: Integer -> Float
aproxPi n = productorioWalis [1..fromInteger n] * 2

-- Rellenar con la lista real de los ejercicios resueltos
mi_lista = [0, 1, 2, 3, 4]
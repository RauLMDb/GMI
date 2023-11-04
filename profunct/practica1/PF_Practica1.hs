module PF_Practica1 (mi_lista, frecuenciasVocales, escaleras, intercalar, JugadasRugby (Ensayo, Transformacion, Drop), resultadoRugby, aproxPi) where
import GHC.Builtin.Names (v1TyConKey)


-- Ejercicio 0
frecuenciasVocales :: String -> [Int]
frecuenciasVocales [] = [0, 0, 0, 0, 0]
frecuenciasVocales s = [a,e,i,o,u]
    where a = length [x | x <- s, x == 'a']
          e = length [x | x <- s, x == 'e']
          i = length [x | x <- s, x == 'i']
          o = length [x | x <- s, x == 'o']
          u = length [x | x <- s, x == 'u']

-- Ejercicio 1                                                        
escaleras :: Int -> [[Int]]

escaleras 0 = [[0]]
escaleras n = reverse [0 .. n] : escaleras (n-1)

-- Ejercicio 2
intercalar:: [a] -> [a] -> [a]
intercalar [] dr = dr
intercalar iz [] = iz
intercalar (i:iz) (d:dr) = i:d:intercalar iz dr


-- Ejercicio 3
data JugadasRugby = Ensayo | Transformacion | Drop deriving (Show, Eq, Enum)

resultadoRugby :: [(Int, JugadasRugby)]  -> (Int, Int)
resultadoRugby [] = (0,0)
resultadoRugby ((e,j):jugadas)  = (l,v)
        where l | e == 0 = puntos j + l1
                | otherwise = l1
              v | e == 1 = puntos j + v1
                | otherwise = v1
              (l1,v1) = resultadoRugby jugadas

puntos :: JugadasRugby -> Int
puntos Ensayo = 5
puntos Transformacion = 2
puntos Drop = 3

-- Ejercicio 4
aproxPi :: Integer -> Float
aproxPi n = 2 * product l 
    where l = [(2*x / (2*x - 1))*(2*x/(2*x +1)) | x <- [1..fromInteger n]]

-- Rellenar con la lista real de los ejercicios resueltos
mi_lista = [0,1,2,3,4]

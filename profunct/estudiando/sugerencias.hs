{- tema 1 expresiones funcionales -}
-- volumen esfera
{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use newtype instead of data" #-}
{-# OPTIONS_GHC -Wno-overlapping-patterns #-}
{-# LANGUAGE DataKinds #-}
import Data.Map.Internal.Debug (balanced)
import Data.Time.Format.ISO8601 (yearFormat)
volumenEsfera :: Float -> Float
volumenEsfera r = 4*pi*r^3/3

mayorArea :: (Float, Float) -> (Float,Float) -> (Float,Float)
mayorArea (h1,b1) (h2,b2) | h1*b1 > h2*b2 = (h1,b1)
                          |otherwise = (h2,b2)

{- tema 2 -}

data LIFO a = LIFO [a] deriving (Show, Eq)
type Tabla clave valor = [(clave,valor)]
type Tablero a = [[a]]
data Arbol a = Vacio | Nodo (Arbol a) a (Arbol a)

push :: LIFO a -> a -> LIFO a
push (LIFO l) e = LIFO (e:l)

pull :: LIFO a -> LIFO a
pull (LIFO (e:l)) = LIFO l

buscar :: Eq a => a -> LIFO a -> Int
buscar a (LIFO (e:l)) | a == e = 0
                      | a `elem` l = 1 + buscar a (LIFO l)
                      | otherwise = -1

buscarTabla :: Eq clave => clave -> Tabla clave valor -> valor
buscarTabla clave ((c,v):t) | clave == c = v
                            | otherwise = buscarTabla clave t
buscarTabla _ [] = error "NO EXISTENTE"

diagonal :: Tablero a -> Int -> [a]
diagonal t n = [t !! i !! i | i <- [0..n-1]]

trioPitagorico :: Int -> [(Int,Int,Int)]
trioPitagorico n = [(x,y,z) | x<- [1..n],y<- [1..n],z <- [1..n], x^2 + y^2 == z^2]

sumaOdd :: Int -> Int
sumaOdd n = sum [x | x <- [1..n], odd x]

altura :: Arbol a -> Int
altura a = altura' a 1

altura' :: Arbol a -> Int -> Int
altura' (Nodo s1 v s2) n | a1 > a2 = a1
                         | otherwise = a2
                         where a1 = altura' s1 n+1
                               a2 = altura' s2 n+1
altura' Vacio n = n-1


contarVocales :: String -> Int
contarVocales = foldr (\y acc -> if y `elem` ['a','e','i','o','u'] then acc + 1 else acc) 0

contraseñaCorrecta :: String -> Bool
contraseñaCorrecta s = any (`elem` "QWERTYUIOPASDFGHJKLZXCVBNM") s && any (`elem` "qwertyuiopasdfghjklzxcvbnm") s && any (`elem` "123456789") s && length s >= 15
balancedTree :: Arbol a -> Bool
balancedTree (Nodo s1 v s2) = abs (altura s1 - altura s2) <= 1
criba :: [Int] -> [Int]
criba (x:resto) = x : criba [p|p <- resto , p `mod` x /= 0]
nprimos :: Int  -> [Int]
nprimos n = take n (1:criba [2..])

replicateN :: Enum a => Int -> a -> [a]
replicateN n a = take n (a:replicateN (n-1) a)
replicateN 0 _ = []

nTriangulares :: Int -> [Int]
nTriangulares n = sum [1..n]: nTriangulares (n+1)

minConMasDiv :: Int -> Int
minConMasDiv n = if ndivisor > n then t else minConMasDiv (n+1)
       where t = nTriangulares 1 !! (n-1)
             ndivisor = length [x | x <- [1..t],t `mod` x == 0]

perteneceRango :: Eq a => Ord a => a -> (Int -> a) -> Bool
perteneceRango x f = x `elem` takeWhile (x>) (map f [1..])

data Enteros = Cero | Suc Enteros | Pred Enteros deriving (Show)

entToInt :: Enteros -> Int -> Int
entToInt (Suc x) n = entToInt x (n+1)
entToInt (Pred x) n = entToInt x (n-1)
entToInt Cero n = n
instance Eq Enteros where
      x == y = entToInt x 0 == entToInt y 0
repite :: Int -> [a] -> [a]
repite n = foldr (\ x acc -> replicate n x ++ acc) []
resta :: Enteros -> Enteros -> Enteros
resta Cero x = x
resta (Suc x) y = resta x (Pred y)
resta (Pred x) y = Pred (resta x y)


elimDup :: Eq a => [a] -> [a]
elimDup = foldl (\acc x -> if null acc || x /= last acc then acc ++ [x] else acc) []


main :: IO()
main = do
    print (elimDup "aaabbbcdab")
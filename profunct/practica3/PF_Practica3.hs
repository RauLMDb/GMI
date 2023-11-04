{-# OPTIONS_GHC -Wno-unrecognised-pragmas #-}
{-# HLINT ignore "Use camelCase" #-}
module PF_Practica3 (mi_lista, itera, orbita, collatz, aprox_e, serie, termino, aleatorios, semilla, tirada, espiral, en_circulo) where

------------------------------------------------------------------------
-- Aquí se indican los ejercicios realizados.
-- Modificar la lista acorde a lo que se entrega
------------------------------------------------------------------------
mi_lista :: [Int]
mi_lista = [0,2,3]

-- Practica 3 - comun
itera :: (a -> a) -> a -> [a]
itera f x = x : itera f (f x)

-- Ejercicio 0
f_collatz :: Integer -> Integer
f_collatz n | even n = n `div` 2
            | otherwise = 3*n + 1

orbita :: Integer-> [Integer]
orbita = itera f_collatz

collatz :: Integer -> [Integer]
collatz n = takeWhile (/= 1) (orbita n)

-- Ejercicio 1
fact :: Integer -> Integer
fact n | n /= 0 = product [1..n]
fact 0 = 1
termino :: (Integer, Integer, Integer) -> Float
termino (x, y, z) = fromInteger (y^x)/fromInteger (fact z)

aprox_e :: Integer -> Float
aprox_e n = sum (takeWhile ( >1e-4) (map termino (serie n)))

serie :: Integer -> [(Integer, Integer, Integer)]
serie x  = itera (\(y,x,z)->(y+1,x,y+1)) (0,x,0)

-- Ejercicio 2

semilla :: Int
semilla = 1239

aleatorios :: Int-> [Int]
aleatorios = itera (\x -> (77*x + 1) `mod` 1024) 

tirada :: Int -> Int
tirada n = map (\x -> x`mod`6 + 1 ) (aleatorios semilla) !! n

-- Ejercicio 3

espiral :: [(Integer, Integer)]
espiral = intercalar a b 
            where a = intercalar [(1 + 4*x,0)| x <- [0..]] [(-3 -4*x,0) | x <- [0..]]
                  b = intercalar [(0,-2 - 4*x)| x <- [0..]] [(0,4 +4*x) | x <- [0..]]

intercalar:: [a] -> [a] -> [a]
intercalar [] dr = dr
intercalar iz [] = iz
intercalar (i:iz) (d:dr) = i:d:intercalar iz dr

en_circulo ::  Integer -> [(Integer, Integer)]
en_circulo r = takeWhile (\(a,b) -> a^2 + b^2 <= r^2) espiral




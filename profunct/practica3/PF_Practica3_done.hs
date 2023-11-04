module PF_Practica3 (mi_lista, itera, orbita, collatz, aprox_e, serie, termino, aleatorios, semilla, tirada, espiral, en_circulo) where

------------------------------------------------------------------------
-- Aquí se indican los ejercicios realizados.
-- Modificar la lista acorde a lo que se entrega
------------------------------------------------------------------------
mi_lista :: [Int]
mi_lista = [0, 1, 2, 3]

-- Practica 3 - itera
-- Como elemento común a todos los ejercicios se pide programar la función de orden superior 
-- itera,
-- itera :: (a -> a) -> a -> [a]
-- tal que (itera f x) es la lista cuyo primer elemento es x y los siguientes elementos se 
-- calculan aplicando f al elemento anterior. Observad que es infinita.
-- (itera f x) = [x, (f x), (f (f x)), (f (f (f x))), …]
-- A partir de esta función (es decir, usándola) se pide desarrollar estas funciones.
itera :: (a -> a) -> a -> [a]
itera f x = x : itera f (f x)



-- Ejercicio 0.- Sucesión de Collatz. La órbita de Collatz de un número se define como la aplicación 
-- sucesiva (esto es, itera) de la función que dado un número positivo devuelve su división por 
-- dos, si es par y si es impar, su multiplicación por tres y luego sumarle uno. La conjetura de Collatz
-- dice que la órbita de un número siempre alcanza un uno (y luego es periódica). La 
-- correspondiente a 13 es 13, 40, 20, 10, 5, 16, 8, 4, 2, 1, 4, 2, 1,... (y los tres últimos números se 
-- repiten). Se debe programar la función orbita, sin recursión y con itera:
-- orbita :: Integer -> [Integer]
-- que calcule dicha lista infinita. Se pide también definir la función 
-- collatz :: Integer -> [Integer]
-- tal que (collatz n) es la órbita de Collatz de n hasta alcanzar el 1. En el caso del 13:
-- collatz 13 = [13,40,20,10,5,16,8,4,2].
-- Para la condición de terminación se recuerda la existencia en la biblioteca de Haskell de la 
-- función takeWhile
orbita :: Integer-> [Integer]
orbita n = itera calculoOrbita n

calculoOrbita :: Integer -> Integer
calculoOrbita n = if even n then div n 2 else 3*n + 1

collatz :: Integer -> [Integer]
collatz n = takeWhile (/= 1) (orbita n)


-- Ejercicio 1. Cálculo de ex
-- : La siguiente serie de potencias calcula el valor de ex
-- :
-- Se pide una función aprox_e para aproximar el valor de ex para un x dado, de manera que la 
-- suma de los términos de serie hasta que se obtenga un término menor a una diezmilésima (que 
-- si se sumará). 
-- aprox_x :: Integer -> Float
-- Programación funcional, Curso 2022-2023
-- Para ello se sumará (función sum) la parte necesaria de la lista infinita con el valor de cada uno 
-- de los términos de la serie. 
-- Una forma sencilla de calcular esa lista infinita es aplicar map termino a la sucesión de tríos 
-- (1, x, 1!,) (2, x2
-- , 2!), (3, x3
-- , 3!), … con 
-- termino (x, y, z) = (fromInteger y)/(fromInteger z). Observad que el 
-- segundo elemento de ese trio es el numerador y el tercero es el denominador. 
-- Y esta sucesión es una aplicación inmediata (y eficiente) de itera, con una función que debéis 
-- determinar. Se calculará mediante la expresión serie x donde:
-- serie :: Integer -> -> [(Integer, Integer, Integer)]
-- que debe programarse también.
-- Como para el ejercicio anterior se recuerda la existencia en la biblioteca de Haskell de la función
-- takeWhile

termino :: (Integer, Integer, Integer) -> Float
termino (x, y, z) = (fromInteger y)/(fromInteger z)

aprox_e :: Integer -> Float
aprox_e n = sum (takeWhile(> 1e-4) (map termino (serie n)))

serie :: Integer -> [(Integer, Integer, Integer)]
serie x  = map quitaRaiz (itera calculoSerie (x, 0, 1, 1))

calculoSerie :: (Integer, Integer, Integer, Integer) -> (Integer, Integer, Integer, Integer)
calculoSerie (x, n, p, f) = (x, n+1, p*x, f*(n+1))

quitaRaiz :: (Integer, Integer, Integer, Integer) -> (Integer, Integer, Integer)
quitaRaiz (x, y, z, w) = (y, z, w)
main :: IO()
main = do print (sum (takeWhile ( >1e-4) (map termino (serie 1))))
-- Ejercicio 2. Números aleatorios. Si iteramos con itera la función:
-- f x = (77 * x + 1) `mod` 1024
-- obtenemos una secuencia pseudo-aleatoria de números del intervalo [0..1023] cuando le damos 
-- una semilla inicial. Se pide programar dicha secuencia (infinita) con la función aleatorios:
-- aleatorios :: Int -> [Int]
-- Para una aplicación que juega al parchís, se requiere simular tiradas sucesivas de un dado, que 
-- se obtendrá con un uso de map adecuado a la secuencia anterior. La tirada n-sima se logrará
-- con una función sobre esa segunda secuencia.
-- tirada :: Int -> Int 
-- -- POST: tirada (n) esta {1..6} y es un valor aleatorio
-- Realizar aleatorios (lista infinita) y tirada a partir de la lista aleatoria indicada y un uso 
-- adecuado y sencillo de las funciones map y !!. Usar el valor semilla (proporcionado) como 
-- valor inicial.

semilla :: Int
semilla = 1239

aleatorios :: Int -> [Int]
aleatorios sem = itera (\x -> (77 * x + 1) `mod` 1024) sem
 

tirada :: Int -> Int
tirada n = (aleatorios semilla !! n) `mod` 6 + 1

-- Ejercicio 3. Espirales: La sucesión
-- (1, 0) (0,-2) (-3, 0) (0, 4) (5, 0) (0,-6) (-7, 0) 
-- son los puntos de corte con los ejes de coordenadas de una pseudo–espiral.
-- Definir la función espiral,mediante itera, que generar la lista infinita de esta espiral. 
-- También se debe definir la función en_circulo, que devuelve la parte de esa espiral que está 
-- inscrita en un círculo de centro (0,0) y radio r.
-- en_circulo :: Integer -> [(Integer, Integer)]
-- -- en_circulo r es la parte de la espiral inscrita en 
-- -- el círculo de radio r y centro (0, 0)
-- Como para ejercicios anterioresse recuerda la existencia en la biblioteca de Haskell de la función
-- takeWhile

espiral :: [(Integer, Integer)]
espiral = itera calculoEspiral (1, 0)

calculoEspiral :: (Integer, Integer) -> (Integer, Integer)
calculoEspiral (x, y) = if x == 0
      then (signo * (abs y + 1), 0)
      else (0,-1 * signo * (abs x + 1))
      where signo = if x == 0 then signum y else signum x

en_circulo :: Integer -> [(Integer, Integer)]
en_circulo r = takeWhile (\(x, y) -> x^2 + y^2 <= r^2) espiral





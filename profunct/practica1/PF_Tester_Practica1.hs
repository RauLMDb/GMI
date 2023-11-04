module Main (main) where

import Data.List
import PF_Practica1



{----------------------------------------------------------------------------------------------------------
Tester Práctica 1 asignatura Programación Funcional
Uso:
Modificar el cuerpo de PF_Practica1.hs para que indique las funciones que se han hecho.
  
Para indicar los ejercicios realizados se modificará la función constante mi_lista.
  
  mi_lista = [p1, ..., pn] donde cada pi es el número de problema
  para el que se ha programado una solución.
  Por ejemplo:
    mi_lista = [0]
  prueba el ejercicio 0.
    mi_lista=  [1, 3]
  prueba los ejercicios 1 y 3.
    mi_lista = [0, 1, 2, 3, 4]
  prueba todos los ejercicios.
  
  Debe haber una función para todos los ejercicios, de manera que no debéis
  de borrar las definiciones de las funciones que no programeis.
  Para las que sí lo hagais, debéis de introducir vuestro código.
  
  --------------------------------------------------------------------------------------------------------}

------------------------------------------------------------------------
-- Lo que sigue es el tester y no debe modificarse.
-- NO DEBE ENTREGARSE, SOLO PF_Practica1.hs
-------------------------------------------------------------------------

main = putStrLn prueba





prueba = if (null mi_lista) then "Fallo : Lista de ejercicios vacia" else (p_p_1 mi_lista)

lfp = ["frecuenciasVocales", "escaleras", "intercalar", "resultadoRugby", "aproxPi"]

ar = reverse

lCG = "Numero de problema no valido "

p_p_1 [] = ""
p_p_1 (x:l) = (ch x) ++ " \n" ++ (p_p_1 l)

mCD = map concat (concat (map permutations (subsequences palabras)))


chf:: (Eq r, Show a, Show r) => String -> [(a, r)] -> [(a, r)] -> String
chf fct [] [] = mts ++ fct
chf fct ((arg1, res1):resto1) ((arg2, res2):resto2) =
      if (res1 /= res2) then feF++ fct++mSp++ show arg1 ++cBD++ show res1 ++mBE++ show res2
                             else (chf fct resto1 resto2)
                             
ch 0 = chf (lfp!!0) (map (\x -> (x, frecuenciasVocales x)) mCD) (map (\x -> (x,  f0f  x)) mCD) 
              where{f0f  s = mp (fc $ s) vw;  nl = 0; eu = 1; ad  = flip (+); mp = map; g   = flip (flip (/=)) ; cnd  b = if b then nl else eu; fc a b = case a of [] -> nl; y:l -> ad (cnd (g b y))  (fc l b)}

ch 1 = chf (lfp!!1)  (map (\n -> (n, escaleras n)) [1..50]) (map (\n -> (n, f1f n)) (e 1 50))
             where{f1f n = r (pm  r (ff n eL)); eL = []; c = ((:) 0 eL); ee = flip enumFromTo; r = ar; p t = (-) t  u; pm = map; j = head; cc = (:);  e = flip ee; u = 1; ff w k = case w of 0 -> (cc c k); d -> (ff (p $ d)) (cc (e (j $ c) w) k)}

ch 2  =  chf (lfp!!2) (map (\(l1, l2) -> (Arg2(l1, l2), (intercalar l1 l2)))  t2)  (map( \(l1, l2)  ->  (Arg2(l1, l2), (f2f l1 l2))) t2)
             where{h = head; t =tail; f2f l1 l2 = case l1 of []  ->  (id $ l2); ll -> case l2 of [] -> (id $ ll); (x:l) -> (h $ (id $ ll)):x:((flip f2f) (id l) (t $ (id $ ll)) )}

ch 3= chf (lfp!!3)  (map (\n -> (n, resultadoRugby n)) (genRgJ 3)) (map (\n -> (n, f3f n)) (genRgJ 3))
            where{a = [(div) 6  2]; u = 1; rp = ((flip enumFromTo) dr  ty);  w = ((:) (u+1) a); ep = fst; oJ (Just e) = e; iN s = (==) s 0; es = snd; nP = (0, 0); iO f = (==) f 1; sP j = tr !! (oJ (findIndex (== j) rp)); tr = ((mod) (4*10) 7):w; f3f d = case d of [] -> nP; (e,j):k -> if (iN e) then ((sP $ j) +(ep $ (f3f  $ k)), (es $ (f3f k))) else ((ep$ (f3f $ k)), (es $ (f3f $ k)) + (sP  j))}
            

ch 4 = chf (lfp!!4)  (map (\n -> (n, (rd (aproxPi n) rdd))) [500..600]) (map (\n-> (n, (rd ((2*(f4f 1 n)::Float)) rdd))) [500..600]) 
                 where {u = 1.0; ui = 1; c = 2.0; r = sqrt; ex = 2; f x = ((*) c x)/(((*) c x) - u); cf x = ((*) c x)/(((*) c x)+ui); g x = (fromInteger $ x)^ex; f4f h i = case i of 0 -> u; j -> (f (fromInteger $ h))*(cf (fromInteger $ h))*(f4f (h+1) (j-1))}

ch n = error lCG ++ (show n)       


genRgJ n = take 10000 (permutations [(a, j) | a <- [0, 1] , j <- concat (replicate (2*n) [ty, tf, dr])])

sep l = splitAt (m (length l)) [x|p<-lp,(i,x)<-zip[0..]l,p i]

newtype A1 a = Arg1 a
newtype A2 a b = Arg2 (a, b)
newtype A3 a b c = Arg3 (a, b, c)

rdd = 6
feF = "Fallo en funcion  "

instance (Show a) => Show (A1 a ) where 
  show (Arg1 x) = (show x) 
  
ty = Ensayo

mBE = " pero debe ser  "

instance (Show a, Show b) => Show (A2 a b) where 
  show (Arg2 (x, y)) = (show x) ++ "  " ++ show y
  
t2 =  [sep l | l<- map (enumFromTo 0) [1 .. 200]]

mts = "Test superado para funcion " 

tf = Transformacion

rd :: Float -> Float  -> Float
rd x n = (fromInteger (round  ((exp n)*x)))/(exp n)
             where
               exp 0 = 1
               exp n = 10*(exp (n-1))
  
instance (Show a, Show b, Show c) => Show (A3 a b c) where 
  show (Arg3 (x, y, z)) = (show x) ++ "  " ++ show y ++ "  " ++ show z

mSp = " aplicada a "

m n = (n `div` 2)  + (n `mod` 2)

vw = ['a','e','i','o','u']

cBD = ". Calculado "

lp = [even,odd]

dr = Drop
   
palabras = ["hoy ", "todo ",  "es ", "futilisimo ", "antes ", "o", "durante "]

------------------------------------------------------------------------------------------------------
-- Para el tester final
------------------------------------------------------------------------------------------------------
subcadena:: String -> String -> Bool
subcadena xs = any (xs==) . map (take $ length xs) . tails

pruebaCorrecta = not (subcadena "Fallo" prueba)
  
  




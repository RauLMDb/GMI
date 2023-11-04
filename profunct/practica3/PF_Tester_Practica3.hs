module Main (main) where
import Data.List
import PF_Practica3

{----------------------------------------------------------------------------------------------------------
Tester Práctica 3 asignatura Programación Funcional
Uso:
Modificar el cuerpo de PF_Practica3.hs para que indique las funciones que se han hecho.
  
Para indicar los ejercicios realizados se modificará la función constante mi_lista.
  
  mi_lista = [p1, ..., pn] donde cada pi es el número de problema
  para el que se ha programado una solución.
  Por ejemplo:
    mi_lista = [0]
  prueba el ejercicio 0.
    mi_lista=  [1, 3]
  prueba los ejercicios 1 y 3.
    mi_lista = [0, 1, 2, 3]
  prueba todos los ejercicios.
  
  Debe haber una función para todos los ejercicios, de manera que no debéis
  de borrar las definiciones de las funciones que no programeis.
  Para las que sí lo hagais, debéis de introducir vuestro código.
  
  --------------------------------------------------------------------------------------------------------}

main = putStrLn prueba



------------------------------------------------------------------------
-- Lo que sigue es el tester y no debe modificarse.
-- NO DEBE ENTREGARSE, SOLO PF_Practica3.hs
-------------------------------------------------------------------------

prueba = if (null mi_lista) then "Fallo : Lista de ejercicios vacia" else (prueba_practica_3 mi_lista)


prueba_practica_3 :: [Int] -> String
prueba_practica_3 [] = ""
prueba_practica_3 (x:l) = (ch x) ++ " \n" ++ (prueba_practica_3 l)

chf :: (Eq r, Show a, Show r) => String -> [(a, r)] -> [(a, r)] -> String
chf fct [] [] = mts ++ fct
chf fct ((arg1, res1):resto1) ((arg2, res2):resto2) =
      if (res1 /= res2) then feF++ fct++mSp++ show arg1 ++cBD++ show res1 ++mBE++ show res2
                             else (chf fct resto1 resto2)

chfLI :: (Eq a, Show a, Show b) => String -> b -> [a] -> [a] -> String
chfLI fct v l1 l2 = let n = (wheredif l1 l2 0) in
                                                  if  iZ n then mts ++ fct ++"\n"
                                                            else feF++ fct++mSp ++ (show v) ++ wth ++ show(n) ++ fel ++ ". " ++ cBD ++ show (take n l1) ++ mBE ++ show (take n l2)



                             
ch n@0 = let t1 = chf (lfp!!(ex n)) (map (\x  -> ((Arg1 x), collatz x))  as01) (map (\x  -> ((Arg1 x), f0f x))  [11..25]) in 
                     if isf t1 then t1
                      else let t2 = (chfLI  (lfp!!(exb n)) a2 (orbita a2) (f0fo a2)) in
                               if isf t2 then t1 ++ "\n" ++ t2 ++"\n"
                                          else t1 ++ "\n" ++ t2 ++ "\n" ++ (chfLI (lfp!!8) (Arg2 ("id", 1))  (itera id 1) (f0ft h 1))
                where {f0f = ctz; fz n = (case (e n) of True -> d n 2; False -> w n); d = div; e = not.odd; w n = n+n + 1+n; o n = (f0ft fz n); ctz n = (flip t)  (o n) (not.(== u)); f0ft = iterate; h x = x; a2 = args !! 0; t = takeWhile; u = 1; f0fo = o}
 
ch n@1 = let t1 = chf (lfp!!(ex n)) (map (\x  -> ((Arg1 x), truncar (aprox_e x) 5))  [1..11]) (map (\x  -> ((Arg1 x), truncar (1+(ff1f x)) 5))  [1..11]) in 
                let t1' =    chf (lfp!!(ex n)) (map (\x  -> ((Arg1 x), aprox_e x))  [1..11]) (map (\x  -> ((Arg1 x), 1+ff1f' x))  [1..11]) in 
                  if (isf t1) && (isf t1')  then t1
                  else 
                    let tt1 = if (isf t1) then t1' else t1 in
                       let t2 = (chfLI  (lfp!!(exb n)) a2 (serie a2) (ff1fs a2)) in
                               if (isf t2) then tt1 ++ "\n" ++ t2 ++"\n"
                                            else tt1 ++ "\n" ++ t2 ++ "\n" ++ (chfLI (lfp!!8) (Arg2 ("id", 1))  (itera id 1) (ff1ft h 1))
                where {ff1f n = dr (+) 0 ((flip t)  ((flip m) (s n) r) fm); ff1f' n = dl (+) 0 ((flip t)  ((flip m) (s n) r) fm); s x  = ff1ft (z x)  (1, x, 1); ff1ft = iterate; dr = foldl; dl = foldr; z x (a, b, c)  = ((p1 a), (l x b), (l (a+1) c)); p1 = (+ 1); l = (*); h x = x; a2 = args !! 1; ff1fs = s; m = map; t = takeWhile; mil = 1/10000; fm = (>= mil); r = termino}

ch n@2 =  let t1 = chf (lfp!!(ex n))  (map (\x  -> ((Arg1 x), tirada x))  [1..(2*ct)]) (map (\x  -> ((Arg1 x), fff2f x))  [1..(2*ct)]) in 
                     if (isf t1) then t1
                      else let t2 = (chfLI  (lfp!!(exb n)) semilla (aleatorios semilla) (fff2fl (fff2fs 0))) in
                               if (isf t2) then t1 ++ "\n" ++ t2 ++"\n"
                                            else t1 ++ "\n" ++ t2 ++ "\n" ++ (chfLI (lfp!!8) (Arg2 ("id", 1)) (itera id 1) (fff2ft h 1))
                where {fff2fl b= fff2ft p b; s = semilla; m = mod; u = 1; sx = 2*3; z a = pam (\x -> ((m x  sx) + u)) (fff2fl  a); t = (!!); fff2ft f x = x:(fff2ft f (f x)); fff2f = t (z (fff2fs u)); p x = m (o * x + u)  q; fff2fs n = s; q = 2*500 + 2*12; o = 7*10+7; h x = x; pam = map}

ch n@3 =  let t1 = chf (lfp!!(ex n))  (map (\x  -> ((Arg1 x), en_circulo x))  as11) (map (\x  -> ((Arg1 x), ffff3f x))  ([11..25]++[81, 123])) in 
                      if (isf t1) then t1
                      else let t2 = (chfLI  (lfp!!(exb n)) "" (espiral) (ffff3fe)) in
                               if (isf t2) then t1 ++ "\n" ++ t2 ++"\n"
                                           else t1 ++ "\n" ++ t2 ++ "\n" ++ (chfLI (lfp!!8) (Arg2 ("id", 1))  (itera id 1) (ffff3ft h 1))
                where {ffff3f =  c; j = (&&); i = (1, 0); lq = (<=); mk a b = (a, b); p = ffff3ft (sl) i; d1 k = mk 0 (-(fst k)-1); d2 k = mk 0 (-(fst k)+1); d3 k = mk ((snd k) + 1) 0; d4 k = mk ((snd k) - 1) 0; cd c1 c2 c3 k = if c1 then (d1 k)  else if c2 then (d2 k) else if c3 then (d3 k) else (d4 k); sl k = cd ((fst k) > 0) ((fst k) < 0)  ((snd k) > 0) k; ffff3ft = iterate; c r = (flip t) p (\(x, y) -> j (q x r)  (q y r)); q a b = lq (abs a) b; h x = x; t = takeWhile; ffff3fe = p}

ch n = error "Numero de problema no valido " ++ (show n)

as01 = [11..25]
as11 = as01 ++ [81, 123]
 -- = [([11..25], 13::Int), ([11..25]++[81, 123], 55), ([1..(2*ct)], 0), ([11..25]++[81, 123], 0)]

ct = 250
ex m = 2*m

exb m = 2*m +1

feF = flr ++ " en funcion  "

truncar :: Float -> Int -> Float
truncar x n = (fromIntegral (floor (x * t))) / t
    where t = 10^n


cg = "Fallo"

args = [15, 55]

mSp = " aplicada a "

cBD = ". Calculado "

wheredif [] [] n = 0
wheredif (x:l) (y:r) n = if n > ct then 0
                                else if (x /= y) then n
                                else (wheredif l r (n+1))
wheredif [] (y:r) n = n+1
wheredif (x:l) [] n = n+1


mBE = " pero debe ser  "



newtype A1 a = Arg1 a
newtype A2 a b = Arg2 (a, b)
newtype A3 a b c = Arg3 (a, b, c)

wth = " con los "

fel = " primeros elementos"

iZ q = q == 0


instance (Show a) => Show (A1 a ) where 
  show (Arg1 x) = (show x) 

mts = "Test superado para funcion " 
  
instance (Show a, Show b) => Show (A2 a b) where 
  show (Arg2 (x, y)) = (show x) ++ "  " ++ show y
  
lfp = ["collatz", "orbita", "aprox_e", "serie", "tirada", "aleatorios", "en_circulo", "espiral",  "itera"]

isf w = (take (length flr) w) == flr

flr = "Fallo"
  
instance (Show a, Show b, Show c) => Show (A3 a b c) where 
  show (Arg3 (x, y, z)) = (show x) ++ "  " ++ show y ++ "  " ++ show z
  

  
------------------------------------------------------------------------------------------------------
-- Para el tester final
------------------------------------------------------------------------------------------------------
sch:: String -> String -> Bool
sch xs = any (xs==) . map (take $ length xs) . tails

pruebaCorrecta = not (sch cg prueba)
  
  
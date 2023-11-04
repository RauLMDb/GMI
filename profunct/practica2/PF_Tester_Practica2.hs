module Main (main) where
import Data.List
import PF_Practica2

{----------------------------------------------------------------------------------------------------------
Tester Práctica 2 asignatura Programación Funcional
Uso:
Modificar el cuerpo de PF_Practica2.hs para que indique las funciones que se han hecho.
  
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
-- NO DEBE ENTREGARSE, SOLO PF_Practica2.hs
-------------------------------------------------------------------------

prueba = if (null mi_lista) then cg ++": Lista de ejercicios vacia" else (prueba_practica_2 mi_lista)

genExp =
               xm [ms e1 e2 | e1 <- misExpSinVar, e2 <- misExpSinVar, 
                                                  kT (vss 1 (ms e1 e2)),
                                                  kT (vss 2 (ms e1 e2))]
                            [ss cond e1 e2 | cond <- misExpSinVar, e1<- misExpSinVar, e2 <- misExpSinVar,
                                                  kT (vss 1 (ss cond e1 e2)),
                                                  kT (vss 2 (ss cond e1 e2))]
                                                  
genExpSinVar = [br 7, br (-3), lb True,  lb False] ++
                         (xm [ms e1 e2 | e1 <- genExpSinVar, e2 <- genExpSinVar] 
                                     [ss cond e1 e2 | cond <- genExpSinVar, e1<- genExpSinVar, e2 <- genExpSinVar])

kT (x:(y:_)) = False
kT [] = True
kT (x:[]) = True

ms a b = (Suma a b)

ss a b c = (If a b c)

xm (x:l) (y:r) = x:(y:(xm l r))
xm [] l = l
xm l [] = l

br x = (Numero x)

vr x = (Var x)

vss 1 (Var "x") = [1]
vss 2 (Var "b") = [1]
vss x (Suma e1 e2) = xm (vss x e1)  (vss x e1) 
vss x (If cond e1 e2)  = xm (vss x cond) (xm (vss x e1) (vss x e2))
vss _ _ = []

lb y = (Logico y)

lfp = ["tipoCorrecto", "tipo", "calcular", "aplicar"]
cg = "Fallo"

misExpSinVar = [(vr "x"), (vr "b")]  ++ (ktt 100 genExpSinVar)

misExp = (take 500 genExp)

ktt = take

prueba_practica_2 :: [Int] -> String
prueba_practica_2 [] = ""
prueba_practica_2 (x:l) = (ch x) ++ " \n" ++ (prueba_practica_2 l)

                          
chf:: (Eq r, Show a, Show r) => String -> [(a, r)] -> [(a, r)] -> String
chf fct [] [] = mts ++ fct
chf fct ((arg1, res1):resto1) ((arg2, res2):resto2) =
      if (df res1 res2) then feF++ fct++mSp++ show arg1 ++cBD++ show res1 ++mBE++ show res2
                             else (chf fct resto1 resto2)
                             
ch n@0 = chf (lfp!!n) (map (\x -> (x, tipoCorrecto x)) misExp) 
                                                     (map (\x -> (x,  f0f  x)) misExp) 
              where {g = gl;e = iG;o = oo; co = vD; mt = cB; qz a b = (||) a  b; a3 q p s = (&&) ((&&) q p) s; gg a b = (||) (flip (==) a b)  (flip (==) a g); f0f  = f0fg g;  f0fg t x =  case x of  (Var _) -> co; (Numero _) -> (gg t e); (Logico _) -> (gg t o); (Suma k z) -> (a3 (df t o) (f0fg e k)  (f0fg e z)); (If d k z) -> (f0fg o d) && (qz (a3 (mt t e)  (f0fg e k) (f0fg e z))   (a3 (mt  t o)  (f0fg o k)  (f0fg o z))) }
 
ch n@1 = chf (lfp!!n) (map (\x -> (x, tipo x)) misExp) (map (\x -> (x, ff1f x)) misExp)
             where {g = gl; h = nDn; o = oo; iB a = if (ije a) then ng else co;  j w = (eSe w); ng = mTn; e = iG; co = vD; umg = umG; i3 p  b c pr = if  (p b c) then if pr then (j e)  else (umg b c) else h; eb = aE ; st = mT; ff1f x = case x of (Var _)  -> (j g); (Numero _) -> (j e); (Logico _) -> (j o); (Suma y z) -> i3 eb (ff1f y) (ff1f z) co; (If m z y) -> if (iB (ff1f m)) then (i3 st (ff1f z) (ff1f y) ng) else h}
                                               
ch n@2  =  let t1 = chf (lfp!!n) (map (\x  -> (Arg2(x, ent), calcular x ent))  misExp) (map (\x  -> (Arg2(x, ent), fff2f x ent))  misExp) in 
                     if (ktt 5 t1) == cg then t1
                                              else chf (lfp!!n) (map (\x  -> (Arg2(x, ent'), calcular x ent'))  misExp) (map (\x  -> (Arg2(x, ent'), fff2f x ent'))  misExp)
                where {ent = mE co nm; ent' = mE ng nm'; co = vD; wr = fL; at p a b = if iO p then if qO p then a else b else wr; ng = mTn; fd v (Entorno l) = (case l of [] -> wr; ((vv, r):t) -> if (flip (==) v vv) then r else (fd v (mmE t)));nm = 7; mE = \b i -> mmE (("x", vO co i ng): ("b", vO ng 0 b):[]); (++++++) = sV; sV a b = (case (a, b) of (Error, _) -> wr; (_, Error) -> wr; ((OkI v1), (OkI v2)) -> (OkI (v1+v2)); otherwise -> wr);; nm' = (-3); mmE = Entorno; vO b w z = (case b of True -> (OkI w); False -> (OkB z)); fff2f x  ett = case x of  (Var v) ->  fd v ett; (Numero n) ->  vO co n ng; (Logico b)  -> vO ng 0 b; (Suma z w)  ->  (fff2f z ent) ++++++ (fff2f  w ett); (If p w z) -> at (fff2f  p ett)  (fff2f  w ett)  (fff2f z ett)}
                                                                                      
ch n@3 = chf (lfp!!n)  (map (\x-> (Arg2(x, 7), aplicar  x 7))
                                                                                (map (\x -> (Funcion "x" x)) misExp))  
                                              (map (\x-> (Arg2(x, 7), ffff3f x 7))
                                                                                (map (\x -> (Funcion "x" x)) misExp))
              where {j w = (eSe w); a3 p a b = if iO p then if qO p then a else b else wr; h = nDn; co = vD; ng = mTn; wr = fL; (++++++) = sV; sV a b = (case (a, b) of (Error, _) -> wr; (_, Error) -> wr; ((OkI v1), (OkI v2)) -> (OkI (v1+v2)); otherwise -> wr);  getOK a = (case a of (OkI x) -> x; otherwise -> 0); a2 b w = if b then w else fL; isOk ss = (case ss of (OkI _) -> co; otherwise -> ng); vO b w z = (case b of True -> (OkI w);False -> (OkB z)); ffff3f f x = (case ffff3g f (vO co x ng) of (OkI i) -> j i; otherwise -> h); ffff3g f x = (case f of (Funcion k (Var v)) -> a2 (k == v) (vO co (getOK x) ng); (Funcion _ (Numero n)) -> vO co n ng; (Funcion _ (Logico b))  ->  vO ng 0 b; (Funcion y (Suma z w))  -> ( ffff3g (mF y z) x) ++++++ (ffff3g (mF y w) x); (Funcion k (If p w z))  -> a3 (ffff3g  (Funcion k p) x) (ffff3g  (mF k w) x) (ffff3g  (mF k z) x) )}

ch n = error "Numero de problema no valido " ++ (show n)


newtype A1 a = Arg1 a
newtype A2 a b = Arg2 (a, b)
newtype A3 a b c = Arg3 (a, b, c)

vD = True

eSe w = Just w

ije (Just Entero) = True
ije _ = False

instance (Show a) => Show (A1 a ) where 
  show (Arg1 x) = (show x) 
  
mTn = False

qO (OkB l) = l

iO (OkB h) = True
iO  _= False
  
instance (Show a, Show b) => Show (A2 a b) where 
  show (Arg2 (x, y)) = (show x) ++ "  " ++ show y

fL = Error
  
instance (Show a, Show b, Show c) => Show (A3 a b c) where 
  show (Arg3 (x, y, z)) = (show x) ++ "  " ++ show y ++ "  " ++ show z
  
feF = "Fallo en funcion  "

gl = General
  
mT :: Maybe Tipos -> Maybe Tipos -> Bool
mT Nothing _ = False
mT _ Nothing = False
mT (Just t1) (Just t2) = (t1 == t2)  || (t1 == General) || (t2 == General)

mBE = " pero debe ser  "

iG = Entero

umG :: Maybe Tipos -> Maybe Tipos -> Maybe Tipos
umG (Just General) t2 = t2
umG t1 (Just General) = t1
umG t1 t2 = t1

mts = "Test superado para funcion " 

oo = Booleano

cB :: Tipos -> Tipos -> Bool
cB x y  |  (x == General) || (y == General)         = True
            |  (x == Booleano) || (y == Booleano)     = (x /= Entero) && (y /= Entero)
            |  (x == Entero) || (y == Entero)             = (x /= Booleano) && (y /= Booleano)
            |  otherwise                                         = False                          

mSp = " aplicada a "

nDn = Nothing

df x y = (x /= y)

mF = Funcion

aE :: Maybe Tipos -> Maybe Tipos -> Bool
aE Nothing _ = False
aE _ Nothing = False
aE (Just t1) (Just t2) = (t1 == Entero) && (t2 == Entero)   || 
                                  (t1 == General) && (t2 /= Booleano)|| 
                                  (t2 == General) && (t1/= Booleano)
  
cBD = ". Calculado "
------------------------------------------------------------------------------------------------------
-- Para el tester final
------------------------------------------------------------------------------------------------------
sch:: String -> String -> Bool
sch xs = any (xs==) . map (take $ length xs) . tails

pruebaCorrecta = not (sch cg prueba)
  
  




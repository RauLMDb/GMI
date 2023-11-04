{-# LANGUAGE BlockArguments #-}
module PF_Pratica4 where
import GHC.IO ( unsafePerformIO )
import Data.Time.Clock
import System.Random
import System.Random.Shuffle
import Data.List.Split (chunksOf)
import Data.List
import Control.Monad
import Control.Monad.Trans.RWS (put, get)
import System.Posix.Internals (puts)
import GHC.Builtin.Types (trueDataCon)

-- Obtiene la hora actual del sistema como un número entero redondeado a milisegundos para la generacion de una semilla aleatoria
--POST: semilla generada a partir de la hora actual del sistema
getSeed :: IO Int
getSeed = fmap (round . (* 1000) . toRational . utctDayTime) getCurrentTime

--PRE: dos listas comparables
--POST: Booleano si todos los elementos de la primera lista están presentes en la segunda lista
todosEnLista :: Eq a => [a] -> [a] -> Bool
todosEnLista [] _ = True  -- Si la primera lista está vacía, todos sus elementos están en la segunda lista
todosEnLista (x:xs) ys = elem x ys && todosEnLista xs ys


--PRE: lista de enteros y semilla de generacion
--POST: un entero aleatorio entre 1 y 9 que no esta en la lista
randomOut :: Int -> [Int] -> Int
randomOut semilla l = if todosEnLista [1..9] l then 0 else head (filter (`notElem` l) (randomRs (0,9) (mkStdGen semilla)))

--PRE: posicion y sudoku
--POST: lista de los elementos que no se pueden escoger (no validos) para dicha posicion
getNoValidos :: (Int,Int) -> [[Int]]-> [Int]
getNoValidos (x,y) sudoku = getFila (x,y) sudoku ++ getColumna (x,y) sudoku ++ getCuadrado (x,y) sudoku

--PRE: posicion y sudoku
--POST: lista de los elementos que no se pueden escoger de su columna (no validos) para dicha posicion
getColumna :: (Int,Int) -> [[Int]] -> [Int]
getColumna (x,y) = getColumnaAux (x,y) ([0..x-1]++[x+1..8])

--Funcion auxiliar para getColumna
getColumnaAux :: (Int,Int) -> [Int] -> [[Int]] -> [Int]
getColumnaAux (x,y) l sudoku = [sudoku !! x !! y | x <- l]

--PRE: posicion y sudoku
--POST: lista de los elementos que no se pueden escoger de su fila (no validos) para dicha posicion
getFila :: (Int,Int) -> [[Int]] -> [Int]
getFila (x,y) = getFilaAux (x,y) ([0..y-1]++[y+1..8])

--Funcion auxiliar para getFila
getFilaAux :: (Int,Int) -> [Int] -> [[Int]] -> [Int]
getFilaAux (x,y) l sudoku = [sudoku !! x !! y | y <- l]

--PRE: posicion y Matriz Sudoku
--POST: devuelve la lista de elementos del cuadrado 3x3 en el que se encuentra el elementos de la posicion
getCuadrado :: (Int,Int) -> [[Int]] -> [Int]
getCuadrado (x,y) sudoku
  | x < 3 = if y < 3 then getCuadradoAux (x,y) ([0..x-1]++[x+1..2]) ([0..y-1]++[y+1..2]) sudoku
                                                else if y < 6 then getCuadradoAux (x,y) ([0..x-1]++[x+1..2]) ([3..y-1]++[y+1..5]) sudoku
                                                              else getCuadradoAux (x,y) ([0..x-1]++[x+1..2]) ([6..y-1]++[y+1..8]) sudoku
  | x < 6 = if y < 3 then getCuadradoAux (x,y) ([3..x-1]++[x+1..5]) ([0..y-1]++[y+1..2]) sudoku
                                                              else if y < 6 then getCuadradoAux (x,y) ([3..x-1]++[x+1..5]) ([3..y-1]++[y+1..5]) sudoku
                                                                            else getCuadradoAux (x,y) ([3..x-1]++[x+1..5]) ([6..y-1]++[y+1..8]) sudoku
  | y < 3 = getCuadradoAux (x,y) ([6..x-1]++[x+1..8]) ([0..y-1]++[y+1..2]) sudoku
  | y < 6 = getCuadradoAux (x,y) ([6..x-1]++[x+1..8]) ([3..y-1]++[y+1..5]) sudoku
  | otherwise = getCuadradoAux (x,y) ([6..x-1]++[x+1..8]) ([6..y-1]++[y+1..8]) sudoku

--Funcion auxiliar para getCuadrado
getCuadradoAux :: (Int,Int) -> [Int] -> [Int] -> [[Int]] -> [Int]
getCuadradoAux (x,y) l1 l2 sudoku = [sudoku !! x2 !! y2 | x2 <- l1 , y2 <- l2]

--PRE: semilla, posicion y matriz sudoku
--POST: genera un nuevo numero valido aleatoriamente para la posicion (x,y) del sudoku
genNuevoNumero :: Int -> (Int,Int) -> [[Int]] -> Int  
genNuevoNumero semilla (x,y) sudoku = if n >= 10 then randomOut semilla (getNoValidos (x,y) sudoku) else n
  where n = randomOut semilla (getNoValidos (x,y) sudoku)


--PRE: posicion, elemento y matriz sudoku
--POST: Matriz sudoku con el elemento en la posicion seleccionada
setElem :: (Int,Int) -> Int -> [[Int]] -> [[Int]]
setElem (x,y) n sudoku = take x sudoku ++ [take y (sudoku !! x) ++ [n] ++ drop (y+1) (sudoku !! x)] ++ drop (x+1) sudoku
    where e = sudoku !! x !! y

--PRE: semilla, matriz sudoku, x: limite (valor de p o q inicial) , (p,q)= esquina de arriba a la izquierda de un cuadrado 3x3
--POST: Sudoku con los cuadrados 3x3 de la diagonal principal completos
llenarCuadrados :: Int -> [[Int]] -> Int -> Int -> Int -> [[Int]]
llenarCuadrados semilla sudoku x p q
  | p == x + 3 = sudoku
  | q == x + 3 = llenarCuadrados semilla sudoku x (p+1) x
  | otherwise = llenarCuadrados semilla (setElem (p,q) (genNuevoNumero semilla (p,q) sudoku) sudoku) x p (q+1)

--PRE: semilla, sudoku vacío
--POST: sudoku con los cuadrados 3x3 de la diagonal principal rellenos
genSudoku :: Int -> [[Int]] -> [[Int]]
genSudoku semilla sudoku = do
  let sdk1 = llenarCuadrados semilla sudoku 0 0 0
  let sdk2 = llenarCuadrados (semilla+1) sdk1 3 3 3
  llenarCuadrados (semilla+2) sdk2 6 6 6

--POST: Imprime sudoku
printGrid :: [[Int]] -> [(Int, Int)] -> IO ()
printGrid grid posiciones = do
    putStrLn "\x1b[31m   1   2   3   4   5   6   7   8   9  \x1b[0m"
    putStrLn " +-----------+-----------+-----------+"
    printRows 1 grid posiciones

--POST: imprime las filas
printRows :: Int -> [[Int]] -> [(Int, Int)] -> IO ()
printRows _ [] _ = return ()
printRows n (r:rs) posiciones = do
    putStr ("\x1b[31m"++show n ++"\x1b[0m")
    printCols r posiciones (n,1)
    putStrLn "|"
    if n `mod` 3 == 0
        then putStrLn " +-----------+-----------+-----------+" >> printRows (n+1) rs posiciones
        else printRows (n+1) rs posiciones

--POST: imprime las columnas
printCols :: [Int] -> [(Int, Int)] -> (Int, Int) -> IO ()
printCols [] _ _= return ()
printCols (c:cs) posiciones (x,y) = do
    putStr "| "
    if c == 0 then do putStr ("\x1b[34m" ++ show c ++ "\x1b[0m") else do if (x,y) `notElem` posiciones then do putStr ("\x1b[32m" ++ show c ++ "\x1b[0m") else do putStr (show c)
    putStr " "
    printCols cs posiciones (x,y+1)

--PRE: Lista no valida de posiciones para introducir un numero
--POST: La posicion y el numero en a introducir en formato (x,y,p)
introducirNumero :: [(Int, Int)] ->IO (Int, Int, Int)
introducirNumero posiciones = do
  putStrLn "Introduzca la fila en la que desea escribir un numero (entre 1 y 9):"
  x <- readLn
  putStrLn "Introduce la coluna en la que desea escribir un numero (entre 1 y 9):"
  y <- readLn
  putStrLn "Introduce el un numero entre 1 y 9 :"
  p <- readLn
  if (x,y) `notElem` posiciones then putStrLn "La posicion introducida no es modificable, introduzca otra posicion :" >> introducirNumero posiciones
  else return (x,y,p)

--POST: Seleccion de la dificultad del sudoku
introducirDificultad :: IO Int
introducirDificultad = do
  putStrLn "Introduzca la dificultad:"
  putStrLn "1) Facil"
  putStrLn "2) Medio"
  putStrLn "3) Dificil"
  x <- readLn
  if x > 3 || x < 1 then putStrLn "Dificultad no valida, introduzca un numero entre 1 y 3 :" >> introducirDificultad
  else return x

--Funcion para jugar al sudoku
--PRE: Matriz sudoku
jugar :: [[Int]] -> IO ()
jugar sudoku = do
  putStrLn "Bienvenido al Sudoku"
  let dificultad = unsafePerformIO introducirDificultad
  seed <- getSeed -- generacion aleatoria de semillas mediante hora del sistema
  let str = sudokuToString (genSudoku seed sudoku)
  let solucion = solveSudoku str
  let sudoku1 = stringToIntMatrix solucion

  let (numHuecos, msg) =
        if dificultad == 1
          then (40, "facil")
          else if dificultad == 2
                 then (50, "medio")
                 else (60, "dificil")

  let posiciones = huecosRandom numHuecos seed
  let sudoku2 = setGaps sudoku1 posiciones
  let res = unsafePerformIO (completar sudoku2 posiciones numHuecos [])

  -- putStrLn "La solución que has proporcionado es:"
  printGrid res posiciones

  if comprobar res
    then putStrLn "Sudoku resuelto" 
    else putStrLn "Sudoku resuelto incorrectamente" >> putStrLn "La solución es:" >>  printGrid sudoku1 []

  putStrLn "FIN DEL JUEGO"

--PRE: Matriz sudoku, lista de huecos iniciales, numero de huecos restantes por rellenar, posiciones rellenadas
--POST: Matriz completada
completar :: [[Int]] -> [(Int,Int)] -> Int -> [(Int,Int)] -> IO[[Int]]
completar sudoku _ 0 _ = return sudoku
completar sudoku posiciones n introducidas = do
  printGrid sudoku posiciones
  let (x,y,p) = unsafePerformIO (introducirNumero posiciones)
  let sudoku2 = setElem (x-1,y-1) p sudoku
  if (x,y) `elem` introducidas then do completar sudoku2 posiciones n introducidas else do completar sudoku2 posiciones (n-1) ((x,y):introducidas)

--PRE: Sudoku en formato matriz
--POST: Sudoku en formato string
sudokuToString :: [[Int]] -> String
sudokuToString = concatMap (concatMap show)


--PRE: sudoku en formato string
--POST: sudoku en formato matriz
stringToIntMatrix :: String -> [[Int]]
stringToIntMatrix str = chunksOf 9 $ map (\c -> read [c]) str


--PRE: un entero n y una semilla
--POST: devuelve una lista de n pares diferentes de enteros (x,y)
huecosRandom :: Int -> Int -> [(Int, Int)]
huecosRandom n semilla = take n $ shuffle' coords (length coords) (mkStdGen semilla)
  where
    coords = [(x, y) | x <- [1..9], y <- [1..9]]


--PRE: Matriz sudoku y lista de huecos a añadir
--POST: Matriz sudoku con los huecos creados
setGaps :: [[Int]] -> [(Int, Int)] -> [[Int]]
setGaps = foldl (\sudoku (x, y) -> setElem  (x-1, y-1) 0 sudoku)

--PRE: Matriz sudoku
--POST: Comprueba que ninguna casilla del sudoku entre en conflicto con otra
comprobar :: [[Int]] -> Bool
comprobar sudoku = comprobarAux sudoku (0,0) True

-- Funcion auxiliar para comprobar
comprobarAux :: [[Int]] -> (Int,Int) -> Bool -> Bool
comprobarAux sudoku (x,y) b
  | not b = False
  | x == 9 = b
  | y == 9 = comprobarAux sudoku (x+1,0) b
  | otherwise = comprobarAux sudoku (x,y+1) (b && e /= 0 && e `notElem` getNoValidos (x,y) sudoku)
    where e = sudoku !! x !! y

--PRE: Un sudoku en formato String sin resolver
--POST: El sudoku resuelto mediante backtracking en formato String
solveSudoku :: String -> String
solveSudoku s = head (f [] s)
  where
    f x s@(h:y) =
      let
        (r, c) = divMod (length x) 9
        m # n = m `div` 3 == n `div` 3
        e = [0..8]
      in [ a | z <- ['1'..'9'],
               h == z || h == '0' && notElem z ([(x ++ s) !! (i * 9 + j) |
                                                   i <- e, j <- e, i == r || j == c || i # r && j # c]),
               a <- f (x ++ [z]) y ]
    f x [] = [x]


--Funcion que permite jugar varias veces al sudoku
volverAjugar :: IO ()
volverAjugar = do
  putStrLn "¿Quieres volver a jugar? (s/n)"
  respuesta <- getLine
  if respuesta == "s"
    then do
      let sudoku = [[0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0],
                    [0,0,0,0,0,0,0,0,0]]
      jugar sudoku
      volverAjugar
    else
      putStrLn "¿Hasta pronto!"


-- main del juego
main :: IO ()
main = do
  let sudoku = [[0,0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0,0],
          [0,0,0,0,0,0,0,0,0]]
  jugar sudoku
  volverAjugar




:-module(_,_,[assertions, regtypes]).
:- doc(title, "Programaci@'{o}n L@'{o}gica, Programaci@'{o}n en ISO-Prolog"). 
author_data('Muñoz','Davila','Raul','20M063').

:- doc(author,"Ra@'{u}l Munoz D@'{a}vila, C20M063").

:- doc(module,"Se dispone de un tablero NxN donde cada casilla est@'{a} etiquetada con un operador aritm@'{e}tico binario y un operando entero. Comenzando desde una casilla elegida
de forma arbitraria, y con el valor inicial 0 (que pasa a ser el valor actual), se recorre el tablero visitando cada una de las casillas exactamente una vez. Por cada casilla
visitada se debe realizar la operaci@'{o}n indicada utilizando el valor actual como operando izquierdo, y el valor entero que aparece en la casilla como operando derecho. El
resultado de esta operaci@'{o}n se utilizar@'{a} como operando izquierdo para la operaci@'{o}n
asociada a la siguiente casilla visitada, y as@'{i} sucesivamente. El valor final obtenido
(es decir, el que se obtiene tras completar el recorrido) depende evidentemente del
recorrido realizado.

Tableros: El tablero se representa mediante una lista de celdas definidas mediante
estructuras cell/2 con cabecera cell(Pos,Op) . El argumento Pos debe tomar valores pos(Row,Col) donde las variables Row y Col identifican la fila y la columna
del tablero en la que est@'{a} ubicada la celda en cuesti@'{o}n. Estas variables @'{u}nicamente pueden tomar valores entre 1 y N. Por otro lado, la variable Op representa a la
operaci@'{o}n matem@'{a}tica asociada a la celda. Estas operaciones se explicitan mediante
la estructura op/2 con cabecera op(Op,Val) , donde Op es un operador aritm@'{e}tico
escogido de entre los pertenecientes al conjunto predefinido, y Val es un n@'{u}mero
entero.

@includedef{cell/2}

@includedef{pos/2}

@includedef{op/2}

@begin{verbatim}
board1([cell(pos (1 ,1),op(*,-3)),
        cell(pos (1 ,2),op(-,1)),
        cell(pos (1 ,3),op(-,4)),
        cell(pos (1 ,4),op(- ,555)),
        cell(pos (2 ,1),op(-,3)),
        cell(pos (2 ,2),op(+ ,2000)),
        cell(pos (2 ,3),op(* ,133)),
        cell(pos (2 ,4),op(- ,444)),
        cell(pos (3 ,1),op(*,0)),
        cell(pos (3 ,2),op(* ,155)),
        cell(pos (3 ,3),op(// ,2)),
        cell(pos (3 ,4),op(+ ,20)),
        cell(pos (4 ,1),op(-,2)),
        cell(pos (4 ,2),op(- ,1000)),
        cell(pos (4 ,3),op(-,9)),
        cell(pos (4 ,4),op(*,4))]).
@end{verbatim}

Direcciones: las direcciones de tr@'{a}nsito de una casilla a otra se identifican por las
siguientes constantes: n (norte), s (sur), e (este), o (oeste), no (noroeste), ne
(noreste), so (suroeste) y se (sureste).

@includedef{dir/2}

Direcciones Permitidas: Para realizar el tr@'{a}nsito de una casilla a la siguiente es necesario escoger una direcci@'{o}n de entre las disponibles en una lista denominada
DireccionesPermitidas . Esta lista contiene una serie de estructuras dir/2 con
cabecera dir(Dir,Num) , donde Dir es una de las direcciones anteriores y Num es
el n@'{u}mero m@'{a}ximo de veces que puede utilizarse dicha direcci@'{o}n durante el c@'{a}lculo
de un recorrido. Por ejemplo, con la lista:

DireccionesPermitidas =[dir(n,3), dir(s,4), dir(o,2), dir(se,10)]

solamente estar@'{i}a permitido transitar desde la casilla actual a la casilla ubicada al
norte, sur, oeste o sureste de esta siempre y cuando la casilla exista (es decir, que se
encuentre dentro de los l@'{i}mites del tablero). Asimismo, durante todo el recorrido, como m@'{a}ximo se puede transitar a las direcciones n , s , o y se en 3, 4, 2 y 10 ocasiones
respectivamente.

Cabe tambi@'{e}n aclarar que podr@'{i}a suceder que no sea posible completar ning@'{u}n
recorrido con ciertas listas de direcciones permitidas independientemente de cual
sea la casilla de comienzo.

@subsection{Apartado 1 (6 puntos)}
El objetivo de este primer apartado es implementar un predicado que genere recorridos en tableros. Se pide resolver el problema de forma gradual. Primero se deben
implementar los siguientes predicados:

@item (0.5 puntos) efectuar_movimiento(Pos,Dir,Pos2) : Pos2 es la posici@'{o}n resultante de moverse desde Pos en la direcci@'{o}n indicada por Dir .

@includedef{efectuar_movimiento/3}

@includedef{mover_en_dir/3}

@item (0.5 puntos) movimiento_valido(N,Pos,Dir) : debe tener @'{e}xito si la posici@'{o}n resultado de moverse desde Pos en la direcci@'{o}n indicada por Dir representa
una posici@'{o}n v@'{a}lida en un tablero de NxN.

@includedef{movimiento_valido/3}

@item (0.5 puntos) select_cell(IPos,Op,Board,NewBoard) : extrae (se puede consultar el predicado Prolog select/3 como inspiraci@'{o}n) la celda con posici@'{o}n
IPos de la lista Board obteniendo NewBoard (sin dicha celda) y unificando
Op con la operaci@'{o}n asociada a la respectiva celda.

@includedef{select_cell/4}

@item (0.5 puntos) select_dir(Dir,Dirs,NewDirs) : extrae (se puede consultar el
predicado Prolog select/3 como inspiraci@'{o}n) una direcci@'{o}n Dir de las permitidas en Dirs , obteniendo en NewDirs la lista de direcciones permitidas
que restan. Esta puede ser la misma lista Dirs pero con el n@'{u}mero de aplicaciones de la direcci@'{o}n seleccionada disminuido en uno, o, si esta fuera la @'{u}ltima
aplicaci@'{o}n permitida, sin ese elemento.

@includedef{select_dir/3}

@item (0.5 puntos) aplicar_op(Op, Valor, Valor2) : dado Op=op(Operador,Operando) ,
aplica la operaci@'{o}n indicada en Valor para obtener Valor2 .

@includedef{aplicar_op/3}

Finalmente, para terminar este apartado se debe escribir el predicado
generar_recorrido/6 (3.5 puntos). Este predicado est@'{a} formalizado mediante la cabecera:
generar_recorrido (Ipos ,N,Board , DireccionesPermitidas ,
Recorrido ,Valor)
donde Ipos representa posici@'{o}n (columna y fila en forma de t@'{e}rmino pos(Irow,Icol) )
de la celda desde la que se inicia el recorrido del tablero Board de dimensi@'{o}n N teniendo en cuenta las direcciones permitidas indicadas por DireccionesPermitidas .

Por otro lado, Recorrido es una lista de tuplas (Pos, ValorActual) , donde la variable Pos hace referencia a las estructuras pos/2 con cabecera pos(Row,Col) que representan la posici@'{o}n de las celdas que forman parte del recorrido generado cuyo valor
final es Valor . Asimismo, la variable ValorActual representa al valor actual del recorrido tras la realizaci@'{o}n de la operaci@'{o}n indicada en la celda correspondiente. N@'{o}tese
que este predicado debe devolver por vuelta hacia atr@'{a}s todos los recorridos que parten desde la casilla señalada por la posici@'{o}n Ipos usando @'{u}nicamente las direcciones
reseñadas en la lista DireccionesPermitidas . A modo de ejemplo, se muestra el resultado de la ejecuci@'{o}n de una consulta que busca obtener por vuelta hacia atr@'{a}s todos los recorridos (en este caso un total de 27) posibles que pueden construirse partiendo
desde la casilla (2, 2) del tablero de ejemplo mostrado anteriormente, usando para
ello la lista de direcciones permitidas [dir(n,5),dir(s,6),dir(e,7),dir(o,4)] .

@begin{verbatim}
?- board1(_Board),
_Dirs = [dir(n ,5) ,dir(s ,6) ,dir(e ,7) ,dir(o ,4)],
generar_recorrido (pos (2 ,2) ,4,_Board ,_Dirs ,Recorrido ,Valor).
Esta que se muestra a continuaci@'{o}n es una de las 27 respuestas que devuelve Prolog
tras la ejecuci@'{o}n de la pregunta anterior:
Recorrido = [(pos (2 ,2) ,2000) ,
(pos (1 ,2) ,1999) ,
(pos (1 ,1),-5997) ,
(pos (2 ,1),-6000) ,
(pos (3 ,1) ,0),
(pos (4 ,1),-2),
(pos (4 ,2),-1002) ,
(pos (4 ,3),-1011) ,
(pos (4 ,4),-4044) ,
(pos (3 ,4),-4024) ,
(pos (2 ,4),-4468) ,
(pos (1 ,4),-5023) ,
(pos (1 ,3),-5027) ,
(pos (2 ,3),-668591) ,
(pos (3 ,3),-334295) ,
(pos (3 ,2),-51815725)],
Valor = -51815725 ?
@end{verbatim}

@includedef{generar_recorrido/6}

@includedef{generar_recorrido_aux/6}

@includedef{valida/2}

@subsection{Apartado 2 (1.25 puntos)}

A continuaci@'{o}n, el alumno debe escribir el predicado generar_recorridos/5 con
cabecera generar_recorridos(N, Board, DireccionesPermitidas, Recorrido, Valor) .
Este predicado genera por vuelta hacia atr@'{a}s el valor Valor del recorrido Recorrido
correspondiente a todos los recorridos posibles en el tablero Board de dimensi@'{o}n N
partiendo desde cualquier casilla, y teniendo en cuenta las direcciones permitidas en
la lista DireccionesPermitidas . Este predicado facilitar@'{a} al alumno el averiguar en
el siguiente apartado cual es el valor m@'{i}nimo de entre los proporcionados por todos
los recorridos posibles. El recorrido devuelto en la variable Recorrido debe seguir el
mismo formato que en el predicado anterior. A continuaci@'{o}n se muestra un ejemplo
de uso del citado predicado.

@begin{verbatim}
?- board1(_Board),
_Dirs = [dir(n ,5) ,dir(s ,6) ,dir(e ,7) ,dir(o ,4)],
generar_recorridos (4,_Board ,_Dirs ,R,V).
R = [(pos (1 ,1) ,0),

(pos (2 ,1),-3),
(pos (3 ,1) ,0),
(pos (4 ,1),-2),
(pos (4 ,2),-1002) ,
(pos (4 ,3),-1011) ,
(pos (4 ,4),-4044) ,
(pos (3 ,4),-4024) ,
(pos (2 ,4),-4468) ,
(pos (1 ,4),-5023) ,
(pos (1 ,3),-5027) ,
(pos (1 ,2),-5028) ,
(pos (2 ,2),-3028) ,
(pos (3 ,2),-469340) ,
(pos (3 ,3),-234670) ,
(pos (2 ,3),-31211110)],
V = -31211110 ?
@end{verbatim}

Al igual que en el ejemplo anterior, aqu@'{i} solamente se muestra una @'{u}nica respuesta.
No obstante, el alumno debe tener en cuenta que este ejemplo devuelve un total de
362 soluciones por vuelta hacia atr@'{a}s. El predicado implementado por el alumno debe
devolver por vuelta hacia atr@'{a}s todas las soluciones posibles.

@includedef{generar_recorridos/5}

@subsection{Apartado 3 (1.25 puntos)}

Para finalizar, se pide al alumno que escriba un predicado tablero/5 descrito
mediante la cabecera:

tablero(N, Tablero , DireccionesPermitidas , ValorMinimo ,
NumeroDeRutasConValorMinimo )

que se hace cierto si ValorMinimo unifica con el m@'{i}nimo valor final que es posible obtener teniendo en cuenta todas las rutas posibles comenzando en una casilla
cualquiera del tablero de dimensi@'{o}n N , y utilizando @'{u}nicamente los movimientos indicados en la lista DireccionesPermitidas para transitar entre las diferentes casillas
del tablero. Adem@'{a}s, la variable NumeroDeRutasConValorMinimo debe unificarse con
el n@'{u}mero de rutas existentes que permiten obtener el valor m@'{i}nimo indicado por
ValorMinimo . Si no existiese ninguna ruta posible, el programa debe fallar. Se recomienda al alumno que repase los predicados de agregaci@'{o}n estudiados en las clases
de teor@'{i}a porque pueden serle de mucha utilidad en la implementaci@'{o}n de este predicado. A continuaci@'{o}n se muestra un ejemplo de uso de este predicado.

@begin{verbatim}
?- board1(_Board),
_Dirs = [dir(n ,5) ,dir(s ,6) ,dir(e ,7) ,dir(o ,4)],
tablero (4,_Board ,_Dirs ,VM ,NR).
NR = 1,
VM = -246992940 ? ;
no
@end{verbatim}

@includedef{tablero/5}

@includedef{min_list/2}

").
:- prop cell(Pos,Op) # "Estructura que representa una celda del tablero. El primer argumento es la posici@'{o}n de la celda y el segundo es la operaci@'{o}n.@includedef{cell/2}".
cell(pos(X,Y),op(Op, Value)):-
    pos(X,Y),op(Op,Value).

:- prop pos(X,Y) # "Estructura que representa una posici@'{o}n en el tablero. El primer argumento es la fila y el segundo es la columna.@includedef{pos/2}".
pos(X, Y) :-
    integer(X), integer(Y).

:- prop op(Op,Value) # "Estructura que representa una operaci@'{o}n. El primer argumento es el operador y el segundo es el operando.@includedef{op/2}".
op(Op, Value) :-
    member(Op, [*,-,+,//]),
    integer(Value).
:- prop dir/2 # "Estructura que representa una direcci@'{o}n. El primer argumento es la direcci@'{o}n y el segundo es el n@'{u}mero de veces que se puede utilizar dicha direcci@'{o}n.@includedef{dir/2}".
dir(N,X) :-
     member(N,[n,s,e,o,no,ne,se,so]), integer(X), X > 0.


:- pred efectuar_movimiento(+Pos, +Dir, -Pos2) # "Predicado que calcula la posici@'{o}n resultante de moverse desde Pos en la direcci@'{o}n indicada por Dir.@includedef{efectuar_movimiento/3}".
efectuar_movimiento(pos(X,Y), Dir, pos(X2,Y2)) :-
    dir(Dir,1), % verificar que la direcci@'{o}n sea v@'{a}lida
    mover_en_dir(pos(X,Y), Dir, pos(X2,Y2)). % llamar a un predicado auxiliar que mueva en la direcci@'{o}n indicada

:- pred mover_en_dir(+Pos, +Dir, -Pos2) # "Predicado auxiliar que calcula la posici@'{o}n resultante de moverse desde Pos en la direcci@'{o}n indicada por Dir.@includedef{mover_en_dir/3}".
mover_en_dir(pos(X,Y), o, pos(X,Y2)) :- Y2 is Y - 1.
mover_en_dir(pos(X,Y), e, pos(X,Y2)) :- Y2 is Y + 1.
mover_en_dir(pos(X,Y), n, pos(X2,Y)) :- X2 is X - 1.
mover_en_dir(pos(X,Y), s, pos(X2,Y)) :- X2 is X + 1.
mover_en_dir(pos(X,Y), no, pos(X2,Y2)) :- X2 is X - 1, Y2 is Y - 1.
mover_en_dir(pos(X,Y), so, pos(X2,Y2)) :- X2 is X + 1, Y2 is Y - 1.
mover_en_dir(pos(X,Y), ne, pos(X2,Y2)) :- X2 is X - 1, Y2 is Y + 1.
mover_en_dir(pos(X,Y), se, pos(X2,Y2)) :- X2 is X + 1, Y2 is Y + 1.

:- pred movimiento_valido(+N, +Pos, +Dir) # "Predicado que verifica si la posici@'{o}n resultante de moverse desde Pos en la direcci@'{o}n indicada por Dir representa una posici@'{o}n v@'{a}lida en un tablero de NxN.@includedef{movimiento_valido/3}".
movimiento_valido(N, Pos, Dir) :-
    % Calcular las coordenadas resultantes del movimiento
    efectuar_movimiento(Pos, Dir, pos(Fila,Columna)),
    % Verificar si las coordenadas resultantes est@'{a}n dentro de los l@'{i}mites del tablero
    between(1, N, Fila),
    between(1, N, Columna).

:- pred select_cell(+IPos, -Op, +Board, -NewBoard) # " Predicado que extrae la celda con posici@'{o}n IPos de la lista Board obteniendo NewBoard (sin dicha celda) y unificando Op con la operaci@'{o}n asociada a la respectiva celda.@includedef{select_cell/4}".
select_cell(IPos, Op, Board, NewBoard) :-
    select(cell(IPos, Op), Board, NewBoard).

:- pred select_dir(+Dir, +Dirs, -NewDirs) # " Predicado que extrae una direcci@'{o}n Dir de las permitidas en Dirs , obteniendo en NewDirs la lista de direcciones permitidas que restan. Esta puede ser la misma lista Dirs pero con el n@'{u}mero de aplicaciones de la direcci@'{o}n seleccionada disminuido en uno, o, si esta fuera la @'{u}ltima aplicaci@'{o}n permitida, sin ese elemento.@includedef{select_dir/3}".
select_dir(D, Dirs, NewDirs) :-
    select(dir(Dir,X), Dirs, RestDirs),
    (X > 1 -> Y is X - 1, NewDirs = [dir(Dir, Y) | RestDirs] ; NewDirs = RestDirs).

:- pred aplicar_op(+Op, +X, -Z) # " Predicado que aplica la operaci@'{o}n indicada en X para obtener Z.@includedef{aplicar_op/3}".
aplicar_op(op(+,X), Y, Z) :- Z is X + Y.
aplicar_op(op(-,X), Y, Z) :- Z is Y - X.
aplicar_op(op(*,X), Y, Z) :- Z is X * Y.
aplicar_op(op(//,X), Y, Z) :- Z is Y // X.

:- prop valida/2 # "Verifica si una posici@'{o}n es v@'{a}lida en un tablero de NxN.@includedef{valida/2}".
valida(pos(X,Y), N) :-
    between(1, N, X),
    between(1, N, Y).

:- pred generar_recorrido(+Ipos, +N, +Board, +DireccionesPermitidas, -Recorrido, +Valor) # " Predicado que genera el recorrido y calcula el valor.@includedef{generar_recorrido/6}".
generar_recorrido(Ipos, N, Board, DireccionesPermitidas, Recorrido,Valor) :-
    valida(Ipos, N), % Verificar que la posición sea válida
    select_cell(Ipos, op(_, Ivalue), Board, NewBoard), % Seleccionar la celda del tablero
    generar_recorrido_aux(Ipos, N, NewBoard, DireccionesPermitidas, NewRecorrido, Ivalue),
    Recorrido = [(Ipos,Ivalue)|NewRecorrido],
    last(Recorrido,(_,Valor)).

:- pred generar_recorrido_aux(+Ipos, +N, +Board, +DireccionesPermitidas, -Recorrido, +Ivalue) # "redicado auxiliar que genera el recorrido.@includedef{generar_recorrido_aux/6}".
generar_recorrido_aux(Ipos, N, Board, DireccionesPermitidas,Recorrido, Ivalue) :-
    valida(Ipos, N), % Verificar que la posición sea válida
    select_dir(Dir, DireccionesPermitidas, NewDirs), % Seleccionar una dirección
    movimiento_valido(N, Ipos, Dir), % Verificar que el movimiento sea válido
    efectuar_movimiento(Ipos, Dir, Pos), % Calcular la posición resultante del movimiento
    select_cell(Pos, op(Op, Num), Board, NewBoard), % Seleccionar la celda del tablero
    aplicar_op(op(Op, Num), Ivalue, Value), % Aplicar la operación
    ((NewBoard = []) -> 
    Recorrido = [(Pos,Value)]
    ;
    append([(Pos,Value)],NewRecorrido,Recorrido),
    generar_recorrido_aux(Pos,N,NewBoard,NewDirs,NewRecorrido,Value)
    ).

:- pred generar_recorridos(+N, +Board, +DireccionesPermitidas, -Recorrido, -Valor) # " Predicado que genera todos los recorridos posibles en el tablero Board de dimensi@'{o}n N partiendo desde cualquier casilla, y teniendo en cuenta las direcciones permitidas en la lista DireccionesPermitidas .@includedef{generar_recorridos/5}".
generar_recorridos(N,Board,DireccionesPermitidas,Recorrido,Valor):-
    generar_recorrido(_,N,Board,DireccionesPermitidas,Recorrido,Valor).

:- pred min_list(+Lista, -Min) # " Predicado que calcula el m@'{i}nimo de una lista de enteros.@includedef{min_list/2}".
min_list(Lista, Min):-
    member(Min,Lista),
    \+ (member(E, Lista), E < Min).

:- pred tablero(+N, +Tablero, +DireccionesPermitidas, -ValorMinimo, -NumeroDeRutasConValorMinimo) # " Predicado que calcula el valor m@'{i}nimo de entre los proporcionados por todos los recorridos posibles. El recorrido devuelto en la variable Recorrido debe seguir el mismo formato que en el predicado anterior.@includedef{tablero/5}".
tablero(N, Tablero , DireccionesPermitidas , ValorMinimo ,NumeroDeRutasConValorMinimo ):-
    findall(Valor,generar_recorridos(N,Tablero,DireccionesPermitidas,_,Valor),Valores),
    min_list(Valores,ValorMinimo),
    findall(MinRecorrido,generar_recorridos(N,Tablero,DireccionesPermitidas,MinRecorrido,ValorMinimo),MinRecorridos),
    length(MinRecorridos,NumeroDeRutasConValorMinimo).













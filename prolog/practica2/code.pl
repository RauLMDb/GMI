:- module(_,_,[classic]).
:- use_module(library(lists)).
:- use_module(library(between)).
author_data('Muñoz','Davila','Raul','20M063').
% Definición del predicado cell/2 para representar una celda del tablero
cell(pos(X,Y),op(Op, Value)):-
    pos(X,Y),op(Op,Value).

% Definición del predicado pos/2 para representar la posición de una celda
pos(X, Y) :-
    integer(X), integer(Y).

% Definición del predicado op/2 para representar la operación de una celda
op(Op, Value) :-
    member(Op, [*,-,+,//]),
    integer(Value).
% Tablero del ejemplo
board2([cell(pos(1 ,1),op(//,16)),cell(pos(1 ,2),op(//,2)),cell(pos(2 ,1),op(//,2)),cell(pos(2 ,2),op(//,2))]).

board1([cell(pos(1 ,1),op(*,-3)),
        cell(pos(1 ,2),op(-,1)),
        cell(pos(1 ,3),op(-,4)),
        cell(pos(1 ,4),op(- ,555)),
        cell(pos(2 ,1),op(-,3)),
        cell(pos(2 ,2),op(+ ,2000)),
        cell(pos(2 ,3),op(* ,133)),
        cell(pos(2 ,4),op(- ,444)),
        cell(pos(3 ,1),op(*,0)),
        cell(pos(3 ,2),op(* ,155)),
        cell(pos(3 ,3),op(// ,2)),
        cell(pos(3 ,4),op(+ ,20)),
        cell(pos(4 ,1),op(-,2)),
        cell(pos(4 ,2),op(- ,1000)),
        cell(pos(4 ,3),op(-,9)),
        cell(pos(4 ,4),op(*,4))]).

dir(N,X) :-
     member(N,[n,s,e,o,no,ne,se,so]), integer(X), X > 0.

% Definición de direcciones permitidas
% Ejemplo: norte, sur, oeste y sureste permitidos con límites de 3, 4, 2 y 10 veces respectivamente
% Solo se permiten las direcciones definidas previamente
% Si no se especifica un límite para una dirección, se asume que no hay límite
direccionesPermitidas([dir(n,1), dir(s,1), dir(o,1), dir(e,1)]).

%-----------------------------
efectuar_movimiento(pos(X,Y), Dir, pos(X2,Y2)) :-
    dir(Dir,1), % verificar que la dirección sea válida
    mover_en_dir(pos(X,Y), Dir, pos(X2,Y2)). % llamar a un predicado auxiliar que mueva en la dirección indicada

mover_en_dir(pos(X,Y), o, pos(X,Y2)) :- Y2 is Y - 1.
mover_en_dir(pos(X,Y), e, pos(X,Y2)) :- Y2 is Y + 1.
mover_en_dir(pos(X,Y), n, pos(X2,Y)) :- X2 is X - 1.
mover_en_dir(pos(X,Y), s, pos(X2,Y)) :- X2 is X + 1.
mover_en_dir(pos(X,Y), no, pos(X2,Y2)) :- X2 is X - 1, Y2 is Y - 1.
mover_en_dir(pos(X,Y), so, pos(X2,Y2)) :- X2 is X + 1, Y2 is Y - 1.
mover_en_dir(pos(X,Y), ne, pos(X2,Y2)) :- X2 is X - 1, Y2 is Y + 1.
mover_en_dir(pos(X,Y), se, pos(X2,Y2)) :- X2 is X + 1, Y2 is Y + 1.
%------------------------------
% movimiento_valido(+N, +Pos, +Dir)
movimiento_valido(N, Pos, Dir) :-
    % Calcular las coordenadas resultantes del movimiento
    efectuar_movimiento(Pos, Dir, pos(Fila,Columna)),
    % Verificar si las coordenadas resultantes están dentro de los límites del tablero
    between(1, N, Fila),
    between(1, N, Columna).
%------------------------------
select_cell(IPos, Op, Board, NewBoard) :-
    select(cell(IPos, Op), Board, NewBoard).
%------------------------------
select_dir(Dir, Dirs, NewDirs) :-
    select(dir(Dir,X), Dirs, RestDirs),
    (X > 1 -> Y is X - 1, NewDirs = [dir(Dir, Y) | RestDirs] ; NewDirs = RestDirs).

%------------------------------
aplicar_op(op(+,X), Y, Z) :- Z is Y + X.
aplicar_op(op(-,X), Y, Z) :- Z is Y - X.
aplicar_op(op(*,X), Y, Z) :- Z is X * Y.
aplicar_op(op(//,X), Y, Z) :- Z is Y // X.
%------------------------------
valida(pos(X,Y), N) :-
    between(1, N, X),
    between(1, N, Y).

generar_recorrido(Ipos, N, Board, DireccionesPermitidas, Recorrido,Valor) :-
    valida(Ipos, N), % Verificar que la posición sea válida
    select_cell(Ipos, Iop, Board, NewBoard), % Seleccionar la celda del tablero
    aplicar_op(Iop,0,Value),
    generar_recorrido_aux(Ipos, N, NewBoard, DireccionesPermitidas, NewRecorrido, Value),
    Recorrido = [(Ipos,Value)|NewRecorrido],
    last(Recorrido,(_,Valor)).

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

generar_recorridos(N,Board,DireccionesPermitidas,Recorrido,Valor):-
    generar_recorrido(_,N,Board,DireccionesPermitidas,Recorrido,Valor).

min_list(Lista, Min):-
    member(Min,Lista),
    \+ (member(E, Lista), E < Min).

tablero(N, Tablero , DireccionesPermitidas , ValorMinimo ,NumeroDeRutasConValorMinimo ):-
    findall(Valor,generar_recorridos(N,Tablero,DireccionesPermitidas,_,Valor),Valores),
    min_list(Valores,ValorMinimo),
    findall(MinRecorrido,generar_recorridos(N,Tablero,DireccionesPermitidas,MinRecorrido,ValorMinimo),MinRecorridos),
    length(MinRecorridos,NumeroDeRutasConValorMinimo).













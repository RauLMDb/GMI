:-module(_,_,[]).
author_data('Munoz','Davila','Raul','20M063').

% Ejercicio 1 Dada la definición estándar de número natural:
% natural (0).
% natural(s(X)) :-
% natural(X).
% definir los siguientes predicados (nota: algunos pueden estar ya en las transparencias o en el código
% que acompaña a las transparencias!):
% a) suma(X,Y,Z), cierto si y solo si Z es la suma de X e Y.
% b) par(X), cierto si y sólo si X es par.
% c) impar(X), cierto si y sólo si X es impar
natural(0).
natural(s(X)) :-
natural(X).

suma(0,X,X) :- natural(X).
suma(s(Y),X,s(Z)) :- suma(Y,X,Z).

par(0).
par(s(X)) :- \+par(X).

impar(s(0)).
impar(s(X)) :- par(X).

% Ejercicio 2 Utilizando los predicados del ejercicio anterior, escribir también los siguientes predicados:
% a) suma_a_lista(L,N,SL), cierto sí y sólo si SL es la lista que se obtiene al sumarle N a cada uno
% de los elementos de la lista L.
% b) pares_lista(L,Ps), cierto sí y sólo si Ps es una lista que contiene los números que son pares de
% la lista L.

% Caso base: si la lista está vacía, la suma de la lista con N es la lista vacía.
suma_a_lista([],_,[]).
% Caso recursivo: si la lista no está vacía, sumamos el primer elemento de la lista con N y
suma_a_lista([X|L],N,[Y|SL]) :- suma(X,N,Y), suma_a_lista(L,N,SL).

% Caso base: si la lista está vacía, la lista de pares es la lista vacía.
pares_lista([], []).
% Caso recursivo: si el primer elemento de la lista es par, lo añadimos a la lista de pares 
pares_lista([X|Xs], [X|Ys]) :- par(X), pares_lista(Xs, Ys).
% Caso recursivo: si el primer elemento de la lista es impar, no lo añadimos a la lista de pares
pares_lista([X|Xs], Ps) :- impar(X), pares_lista(Xs, Ps).

% Ejercicio 3 Escribir un predicado puro extrae_elemento(I,L,E,NL) que es cierto si I es un índice (un
% natural en notación de Peano), L una lista, E el elemento de L que está en la posición indicada por el
% índice I, y NL es lista L pero sin ese elemento E. El primer elemento de la lista L es la posición 0. Por
% ejemplo, dado el índice s(s(0)) y la lista [a,b,c,d,e] el elemento E es c y la lista NL es [a,b,d,e].

% Caso base: si el índice es 0, el elemento es el primer elemento de la lista y la lista sin el elemento
% es la lista sin el primer elemento.
extrae_elemento(0,[X|L],X,L).
% Caso recursivo: si el índice es mayor que 0, el elemento es el elemento de la lista que está en la
% posición indicada por el índice y la lista sin el elemento es la lista sin el primer elemento.
extrae_elemento(s(I),[X|L],E,[X|NL]) :- extrae_elemento(I,L,E,NL).





:-module(_,_,[assertions, regtypes]).
:- doc(title, "Programaci@'{o}n L@'{o}gica, Automata Celular"). 
author_data('Munoz','Davila','Raul','20M063').

:- doc(author,"Raul Munoz Davila, C20M063").

:- doc(module,"Este programa es una implementaci@'{o}n del aut@'{o}mata celular en Prolog. 
  Un autómata celular esta formado por una cinta infinita (por ambos extremos) que
contiene células, las cuales tienen siempre un color dado, y un conjunto de reglas
que determinan cómo evoluciona el estado de la cinta: cómo cambian de color sus
células. La evolución del autómata consiste en aplicar las reglas a un estado de la cinta
para llegar a un nuevo estado, moidficando los colores de las células. Por simplicidad,
utilizaremos solo el blanco y el negro, representados por o y x , respectivamente.
@includedef{color/1}

El nuevo color de una célula depende solo de los colores (originales) de sus dos
células vecinas, a izquierda y derecha. Gracias a esto las reglas se pueden definir a
partir de solo tres células.
@includedef{rule/5}

Los estados deben seguir una serie de restricciones:

@item Los estados deben empezar y terminar por células blancas ( [o,x,o,x,o] es un
estado admitido mientras que [x,o,x] no lo debe ser).

@item El estado resultante debe contener siempre dos células más que el estado inicial. Por simplicidad no consideramos representaciones minimizadas (es decir,
si [o,x,o] evolucionara a [o,o,x,o,o] – que es equivalente – no debemos
eliminar las celulas redundantes).

@item El predicado debe fallar si no se cumple la especificación dada mostrada en los
puntos anteriores.
Para programar el predicado cells/3 que realiza un paso de evolucion dado un estado inicial y un conjunto de reglas.
Se han creado las siguientes predicados auxiliares: 

@item Definici@'{o}n de los n@'{u}meros naturales:

@includedef{natural/1}
@begin{verbatim}
?- natural(X).
X = 0 ? ;
X = s(0) ? 
yes
?- 
@end{verbatim}

@item Definic@'{o}n de la suma de dos n@'{u}meros naturales:

@includedef{suma/3}
@begin{verbatim}
?- suma(0,s(0),Z).
Z = s(0) ? 
yes
?- 
@end{verbatim}

@item Definici@'{o}n de los n@'{u}meros pares:

@includedef{par/1}
@begin{verbatim}
?- par(X).
X = 0 ? ;
X = s(s(0)) ?
yes
?-
@end{verbatim}

@item Definici@'{o}n de los n@'{u}meros impares:

@includedef{impar/1}
@begin{verbatim}
?- impar(X).
X = s(0) ? ;
X = s(s(s(0))) ?
yes
?-
@end{verbatim}

@item Calculo de la longitud de una lista:

@includedef{longitud/1}
@begin{verbatim}
?- longitud([o,x,o],X).
X = s(s(s(0))) ?
yes
?-
@end{verbatim}

@item Comprobacion de que la diferencia de longitudes entre 2 listas es 2:

@includedef{dif2/2}
@begin{verbatim}
?- dif2([o,x,o],[o,x,o,x,o]).
yes
?- dif2([o,x,o],[o,x,o,x]).
no
?-
@end{verbatim}

@item Comprobacion de que una lista termina en o:

@includedef{termina/1}
@begin{verbatim}
?- termina([o,x,o]).
yes
?- termina([o,x,x]).
no
?-
@end{verbatim}

@item Comprobacion de que una lista empieza y termina por o:

@includedef{empieza_termina/1}
@begin{verbatim}
?- empieza_termina([o,x,o]).
yes
?- empieza_termina([x,o,x]).
no
?- empieza_termina([o,x,o,x,x]).
no
?- empieza_termina([x,o,x,o]).
no
?-
@end{verbatim}

@item Predicado auxiliar para aplicar una regla a una lista:

@includedef{aplicar_regla/4}
@begin{verbatim}
?- aplicar_regla(o,[o,x,o], r(x,x,x,o,o,x,o), Cells).
Cells = [o,x,x,x,o] ? ;
no
?-
@end{verbatim}

De este modo cells/3 queda de la siguinte manera, donde primero se aplica la regla en cuestion y 
luego se comprueban las restricciones del formato:
@includedef{cells/3}
@begin{verbatim}
?- cells([o,x,o], r(x,x,x,o,o,x,o), Cells).
Cells = [o,x,x,x,o] ? ;
no
?-
@end{verbatim}

Para el predicado evol/3 se realiza una llamada recursiva a si mismo, donde se ve el estado anterior del automata y 
una llamada a cells para comprobar que es el estado anterior del automata y una llamada a cells para comprobar que es el estado siguiente del automata:
@includedef{evol/3}
@begin{verbatim}
?- evol(N, r(x,x,x,o,o,x,o), Cells).
Cells = [o,x,o],
N = 0 ? ;
Cells = [o,x,x,x,o],
N = s(0) ? ;
Cells = [o,x,x,o,o,x,o],
N = s(s(0)) ? ;
Cells = [o,x,x,o,x,x,x,x,o],
N = s(s(s(0))) ? ;
Cells = [o,x,x,o,o,x,o,o,o,x,o],
N = s(s(s(s(0)))) ? 
yes
?-
@end{verbatim}

Para el predicado auxiliar steps/2 se compreuba que el estado cells es correcto y 
luego se calcula si es posible el numero de pasos que se necesitan para llegar a el:
@includedef{steps/2}
@begin{verbatim}
?- steps([o,x,x,o,o,x,o],N).
N = s(s(0)) ?
yes
?-
@end{verbatim}

Este predicado se usa en el predicado ruleset para comprobar que el conjunto de reglas es correcto:
@includedef{ruleset/2}
@begin{verbatim}
?- ruleset(RuleSet , [o,x,x,o,o,x,o,o,o,o,x,o,o,x,o]).
RuleSet = r(x,x,x,o,o,x,o) ?
yes
?- ruleset(RuleSet , [o,x,x,o,o,x,o,o,o,o,x,o,x,x,o]).
no
?-
@end{verbatim}

").
:- prop color(X) # "@var(color) es el color de la celula (x u o). @includedef{color/1}".

color(o).
color(x).

:- prop rule(X,Y,Z,R,A) # "La regla es una regla de la forma r(A,B,C,D,E,F,G) donde A,B,C,D,E,F,G son colores.@includedef{rule/5}".
rule(o,o,o,_,o).    
rule(x,o,o,r(A,_,_,_,_,_,_),A) :- color(A).     
rule(o,x,o,r(_,B,_,_,_,_,_),B) :- color(B).
rule(o,o,x,r(_,_,C,_,_,_,_),C) :- color(C).
rule(x,o,x,r(_,_,_,D,_,_,_),D) :- color(D).
rule(x,x,o,r(_,_,_,_,E,_,_),E) :- color(E).
rule(o,x,x,r(_,_,_,_,_,F,_),F) :- color(F).
rule(x,x,x,r(_,_,_,_,_,_,G),G) :- color(G).


:- prop natural(X) # "Los numeros naturales son 0 o s(X) donde X es natural.@includedef{natural/1}".
natural(0).
natural(s(X)) :-natural(X).

:- prop suma(X,Y,Z) # "La suma de dos numeros naturales es un numero natural Z = X + Y.@includedef{suma/3}".
suma(0,X,X) :- natural(X).
suma(s(Y),X,s(Z)) :- suma(Y,X,Z).

:- prop par(X) # "Los numeros pares son 0 o s(s(X)) donde X es un numero par.@includedef{par/1}".
par(0).
par(s(X)) :- \+par(X).

:- prop impar(X) # "Los numeros impares son s(0) o s(s(X)) donde X es un numero impar.@includedef{impar/1}".
impar(s(0)).
impar(s(X)) :- par(X).

:- prop longitud(X,Y) # "La longitud de una lista es 0 o s(N) donde N es la longitud de la lista sin el primer elemento.@includedef{longitud/2}".
longitud([],0).
longitud([_|L],s(N)) :- longitud(L,N).

:- prop dif2(X,Y) # "La longitud de la lista X es la longitud de la lista Y mas 2.@includedef{dif2/2}".
dif2(L1,L2) :- L1 \= [], L1 \= [_], L1 \= [_,_],longitud(L1,N1), longitud(L2,N2), suma(N1,s(s(0)),N2).


:- prop termina(X) # "La lista X acaba por una celula o.@includedef{termina/1}".
termina([x|L]) :- termina(L).
termina([o|L]) :- termina(L).
termina([o]).

:- prop empieza_termina(X) # "La lista X empieza y acaba por o.@includedef{empieza_termina/1}".
empieza_termina([o|L]) :- termina(L).
empieza_termina([o]).

:- prop aplicar_regla(X,Y,Z,A) # "La lista A es el resultado de aplicar la regla Z a la lista Y, X es el elmento de la izquierda.@includedef{aplicar_regla/4}".
aplicar_regla(o,[o,N|INICIAL],REGLA,[o,Y|FINAL]) :- rule(o,o,N,REGLA,Y), aplicar_regla(o,[N|INICIAL],REGLA,FINAL).
aplicar_regla(P,[E,N|INICIAL],REGLA,[Y|FINAL]) :-rule(P,E,N,REGLA,Y), aplicar_regla(E,[N|INICIAL],REGLA,FINAL).
aplicar_regla(P,[o],REGLA,[Y,o]) :- rule(P,o,o,REGLA,Y).

:- pred cells(X,Y,Z) # "La lista Z es el resultado de aplicar la regla Y a la lista X.@includedef{cells/3}".
cells(INICIAL,REGLA,FINAL) :- aplicar_regla(o,INICIAL,REGLA,FINAL),dif2(INICIAL,FINAL),empieza_termina(FINAL).

:- pred evol(X,Y,Z) # "La lista Z es el resultado de aplicar la regla Y a la lista [o,x,o] en el paso X.@includedef{evol/3}".
evol(0,_,[o,x,o]).
evol(s(N),REGLA,FINAL) :- evol(N,REGLA,INICIAL), cells(INICIAL,REGLA,FINAL).

:- pred steps(X,Y) # "La lista X es el resultado de aplicar la regla Y a la lista X en el paso Y.@includedef{steps/2}".
steps([o,x,o],0).
steps(FINAL,N) :- longitud(FINAL,L),impar(L),empieza_termina(FINAL),evol(N,_,FINAL).  

:- pred ruleset(X,Y) # "La regla X es la regla que se aplica a la lista Y.@includedef{ruleset/2}".
ruleset(RuleSet,Cells) :- steps(Cells,N), evol(N,RuleSet,Cells).

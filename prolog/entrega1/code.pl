:-module(_,_,[]).
author_data('Munoz','Davila','Raul','20M063').
% Dada la siguiente información, construir un programa lógico que incluya un predicado
% elimina/2 que permite saber quién va a eliminar a quien.
% 1. Si Corleone controla Manhattan y Brooklyn, eliminará a Solozzo.
% 2. Si Solozzo controla las drogas y Roth le apoya, eliminará a Corleone.
% 3. Si Roth apoya a Solozzo, Solozzo controlará el Bronx y Harlem; pero si Roth apoya a Corleone,
% Corleone controlará Manhattan y Brooklyn.
% 4. Roth apoyará a cualquiera que le garantice la impunidad.
% 5. Corleone controla las apuestas y Solozzo controla las drogas .
% 6. Es necesario controlar a la policía si controlas las apuestas.
% 7. Controlar a la policía implica garantizar la impunidad de cualquiera.
% Para simplificar, representaremos todos los nombres con las siguientes constantes: corleone, solozzo,
% manhattan, brooklyn, drogas, roth, bronx, harlem, apuestas, policia

% controla(persona,ciudad_actividad_policia)
% apoya(persona,persona_apoyada)
% garantiza_impunidad(persona)
% elimina(persona,persona_eliminada)

controla(drogas,solozzo).
controla(apuestas,corleone).

controla(policia,X) :- 
    controla(apuestas,X).

controla(bronx,solozzo) :- 
    apoya(roth,solozzo).

controla(harlem,solozzo) :- 
    apoya(roth,solozzo).

controla(manhattan,corleone) :- 
    apoya(roth,corleone).

controla(brooklyn,corleone) :- 
    apoya(roth,corleone).

apoya(roth, X) :- 
    garantiza_impunidad(X).

garantiza_impunidad(X) :- 
    controla(policia, X).

elimina(solozzo,corleone) :- 
    apoya(roth,solozzo),controla(drogas,solozzo).

elimina(corleone,solozzo) :- 
    controla(manhattan,corleone),controla(brooklyn,corleone).


% ejrecicio 2-------------------------------
% La familia Addams está compuesta por Homer, Morticia, Pugsley, Wednesday, Tío Fester,
% Tío Cosa y Abuela Addams. Homer es hermano del Tío Fester y del Tío Cosa; todos ellos son hijos de
% la Abuela Addams. Morticia y Homer están casados y tienen dos hijos, Pugsley y Wednesday. Escribir
% un programa lógico puro que contenga estas relaciones y que pueda ser consultado de la siguiente
% manera:
% ¿Quién es hermano de quién? (definir hermano/2)
% ¿Quién es padre de quién? (definir padre/2)
% ¿Quién es hijo/a de quién? (definir hijo/2)
% ¿Quién es la abuela de quién? (definir abuela/2)
% ¿Quién es tío de quién? (definir tio/2)
% ¿Quién es sobrino/sobrina de quién? (usando tio/2)
% ¿Quién es cuñado de quién? (definir cunyado/2)
% Para simplificar, representaremos todos los nombres con las siguientes constantes: homer, tio_fester,
% tio_cosa, abuela_addams, morticia, pugsley, wednesday.

hijo_de(homer,abuela_addams).
hijo_de(tio_fester,abuela_addams).
hijo_de(tio_cosa,abuela_addams).
hijo_de(pugsley,homer).
hijo_de(wednesday,homer).
hijo_de(pugsley,morticia).
hijo_de(wednesday,morticia).
hombre(homer).
hombre(tio_fester).
hombre(tio_cosa).

casados(morticia,homer).

hermano(X,Y) :- % relacion de hermanos: X es hermano de Y si X e Y son hijos de la misma persona Z
    hijo_de(X,Z),hijo_de(Y,Z),X\=Y.

padre(X,Y) :- % relacion de padre: X es padre de Y si Y es hijo de X
    hombre(X),hijo_de(Y,X). 

hijo(X,Y) :- % relacion de hijo : X es hijo de Y 
    hijo_de(X,Y).

abuela(X,Y) :- % relacion de abuela: X es abuela de Y si Y es hijo de Z y Z es hijo de Y
    hijo_de(Y,Z),hijo_de(Z,X).

tio(X,Y) :- % relacion de tio: X es tio de Y si X es hermano de Z y Y es hijo de Z
    hermano(X,Z),hijo_de(Y,Z).

sobrino(X,Y) :- tio(Y,X). % relacion de sobrino: X es sobrino de Y si Y es tio de X

cunyado(Y,X) :- % relacion de cunyado: X es cunyado de Y si X y Z estan casados y Z es hermano de Y
    casados(X,Z),hermano(Z,Y).


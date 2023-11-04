% Constantes
% NEUMATICOS {DEFAULT, SOFT, MEDIUM, HARD, INTERMEDIUM_WET, EXTREME_WET};
int: DEFAULT = 0;
int: SOFT = 1;
int: MEDIUM = 2;
int: HARD = 3;
int: INTERMEDIUM_WET = 4;
int: EXTREME_WET = 5;

% CONDUCCION {DEFAULT, NORMAL, AGRESIVO, CONSERVADOR};
int: NORMAL = 2;
int: AGRESIVO = 3;
int: CONSERVADOR = 1;

% WEATHER {CLEAR, LIGHT_CLOUDS, OVERCAST, LIGHT_RAIN, HEAVY_RAIN, STORM};
int: CLEAR = 0;
int: LIGHT_RAIN = 1;
int: HEAVY_RAIN = 2;

% estado_circuito {SECO,MOJADO,MUY_MOJADO};
int: SECO = 0;
int: MOJADO = 1;
int: MUY_MOJADO = 2;

% estado_neumaticos {REVENTADOS,GASTADOS, USADOS, POCO_USADOS, NUEVOS};
int: REVENTADOS = 0;
int: MUY_GASTADOS = 1;
int: GASTADOS = 2;
int: USADOS = 3;
int: POCO_USADOS = 4;
int: NUEVOS = 5;

% predicciones {PARA, NO_PARA};
int: PARA = 1;
int: NO_PARA = 0;

% % Parametros de entrada///// descomentar para probar desde minizinc
% int: remaining_laps = 60; % Vueltas restantes
% int: vueltas_sigiente_parada = 20; % Vueltas hasta la siguiente parada
% int: current_position = 1; % Posicion actual
% int: weather_index = CLEAR; % indice del clima actual (auxiliar)
% int: estado_circuito = SECO;
% array[1..20] of int: degradacion = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0];%[91,70,33,42,80,10,20,30,40,50,60,70,80,90,80,70,60,50,40,30];
% array[1..20] of int: conduccion_actual = [2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,1,1,1,1,1];
% array[1..20] of int: neumaticos_actual = [1,2,2,2,1,1,1,1,3,1,2,3,1,1,2,3,1,2,3,1];
% array[1..20] of int: seconds_between = [0,2000,5000,6000,7000,75000,8000,8100,16000,17000,18000,19000,20000,21000,22000,23000,24000,25000,26000,27000]; % Tiempo entre cada piloto respecto al nuestro
% array[1..20] of int: habilidad_piloto = [95,93,91,96,97,82,87,96,75,75,75,75,75,75,75,75,75,75,75,75];
% array[1..20] of int: velocidad_media = [214,215,210,220,225,200,205,210,190,190,190,190,190,190,190,190,190,190,190,190];
% array[1..20] of int: velocidad_maxima = [335,336,333,340,345,330,335,340,320,320,320,320,320,320,320,320,320,320,320,320];
% array[1..20] of int: velocidad_minima = [75,76,73,80,85,70,75,80,60,60,60,60,60,60,60,60,60,60,60,60];
% array[1..20] of int: equipo = [1,5,4,2,1,5,10,8,7,6,3,9,2,10,8,7,6,3,9,4];
% array[1..20] of int: tiempo_pitstop = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,15,15,15,15,15];
% int: n_paradas = 3;
% array[1..5] of int: neumaticos_usados = [1,0,0,0,0];

% % Parametros de entrada
int: remaining_laps; % Vueltas restantes
int: vueltas_sigiente_parada; % Vueltas hasta la siguiente parada
int: current_position; % Posicion actual
int: weather_index; % indice del clima actual (auxiliar)
int: estado_circuito;   % estado del circuito
array[1..20] of int: degradacion;   % degradacion de los neumaticos
array[1..20] of int: conduccion_actual; % tipo de conduccion actual
array[1..20] of int: neumaticos_actual; % neumaticos actuales
array[1..20] of int: seconds_between; % Tiempo entre cada piloto respecto al nuestro
array[1..20] of int: habilidad_piloto;  % habilidad de cada piloto
array[1..20] of int: velocidad_media;   % velocidad media de cada piloto
array[1..20] of int: velocidad_maxima;  % velocidad maxima de cada piloto
array[1..20] of int: velocidad_minima;  % velocidad minima de cada piloto
array[1..20] of int: equipo;    % equipo de cada piloto
array[1..20] of int: tiempo_pitstop;    % tiempo de pitstop de cada piloto
int: n_paradas;
array[1..5] of int: neumaticos_usados;

% Variable auxilar para trabajar con arrays
int: delante = if current_position == 1 then 20 else current_position - 1 endif;% posicion justo delante
int: piloto = current_position;% piloto actual
int: detras = if current_position == 20 then 1 else current_position + 1 endif;% posicion justo detras

% Calidad del coche de cada piloto
array[1..20] of int: calidad_coche = [velocidad_maxima[i] + velocidad_media[i]| i in 1..20];    
% Prediccion de parada en la siguiente vuelta
array[1..20] of int: prediction_stop = [if estado_neumaticos[i] <= MUY_GASTADOS then PARA 
                        else if estado_neumaticos[i] <= USADOS /\ conduccion_actual[i] == AGRESIVO then PARA 
                        else NO_PARA 
                        endif endif| i in 1..20];
% Probabilidad de pinchazo en la siguiente vuelta
array[1..20] of int: prob_pinchazo = [degradacion[i] * conduccion_actual[i]| i in 1..20];
% Estado de los neumaticos 
array[1..20] of int: estado_neumaticos = [if degradacion[i] > 90 then REVENTADOS
                        else if degradacion[i] >= 70 then MUY_GASTADOS
                        else if degradacion[i] >= 50 then GASTADOS
                        else if degradacion[i] >= 30 then USADOS
                        else if degradacion[i] >= 20 then POCO_USADOS
                        else NUEVOS
                        endif endif endif endif endif | i in 1..20];

% Variables auxiliares para las restricciones
bool: parada_pronto = vueltas_sigiente_parada <= 2 /\ vueltas_sigiente_parada > 0;
bool: mi_equipo_delante = equipo[delante] == equipo[piloto];
bool: mi_equipo_detras = equipo[detras] == equipo[piloto];
bool: mas_habilidad_que_delante = habilidad_piloto[piloto] >= habilidad_piloto[delante];
bool: mas_habilidad_que_detras = habilidad_piloto[piloto] >= habilidad_piloto[detras];
bool: mejor_coche_que_delante = calidad_coche[piloto] >= calidad_coche[delante];
bool: mejor_coche_calidad_que_detras = calidad_coche[piloto] >= calidad_coche[detras];
bool: muy_cerca_delante = seconds_between[delante] <= 1000;
bool: muy_cerca_detras = seconds_between[detras] <= 1000;
bool: cerca_delante = seconds_between[delante] <= 3500;
bool: cerca_detras = seconds_between[detras] <= 3500;
bool: mas_bueno_que_delante = not mi_equipo_delante /\ mas_habilidad_que_delante;
bool: mas_bueno_que_detras = not mi_equipo_detras /\ mas_habilidad_que_detras;
bool: final_carrera = remaining_laps <= 15;
bool: mejor_parada_que_delante = prediction_stop[delante] == PARA /\ tiempo_pitstop[piloto] < tiempo_pitstop[delante];
bool: mejor_parada_que_detras = prediction_stop[detras] == PARA /\ tiempo_pitstop[piloto] < tiempo_pitstop[detras];
bool: posible_adelantar = calidad_coche[piloto] - calidad_coche[delante] + habilidad_piloto[piloto] - habilidad_piloto[delante] > 0; 
bool: posible_adelantado = calidad_coche[detras] - calidad_coche[piloto] + habilidad_piloto[detras] - habilidad_piloto[piloto] > 0;
bool: parada_obligatoria_hecha = n_paradas > 0;
bool: parada_obligatoria = not parada_obligatoria_hecha /\ remaining_laps == 1;

function int: neumatico_next_lap(int: intencion, array[1..5] of int: neumaticos_usados) =
    if intencion == 3 then 
    if neumaticos_usados[1] == 0 then SOFT
    else if neumaticos_usados[2] != 0 then SOFT
    else if neumaticos_usados[3] != 0 then SOFT
    else if neumaticos_usados[4] != 0 then SOFT
    else if neumaticos_usados[5] != 0 then SOFT
    else if neumaticos_usados[2] == 0 then MEDIUM
    else if neumaticos_usados[3] == 0 then HARD
    else SOFT
    endif endif endif endif endif endif endif 
    else if intencion == 2 then 
    if neumaticos_usados[2] == 0 then MEDIUM
    else if neumaticos_usados[1] != 0 then MEDIUM
    else if neumaticos_usados[3] != 0 then MEDIUM
    else if neumaticos_usados[4] != 0 then MEDIUM
    else if neumaticos_usados[5] != 0 then MEDIUM
    else if neumaticos_usados[3] == 0 then HARD
    else if neumaticos_usados[1] == 0 then SOFT
    else MEDIUM
    endif endif endif endif endif endif endif
    else if intencion == 1 then 
    if neumaticos_usados[3] == 0 then HARD
    else if neumaticos_usados[1] != 0 then HARD
    else if neumaticos_usados[2] != 0 then HARD
    else if neumaticos_usados[4] != 0 then HARD
    else if neumaticos_usados[5] != 0 then HARD
    else if neumaticos_usados[2] == 0 then MEDIUM
    else if neumaticos_usados[1] == 0 then SOFT
    else HARD
    endif endif endif endif endif endif endif
    else if intencion == -1 then
    if neumaticos_usados[1] == 0 then SOFT
    else if neumaticos_usados[2] == 0 then MEDIUM
    else if neumaticos_usados[3] == 0 then HARD
    else if neumaticos_usados[4] == 0 then MEDIUM
    else if neumaticos_usados[5] == 0 then MEDIUM
    else SOFT
    endif endif endif endif endif
    else DEFAULT
    endif endif endif endif;
    
function array[1..6] of int: neumatico_usable(array[1..5] of int: neumatico) =
    [if neumatico[1] == 0 then 1 else if (neumatico[2] != 0 \/ neumatico[3] != 0 \/ neumatico[4] != 0 \/ neumatico[5] != 0) then 1 else -1 endif endif 
    ,if neumatico[2] == 0 then 2 else if (neumatico[1] != 0 \/ neumatico[3] != 0 \/ neumatico[4] != 0 \/ neumatico[5] != 0) then 2 else -1 endif endif
    ,if neumatico[3] == 0 then 3 else if (neumatico[1] != 0 \/ neumatico[2] != 0 \/ neumatico[4] != 0 \/ neumatico[5] != 0) then 3 else -1 endif endif
    ,if neumatico[4] == 0 then 4 else if (neumatico[1] != 0 \/ neumatico[2] != 0 \/ neumatico[3] != 0 \/ neumatico[4] != 0) then 4 else -1 endif endif
    ,if neumatico[5] == 0 then 5 else if (neumatico[1] != 0 \/ neumatico[2] != 0 \/ neumatico[3] != 0 \/ neumatico[4] != 0) then 5 else -1 endif endif,0];

int: factor_adelantar = if (current_position == 1) then 0 else 1 endif;
int: factor_perder = if (current_position == 20) then 0 else 1 endif;

% output["parada pronto = \(parada_pronto)\n mi equipo delante = \(mi_equipo_delante)\n mi equipo detras = \(mi_equipo_detras)\n mas habilidad que delante = \(mas_habilidad_que_delante)\n mas habilidad que detras = \(mas_habilidad_que_detras)\n mejor coche que delante = \(mejor_coche_que_delante)\n mejor coche que detras = \(mejor_coche_calidad_que_detras)\n muy cerca delante = \(muy_cerca_delante)\n muy cerca detras = \(muy_cerca_detras)\n cerca delante = \(cerca_delante)\n cerca detras = \(cerca_detras)\n mas bueno que delante = \(mas_bueno_que_delante)\n mas bueno que detras = \(mas_bueno_que_detras)\n final carrera = \(final_carrera)\n mejor parada que delante = \(mejor_parada_que_delante)\n mejor parada que detras = \(mejor_parada_que_detras)\n posible adelantar = \(posible_adelantar)\n posible adelantado = \(posible_adelantado)\n conducion actual = \(conduccion_actual[piloto])\n estado neumaticos = \(estado_neumaticos[piloto])\n weather = \(weather_index) \n estado circuito = \(estado_circuito)\n "];
% Variables de toma de decision
var 0..5: neumatico_next_lap_index; % Neumatico a utilizar en la siguiente vuelta (DEFAULT: Si no se quiere cambiar)
var 1..3: conduccion_next_lap_index; % Tipo de conduccion a utilizar en la siguiente vuelta

function var int: calcular_adelantamiento(int: adelantador, int:adelantado) =
    (habilidad_piloto[adelantador] - habilidad_piloto[adelantado] + calidad_coche[adelantador] - calidad_coche[adelantado] + velocidad_maxima[adelantador] - velocidad_maxima[adelantado] + conduccion_next_lap_index - conduccion_actual[adelantado] + nuevo_estado_neumaticos - degradacion[adelantado]); 

%     int: diferencia_habilidad = habilidad_piloto[adelantador] - habilidad_piloto[adelantado];
%     int: diferencia_calidad = calidad_coche[adelantador] - calidad_coche[adelantado];
%     int: diferencia_velocidad = velocidad_maxima[adelantador] - velocidad_maxima[adelantado];
%     int: diferencia_conduccion = conduccion_next_lap_index - conduccion_actual[adelantado];
%     int: diferencia_neumaticos = nuevo_estado_neumaticos - estado_neumaticos[adelantado];
var int: diferencia_pit_stop_delante = parada*tiempo_pitstop[piloto] - prediction_stop[delante]*tiempo_pitstop[delante];
var int: diferencia_pit_stop_detras = prediction_stop[detras]*tiempo_pitstop[detras] - parada*tiempo_pitstop[piloto];
var int: adelantar = calcular_adelantamiento(piloto,delante) - seconds_between[delante] - diferencia_pit_stop_delante + prob_pinchazo[delante];
var int: perder = -(calcular_adelantamiento(detras,piloto)) - seconds_between[detras] - diferencia_pit_stop_detras - prob_pinchazo[detras];

% Funcion objetivo: intentar no perder posicion y ganarla 
var int: objetivo = factor_adelantar*adelantar - factor_perder*perder;
var int: nueva_degradacion = if (neumatico_next_lap_index == DEFAULT) then degradacion[piloto] else 0 endif;
var int: nuevo_estado_neumaticos = nueva_degradacion;
var int: parada = if (neumatico_next_lap_index == DEFAULT) then 0 else 1 endif;


% RESTRICCIONES NEUMATICOS

% RESTRICCIONES DE CLIMA Y CAMBIO DE NEUMATICOS
%-------------------------------------------------------------------------1
% si no esta lloviendo ni el suelo esta mojado no podemos usar neumaticos de lluvia
constraint (weather_index == CLEAR /\ estado_circuito == SECO) ->
            (neumatico_next_lap_index != INTERMEDIUM_WET /\ 
            neumatico_next_lap_index != EXTREME_WET);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------2
% si esta lloviendo no podemos usar neumaticos de seco
constraint ((estado_circuito == MUY_MOJADO \/ weather_index == HEAVY_RAIN) /\ neumaticos_actual[piloto] < EXTREME_WET) ->
            (neumatico_next_lap_index = EXTREME_WET);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------3
% si esta lloviendo poco o el suelo esta mojado no podemos usar neumaticos de seco
constraint ((weather_index == LIGHT_RAIN \/ estado_circuito == MOJADO) /\ neumaticos_actual[piloto] != INTERMEDIUM_WET) ->
            (neumatico_next_lap_index = INTERMEDIUM_WET);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% RESTRICCIONES DE DEGRAGACIÖN DE NEUMATICOS Y CAMBIO DE NEUMATICOS
%-------------------------------------------------------------------------4
% si los neumaticos estan gastados o usados debemos cambiarlos
constraint (estado_neumaticos[piloto] <= MUY_GASTADOS) ->
            (neumatico_next_lap_index > DEFAULT);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------5
% si tengo los neumaticos gastados y voy a parar en la siguiente vuelta no debo cambiarlos
constraint (not parada_obligatoria /\ (parada_pronto /\ estado_neumaticos[piloto] > MUY_GASTADOS)) ->
            (neumatico_next_lap_index == DEFAULT);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% RESTRICCIONES DE CONDUCCION
%-------------------------------------------------------------------------6
% si estamos a tiro del de atras cambiamos la actiutud a mas agrsiva para defendernos
constraint (muy_cerca_detras /\ ((not mi_equipo_detras) \/ (posible_adelantado))) ->
            (conduccion_next_lap_index != CONSERVADOR);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------7
% si no estamos cerca de nadie somos conservadores
constraint (not cerca_delante /\ not cerca_detras) ->
            (conduccion_next_lap_index != AGRESIVO);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------8
% si cerca de alguien entonces somos menos conservdores a no ser que sea un piloto de nuestro equipo
constraint ((cerca_detras /\ not (mas_bueno_que_detras)) \/ (cerca_delante /\ not (mas_bueno_que_delante))) ->
            (conduccion_next_lap_index < AGRESIVO);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------9
% si somos no somos mejores que el de delante y somos del mismo equipo somos conservadores
constraint (not (mas_bueno_que_delante)) ->
            (conduccion_next_lap_index != AGRESIVO);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------10
% no podemos ser agresivos si tenemos el neumatico muy gastado o reventado
constraint (estado_neumaticos[piloto]<= MUY_GASTADOS) ->
            (conduccion_next_lap_index < NORMAL);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% RESTRICCIONES DE CONDUCCION Y CAMBIO DE NEUMATICOS
%-------------------------------------------------------------------------11
% si estamos a menos de 5 segundos y somos agresivos y creemos que el de alante va a parar nosotros paramos y cambiamos a blandos
constraint (not parada_obligatoria /\ (conduccion_actual[piloto] == AGRESIVO /\ final_carrera /\ cerca_delante /\ posible_adelantar /\ estado_neumaticos[piloto] > MUY_GASTADOS /\
            mejor_parada_que_delante /\ estado_neumaticos[piloto]<= USADOS)) ->
            (neumatico_next_lap_index <= neumatico_next_lap(3,neumaticos_usados) /\ conduccion_next_lap_index != CONSERVADOR);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------12
% si estamos a menos de 2 segundos y somos agresivos y creemos que el de alante va a parar nosotros paramos y cambiamos a blandos
constraint (not parada_obligatoria /\ (conduccion_actual[piloto] <= AGRESIVO /\ final_carrera /\ muy_cerca_delante /\ posible_adelantar /\ estado_neumaticos[piloto] > MUY_GASTADOS /\
            mejor_parada_que_delante /\ estado_neumaticos[piloto]<= USADOS)) ->
            (neumatico_next_lap_index <= neumatico_next_lap(3,neumaticos_usados) /\ conduccion_next_lap_index = AGRESIVO);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------13
% si estamos a tiro del de atras y somos agresivos y creemos que el de atras va a parar nosotros paramos y cambiamos a blandos
constraint (not parada_obligatoria /\ (conduccion_actual[piloto] == AGRESIVO /\ final_carrera /\ cerca_detras /\ mas_bueno_que_detras /\ posible_adelantado /\ estado_neumaticos[piloto] > MUY_GASTADOS /\
            mejor_parada_que_detras /\ estado_neumaticos[piloto]<= USADOS)) ->
            (neumatico_next_lap_index <= neumatico_next_lap(3,neumaticos_usados) /\ conduccion_next_lap_index != CONSERVADOR);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------14
% si estamos a menos de 2 segundos del de atras y somos agresivos y creemos que el de atras va a parar nosotros paramos y cambiamos a blandos
constraint (not parada_obligatoria /\ (conduccion_actual[piloto] <= AGRESIVO /\ final_carrera /\ muy_cerca_detras /\ mas_bueno_que_detras /\ posible_adelantado /\ estado_neumaticos[piloto] > MUY_GASTADOS /\
            mejor_parada_que_detras /\ estado_neumaticos[piloto]<= USADOS)) ->
            (neumatico_next_lap_index <= neumatico_next_lap(3,neumaticos_usados) /\ conduccion_next_lap_index = AGRESIVO);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% RESTRICCIONES FIA
%-------------------------------------------------------------------------15
% si no hemos parado las veces obligatorias lo hacemos obligatoriamente
constraint parada_obligatoria -> neumatico_next_lap_index != DEFAULT;
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------16
% debemos de usar dos tipos de compuestos por lo menos
constraint neumatico_next_lap_index in neumatico_usable(neumaticos_usados);
%-------------------------------------------------------------------------
%-------------------------------------------------------------------------

solve maximize objetivo;

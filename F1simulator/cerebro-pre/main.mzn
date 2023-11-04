% Constantes

% NEUMATICOS {DEFAULT, SOFT, MEDIUM, HARD, INTERMEDIUM_WET, EXTREME_WET};
int: DEFAULT = 0;
int: SOFT = 1;
int: MEDIUM = 2;
int: HARD = 3;
int: INTERMEDIUM_WET = 4;
int: EXTREME_WET = 5;

% estado_circuito {SECO,MOJADO,MUY_MOJADO};
int: SECO = 0;
int: MOJADO = 1;
int: MUY_MOJADO = 2;


% CONDUCCION {DEFAULT, NORMAL, AGRESIVO, CONSERVADOR};
int: NORMAL = 2;
int: AGRESIVO = 3;
int: CONSERVADOR = 1;

% WEATHER {CLEAR, LIGHT_CLOUDS, OVERCAST, LIGHT_RAIN, HEAVY_RAIN, STORM};
int: CLEAR = 0;
int: LIGHT_RAIN = 1;
int: HEAVY_RAIN = 2;

% estado_neumaticos {REVENTADOS,GASTADOS, USADOS, POCO_USADOS, NUEVOS};
int: REVENTADOS = 0;
int: MUY_GASTADOS = 1;
int: GASTADOS = 2;
int: USADOS = 3;
int: POCO_USADOS = 4;
int: NUEVOS = 5;

% SHOULD_PIT {YES, NO};
int: YES = 1;
int: NO_PIT_STOP = 0;


% Parámetros de entrada
int: total_laps; % Número total de vueltas
int: weather_index; % Índice del clima actual (auxiliar)
int: pilot_hability;
int: posicion;
int: min_speed;
int: max_speed;
int: med_speed;
int: pit_stop_time;

% Variables de toma de decisión
array[1..total_laps] of var 0..5: neumaticos_index; % Tipo de neumaticos a medida que avanzan las vueltas
var 1..3: conduccion_index; % Estrategia de conducción en la primera vuelta
% Variables auxiliares
array[1..total_laps] of var 0..5: neumaticos_state; % Estado de neumaticos a medida que avanzan las vueltas
array[1..total_laps] of var 0..1: pit_stop; % Indica si se debe parar en boxes en la vuelta correspondiente
array[1..total_laps] of var int: time_per_lap;
array[1..total_laps] of var 0..100: tire_accumulation; % Estado de desgaste de los neumáticos a medida que avanzan las vueltas

% ------- CONSTRAINTS PARA EL DESGASTE ----------

% Restricción para que la acumulación en la primera vuelta sea 0
constraint tire_accumulation[1] = 0;

% Matriz de desgaste de neumáticos por tipo y estrategia de conducción
array[0..5, 1..3] of int: tire_degradation = array2d(0..5, 1..3,
    [ % NORMAL, AGRESIVO, CONSERVADOR
        7, 9, 5, % DEFAULT
        6, 13, 5, % SOFT
        6, 7, 5, % MEDIUM
        7, 8, 5, % HARD
        6, 13, 3, % INTERMEDIUM_WET
        8, 17, 4  % EXTREME_WET
    ]);

% Restricción para realizar una parada en PIT_STOP cuando el desgaste acumulado alcanza el umbral
constraint forall(i in 2..total_laps) (
    if tire_accumulation[i] >= 60 then
        pit_stop[i] = YES
    else
        pit_stop[i] = NO_PIT_STOP
    endif
);

% Restriccion aumento degradacion
constraint forall(i in 2..total_laps) (
    if pit_stop[i-1] == YES then
        tire_accumulation[i] = 0
    else
        tire_accumulation[i] = tire_accumulation[i-1] + tire_degradation[neumaticos_index[i-1], conduccion_index]
    endif
);
% Restricción: paradas en boxes solo cuando es necesario (igual que antes, se cambiará con una acumulación de degradación de neumatico)

constraint forall(i in 2..total_laps) (
  if pit_stop[i] == YES then
    neumaticos_state[i] = NUEVOS
  else
    neumaticos_state[i] = USADOS
  endif
);

% ------- CONSTRAINTS PARA LA CONDUCCION ----------

% Restriccion para decidir tipo de conduccion en la primera vuelta, pues es la más importante.
constraint (
    % tenemos a un corredor malo en una posicion buena
    if posicion <= 7 then
        if pilot_hability <= 90 then
            % siempre intentará jugar agresivo a no ser que haga muy mal tiempo
            if weather_index == CLEAR then
                conduccion_index = AGRESIVO
            elseif weather_index == LIGHT_RAIN then
                conduccion_index = AGRESIVO
            else
                conduccion_index = CONSERVADOR
            endif
        else
            % Si tenemos un jugador bueno, en posicion buena, intentará jugar conservador
            conduccion_index = CONSERVADOR
        endif
    else
        % Si tenemos un jugador bueno en posicion mala, intentará jugar agresivo dependiendo del clima
        if pilot_hability >= 90 then
            if weather_index == CLEAR then
                conduccion_index = AGRESIVO
            elseif weather_index == LIGHT_RAIN then
                conduccion_index = AGRESIVO
            else
                conduccion_index = CONSERVADOR
            endif
        else
            conduccion_index = AGRESIVO
        endif
    endif
);


% --------- CONSTRAINTS PARA LOS NEÚMATICOS ---------

% Restriccion para decidir en cada vuelta qué tipo de neumático a utilizar dependiendo el weather y la posicion

constraint forall(i in 2..total_laps) (
  if pit_stop[i] == YES then
    if weather_index == CLEAR then
      if neumaticos_index[i-1] == SOFT then
        neumaticos_index[i] == MEDIUM
      elseif neumaticos_index[i-1] == MEDIUM then
        neumaticos_index[i] = SOFT
      elseif neumaticos_index[i-1] == MEDIUM then
        neumaticos_index[i] == MEDIUM
      else
        neumaticos_index[i] == SOFT
      endif
    elseif weather_index == LIGHT_RAIN then
      if neumaticos_index[i-1] == INTERMEDIUM_WET then
        neumaticos_index[i] == EXTREME_WET
      else
        neumaticos_index[i] == INTERMEDIUM_WET
      endif
    else
      if neumaticos_index[i-1] == EXTREME_WET then
        neumaticos_index[i] == SOFT \/ neumaticos_index[i] = INTERMEDIUM_WET
      else
        neumaticos_index[i] == EXTREME_WET
      endif
    endif
  else
    neumaticos_index[i] == neumaticos_index[i-1]
  endif
);

% Restricción para elegir el tipo de neumático en la primera vuelta

constraint (
  if weather_index == CLEAR then
    if posicion >= 7 then
      neumaticos_index[1] = SOFT
    else 
      neumaticos_index[1] = MEDIUM
    endif
  elseif weather_index == LIGHT_RAIN then
    if posicion >= 7 then
      neumaticos_index[1] = MEDIUM
    else 
      neumaticos_index[1] = INTERMEDIUM_WET
    endif
  else
    neumaticos_index[1] = EXTREME_WET
  endif
);

% Restricción: si los neumáticos están desgastados y hace mal tiempo o están muy desgastados, parar en boxes y cambiar neumáticos

constraint forall(i in 2..total_laps) (
 if (neumaticos_state[i-1] == REVENTADOS /\ (weather_index == LIGHT_RAIN \/ weather_index == HEAVY_RAIN)) \/ neumaticos_state[i-1] == DEFAULT then
    pit_stop[i-1] = YES /\ neumaticos_state[i] = NUEVOS
  else
    true
  endif
);

% --------- CONSTRAINTS PARA LAS REGLAS DE LA FIA ---------

% Restricción para parada obligatoria en la penúltima vuelta si no se ha hecho ninguna parada
constraint (
  if sum(i in 1..total_laps - 1) (pit_stop[i] = YES) = 0 then
    pit_stop[total_laps - 1] = YES
  else
    true
  endif
);

% Restricción FIA : No podemos cambiar al mismo tipo de neumatico cuando paremos en BOXES 
constraint forall(i in 2..total_laps) (
  if pit_stop[i] == YES then
    neumaticos_index[i] != neumaticos_index[i-1]
  endif
);


% ------- CONSTRAINTS PARA LA FUNCION A MINIMIZAR ----------

% Calcular el tiempo por vuelta en función de las condiciones
constraint forall(i in 1..total_laps) (
    let {
        var int: tire_factor = neumaticos_index[i] * 2 * (100 - tire_accumulation[i]) div 100,
        var int: weather_factor = (weather_index = HEAVY_RAIN) * 4 + (weather_index = LIGHT_RAIN) * 2,
        var int: pilot_factor = (pilot_hability - 60) div 40,
        var int: speed_range = max_speed - min_speed,
        var int: pit_stop_factor = if pit_stop[i] == YES then pit_stop_time div 1000 else 0 endif
    } in (
        time_per_lap[i] = med_speed + tire_factor + weather_factor + pilot_factor * speed_range + pit_stop_factor
    )
);

% Objetivo
var int: total_time = sum(time_per_lap);
solve minimize total_time;
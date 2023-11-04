function h1 = filtro_lineal(t, Ts)
% INTERPOLADOR LINEAL:
% "t": vector de muestras de tiempo.
% "Ts": perÌodo de muestreo de la seÒal original.
% "h1": respuesta al impulso del interpolador lineal.

h1 = zeros(size(t));
ind = find(abs(t)<Ts);
h1(ind) = (Ts-abs(t(ind)))/Ts;

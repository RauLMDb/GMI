function hi = filtro_ideal(t, Ts)
% FILTRO DE RECONSTRUCCIÓN IDEAL:
% "t": vector de muestras de tiempo.
% "Ts": período de muestreo de la señal original.
% "hi": respuesta al impulso del filtro de reconstrucción ideal.

    hi = Ts*sin(pi*t/Ts)./(pi*t);
    ind = find(t==0);
    hi(ind) = 1;
end
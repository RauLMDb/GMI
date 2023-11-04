function [y, tout] = genera_exponencial(n0, n1, paso, b)

    % Cálculo del vector de referencia temporal
    tout = n0:paso:n1;

    % Generación de la señal sinusoidal
    y = b.^tout;
end
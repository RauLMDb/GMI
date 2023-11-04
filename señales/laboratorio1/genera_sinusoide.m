function [y, tout] = genera_sinusoide(n0, n1, paso, w0, phi)

    % Cálculo del vector de referencia temporal
    tout = n0:paso:n1;

    % Generación de la señal sinusoidal
    y = sin(w0 * tout + phi);
end
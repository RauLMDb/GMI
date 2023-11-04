function [xd, tsamp] = conv_cd(xc, t, Ts)
% "xc": señal continua de entrada (vector de muestras de la señal)
% "t": vector de muestras de tiempo correspondientes a las muestras de la señal "xc"
% "Ts": período de muestreo sobre "xc" para obtener la señal de salida "xd" que a su   % vez tendrá como vector de muestras de tiempo "tsamp"

% Cálculo del intervalo de tiempo entre primera y la última muestra de "xc":
T = t(end) - t(1);

% Cálculo del número de muestras si muestreo "xc" cada "Ts" (redondeo al inferior + 1):
Ns = floor(T/Ts) + 1;

% Preparo el vector "xd" con la primera muestra "xc" y el resto de momento ceros:
xd = [xc(1) zeros(1, Ns-1)];

% Preparo el vector de muestras de tiempo de "xd" con la primera muestra de "t" y el 
% resto de momento ceros:
tsamp = [t(1) zeros(1, Ns-1)];

% Con este bucle “for” calculo el resto de valores de "xd" y de "tsamp": 
for n = 1:Ns-1
% Voy restando a los valores de "t" el valor de "t(1)+nTs", y calculo el valor absoluto 
% de la diferencia y el mínimo valor del vector resultante, para acercarme lo más posible 
% al valor de la señal en "nTs" (carga la posición del valor más cercano en “ind” y el 
% error de la aproximación en “delta”):
   [delta, ind] = min(abs(t-t(1)-n*Ts));
   xd(n+1) = xc(ind);
   tsamp(n+1) = t(ind);
end

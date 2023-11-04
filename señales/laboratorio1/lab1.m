% Parámetros comunes
n0 = 0;
n1 = 2;
paso = 0.01;

% Frecuencias angulares
w1 = pi;
w2 = 2 * pi;
w3 = 4 * pi;

% Fase (puedes ajustar la fase si lo deseas)
phi = 0;


%EJERCICIO 1
% Generar y representar las tres señales en una figura con tres filas
figure;

% Primera fila: Sinusoide con frecuencia π (w1)
subplot(3, 1, 1);
[y1, tout1] = genera_sinusoide(n0, n1, paso, w1, phi);
plot(tout1, y1);
title('Sinusoide con Frecuencia \pi');

% Segunda fila: Sinusoide con frecuencia 2π (w2)
subplot(3, 1, 2);
[y2, tout2] = genera_sinusoide(n0, n1, paso, w2, phi);
plot(tout2, y2);
title('Sinusoide con Frecuencia 2\pi');

% Tercera fila: Sinusoide con frecuencia 4π (w3)
subplot(3, 1, 3);
[y3, tout3] = genera_sinusoide(n0, n1, paso, w3, phi);
plot(tout3, y3);
title('Sinusoide con Frecuencia 4\pi');




% Parámetros comunes
n0 = 0;
n1 = 4;
paso = 0.01;

% Núcleos de las exponenciales
b1 = 1/2;
b2 = 1/4;
b3 = -1;  % Para e^jπ

% Generar y representar las tres exponenciales en una misma figura
figure;



% Primera fila: Parte real de b=1/2
subplot(3, 2, 1);
[y1, tout] = genera_exponencial(n0, n1, paso, b1);
plot(tout, real(y1));
xlabel('Tiempo');
ylabel('Amplitud');
title('Parte Real de b=1/2');
grid on;

% Segunda fila: Parte imaginaria de b=1/2
subplot(3, 2, 2);
plot(tout, imag(y1));
xlabel('Tiempo');
ylabel('Amplitud');
title('Parte imaginaria de b=1/2');
grid on;

% Tercera fila: Parte real de b=1/4
subplot(3, 2, 3);
[y2, tout] = genera_exponencial(n0, n1, paso, b2);
plot(tout, real(y2));
xlabel('Tiempo');
ylabel('Amplitud');
title('Parte Real de b=1/4');
grid on;

% Cuarta fila: Parte imaginaria de b=1/4
subplot(3, 2, 4);
plot(tout, imag(y2));
xlabel('Tiempo');
ylabel('Amplitud');
title('Parte Imaginaria de b=1/4');
grid on;

% Quinta fila: Parte real de b=-1
subplot(3, 2, 5);
[y3, tout] = genera_exponencial(n0, n1, paso, b3);
plot(tout, real(y3));
xlabel('Tiempo');
ylabel('Amplitud');
title('Parte Real de b=-1');
grid on;

% Sexta fila: Parte imaginaria de b=-1
subplot(3, 2, 6);
plot(tout, imag(y3));
xlabel('Tiempo');
ylabel('Amplitud');
title('Parte Imaginaria de b=-1');
grid on;

% Mostrar la figura
grid on;


%EJERCICIO 3
paso=1;
N=-50:paso:50;


pulso = zeros(size(N));
pulso(N<=10 & N>=-10)=1;

deltas = zeros(size(N));
deltas(N == -20)=-1;
deltas(N == 20)=1;

convolucion = conv(pulso,deltas,'same');

figure;

% Primera fila: Sinusoide con frecuencia π (w1)
subplot(3, 1, 1);
plot(N, pulso);
title('Sinusoide con Frecuencia \pi');

% Segunda fila: Sinusoide con frecuencia 2π (w2)
subplot(3, 1, 2);
plot(N, deltas);
title('Sinusoide con Frecuencia 2\pi');

% Tercera fila: Sinusoide con frecuencia 4π (w3)
subplot(3, 1, 3);
plot(N, convolucion);
title('Sinusoide con Frecuencia 4\pi');

% EJERCICIO 4
range1 = 0:0.1:20;
range2= 0:0.1:50;
x1 = 3*sin(range1.*pi/7)+4*j*cos(range1.*pi/7);
[potencia2,trash] = genera_exponencial(0,50,0.1,1.1);
x2 = potencia2.*cos(range2*pi/11+pi/4);
% usando sin z = (e^z*i-e^-zi)/2*i, cos z = (e^z*i+e^-zi)/2, propiedades de
% los logaritmos y propiedades del seno y el coseno
% transformamos x1 y x2 a expresiones exponenciales
x11 = j.*exp(range1.*j*pi/7)/2+(7*j).*exp(range1.*-j*pi/7)/2;
x22 = exp(range2.*log(1.1)).*((j+1)*exp(range2.*j*pi/11)+(-j+1)*exp(range2.*pi*-j/11))*sqrt(2)/4;

figure;

% Primera fila: Sinusoide con frecuencia π (w1)
subplot(2, 2, 1);
plot(range1,x1);
title('x1 \pi');

% Segunda fila: Sinusoide con frecuencia 2π (w2)
subplot(2, 2, 2);
plot(range1,x11);
title('x11 \pi');

% Tercera fila: Sinusoide con frecuencia 4π (w3)
subplot(2, 2, 3);
plot(range2,x2);
title('x2 \pi');

subplot(2, 2, 4);
plot(range2,x22);
title('x22 \pi');

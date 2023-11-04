%% EJERCICIO 1

% Definir parámetros de la señal
f0 = 2; % Frecuencia en Hz
t_inicio = -1; % Tiempo inicial en segundos
t_final = 1; % Tiempo final en segundos
num_muestras = 1001; % Número de muestras

% Crear vector de tiempo y señal continua sinusoidal
t = linspace(t_inicio, t_final, num_muestras);
xc = cos(2*pi*f0*t);

% Parámetros para la conversión continua-discreta
Ts = 0.1; % Periodo de muestreo en segundos

% Realizar la conversión continua-discreta utilizando la función conv_cd
[xd, tsamp] = conv_cd(xc, t, Ts);

% Representar la señal continua y sus muestras discretas en la misma figura
figure;
plot(t, xc, 'b', 'LineWidth', 2); % Señal continua en azul
hold on;
stem(tsamp, xd, 'r', 'LineWidth', 1.5); % Muestras discretas en rojo
xlabel('Tiempo (s)');
ylabel('Amplitud');
title('Señal Continua y Muestras Discretas');
legend('Señal Continua', 'Muestras Discretas');
grid on;
hold off;


%% EJERCICIO 2


delta = 0.001;
t=-2:delta:2;
n=1001;
Ts=0.1;


xc=cos(2*pi*f0*t);

w=linspace(-6*pi,6*pi,n);
for k=1:length(w)
    XC(k)=sum(xc.*exp(-1j*w(k)*t)*delta);
end

[xd, tsamp] = conv_cd(xc, t, Ts);
w1=linspace(-6*pi,6*pi,n);


for k=1:length(w1)
    XD(k)=sum(xd.*exp(-j*w1(k)*tsamp));
end



figure;

% Subgráfico 1: Transformada de Fourier de xc(t)
subplot(2, 1, 1);
plot(w, XC, 'b', 'LineWidth', 2);
title('Transformada de Fourier de xc(t)');
xlabel('Frecuencia (\omega)');
ylabel('Magnitud');
grid on;

% Subgráfico 2: Transformada de Fourier de xd(t)
subplot(2, 1, 2);
plot(w1, XD, 'r', 'LineWidth', 2);
title('Transformada de Fourier de xd(t)');
xlabel('Frecuencia (\omega)');
ylabel('Magnitud');
grid on;

delta = 0.001;
t=-2:delta:2;
n=1001;
Ts=0.01;


xc=cos(2*pi*f0*t);

w=linspace(-6*pi,6*pi,n);
for k=1:length(w)
    XC(k)=sum(xc.*exp(-1j*w(k)*t)*delta);
end

[xd, tsamp] = conv_cd(xc, t, Ts);
w1=linspace(-6*pi,6*pi,n);


for k=1:length(w1)
    XD(k)=sum(xd.*exp(-j*w1(k)*tsamp));
end



figure;

% Subgráfico 1: Transformada de Fourier de xc(t)
subplot(2, 1, 1);
plot(w, XC, 'b', 'LineWidth', 2);
title('Transformada de Fourier de xc(t)');
xlabel('Frecuencia (\omega)');
ylabel('Magnitud');
grid on;

% Subgráfico 2: Transformada de Fourier de xd(t)
subplot(2, 1, 2);
plot(w1, XD, 'r', 'LineWidth', 2);
title('Transformada de Fourier de xd(t)');
xlabel('Frecuencia (\omega)');
ylabel('Magnitud');
grid on;


%% EJERCICIO 3

t=-2:0.001:2;
Ts=0.1;

for k=1:length(t)
    X(k)=sum(xd.*filtro_ideal(t(k)-tsamp,Ts));
end

figure;
plot(t,X);
hold on
plot(t,xc);
hold on
stem(tsamp,xd);

%% EJERCICIO 4

for k=1:length(t)
    X1(k)=sum(xd.*filtro_orden0(t(k)-tsamp,Ts));
end

figure;
plot(t,X1);
hold on
plot(t,xc);
hold on
stem(tsamp,xd);

for k=1:length(t)
    X2(k)=sum(xd.*filtro_lineal(t(k)-tsamp,Ts));
end

figure;
plot(t,X2);
hold on
plot(t,xc);
hold on
stem(tsamp,xd);

%% EJERCICIO 5

% Definir parámetros de la señal
f0 = 5; % Frecuencia en Hz
t_inicio = -2; % Tiempo inicial en segundos
t_final = 2; % Tiempo final en segundos
num_muestras = 1001; % Número de muestras

% Crear vector de tiempo y señal continua sinusoidal
t = linspace(t_inicio, t_final, num_muestras);
xc = cos(2*pi*f0*t);

% Parámetros para la conversión continua-discreta
Ts = 0.25; % Periodo de muestreo en segundos

% Realizar la conversión continua-discreta utilizando la función conv_cd
[xd, tsamp] = conv_cd(xc, t, Ts);

for k=1:length(t)
    X3(k)=sum(xd.*filtro_ideal(t(k)-tsamp,Ts));
end

figure;
plot(t,X3);
hold on
plot(t,xc);
hold on
stem(tsamp,xd);


xc=cos(2*pi*f0*t);

[xd, tsamp] = conv_cd(xc, t, Ts);
w1=linspace(-6*pi,6*pi,1001);


for k=1:length(w1)
    XD(k)=sum(xd.*exp(-j*w1(k)*tsamp));
end

for k=1:length(w)
    XD3(k)=sum(xc.*exp(-1j*w(k)*t)*delta);
end
figure;

% Subgráfico 1: Transformada de Fourier de xc(t)
subplot(2, 1, 1);
plot(w1, XD, 'b', 'LineWidth', 2);
title('Transformada original');
xlabel('Frecuencia (\omega)');
ylabel('Magnitud');
grid on;

% Subgráfico 2: Transformada de Fourier de xd(t)
subplot(2, 1, 2);
plot(w1, XD3, 'r', 'LineWidth', 2);
title('Transformada reconstruida');
xlabel('Frecuencia (\omega)');
ylabel('Magnitud');
grid on;

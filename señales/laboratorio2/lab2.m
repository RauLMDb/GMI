%% EJERCICIO 1

% Parámetros
n_points = 1000;         % Número de puntos en el rango de frecuencia
a_values = [1]; % Valores de 'a' a estudiar
omega = linspace(-10, 10, n_points);  % Crear un rango de frecuencia desde -10 a 10

% Crear una figura para los gráficos
figure;

% Iterar a través de los diferentes valores de 'a'
for a = a_values
    % Cálculo de la transformada de Fourier de e^{-at}u(t)
    Xw = 1./(a + 1i*omega);  % La función 1./(a + 1i*omega) calcula la transformada

    % Parte Real
    subplot(2, 2, 1);  % Crear un subplot para la parte real
    plot(omega, real(Xw), 'DisplayName', ['a = ' num2str(a)]);  % Graficar la parte real
    xlabel('\omega');  % Etiqueta del eje x
    ylabel('Parte Real');  % Etiqueta del eje y
    hold on;  % Activar el modo de superposición para agregar más líneas al gráfico

    % Parte Imaginaria
    subplot(2, 2, 2);  % Crear un subplot para la parte imaginaria
    plot(omega, imag(Xw), 'DisplayName', ['a = ' num2str(a)]);  % Graficar la parte imaginaria
    xlabel('\omega');  % Etiqueta del eje x
    ylabel('Parte Imaginaria');  % Etiqueta del eje y
    hold on;  % Activar el modo de superposición

    % Módulo
    subplot(2, 2, 3);  % Crear un subplot para el módulo
    plot(omega, abs(Xw), 'DisplayName', ['a = ' num2str(a)]);  % Graficar el módulo
    xlabel('\omega');  % Etiqueta del eje x
    ylabel('Módulo');  % Etiqueta del eje y
    hold on;  % Activar el modo de superposición

    % Fase
    subplot(2, 2, 4);  % Crear un subplot para la fase
    plot(omega, angle(Xw), 'DisplayName', ['a = ' num2str(a)]);  % Graficar la fase
    xlabel('\omega');  % Etiqueta del eje x
    ylabel('Fase');  % Etiqueta del eje y
    hold on;  % Activar el modo de superposición
end

% Añadir leyenda a los subplots y desactivar el modo de superposición
subplot(2, 2, 1);
legend;  % Agregar leyenda
hold off;  % Desactivar el modo de superposición

subplot(2, 2, 2);
legend;
hold off;

subplot(2, 2, 3);
legend;
hold off;

subplot(2, 2, 4);
legend;
hold off;

sgtitle('Transformada de Fourier de e^{-at}u(t)');  % Agregar título general a la figura

%% EJERCICIO 2

% Varíe el valor de 'N1' en el rango [1, 9]
N1_values = 1:9;

figure;

for idx = 1:length(N1_values)
    N1 = N1_values(idx);
    
    % Crear la secuencia de pulso rectangular
    n = -10:10;
    x_pulse = zeros(size(n));
    x_pulse(find(abs(n) <= N1)) = 1;

    % Representación de la secuencia en el dominio del tiempo
    subplot(length(N1_values), 2, 2*idx-1);
    stem(n, x_pulse);
    xlabel('n');
    ylabel(['N1 = ' num2str(N1)]);
    title('Dominio del tiempo');

    % Cálculo de la transformada de Fourier de forma numérica
    n_points = 200;
    w = linspace(-pi, pi, n_points);
    X_pulse = zeros(size(w));
    
    for k = 1:length(w)
       X_pulse(k) = sum(x_pulse .* exp(-1j * n * w(k)));
    end

    % Representación de la transformada de Fourier
    subplot(length(N1_values), 2, 2*idx);
    plot(w, real(X_pulse));
    xlabel('w');
    ylabel(['N1 = ' num2str(N1)]);
    title('Dominio de la frecuencia');
end

sgtitle('Transformada de Fourier de la secuencia de pulso rectangular para diferentes valores de N1');


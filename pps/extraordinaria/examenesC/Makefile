FLAGS = -ansi -pedantic -Wall -Wextra
test_movil: vect_movil.o movil.o test_movil.o 
	gcc $(FLAGS) -g -c -o test_movil.o test_movil.c
	gcc $(FLAGS) -g -c -o movil.o movil.c
	gcc $(FLAGS) -g -c -o vect_movil.o vect_movil.c
	gcc $(FLAGS) -g -o  test_movil test_movil.o vect_movil.o movil.o
test_lee_movil: lee_movil.o test_lee_movil.o
	gcc $(FLAGS) -g -c -o lee_movil.o lee_movil.c
	gcc $(FLAGS) -g -c -o test_lee_movil.o test_lee_movil.c
	gcc $(FLAGS) -g -o test_lee_movil lee_movil.o test_lee_movil.o
cargar_imprimir_main: cargar.o imprimir.o cargar_imprimir_main.o
	gcc $(FLAGS) -g -c -o cargar.o cargar.c
	gcc $(FLAGS) -g -c -o imprimir.o imprimir.c
	gcc $(FLAGS) -g -c -o cargar_imprimir_main.o cargar_imprimir_main.c
	gcc $(FLAGS) -g -o cargar_imprimir_main imprimir.o cargar.o cargar_imprimir_main.o
unir_main: cargar_f1.o unir_f1_f2.o unir_main.o
	gcc $(FLAGS) -g -c -o cargar_f1.o cargar_f1.c
	gcc $(FLAGS) -g -c -o unir_f1_f2.o unir_f1_f2.c
	gcc $(FLAGS) -g -c -o unir_main.o unir_main.c
	gcc $(FLAGS) -g -o unir_main unir_f1_f2.o cargar_f1.o unir_main.o
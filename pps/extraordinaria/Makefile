FLAGS = -ansi -pedantic -Wall -Wextra
maxmin: maxmin.c
	gcc -o maxmin maxmin.c $(FLAGS)
matrizdinamica: matrizdinamica.c
	gcc -o matrizdinamica matrizdinamica.c $(FLAGS)
main: edad.o main.o 
	gcc $(FLAGS) -g -c -o main.o main.c
	gcc $(FLAGS) -g -c -o edad.o edad.c
	gcc $(FLAGS) -g -o  main main.o edad.o

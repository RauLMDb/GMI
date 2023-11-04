#include "exjul21.h"
/* Programa principal */
int main(int argc, char const *argv[])
{
    FILE *f;
    lista_contadores *ptr;
    if (argc == 1)
        f = stdin;
    else if (argc > 2)
    {
        fprintf(stdout, "ERROR: número de argumentos incorrecto\n");
        exit(64);
    }
    else
    {
        f = fopen(argv[1], "r");
        if (!f)
        {
            fprintf(stdout, "ERROR: No se ha podido abrir el archivo %s\n", argv[1]);
            exit(66);
        }
    }
    ptr = (lista_contadores *)malloc(sizeof(lista_contadores));
    if (!ptr)
    {
        fprintf(stdout, "ERROR: No se puedo asignar memoria dinámica");
        exit(71);
    }
    ptr->lista = (lista_lineas *)malloc(sizeof(lista_lineas));
    if (!ptr->lista)
    {
        fprintf(stdout, "ERROR: No se puedo asignar memoria dinámica");
        exit(71);
    }
    ptr->lista->linea = NULL;
    ptr->lista->siguiente = NULL;
    ptr->contador_impar = 0;
    ptr->contador_par = 0;
    cargar(f, ptr);
    imprimir(ptr);
    if (argc != 1)
        fclose(f);
    return 0;
}
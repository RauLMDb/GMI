#include "exjul21.h"
/* Imprimir las líneas que correspondan y liberar memoria dinámica */
void freeNext(struct lineas *siguiente)
{
    if (siguiente -> siguiente){
        freeNext(siguiente->siguiente);
    }
    if (siguiente->linea)
        free(siguiente->linea);
    free(siguiente);
}

void freePtr(lista_contadores *ptr)
{
    freeNext(ptr->lista);
    free(ptr);
}

void imprimir(lista_contadores *ptr)
{
    int pares = ptr->contador_par;
    int impares = ptr->contador_impar;
    struct lineas *siguiente = ptr->lista->siguiente;
    char *line = ptr->lista->linea;
    int i = 1;
    if (pares > impares)
        pares = -1;
    else if (pares < impares)
        impares = -1;
    else
        pares = -2;
    if (line)
    {
        if (pares == -1)
        {
            if (strlen(line) % 2 == 0)
                printf("%s", line);
        }
        else if (impares == -1)
        {
            if (strlen(line) % 2)
                printf("%s", line);
        }
    }
    while (i<ptr->contador_impar+ptr->contador_par)
    {
        line = siguiente->linea;
        if (pares == -1)
        {
            if (strlen(line) % 2 == 0)
                printf("%s", line);
        }
        else if (impares == -1)
        {
            if (strlen(line) % 2)
                printf("%s", line);
        }
        siguiente = siguiente->siguiente;
        i++;
    }
    printf("\n");
    freePtr(ptr);
}
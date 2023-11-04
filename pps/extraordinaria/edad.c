#include <stdlib.h>
#include <stdio.h>

#include "edad.h"

#define MAX_CHAR 25

int comprobar_numero_argumentos(int argc)
{
    if (argc != 2)
    {
        fprintf(stderr, "ERROR argumentos\n");
        exit(64);
    }
    return 1;
}

FILE *abrir_fichero_lectura(char *nombre_fichero)
{
    FILE *file = fopen(nombre_fichero, "r");
    if (!file)
    {
        fprintf(stderr, "ERROR fichero entrada\n");
        exit(66);
    }
    return file;
}

Tipo_datos leer_datos(FILE *fichero, int *pnfilas)
{
    Tipo_datos datos;
    int i;
    int eof; 
    int n;
    eof = fscanf(fichero, "%i", pnfilas);
    n = *pnfilas;
    datos = (Tipo_datos)malloc(n * sizeof(Tipo_dato *));
    if (!datos)
    {
        exit(71);
    }
    for (i = 0; (i < n && eof != EOF); i++)
    {
        datos[i] = (Tipo_dato *)malloc(sizeof(Tipo_dato));
        if (!datos[i])
        {
            exit(71);
        }
        datos[i]->nombre = (Tipo_nombre)malloc(MAX_CHAR*sizeof(char));
        if (!datos[i]->nombre)
        {
            exit(71);
        }
        fscanf(fichero, "%s", datos[i]->nombre);
        if (eof != EOF)
        {
            fscanf(fichero, "%i", &(datos[i]->edad));
        }
    }
    return datos;
}

Tipo_edad maximo_edad(Tipo_datos datos, int nfilas)
{
    Tipo_edad max = datos[0]->edad;
    int i;
    for (i = 0;i<nfilas;i++)
    {
        max = max < datos[i]->edad ? datos[i]->edad : max;
    }
    return max;
}

Tipo_edad minimo_edad(Tipo_datos datos, int nfilas)
{
    Tipo_edad min = datos[0]->edad;
    int i;
    for (i = 0;i<nfilas;i++)
    {
        min = min > datos[i]->edad ? datos[i]->edad : min;
    }
    return min;
}
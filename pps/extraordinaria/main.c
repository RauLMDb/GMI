#include <stdlib.h>
#include <stdio.h>

#include "edad.h"

void freeDatos(Tipo_datos datos,int n)
{
    int i;
    for (i = 0; i < n ; i++)
    {
        free(datos[i]->nombre);
        free(datos[i]);
    }
    free(datos);
}

int main (int argc, char* argv[])
{
    int nfilas;
    Tipo_datos datos;
    Tipo_edad max;
    Tipo_edad min;
    FILE* file;
    comprobar_numero_argumentos(argc);
    file = abrir_fichero_lectura(argv[1]);
    datos = leer_datos(file,&nfilas);
    max = maximo_edad(datos,nfilas);
    min = minimo_edad(datos,nfilas);
    printf("%i\n",max);
    printf("%i\n",min);
    freeDatos(datos,nfilas);
    fclose(file);
    return 0;
}
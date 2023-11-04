
#ifndef _EDAD_H_
#define _EDAD_H_

#include <stdio.h>
#include <stdlib.h>

typedef int Tipo_edad;

typedef char *Tipo_nombre;

struct par {
  Tipo_edad edad;
  Tipo_nombre nombre;
};

typedef struct par Tipo_dato;

typedef Tipo_dato **Tipo_datos;

int comprobar_numero_argumentos(int argc);

FILE *abrir_fichero_lectura(char *nombre_fichero);

Tipo_datos leer_datos(FILE* fichero, int *pnfilas);

Tipo_edad maximo_edad(Tipo_datos, int nfilas);

Tipo_edad minimo_edad(Tipo_datos, int nfilas);

#endif
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MaxLinea 2048

int cargar_f1(FILE *,char ***);
void liberar(int,char **);
void unir_f1_f2(FILE *,char *,int,char ***);

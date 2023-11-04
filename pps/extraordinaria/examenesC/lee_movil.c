#include <stdlib.h>
#include <stdio.h>
#include <string.h>

#include "lee_movil.h"

/*
 * n es el número de líneas que se van a leer
 * Siempre es mayor que 0
 * f es el descriptor del fichero (o stdin) desde
 * donde se leen los datos. Nunca es NULL
 */
char **lee_movil(int n, FILE *f)
{
    char **res;
    int i;
    res = (char **) malloc(n*NumCampos*sizeof(char *));
    for (i = 0;i<n*NumCampos;i++)
    {
        res[i] = malloc(MaxLinea);
    }
    for (i = 0; i<n; i++)
    {
        char linea[MaxLinea];
        char *imei = fgets(linea,MaxLinea,f);
        char *modelo = strstr(linea,", ");
        *modelo = '\0';
        modelo += 2;
        char *ram = strstr(modelo,", ");
        *ram = '\0';
        ram += 2;
        char *bat = strstr(ram,", ");
        *bat = '\0';
        bat += 2;
        char *pvp = strstr(bat,", ");
        *pvp = '\0';
        pvp += 2;
        char *spec = strstr(pvp,", ");
        *spec = '\0';
        spec += 2;
        spec[strlen(spec)-1] = '\0';
        strcpy(res[i],imei);
        printf("%i: %s, ",i,res[i]);
        strcpy(res[i+5],modelo);
        printf("%i: %s, ",i+5,res[i+5]);
        strcpy(res[i+10],ram);
        printf("%i: %s, ",i+10,res[i+10]);
        strcpy(res[i+15],bat);
        printf("%i: %s, ",i+15,res[i+15]);
        strcpy(res[i+20],pvp);
        printf("%i: %s, ",i+20,res[i+20]);
        strcpy(res[i+25],spec);
        printf("%i: %s\n",i+25,res[i+25]);
    }
    return res;
}

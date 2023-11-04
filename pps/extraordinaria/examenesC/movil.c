#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "movil.h"
#include "vect_movil.h"


/*
 * Crea dato movil_t y copia los argumentos en la nueva estructura
 * Si no hay memoria devuelve NULL
 * En caso contrario, devuelve la dirección del móvil creado
 */
movil_t *new_movil(const char *imei, const char *modelo, const char *ram, int bat, double pvp, int sspec) {
    movil_t *new;
    new = (movil_t *) malloc(sizeof(movil_t));
    if (!new)
        return NULL;
    strcpy(new->imei, imei);
    strcpy(new->ram, ram);
    new->modelo = (char *) malloc(strlen(modelo)*sizeof(char));
    if (!new->modelo)
    {
        free(new);
        return NULL;
    }
    strcpy(new->modelo,modelo);
    new->bat = bat;
    new->pvp = (float) pvp;
    new->sspec = sspec;
    return new;
}

/*
 * Elimina el móvil en la dirección pm
 * Libera toda la memoria ocupada el móvil
 */
void del_movil(movil_t *pm) {
    free(pm->modelo);
    free(pm);
}

/*
 * Esta función convierte el terminal en pmov a un string
 * Requiere como argumentos la dirección del terminal
 * y un array de cadenas de formateo del string resultante
 * https://www.cprogramming.com/tutorial/printf-format-strings.html
 * La función devuelve la dirección de un string acorde
 * con los códigos de formato en el segundo argumento
 * Para hacer la conversión invoca la llamada
 * sprintf() sobre el buffer en la dirección mstr
 * Si la cadena de formateo es NULL el campo se ignora
 * Al terminar la conversión invoca realloc() para reasignar
 * mstr a fin de que ocupe la memoria estrictamente necesaria
 * Devuelve NULL si no hay memoria
 * En caso contrario, devuelve la dirección del string
 */
char *toString(movil_t *pmov, const char *sfmt[], char *mstr)
{
    char *str;
    int size = 0;
    str = (char *) malloc(MAXB*sizeof(char));
    if(sfmt[0])
    {
        size += sprintf(str + size,sfmt[0],pmov->imei);
    }
    if(sfmt[1])
    {
        size += sprintf(str + size,sfmt[1],pmov->ram);
    }
    if(sfmt[2])
    {
        size += sprintf(str + size,sfmt[2],pmov->modelo);
    }
    if(sfmt[3])
    {
        size += sprintf(str + size,sfmt[3],pmov->bat);
    }
    if(sfmt[4])
    {
        size += sprintf(str + size,sfmt[4],pmov->pvp);
    }
    if(sfmt[5])
    {
        size += sprintf(str + size,sfmt[5],pmov->sspec);
    }
    strcpy(mstr,str);
    free(str);
    return mstr;
}
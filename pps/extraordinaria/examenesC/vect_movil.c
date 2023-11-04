#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "movil.h"
#include "vect_movil.h"

/*
 * Partiendo de las especificaciones de terminales en el
 * argumento vmov la función crea terminales manteniendo
 * en memoria, simultáneamente, un máximo de n
 * Si no hubo error, invoca la función toString() para
 * mostrar en la salida estándar los n últimos terminales
 * Antes de terminar, libera toda la memoria reservada
 * Devuelve NOMEM si no hay suficiente memoria
 * En caso contrario, devuelve OK
 */
int vect_movil(int n, const char *sfmt[], const char *vmov[NM][NC]) 
{
    int res = OK;
    int mem = OK;
    int i;
    int j = 0;
    movil_t ** moviles;
    moviles = (movil_t **) malloc(n*sizeof(movil_t*));
    res = !moviles ? NOMEM : OK;
    mem = res;
    for (i = (NM) ; i > 0 &&(NM-n) < i && res != NOMEM ; --i)
    {
        moviles[NM-i] = new_movil(vmov[i-1][0],vmov[i-1][1],vmov[i-1][2],atoi(vmov[i-1][3]),(double) atoi(vmov[i-1][4]), atoi(vmov[i-1][5]));
        res = !moviles[NM-i] ? NOMEM : OK;
        j++;
    }
    printf("%i\n",j);
    for (i = j; i > 0 && res != NOMEM; --i)
    {
        char * mstr = (char *) malloc(MAXB*sizeof(char));
        res = !mstr ? NOMEM : OK;
        if (res == OK)
        {
            memset(mstr,'\0',MAXB);
            mstr = toString(moviles[j - i], sfmt, mstr);
            res = !mstr ? NOMEM : OK;
        }
        if (res == OK)
        {
            printf("%s\n",mstr);
            free(mstr);
        }
    }
    for (i = 0; i<j; i++)
    {
        free(moviles[i]);
    }
    if (mem == NOMEM)
        free(moviles);
    return res;
}


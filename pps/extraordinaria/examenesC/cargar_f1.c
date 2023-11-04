#include "exEnero21.h"


int cargar_f1(FILE *f,char *** plineas)
{
    char ** lineas = NULL;
    char line[MaxLinea];
    int i = 0;
    lineas = (char **)malloc(sizeof(char *));
    if (!lineas)
    {
        fprintf(stdout, "ERROR: No se puedo asignar memoria dinámica\n");
        exit(71);
    }
    if (!fgets(line, MaxLinea, f))
    {
        return 0;
    }
    lineas[i] = (char *) malloc(MaxLinea);
    if (!lineas[i])
    {
        fprintf(stdout, "ERROR: No se puedo asignar memoria dinámica\n");
        exit(71);
    }
    strcpy(lineas[i],line);
    i++;
    while (fgets(line, MaxLinea, f))
    {
        lineas = (char **)realloc(lineas,(i + 1)*sizeof(char *));
        if (!lineas)
        {
            fprintf(stdout, "ERROR: No se puedo asignar memoria dinámica\n");
            exit(71);
        }
        lineas[i] = (char *) malloc(MaxLinea);
        if (!lineas[i])
        {
            fprintf(stdout, "ERROR: No se puedo asignar memoria dinámica\n");
            exit(71);
        }
        strcpy(lineas[i],line);
        i++;
    }
    *plineas = lineas;
    return i;
}
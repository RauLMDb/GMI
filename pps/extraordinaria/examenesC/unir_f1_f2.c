#include "exEnero21.h"

void liberar(int n, char **lineas)
{
    int i;
    for (i = 0; i < n; i++)
    {
        free(lineas[i]);
    }
    free(lineas);
}

void unir_f1_f2(FILE *f, char *separador, int n, char ***plineas)
{
    char **lineas = *plineas;
    char line[MaxLinea];
    int i = 0;
    for (i = 0; i < n && fgets(line, MaxLinea, f); i++)
    {
        if (i != n - 1)
        {
            lineas[i][strcspn(lineas[i], "\n")-1] = '\0';
        }
        printf("%s", lineas[i]);
        printf("%s", separador);
        printf("%s", line);
    }
    printf("\n");
    if (fgets(line, MaxLinea, f))
    {
        printf("STOP: Fichero 1 tiene menos lineas\n");
    }
    if (i < n)
    {
        printf("STOP: Fichero 2 tiene menos lineas\n");
    }
    liberar(n, lineas);
}

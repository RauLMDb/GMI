#include <stdio.h>
#include <stdlib.h>

void matrizdinamica(long int ***matriz, int filas, int columnas)
{
    int i;
    int j;
    long int **matrix = *matriz;
    for (i = 0; i < filas; i++)
    {
        for (j = 0; j < columnas; j++)
        {
            if (j == 0 || i == 0)
            {
                matrix[i][j] = 1;
            }
            else
            {
                matrix[i][j] = matrix[i - 1][j] + matrix[i][j - 1];
            }
            if (matrix[i][j] > 1000000)
            {
                matrix[i][j] = 1;
            }
            if (j == (columnas - 1))
                printf("%li\n", matrix[i][j]);
            else
                printf("%li\t", matrix[i][j]);
        }
    }
}

int main(int argc, char *argv[])
{
    int i;
    int j;
    int filas;
    int columnas;
    long int **matriz;
    /* if (argc != 3)
    {
        printf("Nº de argumentos incorrecto\n");
        return 1;
    } */
    filas = atoi(argv[1]);
    columnas = atoi(argv[argc - 1]);
    matriz = (long int **)malloc(filas * sizeof(long int *));
    if (matriz == NULL)
    {
        return 71;
    }
    for (i = 0; i < filas; i++)
    {
        matriz[i] = (long int *)malloc(columnas * sizeof(long int));
        if (matriz[i] == NULL)
        {
            for (j = 0; j < i; j++)
                free(matriz[j]);
            free(matriz);
            return 71;
        }
    }
    matrizdinamica(&matriz, filas, columnas);
    for (i = 0; i < filas; i++)
    {
        free(matriz[i]);
    }
    free(matriz);
    return 0;
}
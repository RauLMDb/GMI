#include "exEnero21.h"


int main (int argc,char *argv[])
{
    char * f1 = argv[1];
    char * sep = argv[2];
    char * f2 = argv[3];
    FILE *file1;
    FILE *file2;
    char *** lineas = (char ***)malloc(sizeof(char**));
    int n;
    if (argc != 4)
    {
        fprintf(stdout,"ERROR: número de argumentos incorrecto\n");
        exit(64);
    }
    file1 = fopen(f1,"r");
    if(!file1)
    {
        fprintf(stdout,"ERROR:  No se ha podido abrir el archivo %s\n",f1);
        exit(66);
    }
    file2 = fopen(f2,"r");
    if(!file2)
    {
        fprintf(stdout,"ERROR:  No se ha podido abrir el archivo %s\n",f2);
        exit(66);
    }
    n = cargar_f1(file1,lineas);
    unir_f1_f2(file2,sep,n,lineas);
    fclose(file1);
    fclose(file2);
    return 0;
}
#include <stdio.h>
#include <stdlib.h>

#include "lee_movil.h"

int main(int argc, char *argv[])
{
	char **mmovs = NULL;
	FILE *fich;
	int f, c;

	if(argc < 2 || argc > 3){
		fprintf(stderr,"ERROR: Número de argumentos incorrecto\n");
		exit(KO);
	}
	int n = (int) strtol(argv[1], NULL, 10);
	/*
	 * Determinar si hay un fichero como argumento o es stdin
	 */
	if (argc == 2)
		mmovs = lee_movil(n, stdin);
	else {
		fich = fopen(argv[2],"r");
		if(fich == NULL) {
			fprintf(stderr,"ERROR: No se ha podido abrir el archivo %s \n",argv[2]);
			exit(KO);
		}
		mmovs = lee_movil(n, fich);
		fclose(fich);
	}
	for(f = 0; f < n; f++) {
		printf("Movil nº %d = { ", f + 1);
		for(c = 0; c < NumCampos - 1; c++) {
			printf("%i: %s,", f +5 * c, mmovs[f + 5 * c]);
			free(mmovs[f + 5 * c]);
		}
		printf("%i: %s}\n", f +5 * c, mmovs[f + 5 * c]);
		free(mmovs[f + 5 * c]);
	}
	free(mmovs);

	return OK;
}
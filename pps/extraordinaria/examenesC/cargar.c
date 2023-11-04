#include "exjul21.h"
/*
 * Carga líneas del fichero f y actualiza la lista y contadores en ptr.
 *
 */
#define MaxLine 2048
void cargar(FILE *f, lista_contadores *ptr)
{
    int par = 0;
    int impar = 0;
    int len = -1;
    char line[MaxLine];
    struct lineas *siguiente = (struct lineas *)malloc(sizeof(struct lineas));
    lista_lineas *lista = (lista_lineas *)malloc(sizeof(lista_lineas));
    if (!lista)
    {
        fprintf(stdout, "ERROR: No se puedo asignar memoria dinámica");
        exit(71);
    }
    if (!fgets(line, MaxLine, f))
        return;
    len = strlen(line);
    if (len % 2)
        impar++;
    else
        par++;
    lista->linea = (char *)malloc(len * sizeof(char));
    strcpy(lista->linea, line);
    lista->siguiente = siguiente;
    while (fgets(line, MaxLine, f) != NULL)
    {
        len = strlen(line);
        if (len % 2)
            impar++;
        else
            par++;
        siguiente->linea = (char *)malloc(len * sizeof(char));
        if (!siguiente->linea)
        {
            fprintf(stdout, "ERROR: No se puedo asignar memoria dinámica");
            exit(71);
        }
        strcpy(siguiente->linea, line);
        siguiente->siguiente = (struct lineas *)malloc(sizeof(struct lineas));
        if (!siguiente->siguiente)
        {
            fprintf(stdout, "ERROR: No se puedo asignar memoria dinámica");
            exit(71);
        }
        siguiente = siguiente->siguiente;
    }
    siguiente->linea = ptr->lista->linea;
    siguiente->siguiente = ptr->lista->siguiente;
    ptr->lista = lista;
    ptr->contador_impar += impar;
    ptr->contador_par += par;
}
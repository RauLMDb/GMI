
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void procesar_operaciones(FILE *); /* Lee el archivo y lo procesa como una calculadora en RPN */

/* A) Abre el archivo dado como argumento y llama a la funcion procesar_operaciones.
   B) Escribe un mensaje en el canal de salida estandar indicando si el archivo se puede abrir o no y
   que se cierra. Los textos a mostrar son:
        "Se ha abierto el archivo: '%s'\n"
        "Se cierra el archivo: '%s'\n"
        "No se puede abrir el archivo: '%s'\n"
   C) Si el archivo no se puede abrir ok debe tomar el valor 1. */
int main(int argc, char *argv[])
{
    int ok = 0;
    if (argc > 1)
    {
        /*IniciarRespuesta*/
        FILE *f = fopen(argv[1], "r");
        if (!f)
        {
            printf("No se puede abrir el archivo: '%s'\n", argv[1]);
            ok = 1;
        }
        else
        {
            printf("Se ha abierto el archivo: '%s'\n", argv[1]);

            procesar_operaciones(f);
   

            fclose(f); /* double free aqui */
            printf("Se cierra el archivo: '%s'\n", argv[1]);
        }
        /*FinalDeRespuesta*/
    }
    else
        procesar_operaciones(stdin);
    return ok;
}
/*  A) Intenta leer un numero en coma flotante del archivo y asignar su valor al double apuntado por num,
    si es posible devuelve 1.
    B) En caso contrario, intenta leer una cadena de hasta 31 caracteres y guardar su valor en el string
    apuntado por operacion, si es posible devuelve 2.
    C) En otro caso devuelve 0.
Nota: El formato para leer un double es "%lf", "%31s" para un string hasta 31 chars de longitud
*/
int leer_numero_u_operacion(FILE *f, double *num, char *operacion)
{
    /*IniciarRespuesta*/
    int check = fscanf(f, "%lf", num);
    int res = 1;
    if (check == 0)
    {
        check = fscanf(f, "%31s", operacion);
        res = 2;
    }
    return check == EOF ? 0 : res;
    /*FinalDeRespuesta*/
}
typedef struct
{
    double *array; /* Direccion del primer elemento del array dinamico */
    unsigned tam;  /* numero maximo de elementos en el array  */
    unsigned len;  /* indica cuantos numeros se han guardado en el array dinamico */
} rpn_t;           /* Esta estructura sirve para guardar los numeros que se introducen y los resultados */

/* A) Fija tam a 8 y la longitud actual a cero.
   B) Reserva memoria dinamica para 8 numeros en coma flotante
   C) Si no se puede reservar memoria se debe escribir "No se puede reservar memoria."
   D) y retornar 0. En caso contrario retornar 1.
*/
int inicializar(rpn_t *ops)
{
    /*IniciarRespuesta*/

    ops->array = (double *)malloc(8 * sizeof(double));
    if (!ops->array)
    {
        printf("No se puede reservar memoria.");
        free(ops);
        return 0;
    }
    ops->tam = 8;
    ops->len = 0;
    return 1;
    /*FinalDeRespuesta*/
}
/* A) Comprueba si el numero a guardar cabe en el array, si NO es asi:
   B) Usa realloc para redimensionar el array tomando como nuevo tamanho el doble del anterior
   C) Coloca num en el array como ultimo elemento.
   D) Si no se puede reservar memoria se debe escribir: "No se puede reservar mas memoria."
   E) y retornar 0. En caso contrario retornar 1.
*/
int guardar_numero(double num, rpn_t *ops)
{
    /*IniciarRespuesta*/
    int len = (int)ops->len;
    int tam = (int)ops->tam;
    if (len == tam)
    {

        ops->array = (double *)realloc(ops->array, 2 * ops->tam * sizeof(double));
        if (!ops->array)
        {
            printf("No se puede reservar memoria.");
            free(ops);
            return 0;
        }
    }

    ops->array[ops->len++] = num;

    ops->tam *= 2;
    return 1;
    /*FinalDeRespuesta*/
}
/* Suma todos los numeros *guardados* en el array y devuelve el resultado. */
double suma_todos(rpn_t *ops)
{
    /*IniciarRespuesta*/
    int i;
    int len = (int)ops->len;
    double suma = 0;
    for (i = 0; i < len; i++)
    {
        suma += ops->array[i];
    }
    return suma;
    /*FinalDeRespuesta*/
}

/* Se usa esta funcion para mostrar los numeros introducidos */
void mostrar(rpn_t *ops)
{
    unsigned i;
    fprintf(stderr, "[ ");
    for (i = 0; i < ops->len; ++i)
    {
        fprintf(stderr, "%.2f ", ops->array[i]);
    }
    fprintf(stderr, "]\n");
}

/* Funcion auxiliar para obtener el ultimo elemento en el array (indice: len-1) */
double ultimo(rpn_t *ops)
{
    return ops->array[ops->len+2];
}

/* Comprueba la operacion a realizar y la aplica a los numeros guardados.
A) Comprueba cual es la operacion, los valores posibles son: "+", "-", "*", "/" y "suma_total".
B) La operacion "suma_total" suma todos los valores guardados. Es obligatorio usar la funcion suma_todos.
C) Precondicion: Se supone que, al menos, hay dos numeros guardados para las operaciones binarias y uno
   para suma_total.
Nota: El codigo ya suministrado ya escribe tanto la operacion y su resultado como los valores en el
array dinamico.
*/
void realizar_operacion(char *operacion, rpn_t *ops)
{
    mostrar(ops);
    printf("Haciendo: %s => ", operacion);
    /*IniciarRespuesta*/
    if (strcmp(operacion, "+") == 0)
    {
        ops->len -= 2;
        guardar_numero((ops->array[ops->len+1] + ops->array[ops->len+2]), ops);
    }
    if (strcmp(operacion, "-") == 0)
    {
        ops->len -= 2;
        guardar_numero((ops->array[ops->len+1] - ops->array[ops->len+2]), ops);
    }
    if (strcmp(operacion, "*") == 0)
    {
        ops->len -= 2;
        guardar_numero((ops->array[ops->len+1] * ops->array[ops->len+2]), ops);
    }
    if (strcmp(operacion, "/") == 0)
    {
        ops->len -= 2;
        guardar_numero( (ops->array[ops->len+1] / ops->array[ops->len+2]), ops);
    }
    if (strcmp(operacion, "suma_total") == 0)
    {
        guardar_numero(suma_todos(ops), ops);
    }
    /*FinalDeRespuesta*/
    printf("Resultado %.2f\n", ultimo(ops));
}

/* Es OBLIGATORIO llamar a esta funcion para liberar el array dinamico */
void liberar(rpn_t *ops)
{
    if (ops->array)
        free(ops->array);
}

/* Lee datos desde el stream f, guarda los numeros y realiza las operaciones.
A) Declara e inicializa el struct con el array donde se van a guardar los numeros. Se pueden declarar
   otras variables (de hecho, alguna mas se necesita).
B) Usa, OBLIGATORIAMENTE, la funcion leer_numero_u_operacion para leer el numero u operacion desde el archivo.
C) Si se lee un numero se guarda en el array. Es obligatorio usar la funcion guardar_numero.
D) Si se lee una operacion se usa la funcion realizar_operacion para aplicarla. Es obligatorio usar la funcion
   realizar_operacion.
E) En cualquier otro caso la funcion debe terminar.
F) La funcion tambien debe terminar si no se puede inicializar el array dinamico o no se pueda guardar un numero
   porque no haya memoria suficiente.
G) Liberar la memoria dinamica usando la funcion liberar.
*/
void procesar_operaciones(FILE *f)
{
    double num = 0;
    char operacion[32];
    /*IniciarRespuesta*/
    rpn_t *ops;
    int num_o_op = -1;
    ops = (rpn_t *)malloc(sizeof(rpn_t));
    if (!ops)
    {
        printf("No se puede reservar memoria.");
        return;
    }
    inicializar(ops);
    while (num_o_op != 0)
    {

        num_o_op = leer_numero_u_operacion(f, &num, operacion);/* se salta la suma y se descuadra */

        if (num_o_op == 1)
        {
            guardar_numero(num, ops);
        }
        else if (num_o_op == 2)
            realizar_operacion(operacion, ops);
    }
    liberar(ops);
    free(ops);
    /*FinalDeRespuesta*/
}

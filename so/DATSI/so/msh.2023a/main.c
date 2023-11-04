/*-
 * main.c
 * Minishell C source
 * Shows how to use "obtain_order" input interface function.
 *
 * Copyright (c) 1993-2002-2019, Francisco Rosales <frosal@fi.upm.es>
 * Todos los derechos reservados.
 *
 * Publicado bajo Licencia de Proyecto Educativo Práctico
 * <http://laurel.datsi.fi.upm.es/~ssoo/LICENCIA/LPEP>
 *
 * Queda prohibida la difusión total o parcial por cualquier
 * medio del material entregado al alumno para la realización
 * de este proyecto o de cualquier material derivado de este,
 * incluyendo la solución particular que desarrolle el alumno.
 *
 * DO NOT MODIFY ANYTHING OVER THIS LINE
 * THIS FILE IS TO BE MODIFIED
 */

#include <stddef.h>    /* NULL */
#include <stdio.h>     /* printf, fprintf, stderr, perror */
#include <stdlib.h>    /* exit, malloc, free, realloc */
#include <sys/types.h> /* pid_t */
#include <sys/wait.h>  /* waitpid */
#include <unistd.h>    /* fork, execvp, chdir, getcwd, access */
#include <fcntl.h>     /* open, creat */
#include <signal.h>    /* signal */
#include <string.h>    /* strcmp, strlen, strcpy, strncpy, strcat, strncat, strchr, strstr */
#include <errno.h>     /* errno */
#include <sys/stat.h>  /* stat, umask */
#include <limits.h>    /* PATH_MAX */
#include <ctype.h>     /* isspace */
#include <pwd.h>       /* getpwnam */
#include <sys/times.h> /* times */
#include <glob.h>      /* glob, globfree */
#include <dirent.h>    /* opendir, readdir, closedir */

extern int obtain_order(); /* See parser.y for description */

/*
 * free_memory:
 * Libera la memoria de los arrays.
 * Ver get_memory.
 * @param pipes: array de fd de cada pipe
 * @param procesos: array del pid de procesos
 * @param argvc: numero de comandos
 * @return void
 */
void free_memory(pid_t **procesos, int ***pipes, char ***args, int argvc)
{
    if (argvc != -2) /*si ha sido necesario usar procesos*/
    {
        free(*procesos); /* liberamos la memoria del array de procesos*/
    }
    if (argvc > 1) /*si ha sido necesario usar pipes*/
    {
        for (int i = 0; i < argvc - 1; i++) /*liberamos la memoria de cada pipe*/
        {
            free((*pipes)[i]);
        }
        free(*pipes); /* liberamos la memoria del array de pipes*/
    }
    if (argvc == -2) /*si ha sido necesario usar args*/
    {
        for (int i = 0; (*args)[i] != NULL; i++) /* Recorrer el array */
        {  
            free((*args)[i]);                 /* Liberar cada elemento */
        }
        free(*args); /* liberamos la memoria del array de comandos*/
    }
}
/*
 *añadir:
 *Añade más espacio a un array si es necesario y añade un elemento.
 *Ver expandir_nombres_archivo
 *@param tamano_argumentos: puntero al tamaño del array
 *@param argumento: puntero al argumento a añadir
 *@param nuevos_argumentos: puntero al array de argumentos
 *@param num_nuevos_argumentos: puntero al número de argumentos en el array
 *@return void
 */
void añadir(size_t *tamano_argumentos, char *argumento, char ***nuevos_argumentos, int *num_nuevos_argumentos)
{
    if (*num_nuevos_argumentos == *tamano_argumentos) /* Si no hay espacio suficiente */
    {
        *tamano_argumentos = (*tamano_argumentos) * 2;                                         /* Duplicar el tamaño */
        *nuevos_argumentos = realloc(*nuevos_argumentos, *tamano_argumentos * sizeof(char *)); /* Redimensionar el array */
        if (*nuevos_argumentos == NULL)                                                        /* Si no se ha podido redimensionar */
        {
            fprintf(stderr, "Error: no se ha podido redimensionar el array de argumentos.\n"); /* Mostrar error */
            exit(EXIT_FAILURE);                                                                /* Salir con error */
        }
    }
    (*nuevos_argumentos)[(*num_nuevos_argumentos)++] = strdup(argumento); /* Añadir el argumento al array */
    if ((*nuevos_argumentos)[(*num_nuevos_argumentos) - 1] == NULL)       /* Si no se ha podido copiar */
    {
        fprintf(stderr, "Error: no se ha podido copiar el argumento.\n"); /* Mostrar error */
        exit(EXIT_FAILURE);                                               /* Salir con error */
    }
}
/*
 *expandir_nombres_archivo:
 *Expande los nombres de archivo que contengan interrogaciones, si no hay interrogaciones o hay / no se expande.
 *Ver execute
 *@param argumentos: array de argumentos
 *@return void
 */
void expandir_nombres_archivo(char **argumentos[])
{
    char **nuevos_argumentos = (char **)malloc(sizeof(char *));  /* Reservar espacio para el primer nuevo argumento */
    int num_nuevos_argumentos = 0;                               /* Número de nuevos argumentos */
    size_t tamano_argumentos = 1;                                /* Tamaño de nuevos_argumentos */
    char *argumento;                                             /* Argumento a expandir */
    for (int i = 0; (argumento = (*argumentos)[i]) != NULL; i++) /* Recorrer los argumentos */
    {
        char *p;                                                                    /* Puntero a la interrogación */
        if ((p = strchr(argumento, '?')) != NULL && strchr(argumento, '/') == NULL) /* Si hay interrogación y no hay / */
        {
            glob_t results;                               /* Resultados de la expansión */
            int ret = glob(argumento, 0, NULL, &results); /* Expandir el argumento */
            if (ret != 0)                                 /* Si hay error */
            {
                añadir(&tamano_argumentos, argumento, &nuevos_argumentos, &num_nuevos_argumentos); /* Añadir el argumento al array */
            }
            else /* Si no hay error */
            {
                for (int j = 0; j < results.gl_pathc; j++) /* Recorrer los resultados */
                {
                    añadir(&tamano_argumentos, results.gl_pathv[j], &nuevos_argumentos, &num_nuevos_argumentos); /* Añadir el resultado al array */
                }
            }
            globfree(&results); /* Liberar los resultados */
        }
        else /* Si no hay interrogación o hay / */
        {
            añadir(&tamano_argumentos, argumento, &nuevos_argumentos, &num_nuevos_argumentos); /* Añadir el argumento al array */
        }
    }
    nuevos_argumentos = realloc(nuevos_argumentos, (num_nuevos_argumentos + 1) * sizeof(char *)); /* Liberar espacio innecesario */
    if (nuevos_argumentos == NULL)                                                                /* Si no se ha podido liberar espacio */
    {
        fprintf(stderr, "Error: no se ha podido liberar espacio innecesario.\n"); /* Mostrar error */
        exit(EXIT_FAILURE);                                                       /* Salir con error */
    }
    nuevos_argumentos[num_nuevos_argumentos] = NULL;   /* Añadir el NULL al final del array */
    for (int i = 0; nuevos_argumentos[i] != NULL; i++) /* Recorrer el array */
    {
        (*argumentos)[i] = strdup(nuevos_argumentos[i]); /* Copiar el argumento al array */
        if ((*argumentos)[i] == NULL)                    /* Si no se ha podido reservar espacio */
        {
            fprintf(stderr, "Error: no se ha podido reservar espacio para el argumento.\n"); /* Mostrar error */
            exit(EXIT_FAILURE);                                                              /* Salir con error */
        }
        *argumentos = realloc(*argumentos, (num_nuevos_argumentos + 1) * sizeof(char *)); /* Liberar espacio innecesario */
        (*argumentos)[num_nuevos_argumentos] = NULL;                                      /* Añadir el NULL al final del array */
    }
    free_memory(NULL, NULL, &nuevos_argumentos, -2); /* Liberar memoria */
}
/*
 * concat:
 * Concatena la cadena de entrada con la cadena de entrada 2
 * Ver replace_metachars
 * @param str: puntero a cadena de entrada
 * @param str2: cadena de entrada 2
 * @return void
 */
void concat(char **str, char *str2)
{
    *str = realloc(*str, strlen(*str) + strlen(str2) + 1); /* Reservar espacio para la cadena de entrada 2 */
    if (*str == NULL)                                      /* Si no se ha podido reservar espacio */
    {
        fprintf(stderr, "Error: realloc\n");
        exit(1);
    }
    strcpy(*str + strlen(*str), str2); /* Concatenar la cadena de entrada 2 */
    (*str)[strlen(*str)] = '\0';       /* Añadir el NULL al final de la cadena */
}
/*
 * concat_ini:
 * Concatena la cadena de entrada con el directorio actual
 * ver replace_metachars
 * @param str: cadena de entrada
 * @param dir: directorio actual
 * @return void
 */
void concat_ini(char **str, char *dir)
{
    *str = realloc(*str, strlen(dir) + 1); /* Reservar espacio para el directorio actual */
    if (*str == NULL)                      /* Si no se ha podido reservar espacio */
    {
        fprintf(stderr, "Error: realloc\n");
        exit(1);
    }
    strcpy(*str, dir);           /* Concatenar el directorio actual */
    (*str)[strlen(*str)] = '\0'; /* Añadir el NULL al final de la cadena */
}
/*
 * replace_metachars:
 * Reemplaza los metacaracteres de la cadena de entrada
 * ver execute
 * @param input: cadena de entrada
 * @return void
 */
void replace_metachars(char **input)
{
    char *principio = (char *)malloc((strlen(*input) + 1) * sizeof(char)); /* Puntero al principio de la cadena */
    char *final = (char *)malloc((strlen(*input) + 1) * sizeof(char));     /* Puntero al final de la cadena */
    char *meta = (char *)malloc((strlen(*input) + 1) * sizeof(char));      /* Puntero a la variable */
    char *in = (char *)malloc((strlen(*input) + 1) * sizeof(char));        /* Puntero a la cadena de entrada */
    char *home = getenv("HOME");                                           /* Puntero al directorio home */
    char *env = NULL;                                                      /* Puntero a la variable de entorno */
    struct passwd *pwd = NULL;                                             /* Puntero a la estructura passwd */

    if (principio == NULL || final == NULL || meta == NULL || in == NULL || home == NULL) /* Si no se ha podido reservar espacio */
    {
        fprintf(stderr, "Error: malloc or getenv\n");
        exit(1);
    }

    strcpy(in, *input);    /* Copiar la cadena de entrada */
    in[strlen(in)] = '\0'; /* Añadir el NULL al final de la cadena */

    if (in[0] == '~') /* Si la cadena de entrada empieza por ~ */
    {
        int n = sscanf(in, "~%[^/]/%s", meta, final); /* Separar la cadena de entrada */
        switch (n)                                    /* Según el número de variables */
        {
        case -1:                   /* Si no hay / */
            concat_ini(&in, home); /* Concatenar el directorio home */
            break;
        case 0:                            /* Si no hay variables */
            n = sscanf(in, "~/%s", final); /* Separar la cadena de entrada */
            concat_ini(&in, home);         /* Concatenar el directorio home */
            concat(&in, "/");              /* Concatenar el / */
            if (n > 0)                     /* Si hay cadena de entrada */
            {
                concat(&in, final); /* Concatenar la cadena de entrada */
            }
            break;
        default:                  /* Si hay variables */
            pwd = getpwnam(meta); /* Obtiene la información del usuario */
            if (pwd != NULL)      /* Si el usuario existe */
            {
                concat_ini(&in, pwd->pw_dir); /* Concatenar el directorio del usuario */
                if (n > 1)                    /* Si hay cadena de entrada */
                {
                    concat(&in, "/");   /* Concatenar el / */
                    concat(&in, final); /* Concatenar la cadena de entrada */
                }
            }
            break;
        }
    }
    else if (strstr(in, "$") != NULL) /* Si la cadena de entrada contiene $ */
    {
        int n = sscanf(in, "%[^$]$%[^/]/%s", principio, meta, final); /* Separar la cadena de entrada */
        switch (n)                                                    /* Según el número de variables */
        {
        case 0:                                       /* Si no hay variables */
            n = sscanf(in, "$%[^/]/%s", meta, final); /* Separar la cadena de entrada */
            if (n == 1)                               /* Si hay una variable */
            {
                env = getenv(meta); /* Obtiene la variable de entorno */
                if (env != NULL)    /* Si la variable existe */
                {
                    concat_ini(&in, env); /* Concatenar la variable */
                }
                if (strstr(*input, "/") != NULL) /* Si hay / */
                {
                    concat(&in, "/"); /* Concatenar el / */
                }
            }
            else if (n == 2) /* Si hay dos variables */
            {
                env = getenv(meta); /* Obtiene la variable de entorno */
                if (env != NULL)    /* Si la variable existe */
                {
                    concat_ini(&in, env); /* Concatenar la variable */
                    concat(&in, "/");     /* Concatenar el / */
                    concat(&in, final);   /* Concatenar la cadena de entrada */
                }
            }
            break;
        case 2:                 /* Si hay una variable */
            env = getenv(meta); /* Obtiene la variable de entorno */
            if (env != NULL)    /* Si la variable existe */
            {
                concat_ini(&in, principio); /* Concatenar la variable */
                concat(&in, env);           /* Concatenar la variable */
            }
            break;
        case 3:
            env = getenv(meta); /* Obtiene la variable de entorno */
            if (env != NULL)    /* Si la variable existe */
            {
                concat_ini(&in, principio); /* Concatenar la variable */
                concat(&in, env);           /* Concatenar la variable */
                concat(&in, "/");           /* Concatenar el / */
                concat(&in, final);         /* Concatenar la cadena de entrada */
            }
            break;
        default:
            break;
        }
    }
    *input = realloc(*input, strlen(in) + 1); /* Reasignar memoria */
    strcpy(*input, in);                       /* Copiar la cadena de entrada */
    (*input)[strlen(in)] = '\0';              /* Añadir el NULL al final de la cadena */
    free(in);                                 /* Liberar memoria */
    free(meta);                               /* Liberar memoria */
    free(final);                              /* Liberar memoria */
    free(principio);                          /* Liberar memoria */
}
/*
 * read_internal:
 * Implementa el comando interno read.
 * Ver execute_internal_command
 * @param argv: array con el comando y sus argumentos
 * @return int: 1 si es exit succes, 0 si no
 */
int read_internal(char **argv)
{
    int count = 0;                  /* Contador de argumentos */
    while (argv[count + 1] != NULL) /* Cuenta el número de argumentos */
    {
        count++; /* Incrementa el contador */
    }

    char **variables = &argv[1]; /* Obtener los nombres de las variables de entorno */

    char input[1024]; /* Buffer para la entrada de datos */
    char *token;      /* Puntero a la cadena de entrada */

    fgets(input, sizeof(input), stdin); /* Leer una línea de entrada desde la entrada estándar */

    if (count < 1) /* Verificar si se proporcionó al menos una variable de entorno */
    {
        perror("read: missing variable name"); /* Imprime un mensaje de error */
        return EXIT_FAILURE;                   /* Si no se proporcionó ninguna variable de entorno, termina la ejecución del comando */
    }

    if (count == 1) /* Si solo se proporcionó una variable de entorno, asigna la línea de entrada completa a la variable de entorno */
    {
        token = strtok(input, ""); /* Dividir la línea en palabras utilizando espacios y tabuladores como separadores */
    }
    else
    {
        token = strtok(input, " \t"); /* Dividir la línea en palabras utilizando espacios y tabuladores como separadores */
    }
    for (int i = 0; i < count; i++) /* Asignar la primera palabra a la primera variable, la segunda a la segunda variable, y así sucesivamente */
    {
        if (token != NULL) /* Verifica si la palabra no es nula */
        {
            while (isspace(*token)) /* Limpia el token antes de asignarlo a la variable de entorno */
            {
                token++; /* Avanza al siguiente carácter */
            }
            size_t len = strlen(token);     /* Obtiene la longitud del token */
            while (isspace(token[len - 1])) /* Limpia el token antes de asignarlo a la variable de entorno */
            {
                len--; /* Decrementa la longitud del token */
            }
            token[len] = '\0';              /* Agrega el carácter nulo al final del token */
            setenv(variables[i], token, 1); /* Asigna el valor del token a la variable de entorno */
            if (i < count - 2)              /* Si no es la última variable, obtiene la siguiente palabra */
            {
                token = strtok(NULL, " \t"); /* Obtiene la siguiente palabra */
            }
            else /*obtiene el resto de la entrada*/
            {
                token = strtok(NULL, ""); /* Obtiene la siguiente palabra */
            }
        }
        else
        {
            break; /* Si no hay más palabras, termina el ciclo */
        }
    }
    return EXIT_SUCCESS; /* Termina la ejecución del comando */
}
/*
 * time_internal:
 * Implementa el comando interno time.
 * Ver execute_internal_command
 * @param argv: array con el comando y sus argumentos
 * @return int: 1 si es exit succes, 0 si no
 */
int time_internal(char **args)
{
    struct tms tms_start, tms_end;  /* Estructura para almacenar los tiempos de usuario y sistema */
    clock_t clock_start, clock_end; /* Estructura para almacenar los tiempos reales */

    if (args[1] == NULL) /* Verifica si se proporcionó un comando */
    {
        clock_start = times(&tms_start); /* Obtiene los tiempos de usuario y sistema */
        clock_end = times(&tms_end);     /* Obtiene los tiempos de usuario y sistema */
    }
    else /* Si se proporcionó un comando, ejecuta el comando */
    {
        clock_start = times(&tms_start); /* Obtiene los tiempos de usuario y sistema */
        if (fork() == 0)                 /* Crea un proceso hijo */
        {
            execvp(args[1], &args[1]); /* Ejecuta el comando */
            perror(args[1]);           /* Imprime el error si el comando no se ejecuta */
            exit(1);                   /* Termina la ejecución del proceso hijo */
        }
        wait(NULL);                  /* Espera a que el proceso hijo termine */
        clock_end = times(&tms_end); /* Obtiene los tiempos de usuario y sistema */
    }
    double real_time = (double)(clock_end - clock_start) / sysconf(_SC_CLK_TCK);                 /* Calcula el tiempo real */
    double user_time = (double)(tms_end.tms_utime - tms_start.tms_utime) / sysconf(_SC_CLK_TCK); /* Calcula el tiempo de usuario */
    double sys_time = (double)(tms_end.tms_stime - tms_start.tms_stime) / sysconf(_SC_CLK_TCK);  /* Calcula el tiempo de sistema */
    printf("%d.%03du %d.%03ds %d.%03dr\n",
           (int)user_time, (int)(user_time * 1000) % 1000,
           (int)sys_time, (int)(sys_time * 1000) % 1000,
           (int)real_time, (int)(real_time * 1000) % 1000); /* Imprime los tiempos */
    return 0;
}
/*
 * umask_internal:
 * Implementa el comando interno umask.
 * Ver execute_internal_command
 * @param argv: array con el comando y sus argumentos
 * @return int: 1 si es exit succes, 0 si no
 */
int umask_internal(char **argv)
{
    mode_t old_mask, new_mask; /* Máscaras de permisos */
    char *endptr;              /* Puntero para la función strtol */
    if (argv[1] == NULL)       /*si no hay argumentos*/
    {
        new_mask = 0; /*establecemos la máscara de creación de archivos a 0 (lo que significa que se permiten todos los permisos)*/
    }
    else if (argv[2] != NULL) /*si hay mas de un argumento*/
    {
        fprintf(stderr, "umask: too much arguments\n"); /*imprimimos un error*/
        return EXIT_FAILURE;                            /*salimos de la funcion*/
    }
    else /*si hay un argumento*/
    {
        long int value = strtol(argv[1], &endptr, 8); /*convertimos el argumento a un entero en octal*/
        if (*endptr != '\0')                          /*si el argumento no es un numero en octal*/
        {
            fprintf(stderr, "umask: invalid argument '%s'\n", argv[1]); /*imprimimos un error*/
            return EXIT_FAILURE;                                        /*salimos de la funcion*/
        }
        new_mask = (mode_t)value; /*guardamos el valor en la variable new_mask*/
    }

    /* Utilizamos la llamada al sistema `umask` para obtener la máscara anterior y la guardamos en `old_mask`.
    La máscara actual se establece como `new_mask`. */
    old_mask = umask(new_mask);

    /* Mostramos por la salida estándar el valor en octal de la máscara anterior. */
    printf("%o\n", old_mask);

    /* Devolvemos 0 para indicar que todo ha ido bien. */
    return EXIT_SUCCESS;
}
/*
 * cd_internal:
 * Implementa el comando interno cd.
 * Ver execute_internal_command
 * @param argv: array con el comando y sus argumentos
 * @return int: 1 si es exit succes, 0 si no
 */
int cd_internal(char **argv)
{
    char *path = argv[1]; /*variable para guardar el path*/
    if (path == NULL)     /*si no hay argumentos*/
    {
        path = getenv("HOME"); /*guardamos el path del home*/
    }
    else if (argv[2] != NULL) /*si hay mas de un argumento*/
    {
        fprintf(stderr, "cd: too much arguments\n"); /*imprimimos un error*/
        return EXIT_FAILURE;                         /*salimos de la funcion*/
    }
    if (chdir(path) == -1) /*si no se ha podido cambiar de directorio*/
    {
        fprintf(stderr, "cd: %s: No such file or directory\n", path); /*imprimimos un error*/
        return EXIT_FAILURE;                                          /*salimos de la funcion*/
    }
    char cwd[1024];                       /*variable para guardar el directorio actual*/
    if (getcwd(cwd, sizeof(cwd)) == NULL) /*si no se ha podido obtener el directorio actual*/
    {
        perror("getcwd() error"); /*imprimimos un error*/
        return EXIT_FAILURE;      /*salimos de la funcion*/
    }
    printf("%s\n", cwd); /*imprimimos el directorio actual*/
    return EXIT_SUCCESS; /*devolvemos exit succes*/
}
/*
 * execute_internal_command:
 * Ejecuta el comando interno en cuestion.
 * Ver internal_command.
 * @param argv: array con el comando y sus argumentos
 * @return int: 1 si es exit succes, 0 si no
 */
int execute_internal_command(char **argv)
{
    int res = 0;
    if (strcmp(argv[0], "cd") == 0) /*si es cd*/
    {
        res = cd_internal(argv); /*llamamos a la funcion cd*/
    }
    else if (strcmp(argv[0], "umask") == 0) /*si es umask*/
    {
        res = umask_internal(argv); /*llamamos a la funcion umask*/
    }
    else if (strcmp(argv[0], "time") == 0) /*si es time*/
    {
        res = time_internal(argv); /*llamamos a la funcion time*/
    }
    else if (strcmp(argv[0], "read") == 0) /*si es read*/
    {
        res = read_internal(argv); /*llamamos a la funcion read*/
    }
    return res; /*devolvemos el resultado de la funcion*/
}
/*
 * is_internal_command:
 * Comprueba si el comando es interno o no.
 * Ver internal_commands, tipo_comando
 * @param argv: array con el comando y sus argumentos
 * @return int: 1 si es interno, 0 si no lo es
 */
int is_internal_command(char **argv)
{
    int is_internal = 0;            /*variable para saber si es un comando interno*/
    if (strcmp(argv[0], "cd") == 0) /*si es cd*/
    {
        is_internal = 1; /*es un comando interno*/
    }
    else if (strcmp(argv[0], "umask") == 0) /*si es umask*/
    {
        is_internal = 1; /*es un comando interno*/
    }
    else if (strcmp(argv[0], "time") == 0) /*si es time*/
    {
        is_internal = 1; /*es un comando interno*/
    }
    else if (strcmp(argv[0], "read") == 0) /*si es read*/
    {
        is_internal = 1; /*es un comando interno*/
    }
    return is_internal; /*devolvemos si es un comando interno*/
}
/*
 * internal_command:
 * Comprueba si el comando es interno y lo ejecuta si es el caso.
 * Ver is_internal_command, execute, tipo_comando, execute_internal_command
 * @param argv: array con el comando y sus argumentos
 * @param bg: 1 si es background, 0 si no
 * @param n_commands: numero de comandos
 * @param command: numero de comando
 * @param status_str: string con el estado del comando
 * @return int 1 si es exit succes, 0 si no
 */
int internal_command(char **argv, int bg, int n_commands, int command, char *status_str)
{
    int is_internal = is_internal_command(argv); /*variable guardar si es un comando interno*/
    if (is_internal)                             /*si es un comando interno*/
    {
        int exit_status = execute_internal_command(argv); /*llamamos a la funcion execute_internal_command y guardamos su exit status*/

        /*no debe hacer exit si es el unico mandato y es foreground, o si forma parte de una cadena de mandatos y es el ultimo*/
        if (bg && n_commands == 1) /*debe hacer exit si es el unico mandato y es background*/
        {
            if (exit_status == 0) /*si el exit status es 0*/
            {
                exit(EXIT_SUCCESS); /*salimos con exit succes*/
            }
            else /*si no*/
            {
                exit(EXIT_FAILURE); /*salimos con exit failure*/
            }
        }
        else if (n_commands > 1 && command != (n_commands - 1)) /*si forma parte de una cadena de mandatos y no es el ultimo*/
        {
            if (exit_status == 0) /*si el exit status es 0*/
            {
                exit(EXIT_SUCCESS); /*salimos con exit succes*/
            }
            else /*si no*/
            {
                exit(EXIT_FAILURE); /*salimos con exit failure*/
            }
        }
        sprintf(status_str, "status=%d", exit_status); /*guardamos el estado del proceso hijo*/
        putenv(status_str);
    }
    return is_internal; /*devolvemos si es un comando interno*/
}
/*
 * ignore_signals:
 * Ignorar las señales de control de terminal (SIGINT y SIGQUIT).
 * Ver execute_commands
 * @param bg: > 0 si es background o el padre, 0 si es foreground
 * @return void
 */
void ignore_signals(int bg)
{
    if (bg) /*si es background o el proceso padre ignormaos*/
    {
        signal(SIGINT, SIG_IGN);  /*ignoramos la señal SIGINT*/
        signal(SIGQUIT, SIG_IGN); /*ignoramos la señal SIGQUIT*/
    }
    else
    {
        signal(SIGINT, SIG_DFL);  /*restauramos la señal SIGINT*/
        signal(SIGQUIT, SIG_DFL); /*restauramos la señal SIGQUIT*/
    }
}
/*
 * execute:
 * Ejecuta un comando, ya sea interno o no, tambien expande sus argumentos y reemplaza los metacaracteres si es necesario.
 * Ver connect_and_execute, execute_commands, internal_command, replace_metachars, expandir_nombres_archivo
 * @param argv: array dcon el comando y sus argumentos
 * @return void
 */
void execute(char **argv, int n_commands, int bg, int command, char *status_str)
{
    /*reemplazamos los metacaracteres*/
    for (int i = 0; argv[i] != NULL; i++) /*recorremos el array buscando argumentos que empiecen por ~ o $*/
    {
        replace_metachars(&argv[i]); /*reemplazamos los metacaracteres*/
    }
    /*expandimos los nombres de los archivos*/
    expandir_nombres_archivo(&argv);
    /*ejecutamos el comando*/
    if (internal_command(argv, bg, n_commands, command, status_str) == 0) /*si no es un comando interno*/
    {
        execvp(argv[0], argv); /*ejecutamos el comando*/
        perror(argv[0]);       /*si no se ha ejecutado correctamente, se imprime un error*/
        exit(EXIT_FAILURE);    /*salimos del proceso hijo*/
    }
}
/*
 * fg_bg:
 * Comprueba si el proceso es foreground o background y lo espera o no en cada caso.
 * Actualiza las variables de entorno bgpid y status.
 * Ver execute_commands.
 * @param pid_son: pid del proceso hijo
 * @param bg: > 0 si es background, 0 si es foreground
 * @param status_str: string con el estado del proceso hijo
 * @param bgpid_str: string con el pid del proceso hijo
 * @return void
 */
void fg_bg(pid_t pid_son, int bg, char *status_str, char *bgpid_str)
{
    int status; /*estado del proceso hijo*/
    if (bg)     /*si es un proceso en background, guardamos su pid*/
    {
        sprintf(bgpid_str, "bgpid=%d", pid_son); /*guardamos el pid del proceso hijo*/
        putenv(bgpid_str);                       /*guardamos el pid del proceso hijo en la variable de entorno bgpid*/

        printf("[%d]\n", pid_son); /*imprimimos el pid del proceso hijo*/

        waitpid(-1, &status, WNOHANG); /*esperamos a que termine el proceso hijo para no dejarlo zombie*/
    }
    else /*si es un proceso en foreground, esperamos a que termine*/
    {
        waitpid(pid_son, &status, 0); /* esperamos a que termine el proceso hijo*/

        sprintf(status_str, "status=%d", status); /*guardamos el estado del proceso hijo*/
        putenv(status_str);
    }
}
/*
 * get_memory:
 * Reserva la memoria de los arrays.
 * Ver free_memory.
 * @param pipes: array de fd de cada pipe
 * @param procesos: array del pid de procesos
 * @param argvc: numero de comandos
 * @return void
 */
void get_memory(pid_t **procesos, int ***pipes, int argvc)
{
    *procesos = (pid_t *)malloc(sizeof(pid_t) * argvc); /*reservamos memoria para el array de procesos*/
    if (argvc > 1)                                      /*si hay mas de un comando*/
    {
        *pipes = (int **)malloc(sizeof(int *) * argvc - 1); /*reservamos memoria para el array de pipes*/
        for (int i = 0; i < argvc - 1; i++)                 /*reservamos memoria para cada pipe*/
        {
            (*pipes)[i] = (int *)malloc(sizeof(int) * 2);
        }
    }
}
/*
 * make_pipe:
 * Crea un pipe
 * Ver execute_commands
 * @param pipes: array de fd de cada pipe
 * @param npipe: pipe actual
 * @return void
 */
void make_pipe(int **pipes, int npipe)
{
    if (pipe(pipes[npipe]) == -1) /*creamos el pipe y vemos si da error*/
    {
        perror("Error en la creacion de los pipes");
        exit(EXIT_FAILURE);
    }
}
/*
 * connect_and_execute:
 * Conecta los procesos con los pipes y ejecuta el comando que toque.
 * Ver execute_command, execute
 * @param commands: array de comandos
 * @param command: comando actual
 * @param pipes: array de fd de cada pipe
 * @param npipe: pipe actual
 * @param n_commands: numero de comandos
 * @param bg: > 0 si es background, 0 si es foreground
 * @param tipo: tipo de hijo en el que nos encontramos, 0: extremo entrada, 1: extremo salida, 2: intermedio
 * @param status_str: string con el estado del proceso hijo
 * @return void
 */
void connect_and_execute(char ***commands, int command, int **pipes, int npipe, int n_commands, int bg, int tipo, char *status_str)
{
    switch (tipo) /*según el tipo de conexión*/
    {
    case 0:                                                              /*extremo entrada*/
        close(pipes[npipe][0]);                                          /*cerramos el extremo de lectura*/
        dup2(pipes[npipe][1], STDOUT_FILENO);                            /*redirigimos la salida estandar al pipe*/
        close(pipes[npipe][1]);                                          /*cerramos el extremo de escritura*/
        execute(commands[command], n_commands, bg, command, status_str); /*ejecutamos el comando*/
        break;
    case 1:                                                                      /*extremo salida*/
        close(pipes[npipe][1]);                                                  /*cerramos el extremo de escritura*/
        dup2(pipes[npipe][0], STDIN_FILENO);                                     /*redirigimos la entrada estandar al pipe*/
        close(pipes[npipe][0]);                                                  /*cerramos el extremo de lectura*/
        execute(commands[command + 1], n_commands, bg, command + 1, status_str); /*ejecutamos el comando*/
        break;
    case 2: /*pipe intermedio*/
        /*pipe siguiente*/
        close(pipes[npipe + 1][0]);               /*cerramos el extremo de lectura*/
        dup2(pipes[npipe + 1][1], STDOUT_FILENO); /*redirigimos la salida estandar al pipe*/
        close(pipes[npipe + 1][1]);               /*cerramos el extremo de escritura*/
        /*pipe actual*/
        close(pipes[npipe][1]);                                                  /*cerramos el extremo de escritura*/
        dup2(pipes[npipe][0], STDIN_FILENO);                                     /*redirigimos la entrada estandar al pipe*/
        close(pipes[npipe][0]);                                                  /*cerramos el extremo de lectura*/
        execute(commands[command + 1], n_commands, bg, command + 1, status_str); /*ejecutamos el comando*/
        break;
    }
}
/*
 * execute_commands:
 * Crea una estrutuctura recursiva de procesos y pipes para ejecutar una cadena de mandatos.
 * El primer hijo ejecuta el ultimo mandato y el ultimo hijo ejecuta el primer mandato. Si el ultimo mandato es interno se ejecuta en el padre.
 * Ver connect_and_execute, make_pipe, execute, fg_bg, ignore_signal
 * @param procesos: array del pid de procesos
 * @param commands: array de comandos
 * @param pipes: array de fd de cada pipe
 * @param n_commands: numero de comandos
 * @param command: comando actual
 * @param npipe: pipe actual
 * @param bg: si esta en bg
 * @param internal: si es interno
 * @param ultimo: si es el ultimo mandato
 * @param status_str: string del status
 * @param bgpid_str: string del bgpid
 * @return void
 */
void execute_commands(pid_t *procesos, char ***commands, int **pipes, int n_commands, int command, int npipe, int bg, int internal, int ultimo, char *status_str, char *bgpid_str)
{
    if (n_commands == 0) /*si no hay mandatos salimos*/
    {
        return;
    }
    if (internal && ultimo) /*si es interno y esta en fg y es el ultimo mandato*/
    {
        n_commands--;              /*no se cuenta como mandato*/
        command--;                 /*no se cuenta como mandato*/
        make_pipe(pipes, npipe--); /*creamos el pipe que conecta con el padre*/
    }
    if ((!ultimo && internal) || (command != (n_commands - 1) && !internal)) /*si no es el ultimo mandato o el unico o es interno y esta en fg*/
    {
        make_pipe(pipes, npipe); /*creamos el pipe*/
    }
    switch (procesos[command] = fork()) /*creamos el proceso hijo*/
    {
    case -1: /*error al crear el proceso hijo*/
        perror("Error en la creacion de procesos");
        exit(EXIT_FAILURE);
        break;
    case 0:                              /*este es el proceso hijo*/
        if (command == (n_commands - 1)) /*ultimo o unico comando*/
        {
            ignore_signals(bg);  /*ignoramos las señales*/
            if (n_commands == 1) /*si solo hay un mandato*/
            {
                if (internal) /*si hay un comando interno*/
                {
                    connect_and_execute(commands, command, pipes, npipe + 1, n_commands, bg, 0, status_str); /*conectamos el comando con la salida y lo ejecutamos*/
                }
                else /*si no hay un comando interno*/
                {
                    execute(commands[command], n_commands, bg, command, status_str); /*ejecutamos el comando*/
                }
            }
            else /*ultimo mandato*/
            {
                execute_commands(procesos, commands, pipes, n_commands, command - 1, npipe, bg, internal, 0, status_str, bgpid_str); /*ejecutamos el comando anterior*/
            }
        }
        else if (command == 0) /*primer comando, se termina el proceso de recusividad y se empiezan a ejecutar todos los mandatos*/
        {
            connect_and_execute(commands, command, pipes, npipe, n_commands, bg, 0, status_str); /*conectamos el primer comando con el pipe y lo ejecutamos*/
        }
        else /*commando intermedio*/
        {
            /*continuamos creando el resto de procesos hasta llegar al primer proceso y se empiecen a ejecutar los comandos del 0 al n*/
            execute_commands(procesos, commands, pipes, n_commands, command - 1, npipe - 1, bg, internal, 0, status_str, bgpid_str);
        }
        break;
    default:                               /*este es el proceso padre*/
        if ((command == (n_commands - 1))) /*este es el proceso padre del ultimo comando(es el msh)*/
        {
            if (internal) /*si es un mandato interno el ultimo, cerramos la conexion con el pipe*/
            {
                connect_and_execute(commands, command, pipes, npipe + 1, n_commands + 1, bg, 1, status_str); /*conectamos la salida del ultimo comando con la entrada estandar*/
            }
            else
            {
                fg_bg(procesos[n_commands - 1], bg, status_str, bgpid_str); /*esperamos el comando en foreground o background*/
            }
        }
        else if (command == (n_commands - 2) && !internal) /*si es el penultimo comando*/
        {
            connect_and_execute(commands, command, pipes, npipe, n_commands, bg, 1, status_str); /*conectamos y cerramos el primer fd y ejecuatmos el primer comando*/
        }
        else /*si hay mas de dos comandos*/
        {
            connect_and_execute(commands, command, pipes, npipe, n_commands, bg, 2, status_str); /*conectamos el pipe 1 y 2 y ejecutamos el siguiente comando*/
        }
        break;
    }
}
/*
 * reset_std:
 * Restaura los descriptores de entrada y salida estandar y salida de error
 * @param fd_in: fd de la entrada
 * @param fd_out: fd de la salida
 * @param fd_err: fd de la salida de error
 * @return void
 */
void reset_std(int fd_in, int fd_out, int fd_err, char *filev[3], int internal)
{
    if (filev[0] || internal)
    {
        if (dup2(fd_in, STDIN_FILENO) == -1) /*restauramos la entrada*/
        {
            perror("Error al restaurar la entrada!");
            close(fd_in);
        }
    }
    if (filev[1])
    {
        if (dup2(fd_out, STDOUT_FILENO) == -1) /*restauramos la salida*/
        {
            perror("Error al restaurar la salida\n");
            close(fd_out);
        }
    }
    if (filev[2])
    {
        if (dup2(fd_err, STDERR_FILENO) == -1) /*restauramos la salida de error*/
        {
            perror("Error al restaurar la salida de error\n");
            close(fd_err);
        }
    }
}
/*
 * redirect_to_file:
 * Redirige fd_std a fd_dest
 * @param fd_dest: fd del archivo de destino
 * @param fd_std: fd del descriptor standard
 * @return int: EXIT_SUCCESS si se ha redirigido correctamente, EXIT_FAILURE si no
 */
int redirect_to_file(int fd_dest, int fd_std)
{
    if (fd_dest == -1) /*error al abrir el archivo de destino*/
    {
        perror("Error al crear o abrir el archivo de destino/entrada");
        close(fd_dest); /*cerramos el archivo de destino*/
        return EXIT_FAILURE;
    }
    if (dup2(fd_dest, fd_std) == -1) /*redirigir la salida*/
    {
        perror("Error al redirigir la salida\n");
        close(fd_dest); /*cerramos el archivo de destino*/
        return EXIT_FAILURE;
    }
    close(fd_dest); /*cerramos el archivo de destino*/
    return EXIT_SUCCESS;
}
/*
 * redirect_std:
 * Redirige el descriptor estandar correcpondiente a un archivo dado en filev[i]
 * @param filev: vector con el nombre de archivos a redirigir
 * @return int: 0 si no hay error, 1 si no hay
 */
int redirect_std(char *filev[3])
{
    int res = 0;
    if (filev[0]) /*redirigimos la entrada si hay que hacerlo*/
    {
        res = redirect_to_file(open(filev[0], O_RDONLY), STDIN_FILENO);
    }
    if (res != 1 && filev[2]) /*redirigimos la salida de error si hay que hacerlo*/
    {
        res = redirect_to_file(open(filev[2], O_WRONLY | O_CREAT | O_TRUNC, 0666), STDERR_FILENO);
    }
    if (res != 1 && filev[1]) /*redirigimos la salida si hay que hacerlo*/
    {
        res = redirect_to_file(open(filev[1], O_WRONLY | O_CREAT | O_TRUNC, 0666), STDOUT_FILENO);
    }
    return res;
}
/*
 * tipo_comando:
 * Comprueba si el comando es interno o no y si lo es, lo ejecuta, tambien en caso de que sea un cadena de comandos,
 * mira si el ultimo es un comando interno y actualiza los flags de ultimo e internal,
 * si es un proceso en background, aumenta el contador de procesos en background
 * @param argvv: vector de comandos
 * @param bg_procs: numero de procesos en background
 * @param bg: flag de background
 * @param internal: flag de comando interno
 * @param ultimo: flag de ultimo comando interno
 * @param argvc: numero de comandos
 * @param status_str: string con el estado del ultimo comando
 * @return void
 */
void tipo_comando(char ***argvv, int *bg_procs, int bg, int *internal, int *ultimo, int *argvc, char *status_str)
{
    if (*argvc == 0) /*si no hay comandos, no hacemos nada*/
    {
        return;
    }
    if (bg) /*si es un proceso en background*/
    {
        (*bg_procs)++; /*aumentamos el contador de procesos en background*/
    }
    else if (*argvc == 1) /*si solo hay un comando, comprobamos si es un comando interno*/
    {
        if (is_internal_command(argvv[0])) /*si es un comando interno lo ejecutamos*/
        {
            execute(argvv[0], 1, bg, 0, status_str); /*ejecutamos el comando interno*/
            (*argvc)--;                              /*quitamos el comando interno del vector de comandos*/
        }
    }
    else if (*argvc > 1 && is_internal_command(argvv[*argvc - 1]) && bg == 0) /*si el ultimo es un comando interno y es fg*/
    {
        *internal = 1; /*ponemos el flag a 1*/
        *ultimo = 1;   /*ponemos el flag a 1*/
    }
}

int main()
{
    char ***argvv = NULL;                /* Vector de comandos */
    int argvc;                           /* Numero de comandos */
    char *filev[3] = {NULL, NULL, NULL}; /* Vector de archivos */
    int bg;                              /* Background flag */
    int bg_procs = 0;                    /* Background processes */
    pid_t pid;                           /* Process ID */
    int ret;                             /* Return value */
    pid_t *procesos = NULL;              /*vector de procesos*/
    int **pipes = NULL;                  /*vector de pipes*/
    int internal = 0;                    /*flag para saber si es un comando interno*/
    int ultimo = 0;                      /*flag para saber si es el ultimo comando*/
    int fd_in = dup(STDIN_FILENO);       /* Save stdin */
    int fd_out = dup(STDOUT_FILENO);     /* Save stdin */
    int fd_err = dup(STDERR_FILENO);     /* Save stdin */
    char mypid[16];                      /*variable para guardar variable de entorno el pid*/
    char status_str[16];                 /*variable para guardar variable de entorno el status*/
    char bgpid_str[16];                  /*variable para guardar variable de entorno el bgpid*/

    setbuf(stdout, NULL); /* Unbuffered */
    setbuf(stdin, NULL);  /* Unbuffered */

    sprintf(mypid, "mypid=%d", getpid()); /*guardamos el pid en la variable mypid*/
    putenv(mypid);                        /*guardamos la variable mypid en el entorno*/

    putenv("prompt=msh>"); /*guardamos la variable prompt en el entorno*/

    ignore_signals(1); /* Ignoramos las señales (SIGINT, SIGQUIT) */
    while (1)
    {
        while ((pid = waitpid(-1, NULL, WNOHANG)) > 0 && bg_procs > 0) /*Si hay algun proceso zombie lo recogemos para que termine definitivamente*/
        {
            bg_procs--; /*decrementamos el contador de procesos en background*/
        }
        char *prompt = getenv("prompt"); /*obtenemos el prompt*/
        if (prompt == NULL)
        {
            fprintf(stderr, "Error al obtener el prompt\n");
            exit(EXIT_FAILURE);
        }
        fprintf(stderr, "%s", prompt); /* Prompt */
        ret = obtain_order(&argvv, filev, &bg);
        if (ret == 0)
            break; /* EOF */
        if (ret == -1)
            continue;    /* Syntax error */
        argvc = ret - 1; /* Line */
        if (argvc == 0)
            continue; /* Empty line */

        /*reservamos memoria*/
        get_memory(&procesos, &pipes, argvc);
        /*redirigimos la entrada y la salida*/
        if ((redirect_std(filev)) == 1) /*si hay error en la redireccion, no hacemos nada*/
        {
            argvc = 0; /*desactualizamos el numero de comandos*/
        }
        /*comprobamos si es un comando interno y si es asi lo ejecutamos*/
        tipo_comando(argvv, &bg_procs, bg, &internal, &ultimo, &argvc, status_str);
        /*ejecutamos los comandos*/
        execute_commands(procesos, argvv, pipes, argvc, argvc - 1, argvc - 2, bg, internal, ultimo, status_str, bgpid_str);
        /*restauramos la entrada y la salida*/
        reset_std(fd_in, fd_out, fd_err, filev, internal);
        /*liberamos la memoria*/
        free_memory(&procesos, &pipes, NULL, argvc);
        /*reiniciamos los flags*/
        internal = 0;
        ultimo = 0;
    }
    /*cerramos fd_in, fd_out y fd_err*/
    close(fd_in);
    close(fd_out);
    close(fd_err);
    return 0;
}
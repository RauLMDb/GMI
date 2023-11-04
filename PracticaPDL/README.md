# Práctica de Procesadores de Lenguajes
## Procesador de JavaScript PDL

## Funcionamiento
1. En input.txt se sube el archivo que se quiere procesar en el formato de JavaScript-PDL.
2. Se crea una nueva ventana de la terminal y se accede a la carpeta dónde está este fichero.
3. En esa misma ventana de la terminal se ejecuta cualquier comando de ejecución
4. El resultado del procesado aparecerá por pantalla y en la carpeta ´./out/´ se guardarán los ficheros de tokens, el parse y la tabla de símbolos.

`Es importante saber que hay que tener Node instalando para que funcione el procesador`

## Comandos de ejecución
Para ejecutar el procesador se dispone de dos comandos que en esencia hacen lo mismo, uno más específico y otro más simple para un perfil de usuarios sin conocimientos de ´node´:
- node ./src/main.js : Procesa el archivo que esta en ./input.txt y retorna los archivos a la carpeta ./out/
- npm run start : Procesa el archivo que esta en ./input.txt y retorna los archivos a la carpeta ./out/


## Aspectos destacables
El zip contiene ya descargadas todas las librerías en la carpeta ´./node_modules/´ pero igualmente se pueden reinstalar con el comando ´npm i´. Para poder ejecutar el programa se debe tener instalado ´node´ en la computadora y se recomienda algún gestor de archivos de node como ´npm´ o ´yarn´.
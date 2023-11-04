comando para enviar main.c a triqui :
ssh c20m063@triqui4.fi.upm.es
scp main.c c20m063@triqui4.fi.upm.es:DATSI/so/msh.2023a
valgrind --tool=memcheck --leak-check=yes ./msh
el procedimiento sera edito main aqui y luego lo envio a triqui donde lo compilo y ejecuto.
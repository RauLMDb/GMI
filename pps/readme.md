cd /mnt/c/Users/clash/OneDrive/Escritorio/GMI/VScode/RAUL/pps/examenJulioC
scp *? c20m063@triqui2.fi.upm.es:pps/examenJulioC
valgrind --tool=memcheck --leak-check=yes ./main in1.txt -- in2.txt

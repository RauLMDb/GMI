public class Retos {
    public static void main(String[] args) {
        String word1 = "listen";
        String word2 = "silent";
        System.out.println(isAnagram(word1, word2));
        fibonacciOPrimo(6);
    }

    /*
     * Escribe una función que reciba dos palabras (String) y retorne
     * verdadero o falso (Bool) según sean o no anagramas.
     * - Un Anagrama consiste en formar una palabra reordenando TODAS
     * las letras de otra palabra inicial.
     * - NO hace falta comprobar que ambas palabras existan.
     * - Dos palabras exactamente iguales no son anagrama.
     */
    private static boolean isAnagram(String word1, String word2) {
        boolean isAnagram = true;
        if (word1.length() != word2.length())
            return false;
        for (char c : word1.toCharArray()) {
            boolean contains = false;
            if (!isAnagram)
                return false;
            for (char d : word2.toCharArray()) {
                contains |= c == d;
            }
            isAnagram &= contains;
        }
        return isAnagram;
    }

    /*
     * Escribe un programa que, dado un número, compruebe y muestre si es primo,
     * fibonacci y par.
     * Ejemplos:
     * - Con el número 2, nos dirá: "2 es primo, fibonacci y es par"
     * - Con el número 7, nos dirá: "7 es primo, no es fibonacci y es impar"
     */
    private static void fibonacciOPrimo(int number) {
        boolean isPrime = true;
        boolean isFibonacci = false;
        boolean isEven = number % 2 == 0;
        for (int i = 2; i < number; i++) {
            isPrime &= number % i != 0;
            isFibonacci |= number == fibbonacci(i);
        }
        System.out.println(number + " es " + (isPrime ? "" : "no ") + "primo, " + (isFibonacci ? "" : "no ") + "fibonacci y es " + (isEven ? "" : "im") + "par");
     }
     private static int fibbonacci(int number) {
        if (number == 0)
            return 0;
        if (number == 1)
            return 1;
        return fibbonacci(number - 1) + fibbonacci(number - 2);
    }
        
   
}

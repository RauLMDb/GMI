package aed.tries;


public class triespruebas {

	public static void main(String[] args) {
		Primes p = new Primes();
		for (int i = 1;i<631;i++) {
			p.add(i);
		}
		
		System.out.println(p.add(631));
		System.out.println(p.primes);
		
	}
	private static int power(int base, int exponente) {
		if (exponente == 0) return 1;
		else return base*power(base,exponente - 1);
	}
}

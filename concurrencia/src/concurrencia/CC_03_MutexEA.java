package concurrencia;
//Exclusión mutua con espera activa.
//
//Intentar garantizar la exclusión mutua en sc_inc y sc_dec sin
//utilizar más mecanismo de concurrencia que el de la espera activa
//(nuevas variables y bucles).
//
//Las propiedades que deberán cumplirse:
//- Garantía mutual exclusión (exclusión mútua): nunca hay dos
//procesos ejecutando secciones críticas de forma simultánea.
//- Ausencia de deadlock (interbloqueo): los procesos no quedan
//"atrapados" para siempre.
//- Ausencia de starvation (inanición): si un proceso quiere acceder
//a su sección crítica entonces es seguro que alguna vez lo hace.
//- Ausencia de esperas innecesarias: si un proceso quiere acceder a
//su sección crítica y ningún otro proceso está accediendo ni
//quiere acceder entonces el primero puede acceder.
//
//Ideas:
//- Una variable booleana en_sc que indica que algún proceso está
//ejecutando en la sección crítica?
//- Una variable booleana turno?
//- Dos variables booleanas en_sc_inc y en_sc_dec que indican que un
//determinado proceso (el incrementador o el decrementador) está
//ejecutando su sección crítica?
//- Combinaciones?

class CC_03_MutexEA {
	static final int N_PASOS = 10000;

	// Generador de números aleatorios para simular tiempos de
	// ejecución
	static final java.util.Random RNG = new java.util.Random(0);

	// Variable compartida
	volatile static int n = 0;

	// Variables para evitar la inanicion
	static volatile int controlInc = 0;
	static volatile int controlDec = 0;
	static int counterInc = 0;
	static int counterDec = 0;
	// Variables para asegurar exclusión mutua
	static volatile boolean scInc = false;
	static volatile boolean scDec = false;
	static volatile boolean en_sc_inc = false;
	static volatile boolean en_sc_dec = false;
	// Sección no crítica
	static void no_sc() {
//		System.out.println("No SC");
//		try {
//			// No más de 2ms
//			Thread.sleep(RNG.nextInt(3));
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	// Secciones críticas
	static void sc_inc() {
		System.out.println("+" + controlInc);
		n++;
	}

	static void sc_dec() {
		System.out.println("-" + controlDec);
		n--;
	}

	// La labor del proceso incrementador es ejecutar no_sc() y luego
	// sc_inc() durante N_PASOS asegurando exclusión mutua sobre
	// sc_inc().
	static class Incrementador extends Thread {
		public void run () {
			for (int i = 0; i < N_PASOS; i++) {
				// Sección no crítica
				counterInc++;
				no_sc();
				scInc = true;
				
				if (counterDec != N_PASOS) {			// Comprueba si es necesario el control de starvation (si lo es si quedan incrementos por hacer)
					while (scDec || controlInc>1) {

						scInc = false;
						scInc = true;

					}
				}else {
					while (scDec) {

						scInc = false;
						scInc = true;

					}
				}
				en_sc_inc = true;

				// Sección crítica

				sc_inc();
				
				if(en_sc_inc && en_sc_dec) {
					System.out.println("CRASH");
				}
				
				// Protocolo de salida de la sección crítica y actualizacion del control de starvation
				en_sc_inc = false;
				
				controlInc++;
				controlDec = 0;
				
				scInc = false;
			}
		}
	}

	// La labor del proceso incrementador es ejecutar no_sc() y luego
	// sc_dec() durante N_PASOS asegurando exclusión mutua sobre
	// sc_dec().
	static class Decrementador extends Thread {
		public void run () {
			for (int i = 0; i < N_PASOS; i++) {
				counterDec++;
				// Sección no crítica
				no_sc();
				
				scDec = true;

				if (counterInc != N_PASOS) {			// Comprueba si es necesario el control de starvation (si lo es si quedan decrementos por hacer)
					while (scInc || controlDec>1) {

						scDec = false;
						scDec = true;

					}
				}else {
					while (scInc) {

						scDec = false;
						scDec = true;

					}
				}
				
				en_sc_dec = true;
				
				// Sección crítica

				sc_dec();

				if(en_sc_inc && en_sc_dec) {
					System.out.println("CRASH");
				}
				
				// Protocolo de salida de la sección crítica y actualizacion del control de starvation
				en_sc_dec = false;
				
				controlDec++;
				controlInc = 0;
				
				scDec = false;
			}
		}
	}

	public static final void main(final String[] args)
			throws InterruptedException
	{
		Thread t1 = new Incrementador();
		Thread t2 = new Decrementador();

		// Las ponemos en marcha
		t1.start();
		t2.start();

		// Esperamos a que terminen
		t1.join();
		t2.join();

		// Simplemente se muestra el valor final de la variable:
		System.out.print("n = "+n);
	}
}
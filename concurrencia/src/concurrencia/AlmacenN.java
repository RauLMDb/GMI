package concurrencia;

import es.upm.babel.cclib.*;

// TODO: importar la clase de los semáforos.

/**
 * Implementación de la clase Almacen que permite el almacenamiento
 * FIFO de hasta un determinado número de productos y el uso
 * simultáneo del almacén por varios threads.
 */
class AlmacenN implements Almacen {
	private int capacidad = 0;
	private Producto[] almacenado = null;
	private volatile int nDatos = 0;
	private int aExtraer = 0;
	private int aInsertar = 0;



	// TODO: declaración de los semáforos necesarios
	private static Semaphore acceso;
	private static Semaphore almacenar;
	private static Semaphore extraer;

	public AlmacenN(int n) {
		capacidad = n;
		almacenado = new Producto[capacidad];
		nDatos = 0;
		aExtraer = 0;
		aInsertar = 0;

		// TODO: inicialización de los semáforos
		acceso = new Semaphore(1);
		almacenar = new Semaphore(1);
		extraer = new Semaphore(0);
	}

	public void almacenar(Producto producto) {

		acceso.await();
		almacenar.await();
		if (nDatos >= capacidad) 			
			almacenar.await();
		// Sección crítica
		almacenado[aInsertar] = producto;
		nDatos++;
		aInsertar++;
		aInsertar %= capacidad;
		//salida
		if (nDatos >= capacidad) 
			extraer.signal();
		else {
			almacenar.signal();
		}
		acceso.signal();
	}

	public Producto extraer() {
		Producto result;
		if(nDatos > 0) {
			acceso.await();
		}
		extraer.await();
		if(nDatos <= 0) 
			extraer.await();
		// Sección crítica
		result = almacenado[aExtraer];
		almacenado[aExtraer] = null;
		nDatos--;
		aExtraer++;
		aExtraer %= capacidad;
		//salida
		if (nDatos <= 0) 
			almacenar.signal();
		else {
			extraer.signal();
		}
		acceso.signal();

		return result;
	}
}
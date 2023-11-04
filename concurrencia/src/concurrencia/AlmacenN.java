package concurrencia;

import es.upm.babel.cclib.*;

// TODO: importar la clase de los sem�foros.

/**
 * Implementaci�n de la clase Almacen que permite el almacenamiento
 * FIFO de hasta un determinado n�mero de productos y el uso
 * simult�neo del almac�n por varios threads.
 */
class AlmacenN implements Almacen {
	private int capacidad = 0;
	private Producto[] almacenado = null;
	private volatile int nDatos = 0;
	private int aExtraer = 0;
	private int aInsertar = 0;



	// TODO: declaraci�n de los sem�foros necesarios
	private static Semaphore acceso;
	private static Semaphore almacenar;
	private static Semaphore extraer;

	public AlmacenN(int n) {
		capacidad = n;
		almacenado = new Producto[capacidad];
		nDatos = 0;
		aExtraer = 0;
		aInsertar = 0;

		// TODO: inicializaci�n de los sem�foros
		acceso = new Semaphore(1);
		almacenar = new Semaphore(1);
		extraer = new Semaphore(0);
	}

	public void almacenar(Producto producto) {

		acceso.await();
		almacenar.await();
		if (nDatos >= capacidad) 			
			almacenar.await();
		// Secci�n cr�tica
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
		// Secci�n cr�tica
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
package concurrencia;

import es.upm.babel.cclib.*;

//TODO: importar la clase de los sem�foros.

/**
 * Implementaci�n de la clase Almacen que permite el almacenamiento
 * de producto y el uso simult�neo del almacen por varios threads.
 */
class Almacen1 implements Almacen {
	// Producto a almacenar: null representa que no hay producto
	private Producto almacenado = null;

	// TODO: declaraci�n e inicializaci�n de los sem�foros
	// necesarios

	private Semaphore almacenar;
	private Semaphore extraer;
	private static volatile boolean lleno;

	public Almacen1() {
		almacenar = new Semaphore(1);
		extraer = new Semaphore(0);
		lleno = false;
	}

	public void almacenar(Producto producto) {
		// TODO: protocolo de acceso a la secci�n cr�tica y c�digo de
		// sincronizaci�n para poder almacenar.

		almacenar.await();
		
		if (lleno)
			almacenar.await();
		
		// Secci�n cr�tica

		almacenado = producto;
		lleno = true;
		
		// TODO: protocolo de salida de la secci�n cr�tica y c�digo de
		// sincronizaci�n para poder extraer.
		
		extraer.signal();
		
	}

	public Producto extraer() {
		Producto result;

		// TODO: protocolo de acceso a la secci�n cr�tica y c�digo de
		// sincronizaci�n para poder extraer.

		extraer.await();

		if(!lleno)
			extraer.await();

		// Secci�n cr�tica
		result = almacenado;
		almacenado = null;
		lleno = false;

		// TODO: protocolo de salida de la secci�n cr�tica y c�digo de
		// sincronizaci�n para poder almacenar.
		
		almacenar.signal();
		
		return result;
	}
}
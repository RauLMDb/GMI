package concurrencia;

import es.upm.babel.cclib.*;

//TODO: importar la clase de los semáforos.

/**
 * Implementación de la clase Almacen que permite el almacenamiento
 * de producto y el uso simultáneo del almacen por varios threads.
 */
class Almacen1 implements Almacen {
	// Producto a almacenar: null representa que no hay producto
	private Producto almacenado = null;

	// TODO: declaración e inicialización de los semáforos
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
		// TODO: protocolo de acceso a la sección crítica y código de
		// sincronización para poder almacenar.

		almacenar.await();
		
		if (lleno)
			almacenar.await();
		
		// Sección crítica

		almacenado = producto;
		lleno = true;
		
		// TODO: protocolo de salida de la sección crítica y código de
		// sincronización para poder extraer.
		
		extraer.signal();
		
	}

	public Producto extraer() {
		Producto result;

		// TODO: protocolo de acceso a la sección crítica y código de
		// sincronización para poder extraer.

		extraer.await();

		if(!lleno)
			extraer.await();

		// Sección crítica
		result = almacenado;
		almacenado = null;
		lleno = false;

		// TODO: protocolo de salida de la sección crítica y código de
		// sincronización para poder almacenar.
		
		almacenar.signal();
		
		return result;
	}
}
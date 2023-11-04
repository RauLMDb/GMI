package concurrencia;

import es.upm.babel.cclib.Producto;
import es.upm.babel.cclib.MultiAlmacen;
import es.upm.babel.cclib.Monitor;

// importar la librería de monitores

public class MultiAlmacenMon implements MultiAlmacen {
	private int capacidad = 0;
	private Producto almacenado[] = null;
	private int aExtraer = 0;
	private int aInsertar = 0;
	private int nDatos = 0;

	// TODO: declaración de atributos extras necesarios
	// para exclusión mutua y sincronización por condición
	private Monitor acceso;
	private Monitor.Cond almacenar;
	private Monitor.Cond consumir;
	// Para evitar la construcción de almacenes sin inicializar la
	// capacidad 
	private MultiAlmacenMon() {
	}

	public MultiAlmacenMon(int n) {
		almacenado = new Producto[n];
		aExtraer = 0;
		aInsertar = 0;
		capacidad = n;
		nDatos = 0;

		// TODO: inicialización de otros atributos
		acceso = new Monitor();
		almacenar = acceso.newCond();
		consumir = acceso.newCond();
	}

	private int nDatos() {
		return nDatos;
	}

	private int nHuecos() {
		return capacidad - nDatos;
	}

	public void almacenar(Producto[] productos) {
		//entrada
		acceso.enter();
		if (nHuecos() <= productos.length) {
			consumir.signal();
			almacenar.await();	
		}
		// Sección crítica
		for (int i = 0; i < productos.length; i++) {
			almacenado[aInsertar] = productos[i];
			nDatos++;
			aInsertar++;
			aInsertar %= capacidad;
		}
		System.out.println(nDatos);
		//salida
		if (nHuecos() <= productos.length) consumir.signal();
		else almacenar.signal();
		acceso.leave();

	}

	public Producto[] extraer(int n) {
		Producto[] result = new Producto[n];

		// entrada
		acceso.enter();
		if (n > nDatos) {
			almacenar.signal();
			consumir.await();
			
		}
		// Sección crítica
		for (int i = 0; i < result.length; i++) {
			result[i] = almacenado[aExtraer];
			almacenado[aExtraer] = null;
			nDatos--;
			aExtraer++;
			aExtraer %= capacidad;
		}
		// salida
		if (n > nDatos) almacenar.signal();
		else  consumir.signal();
		acceso.leave();

		return result;
	}
}
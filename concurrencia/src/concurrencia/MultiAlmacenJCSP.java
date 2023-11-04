package concurrencia;

import es.upm.babel.cclib.Producto;
import es.upm.babel.cclib.MultiAlmacen;
// importamos la librería JCSP
import org.jcsp.lang.*;

import java.util.ArrayList;

class MultiAlmacenJCSP implements MultiAlmacen, CSProcess {

	// Canales para enviar y recibir peticiones al/del servidor
	private final Any2OneChannel chAlmacenar = Channel.any2one();
	private final Any2OneChannel chExtraer = Channel.any2one();
	private int TAM;
	
	// Canal para gestionar la extraccion de productos
	private class ExtProd {
		private Any2OneChannel extraccion;
		int n;

		public ExtProd(int n) {
			extraccion = Channel.any2one();
			this.n = n;
		}
	}
	
	// Para evitar la construcción de almacenes sin inicializar la
	// capacidad
	private MultiAlmacenJCSP() {
	}

	public MultiAlmacenJCSP(int n) {
		this.TAM = n;

		// COMPLETAR : inicialización de otros atributos
	}

	public void almacenar(Producto[] productos) {
		// COMPLETAR : comunicación con el servidor
		chAlmacenar.out().write(productos);
	}

	public Producto[] extraer(int n) {
		// COMPLETAR : comunicación con el servidor
		ExtProd prod = new ExtProd(n);
		chExtraer.out().write(prod);
		return (Producto[]) prod.extraccion.in().read();
	}

	// código del servidor
	private static final int ALMACENAR = 0;
	private static final int EXTRAER = 1;

	public void run() {
		// COMPLETAR : declaración de canales y estructuras auxiliares
		ArrayList<Producto> waitingAlm = new ArrayList<>();//array para almacenar los prductos en espera de ser alamcenados
		ArrayList<ExtProd> waitingExt = new ArrayList<>();//array para almacenar los productos en espera de ser extraidos
		ArrayList<Producto> almacen = new ArrayList<>();//array para gestionar los productos almacenados y que por tanto seran extraidos

		Guard[] entradas = { chAlmacenar.in(), chExtraer.in() };

		Alternative servicios = new Alternative(entradas);
		int choice = 0;

		while (true) {
			try {
				choice = servicios.fairSelect();
			} catch (ProcessInterruptedException e) {

			}

			switch (choice) {
			case ALMACENAR:
				// COMPLETAR : tratamiento de la petición
				Producto[] prods = (Producto[]) chAlmacenar.in().read();
				for (Producto prod : prods) {
					waitingAlm.add(prod);
				}
				break;
			case EXTRAER:
				// COMPLETAR : tratamiento de la petición
				ExtProd pack = (ExtProd) chExtraer.in().read();
				waitingExt.add(pack);
				break;
			default:
			}

			// COMPLETAR : atención de peticiones pendientes
			while (capacidad(almacen) > 0 && !waitingAlm.isEmpty()) {
				almacen.add(waitingAlm.remove(0));
			}
			int i = 0;
			while (!almacen.isEmpty() && i < waitingExt.size()) {
				ExtProd pack = waitingExt.get(i);
				respuesta(pack, waitingExt, almacen, i);
				i++;
			}
		}
	}

	private int capacidad(ArrayList<Producto> alm) {
		return TAM - alm.size();
	}

	private void respuesta(ExtProd pack, ArrayList<ExtProd> waitingExt, ArrayList<Producto> almacen, int i) {
		if (pack.n <= almacen.size()) {
			Producto[] response = new Producto[pack.n];
			for (int j = 0; j < pack.n; j++) {
				response[j] = almacen.remove(0);
			}
			pack.extraccion.out().write(response);
			waitingExt.remove(i);
			i--;
		}
	}
}
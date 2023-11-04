package cc.controlReciclado;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import es.upm.babel.cclib.Monitor;

public final class ControlRecicladoMonitor implements ControlReciclado {
	private enum Estado {
		LISTO, SUSTITUIBLE, SUSTITUYENDO
	}

	private final int MAX_P_CONTENEDOR;
	private final int MAX_P_GRUA;

	// Creo variables del C-TAD
	private int peso;
	private Estado estado;
	private int accediendo;

	// Creo monitor y condiciones:
	// Mutex
	private Monitor mutex;

	// Clase privada para bloquear procesos que intenten los metodos notificarPeso o
	// incrementarPeso
	private class SolicitudPeso {
		// Peso que se quiere notificar o soltar en el contenedor
		private int peso;

		// Variable para distinguir un metodo u otro, true->notificarPeso,
		// false->incrementarPeso
		private boolean notificar;

		// Con que hay q bloquear tras instanciar la clase
		private Monitor.Cond cond;

		// Constructor
		public SolicitudPeso(int p, boolean n) {
			peso = p;
			notificar = n;
			cond = mutex.newCond();
		}
	}

	// Lista de procesos que quieren realizar notificarPeso o IncremnetarPeso() pero
	// no cumplen sus CPRE,
	// Despues de aniadir la instancia a la lista hay que bloquerlo
	// (la clase no lo hace automaticamente al instanciarla)
	private Collection<SolicitudPeso> pesoEsperando;

	// Cond que se llama (await) si se quiere sustituir pero no se cumplen las
	// condiciones necesarias
	// (estado=SUSTITUIBLE, accediendo=0),
	// signal cuando se cumpla
	private Monitor.Cond sustituible;

	public ControlRecicladoMonitor(int max_p_contenedor, int max_p_grua) {
		MAX_P_CONTENEDOR = max_p_contenedor;
		MAX_P_GRUA = max_p_grua;

		// Inicializo variables como indica el C_TAD
		peso = 0;
		estado = Estado.LISTO;
		accediendo = 0;

		// Inicializo monitor y condiciones
		mutex = new Monitor();
		pesoEsperando = new ArrayList<>();
		sustituible = mutex.newCond();
	}

	public void notificarPeso(int p) {
		// Compruebo la PRE (0<p<=MAX_P_GRUA)
		if (!(0 < p && p <= MAX_P_GRUA))
			throw new IllegalArgumentException();

		// Hago el mutex
		mutex.enter();

		// Compruebo CPRE (estado!=SUSTITUYENDO)
		if (estado == Estado.SUSTITUYENDO) {
			// Creo la clase para bloquear el proceso y la aniado a la lista,
			// instanciar la clase no bloquea el proceso
			// Estamos en el metodo notificar, por lo tanto el segundo parametro del
			// constructor es true
			SolicitudPeso sp = new SolicitudPeso(p, true);
			pesoEsperando.add(sp);
			sp.cond.await();
		}

		// Seccion critica
		if (peso + p > MAX_P_CONTENEDOR)
			estado = Estado.SUSTITUIBLE;
		else
			estado = Estado.LISTO;

		// Sincronizacion
		desbloqueoGenerico();

		// Salgo mutex
		mutex.leave();
	}

	public void incrementarPeso(int p) {
		// Compruebo la PRE (0<p<=MAX_P_GRUA)
		if (!(0 < p && p <= MAX_P_GRUA))
			throw new IllegalArgumentException();

		// Hago el mutex
		mutex.enter();

		// Compruebo CPRE, (peso+p<=MAX_P_CONTENEDOR ^ estado!=SUSTITUYENDO),
		if (!(peso + p <= MAX_P_CONTENEDOR && estado != Estado.SUSTITUYENDO)) {
			// Creo la clase para bloquear el proceso y la aniado a la lista,
			// instanciar la clase no bloquea el proceso
			// Estamos en el metodo incrementar, por lo tanto el segundo parametro del
			// constructor es flase
			SolicitudPeso sp = new SolicitudPeso(p, false);
			pesoEsperando.add(sp);
			sp.cond.await();
		}

		// Seccion critica
		peso += p;
		accediendo++;

		// Sincronizacion
		desbloqueoGenerico();

		// Salgo mutex
		mutex.leave();
	}

	public void notificarSoltar() {
		// Hago el mutex
		mutex.enter();

		// CPRE es siempre cierta, luego no hago nada

		// Seccion critica
		accediendo--;

		// Sincronizacion
		desbloqueoGenerico();

		// Salgo mutex
		mutex.leave();
	}

	public void prepararSustitucion() {
		// Hago el mutex
		mutex.enter();

		// Compruebo CPRE (estado=SUSTITUIBLE ^ accediendo=0)
		if (!(estado == Estado.SUSTITUIBLE && accediendo == 0))
			sustituible.await();

		// Seccion critica
		estado = Estado.SUSTITUYENDO;
		accediendo = 0;

		// Sincronizacion, ninguna accion con una CPRE que bloquee admite el estado
		// SUSTITUYENDO,
		// luego no desbloqueo nada. (se podria llamar al desbloqueoGenerico pero seria
		// una accion vacia)

		// Salgo mutex
		mutex.leave();
	}

	public void notificarSustitucion() {
		// Hago el mutex
		mutex.enter();

		// CPRE es siempre cierta, luego no hago nada

		// Seccion critica
		peso = 0;
		estado = Estado.LISTO;
		accediendo = 0;

		// Sincronizacion
		desbloqueoGenerico();

		// Salgo mutex
		mutex.leave();
	}

	// Desbloqueo generico, se desbloquea tras cualquier accion
	private void desbloqueoGenerico() {
		// Si el estado es Sustituible compruebo que se cumple CPRE de
		// prepararSustitucion
		if (estado == Estado.SUSTITUIBLE && accediendo == 0)
			sustituible.signal();
		// En caso contrario comprueblo que no estoy sustituyendo e intento desbloquer
		// algun priceso de la lista
		else if (estado != Estado.SUSTITUYENDO)
			desbloqueoLista();
	}

	// Metodo para desbloquear la lista, solo se puede llamar si
	// estado!=SUSTITUYENDO
	private void desbloqueoLista() {
		if (estado != Estado.SUSTITUYENDO) {
			Iterator<SolicitudPeso> it = pesoEsperando.iterator();
			boolean encontrado = false;
			while (it.hasNext() && !encontrado) {
				SolicitudPeso sp = it.next();
				// Si es un proceso de notificar ya se cumple la CPRE (estado!=sustituyendo)
				// luego lo desbloqueo directamente
				// Si es de incrementar copruebo que cabe en el contenedor
				if (sp.notificar || peso + sp.peso <= MAX_P_CONTENEDOR) {
					encontrado = true;
					it.remove();
					sp.cond.signal();
				}

			}
		}
	}
}
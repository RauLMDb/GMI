package cc.controlReciclado;

import org.jcsp.lang.*;

import es.upm.aedlib.lifo.LIFOList;

public class ControlRecicladoCSP implements ControlReciclado, CSProcess {
	private enum Estado {
		LISTO, SUSTITUIBLE, SUSTITUYENDO
	}

	private final int MAX_P_CONTENEDOR;
	private final int MAX_P_GRUA;

	// Declaracion de las variables del C-TAD
	private int peso;
	private Estado estado;
	private int accediendo;

	// Declaracion de los canales de las operaciones
	private Any2OneChannel cNPeso;
	private Any2OneChannel cIPeso;
	private Any2OneChannel cPSustitucion;
	private Any2OneChannel cNSoltar;
	private Any2OneChannel cNSustitucion;

	// Clase auxiliar para tratar las operaciones con CPRE
	private class Peticion {
		int peso;
		One2OneChannel cPeticion;
		int tipo;

		public Peticion(int p, int tipo) {
			peso = p;
			this.tipo = tipo;
			cPeticion = Channel.one2one();
		}
	}

	public ControlRecicladoCSP(int max_p_contenedor, int max_p_grua) {
		MAX_P_CONTENEDOR = max_p_contenedor;
		MAX_P_GRUA = max_p_grua;

		// Inicializacion de los canales de las operaciones
		cNPeso = Channel.any2one();
		cIPeso = Channel.any2one();
		cPSustitucion = Channel.any2one();
		cNSoltar = Channel.any2one();
		cNSustitucion = Channel.any2one();

		// Inicializaion del self
		peso = 0;
		estado = Estado.LISTO;
		accediendo = 0;

		new ProcessManager(this).start();
	}

	public void notificarPeso(int p) {

		// Comprobacion de la PRE (0 < p <= MAX_P_GRUA)
		if (!(0 < p && p <= MAX_P_GRUA))
			throw new IllegalArgumentException();

		// Creacion de la peticion de instruccion
		Peticion notPeso = new Peticion(p, NOTIFICAR_PESO);

		// Envio la peticion por el canal correspondiente
		cNPeso.out().write(notPeso);

		// Bloqueante hasta que la accion de ejecuta
		notPeso.cPeticion.in().read();
	}

	public void incrementarPeso(int p) {

		// Comprobacion de la PRE (0 < p <= MAX_P_GRUA)
		if (!(0 < p && p <= MAX_P_GRUA))
			throw new IllegalArgumentException();

		// Creacion de la peticion de instruccion
		Peticion incPeso = new Peticion(p, INCREMENTAR_PESO);

		// Envio la peticion por el canal correspondiente
		cIPeso.out().write(incPeso);

		// Bloqueante hasta que la accion de ejecuta
		incPeso.cPeticion.in().read();

	}

	public void notificarSoltar() {

		// Envio la peticion por el canal correspondiente y espera a desbloqueo
		cNSoltar.out().write(true);
		
	}

	public void prepararSustitucion() {

		// Creacion de la peticion de instruccion
		Peticion prepSust = new Peticion(0, PREPARAR_SUSTITUCION);

		// Envio la peticion por el canal correspondiente
		cIPeso.out().write(prepSust);

		// Bloqueante hasta que la accion de ejecuta
		prepSust.cPeticion.in().read();

	}

	public void notificarSustitucion() {

		// Envio la peticion por el canal correspondiente y espera a desbloqueo
		cNSustitucion.out().write(true);

	}

	// Valores para identificar cada operacion
	private static final int NOTIFICAR_PESO = 0;
	private static final int INCREMENTAR_PESO = 1;
	private static final int NOTIFICAR_SOLTAR = 2;
	private static final int PREPARAR_SUSTITUCION = 3;
	private static final int NOTIFICAR_SUSTITUCION = 4;

	public void run() {
		// Lista donde se guardan las peticiones con CPRE != TRUE
		LIFOList<Peticion> waiting = new LIFOList<>();

		AltingChannelInput[] entradas = { cNPeso.in(), cIPeso.in(), cNSoltar.in(), cPSustitucion.in(),
				cNSustitucion.in() };
		Alternative servicios = new Alternative(entradas);

		int choice = 0;
		while (true) {
			try {
				choice = servicios.fairSelect();
			} catch (ProcessInterruptedException e) {
			}
			switch (choice) {
			case NOTIFICAR_SOLTAR:
				// Se lee la peticon con CPRE = TRUE y se desbloquea
				entradas[NOTIFICAR_SOLTAR].read();
				// POST
				accediendo--;

				break;
			case NOTIFICAR_SUSTITUCION:
				// Se lee la peticon con CPRE = TRUE y se desbloquea
				entradas[NOTIFICAR_SUSTITUCION].read();
				// POST
				peso = 0;
				estado = Estado.LISTO;
				accediendo = 0;
				
				break;
			default:
				// Se guardan todas las peticiones con CPRE != TRUE en waiting para
				// desbloquearlas depues
				Peticion sol = (Peticion) entradas[choice].read();
				waiting.push(sol);
				break;
			}
			// Se comprueba si se puede desbloquear alguna operacion
			revisarPeticiones(waiting);
			// No quedan peticiones activables
		}
	}

	/**
	 * Recorre waiting y para cada tipo de operacion comprueba si se cumple la CPRE
	 * y entonces realiza el POST, en ese caso se elimina de waiting, si no se pasa
	 * a la siguiente peticion. Si se realiza PS no se sigue buscando peticiones por
	 * activar.
	 * 
	 * @param waiting
	 */
	private void revisarPeticiones(LIFOList<Peticion> waiting) {
		int realizado = 0;
		// Variable auxiliar para poder actualizar waiting
		LIFOList<Peticion> aux = new LIFOList<>();
		while (waiting.size() > 0 && realizado != -1) {

			Peticion p = waiting.pop();
			switch (p.tipo) {
			case NOTIFICAR_PESO:
				realizado = ejecutarNP(p.peso);
				break;
			case INCREMENTAR_PESO:
				realizado = ejecutarIP(p.peso);
				break;
			case PREPARAR_SUSTITUCION:
				realizado = ejecutarPS();
				break;
			default:
				break;
			}

			if (realizado != 0) {
				// Se responde al canal de la peticion en concreto
				p.cPeticion.out().write(realizado);
			} else {
				// Guarda de la peticion no aceptada
				aux.push(p);
			}
		}
		// Se actualiza waiting ,ya sin las peticiones que se han realizado
		while (aux.size() > 0) {

			waiting.push(aux.pop());

		}
	}

	/**
	 * Comprueba la CPRE, si se cumple realiza el POST y devuelve -1, si no 0
	 * 
	 * @return
	 */
	private int ejecutarPS() {
		int hecho = 0;
		// CPRE
		if (estado == Estado.SUSTITUIBLE && accediendo == 0) {
			// POST
			estado = Estado.SUSTITUYENDO;
			accediendo = 0;
			hecho = -1;
		}
		return hecho;
	}

	/**
	 * Comprueba la CPRE, si se cumple realiza el POST y devuelve 1, si no 0
	 * 
	 * @return
	 */
	private int ejecutarIP(int p) {
		int hecho = 0;
		// CPRE
		if (estado != Estado.SUSTITUYENDO && peso + p <= MAX_P_CONTENEDOR) {
			// POST
			peso += p;
			accediendo++;
			hecho = 1;
		}
		return hecho;
	}

	/**
	 * Comprueba la CPRE, si se cumple realiza el POST y devuelve 1, si no 0
	 * 
	 * @return
	 */
	private int ejecutarNP(int p) {
		int hecho = 0;
		// CPRE
		if (estado != Estado.SUSTITUYENDO) {
			// POST
			if (peso + p > MAX_P_CONTENEDOR)
				estado = Estado.SUSTITUIBLE;
			else
				estado = Estado.LISTO;
			hecho = 1;
		}
		return hecho;
	}
}
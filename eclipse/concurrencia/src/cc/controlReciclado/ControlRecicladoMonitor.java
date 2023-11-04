package cc.controlReciclado;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import es.upm.babel.cclib.Monitor;

public final class ControlRecicladoMonitor implements ControlReciclado {
	private enum Estado { LISTO, SUSTITUIBLE, SUSTITUYENDO }

	private final int MAX_P_CONTENEDOR;
	private final int MAX_P_GRUA;

	//Creo variables del C-TAD
	private int peso;
	private Estado estado;
	private int accediendo;

	//Creo monitor y condiciones:
	//Mutex
	private Monitor mutex;

	//Cond que se llama (await) si se está sustituyendo, signal cuando se ha terminado de sustituir (estado=LISTO)
	private Monitor.Cond sustituyendo;

	//Lista de procesos que quieren realizar IncremnetarPeso() pero no cumplen la CPRE,
	//Después de añadir la instancia a la lista hay que bloquerlo (la clase no lo hace automáticamente al instanciarla)
	private Collection<SolicitudIncrementarPeso> incPesoEsperando;

	//Cond que se llama (await) si se quiere sustituir pero no se cumplen las condiciones necesarias(estado=SUSTITUIBLE, accediendo=0), 
	//signal cuando el estado cambia a SUSTITUIBLE y he comprobado que accediendo=0
	private Monitor.Cond sustituible;



	public ControlRecicladoMonitor (int max_p_contenedor,
			int max_p_grua) {
		MAX_P_CONTENEDOR = max_p_contenedor;
		MAX_P_GRUA = max_p_grua;

		//Inicializo variables como indica el C_TAD
		this.peso=0;
		this.estado=Estado.LISTO;
		this.accediendo=0;

		//Inicializo monitor y condiciones
		mutex=new Monitor();
		sustituyendo= mutex.newCond();
		incPesoEsperando= new ArrayList <SolicitudIncrementarPeso>();
		sustituible= mutex.newCond();
	}

	public void notificarPeso(int p) {
		//Compruebo la PRE (0<p<=MAX_P_GRUA)
		if( !(0<p	&&	p<=MAX_P_GRUA) ) throw new IllegalArgumentException();

		//Hago el mutex
		mutex.enter();

		//Compruebo CPRE (estado!=SUSTITUYENDO)
		if(!(this.estado!=Estado.SUSTITUYENDO))
			sustituyendo.await();

		//Sección crítica
		if(this.peso+p>MAX_P_CONTENEDOR)
			estado=Estado.SUSTITUIBLE;
		else
			estado=Estado.LISTO;

		//Sincronización
		desbloqueoGenerico();

		//Salgo mutex
		mutex.leave();
	}

	public void incrementarPeso(int p) {
		//Compruebo la PRE (0<p<=MAX_P_GRUA)
		if( !(0<p	&&	p<=MAX_P_GRUA) ) throw new IllegalArgumentException();

		//Hago el mutex
		mutex.enter();

		//Compruebo CPRE, (peso+p<=MAX_P_CONTENEDOR	^	estado!=SUSTITUYENDO), las hago separadas ya que utilizo condiciones diferentes
		//(peso+p<=MAX_P_CONTENEDOR) Si no hay espacio añado el proceso a la lista y lo bloqueo
		if(!(this.peso+p<=MAX_P_CONTENEDOR)) {
			SolicitudIncrementarPeso sip=new SolicitudIncrementarPeso(p);
			incPesoEsperando.add(sip);
			sip.cond.await();
		}
		//(estado!=SUSTITUYENDO)
		if(!(this.estado!=Estado.SUSTITUYENDO))
			sustituyendo.await();

		//Sección crítica
		this.peso+=p;
		accediendo++;

		//Sincronización
		desbloqueoGenerico();

		//Salgo mutex
		mutex.leave();
	}

	public void notificarSoltar() {

		//Hago el mutex
		mutex.enter();

		//CPRE es siempre cierta, luego no hago nada

		//Sección crítica
		accediendo--;

		//Sincronización
		desbloqueoGenerico();

		//Salgo mutex
		mutex.leave();
	}

	public void prepararSustitucion() {
		//Hago el mutex
		mutex.enter();

		//Compruebo CPRE (estado=SUSTITUIBLE	^	accediendo=0)
		if( !(this.estado==Estado.SUSTITUIBLE	&&	accediendo==0) )
			sustituible.await();

		//Sección crítica
		this.estado=Estado.SUSTITUYENDO;
		this.accediendo=0;

		//Sincronización, ninguna acción con una CPRE que bloquee admite el estado SUSTITUYENDO, luego no desbloqueo nada

		//Salgo mutex
		mutex.leave();
	}

	public void notificarSustitucion() {

		//Hago el mutex
		mutex.enter();

		//CPRE es siempre cierta, luego no hago nada

		//Sección crítica
		this.peso=0;
		this.estado=Estado.LISTO;
		this.accediendo=0;

		//Sincronización
		desbloqueoGenerico();

		//Salgo mutex
		mutex.leave();
	}

	//Desbloqueo genérico
	private void desbloqueoGenerico() {
		switch(this.estado) {
		//Si el estado es listo compruebo si puedo desbloquear algun proceso la lista y desbloqueo la condición de sustituyendo
		case LISTO:
			desbloqueoLista();
			sustituyendo.signal();
			break;
		//
		case SUSTITUIBLE:
			if(this.accediendo==0)	sustituible.signal();
			break;
		//En el caso SUSTITUYENDO (default en este caso) no hacemos nada
		//ya que no se puede hacer ninguna acción bloqueante (notificarPeso, incrementarPeso, prepararSustitucion) cuando se esta sustituyendo
		default:
			break;
		}
	}

	private void desbloqueoLista() {
		Iterator <SolicitudIncrementarPeso> it= incPesoEsperando.iterator();
		boolean encontrado=false;
		while(it.hasNext()	&&	!encontrado) {
			SolicitudIncrementarPeso sip=it.next();
			if(this.peso+sip.peso<=MAX_P_CONTENEDOR) {
				encontrado=true;
				it.remove();
				sip.cond.signal();
			}
		}
	}


	//Clase privada para bloquear procesos que intenten el método incrementarPeso() pero no cumplan la CPRE.
	//Guarda el peso que se intenta soltar en el contenedor, así como la cond que la va a bloquear. Es necesario bloquearla después de crear la instancia
	private class SolicitudIncrementarPeso{
		private int peso;
		public Monitor.Cond cond;
		public SolicitudIncrementarPeso(int p){
			this.peso=p;
			this.cond= mutex.newCond();
		}
	}



}
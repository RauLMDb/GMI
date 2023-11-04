package aed.bancofiel;

import java.util.Comparator;
import es.upm.aedlib.indexedlist.IndexedList;
import es.upm.aedlib.indexedlist.ArrayIndexedList;


/**
 * Implements the code for the bank application.
 * Implements the client and the "gestor" interfaces.
 */
public class BancoFiel implements ClienteBanco, GestorBanco {

	// NOTAD. No se deberia cambiar esta declaracion.
	public IndexedList<Cuenta> cuentas;

	// NOTAD. No se deberia cambiar esta constructor.
	public BancoFiel() {
		this.cuentas = new ArrayIndexedList<Cuenta>();
	}

	// ----------------------------------------------------------------------
	// Anadir metodos aqui ...
	// Funciones auxiliares ...
	/**
	 * busca la posicion de la cuenta con el id insertado en cuentas
	 * @param id
	 * @return posicion de la cuenta con id igual al parametro
	 */
	private int buscaCuenta(String id) {
		int i = 0;
		while (cuentas.size() > i && !cuentas.get(i).getId().equals(id)) {
			i++;
		}
		if (i == cuentas.size())
			i = -1;
		return i;
	}
	/**
	 * devuelve la cuenta con el id insertado
	 * @param id
	 * @return cuenta con el id insertado
	 * @throws CuentaNoExisteExc
	 */
	private Cuenta devuelveCuenta(String id) throws CuentaNoExisteExc {
		if (buscaCuenta(id) == -1) throw new CuentaNoExisteExc();
		return cuentas.get(buscaCuenta(id));
	}
	/**
	 * inserta ordenadamente el elemento e en la lista list
	 * @param <E>
	 * @param e
	 * @param list
	 * @param comp
	 */
	private static <E> void insertar (E e, IndexedList<E> list, Comparator <E> comp) {
		int i = 0;
		while(i < list.size() && 0 < comp.compare(e, list.get(i))) {
			i++;
		}
		list.add(i, e);
	}
	/**
	 * crea una nueva lista vacia a la que va insertando
	 * los elementos de list ya ordenados
	 * @param <E>
	 * @param list
	 * @param comp
	 * @return una nueva lista con los elemnetos ordenados segun comp
	 */
	private static <E> IndexedList <E> insertion(IndexedList<E> list, Comparator <E> comp){
		IndexedList<E> newList = new ArrayIndexedList<>();
		for (int i = 0; i < list.size() ; i++) {
			insertar(list.get(i), newList, comp);
		}
		return newList;
	}

	// ----------------------------------------------------------------------

	//Funciones de ClienteBanco ...

	public String crearCuenta(String dni, int saldoIncial) {
		Cuenta newCuenta = new Cuenta(dni,saldoIncial);
		cuentas.add(0, newCuenta);
		return newCuenta.getId();
	}

	public void borrarCuenta(String id) throws CuentaNoExisteExc, CuentaNoVaciaExc{
		if (buscaCuenta(id) == -1) throw new CuentaNoExisteExc();
		if (devuelveCuenta(id).getSaldo() != 0) throw new CuentaNoVaciaExc();
		cuentas.remove(devuelveCuenta(id));
	}

	public int ingresarDinero(String id, int cantidad) throws CuentaNoExisteExc{
		if (buscaCuenta(id) == -1) throw new CuentaNoExisteExc();
		return devuelveCuenta(id).ingresar(cantidad);
	}

	public int retirarDinero(String id, int cantidad) throws CuentaNoExisteExc, InsuficienteSaldoExc{
		if (buscaCuenta(id) == -1) throw new CuentaNoExisteExc();
		if (devuelveCuenta(id).getSaldo() < cantidad) throw new InsuficienteSaldoExc();
		return devuelveCuenta(id).retirar(cantidad);
	}

	public int consultarSaldo(String id) throws CuentaNoExisteExc{
		if (buscaCuenta(id) == -1) throw new CuentaNoExisteExc();
		return devuelveCuenta(id).getSaldo();
	}

	public void hacerTransferencia(String idFrom, String idTo, int cantidad) throws CuentaNoExisteExc, InsuficienteSaldoExc{
		if (buscaCuenta(idFrom) == -1 || buscaCuenta(idTo) == -1) throw new CuentaNoExisteExc();
		if (devuelveCuenta(idFrom).getSaldo() < cantidad) throw new InsuficienteSaldoExc();
		retirarDinero(idFrom, cantidad);
		ingresarDinero(idTo, cantidad);
	}

	public IndexedList<String> getIdCuentas(String dni){
		IndexedList<String> cuentasDni = new ArrayIndexedList<>();
		for (int i = 0; i < cuentas.size(); i++) {
			if (dni.equals(cuentas.get(i).getDNI())) 
				cuentasDni.add(0, cuentas.get(i).getId());
		}
		return cuentasDni;
	}

	public int getSaldoCuentas(String dni) {
		int saldoTotal = 0;
		IndexedList<String> cuentasDni = getIdCuentas(dni);
		try {
			for (int i = 0; i < getIdCuentas(dni).size(); i++) {
				saldoTotal += consultarSaldo(cuentasDni.get(i));
			}
		} catch (IndexOutOfBoundsException | CuentaNoExisteExc e) {
			e.printStackTrace();
		}

		return saldoTotal;
	}

	//--------------------------------------------------------------------

	//Funciones de GestorBanco ...

	public IndexedList<Cuenta> getCuentasOrdenadas(Comparator<Cuenta> cmp){
		return insertion(cuentas, cmp);
	}

	// ----------------------------------------------------------------------
	// NOTAD. No se deberia cambiar este metodo.

	public String toString() {
		return "banco";
	}
}
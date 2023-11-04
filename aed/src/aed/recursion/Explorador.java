package aed.recursion;

import java.util.Iterator;

import es.upm.aedlib.Pair;
import es.upm.aedlib.Position;
import es.upm.aedlib.positionlist.*;


public class Explorador {

	public static Pair<Object,PositionList<Lugar>> explora(Lugar inicialLugar) {
		Object tesoro = null;
		Pair<Object,PositionList<Lugar>> res = null;
		PositionList<Lugar> camino = new NodePositionList<>();
		PositionList<Lugar> caminos = (PositionList<Lugar>) inicialLugar.caminos();
		Iterator<Lugar> it= caminos.iterator();
		if (it.hasNext()) {
			inicialLugar.marcaSueloConTiza();
			recorreCamino(inicialLugar,tesoro,camino,null);
		}
		else {
			tesoro = inicialLugar.getTesoro();
			inicialLugar.marcaSueloConTiza();
			camino.addLast(inicialLugar);
		}
		res = new Pair<>(tesoro, camino);
		return res;
	}
	private static void recorreCamino(Lugar lugar, Object tesoro,PositionList<Lugar> camino,Position<Lugar> opcion) {
		camino.addLast(lugar);
		if (opcion == null)	opcion = camino.last();
		Lugar siguiente = null;
		Lugar aux;
		Iterator<Lugar> it = lugar.caminos().iterator();
		while (it.hasNext() && siguiente == null) {
			aux = it.next();
			if (!aux.sueloMarcadoConTiza()) siguiente = aux;
		}
		if (opcion.element().equals(camino.first()) && siguiente == null) camino = null;
		boolean found = lugar.tieneTesoro();
		if (found) tesoro = lugar.getTesoro();
		else if (camino != null){
			if (siguiente == null) {
				opcion = camino.prev(opcion);
				recorreCamino(opcion.element(), tesoro, camino,opcion);
			} else {
				recorreCamino(siguiente, tesoro, camino,null);
			}
		}
	}
}
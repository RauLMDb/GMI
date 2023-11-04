package aed.airport;

import es.upm.aedlib.Entry;
import es.upm.aedlib.EntryImpl;
import es.upm.aedlib.priorityqueue.SortedListPriorityQueue;

public class airportpruebas {

	public static void main(String[] args) {
		IncomingFlightsRegistry a = new IncomingFlightsRegistry();
		for (int i = 0; i < 64; i++) {
			a.arrivesAt("" + i, i);
		}
		System.out.println();
		System.out.println("registro" + a.registry);

		System.out.println("soon" + a.arriving(0));

	}

}

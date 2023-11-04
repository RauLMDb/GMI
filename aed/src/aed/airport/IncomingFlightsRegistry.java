package aed.airport;

import es.upm.aedlib.Entry;
import es.upm.aedlib.priorityqueue.*;
import es.upm.aedlib.map.*;
import es.upm.aedlib.positionlist.*;


/**
 * A registry which organizes information on airplane arrivals.
 */
public class IncomingFlightsRegistry {
	HeapPriorityQueue<Long, FlightArrival> registry;
	HashTableMap<String,Entry<Long,FlightArrival>> controlTower;

	/**
	 * Constructs an class instance.
	 */
	public IncomingFlightsRegistry() {
		registry = new HeapPriorityQueue<>();
		controlTower = new HashTableMap<>();
	}

	/**
	 * A flight is predicted to arrive at an arrival time (in seconds).
	 */
	public void arrivesAt(String flightName, long time) {
		if (controlTower.containsKey(flightName)) {								
			Entry<Long,FlightArrival> flight = controlTower.get(flightName);	
			registry.replaceKey(flight,time);									
			flight.getValue().setRight(time);									
		}else {																	
			FlightArrival flightData = new FlightArrival(flightName,time);		
			controlTower.put(flightName,registry.enqueue(time,flightData));		
		}
	}																			

	/**
	 * A flight has been diverted, i.e., will not arrive at the airport.
	 */
	public void flightDiverted(String flight) {
		if (controlTower.containsKey(flight)) 									
			registry.remove(controlTower.remove(flight));						
	}

	/**
	 * Returns the arrival time of the flight.
	 * @return the arrival time for the flight, or null if the flight is not predicted
	 * to arrive.
	 */
	public Long arrivalTime(String flight) {
		if (controlTower.containsKey(flight)) 
			return controlTower.get(flight).getValue().getRight();			
		else return null;													
	}

	/**
	 * Returns a list of "soon" arriving flights, i.e., if any 
	 * is predicted to arrive at the airport within nowTime+180
	 * then adds the predicted earliest arriving flight to the list to return, 
	 * and removes it from the registry.
	 * Moreover, also adds to the returned list, in order of arrival time, 
	 * any other flights arriving withinfirstArrivalTime+120; these flights are 
	 * also removed from the queue of incoming flights.
	 * @return a list of soon arriving flights.
	 */
	public PositionList<FlightArrival> arriving(long nowTime) {
		PositionList<FlightArrival> soon = new NodePositionList<>();					
		if (!registry.isEmpty() && nowTime + 180 >= registry.first().getKey()) {		
			FlightArrival flight = registry.dequeue().getValue();					
			controlTower.remove(flight.getLeft());								
			soon.addLast(flight);								            
			while (!registry.isEmpty() && flight.getRight() + 120 >= registry.first().getKey()) {	// O(1) porque el numero de aviones en conflicto es constante, 
				flight = registry.dequeue().getValue();												// por tanto, si entra, siempre se ejecuta un numero constante de veces.
				controlTower.remove(flight.getLeft());									
				soon.addLast(flight);																	
			}
		}
		return soon;																
	}
}
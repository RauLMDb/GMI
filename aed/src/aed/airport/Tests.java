package aed.airport;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import es.upm.aedlib.positionlist.*;


public class Tests {
	@Test
	public void testPropiedad1() {
		IncomingFlightsRegistry airport = new IncomingFlightsRegistry();
		airport.arrivesAt("1",1200);
		assertEquals((long)1200, airport.arrivalTime("1"));
	}
	@Test
	public void testPropiedad2() {
		IncomingFlightsRegistry airport = new IncomingFlightsRegistry();
		PositionList<FlightArrival> res = new NodePositionList<>();
		airport.arrivesAt("1",20);
		res.addFirst(airport.registry.first().getValue());
		airport.arrivesAt("2",10);
		res.addFirst(airport.registry.first().getValue());
		assertEquals(res, airport.arriving(0));
	}
}


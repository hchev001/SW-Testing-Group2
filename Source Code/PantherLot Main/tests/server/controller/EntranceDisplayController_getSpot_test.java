package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.storage.ParkingSpot;

public class EntranceDisplayController_getSpot_test {

	ControllerFacade fac;
	ParkingSpot spot;
	
	
	@Before
	public void setUp() throws Exception {
		fac = new ControllerFacade();
		fac.createEntranceDisplayController(fac);
		spot = mock(ParkingSpot.class);
		fac.getEntranceDisplayController().setSpot(spot);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSpot() {
		assertEquals(fac.getEntranceDisplayController().getSpot(), spot);
	}

}

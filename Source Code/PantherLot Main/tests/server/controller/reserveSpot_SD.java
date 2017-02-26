package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.storage.ParkingSpot;

public class reserveSpot_SD {
	ControllerFacade facade;
	ParkingSpot spot;
	String id;

	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		facade.createAccessControlServer(3738);
		
		id = "1663314";
		
		spot = mock(ParkingSpot.class);
		when(spot.getParkingNumber()).thenReturn("101");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected=NullPointerException.class)
	public void test() {
		facade.reserveSpot(spot, id);;
	}

}

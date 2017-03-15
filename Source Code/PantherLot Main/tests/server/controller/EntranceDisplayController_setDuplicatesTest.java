package server.controller;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.storage.ParkingSpot;
// DV2 NEW TEST
public class EntranceDisplayController_setDuplicatesTest {
	ControllerFacade facade;
	HashMap<String,ParkingSpot> garageConnector;
	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		facade.createEntranceDisplayController(facade);
		garageConnector = new HashMap<String, ParkingSpot>();
		facade.getEntranceDisplayController().setDuplicates(garageConnector);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetDuplicates() {
		assertEquals(facade.getEntranceDisplayController().getDuplicates(), garageConnector);
	}

}

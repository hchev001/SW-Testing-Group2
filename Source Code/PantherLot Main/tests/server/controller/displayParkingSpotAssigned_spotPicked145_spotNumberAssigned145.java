package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.maindisplay.SpotNumberDisplay;
import server.storage.ParkingSpot;

public class displayParkingSpotAssigned_spotPicked145_spotNumberAssigned145 {
	
	ControllerFacade facade;
	SpotNumberDisplay spotDisplay;
	ParkingSpot spot;
	EntranceDisplayController displayController;
	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		facade.createEntranceDisplayController(facade);
		
		spotDisplay = mock( SpotNumberDisplay.class);
		doReturn(true).when(spotDisplay).runDisplay(null);
		
		spot = mock(ParkingSpot.class);
		when(spot.getParkingNumber()).thenReturn("145");
		
		// inject dependencies into facade entranceDisplayController
		facade.getEntranceDisplayController().setsDisp(spotDisplay);
		facade.getEntranceDisplayController().setSpot(spot);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDisplayParkingSpotAssigned() {
		assertEquals(facade.displayParkingSpotAssigned(), "Your spot number is 145");
	}

}

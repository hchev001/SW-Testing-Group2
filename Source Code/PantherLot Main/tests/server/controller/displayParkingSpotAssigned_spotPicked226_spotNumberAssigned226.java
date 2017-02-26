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

public class displayParkingSpotAssigned_spotPicked226_spotNumberAssigned226 {
	
	ControllerFacade facade11;
	SpotNumberDisplay spotNumDisp2;
	ParkingSpot spot6;

	@Before
	public void setUp() throws Exception {
		facade11 = new ControllerFacade();
		facade11.createEntranceDisplayController(facade11);
		
		spotNumDisp2 = mock( SpotNumberDisplay.class);
		doReturn(true).when(spotNumDisp2).runDisplay(null);
		
		spot6 = mock(ParkingSpot.class);
		when(spot6.getParkingNumber()).thenReturn("226");
		
		// inject dependencies into facade entranceDisplayController
		facade11.getEntranceDisplayController().setsDisp(spotNumDisp2);
		facade11.getEntranceDisplayController().setSpot(spot6);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void SST007_testDisplayParkingSpotAssigned_SD1() {
		assertEquals(facade11.displayParkingSpotAssigned(), "Your spot number is 226");
	}

}

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
	
	ControllerFacade facade10; // facade 9
	SpotNumberDisplay spotNumDisp1; // spotNumDisp1
	ParkingSpot spot5; // spot 5
	@Before
	public void setUp() throws Exception {
		facade10 = new ControllerFacade();
		facade10.createEntranceDisplayController(facade10);
		
		spotNumDisp1 = mock( SpotNumberDisplay.class);
		doReturn(true).when(spotNumDisp1).runDisplay(null);
		
		spot5 = mock(ParkingSpot.class);
		when(spot5.getParkingNumber()).thenReturn("145");
		
		// inject dependencies into facade entranceDisplayController
		facade10.getEntranceDisplayController().setsDisp(spotNumDisp1);
		facade10.getEntranceDisplayController().setSpot(spot5);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void SST006_testDisplayParkingSpotAssigned_SD0() {
		assertEquals(facade10.displayParkingSpotAssigned(), "Your spot number is 145");
	}

}

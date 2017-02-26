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

public class displayParkingSpotAssigned_spotPickedNone_spotNumberAssignedNone {
	
	ControllerFacade facade12;
	SpotNumberDisplay spotNumDisp3;
	ParkingSpot spot7;
	
	@Before
	public void setUp() throws Exception {
		facade12 = new ControllerFacade();
		facade12.createEntranceDisplayController(facade12);
		
		spotNumDisp3 = mock( SpotNumberDisplay.class);
		doReturn(true).when(spotNumDisp3).runDisplay(null);
		
		spot7 = mock(ParkingSpot.class);
		when(spot7.getParkingNumber()).thenReturn("");
		
		// inject dependencies into facade entranceDisplayController
		facade12.getEntranceDisplayController().setsDisp(spotNumDisp3);
		facade12.getEntranceDisplayController().setSpot(spot7);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void SST008_testDisplayParkingSpotAssigned_SD2() {
		assertEquals(facade12.displayParkingSpotAssigned(), "Your spot number is ");
	}


}

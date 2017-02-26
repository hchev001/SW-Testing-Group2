package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.maindisplay.WelcomeDisplay;

public class IdentifyUser_userIsHandicapped_identifiedAsHandicapped {
	ControllerFacade facade15;
	
	WelcomeDisplay welcDisp2;
	
	@Before
	public void setUp() throws Exception {
		facade15 = new ControllerFacade();
		welcDisp2 = mock(WelcomeDisplay.class);
		facade15.createEntranceDisplayController(facade15);
		facade15.getEntranceDisplayController().setwDisp(welcDisp2);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void SST017_testIdentifyUser_SD() {
		when(welcDisp2.getID()).thenReturn("1654333");
		when(welcDisp2.returnType()).thenReturn("FiuParkingUser");
		assertEquals(facade15.identifyUser(), "Handicapped");	
	}
}

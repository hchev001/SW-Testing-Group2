package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.maindisplay.WelcomeDisplay;

public class IdentifyUser_userIsHandicapped_identifiedAsHandicapped {
	ControllerFacade facade;
	
	WelcomeDisplay welcomeDisplay;
	
	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		welcomeDisplay = mock(WelcomeDisplay.class);
		facade.createEntranceDisplayController(facade);
		facade.getEntranceDisplayController().setwDisp(welcomeDisplay);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIdentifyUser() {
		when(welcomeDisplay.getID()).thenReturn("1654333");
		when(welcomeDisplay.returnType()).thenReturn("FiuParkingUser");
		assertEquals(facade.identifyUser(), "Handicapped");	
	}
}

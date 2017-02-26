package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.maindisplay.WelcomeDisplay;

public class IdentifyUser_emptyID_identifiedAsGuest {

	ControllerFacade facade13;
	
	WelcomeDisplay welcDisp0;
	
	@Before
	public void setUp() throws Exception {
		facade13 = new ControllerFacade();
		welcDisp0 = mock(WelcomeDisplay.class);
		facade13.createEntranceDisplayController(facade13);
		facade13.getEntranceDisplayController().setwDisp(welcDisp0);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void SST009_testIdentifyUser_SD() {
		when(welcDisp0.getID()).thenReturn("");
		when(welcDisp0.returnType()).thenReturn("FiuParkingUser");
		assertEquals(facade13.identifyUser(), "Guest");	
	}
}

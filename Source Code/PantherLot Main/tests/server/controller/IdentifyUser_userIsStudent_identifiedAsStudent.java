package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.maindisplay.WelcomeDisplay;

public class IdentifyUser_userIsStudent_identifiedAsStudent {
	ControllerFacade facade16;
	
	WelcomeDisplay welcDisp3;
	
	@Before
	public void setUp() throws Exception {
		facade16 = new ControllerFacade();
		welcDisp3 = mock(WelcomeDisplay.class);
		facade16.createEntranceDisplayController(facade16);
		facade16.getEntranceDisplayController().setwDisp(welcDisp3);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void SST018_testIdentifyUser_SD() {
		when(welcDisp3.getID()).thenReturn("2223432");
		when(welcDisp3.returnType()).thenReturn("FiuParkingUser");
		assertEquals(facade16.identifyUser(), "Student");	
	}
}
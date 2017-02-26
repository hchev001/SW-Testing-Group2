package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.maindisplay.WelcomeDisplay;

public class IdentifyUser_userIsFaculty_identifiedAsFaculty {
	ControllerFacade facade14;
	
	WelcomeDisplay welcDisp1;
	
	@Before
	public void setUp() throws Exception {
		facade14 = new ControllerFacade();
		welcDisp1 = mock(WelcomeDisplay.class);
		facade14.createEntranceDisplayController(facade14);
		facade14.getEntranceDisplayController().setwDisp(welcDisp1);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void SST016_testIdentifyUser_SD() {
		when(welcDisp1.getID()).thenReturn("1663314");
		when(welcDisp1.returnType()).thenReturn("FiuParkingUser");
		assertEquals(facade14.identifyUser(), "Faculty");	
	}
}

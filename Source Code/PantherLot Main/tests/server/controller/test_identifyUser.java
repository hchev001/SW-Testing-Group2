package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import client.maindisplay.WelcomeDisplay;


public class test_identifyUser {
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
		when(welcomeDisplay.getID()).thenReturn("1663314");
		when(welcomeDisplay.returnType()).thenReturn("FiuParkingUser");
		assertEquals(facade.identifyUser(), "Faculty");	
	}

}
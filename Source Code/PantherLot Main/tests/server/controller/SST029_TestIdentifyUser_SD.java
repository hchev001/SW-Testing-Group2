package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import client.maindisplay.WelcomeDisplay;
/*
 * Purpose: Test the call identifyUser() after the user enters no id
 * and clicks on the Handicap Button.
 */
public class SST029_TestIdentifyUser_SD {

	ControllerFacade facade = new ControllerFacade();
	WelcomeDisplay welcomeDisplay;
	
	/* 
	 * Test Setup: Through the package facade, create an EntranceDisplayControler object.
	 * Mock its WelcomeDisplay object reference.
	 * 
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		welcomeDisplay = mock(WelcomeDisplay.class);
		facade.createEntranceDisplayController(facade);
		facade.getEntranceDisplayController().setwDisp(welcomeDisplay);
		when(welcomeDisplay.getID()).thenReturn("");
		when(welcomeDisplay.returnType()).thenReturn("handicap");
	}
	/*
	 * Test Input: identifyUser() is called.
	 * 
	 * Expected Out: identifyUser returns "handicap"
	 */
	@Test
	public void testIdentifyUser() {
		assertEquals(facade.identifyUser(), facade.getEntranceDisplayController().getUser().toString());
	}

}

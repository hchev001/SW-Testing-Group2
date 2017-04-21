package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import client.maindisplay.WelcomeDisplay;
/* Test Case ID: SST029_TestIdentifyUser_SD
 * Purpose: Test the call identifyUser() after the user enters no id
 * and clicks on the Handicap Button.
 */
public class SST029_TestIdentifyUser_SD {

	ControllerFacade facade = new ControllerFacade();
	WelcomeDisplay welcomeDisplay;
	
	/* 
	 * Test Setup: Through the package façade, create an entranceDisplayController 
	 * object. Mock the dependency WelcomeDisplay.class. When the mocked instance getID 
	 * call is invoked, it returns an empty string. When the returnType call is invoked, 
	 * it returns “handicap”.
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

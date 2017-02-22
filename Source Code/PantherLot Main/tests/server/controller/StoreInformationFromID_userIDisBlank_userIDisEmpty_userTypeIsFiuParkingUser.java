package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.maindisplay.WelcomeDisplay;

public class StoreInformationFromID_userIDisBlank_userIDisEmpty_userTypeIsFiuParkingUser {

	ControllerFacade facade;
	WelcomeDisplay welcomeDisplay;
	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		welcomeDisplay = mock(WelcomeDisplay.class);
		facade.createEntranceDisplayController(facade);
		facade.getEntranceDisplayController().setwDisp(welcomeDisplay);
		when(welcomeDisplay.getID()).thenReturn("");
		when(welcomeDisplay.returnType()).thenReturn("FiuParkingUser");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testStoreInformationFromID() {
		facade.storeInformationFromID();
		assertEquals(facade.getEntranceDisplayController().getUserID(), "");
		assertEquals(facade.getEntranceDisplayController().getUserType(), "FiuParkingUser");
	}
	

}

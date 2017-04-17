package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import client.maindisplay.WelcomeDisplay;

public class SST029_TestIdentifyUser_SD {

	ControllerFacade facade = new ControllerFacade();
	WelcomeDisplay welcomeDisplay;
	
	/*
	 * The WelcomeDisplay object is mocked as it is outside of the scope of the
	 * package, a new EntranceDispalyController is instantiated, and the mocked
	 * object is injected. WHen identiyUser() is called, the mocked object
	 * will return an empty string for the ID and "handicap" for user type because
	 * they entered no id and clicked on the Handicap button.
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
	 * identifyUser() returns the string value for the type of user interacting
	 * with PantherLot. We assert equals to compare that the user is identified
	 * as a handicap.
	 */
	@Test
	public void testIdentifyUser() {
		assertEquals(facade.identifyUser(), facade.getEntranceDisplayController().getUser().toString());
	}

}

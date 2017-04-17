package server.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import client.maindisplay.WelcomeDisplay;

/*
 * Test Purpose: To confirm that a new Guest user is created upon clicking
 * the Guest Button on the Welcome Display.
 */
public class SST030_TestIdentifyUser_SD {

	ControllerFacade facade = new ControllerFacade();
	WelcomeDisplay welcomeDisplay;
	
	/*
	 * The WelcomeDisplay object is mocked as it is outside of the scope of the
	 * package, a new EntranceDispalyController is instantiated, and the mocked
	 * object is injected. WHen identiyUser() is called, the mocked object
	 * will return an empty string for the ID and "guest" for user type because
	 * they entered no id and clicked on the Guest button.
	 */
	@Before
	public void setUp() throws Exception {
		welcomeDisplay = mock(WelcomeDisplay.class);
		facade.createEntranceDisplayController(facade);
		facade.getEntranceDisplayController().setwDisp(welcomeDisplay);
		when(welcomeDisplay.getID()).thenReturn("");
		when(welcomeDisplay.returnType()).thenReturn("guest");
	}

	/*
	 * identifyUser() returns the string value for the type of user interacting
	 * with PantherLot. We assert equals to compare that the user is identified
	 * as a guest.
	 */
	@Test
	public void testIdentifyUser() {
		assertEquals(facade.identifyUser(), facade.getEntranceDisplayController().getUser().toString());
	}

}

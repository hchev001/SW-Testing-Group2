package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.maindisplay.DisplayDirections;
import client.maindisplay.ParkingNotification;
import client.maindisplay.SpotNumberDisplay;
import client.maindisplay.WelcomeDisplay;
/*
 * Test Case ID: SST024_EntranceDisplayController_testGetwDisp
 * Test Purpose: 
 * To test the call of getwDisp() from an EntranceDisplayController object and get coverage for the setter
 * 
 * Test Case ID: SST031_EntranceDisplayController_testGetpDisp
 * Test Purpose:
 * To test the call of getpDisp() from an EntranceDisplayController object and get coverage for the setter
 * 
 * Test Case ID: SST032_EntranceDisplayController_testGetsDisp
 * Test Purpose:
 * To test the call of getsDisp() from an EntranceDisplayController object and get coverage for the setter
 * 
 * Test Case ID: SST033_EntranceDisplayController_testGetdDisp
 * Test Purpose:
 * To test the call of getdDisp() from an EntranceDisplayController object and get coverage for the setter
 */
public class SST024_EntranceDisplayController_DisplayGettersSettersTest {
	ControllerFacade facade;
	WelcomeDisplay wDisp;
	SpotNumberDisplay sDisp;
	ParkingNotification pDisp;
	DisplayDirections dDisp;
	
	/*
	 * Test Setup:
	 * Through a facade, instantiate an EntranceDisplayController object.
	 * Inject a mock of WelcomeDisplay, Parkingnotification, SpotNumberDisplay
	 * and DisplayDirections classes.
	 */
	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		facade.createEntranceDisplayController(facade);
		wDisp = mock(WelcomeDisplay.class);
		pDisp = mock(ParkingNotification.class);
		sDisp = mock(SpotNumberDisplay.class);
		dDisp = mock(DisplayDirections.class);
		
		facade.getEntranceDisplayController().setwDisp(wDisp);
		facade.getEntranceDisplayController().setpDisp(pDisp);
		facade.getEntranceDisplayController().setsDisp(sDisp);
		facade.getEntranceDisplayController().setdDisp(dDisp);
		
		
	}

	@After
	public void tearDown() throws Exception {
	}
	/*
	 * Test Input: getwDisp() is called.
	 * Expected Output: The object returned by getwDisp() has the same
	 * memory address as the mocked object wDisp.
	 */
	@Test
	public void SST024_EntranceDisplayController_testGetwDisp() {
		assertEquals(facade.getEntranceDisplayController().getwDisp(), wDisp);
	}
	
	/*
	 * Test Input: getpDisp() is called.
	 * Expected Outpuit: The object returned by getpDisp() has the same
	 * memory address as the mocked object pDisp.
	 */
	@Test
	public void SST031_EntranceDisplayController_testGetpDisp() {
		assertEquals(facade.getEntranceDisplayController().getpDisp(), pDisp);
	}
	/*
	 * Test Input: getsDisp() is called.
	 * Expected Output: The object returned by getsDisp() has the same
	 * memory address as the mocked object sDisp.
	 */
	@Test
	public void SST032_EntranceDisplayController_testGetsDisp() {
		assertEquals(facade.getEntranceDisplayController().getsDisp(), sDisp);
	}
	
	/*
	 * Test Input: getdDisp() is called
	 * Expected Output: The object returned by getdDisp() has the same
	 * memory address as the mocked object dDisp.
	 */
	@Test
	public void SST033_EntranceDisplayController_testGetdDisp() {
		assertEquals(facade.getEntranceDisplayController().getdDisp(), dDisp);
	}

}

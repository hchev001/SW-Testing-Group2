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
// DV2 NEW TEST
public class SST024_EntranceDisplayController_DisplayGettersSettersTest {
	ControllerFacade facade;
	WelcomeDisplay wDisp;
	SpotNumberDisplay sDisp;
	ParkingNotification pDisp;
	DisplayDirections dDisp;
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

	@Test
	public void testGetwDisp() {
		assertEquals(facade.getEntranceDisplayController().getwDisp(), wDisp);
	}

	@Test
	public void testGetpDisp() {
		assertEquals(facade.getEntranceDisplayController().getpDisp(), pDisp);
	}

	@Test
	public void testGetsDisp() {
		assertEquals(facade.getEntranceDisplayController().getsDisp(), sDisp);
	}

	@Test
	public void testGetdDisp() {
		assertEquals(facade.getEntranceDisplayController().getdDisp(), dDisp);
	}

}

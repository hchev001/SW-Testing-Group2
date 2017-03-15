package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.maindisplay.ParkingNotification;
//DV2 NEW Test Coverage generate message when user is found but usertype is invalid
public class SST023_testSetParkingNotification_RD {
	ControllerFacade facade;
	ParkingNotification parkingNotificationDisp;
	ParkingUser parkingUsr;
	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		parkingNotificationDisp = mock(ParkingNotification.class);
		parkingUsr = mock(FacultyUser.class);
		when(parkingUsr.toString()).thenReturn("Faculty");
		
		facade.createEntranceDisplayController(facade);
		facade.getEntranceDisplayController().setUserType("invalid");
		facade.getEntranceDisplayController().setFound(true);
		facade.getEntranceDisplayController().setUserID("1663314");
		facade.getEntranceDisplayController().setpDisp(parkingNotificationDisp);
		facade.getEntranceDisplayController().setUser(parkingUsr);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetParkingNotification() {
		String actual = "Invalid ID!  Assigning guest parking spot";
		String expected = facade.setParkingNotification();
		assertEquals(expected, actual);
	}

}

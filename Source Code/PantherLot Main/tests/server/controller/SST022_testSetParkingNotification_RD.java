package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.maindisplay.ParkingNotification;

/* Test Case ID: SST022_testSetParkingNotification_RD
 * Purpose: Test setParkingNotification() when the user is not found in the database.
 */
public class SST022_testSetParkingNotification_RD {
	ControllerFacade facade;
	ParkingNotification parkingNotificationDisp;
	ParkingUser parkingUsr;
	
	/*
	 * Test Setup: Through the package facade, create an EntranceDisplayController
	 * Mock its ParkingNotification and FacultyUser dependency.
	 * Sets the user's type to be valid and found. With a valid ID.
	 */
	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		parkingNotificationDisp = mock(ParkingNotification.class);
		parkingUsr = mock(FacultyUser.class);
		when(parkingUsr.toString()).thenReturn("Faculty");
		
		facade.createEntranceDisplayController(facade);
		facade.getEntranceDisplayController().setFound(false);
		facade.getEntranceDisplayController().setUserID("1663314");
		facade.getEntranceDisplayController().setpDisp(parkingNotificationDisp);
		facade.getEntranceDisplayController().setUserType("Fiu Parking user");
		facade.getEntranceDisplayController().setUser(parkingUsr);
	}

	@After
	public void tearDown() throws Exception {
	}
	/*
	 * Test Input: setParkingNotification() is called.
	 * Expected Output: setParkingNotificaiton() returns "Valid Request: There are no faculty spots available"
	 */
	@Test
	public void test() {
		String actual = "Valid Request  There are no faculty spots available";
		String expected = facade.setParkingNotification();
		assertEquals(expected, actual);
	}

}

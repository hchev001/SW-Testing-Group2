package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import server.storage.ParkingSpot;

public class SST028_EntranceDisplayController_SetGetDuplicateTest {
	/*
	 * Purpose: To verify that the setDuplicate(true) function of the 
	 * EntranceDisplayController, sets the boolean duplicate variable correctly
	 * and getDuplicate() returns the same value.
	 */
	
	
	ControllerFacade fac;
	
	/*
	 * A new ControllerFacade is instantiated. Through it, a EntranceDisplayController
	 * object is instantiated and its boolean duplicate value is set to true.
	 */
	@Before
	public void setUp() throws Exception {
		fac = new ControllerFacade();
		fac.createEntranceDisplayController(fac);
		fac.getEntranceDisplayController().setDuplicate(true);
		
		
		
	}
	
	/*
	 * We assertTrue to check if the method from the EntranceDisplayController
	 * getDuplicate() returns the current state of the boolean duplicate instance
	 * variable.
	 * 
	 * Expected Output: True -- Duplicate is true
	 */
	@Test
	public final void testGetDuplicate() {
		assertTrue(fac.getEntranceDisplayController().getDuplicate());
	}

}

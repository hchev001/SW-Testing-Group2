package server.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/*
 * Purpose: Test the call isValid() after setValid() is called with a boolean passed as its parameter
 */
public class SST025_EntranceDisplayConroller_isValidTest {
	ControllerFacade facade;
	
	/*
	 * Setup: Through the package facade, create a new EntranceDisplayController
	 * 
	 * Input: Set the EntranceDisplayController boolean valid to true.
	 */
	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		facade.createEntranceDisplayController(facade);
		facade.getEntranceDisplayController().setValid(true);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/*
	 * Expected Output: isValid() returns true.
	 */
	@Test
	public void testIsValid() {
		assertTrue(facade.getEntranceDisplayController().isValid());
	}

}

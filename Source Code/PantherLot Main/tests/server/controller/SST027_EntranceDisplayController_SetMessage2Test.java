package server.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * Test Purpose: Test the call getMessage2() from EntranceDisplayController after
 * setMessage2() was called.
 */
public class SST027_EntranceDisplayController_SetMessage2Test {
	ControllerFacade facade;
	
	/*
	 * Through a facade, instantiate an EntranceDisplayController object
	 * Set its String Message2 variable to "Test string"
	 * 
	 * Test Input: setMessage2("Test string")
	 */
	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		facade.createEntranceDisplayController(facade);
		facade.getEntranceDisplayController().setMessage2("Test string");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/*
	 * Expected Output: getMessage2() returns "Test string"
	 */
	@Test
	public void testSetMessage2() {
		assertEquals(facade.getEntranceDisplayController().getMessage2(), "Test string");
	}

}

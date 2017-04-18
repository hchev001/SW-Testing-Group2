package server.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/*
 * Test Purpose: Test the call getMessage1() from EntranceDisplayController after
 * setMessage1() was called.
 */
public class SST026_EntranceDisplayConroller_SetMessage1Test {
	ControllerFacade facade;
	String message;
	
	/*
	 * Through a facade, instantiate an EntranceDisplayController object
	 * Set its String Message2 variable to "test message"
	 * 
	 * Test Input: setMessage1("test message")
	 */
	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		facade.createEntranceDisplayController(facade);
		message = "test message";
		facade.getEntranceDisplayController().setMessage1(message);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/*
	 * Expected Output: getMessage1() returns "test message"
	 */
	@Test
	public void test() {
		assertEquals(facade.getEntranceDisplayController().getMessage1(), message);
	}

}

package server.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
// DV 2 Test
public class SST027_EntranceDisplayController_SetMessage2Test {
	ControllerFacade facade;
	
	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		facade.createEntranceDisplayController(facade);
		facade.getEntranceDisplayController().setMessage2("Test string");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetMessage2() {
		assertEquals(facade.getEntranceDisplayController().getMessage2(), "Test string");
	}

}

package server.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EntranceDisplayController_isValid_test {
	ControllerFacade facade;
	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		facade.createEntranceDisplayController(facade);
		facade.getEntranceDisplayController().setValid(true);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIsValid() {
		assertTrue(facade.getEntranceDisplayController().isValid());
	}

}

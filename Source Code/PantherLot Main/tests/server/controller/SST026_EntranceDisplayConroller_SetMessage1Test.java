package server.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SST026_EntranceDisplayConroller_SetMessage1Test {
	ControllerFacade facade;
	String message;
	
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

	@Test
	public void test() {
		assertEquals(facade.getEntranceDisplayController().getMessage1(), message);
	}

}

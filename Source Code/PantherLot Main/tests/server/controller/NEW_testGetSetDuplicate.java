package server.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NEW_testGetSetDuplicate {
	
	ControllerFacade facade;
	

	@Before
	public void setUp() throws Exception {
		facade = new ControllerFacade();
		facade.createEntranceDisplayController(facade);
		facade.getEntranceDisplayController().setDuplicate(true);
	}

	@Test
	public void testGetDuplicate() {
		assertEquals(facade.getEntranceDisplayController().getDuplicate(), true);
	}

}

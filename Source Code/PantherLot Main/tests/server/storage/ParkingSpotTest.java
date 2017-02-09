package server.storage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ParkingSpotTest {
	
	/* Setups and Before
	 * -------------------
	 * Place anything that needs to be run before 
	 * the tests here. You can add the multiple @Before
	 * methods here in this format:
	 * 
	 * @Before
	 * setUpForxxTests() {
	 * }
	 * 
	 * Where xx corresponds to the tests using that 
	 * numbering format starting at 01.
	 ===================================================*/
	@Before
	public void setUpFor01Tests() {
		
	}
	
	
	/* Tests
	 * ------------------
	 * Runs the tests here. Name them what you want but
	 * use this format:
	 * 
	 * nameOfTestingMethodxx() {
	 * }
	 * 
	 * Where xx corresponds to the setup numbering 
	 * format starting at 01.
	 ===================================================*/
	
	//Tests if the getParkingSpot method returns the parkingSpotNumber
	//provided to it
	@Test
	public void getParkingSpotTest01() {
		
	}
	
	//Tests to see the getParkingSpot method returns an empty string
	//if an empty string is provided as the parkingSpotNumber
	//NOTE: This test may not be necessary!
	@Test
	public void getParkingSpotEmptyStringTest01() {
		
	}
	
	//Tests to see the getParkingSpot method throws an error if the 
	//parkingNumber is provided as an integer instead of a string.
	@Test
	public void getParkingSpotIntegerTest01() {
		
	}
}

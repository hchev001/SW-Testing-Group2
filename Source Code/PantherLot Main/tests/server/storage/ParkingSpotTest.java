package server.storage;

import static org.junit.Assert.*;

import org.junit.Test;

import server.controller.*;

import org.junit.Before;


public class ParkingSpotTest {

	// Variables used throughout the tests
	ParkingSpot testSpot;
	
	ParkingUser person01;
	ParkingUser person02;
	ParkingUser person03;
	ParkingUser person04;
	ParkingUser person05;
	

	
	@Before
	/*
	 * Set up for the tests to be executed
	 */
	public void setUp(){
		
		testSpot = new ParkingSpot(300 , "Guest" , 3 , "300" , "South");
		person01 = new StudentUser("Carl" , "7654321");
		person02 = new FacultyUser("John" , "1234567");
		person03 = new GuestUser();
		person04 = new HandicappedUser();
	}
	
	@Test
	/*
	 * This is testing the assigning and fetching of a Student User
	 */
	public void test_AssignParkingSpot_01() {
		
		testSpot.assignParkingSpot(person01);

		ParkingUser test = testSpot.getUser();
		
		assertEquals(person01 , test);
		
	}

	@Test
	/*
	 * This is testing the assigning and fetching of a Faculty User
	 * 
	 */
	public void test_AssignParkingSpot_02() {
		
		testSpot.assignParkingSpot(person02);

		ParkingUser test = testSpot.getUser();
		
		assertEquals(person02 , test);
		
	}
	
	@Test
	/*
	 * This is testing the assigning and fetching of a Guest User
	 * 
	 */
	public void test_AssignParkingSpot_03() {
		
		testSpot.assignParkingSpot(person03);

		ParkingUser test = testSpot.getUser();
		
		assertEquals(person03 , test);
		
	}
	
	@Test
	/*
	 * This is testing the assigning and fetching of a Student User
	 * 
	 */
	public void test_AssignParkingSpot_04() {
		
		testSpot.assignParkingSpot(person04);

		ParkingUser test = testSpot.getUser();
		
		assertEquals(person04 , test);
		
	}
	
	@Test
	/*
	 * This is for the testing for the removal of a parked user from the spot
	 * 
	 */
	public void test_RemoveUser() {
		
		testSpot.removeAssignedUser();;
		
		ParkingUser test = testSpot.getUser();
		
		assertEquals(null , test);
		
	}
	
	
	@Test
	/*
	 * Testing for the retrieval of the Parking Spot
	 */
	public void test_getParkingSpot_int() {
		
		int test = testSpot.getParkingSpot();
		
		assertEquals(300 , test);
		
	}
	
	@Test
	/*
	 * Testing for the retrieval of the Parking Number
	 */
	public void test_getParkingNUmber_String() {
		
		String test = testSpot.getParkingNumber();
		
		assertEquals(300 , test);
	}
	
	@Test
	/*
	 * Testing for the retrieval of the Parking Number
	 */
	public void test_getFloor() {
		
		int test = testSpot.getFloor();
		
		assertEquals(3 , test);
	}
}

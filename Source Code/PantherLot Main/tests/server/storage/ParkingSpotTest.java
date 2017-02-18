package server.storage;

import static org.junit.Assert.*;

import java.io.PrintWriter;

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
	
	PrintWriter pout;

	
	@Before
	public void setUp(){
		
		testSpot = new ParkingSpot(300 , "Faculty" , 3 , "300" , "South");
		
		person01 = new StudentUser("Carl" , "7654321");
		person02 = new FacultyUser("John" , "1234567");
		person03 = new GuestUser();
		person04 = new HandicappedUser();
		
		testSpot.assignParkingSpot(person01);
		
		pout = new PrintWriter(System.out);

	}//Here we are creating a parking spot
	

	
	@Test
	/*
	 * This is testing the assigning and fetching of a Student User
	 * 
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
	public void test_getParkingSpot() {
		
		int parkingSpot = 300;
		int test = testSpot.getParkingSpot();
		
		assertEquals(parkingSpot , test);
		
	}
	
	@Test
	/*
	 * Testing for the retrieval of the Parking Number
	 */
	public void test_getParkingNUmber() {
		
		String parkingNumber = "300";
		String test = testSpot.getParkingNumber();
		
		assertEquals(parkingNumber , test);
	}
	
	@Test
	/*
	 * Testing for the retrieval of the Parking Floor number
	 */
	public void test_getFloor() {
		
		int parkingFloor = 3;
		int test = testSpot.getFloor();
		
		assertEquals( parkingFloor , test);
	}
	
	@Test
	/*
	 * Testing for the retrieval of the direction of the parking spot
	 * 
	 */
	public void test_getDirections(){
		
		String direction = "South";
		String test = testSpot.getDirections();
		
		assertEquals( direction , test);
	}
	
	@Test
	/*
	 * This is going to test that the correct spot type is being returned
	 * the getParkingType is being used.
	 */
	public void test_getParkingType(){
		
		String desc = "Faculty";
		String test = testSpot.getParkingType();
		
		assertEquals( desc , test );
	}
		
	@Test
	/*
	 * Testing the method 'isAvailable' to see if it returns the correct boolean
	 * values. In this test, a user is being assigned to the parking spot being tested 
	 */
	public void test_isAvailable01(){
		
		testSpot.assignParkingSpot( person01 );
		
		boolean avail = false;
		boolean test = testSpot.isAvailable();
		
		assertEquals( avail , test);
		
	}
	
	@Test
	/*
	 * Testing the method 'isAvailable' to see if it returns the correct boolean
	 * values. In this test, no user is assigned to the parking spot being tested
	 */
	public void test_isAvailable02(){
		
		testSpot.removeAssignedUser();
		
		boolean avail = true;
		boolean test = testSpot.isAvailable();
		
		assertEquals( avail , test);
		
	}
	
	@Test
	public void test_isConnected01(){

		boolean test = testSpot.isConnected();
		
		assertEquals(false , test);
		
	}
	
	@Test
	public void test_isConnected02(){

		testSpot.setPrintWriter(pout);
		
		boolean test = testSpot.isConnected();
		
		assertEquals(true , test);
		
	}
	
	@Test
	/*
	 * This tests createParkingDirections() and
	 */
	public void test_createParkingDirections(){
		
		testSpot.assignParkingSpot( person01 );
		
		String disp = "1. Go to floor #" 							
        		+ "3" + "\n2. Head to the " 
        		+ "South" + " part." +
        		"\n3. Park on " + testSpot.getUser().toString() 
        		+ " spot labeled #" + "300" + ".";
		
		String test = testSpot.createParkingDirections();
		
		assertEquals( disp , test );
		
	}
	
	
}

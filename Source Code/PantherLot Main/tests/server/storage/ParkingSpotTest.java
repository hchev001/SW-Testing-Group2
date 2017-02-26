package server.storage;

import static org.junit.Assert.*;
import java.io.PrintWriter;
import org.junit.Test;
import server.controller.*;
import org.junit.Before;
import org.junit.After;


public class ParkingSpotTest {

	// Variables used throughout the tests
	ParkingSpot testSpot;
	ParkingSpot testSpot2;
	ParkingSpot testSpot3;
	
	ParkingUser person01;
	ParkingUser person02;
	ParkingUser person03;
	ParkingUser person04;
	ParkingUser person05;
	
	PrintWriter pout;

	
	@Before
	/*
	 *  The set up creates 2 Parking Spots and 4 possible users, one of each possible
	 *  type of user. It also assigns a the faculty user into the parking spot.
	 *  Finally, it creates a new PrintWriter.
	 */
	public void setUp(){
		
		testSpot = new ParkingSpot(300 , "Faculty" , 3 , "300" , "South");
		testSpot2 = new ParkingSpot(300 , "Student" , 3 , "300" , "North");
		testSpot3 = new ParkingSpot(200 , "Student" , 2 , "200" , "West");
		
		person01 = new StudentUser("Carl" , "7654321");
		person02 = new FacultyUser("John" , "1234567");
		person03 = new GuestUser();
		person04 = new HandicappedUser();
		
		testSpot.assignParkingSpot(person02);
		
		pout = new PrintWriter(System.out);

	}
	
	@Test
	/*
	 * This is testing the method call '.assignParkingSpot(ParkingUser user)'
	 * and the method call '.getUser()'. It makes sure that the assignment was done
	 * without errors.
	 * 
	 */
	public void Unit001_AssignParkingSpot_01() {
		
		testSpot.assignParkingSpot(person01);

		ParkingUser test = testSpot.getUser();
		
		assertEquals(person01 , test);
		
	}

	@Test
	/*
	 * This is testing the assigning and fetching of a Faculty User
	 * 
	 */
	public void Unit002_AssignParkingSpot_02() {
		
		testSpot.assignParkingSpot(person02);

		ParkingUser test = testSpot.getUser();
		
		assertEquals(person02 , test);
		
	}
	
	@Test
	/*
	 * This is testing the assigning and fetching of a Guest User
	 * 
	 */
	public void Unit003_AssignParkingSpot_03() {
		
		testSpot.assignParkingSpot(person03);

		ParkingUser test = testSpot.getUser();
		
		assertEquals(person03 , test);
		
	}
	
	@Test
	/*
	 * This is testing the assigning and fetching of a Student User
	 * 
	 */
	public void Unit004_AssignParkingSpot_04() {
		
		testSpot.assignParkingSpot(person04);

		ParkingUser test = testSpot.getUser();
		
		assertEquals(person04 , test);
		
	}
	
	@Test
	/*
	 * This is for the testing for the removal of a parked user from the spot
	 * 
	 */
	public void Unit005_RemoveUser() {
		
		testSpot.assignParkingSpot(person01);
		testSpot.removeAssignedUser();;
		
		ParkingUser test = testSpot.getUser();
		
		assertEquals(null , test);
		
	}
	
	
	@Test
	/*
	 * Testing for the retrieval of the Parking Spot 
	 */
	public void Unit006_getParkingSpot() {
		
		int parkingSpot = 200;
		int test = testSpot3.getParkingSpot();
		
		assertEquals(parkingSpot , test);
		
	}
	
	@Test
	/*
	 * Testing for the retrieval of the Parking Number
	 */
	public void Unit007_getParkingNUmber() {
		
		String parkingNumber = "200";
		String test = testSpot3.getParkingNumber();
		
		assertEquals(parkingNumber , test);
	}
	
	@Test
	/*
	 * Testing for the retrieval of the Parking Floor number
	 */
	public void Unit008_getFloor() {
		
		int parkingFloor = 3;
		int test = testSpot.getFloor();
		
		assertEquals( parkingFloor , test);
	}
	
	@Test
	/*
	 * Testing for the retrieval of the direction of the parking spot
	 * 
	 */
	public void Unit009_getDirections(){
		
		String direction = "South";
		String test = testSpot.getDirections();
		
		assertEquals( direction , test);
	}
	
	@Test
	/*
	 * This is going to test that the correct spot type is being returned
	 * the getParkingType is being used.
	 */
	public void Unit010_getParkingType(){
		
		String desc = "Faculty";
		String test = testSpot.getParkingType();
		
		assertEquals( desc , test );
	}
	
	
	@Test
	/*
	 * This tests the methods getPrintWriter() and setPrintWriter(). Tests that the 
	 * assignment and the retrieval of the PrintWriter work properly.
	 * 
	 */
	public void Unit011_get_AND_setPrintWriter(){
		
		testSpot.setPrintWriter(pout);
		
		PrintWriter test = testSpot.getPrintWriter();
		
		assertEquals(pout , test);
	}
		
	@Test
	/*
	 * Testing the method 'isAvailable' to see if it returns the correct boolean
	 * values. In this test, a user is being assigned to the parking spot being tested 
	 */
	public void Unit012_isAvailable01(){
		
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
	public void Unit013_isAvailable02(){
		
		testSpot.removeAssignedUser();
		
		boolean avail = true;
		boolean test = testSpot.isAvailable();
		
		assertEquals( avail , test);
		
	}
	
	@Test
	/*
	 * This tests 'isConnected()' method on a ParkingSpot that is not connected. 
	 */
	public void Unit014_isConnected01(){

		boolean test = testSpot.isConnected();
		
		assertEquals(false , test);
		
	}
	/*
	 * This tests 'isConnected()' method on a ParkingSpot that is connected. 
	 */
	@Test
	public void Unit015_isConnected02(){

		testSpot.setPrintWriter(pout);
		
		boolean test = testSpot.isConnected();
		
		assertEquals(true , test);
		
	}
	
	@Test
	/*
	 * This tests createParkingDirections() and verifies that the 
	 * correct output is returned
	 */
	public void Unit016_createParkingDirections(){
		
		testSpot.assignParkingSpot( person01 );
		
		String disp = "1. Go to floor #" 							
        		+ "3" + "\n2. Head to the " 
        		+ "South" + " part." +
        		"\n3. Park on " + testSpot.getUser().toString() 
        		+ " spot labeled #300.";
		
		String test = testSpot.createParkingDirections();
		
		assertEquals( disp , test );
		
	}
	
	@Test
	/* 
	 * This tests the compareTo() method, it compares the parking numbers and returns '0' 
	 * when they are equal.
	 * 
	 */
	public void Unit017_compateTo_01(){
		
		int test = testSpot.compareTo(testSpot2);
		int result = 0;
		
		assertTrue(result == test); 
	
	}
	
	@Test
	/*
	 * This tests the 'compateTo()' method. This test asserts that the 2 items are not equal
	 * by testing if the comparison does not equal 0
	 */
	public void Unit018_compareTo_02(){
		
		int result = 0;
		int test = testSpot.compareTo(testSpot3);
		
		assertFalse(result == test);
		
	}
	
	@Test
	/*
	 * This tests the method 'sFormat()' which does a call to 'format()' which is private
	 * This verifies that the formatting was done correctly.
	 */
	public void Unit019_formatTest(){
		
		String result = "Hello    "; //The word 'Hello' followed by 4 spaces.
		int strLength = 9;//Total length of 'Hello' followed by 4 spaces.
		
		String test = ParkingSpot.sFormat("Hello" , strLength);
		
		assertEquals( result , test);
		
	}
	
	@Test
	/*
	 * This will test that the toString() method returns the correct output
	 * when the method is called on a ParkingSpot that is available and not connected.
	 */
	public void Unit020_toString01(){
		
		
		String wrd = "200 Parking #200  "
				+ "Type:Student     Floor:2  "
				+ "Direction:West  Status:Free  "
				+ "Connection:Off";
		
		String test = testSpot3.toString();
		
		assertEquals(wrd , test);
		
	}
	
	@Test
	/*
	 * This will test that the toString() method returns the correct output
	 * when the method is called on a ParkingSpot that is not
	 * available and not connected.
	 */
	public void Unit021_toString02(){
		
		
		String wrd = "200 Parking #200  "
				+ "Type:Student     Floor:2  "
				+ "Direction:West  Status:Taken "
				+ "Connection:Off";
		
		testSpot3.assignParkingSpot(person01);
		String test = testSpot3.toString();
		
		assertEquals(wrd , test);
		
	}
	
	@Test
	/*
	 * This will test that the toString() method returns the correct output
	 * when the method is called on a ParkingSpot that is available and connected.
	 */
	public void Unit022_toString03(){
		
		
		String wrd = "200 Parking #200  "
				+ "Type:Student     Floor:2  "
				+ "Direction:West  Status:Free  "
				+ "Connection:On";
		
		testSpot3.setPrintWriter(pout);
		String test = testSpot3.toString();
		
		assertEquals(wrd , test);
		
	}
	
	@Test
	/*
	 * This will test that the toString() method returns the correct output
	 * when the method is called on a ParkingSpot that is 
	 * not available and connected.
	 */
	public void Unit023_toString04(){
		
		
		String wrd = "200 Parking #200  "
				+ "Type:Student     Floor:2  "
				+ "Direction:West  Status:Taken "
				+ "Connection:On";
		
		testSpot3.setPrintWriter(pout);
		testSpot3.assignParkingSpot(person01);
		
		String test = testSpot3.toString();
		
		assertEquals(wrd , test);
		
	}
	
	@After
	public void tearDown(){
		//enter code here
	}
	
}

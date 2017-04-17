package server.storage;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;
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
	 * Test Case ID:	Unit001_AssignParkingSpot_01()
	 * 
	 * Purpose:	To test the assignment of a User of type 'Student’ to a ParkingSpot object 
	 * 			with the method ‘.assignParkingSpot()’ (This method does not compare that 
	 * 			the User type matches with the Parking spot type) and the method call 
	 * 			'.getUser()'. It makes sure that the assignment was done without errors.
	 * 
	 * Input:	-Invoke the method ‘.assignParkingSpot()’ on ParkingSpot labled ‘testSpot’ with a 
	 * 			 ParkingUser as a parameter 
	 * 			-The ParkingUser should be ‘person01’ from the test setup
	 * 
	 * Expected Output:	When method ‘.getUser()’ is invoked on ‘testSpot’, ParkingUser labled
	 * 			 ‘person01’ is expected to be returned
	 */
	public void Unit001_AssignParkingSpot_01() {
		
		testSpot.assignParkingSpot(person01);

		ParkingUser test = testSpot.getUser();
		
		assertEquals(person01 , test);
		
	}

	@Test
	/*
	 * Test Case ID:	Unit002_AssignParkingSpot_02()
	 * 
	 * Purpose:	To test the assignment of a User of type ‘Faculty’ to a ParkingSpot 
	 * 			object with the method ‘.assignParkingSpot()’ (This method does not compare 
	 * 			that the User type matches with the Parking spot type).
	 * 
	 * Input:	-Invoke the method ‘.assignParkingSpot()’ on ParkingSpot labeled ‘testSpot’ with 
	 * 			a ParkingUser as a parameter.
	 * 			-The ParkingUser should be ‘person02’ from the test setup.
	 * 
	 * Expected Output: ParkingUser labled ‘person02’ is expected to be returned
	 * 
	 */
	public void Unit002_AssignParkingSpot_02() {
		
		testSpot.assignParkingSpot(person02);

		ParkingUser test = testSpot.getUser();
		
		assertEquals(person02 , test);
		
	}
	
	@Test
	/*
	 * Test Case ID:	Unit003_AssignParkingSpot_03()
	 * 
	 * Purpose:	To test the assignment of a User of type ‘Guest’ to a ParkingSpot 
	 * 			object with the method ‘.assignParkingSpot()’ (This method does not compare 
	 * 			that the User type matches with the Parking spot type).
	 * 
	 * Input:	-Invoke the method ‘.assignParkingSpot()’ on ParkingSpot labeled ‘testSpot’ with a
	 * 			ParkingUser as a parameter.
	 * 			-the ParkingUser should be ‘person03’ from the test setup.
	 * 
	 * Expected Output:	ParkingUser labeled ‘person03’ is expected to be returned
	 * 
	 */
	public void Unit003_AssignParkingSpot_03() {
		
		testSpot.assignParkingSpot(person03);

		ParkingUser test = testSpot.getUser();
		
		assertEquals(person03 , test);
		
	}
	
	@Test
	/*
	 * Test Case ID:	Unit004_AssignParkingSpot_04().
	 * 
	 * Purpose	To test the assignment of a User of type ‘Handicapped’ to a ParkingSpot 
	 * 			object with the method ‘.assignParkingSpot()’ (This method does not compare 
	 * 			that the User type matches with the Parking spot type).
	 * 
	 * Input:	-Invoke the method ‘.assignParkingSpot()’ on ParkingSpot labeled ‘testSpot’ with a 
	 *			ParkingUser as a parameter.
	 *			-the ParkingUser should be ‘person04’ from the test setup.
	 *
	 *Expected Output:	When method ‘.getUser()’ is invoked on ‘testSpot’, ParkingUser labeled 
	 *			‘person04’ is expected to be returned.
	 * 
	 */
	public void Unit004_AssignParkingSpot_04() {
		
		testSpot.assignParkingSpot(person04);

		ParkingUser test = testSpot.getUser();
		
		assertEquals(person04 , test);
		
	}
	
	@Test
	/*
	 * Test Case ID:	Unit005_RemoveUser()
	 * 
	 * Purpose:	To test the removal of a User from a ParkingSpot with the method 
	 * 			‘.removeAssignedUser()’
	 * 
	 * Input:	-Invoke the method ‘.removeAssignedUser()’ on ‘testSpot’
	 * 			(  testSpot.removeAssignedUser()  )
	 * 
	 * Expected Output:	‘null’ is expected to be returned.
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
	 * Test Case ID:	Unit006_getParkingSpot()
	 * 
	 * Purpose:	To test the method ‘.getParkingSpot( )’ and see if it returns the 
	 * 			correct value when Invoked.
	 * 
	 * Input:	-Invoke ‘.getParkingSpot( )’ on testSpot3. ( testSpot3.getParkingSpot( )  )
	 * 
	 * Expected Output:	An int value of 200
	 * 
	 */
	public void Unit006_getParkingSpot() {
		
		int parkingSpot = 200;
		int test = testSpot3.getParkingSpot();
		
		assertEquals(parkingSpot , test);
		
	}
	
	@Test
	/*
	 * Test Case ID:	Unit007_getParkingNUmber()
	 * 
	 * Purpose:	To test the method ‘.getParkingNumber( )’ and see if 
	 * 			it returns the correct value when Invoked.
	 * 
	 * Input:	-Invoke ‘.getParkingNumber( )’ on testSpot3. ( testSpot3.getParkingNumber( )  )
	 * 
	 * Expected Output:	A String value of “200”
	 * 
	 */
	public void Unit007_getParkingNUmber() {
		
		String parkingNumber = "200";
		String test = testSpot3.getParkingNumber();
		
		assertEquals(parkingNumber , test);
	}
	
	@Test
	/*
	 * Test Case ID:	Unit008_getFloor()
	 * 
	 * Purpose:	To test the method ‘.getFloor( )’ and see if it returns the correct value when Invoked.
	 * 
	 * Input:	-Invoke ‘.getFloor( )’ on testSpot. ( testSpot.getFloor( )  )
	 * 
	 * Expected Output:	An int value of 3
	 * 
	 */
	public void Unit008_getFloor() {
		
		int parkingFloor = 3;
		int test = testSpot.getFloor();
		
		assertEquals( parkingFloor , test);
	}
	
	@Test
	/*
	 * Test Case ID:	Unit009_getDirections().
	 * 
	 * Purpose:	To test the method ‘.getDirections( )’ and see if it returns the correct 
	 * 			value when Invoked.
	 * 
	 * Input:	-Invoke ‘.getDirections( )’ on testSpot. - testSpot.getDirections( )
	 * 
	 * Expected Output:	A String with the value “South”
	 * 
	 */
	public void Unit009_getDirections(){
		
		String direction = "South";
		String test = testSpot.getDirections();
		
		assertEquals( direction , test);
	}
	
	@Test
	/*
	 * Test Case ID:	Unit010_getParkingType()
	 * 
	 * Purpose:	To test the method ‘.getParkingType( )’ and see if it returns the 
	 * 			correct value when Invoked.
	 * 
	 * Input:	-Invoke ‘.getParkingType( )’ on testSpot - testSpot.getParkingType( )
	 * 
	 * Expected Output:	A String with the value “Faculty”
	 */
	public void Unit010_getParkingType(){
		
		String desc = "Faculty";
		String test = testSpot.getParkingType();
		
		assertEquals( desc , test );
	}
	
	
	@Test
	/*
	 * Test Case ID:	Unit011_get_AND_setPrintWriter()
	 * 
	 * Purpose:	To test the methods ‘.getPrintWriter( )’ and ‘.setPrintWriter()’. 
	 * 			See if it assigns and returns the correct values when Invoked.
	 * 
	 * Input	-Invoke ‘.setPrintWriter( )’ on testSpot with ‘pout’ as parameter 
	 * 			- testSpot.setPrintWriter(pout).
	 * 
	 * Expected Output:	A String with the value “Faculty”
	 * 
	 */
	public void Unit011_get_AND_setPrintWriter(){
		
		testSpot.setPrintWriter(pout);
		
		PrintWriter test = testSpot.getPrintWriter();
		
		assertEquals(pout , test);
	}
		
	@Test
	/*
	 * Test Case ID:	Unit012_isAvailable01()
	 * 
	 * Purpose:	To test the method ‘.isAvailable()’ on an occupied 
	 * 			Parking Spot and see if it returns “false”.
	 * 
	 * Input	-Assign ‘person01’ to ParkingSpot ‘testSpot’ – testSpot.assignParkingSPot( person01 )
	 * 			-Invoke method ‘.isAvaliable()’ on ‘testSpot’ – testSpot.isAvailable( )
	 * 
	 * Expected Output:	A Boolean with the value “false”
	 * 
	 */
	public void Unit012_isAvailable01(){
		
		testSpot.assignParkingSpot( person01 );
		
		boolean avail = false;
		boolean test = testSpot.isAvailable();
		
		assertEquals( avail , test);
		
	}
	
	@Test
	/*
	 * Test Case ID:	Unit013_isAvailable02()
	 * 
	 * Purpose:	To test the method ‘.isAvailable()’ on an unoccupied Parking Spot and see 
	 * 			if it returns “true”.
	 * 
	 * Input:	-Invoke method ‘.isAvaliable()’ on ‘testSpot’ 
	 * 			– testSpot.isAvailable( )
	 * 
	 * Expected Output:	A Boolean with the value “true”
	 */
	public void Unit013_isAvailable02(){
		
		testSpot.removeAssignedUser();
		
		boolean avail = true;
		boolean test = testSpot.isAvailable();
		
		assertEquals( avail , test);
		
	}
	
	@Test
	/*
	 * Test Case ID:	Unit014_isConnected01()
	 * 
	 * Purpose:	To test the method ‘.isConnected()’ on a Parking Spot that is not connected 
	 * 			and see if it returns “false”
	 * 
	 * Input:	-Invoke method ‘.isAvaliable()’ on ‘testSpot’ – testSpot.isConnected( )
	 * 
	 * Expected Output:	A Boolean with the value “false”
	 */
	
	public void Unit014_isConnected01(){

		boolean test = testSpot.isConnected();
		
		assertEquals(false , test);
		
	}
	/*
	 * Test Case ID:	Unit015_isConnected02()
	 * 
	 * Purpose:	To test the method ‘.isConnected()’ on a Parking Spot that is
	 * 			connected and see if it returns “true”.
	 * 
	 * Input	-invoke method ‘.setPrintWriter()’ with parameter ‘pout’ on ‘testSpot’ –   testSpot.setPrintWriter(pout)
	 * 			-Invoke method ‘.isAvaliable()’ on ‘testSpot’ – testSpot.isConnected( )
	 * 
	 * Expected Output	A Boolean with the value “true”
	 */
	@Test
	public void Unit015_isConnected02(){

		testSpot.setPrintWriter(pout);
		
		boolean test = testSpot.isConnected();
		
		assertEquals(true , test);
		
	}
	
	@Test
	/*
	 * Test Case ID:	Unit016_createParkingDirections()
	 * 
	 * Purpose:	To test the method ‘.createParkingDiections()’ on a Parking Spot and see if the correct directions 
	 * 			to the Parking Spot are given.
	 * 
	 * Input:	-Invoke method ‘.assignParkingUser()’ with ‘person01’ as parameter on ‘testSpot’
	 * 			testSpot.assignParkingUser( person01 )
	 * 			-Invoke method ‘.createParkingDirections()’ on ‘testSpot’ 
	 * 			– testSpot.createParkingDirections( )
	 * 
	 * Expected Output	A String with the value: “ 1. Go to floor #3 
	 * 			2. Head to the South part.
	 * 			3. Park on the Faculty spot labeled #300. ”
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
	 * Test Case ID:	Unit017_compateTo_01()
	 * 
	 * Purpose	To test the method ‘.compareTo()’. We are going to compare two ParkingSpots that are the same and
	 * 			see if we get the correct result.
	 * 
	 * Input:	-Invoke the method ‘.compareTo()’ on ‘testSpot’ with ‘testSpot2’ as a parameter 
	 * 			testSpot.compareTo( testSpot2 )
	 * 
	 * Expected Output: An int value of 0 ( This means that the 2 ParkingSpots are equal )
	 * 
	 */
	public void Unit017_compateTo_01(){
		
		int test = testSpot.compareTo(testSpot2);
		int result = 0;
		
		assertEquals(result , test);
	
	}
	
	@Test
	/*
	 * Test Case ID	Unit018_compareTo_02()
	 * 
	 * Purpose	To test the method ‘.compareTo()’. We are going to compare two ParkingSpots that are the same and
	 * 			see if we get the correct result.
	 * 
	 * Input:	-Invoke the method ‘.compareTo()’ on ‘testSpot’ with ‘testSpot2’ as a parameter 
	 * 			testSpot.compareTo( testSpot3 )
	 * 
	 * Expected Output: An int value that != 0 ( This means that the 2 ParkingSpots are not equal )
	 * 
	 */
	public void Unit018_compareTo_02(){
		
		int result = 0;
		int test = testSpot.compareTo(testSpot3);
		
		assertNotEquals(result , test);
		
	}
	
	@Test
	/*
	 * Test Case ID:	Unit019_formatTest()
	 * 
	 * Purpose:	To test the method ‘.format()’ on a String and see if it is correctly formatted
	 * 
	 * Input:	-Invoke the method ‘.format()’ with ‘wrd’ and ‘strLength’ as parameter and 
	 * 			save it into another String – String wrdFormatted = format(wrd , strLength)
	 * 
	 * Expected Output:	The formatted String ‘wrdFormatted’ which should be: “Hello    “.
	 * 			(The word “Hello” followed by 9 spaces)
	 */
	public void Unit019_formatTest(){
		
		String result = "Hello    "; //The word 'Hello' followed by 4 spaces.
		int strLength = 9;//Total length of 'Hello' followed by 4 spaces.
		
		String test = ParkingSpot.sFormat("Hello" , strLength);
		
		assertEquals( result , test);
		
	}
	
	@Test
	/*
	 * Test Case ID:	Unit020_toString01()
	 * 
	 * Purpose:	To test the ‘toString()’ method on a ParkingSpot that is available and not connected.
	 * 
	 * Input:	Invoke the method ‘.toString()’ on ‘testSpot3’ -  testSpot3.toString()
	 * 
	 * Expected Output:	A String with the value “200 Parking #200  Type:Student     Floor:3 
	 * 			 Direction:West  Status:Free  Connection:Off”
	 * 
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
	 * Test Case ID:	Unit021_toString02()
	 * 
	 * Purpose:	To test the ‘toString()’ method on a ParkingSpot that is not available and not connected.
	 * 
	 * Input:	Invoke the method ‘.toString()’ on ‘testSpot3’ -  testSpot3.toString()
	 * 
	 * Expected Output	A String with the value “200 Parking #200  Type:Student     Floor:3  Direction:West  
	 * 			Status:Taken  Connection:Off”
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
	 * Test Case ID:	Unit022_toString03()
	 * 
	 * Purpose:	To test the ‘toString()’ method on a ParkingSpot that is available and connected.
	 * 
	 * Input:	Invoke the method ‘.toString()’ on ‘testSpot3’ -  testSpot3.toString()
	 * 
	 * Expected Output	A String with the value “200 Parking #200  Type:Student     Floor:3  Direction:West 
	 * 			Status:Free  Connection:On”
	 * 
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
	 * Test Case ID:	Unit023_toString04()
	 * 
	 * Purpose:	To test the ‘toString()’ method on a ParkingSpot that is not available and connected.
	 * 
	 * Input:	Invoke the method ‘.toString()’ on ‘testSpot3’ -  testSpot3.toString()
	 * 
	 * Expected Output:	A String with the value “200 Parking #200  Type:Student     Floor:3  Direction:West
	 * 			Status:Taken  Connection:On”
	 * 
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

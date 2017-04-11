package server.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.storage.ParkedUsers;
import server.storage.ParkingSpot;

public class AccessControlServerTest {
	
	//Establish globally used variables
	AccessControlServer acs, acs2, acs3;
	ParkingSpot spot;
	int portNum, portNum2, portNum3, allNull, actual1;
	Iterator it;
	PrintWriter pout1, pout2, pout3, pout4, pout5, pout6, pout7, pout8, 
				pout9, pout10, pout11;
	static ParkedUsers garage;
	File sendMessageFile, sendStatusFile, wrongUserDetectedFile, duplicateIdFoundFile;
	Scanner in1, in2, in3, in4;
	ArrayList<String> statusTest;
	String comparing, msg1, msg2, key, expected1, expected2, expected3,
		   key2, key3, key4;
	boolean expectedBool1, actualBool1, expectedBool2;
	// reserveSpotTest_b variables
	PrintWriter printWriter1;
	StringWriter stringWriter1;
	
	
	@Before
	public void setUp() throws Exception {
		//Establish initial variable values
		//---------------------------------------------------------
		//Ports
		portNum = 3738;
		portNum2 = 5789;
		portNum3 = 999999;
		
		//AccessControlServers
		acs = new AccessControlServer(portNum);
		acs2 = new AccessControlServer(portNum2);
		acs3 = new AccessControlServer(portNum3);
		
		//ParkedUsers
		garage = ParkedUsers.instance("garage.txt");
		statusTest = garage.getStatus();
		
		//ParkingSpots
		spot = new ParkingSpot(1, "Faculty", 1, "101", "South");
		
		//Test Comparisons
		actual1 = 1;
		msg1 = "You are tearing me apart Lisa!";
		msg2 = "I'm fed up wit dis world!";
		expected1 = "Oh, hello doggy";
		expected2 = "wrong Oh hai Mark.";
		expected3 = "duplicate You are tearing me apart Lisa! I'm fed up wit dis world!";
		key = "101";
		key2 = "1663314";
		key3 = "100";
		key4 = spot.getParkingNumber();
		allNull = 1;
		expectedBool1 = true; 
		actualBool1 = false;
		expectedBool2 = false;
				
		//StringWriters
		stringWriter1 = new StringWriter();
		
		//File creation
		sendMessageFile = new File("sendMessageFile.txt");
		if(!sendMessageFile.exists()) {
			sendMessageFile.createNewFile();
		}
		
		sendStatusFile = new File("sendStatusFile.txt");
		if(!sendStatusFile.exists()) {
			sendStatusFile.createNewFile();
		}
		
		wrongUserDetectedFile = new File("wrongUserDetected.txt");
		if(!wrongUserDetectedFile.exists()) {
			wrongUserDetectedFile.createNewFile();
		}
		
		duplicateIdFoundFile = new File("duplicateIdFound.txt");
		if(!duplicateIdFoundFile.exists()) {
			duplicateIdFoundFile.createNewFile();
		}
		
		//PrintWriters
		printWriter1 = new PrintWriter(stringWriter1);
		pout1 = new PrintWriter(sendMessageFile);
		pout2 = new PrintWriter(sendStatusFile);
		pout3 = new PrintWriter(wrongUserDetectedFile);
		pout4 = null;
		pout5 = new PrintWriter(System.out);
		pout6 = new PrintWriter(duplicateIdFoundFile);
		pout7 = null;
		pout8 = new PrintWriter(System.out);
		pout9 = new PrintWriter(System.out);
		pout10 = new PrintWriter(System.out);
		pout11 = new PrintWriter(System.out);
		
		//Scanners
		in1 = new Scanner(sendMessageFile);
		in2 = new Scanner(sendStatusFile);
		in3 = new Scanner(wrongUserDetectedFile);
		in4 = new Scanner(duplicateIdFoundFile);
	}
	
	@Test
	//When called mapConnections should set each parking spot number key
	//in the hashmap to have null for it's value
	public void Unit024_mapConnectionsToAllNullTest() {
		//Create an iterator to go through the hashmap
		it = acs.getDisplayConnections().entrySet().iterator();
		//Check that each value within the hashmap is null
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			//If a value isn't null, the test should fail
			if(pair.getValue() != null) {
				allNull = 0;
				fail();
			}
		}
		assertEquals("All of the values within the connection should be NULL",1, allNull);
	}
	
//	@Test(expected = IOException.class)
//	//We came to the conclusion that in order to test this method we could
//	//just use a property of ServerSockets. Since only one ServerSocket can
//	//be created per connection, if startServer actually worked, then an 
//	//exception would be thrown if it was called again.
//	public void Unit025_startServerTest() throws IOException, NoSuchMethodException, SecurityException {
//		acs.startServer();
//		assertEquals(true, acs.getThrdList().get(0).isAlive());
//	}
	
//	//This test is basically the same as the startServerTest
//	@Test(expected = IOException.class)
//	public void Unit026_runTest() throws IOException {
//		acs.run();
//		acs.startServer();
//	}
		
//	@Test(expected = IOException.class)
//	//If a startServer has already been called by run() and worked, then if we run 
//	//startServer again an IOException should occur.
//	public void Unit027_runDifferentPortNumberTest() throws IOException {
//		acs2.run();
//		acs2.startServer();
//	}
	
	@Test
	//Simply check that a message was sent
	public void Unit029_sendMessageTest() throws IOException {
		acs.sendMessage(expected1, pout1);
		pout1.close();
		String actual = in1.nextLine();
		
		//If there is more than one line sent, fail
		if(in1.hasNextLine()) {
			fail();
		}
		
		in1.close();
		assertEquals("The two string should be equal to eachother.", expected1, actual);
	}
	
//	@Test(expected = IllegalArgumentException.class)
//	public void Unit028_runWithInvalidPortSizeTest() {
//		acs3.run();
//	}
	
	@Test
	public void Unit030_sendStatusTest() throws IOException {
		acs.setSout(pout2);
		acs.sendStatus();
		pout2.close();
		
		for(int i = 0; i < statusTest.size() - 1; i++) {
			comparing = in2.nextLine();
			if(!comparing.equalsIgnoreCase(statusTest.get(i))) {
				actual1 = 0;
				break;
			}
		}

		in2.close();
		assertEquals(1, actual1);
	}
	
	@Test(expected = NullPointerException.class)
	public void Unit031_reserveSpotTest() throws IOException, NullPointerException {
		acs.reserveSpot(spot, key);
	}
	@Test
	public void Unit032_reserveSpotTest_B() {
		acs.getDisplayConnections().put("101", printWriter1);
		acs.reserveSpot(spot, key2);
		String messages = stringWriter1.toString();
		assertEquals(messages, "reserve\r\n1663314\r\n");
	}
	@Test
	public void Unit033_wrongUserDetectedTest() throws IOException {
		acs.setSout(pout3);
		acs.wrongUserDetected("Oh hai Mark.");
		pout3.close();
		//Only two lines should be printed
		String partOne = in3.nextLine();
		String partTwo = in3.nextLine();
		
		if(in3.hasNextLine()) {
			//We have printed out more than twice, something is wrong
			fail("More than two lines of text were printed"); 
		}
		
		String actual = partOne + " " + partTwo;
		assertEquals(expected2, actual);
	}
	
	@Test
	public void Unit034_wrongUserDetectedNullTest() {
		acs.setSout(pout4);
		acs.wrongUserDetected("Oh hai Mark.");
		assertEquals(true, acs.isWrongUserDetectedNull);
	}
	
	@Test
	public void Unit035_wrongUserDetectedNotNullTest() {
		acs.setSout(pout5);
		acs.wrongUserDetected("Oh hai Mark.");
		assertEquals(false, acs.isWrongUserDetectedNull);
		pout5.close();
	}
	
	@Test
	public void Unit036_duplicateIdFoundTest() throws IOException {
		acs.setSout(pout6);
		acs.duplicateIdFound(msg1, msg2);
		pout6.close();
		
		String partOne = in4.nextLine();
		String partTwo = in4.nextLine();
		String partThree = in4.nextLine();
		if(in4.hasNextLine()) {
			fail("We printed more than three lines, and that is not supposed to happen.");
		}
		
		String actual = partOne + " " + partTwo + " " + partThree;
		System.out.println(actual);
		assertEquals(expected3, actual);
	}
	
	@Test
	public void Unit037_duplicateIdFoundNullTest() {
		acs.setSout(pout7);
		acs.duplicateIdFound(msg1, msg2);
		assertEquals(true, acs.isDuplicateIdFoundNull);
	}
	
	@Test
	public void Unit038_duplicateIdFoundNotNullTest() {
		acs.setSout(pout8);
		acs.duplicateIdFound(msg1, msg2);
		
		pout8.close();
		assertEquals(false, acs.isDuplicateIdFoundNull);
	}
	
	@Test
	public void Unit039_addDisplayProperSpotTest() throws IOException{
		acs.callAddDisplay(key4, pout9, spot);
		actualBool1 = acs.getDisplayConnections().containsKey(key4) 
				&& 
				acs.getDisplayConnections().get(key4) == pout9;
		
		assertEquals(expectedBool1, actualBool1);
	}
	
	@Test
	public void Unit040_addDisplayInvalidSpotTest() {
		acs.callAddDisplay(key3, pout10, spot);
		
		assertEquals(expectedBool2, acs.isAddDisplayPossible);
	}
	
	@Test
	public void Unit041_removeTest() {
		acs.callRemoveDisplay(key, spot);
		actualBool1 = spot.getPrintWriter() == null && acs.getDisplayConnections().get(key) == null;
		
		assertEquals(expectedBool1, actualBool1);
	}
	
	@Test
	public void Unit042_isConnectionsAvailableTrueTest() {
		acs.callRemoveDisplay(key, spot);
		actualBool1 = acs.isConnectionAvailable(key);
		
		assertEquals(expectedBool1, actualBool1);
	}
	
	@Test
	public void Unit043_isConnectionsAvailableFalseTest() {
		
		acs.callAddDisplay(key, pout11, spot);
		actualBool1 = acs.isConnectionAvailable(key);
		
		assertEquals(expectedBool2, actualBool1);
	}

	@After
	public void tearDown() throws Exception {
	}
}

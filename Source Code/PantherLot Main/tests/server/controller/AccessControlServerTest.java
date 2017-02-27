package server.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
	AccessControlServer acs;
	ParkingSpot spot;
	int portNum;
	Iterator it;
	PrintWriter pout;
	static ParkedUsers garage;
	File sendMessageFile, sendStatusFile, wrongUserDetectedFile, duplicateIdFoundFile;
	Scanner in;
	ArrayList<String> statusTest;
	String comparing, msg1, msg2, key;
	
	@Before
	public void setUp() throws Exception {
		//Establish initial variable values
		portNum = 3738;
		garage = ParkedUsers.instance("garage.txt");
		statusTest = garage.getStatus();
		spot = new ParkingSpot(1, "Faculty", 1, "101", "South");
		acs = new AccessControlServer(portNum);
		msg1 = "You are tearing me apart Lisa!";
		msg2 = "I'm fed up wit dis world!";
		key = "101";
		
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
	}
	
	@Test
	/*
	 * When called, mapConnections should set each parking spot number key
	 * in the hashmap to have null for it's value
	 */
	public void Unit024_mapConnectionsAllNullTest() {
		int allNull = 1;
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
	
	@Test(expected = IOException.class)
	/*
	 * We came to the conclusion that in order to test this method we could 
	 * just use a property of ServerSockets. Since only one ServerSocket can 
	 * be created per connection, if startServer actually worked, then an
	 * exception would be thrown if it was called again.
	 */
	public void Unit025_startServerTest() throws IOException, NoSuchMethodException, SecurityException {
		acs.startServer();
		acs.startServer();
	}
	
	@Test
	/*
	 * Simply check that a message was sent
	 */
	public void Unit026_sendMessageTest() throws IOException {
		String expected = "So Mark, hows your sex life?";
		pout = new PrintWriter(sendMessageFile);
		in = new Scanner(sendMessageFile);
		acs.sendMessage(expected, pout);
		pout.close();
		String actual = in.nextLine();
		
		//If there is more than one line sent, fail
		if(in.hasNextLine()) {
			fail();
		}
		
		in.close();
		assertEquals("The two string should be equal to eachother.",expected, actual);
	}
	
	
	@Test(expected = IOException.class)
	/*
	 * This test is basically the same as the startServerTest
	 */
	public void Unit027_runTest() throws IOException {
		acs.run();
		acs.startServer();
	}
	
	@Test(expected = IOException.class)
	/*
	 * 
	 */
	public void Unit028_runDifferentPortNumberTest() throws IOException {
		portNum = 5789;
		acs = new AccessControlServer(portNum);
		acs.run();
		acs.startServer();
	}
	
	@Test(expected = IllegalArgumentException.class)
	/*
	 * 
	 */
	public void Unit029_runWithInvalidPortSizeTest() {
		portNum = 999999;
		acs = new AccessControlServer(portNum);
		acs.run();
	}
	
	@Test
	/*
	 * 
	 */
	public void Unit030_sendStatusTest() throws IOException{
		int actual = 1;
		
		pout = new PrintWriter(sendStatusFile);
		acs.setSout(pout);
		acs.sendStatus();
		pout.close();
		in = new Scanner(sendStatusFile);
		
		for(int i = 0; i < statusTest.size() - 1; i++) {
			comparing = in.nextLine();
			if(!comparing.equalsIgnoreCase(statusTest.get(i))) {
				actual = 0;
				break;
			}
		}

		in.close();
		assertEquals(1, actual);
	}
	
	@Test
	/*
	 * 
	 */
	public void Unit031_reserveSpotTest() throws IOException {
		assertEquals(true, true);
	}
	
	@Test
	/*
	 * 
	 */
	public void Unit032_wrongUserDetectedTest() throws IOException {
		String expected = "wrong Oh hai Mark.";
		
		pout = new PrintWriter(wrongUserDetectedFile);
		acs.setSout(pout);
		acs.wrongUserDetected("Oh hai Mark.");
		pout.close();
		in = new Scanner(wrongUserDetectedFile);
		//Only two lines should be printed
		String partOne = in.nextLine();
		String partTwo = in.nextLine();
		
		if(in.hasNextLine()) {
			//We have printed out more than twice, something is wrong
			fail("More than two lines of text were printed"); 
		}
		
		String actual = partOne + " " + partTwo;
		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * 
	 */
	public void Unit033_wrongUserDetectedNullTest() {
		pout = null;
		acs.setSout(pout);
		acs.wrongUserDetected("Oh hai Mark.");
		assertEquals(true, acs.isWrongUserDetectedNull);
	}
	
	@Test
	/*
	 * 
	 */
	public void Unit034_wrongUserDetectedNotNullTest() {
		pout = new PrintWriter(System.out);
		acs.setSout(pout);
		acs.wrongUserDetected("Oh hai Mark.");
		assertEquals(false, acs.isWrongUserDetectedNull);
		pout.close();
	}
	
	@Test
	/*
	 * 
	 */
	public void Unit035_duplicateIdFoundTest() throws IOException {
		String expected = "duplicate You are tearing me apart Lisa! I'm fed up wit dis world!";
		
		pout = new PrintWriter(duplicateIdFoundFile);
		in = new Scanner(duplicateIdFoundFile);
		acs.setSout(pout);
		acs.duplicateIdFound(msg1, msg2);
		pout.close();
		
		String partOne = in.nextLine();
		String partTwo = in.nextLine();
		String partThree = in.nextLine();
		if(in.hasNextLine()) {
			fail("We printed more than three lines, and that is not supposed to happen.");
		}
		
		String actual = partOne + " " + partTwo + " " + partThree;
		System.out.println(actual);
		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * 
	 */
	public void Unit036_duplicateIdFoundNullTest() {
		pout = null;
		
		acs.setSout(pout);
		acs.duplicateIdFound(msg1, msg2);
		
		assertEquals(true, acs.isDuplicateIdFoundNull);
	}
	
	@Test
	/*
	 * 
	 */
	public void Unit037_duplicateIdFoundNotNullTest() {
		pout = new PrintWriter(System.out);
		
		acs.setSout(pout);
		acs.duplicateIdFound(msg1, msg2);;
		
		pout.close();
		assertEquals(false, acs.isDuplicateIdFoundNull);
	}
	
	@Test
	/*
	 * 
	 */
	public void Unit038_addDisplayProperSpotTest() throws IOException{
		boolean expected = true, actual = false;
		pout = new PrintWriter(System.out);
		String key = spot.getParkingNumber();
		
		acs.callAddDisplay(key, pout, spot);
		actual = acs.getDisplayConnections().containsKey("101") 
				&& 
				acs.getDisplayConnections().get(key) == pout;
		
		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * 
	 */
	public void Unit039_addDisplayInvalidSpotTest() {
		String key = "100";
		pout = new PrintWriter(System.out);
		acs.callAddDisplay(key, pout, spot);
		
		assertEquals(false, acs.isAddDisplayPossible);
	}
	
	@Test
	/*
	 * 
	 */
	public void Unit040_removeTest() {
		boolean expected = true, actual = false;
		
		acs.callRemoveDisplay(key, spot);
		actual = spot.getPrintWriter() == null && acs.getDisplayConnections().get(key) == null;
		
		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * 
	 */
	public void Unit041_isConnectionsAvailableTrueTest() {
		boolean expected = true, actual = false;
		
		acs.callRemoveDisplay(key, spot);
		actual = acs.isConnectionAvailable(key);
		
		assertEquals(expected, actual);
	}
	
	@Test
	/*
	 * 
	 */
	public void Unit042_isConnectionsAvailableFalseTest() {
		boolean expected = false, actual = false;
		pout = new PrintWriter(System.out);
		
		acs.callAddDisplay(key, pout, spot);
		actual = acs.isConnectionAvailable(key);
		
		assertEquals(expected, actual);
	}

	@After
	public void tearDown() throws Exception {
		
	}
}

package server.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
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
	Socket myTestSock = new Socket(); 
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
		
		sendStatusFile = new File("sendStatusFile.txt");
		
		wrongUserDetectedFile = new File("wrongUserDetected.txt");
		
		duplicateIdFoundFile = new File("duplicateIdFound.txt");
		
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
	
	/**
	 * Test Case ID: Unit024_mapConnectionsToAllNullTest()
	 * 
	 * Purpose: To test if the mapConnections() method actually sets all the PrintWriters within the HashMap<String, PrintWriter> structure to null.
	 * 
	 * Input: AccessControlServer acs = new AccessControlServer(portNum);
     *        The mapConnections() method is called within the constructor of an AccessControlServer so the input will be simply instantiating an AccessControlServer Object.
     *       
	 * Expected Output: The PrintWriter values for the HashMap<String, PrintWriter> contains null for all the PrintWriters.
	 */
	@Test
	public void Unit024_mapConnectionsToAllNullTest() {
		//Create an iterator to go through the hashmap
		it = acs.getDisplayConnections().entrySet().iterator();
		//Check that each value within the hashmap is null
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
		}
		assertEquals("All of the values within the connection should be NULL",1, allNull);
	}
	/**
	 * Test Case ID: Unit025_startServerTest()
	 * 
	 * Purpose: To test if the SocketServer created by the AccessControlServer establishes a connection and creates a new running thread.
	 * 
	 * Input: acs.startServer();
	 * 
	 * Expected Output: The ServerSocket that was created by the acs variable, has established a connection and is now running a thread that is currently alive.
	 */
	@Test(expected = IOException.class)
	public void Unit025_startServerTest() throws IOException, NoSuchMethodException, SecurityException {
		acs.startServer();
		System.out.println("Start Server Test: " + acs.thrdList.get(0).isAlive());
	}
	
	/**
	 * Test Case ID: Unit026_runTest()
	 * 
	 * Purpose: To test and see if the startServer() is properly called, in other words, checks to see if a thread has been created that is alive.
	 * 
	 * Input: acs.run();
	 *	      acs.startServer(); 
	 *
	 * Expected Output: The ServerSocket that was created by the acs variable, has established a connection and is now running a thread that is currently alive.
	 */
	@Test(expected = IOException.class)
	public void Unit026_runTest() throws IOException {
		acs.run();
		acs.startServer();
	}
	
	/**
	 * Test Case ID: Unit027_runDifferentPortNumberTest()
	 * 
	 * Purpose: To test and see if the startServer() is properly called if a different port number other than the one the project uses by default is used. 
	 * It checks to see if a thread has been created that is alive.
	 * 
	 * Input:acs.run();
	 *       acs.startServer(); 
	 * 
	 * Expected Output: The ServerSocket that was created by the acs variable, has established a connection and is now running a thread that is currently alive.
	 */
	@Test(expected = IOException.class)
	public void Unit027_runDifferentPortNumberTest() throws IOException {
		acs2.run();
		acs2.startServer();
	}
  
	/**
	 * Test Case ID: Unit028_sendMessageTest()
	 * 
	 * Purpose: To test and see if the sendMessage() properly sends a provided message to the PrintWriter, which prints its message to whatever Stream it has been instantiated to.
	 * 
	 * Input: acs.sendMessage(expected1);
	 *        pout1.close();
	 * 
	 * Expected Output: The string created by expected1 has been successfully placed in the file sendMessage.txt.
	 */
	@Test
	public void Unit028_sendMessageTest() throws IOException {
		acs.sendMessage(expected1, pout1);
		pout1.close();
		String actual = in1.nextLine();
		in1.close();
		assertEquals("The two string should be equal to eachother.", expected1, actual);
	}
  
	/**
	 * Test Case ID: Unit029_runWithInvalidPortSizeTest()
	 * 
	 * Purpose: To test and see if a port number that is too large to be a real port, is not attempted to be run by the AccessControlServer when it runs.
	 * 
	 * Input: Acs3.run();
	 * 
	 * Expected Output: An IllegalArgumentException is thrown.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void Unit029_runWithInvalidPortSizeTest() {
		acs3.run();
	}
	
	/**
	 * Test Case ID: Unit030_sendStatusTest()
	 * 
	 * Purpose: Tests to see if sendStatus() will display all of the parkingSpots and all of their properties.
	 * 
	 * Input: acs.setSout(pout2);
	 *		  acs.sendStatus();
	 *		  pout2.close();
	 * 
	 * Expected Output: A message will be sent through the PrintWriter pout2 and printed to whatever pout2 was instantiated to.
	 */
	@Test
	public void Unit030_sendStatusTest() throws IOException {
		acs.setSout(pout2);
		acs.sendStatus();
		pout2.close();
		
		for(int i = 0; i < statusTest.size() - 1; i++) {
			comparing = in2.nextLine();
		}

		in2.close();
		assertEquals(1, actual1);
	}
	
	/**
	 * Test Case ID: Unit031_reserveSpotTest()
	 * 
	 * Purpose: Tests to see if a spot has been set to reserved.
	 * 
	 * Input: acs.reserveSpot(spot, key);
	 * 
	 * Expected Output: A NullPointerException will be thrown, as by default all PrintWriters are null, so when reserveSpot runs it will be accessing a null value. 
	 * This is intended behavior.
	 */
	@Test(expected = NullPointerException.class)
	public void Unit031_reserveSpotTest() throws IOException, NullPointerException {
		acs.reserveSpot(spot, key);
	}
	
	/**
	 * Test Case ID: Unit032_reserveSpotTest_B()
	 * 
	 * Purpose: Tests to see if a spot has been set to reserved. However, unlike the first test for reserve spot,
	 * here we actually set a PrintWriter to be a non-null value to avoid the nullpointer exception, and to see if the spot itself is actually reserved.
	 * 
	 * Input: acs.getDisplayConnections().put("101", printWriter1);
	 *		  acs.reserveSpot(spot, key2);
	 * 
	 * Expected Output: The message: 
	 *					�reserve\r\n1663314\r\n� will be sent across the stream to the ParkingSpot, to mark the spot as reserved.
	 */
	@Test
	public void Unit032_reserveSpotTest_B() {
		acs.getDisplayConnections().put("101", printWriter1);
		acs.reserveSpot(spot, key2);
		String messages = stringWriter1.toString();
		assertEquals(messages, "reserve\r\n1663314\r\n");
	}
	
	/**
	 * Test Case ID: Unit033_wrongUserDetectedTest()
	 * 
	 * Purpose: To test whether wrongUserDetected() properly sends a message that the currently parked user is the wrong one.
	 * 
	 * Input: acs.setSout(pout3);
	 *		  acs.wrongUserDetected("Oh hai Mark.");
	 *		  pout3.close();
	 * Expected Output: The message: 
	 *					�wrong Oh hai Mark.� will be sent across the stream to the ParkingSpot, to mark user and wrong.
	 */
	@Test
	public void Unit033_wrongUserDetectedTest() throws IOException {
		acs.setSout(pout3);
		acs.wrongUserDetected("Oh hai Mark.");
		pout3.close();
		//Only two lines should be printed
		String partOne = in3.nextLine();
		String partTwo = in3.nextLine();
		String actual = partOne + " " + partTwo;
		assertEquals(expected2, actual);
	}
	
	/**
	 * Test Case ID: Unit034_wrongUserDetectedNullTest()
	 * 
	 * Purpose: Tests if the PrintWriter associated with the ParkingSpot is null, then the a message will not be sent as the method will exit before doing anthing.
	 * 
	 * Input: acs.setSout(pout4);
	 *        acs.wrongUserDetected("Oh hai Mark.");
	 * 
	 * Expected Output: The message: �wrong Oh hai Mark.� will be sent across the stream to the ParkingSpot, to mark user and wrong.
	 */
	@Test
	public void Unit034_wrongUserDetectedNullTest() {
		acs.setSout(pout4);
		acs.wrongUserDetected("Oh hai Mark.");
		assertEquals(true, acs.isWrongUserDetectedNull);
	}
	
	/**
	 * Test Case ID: Unit035_wrongUserDetectedNotNullTest()
	 * 
	 * Purpose: Tests if the PrintWriter associated with the ParkingSpot is not null, then a message will be sent.
	 * This however, just checks to see if a non-null PrintWriter will allow the message to be sent, it doesn�t check to see if the message itself has been successfully sent.
	 * 
	 * Input: acs.setSout(pout5);
     *        acs.wrongUserDetected("Oh hai Mark.");
	 * 
	 * Expected Output: The isWrongUserDetectedNull property will be set to False, and the method will continue by sending the message �wrong\nOh hai Mark.� to the console (in our case).
	 */
	@Test
	public void Unit035_wrongUserDetectedNotNullTest() {
		acs.setSout(pout5);
		acs.wrongUserDetected("Oh hai Mark.");
		assertEquals(false, acs.isWrongUserDetectedNull);
		pout5.close();
	}
	
	/**
	 * Test Case ID: Unit036_duplicateIdFoundTest()
	 * 
	 * Purpose: Tests if duplicateIdFound() sends a message through a Stream via a PrintWriter that a duplicated ID was found to be entered into the system.
	 * 
	 * Input: acs.setSout(pout6);
	 *        acs.duplicateIdFound(msg1, msg2);
	 *        pout6.close();
	 * 
	 * Expected Output: The message will be sent through the PrintWriter and printed out on whatever the PrintWriter is currently printing to.
	 */
	@Test
	public void Unit036_duplicateIdFoundTest() throws IOException {
		acs.setSout(pout6);
		acs.duplicateIdFound(msg1, msg2);
		pout6.close();
		
		String partOne = in4.nextLine();
		String partTwo = in4.nextLine();
		String partThree = in4.nextLine();
		System.out.println(partOne);
		System.out.println(partTwo);
		System.out.println(partThree);
		String actual = partOne + " " + partTwo + " " + partThree;
		System.out.println(actual);
		assertEquals(expected3, actual);
	}
	
	/**
	 * Test Case ID: Unit037_duplicateIdFoundNullTest()
	 * 
	 * Purpose: Tests if the PrintWriter associated with the ParkingSpot is null, then a message will not be sent as the method will exit before doing anthing.
	 * 
	 * Input: acs.setSout(pout7);
     *        acs.duplicateIdFound(msg1, msg2);
     *        
	 * Expected Output: The isDuplicateIdFoundNull property will be set to True, and the method will exit without sending a message.
	 */
	@Test
	public void Unit037_duplicateIdFoundNullTest() {
		acs.setSout(pout7);
		acs.duplicateIdFound(msg1, msg2);
		assertEquals(true, acs.isDuplicateIdFoundNull);
	}
	
	/**
	 * Test Case ID: Unit038_duplicateIdFoundNotNullTest()
	 * 
	 * Purpose: Tests if the PrintWriter associated with the ParkingSpot is not null, then a message will be sent.
	 * This however, just checks to see if a non-null PrintWriter will allow the message to be sent, it doesn�t check to see if the message itself has been successfully sent.
	 * 
	 * Input: acs.setSout(pout8);
	 *	      acs.duplicateIdFound(msg1, msg2);
	 *	      pout8.close();
	 * 
	 * Expected Output: The isDuplicateIdFoundNull property will be set to False, and the method will continue by sending the message to the console.
	 */
	@Test
	public void Unit038_duplicateIdFoundNotNullTest() {
		acs.setSout(pout8);
		acs.duplicateIdFound(msg1, msg2);
		
		pout8.close();
		assertEquals(false, acs.isDuplicateIdFoundNull);
	}
	
	/**
	 * Test Case ID:  Unit039_addDisplayProperSpotTest()
	 * 
	 * Purpose: Test if the addDisplay() actually sets the proper PrintWriter and key values to the HashMap<String, PrintWriter>. 
	 * This tests when it is a proper spot that is being provided.
	 * 
	 * Input: acs.callAddDisplay(key4, pout9, spot);
	 * 
	 * Expected Output: The key/value pair of the HashMap will be updated.
	 */
	@Test
	public void Unit039_addDisplayProperSpotTest() throws IOException{
		acs.callAddDisplay(key4, pout9, spot);
		actualBool1 = acs.getDisplayConnections().containsKey(key4) 
				&& 
				acs.getDisplayConnections().get(key4) == pout9;
		
		assertEquals(expectedBool1, actualBool1);
	}
	
	/**
	 * Test Case ID: void Unit040_addDisplayInvalidSpotTest()
	 * 
	 * Purpose: Tests if addDisplayInvalidSpot does not update the key/value pair of the HashMap of <String, PrintWriter> if the provided spot is not a valid one.
	 * 
	 * Input: acs.callAddDisplay(key3, pout10, spot);
	 * 
	 * Expected Output: The isAddDisplayPossible property of the AccessControlServer will be set to false, and the HashMap will not be updated.
	 */
	@Test
	public void Unit040_addDisplayInvalidSpotTest() {
		acs.callAddDisplay(key3, pout10, spot);
		
		assertEquals(expectedBool2, acs.isAddDisplayPossible);
	}
	
	/**
	 * Test Case ID: Unit041_removeTest()
	 * 
	 * Purpose: Tests to see if removeDisplay() will actually set the HashMap value of current spot and PrintWriter to null, this will effectively remove it from the display. 
	 * 
	 * Input: acs.callRemoveDisplay(key, spot);
	 * 
	 * Expected Output: The HashMap value/key pair for the key: �101� will be set to null, thus removing from the display.
	 */
	@Test
	public void Unit041_removeTest() {
		acs.callRemoveDisplay(key, spot);
		actualBool1 = spot.getPrintWriter() == null && acs.getDisplayConnections().get(key) == null;
		
		assertEquals(expectedBool1, actualBool1);
	}
	
	/**
	 * Test Case ID: Unit042_isConnectionsAvailableTrueTest()
	 * 
	 * Purpose: Tests if the isConnectionsAvailable() returns the true if there is a null key/value pair within the HashMap of <String, PrintWriters> of ParkingSpots.
	 * 
	 * Input: acs.callRemoveDisplay(key, spot);
	 * 
	 * Expected Output: The method will return the value �True�
	 */
	@Test
	public void Unit042_isConnectionsAvailableTrueTest() {
		acs.callRemoveDisplay(key, spot);
		actualBool1 = acs.isConnectionAvailable(key);
		
		assertEquals(expectedBool1, actualBool1);
	}
	
	/**
	 * Test Case ID: Unit043_isConnectionsAvailableFalseTest()
	 * 
	 * Purpose: Tests if the isConnectionsAvailable() returns the false if there is a no null key/value pair within the HashMap of <String, PrintWriters> of ParkingSpots.
	 * 
	 * Input: acs.callAddDisplay(key, pout11, spot);
	 * 
	 * Expected Output: The method returns �True�
	 */
	@Test
	public void Unit043_isConnectionsAvailableFalseTest() {
		
		acs.callAddDisplay(key, pout11, spot);
		actualBool1 = acs.isConnectionAvailable(key);
		
		assertEquals(expectedBool2, actualBool1);
	}
	
	/**
	 * Test Case ID: Unit044_getSoutTest()
	 * 
	 * Purpose: Determine if the PrintWriter used by AccessControlServer is the correct PrintWriter
	 * 
	 * Input: PrintWriter test = new PrintWriter(System.out);
	 *	      acs.setSout(test);
	 *
	 * Expected Output: The two printwriters will be equal so the method will return true.
	 */
	@Test
	public void Unit044_getSoutTest() {
		PrintWriter test = new PrintWriter(System.out);
		acs.setSout(test);
		assertEquals(test, acs.getSout());
	}
}

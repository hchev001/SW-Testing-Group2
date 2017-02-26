package server.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.controller.AccessControlServer;
import server.storage.ParkedUsers;

public class AccessControlServerTest {
	
	AccessControlServer acs;
	int portNum;
	Iterator it;
	PrintWriter pout;
	static ParkedUsers garage;
	File sendMessageFile, sendStatusFile;
	
	@Before
	public void setUp() throws Exception {
		portNum = 3738;
		garage = ParkedUsers.instance("garage.txt");
		acs = new AccessControlServer(portNum);
	}
	
	@Test
	public void mapConnectionsAllNullTest() {
		int allNull = 1;
		acs = new AccessControlServer(portNum);
		it = acs.getDisplayConnections().entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			if(pair.getValue() != null) {
				allNull = 0;
				break;
			}
		}
		assertEquals("All of the values within the connection should be NULL",1, allNull);
	}
	
	@Test(expected = IOException.class)
	public void startServerTest() throws IOException, NoSuchMethodException, SecurityException {
		acs.startServer();
		acs.startServer();
	}
	
	@Test
	public void sendMessageTest() throws IOException {
		String expected = "Hello, world!";
		sendMessageFile = new File("sendMessageFile.txt");
		if(!sendMessageFile.exists()) {
			sendMessageFile.createNewFile();
		}
		pout = new PrintWriter(sendMessageFile);
		Scanner in = new Scanner(sendMessageFile);
		acs.sendMessage("Hello, world!", pout);
		pout.close();
		String actual = in.nextLine();
		assertEquals("The two string should be equal to eachother.",expected, actual);
	}
	
	//This test is basically the same as the startServerTest
	@Test(expected = IOException.class)
	public void runTest() throws IOException {
		acs.run();
		acs.startServer();
	}
	
	@Test(expected = IOException.class)
	public void runDifferentPortNumberTest() throws IOException {
		portNum = 5789;
		acs = new AccessControlServer(portNum);
		acs.run();
		acs.startServer();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void runWithInvalidPortSizeTest() {
		portNum = 999999;
		acs = new AccessControlServer(portNum);
		acs.run();
	}
	
	@Test
	public void sendStatusTest() throws IOException{
		sendStatusFile = new File("sendStatusFile.txt");
		ArrayList<String> statusTest = garage.getStatus();
		Scanner in;
		String comparing;
		int actual = 1;
		
		if(!sendStatusFile.exists()) {
			sendStatusFile.createNewFile();
		}
		
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
	public void reserveSpotTest() {
		
	}
	
	@Test
	public void wrongUserDetectedTest() {
		
	}
	
	@Test
	public void wrongUserDetectedNullTest() {
		
	}
	
	@Test
	public void duplicateIdFoundTest() {
		
	}
	
	@Test
	public void duplicateIdFoundNullTest() {
		
	}
	
	@Test
	public void addDisplayTest() {
		
	}
	
	@Test
	public void removeTest() {
		
	}
	
	@Test
	public void isConnectionsAvailableTest() {
		
	}

	@After
	public void tearDown() throws Exception {
	}
}

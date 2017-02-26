package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import client.maindisplay.ParkingNotification;
import server.storage.ParkedUsers;
import server.storage.ParkingSpot;

public class ControllerSubSystemTesting {
	
	ControllerFacade facade0;
	ControllerFacade facade1;
	ControllerFacade facade2;
	ControllerFacade facade3;
	
	AccessControlServer spyServer0;
	AccessControlServer spyServer1;
	AccessControlServer spyServer2;
	AccessControlServer spyServer3;
	AccessControlServer spyServer4;
	
	ControllerFacade spyFacade;
	ArgumentCaptor <String> messageCaptor;
	ArgumentCaptor <PrintWriter> pwCaptor;
	
	ParkingSpot spot;
	String id;
	PrintWriter printWriter0;
	PrintWriter printWriter1;
	PrintWriter printWriter2;
	PrintWriter printWriter3;
	
	StringWriter stringWriter0;
	StringWriter stringWriter1;
	StringWriter stringWriter2;
	StringWriter stringWriter3;
	
	// SST007_testSetParkingNotification
	ControllerFacade facade4;
	ControllerFacade facade5;
	ParkingNotification parkingNotificationDisp;
	ParkingUser parkingNotificaitonUser0;
	ParkingUser parkingNotificaitonUser1;
	ParkingUser parkingNotificaitonUser2;
	ParkingUser parkingNotificaitonUser3;
	
	
	ParkingSpot spot3;
	
	
	@Before
	public void setUp() throws Exception {
		facade0 = new ControllerFacade();
		
		spyServer0 = spy(facade0.createAccessControlServer(3737));
		facade0.setAccessControlServer(spyServer0);
		
		id = "1663314";
		
		spot = mock(ParkingSpot.class);
		when(spot.getParkingNumber()).thenReturn("101");
		
		printWriter0  = new PrintWriter(System.out);
		facade0.getAccessControlServer().getDisplayConnections().put("101",  printWriter0);
		
		messageCaptor = ArgumentCaptor.forClass(String.class);
		pwCaptor = ArgumentCaptor.forClass(PrintWriter.class);
		

		// SST006_testWrongUserDetected_SD setup
		facade1 = new ControllerFacade();
		facade1.createAccessControlServer(3738);
		
		
		// SST006_testWrongUserDetected_SD setup
		facade2 = new ControllerFacade();
		facade2.createAccessControlServer(3738);
		stringWriter0 = new StringWriter();
		printWriter0 = new PrintWriter(stringWriter0);
		facade2.getAccessControlServer().setSout(printWriter0);

		
		// SST007_testSetParkingNotification_SD_ SETUP
		facade4 = new ControllerFacade();
		parkingNotificationDisp = mock(ParkingNotification.class);
		parkingNotificaitonUser0 = mock(FacultyUser.class);
		when(parkingNotificaitonUser0.toString()).thenReturn("Faculty");
		
		facade4.createEntranceDisplayController(facade4);
		facade4.getEntranceDisplayController().setFound(true);
		facade4.getEntranceDisplayController().setUserID("1663314");
		facade4.getEntranceDisplayController().setpDisp(parkingNotificationDisp);
		facade4.getEntranceDisplayController().setUserType("Fiu Parking user");
		facade4.getEntranceDisplayController().setUser(parkingNotificaitonUser0);
		
		
		// SST007_testSetParkingNotification_RD SETUP
		parkingNotificaitonUser1 = mock(FacultyUser.class);
		when(parkingNotificaitonUser1.toString()).thenReturn("Student");
		
		facade5 = new ControllerFacade();
		facade5.createEntranceDisplayController(facade5);
		facade5.getEntranceDisplayController().setFound(false);
		facade5.getEntranceDisplayController().setUserID("1663314");
		facade5.getEntranceDisplayController().setpDisp(parkingNotificationDisp);
		facade5.getEntranceDisplayController().setUserType("invalid");
		facade5.getEntranceDisplayController().setUser(parkingNotificaitonUser1);
		
		//SST008_testDuplicateIdFound_SD setup
		facade3 = new ControllerFacade();
		spot3 = mock(ParkingSpot.class);
		when(spot3.getParkingNumber()).thenReturn("101");
		stringWriter1 = new StringWriter();
		printWriter1 = new PrintWriter(stringWriter1);
		facade3.createAccessControlServer(3738);
		facade3.createEntranceDisplayController(facade3);
		facade3.getEntranceDisplayController().setUserID("1663314");
		facade3.getEntranceDisplayController().setSpot(spot);
		facade3.getEntranceDisplayController().getDuplicates().put("1663314", spot3);
		facade3.getAccessControlServer().setSout(printWriter1);
		
	}

	@After
	public void tearDown() throws Exception {
		printWriter0.close();
	}

	@Test
	public void SST005_testReserveSpot_RD() {
		facade0.reserveSpot(spot, id);
		verify(spyServer0, times(2)).sendMessage(messageCaptor.capture(), pwCaptor.capture());
		
		assertEquals("First message sent is 'reserve' ", messageCaptor.getAllValues().get(0), "reserve");
		assertEquals("Second message sent is " + id, messageCaptor.getAllValues().get(1), id);
		
	}	
	@Test(expected=NullPointerException.class)
	public void SST005_testReserveSpot_SD() {
		facade1.reserveSpot(spot, id);
	}
	@Test
	public void SST006_testWrongUserDetected_SD1() {
		String msg = "User in the wrong parking spot";
		facade2.wrongUserDetected(msg);

		String actual = stringWriter0.toString();
		assertEquals("wrong User detected notification sent", "wrong\r\nUser in the wrong parking spot\r\n", actual);
	}
	@Test
	public void SST006_testWrongUserDetected_SD2() {
		String msg = "";
		facade2.wrongUserDetected(msg);
		String actual = stringWriter0.toString();
		assertEquals("wrong\r\n\r\n", actual);
	}
	@Test
	public void SST007_testSetParkingNotification_SD(){
		String actual = "Valid Request  Assigning faculty parking spot";
		String expected = facade4.setParkingNotification();
		assertEquals(expected, actual);
	}
	@Test
	public void SST007_testSetParkingNotification_RD(){
		String actual = "Invalid ID!  There are no guest spots avialable";
		String expected = facade5.setParkingNotification();
		assertEquals(expected, actual);
	}
	@Test
	public void SST008_testDuplicateIdFound_SD(){
		facade3.duplicateIdFound();
		String actual = "duplicate\r\nUser with ID:1663314 has reported an stolen ID.\r\nThe car with the same ID is parked on spot #101\r\n";
		String expected = stringWriter1.toString();
		assertEquals("duplicate Id found", expected, actual);
	}

}

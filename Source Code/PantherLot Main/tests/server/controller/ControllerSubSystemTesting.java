package server.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;

import client.maindisplay.DisplayDirections;
import client.maindisplay.ParkingNotification;
import client.maindisplay.SpotNumberDisplay;
import server.storage.ParkedUsers;
import server.storage.ParkingSpot;

public class ControllerSubSystemTesting {
	
	ControllerFacade facade0;
	ControllerFacade facade1;
	ControllerFacade facade2;
	ControllerFacade facade3;
	ControllerFacade facade4;
	ControllerFacade facade5;
	ControllerFacade facade6;
	ControllerFacade facade7;
	ControllerFacade facade8;
	ControllerFacade facade9;
	ControllerFacade facade10;
	ControllerFacade facade11;
	ControllerFacade facade12;
	ControllerFacade facade13;
	ControllerFacade facade14;
	ControllerFacade facade15;
	ControllerFacade facade16;
	ControllerFacade facade17;
	ControllerFacade facade18;
	ControllerFacade facade19;
	ControllerFacade facade20;
	ControllerFacade facade21;
	ControllerFacade facade22;
	
	ControllerFacade spyFacade0;
	
	AccessControlServer spyServer0;
	AccessControlServer spyServer1;
	AccessControlServer spyServer2;
	AccessControlServer spyServer3;
	AccessControlServer spyServer4;
	
	EntranceDisplayController edcSpy0;
	
	ArgumentCaptor <String> messageCaptor0;
	ArgumentCaptor <String> directionsCaptor1;
	ArgumentCaptor <String> messageCaptor2;
	ArgumentCaptor <PrintWriter> pwCaptor;
	
	
	PrintWriter printWriter0;
	PrintWriter printWriter1;
	PrintWriter printWriter2;
	PrintWriter printWriter3;
	
	StringWriter stringWriter0;
	StringWriter stringWriter1;
	StringWriter stringWriter2;
	StringWriter stringWriter3;
	
	ParkingSpot spot0;
	ParkingSpot spot1;
	ParkingSpot spot2;
	ParkingSpot spot3;
	ParkingSpot spot4;
	ParkingSpot spot5;
	ParkingSpot spot6;
	ParkingSpot spot7;
	
	ParkedUsers garage0;

	ParkingUser parkingUser0;
	ParkingUser parkingUser1;
	ParkingUser parkingUser2;
	ParkingUser parkingUser3;
	
	ParkingNotification parkingNotificationDisp;
	
	DisplayDirections displayDirectionsDisp0;
	DisplayDirections displayDirectionsDisp1;
	DisplayDirections displayDirectionsDisp2;
	
	SpotNumberDisplay spotNumDisp0;
	SpotNumberDisplay spotNumDisp1;
	SpotNumberDisplay spotNumDisp2;
	SpotNumberDisplay spotNumDisp3;
	
	DisplayDirections Disp1Spy;
	
	String id;
	
	
	@Before
	public void setUp() throws Exception {
		// SST000_testReserveSpot
		facade0 = new ControllerFacade();
		spyServer0 = spy(facade0.createAccessControlServer(3737));
		facade0.setAccessControlServer(spyServer0);
		id = "1663314";
		spot0 = mock(ParkingSpot.class);
		when(spot0.getParkingNumber()).thenReturn("101");
		printWriter0  = new PrintWriter(System.out);
		facade0.getAccessControlServer().getDisplayConnections().put("101",  printWriter0);
		messageCaptor0 = ArgumentCaptor.forClass(String.class);
		pwCaptor = ArgumentCaptor.forClass(PrintWriter.class);
		

		// SST001_testWrongUserDetected_SD setup
		facade1 = new ControllerFacade();
		facade1.createAccessControlServer(3738);
		
		
		// SST001_testWrongUserDetected_SD setup
		facade2 = new ControllerFacade();
		facade2.createAccessControlServer(3738);
		stringWriter0 = new StringWriter();
		printWriter0 = new PrintWriter(stringWriter0);
		facade2.getAccessControlServer().setSout(printWriter0);

		
		// SST002_testSetParkingNotification_SD_ SETUP
		facade4 = new ControllerFacade();
		parkingNotificationDisp = mock(ParkingNotification.class);
		parkingUser0 = mock(FacultyUser.class);
		when(parkingUser0.toString()).thenReturn("Faculty");
		
		facade4.createEntranceDisplayController(facade4);
		facade4.getEntranceDisplayController().setFound(true);
		facade4.getEntranceDisplayController().setUserID("1663314");
		facade4.getEntranceDisplayController().setpDisp(parkingNotificationDisp);
		facade4.getEntranceDisplayController().setUserType("Fiu Parking user");
		facade4.getEntranceDisplayController().setUser(parkingUser0);
		
		
		// SST002_testSetParkingNotification_RD SETUP
		parkingUser1 = mock(FacultyUser.class);
		when(parkingUser1.toString()).thenReturn("Student");
		
		facade5 = new ControllerFacade();
		facade5.createEntranceDisplayController(facade5);
		facade5.getEntranceDisplayController().setFound(false);
		facade5.getEntranceDisplayController().setUserID("1663314");
		facade5.getEntranceDisplayController().setpDisp(parkingNotificationDisp);
		facade5.getEntranceDisplayController().setUserType("invalid");
		facade5.getEntranceDisplayController().setUser(parkingUser1);
		
		//SST003_testDuplicateIdFound_SD setup
		facade3 = new ControllerFacade();
		spot1 = mock(ParkingSpot.class);
		when(spot1.getParkingNumber()).thenReturn("101");
		stringWriter1 = new StringWriter();
		printWriter1 = new PrintWriter(stringWriter1);
		facade3.createAccessControlServer(3738);
		facade3.createEntranceDisplayController(facade3);
		facade3.getEntranceDisplayController().setUserID("1663314");
		facade3.getEntranceDisplayController().setSpot(spot0);
		facade3.getEntranceDisplayController().getDuplicates().put("1663314", spot1);
		facade3.getAccessControlServer().setSout(printWriter1);
		
		//SST003_testDiplicateIdFound_RD setup
		facade8 = new ControllerFacade();
		facade8.createAccessControlServer(3738);
		facade8.createEntranceDisplayController(facade8);
		facade8.getEntranceDisplayController().setUserID("1663314");
		facade8.getEntranceDisplayController().setSpot(spot0);
		facade8.getEntranceDisplayController().getDuplicates().put("1663314", spot1);
		
		//SST004_testDisplayDirectionToSpot_SD0
		facade6 = new ControllerFacade();
		facade6.createEntranceDisplayController(facade6);
		spot2 = mock(ParkingSpot.class);
		when(spot2.createParkingDirections()).thenReturn("Hello");
		facade6.getEntranceDisplayController().setSpot(spot2);
		displayDirectionsDisp0 = mock(DisplayDirections.class);
		facade6.getEntranceDisplayController().setdDisp(displayDirectionsDisp0);
		
		//SST004_testDisplayDirectionToSpot_RD
		facade7 = new ControllerFacade();
		facade7.createEntranceDisplayController(facade7);
		spot3 = mock(ParkingSpot.class);
		when(spot3.createParkingDirections()).thenReturn(null);
		facade7.getEntranceDisplayController().setSpot(spot3);
		displayDirectionsDisp1 = mock(DisplayDirections.class);
		facade7.getEntranceDisplayController().setdDisp(displayDirectionsDisp1);


		//SST005_testFindSpotForUser
		facade9 = new ControllerFacade();
		facade9.createEntranceDisplayController(facade9);
		garage0 = mock(ParkedUsers.class);
		parkingUser2 = mock(ParkingUser.class);
		facade9.getEntranceDisplayController().setUser(parkingUser2);
		facade9.getEntranceDisplayController().setGarage(garage0);
		when(garage0.searchParkingSpot(parkingUser2)).thenReturn(spot4);
		
		//SST006_testDisplayParkingSpotAssigned_SD0
		facade10 = new ControllerFacade();
		facade10.createEntranceDisplayController(facade10);
		spotNumDisp1 = mock( SpotNumberDisplay.class);
		doReturn(true).when(spotNumDisp1).runDisplay(null);
		spot5 = mock(ParkingSpot.class);
		when(spot5.getParkingNumber()).thenReturn("145");
		// inject dependencies into facade entranceDisplayController
		facade10.getEntranceDisplayController().setsDisp(spotNumDisp1);
		facade10.getEntranceDisplayController().setSpot(spot5);
		
		//SST007_testDisplayParkingSpotAssigned_SD1
		facade11 = new ControllerFacade();
		facade11.createEntranceDisplayController(facade11);
		spotNumDisp2 = mock( SpotNumberDisplay.class);
		doReturn(true).when(spotNumDisp2).runDisplay(null);
		spot6 = mock(ParkingSpot.class);
		when(spot6.getParkingNumber()).thenReturn("226");
		// inject dependencies into facade entranceDisplayController
		facade11.getEntranceDisplayController().setsDisp(spotNumDisp2);
		facade11.getEntranceDisplayController().setSpot(spot6);
	}

	@After
	public void tearDown() throws Exception {
		printWriter0.close();
	}

	@Test(expected=NullPointerException.class)
	public void SST000_testReserveSpot_SD() {
		facade1.reserveSpot(spot0, id);
	}
	
	@Test
	public void SST000_testReserveSpot_RD() {
		facade0.reserveSpot(spot0, id);
		verify(spyServer0, times(2)).sendMessage(messageCaptor0.capture(), pwCaptor.capture());
		
		assertEquals("First message sent is 'reserve' ", messageCaptor0.getAllValues().get(0), "reserve");
		assertEquals("Second message sent is " + id, messageCaptor0.getAllValues().get(1), id);
		
	}	
	@Test
	public void SST001_testWrongUserDetected_SD1() {
		String msg = "User in the wrong parking spot";
		facade2.wrongUserDetected(msg);

		String actual = stringWriter0.toString();
		assertEquals("wrong User detected notification sent", "wrong\r\nUser in the wrong parking spot\r\n", actual);
	}
	@Test
	public void SST001_testWrongUserDetected_SD2() {
		String msg = "";
		facade2.wrongUserDetected(msg);
		String actual = stringWriter0.toString();
		assertEquals("wrong\r\n\r\n", actual);
	}
	@Test
	public void SST002_testSetParkingNotification_SD(){
		String actual = "Valid Request  Assigning faculty parking spot";
		String expected = facade4.setParkingNotification();
		assertEquals(expected, actual);
	}
	@Test
	public void SST002_testSetParkingNotification_RD(){
		String actual = "Invalid ID!  There are no guest spots avialable";
		String expected = facade5.setParkingNotification();
		assertEquals(expected, actual);
	}
	@Test
	public void SST003_testDuplicateIdFound_SD(){
		facade3.duplicateIdFound();
		String actual = "duplicate\r\nUser with ID:1663314 has reported an stolen ID.\r\nThe car with the same ID is parked on spot #101\r\n";
		String expected = stringWriter1.toString();
		assertEquals("duplicate Id found", expected, actual);
	}
	@Test
	public void SST003_testDiplicateIdFound_RD() {
		facade8.getAccessControlServer().setSout(null);
		facade8.duplicateIdFound();
		assertTrue(facade8.getAccessControlServer().isDuplicateIdFoundNull);
	}
	@Test
	public void SST004_testDisplayDirectionToSpot_SD(){
		assertEquals(facade6.displayDirectionsToSpot(),"Hello");
	}
	@Test
	public void SST004_testDisplayDirectionToSpot_RD() {
		assertEquals(facade7.displayDirectionsToSpot(), null);
	}
	@Test
	public void SST005_testFindSpotForUser_RD() {
		assertFalse(facade9.findSpotForUser());
	}
	@Test
	public void SST006_testDisplayParkingSpotAssigned_SD0() {
		assertEquals(facade10.displayParkingSpotAssigned(), "Your spot number is 145");
	}
	@Test
	public void SST007_testDisplayParkingSpotAssigned_SD1() {
		assertEquals(facade11.displayParkingSpotAssigned(), "Your spot number is 226");
	}
	
}

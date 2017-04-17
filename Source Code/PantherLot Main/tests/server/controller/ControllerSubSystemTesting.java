package server.controller;


import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import server.controller.stubDB;


public class ControllerSubSystemTesting extends stubDB {	
	
	@Before
	public void setUp() throws Exception {
		createStubDB();

	}

	@After
	public void tearDown() throws Exception {
		printWriter0.close();
	}
	
	@Test(expected=NullPointerException.class)
	public void SST001_testReserveSpot_SD() {
		facade1.reserveSpot(spot0, id);
	}
	@Test
	public void SST002_testReserveSpot_SD() {
		facade0.reserveSpot(spot0, id);
		verify(spyServer0, times(2)).sendMessage(messageCaptor0.capture(), pwCaptor.capture());
		
		assertEquals("First message sent is 'reserve' ", messageCaptor0.getAllValues().get(0), "reserve");
		assertEquals("Second message sent is " + id, messageCaptor0.getAllValues().get(1), id);
		
	}	
	@Test
	public void SST003_testWrongUserDetected_SD() {
		String msg = "User in the wrong parking spot";
		facade2.wrongUserDetected(msg);

		String actual = stringWriter0.toString();
		assertEquals("wrong User detected notification sent", "wrong\r\nUser in the wrong parking spot\r\n", actual);
	}
	@Test
	public void SST004_testWrongUserDetected_SD() {
		String msg = "";
		facade2.wrongUserDetected(msg);
		String actual = stringWriter0.toString();
		assertEquals("wrong\r\n\r\n", actual);
	}
	@Test
	public void SST005_testSetParkingNotification_SD(){
		String actual = "Valid Request  Assigning faculty parking spot";
		String expected = facade4.setParkingNotification();
		assertEquals(expected, actual);
	}
	@Test
	public void SST006_testSetParkingNotification_RD(){
		String actual = "Invalid ID!  There are no guest spots avialable";
		String expected = facade5.setParkingNotification();
		assertEquals(expected, actual);
	}
	@Test
	public void SST007_testDuplicateIdFound_SD(){
		facade3.duplicateIdFound();
		String actual = "duplicate\r\nUser with ID:1663314 has reported an stolen ID.\r\nThe car with the same ID is parked on spot #101\r\n";
		String expected = stringWriter1.toString();
		assertEquals("duplicate Id found", expected, actual);
	}
	@Test
	public void SST008_testDiplicateIdFound_RD() {
		facade8.duplicateIdFound();
		assertTrue(facade8.getAccessControlServer().isDuplicateIdFoundNull);
	}
	@Test
	public void SST009_testDisplayDirectionToSpot_SD(){
		assertEquals(facade6.displayDirectionsToSpot(),"15801 Sheridan St");
	}
	@Test
	public void SST010_testDisplayDirectionToSpot_RD() {
		assertEquals(facade7.displayDirectionsToSpot(), null);
	}
	@Test
	public void SST011_testFindSpotForUser_RD() {
		assertFalse(facade9.findSpotForUser());
	}
	@Test
	public void SST012_testDisplayParkingSpotAssigned_SD() {
		assertEquals(facade10.displayParkingSpotAssigned(), "Your spot number is 145");
	}
	@Test
	public void SST013_testDisplayParkingSpotAssigned_SD() {
		assertEquals(facade11.displayParkingSpotAssigned(), "Your spot number is 226");
	}
	@Test
	public void SST014_testDisplayParkingSpotAssigned_SD() {
		assertEquals(facade12.displayParkingSpotAssigned(), "Your spot number is ");
	}
	@Test
	public void SST015_testIdentifyUser_SD() {
		assertEquals(facade13.identifyUser(), "Guest");	
	}
	@Test
	public void SST016_testIdentifyUser_SD() {
		assertEquals(facade14.identifyUser(), "Faculty");	
	}
	@Test
	public void SST017_testIdentifyUser_SD() {
		assertEquals(facade15.identifyUser(), "Handicapped");	
	}
	@Test
	public void SST018_testIdentifyUser_SD() {
		assertEquals(facade16.identifyUser(), "Student");	
	}
	@Test
	public void SST019_testStoreInformationFromID_SD() {
		facade17.storeInformationFromID();
		assertEquals(facade17.getEntranceDisplayController().getUserID(), "1663314");
		assertEquals(facade17.getEntranceDisplayController().getUserType(), "FiuParkingUser");
	}
	@Test
	public void SST020_testStoreInformationFromID_SD() {
		facade18.storeInformationFromID();
		assertEquals(facade18.getEntranceDisplayController().getUserID(), "");
		assertEquals(facade18.getEntranceDisplayController().getUserType(), "FiuParkingUser");
	}
	@Test
	public void SST021_testStoreInformationFromID_RD() {
		facade19.storeInformationFromID();
		assertEquals(facade19.getEntranceDisplayController().getUserID(), null);
		assertEquals(facade19.getEntranceDisplayController().getUserType(), "FiuParkingUser");
	}

}

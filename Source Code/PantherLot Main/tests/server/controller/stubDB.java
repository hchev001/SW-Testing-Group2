package server.controller;


import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import static org.mockito.Mockito.when;

import java.io.PrintWriter;
import java.io.StringWriter;


import org.mockito.ArgumentCaptor;

import client.maindisplay.DisplayDirections;
import client.maindisplay.ParkingNotification;
import client.maindisplay.SpotNumberDisplay;
import client.maindisplay.WelcomeDisplay;
import server.storage.ParkedUsers;
import server.storage.ParkingSpot;

public class stubDB {
	protected ControllerFacade facade0;
	protected ControllerFacade facade1;
	protected ControllerFacade facade2;
	protected ControllerFacade facade3;
	protected ControllerFacade facade4;
	protected ControllerFacade facade5;
	protected ControllerFacade facade6;
	protected ControllerFacade facade7;
	protected ControllerFacade facade8;
	protected ControllerFacade facade9;
	protected ControllerFacade facade10;
	protected ControllerFacade facade11;
	protected ControllerFacade facade12;
	protected ControllerFacade facade13;
	protected ControllerFacade facade14;
	protected ControllerFacade facade15;
	protected ControllerFacade facade16;
	protected ControllerFacade facade17;
	protected ControllerFacade facade18;
	protected ControllerFacade facade19;
	protected ControllerFacade facade20;
	protected ControllerFacade facade21;
	protected ControllerFacade facade22;
	
	protected ControllerFacade spyFacade0;
	
	protected AccessControlServer spyServer0;
	protected AccessControlServer spyServer1;
	protected AccessControlServer spyServer2;
	protected AccessControlServer spyServer3;
	protected AccessControlServer spyServer4;
	
	protected EntranceDisplayController edcSpy0;
	
	protected ArgumentCaptor <String> messageCaptor0;
	protected ArgumentCaptor <String> directionsCaptor1;
	protected ArgumentCaptor <String> messageCaptor2;
	protected ArgumentCaptor <PrintWriter> pwCaptor;
	
	
	protected PrintWriter printWriter0;
	protected PrintWriter printWriter1;
	protected PrintWriter printWriter2;
	protected PrintWriter printWriter3;
	
	protected StringWriter stringWriter0;
	protected StringWriter stringWriter1;
	protected StringWriter stringWriter2;
	protected StringWriter stringWriter3;
	
	protected ParkingSpot spot0;
	protected ParkingSpot spot1;
	protected ParkingSpot spot2;
	protected ParkingSpot spot3;
	protected ParkingSpot spot4;
	protected ParkingSpot spot5;
	protected ParkingSpot spot6;
	protected ParkingSpot spot7;
	
	protected ParkedUsers garage0;

	protected ParkingUser parkingUser0;
	protected ParkingUser parkingUser1;
	protected ParkingUser parkingUser2;
	protected ParkingUser parkingUser3;
	
	protected ParkingNotification parkingNotificationDisp;
	
	protected DisplayDirections displayDirectionsDisp0;
	protected DisplayDirections displayDirectionsDisp1;
	protected DisplayDirections displayDirectionsDisp2;
	
	protected SpotNumberDisplay spotNumDisp0;
	protected SpotNumberDisplay spotNumDisp1;
	protected SpotNumberDisplay spotNumDisp2;
	protected SpotNumberDisplay spotNumDisp3;
	
	protected WelcomeDisplay welcDisp0;
	protected WelcomeDisplay welcDisp1;
	protected WelcomeDisplay welcDisp2;
	protected WelcomeDisplay welcDisp3;
	protected WelcomeDisplay welcDisp4;
	protected WelcomeDisplay welcDisp5;
	protected WelcomeDisplay welcDisp6;
	protected WelcomeDisplay welcDisp7;
	
	protected DisplayDirections Disp1Spy;
	
	protected String id;
	
	public void createStubDB()
	{
				/*
				 * 	SST001_testReserveSpot_SD setup
				 * 	used to setup the NullPointerException
				 *  spot0 mocks a ParkingSpot object and its
				 *  used to return a string value used for passing
				 *  it as a parameter of the nested sendMessage() 
				 *  methods inside reserveSpot().
				 *  At the creation of the AccessControlServer, 
				 *  its displayConnections hashmap has each index value
				 *  get set to a null PrintWriter object. reserveSpot in the process
				 *  of execution will assign sendMessage() a null PrintWriter object
				 *  from its displayConnections hashmap
				 */
				facade1 = new ControllerFacade();
				facade1.createAccessControlServer(3738);
				id = "1663314";
				spot0 = mock(ParkingSpot.class);
				when(spot0.getParkingNumber()).thenReturn("101");
		
				/*
				 * 	SST002_testReserveSpot_SD SETUP
				 * This method under test in this test case also uses the mocked
				 * ParkingSpot object of SST001_testReserveSpot_SD SETUP as well as
				 * the String id object.
				 * spyServer0 is used to spy on the behavior of facade0 accessControlServer object
				 * printWriter0 is instantiated to System.out and is put in the hashtable with key "101"
				 * ArgumentCaptors are used to capture String Arguments and PrintWriter Arguments
				 *	argumentCaptors are used to verify that the methods parameters of reserveSpot
				 *are the same as the parameters of sendMessage method inside of reserveSpot.
				 */
				facade0 = new ControllerFacade();
				spyServer0 = spy(facade0.createAccessControlServer(3737));
				facade0.setAccessControlServer(spyServer0);
				printWriter0  = new PrintWriter(System.out);
				facade0.getAccessControlServer().getDisplayConnections().put("101",  printWriter0);
				messageCaptor0 = ArgumentCaptor.forClass(String.class);
				pwCaptor = ArgumentCaptor.forClass(PrintWriter.class);
				

								
				/*
				 * SST003_testWrongUserDetected_SD & SST004 setup
				 * A stringWriter is used as the stream of the printWriter to behave like
				 * a buffer, which let's us verify messages were sent.
				 * printWriter0 is set as Sout of the accessControlServer because
				 * wrongUserDetected method calls sendMessage method with
				 * sout as the printwrite to which to print from.
				 */
				facade2 = new ControllerFacade();
				facade2.createAccessControlServer(3738);
				stringWriter0 = new StringWriter();
				printWriter0 = new PrintWriter(stringWriter0);
				facade2.getAccessControlServer().setSout(printWriter0);

				
				/*
				 * SST005_testSetParkingNotification_SD_ SETUP
				 * setParkingNotification() returns the two strings used
				 * to create the label of the parking notification display
				 * where the user is told that their request was valid
				 * and they are being assigned their corresponding parking spot
				 * type
				 * parkingNotificationDisp is a mocked object because the the method called by
				 * setParkingNotification, setUpParkingDisplayNotification calls a method on a 
				 * ParkingNotification object. 
				 * parkingUser0 is a mocked object so we can set up the scenario of a Faculty spot is being assigned.
				 * The id of the Faculty user will be 1663314 and by default the userTYpe is Fiu Parking User
				 */
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
				
				
				
				/*
				 * SST006_testSetParkingNotification_RD SETUP
				 * setParkingNotification() returns the two strings used to create the label
				 * of the parking notification display. Where the user is told that their
				 * request was invalid because of an Invalid ID and since
				 * there are no connections to guest spots then the user is informed
				 * there are no guest spots available.
				 * parkingUser1 is mocked to return the toString() value of itself, which is "Student"
				 * The user id will also be of a Faculty user "1663314" and their type is "invalid"
				 * This generates the scenario of a Faculty user trying to Parking in a Student spot,
				 * however the system is not connected to any Student spot or Guest spot, so it attempts
				 * to assign a Guest spot but it is not able to.
				 */
				parkingUser1 = mock(FacultyUser.class);
				when(parkingUser1.toString()).thenReturn("Student");
				
				facade5 = new ControllerFacade();
				facade5.createEntranceDisplayController(facade5);
				facade5.getEntranceDisplayController().setFound(false);
				facade5.getEntranceDisplayController().setUserID("1663314");
				facade5.getEntranceDisplayController().setpDisp(parkingNotificationDisp);
				facade5.getEntranceDisplayController().setUserType("invalid");
				facade5.getEntranceDisplayController().setUser(parkingUser1);
				
				/*
				 * SST007_testDuplicateIdFound_SD SETUP
				 * When the facade calls this method a series of statements are executed before 
				 * AccessControlServer.duplicateIdFound is called.
				 * duplicateIdFound method of the AccessControlServer class  
				 * receives two strings that are used as parameters of
				 * the nested sendMessage() methods. The StringWriter is used a buffer
				 * to receive the strings and verify they are the same ones.
				 * The two strings are created from the userId and the parkingNumber that
				 * has been identified as duplicated (spot1).
				 * Within duplicateIdFound method a local variable String ID is set to the 
				 * entranceDisplayController CurrentUserID which is set to me "1663314".
				 * Using the same String ID value, getDuplicatedParkingSpot(ID) is called which returns
				 * a ParkingSpot object from the duplicates hash table that exists within EntranceDisplayController.java
				 * A mocked ParkingSpot object is inserted into the duplicates hash table using the key of
				 * CurrentUserID "1663314"  stringWriter1 is set as the stream of printWriter1
				 *  so printWriter1 can be injected into  the AccessControlServer and set as the value of 
				 *  PrintWriter sout. This is done in order to read the contents of the messages
				 *   sent to what would be a socket at port 3738.
				 */
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
				
				/*
				 * SST008_testDiplicateIdFound_RD SETUP
				 * duplicateIdFound of the AccessControlServer class has a guard (sout == null) return;
				 * A global boolean isDuplicatedIdFoundNull was added to the class and is set when the guard is valid
				 * This setup is to test that necessary PrintWriter used to print to System.out
				 * is null. As previously mentioned in SST007_testDuplicateIdFound_SD SETUP
				 * a mocked spot object is put into the duplicates hash map of EntranceDisplayController
				 * class. The userID of the EntranceDisplayController object will be "1663314".
				 * The setup is necessary only so an early null pointer exception is not thrown.
				 */
				facade8 = new ControllerFacade();
				facade8.createAccessControlServer(3738);
				facade8.createEntranceDisplayController(facade8);
				facade8.getEntranceDisplayController().setUserID("1663314");
				facade8.getEntranceDisplayController().setSpot(spot0);
				facade8.getEntranceDisplayController().getDuplicates().put("1663314", spot1);
				facade8.getAccessControlServer().setSout(null);
				
				/*
				 * SST009_testDisplayDirectionToSpot_SD SETUP
				 * displayDirectionsToSpot is used to call EntranceDisplayController.displayDirectionstoParkingSpot()
				 * This method creates the necessary parking directions to 
				 * drive to a parking spot. This setup is used to test that the directions
				 * set are the ones the program mocks. A ParkingSpot object is mocked in order to have it create
				 * the parking directions via its createParkingDIrections() method, afterward it is injected into the 
				 * EntranceDisplayController object of the facade. A DisplayDirection object is mocked,
				 * and injected into the EntranceDisplayController in order to isolate the behavior of the creation
				 * of the String directions and not start a client service of running a user interface.
				 * The address "15801 Sheridan St" is the expected address the method will return.
				 */
				facade6 = new ControllerFacade();
				facade6.createEntranceDisplayController(facade6);
				spot2 = mock(ParkingSpot.class);
				when(spot2.createParkingDirections()).thenReturn("15801 Sheridan St");
				facade6.getEntranceDisplayController().setSpot(spot2);
				displayDirectionsDisp0 = mock(DisplayDirections.class);
				facade6.getEntranceDisplayController().setdDisp(displayDirectionsDisp0);
				
				/*
				 * SST010_testDisplayDirectionToSpot_RD SETUP
				 * displayDirectionsToSpot creates the necessary parking directions to 
				 * drive to a parkingspot. This setup is used to test that the directions
				 * set are the ones the program mocks, a null string. Please refer to
				 * SST009_testDisplayDirectionToSpot_SD SETUP (previous one) for 
				 * an explanation of the setup as it follows the same logic.
				 */
				facade7 = new ControllerFacade();
				facade7.createEntranceDisplayController(facade7);
				spot3 = mock(ParkingSpot.class);
				when(spot3.createParkingDirections()).thenReturn(null);
				facade7.getEntranceDisplayController().setSpot(spot3);
				displayDirectionsDisp1 = mock(DisplayDirections.class);
				facade7.getEntranceDisplayController().setdDisp(displayDirectionsDisp1);


				/*
				 * SST011_testFindSpotForUser_RD SETUP
				 * facade.FindSpotForUser() calls findSpotForUser() of the EntranceDisplayController class.
				 * It retrieves a ParkingSpot object from the instance of the ParkedUsers object garage and assigns 
				 * it to this class ParkingSpot instance variable. If the ParkingSpot variable is not null, it sets 
				 * the instance variable boolean found to True, else its false. It then returns the variable found.
				 * Since a instance of ParkedUsers is called in this class,
				 * garage0 is an dependency from a different package used
				 * to return a ParkingSpot object. There can be no sunny day test
				 * as the object returned from garage is a mocked object and it treats
				 * its value as null so findSpotForUser always returns false for subsystem testing.
				 * 
				 */
				facade9 = new ControllerFacade();
				facade9.createEntranceDisplayController(facade9);
				garage0 = mock(ParkedUsers.class);
				parkingUser2 = mock(ParkingUser.class);
				facade9.getEntranceDisplayController().setUser(parkingUser2);
				facade9.getEntranceDisplayController().setGarage(garage0);
				when(garage0.searchParkingSpot(parkingUser2)).thenReturn(spot4);
				
				/*
				 * SST012_testDisplayParkingSpotAssigned_SD SETUP
				 * facade.displayParkingSpotAssigned calls the displayParkingSpotAssigned() 
				 * method of the EntranceDisplayController class.
				 * spot5 mocked to create a parking number string. The result string
				 * is appended with "Your spot number is ". spotNumbDisp1 is
				 * mocked to avoid the side effects of creating a gui, which is a client
				 * service. This test verifies the correct label was created.
				 */
				facade10 = new ControllerFacade();
				facade10.createEntranceDisplayController(facade10);
				spotNumDisp1 = mock( SpotNumberDisplay.class);
				doReturn(true).when(spotNumDisp1).runDisplay(null);
				spot5 = mock(ParkingSpot.class);
				when(spot5.getParkingNumber()).thenReturn("145");
				facade10.getEntranceDisplayController().setsDisp(spotNumDisp1);
				facade10.getEntranceDisplayController().setSpot(spot5);
				
				/*
				 * SST013_testDisplayParkingSpotAssigned_SD SETUP
				 * Refer to SST012_testDisplayParkingSpotAssigned_SD SETUP for 
				 * description of setup, similar logic of setup except
				 * a different label string is created.
				 */
				facade11 = new ControllerFacade();
				facade11.createEntranceDisplayController(facade11);
				spotNumDisp2 = mock( SpotNumberDisplay.class);
				doReturn(true).when(spotNumDisp2).runDisplay(null);
				spot6 = mock(ParkingSpot.class);
				when(spot6.getParkingNumber()).thenReturn("226");
				facade11.getEntranceDisplayController().setsDisp(spotNumDisp2);
				facade11.getEntranceDisplayController().setSpot(spot6);
				
				/*
				 * SST014_testDisplayParkingSpotAssigned_SD SETUP
				 * Refer to SST012_testDisplayparkingSpotAssigned_SD SETUP for
				 * description of setup, similar logic. This setup is used to test
				 * when the parking number string is null.
				 */
				facade12 = new ControllerFacade();
				facade12.createEntranceDisplayController(facade12);
				spotNumDisp3 = mock( SpotNumberDisplay.class);
				doReturn(true).when(spotNumDisp3).runDisplay(null);
				spot7 = mock(ParkingSpot.class);
				when(spot7.getParkingNumber()).thenReturn("");
				facade12.getEntranceDisplayController().setsDisp(spotNumDisp3);
				facade12.getEntranceDisplayController().setSpot(spot7);
				
				/*
				 * SST015_testIdentifyUser_SD SETUP
				 * ControllerFacade.identifyUser calls the 
				 * createUserFromTypeAndID method of the EntranceDisplayController class.
				 * createUserFromTypeAndID calls the method storeDinformationFromClient()
				 * from the same class to fetch the instance variables of 
				 * a WelcomeDisplay object and assigns them to the
				 * EntranceDisplayController instance variables. The WelcomeDisplay
				 * object is mocked to return predetermined values. These fields
				 * are used to create a user object of the correct type based on
				 * the userType that is further mutated by a method that searches
				 * the FiuDB.txt file for the userID and matches its corresponding user type.  
				 * The WelcomeDisplay object is mocked to return the data
				 * of a user who scanned an ID bar code that was an empty
				 * String. When no ID is found in the FiuDB.txt file that matches what the user inputed, 
				 * they are identified as a Guest and a GuestUser object is assigned to the ParkingUser user
				 * instance variable of EntranceDisplayController.
				 * 
				 * FiuDB.txt file contents are: 
				 * 1663314 Faculty Abraham Cruz
				 * 2223432 Student Elba Garcia
				 * 1654333 Handicapped Nick Caceres
				 * 2233432 Student Michelle Solano
				 * 1323454 Student Alex Cruz
				 * 4234423 Faculty Andres Marcial
				 */
				facade13 = new ControllerFacade();
				welcDisp0 = mock(WelcomeDisplay.class);
				facade13.createEntranceDisplayController(facade13);
				facade13.getEntranceDisplayController().setwDisp(welcDisp0);
				when(welcDisp0.getID()).thenReturn("");
				when(welcDisp0.returnType()).thenReturn("FiuParkingUser");
				
				/*
				 * SST016_testIdentifyUser_SD SETUP
				 * Refer to SST015_testIdentifyUser_SD setup for similar logic
				 * for setup and the database text file used.
				 * . This setup uses the ID of a faculty user, 1663314.
				 */
				facade14 = new ControllerFacade();
				welcDisp1 = mock(WelcomeDisplay.class);
				facade14.createEntranceDisplayController(facade14);
				facade14.getEntranceDisplayController().setwDisp(welcDisp1);
				when(welcDisp1.getID()).thenReturn("1663314");
				when(welcDisp1.returnType()).thenReturn("FiuParkingUser");
				
				/*
				 * SST017_testIdentifyUser_SD SETUP
				 * Refer to SST015_testIdentifyUser_SD setup for similar logic for setup
				 * and the database text file used.
				 * This setup uses the ID of a handicapped user, 1654333
				 */
				facade15 = new ControllerFacade();
				welcDisp2 = mock(WelcomeDisplay.class);
				facade15.createEntranceDisplayController(facade15);
				facade15.getEntranceDisplayController().setwDisp(welcDisp2);
				when(welcDisp2.getID()).thenReturn("1654333");
				when(welcDisp2.returnType()).thenReturn("FiuParkingUser");
				
				/*
				 * SST018_testIdentifyUser_SD SETUP
				 * Refer to SST015_testIdentifyUser_SD setup for similar 
				 * logic for setup and the database text file used. T
				 * his setup uses the ID of a Student user, 2223432.
				 */
				facade16 = new ControllerFacade();
				welcDisp3 = mock(WelcomeDisplay.class);
				facade16.createEntranceDisplayController(facade16);
				facade16.getEntranceDisplayController().setwDisp(welcDisp3);
				when(welcDisp3.getID()).thenReturn("2223432");
				when(welcDisp3.returnType()).thenReturn("FiuParkingUser");
				
				/*
				 * SST019_testStoreInformationFromID_SD SETUP
				 * storeInformationFromID fetches the user's ID and Type
				 * after scanning their ID from the WelcomeDisplay object
				 * and assigns them to the instance variables of EntranceDisplayController
				 * object. The WeldcomeDisplay object is mocked and the user's ID
				 * is 1663314. By default all users who scan an ID, their initial
				 * user type is FiuParkingUser.
				 */
				facade17 = new ControllerFacade();
				welcDisp4 = mock(WelcomeDisplay.class);
				facade17.createEntranceDisplayController(facade17);
				facade17.getEntranceDisplayController().setwDisp(welcDisp4);
				when(welcDisp4.getID()).thenReturn("1663314");
				when(welcDisp4.returnType()).thenReturn("FiuParkingUser");
				
				/*
				 * SST020_testStoreInformationFromID_SD SETUP
				 * Refer to SST019_testStoreInformationFromID_SD for similar logic 
				 * of setup. The user's id is an empty string as it is possible
				 * to click the Scan ID button without actually scanning an id.
				 */
				facade18 = new ControllerFacade();
				welcDisp5 = mock(WelcomeDisplay.class);
				facade18.createEntranceDisplayController(facade18);
				facade18.getEntranceDisplayController().setwDisp(welcDisp5);
				when(welcDisp5.getID()).thenReturn("");
				when(welcDisp5.returnType()).thenReturn("FiuParkingUser");
				
				/*
				 * SST021_testStoreInformationFromID_RD Setup
				 * Refer to SST019_testStoreInformationFromID_SD for similar logic
				 * of setup. The user's id is null. Used to verify that 
				 * EntranceDisplayController instance variable for ID is also
				 * set to null.
				 */
				facade19 = new ControllerFacade();
				welcDisp6 = mock(WelcomeDisplay.class);
				facade19.createEntranceDisplayController(facade19);
				facade19.getEntranceDisplayController().setwDisp(welcDisp6);
				when(welcDisp6.getID()).thenReturn(null);
				when(welcDisp6.returnType()).thenReturn("FiuParkingUser");	
	}
}

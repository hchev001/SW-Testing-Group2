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
				// SST001_testReserveSpot_SD setup
				facade1 = new ControllerFacade();
				facade1.createAccessControlServer(3738);
		
				// SST002_testReserveSpot_RD setup
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
				

				
				
				// SST003_testWrongUserDetected_SD & SST004 setup
				facade2 = new ControllerFacade();
				facade2.createAccessControlServer(3738);
				stringWriter0 = new StringWriter();
				printWriter0 = new PrintWriter(stringWriter0);
				facade2.getAccessControlServer().setSout(printWriter0);

				
				// SST005_testSetParkingNotification_SD_ SETUP
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
				
				
				// SST006_testSetParkingNotification_RD SETUP
				parkingUser1 = mock(FacultyUser.class);
				when(parkingUser1.toString()).thenReturn("Student");
				
				facade5 = new ControllerFacade();
				facade5.createEntranceDisplayController(facade5);
				facade5.getEntranceDisplayController().setFound(false);
				facade5.getEntranceDisplayController().setUserID("1663314");
				facade5.getEntranceDisplayController().setpDisp(parkingNotificationDisp);
				facade5.getEntranceDisplayController().setUserType("invalid");
				facade5.getEntranceDisplayController().setUser(parkingUser1);
				
				//SST007_testDuplicateIdFound_SD setup
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
				
				//SST008_testDiplicateIdFound_RD setup
				facade8 = new ControllerFacade();
				facade8.createAccessControlServer(3738);
				facade8.createEntranceDisplayController(facade8);
				facade8.getEntranceDisplayController().setUserID("1663314");
				facade8.getEntranceDisplayController().setSpot(spot0);
				facade8.getEntranceDisplayController().getDuplicates().put("1663314", spot1);
				
				//SST009_testDisplayDirectionToSpot_SD0
				facade6 = new ControllerFacade();
				facade6.createEntranceDisplayController(facade6);
				spot2 = mock(ParkingSpot.class);
				when(spot2.createParkingDirections()).thenReturn("Hello");
				facade6.getEntranceDisplayController().setSpot(spot2);
				displayDirectionsDisp0 = mock(DisplayDirections.class);
				facade6.getEntranceDisplayController().setdDisp(displayDirectionsDisp0);
				
				//SST010_testDisplayDirectionToSpot_RD
				facade7 = new ControllerFacade();
				facade7.createEntranceDisplayController(facade7);
				spot3 = mock(ParkingSpot.class);
				when(spot3.createParkingDirections()).thenReturn(null);
				facade7.getEntranceDisplayController().setSpot(spot3);
				displayDirectionsDisp1 = mock(DisplayDirections.class);
				facade7.getEntranceDisplayController().setdDisp(displayDirectionsDisp1);


				//SST011_testFindSpotForUser
				facade9 = new ControllerFacade();
				facade9.createEntranceDisplayController(facade9);
				garage0 = mock(ParkedUsers.class);
				parkingUser2 = mock(ParkingUser.class);
				facade9.getEntranceDisplayController().setUser(parkingUser2);
				facade9.getEntranceDisplayController().setGarage(garage0);
				when(garage0.searchParkingSpot(parkingUser2)).thenReturn(spot4);
				
				//SST012_testDisplayParkingSpotAssigned_SD0
				facade10 = new ControllerFacade();
				facade10.createEntranceDisplayController(facade10);
				spotNumDisp1 = mock( SpotNumberDisplay.class);
				doReturn(true).when(spotNumDisp1).runDisplay(null);
				spot5 = mock(ParkingSpot.class);
				when(spot5.getParkingNumber()).thenReturn("145");
				// inject dependencies into facade entranceDisplayController
				facade10.getEntranceDisplayController().setsDisp(spotNumDisp1);
				facade10.getEntranceDisplayController().setSpot(spot5);
				
				//SST013_testDisplayParkingSpotAssigned_SD
				facade11 = new ControllerFacade();
				facade11.createEntranceDisplayController(facade11);
				spotNumDisp2 = mock( SpotNumberDisplay.class);
				doReturn(true).when(spotNumDisp2).runDisplay(null);
				spot6 = mock(ParkingSpot.class);
				when(spot6.getParkingNumber()).thenReturn("226");
				// inject dependencies into facade entranceDisplayController
				facade11.getEntranceDisplayController().setsDisp(spotNumDisp2);
				facade11.getEntranceDisplayController().setSpot(spot6);
				
				// SST014_testDisplayParkingSpotAssigned_SD
				facade12 = new ControllerFacade();
				facade12.createEntranceDisplayController(facade12);
				spotNumDisp3 = mock( SpotNumberDisplay.class);
				doReturn(true).when(spotNumDisp3).runDisplay(null);
				spot7 = mock(ParkingSpot.class);
				when(spot7.getParkingNumber()).thenReturn("");
				
				// inject dependencies into facade entranceDisplayController
				facade12.getEntranceDisplayController().setsDisp(spotNumDisp3);
				facade12.getEntranceDisplayController().setSpot(spot7);
				
				// SST015_testIdentifyUser_SD setup
				facade13 = new ControllerFacade();
				welcDisp0 = mock(WelcomeDisplay.class);
				facade13.createEntranceDisplayController(facade13);
				facade13.getEntranceDisplayController().setwDisp(welcDisp0);
				
				// SST016_testIdentifyUser_SD setup
				facade14 = new ControllerFacade();
				welcDisp1 = mock(WelcomeDisplay.class);
				facade14.createEntranceDisplayController(facade14);
				facade14.getEntranceDisplayController().setwDisp(welcDisp1);
				
				// SST017_testIdentifyUser_SD setup
				facade15 = new ControllerFacade();
				welcDisp2 = mock(WelcomeDisplay.class);
				facade15.createEntranceDisplayController(facade15);
				facade15.getEntranceDisplayController().setwDisp(welcDisp2);
				
				// SST018_testIdentifyUser_SD setup
				facade16 = new ControllerFacade();
				welcDisp3 = mock(WelcomeDisplay.class);
				facade16.createEntranceDisplayController(facade16);
				facade16.getEntranceDisplayController().setwDisp(welcDisp3);
				
				// SST019_testStoreInformationFromID_SD setup
				facade17 = new ControllerFacade();
				welcDisp4 = mock(WelcomeDisplay.class);
				facade17.createEntranceDisplayController(facade17);
				facade17.getEntranceDisplayController().setwDisp(welcDisp4);
				when(welcDisp4.getID()).thenReturn("1663314");
				when(welcDisp4.returnType()).thenReturn("FiuParkingUser");
				
				// SST020_testStoreInformationFromID_SD setup
				facade18 = new ControllerFacade();
				welcDisp5 = mock(WelcomeDisplay.class);
				facade18.createEntranceDisplayController(facade18);
				facade18.getEntranceDisplayController().setwDisp(welcDisp5);
				when(welcDisp5.getID()).thenReturn("");
				when(welcDisp5.returnType()).thenReturn("FiuParkingUser");
				
				// SST021_testStoreInformationFromID_RD setup
				facade19 = new ControllerFacade();
				welcDisp6 = mock(WelcomeDisplay.class);
				facade19.createEntranceDisplayController(facade19);
				facade19.getEntranceDisplayController().setwDisp(welcDisp6);
				when(welcDisp6.getID()).thenReturn(null);
				when(welcDisp6.returnType()).thenReturn("FiuParkingUser");	
	}
}

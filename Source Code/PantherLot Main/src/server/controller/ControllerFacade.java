package server.controller;
import java.io.PrintWriter;

import client.maindisplay.DisplayDirections;
import client.maindisplay.ParkingNotification;
import client.maindisplay.SpotNumberDisplay;
import client.maindisplay.WelcomeDisplay;
import server.controller.EntranceDisplayController;
import server.storage.ParkedUsers;
import server.storage.ParkingSpot;



public class ControllerFacade {
	
	private AccessControlServer accessControlServer;
	
	public AccessControlServer getAccessControlServer() {
		return accessControlServer;
	}

	public void setAccessControlServer(AccessControlServer accessControlServer) {
		this.accessControlServer = accessControlServer;
	}

	//mainDisplay variables
	private WelcomeDisplay wDisp;
	private ParkingNotification pDisp;
	private SpotNumberDisplay sDisp;
	private DisplayDirections dDisp;
	
	
	
	
	// server.controller Variables
	private EntranceDisplayController entranceDisplayController;
	private AccessControlServer server;
	
	
	private ParkingUser parkUser;
	private GuestUser guestUser;
	private HandicappedUser handicappedUser;
	
	private FiuParkingUser fiuParkingUser;
	private FacultyUser facUser;
	private StudentUser studUser;
	
	private ParkedUsers parkedUsersDB;
	private ParkingSpot parkingSpot;
	
	boolean welcomeDisplayEvent;
	private String welcomeDisplayUserType = "";
	private String welcomeDisplayUserID = "";
	

	//*************** ACCESORS AND MUTATORS generated by Eclipse /////////////////////
	public boolean isWelcomeDisplayEvent() {
		return welcomeDisplayEvent;
	}
	
	public void setWelcomeDisplayEvent(boolean welcomeDisplayEvent) {
		this.welcomeDisplayEvent = welcomeDisplayEvent;
	}
	
	public String getWelcomeDisplayUserType() {
		return welcomeDisplayUserType;
	}
	
	public void setWelcomeDisplayUserType(String welcomeDisplayUserType) {
		this.welcomeDisplayUserType = welcomeDisplayUserType;
	}
	
	public String getWelcomeDisplayUserID() {
		return welcomeDisplayUserID;
	}
	
	public void setWelcomeDisplayUserID(String welcomeDisplayUserID) {
		this.welcomeDisplayUserID = welcomeDisplayUserID;
	
	
	}

//////////////////////////////////////////////////////////////	
	/*
	 * Client Methods
	 */

	public void associateClientReferences(WelcomeDisplay wDisp, ParkingNotification pDisp, SpotNumberDisplay sDisp,
			DisplayDirections dDisp, ControllerFacade facade) {
		this.wDisp = wDisp;
		this.wDisp.setcFacade(facade);
		
		this.pDisp = pDisp;
		this.pDisp.setcFacade(facade);
		
		this.sDisp = sDisp;
		this.sDisp.setcFacade(facade);
		
		this.dDisp = dDisp;
		this.dDisp.setcFacade(facade);
		
	}
	
	
	
	
	// USE Case PLI 004 - Identify User Type based On Matching ID
	// Dependencies: WelcomeDisplay userType, WelcomeDisplay userID
	// ParkingUser user FiuDB.txt
	// DONE
	public String identifyUser()
	{
		ParkingUser user = this.entranceDisplayController.createUserFromTypeAndID();
		return user.toString();
	}
	// USE CASE PLI 002 - User scans ID, system gets the users information from the ID,
	// use case ends when system gets the ID information and saves it.
	// DONE
	public void storeInformationFromID()
	{
		this.entranceDisplayController.storeIDinformationFromClient();
		
	}
	// USE CASE PLI010 - Display Parking Spot Assigned
	// Dependencies: SpotNumberDisplay sDisp
	// ParkingSpot spot calls getParkingNumber(), this can be mocked
	// ParkingNotificiation pDisk calls getLocation(), this can be mocked
	// WHat can be tested: SDisp calls updateParkingSpotNumberLabl
	// which has Jlabel spotNumberLabel.setTest(string update)
	// we can test the String this method returns because it will be the label of the gui
	// DONE
	public String displayParkingSpotAssigned()
	{
		return this.entranceDisplayController.displayParkingSpotAssigned();
	}
	
	// USE CASE PLI019 - DONE
	public void reserveSpot(ParkingSpot spot, String userID)
	{
		this.getAccessControlServer().reserveSpot(spot, userID);
	}
	// USE CASE PLI014 - NOTIFY USER OF PARKING IN THE WRONG SPOT	--- DONE
	public void wrongUserDetected(String msg)
	{
		this.accessControlServer.wrongUserDetected(msg);
	}
	
	//Should be a service
	// DONE
	public String setParkingNotification()
	{
		this.entranceDisplayController.setUpParkingDisplayNotification(entranceDisplayController.isFound(), entranceDisplayController.getCurrentUserID(), pDisp);
		//this.pDisp.runDisplay(null);
		return this.entranceDisplayController.getMessage1() + " " + this.entranceDisplayController.getMessage2();
	}
	
	
	// USE CASE PLIS05 - Display Stolen ID Security Alert
	public void duplicateIdFound()
	{
		String ID = this.entranceDisplayController.getCurrentUserID();
		ParkingSpot dup = this.entranceDisplayController.getDuplicateParkingSpot(ID);
		//System.out.println("sending notification");
		String msg1 = "User with ID:" + ID + " has reported an stolen ID.";
		String msg2 ="The car with the same ID is parked on spot #" + dup.getParkingNumber();
		this.accessControlServer.duplicateIdFound(msg1, msg2);
	}
	
	
	//USE CASE PLI011 - Display Directions to Parking Spot
	// may not need to test since call in displayDirectionsToParkingSPot is done to another package.
	public void displayDirectionsToSpot()
	{
		this.entranceDisplayController.displayDirectionsToParkingSpot();
	}
	
	public boolean createUserParkingSpot()
	{
		this.entranceDisplayController.createUserFromTypeAndID();
		return findSpotForUser();
	}
	
	
	public boolean findSpotForUser()
	{
		return this.entranceDisplayController.findSpotForUser();
	}
	
	
	

	public WelcomeDisplay createNewWelcomeDisplay()
	{
		this.wDisp = new WelcomeDisplay();
		return wDisp;
	}
	
	public ParkingNotification createNewParkingNotificationDisplay()
	{
		this.pDisp = new ParkingNotification();
		return pDisp;
	}
	
	public SpotNumberDisplay createNewSpotNumberDisplay()
	{
		this.sDisp = new SpotNumberDisplay();
		return sDisp;
	}
	
	public DisplayDirections createNewDisplayDirectionDisplay()
	{
		this.dDisp = new DisplayDirections();
		return dDisp;
	}
	/*
	 * AccessControl Server methods
	 */
	
	public AccessControlServer createAccessControlServer(int portNumber)
	{
		this.accessControlServer = new AccessControlServer(portNumber);	
		return this.accessControlServer;
	}
	
	public void startAccessControlServer()
	{
		this.accessControlServer.start();
	}
	
	/*
	 * Entrance Display COntroller
	 */
	public EntranceDisplayController createEntranceDisplayController(ControllerFacade facade)
	{
		this.entranceDisplayController = new EntranceDisplayController(facade);
		return this.entranceDisplayController;
	}
	
	public EntranceDisplayController getEntranceDisplayController() {
		return entranceDisplayController;
	}
	
	public void startDisplayLogic()
	{
		this.entranceDisplayController.runDisplays();
	}
	
	public ParkingSpot getParkingSpotObject()
	{
		return entranceDisplayController.getSpot();
	}
	
	public boolean currentUserIDlengthGreaterThan2()
	{
		return entranceDisplayController.getCurrentUserID().length() > 2;
	}
    
}

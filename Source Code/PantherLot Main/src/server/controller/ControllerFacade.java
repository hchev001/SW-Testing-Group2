package server.controller;
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
	

	//*************** ACCESORS AND MUTATORS /////////////////////
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
	
	/*
	 * Client Methods
	 */
	
	}

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
	
	
	
	// USE CASE PLI-02
	public boolean scanIDCallToController(String userID, String userType, boolean action) {
		this.welcomeDisplayEvent = action;
		this.welcomeDisplayUserID = userID;
		this.welcomeDisplayUserType = userType;
		this.entranceDisplayController.createUserFromTypeAndID();
		return !(this.entranceDisplayController.getUser() == null);
	}

	public void guestButtonCallToController(String userType, boolean action) {
		this.welcomeDisplayUserType = userType;
		this.welcomeDisplayEvent = action;
		this.entranceDisplayController.createUserFromTypeAndID();
		
	}

	public void handicapButtonCallToController(String userType, boolean action) {
		this.welcomeDisplayUserType = userType;
		this.welcomeDisplayEvent = action;
		this.entranceDisplayController.createUserFromTypeAndID();
		
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
	
	public void createAccessControlServer(int portNumber)
	{
		this.accessControlServer = new AccessControlServer(portNumber);	
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
	
	// service to be tested
	public boolean findSpotForUser()
	{
		return this.entranceDisplayController.findSpotForUser();
	}
	
	// service to be tested
	public ParkingNotification setParkingNotification()
	{
		this.entranceDisplayController.setUpParkingDisplayNotification(entranceDisplayController.isFound(), 
				entranceDisplayController.getCurrentUserID(), pDisp);
		return pDisp;
	}
}

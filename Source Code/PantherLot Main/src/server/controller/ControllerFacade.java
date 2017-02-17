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
	
	private FacultyUser facUser;
	
	private FiuParkingUser fiuParkingUser;
	
	private GuestUser guestUser;
	
	private HandicappedUser handicappedUser;
	
	private ParkingUser parkUser;
	
	private StudentUser studUser;
	
	//
	private ParkedUsers parkedUsersDB;
	private ParkingSpot parkingSpot;
	
	
	
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
	
	
	/*
	 * WelcomeDisplay fields
	 */
	private boolean welcomeDisplayEvent;
	private String welcomeDisplayUserType = "";
	private String welcomeDisplayUserID = "";
	
	public void scanIDCallToController(String userID, String userType, boolean action) {
		this.welcomeDisplayEvent = action;
		this.welcomeDisplayUserID = userID;
		this.welcomeDisplayUserType = userType;
		this.entranceDisplayController.createUserFromTypeAndID();
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
}

package server.controller;
import client.maindisplay.DisplayDirections;
import client.maindisplay.ParkingNotification;
import client.maindisplay.SpotNumberDisplay;
import client.maindisplay.WelcomeDisplay;
import server.controller.EntranceDisplayController;



public class ControllerFacade {
	
	private AccessControlServer accessControlServer;
	
	public AccessControlServer getAccessControlServer() {
		return accessControlServer;
	}

	public void setAccessControlServer(AccessControlServer accessControlServer) {
		this.accessControlServer = accessControlServer;
	}

	private EntranceDisplayController entranceDisplayController;
	private EntranceDisplayController entrDispController;
	
	private FacultyUser facUser;
	
	private FiuParkingUser fiuParkingUser;
	
	private GuestUser gUser;
	
	private HandicappedUser hUser;
	
	private ParkingUser parkUser;
	
	private StudentUser studUser;
	
	
	/*
	 * Clients
	 */
	private WelcomeDisplay wDisp;
	private ParkingNotification pDisp;

	private SpotNumberDisplay sDisp;
	private DisplayDirections dDisp;
	
	public void associateClientReferences(WelcomeDisplay wDisp, ParkingNotification pDisp, SpotNumberDisplay sDisp,
			DisplayDirections dDisp) {
		this.wDisp = wDisp;
		this.pDisp = pDisp;
		this.sDisp = sDisp;
		this.dDisp = dDisp;
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
	}

	public void guestButtonCallToController(String userType, boolean action) {
		this.welcomeDisplayUserType = userType;
		this.welcomeDisplayEvent = action;
		
	}

	public void handicapButtonCallToController(String userType, boolean action) {
		this.welcomeDisplayUserType = userType;
		this.welcomeDisplayEvent = action;
		
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
}

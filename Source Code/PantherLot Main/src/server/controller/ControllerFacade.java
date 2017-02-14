package server.controller;
import server.controller.EntranceDisplayController;



public class ControllerFacade {
	
	private AccessControlServer accessControlServer;
	
	private EntranceDisplayController entranceDisplayController;
	private EntranceDisplayController entrDispController;
	
	private FacultyUser facUser;
	
	private FiuParkingUser fiuParkingUser;
	
	private GuestUser gUser;
	
	private HandicappedUser hUser;
	
	private ParkingUser parkUser;
	
	private StudentUser studUser;
	
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
	
	
//	
}

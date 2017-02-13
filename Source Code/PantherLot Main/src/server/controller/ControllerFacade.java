package server.controller;



import server.storage.ParkedUsers;
import server.storage.serverStorageFacade;
import server.storage.ParkingSpot;


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
	
	
	private serverStorageFacade storageFacade;	
	private ParkedUsers parkedUsers;
	
	public ControllerFacade()
	{
		
	}
	
	//-------------- Student User ------------------------------
	// Should constructor go here?
	public String studentUserToString()
	{
		return studUser.toString();
	}
	//-------------Handicapped User -----------------------------
	
	public String handicappedUserToString()
	{
		return hUser.toString();
	}
	// ------------Guest User ---------------------------------
	
	public String guestUserToString()
	{
		return gUser.toString();
	}
	// ----------- FiuParkingUser-----------------------------
	
	public String getFiuParkingUserName()
	{
		return fiuParkingUser.getName();
	}
	
	public String getFiuParkingUserID()
	{
		return fiuParkingUser.getUserID();
	}
	// ---------- Faculty User --------------
	public String facultyUserToString()
	{
		return facUser.toString();
	}
	
	// ----------- EntranceDisplayController ------------------
	
	// create getters for
	public void dispControllerResetInstances()
	{
		// resetInstances() is private
	}
	
	public boolean isDuplicate(String id)
	{
		return entrDispController.isDuplicate(id);
	}
	
	/*
	 * Use getters and setters here to test the method
	 */
	public void entranceDisplayRunDisplays()
	{
		entrDispController.runDisplays();
	}
	
	public void getDuplicateParkingSpot(String ID)
	{
		entrDispController.getDuplicateParkingSpot(ID);
	}
	
	public void createUser()
	{
		// Private
	}
	
	public void searchFiu(String ID)
	{
		entrDispController.searchFiu(ID);
	}
	// COnstructors
	public AccessControlServer newAccessControlServer(int portNumber)
	{
		this.accessControlServer = new AccessControlServer(portNumber);
		return this.accessControlServer;
	}
	
	public EntranceDisplayController newEntranceDisplayController() {
		this.entranceDisplayController = new EntranceDisplayController();
		return this.entranceDisplayController;
	}
	
	// Access Control server methods 
	public void startServer(){
		this.accessControlServer.start();
	}
	
	synchronized public void reserveSpot(ParkingSpot spot, String id)
	{
		this.accessControlServer.reserveSpot(spot, id);
	}
	
	synchronized void duplicateIdFound(String msg, String msg2)
	{
		 this.accessControlServer.duplicateIdFound(msg, msg2);
	}
	// AccessControlServer methods end
	
	// Entrance Display COntroller Methods Start
	public void runDisplays(){
		entranceDisplayController.runDisplays();
	}
	
	public ParkingSpot getSpot() 
	{
		return this.entranceDisplayController.getSpot();
	}
	
	public String getCurrentUserID(){
		return this.entranceDisplayController.getCurrentUserID();
	}
	
	public boolean getDuplicate() {
		return this.entranceDisplayController.getDuplicate();
	}
	
	/*public ParkingSpot getDuplicateParkingSpot(String id)
	{
		return this.entranceDisplayController.getDuplicateParkingSpot(id);
	}*/
	// Entrance Display COntroller Methods End
	
	public static ParkedUsers getInstanceOf(String name){
		return serverStorageFacade.getInstanceOf(name);
	}
	public static void printOut(Object obj){
		System.out.println(obj);
	}
}

package server.controller;



import server.storage.ParkedUsers;
import server.storage.serverStorageFacade;
import server.storage.ParkingSpot;


public class ControllerFacade {
	
	private AccessControlServer accessControlServer;
	
	private ParkedUsers parkedUsers;
	
	private EntranceDisplayController entranceDisplayController;
	
	private serverStorageFacade storageFacade;
	
	public ControllerFacade()
	{
		
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
	
	public ParkingSpot getDuplicateParkingSpot(String id)
	{
		return this.entranceDisplayController.getDuplicateParkingSpot(id);
	}
	// Entrance Display COntroller Methods End
	
	public static ParkedUsers getInstanceOf(String name){
		return serverStorageFacade.getInstanceOf(name);
	}
	public static void printOut(Object obj){
		System.out.println(obj);
	}
}

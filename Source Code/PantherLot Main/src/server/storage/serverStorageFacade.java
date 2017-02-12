package server.storage;

public class serverStorageFacade {

	private ParkedUsers parkedUser;
	
	private ParkingSpot parkingSpot;
	
	public serverStorageFacade() {
		
	}
	
	
	static public ParkedUsers getInstanceOf(String name) {
		return ParkedUsers.instance(name);
	}
}

package server.controller;


import server.storage.ParkedUsers;
import server.storage.ParkingSpot;

/**
 * This is the main running class for the parking display, 
 * the server and the storage
 * @author Team5
 */
public class AccessControlMain 
{
    /**
     * 
     * @param args
     */
    @SuppressWarnings("static-access")
	public static void main(String[] args) 
    {
    	
    	AccessControlFacade controlFacade = new AccessControlFacade();
    	
//        AccessControlServer server = new AccessControlServer(3738);
    	AccessControlServer server = controlFacade.newAccessControlServer(3738);
//        server.start();
    	controlFacade.startServer();
        
//        ParkedUsers garage = ParkedUsers.instance("garage.txt");
    	ParkedUsers garage = controlFacade.getInstanceOf("garage.txt");
        
//        System.out.println(garage);
    	controlFacade.printOut(garage);
        
//        EntranceDisplayController eDisp = new EntranceDisplayController();  
    	EntranceDisplayController eDisp = controlFacade.newEntranceDisplayController();
        while(true)
        {
//            eDisp.runDisplays();
        	controlFacade.runDisplays();
            if(controlFacade.getSpot() != null)
            {
                if(controlFacade.getCurrentUserID().length() > 2 
                        && !controlFacade.getDuplicate())
                    controlFacade.reserveSpot(controlFacade.getSpot(), 
                            controlFacade.getCurrentUserID());
                else
                    controlFacade.reserveSpot(controlFacade.getSpot(), "no id");
            }
            else if(controlFacade.getDuplicate())
            {
                String ID = controlFacade.getCurrentUserID();
                ParkingSpot dup = controlFacade.getDuplicateParkingSpot(ID);
//                System.out.println("sending notification");
                controlFacade.printOut("Sending notification");
                server.duplicateIdFound("User with ID:" + ID + 
                        " has reported an stolen ID.","The car with the same ID"
                        + " is parked on spot #" + dup.getParkingNumber());
            }
        }
        
    }   
}

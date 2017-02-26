
package server.controller;

import client.maindisplay.DisplayDirections;
import client.maindisplay.ParkingNotification;
import client.maindisplay.SpotNumberDisplay;
import client.maindisplay.WelcomeDisplay;
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
    public static void main(String[] args) 
    {
    	ControllerFacade facade = new ControllerFacade();
    	facade.createAccessControlServer(3738);
    	facade.startAccessControlServer();
    	
        
        ParkedUsers garage = ParkedUsers.instance("garage.txt");
        
        System.out.println(garage);
        
        EntranceDisplayController eDisp = facade.createEntranceDisplayController(facade);        
        while(true)
        {
            facade.startDisplayLogic();
            if(facade.getParkingSpotObject() != null)
            {
                if(facade.currentUserIDlengthGreaterThan2()  && !eDisp.getDuplicate())
                	facade.reserveSpot(eDisp.getSpot(), eDisp.getCurrentUserID());
                else
                	facade.reserveSpot(eDisp.getSpot(), "no id");
            }
            else if(eDisp.getDuplicate())
            {
            	facade.duplicateIdFound();
            }
        }
        
    }
}

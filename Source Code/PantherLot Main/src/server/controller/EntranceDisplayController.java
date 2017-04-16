
package server.controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import client.maindisplay.DisplayDirections;
import client.maindisplay.ParkingNotification;
import client.maindisplay.SpotNumberDisplay;
import client.maindisplay.WelcomeDisplay;
import client.maindisplay.Display;
import java.awt.Point;
import server.storage.ParkedUsers;
import server.storage.ParkingSpot;
/*
 * main class that runs the GUI for the display at the entrance of the garage
 */
/**
 * 
 * @author Team5
 */
public class EntranceDisplayController
{
   
    private String userID, userName, userType;
    private boolean found,valid, duplicate;
    private WelcomeDisplay wDisp;
    private ParkingNotification pDisp;
    private SpotNumberDisplay sDisp;
    private DisplayDirections dDisp;
    private String message1,message2;  
    private HashMap<String,ParkingSpot> duplicates = 
            new HashMap<String, ParkingSpot>();
    private ParkingUser user;
    private ParkingSpot spot;
    
    private ParkedUsers garage = ParkedUsers.getInstance();
    
    private ControllerFacade facade;
    
    /**
     * default constructor that initializes everything to false
     */
    public EntranceDisplayController(ControllerFacade fac)
    {
        this.facade = fac;
        resetInstances();
    }
    
    
    /*
     * resets all the values gathered so that when the next user is 
     * shown the display screens there is no information that was 
     * kept from the previous one
     */
    
    private void resetInstances()
    {
        userName = "";
        userID = "";
        userType = "";
        message1 = "";
        message2 = "";        
        
        wDisp = facade.createNewWelcomeDisplay();
        pDisp = facade.createNewParkingNotificationDisplay();
        sDisp = facade.createNewSpotNumberDisplay();
        dDisp = facade.createNewDisplayDirectionDisplay();
        
        user = null;
        spot = null;
        
        valid = false;
        found= false;
        duplicate = false;
    }
    
    /**
     * accessor
     * @return the parking spot assigned to the current user
     */
    public ParkingSpot getSpot()
    {
        return spot;
    }
    
    /**
     * method that checks to see if there is an user with the same ID
     * @param id the id to check if it is already contained in the garage
     * @return true iff there is an user with the same ID already parked
     */
    public boolean isDuplicate(String id)
    {
        if(!duplicates.containsKey(id))
            return false;
        ParkingSpot s = duplicates.get(id);
        if(s.isAvailable())
        {
            duplicates.remove(id);
            return false;
        }
        return true;
    }

    /**
     * runs all the main display in a ordered sequence
     */
    public void runDisplays()
    {
        
        resetInstances();

        wDisp.runDisplay(new Point(0,0));
             
        createUserFromTypeAndID();
        
        findSpotForUser();
        
        setUpParkingDisplayNotification(found, userID, pDisp);

        pDisp.runDisplay(wDisp.getLocation());	// client service		
        wDisp = null;						// deletes the welcomeDisplay by removing the reference, could instead set the display to false?
        
        
        duplicate = isDuplicate(userID);
        /*
         * Don't know how to refactor the following code into its own structure, don't think its possible.
         * Checks for conditions of program. 
         * If the user clicked Cancel on the Parking Notificaiton display then program calls retunr and runDisplays() ends
         * If the user used a duplicate ID then the reserved spot get's set to null and runDisplays() ends
         * If no available parking spot was found, then runDisplays() ends
         * else add the user to the parking garage.
         */
        if(pDisp.isCanceled())
        {
            duplicate = false;
            return;
        }
        else if(duplicate)
        {
            System.out.println("here");
            spot = null;
            return;
        }
        else if(!found)
            return;
        else
            garage.addParkingUser(spot, user);
        
        
        pDisp.setVisible(false);		
        displayParkingSpotAssigned();
        pDisp = null;						
       
        if(sDisp.isCanceled())
        {
            garage.removeParkedUser(spot);
            return;
        }
        

        sDisp.setVisible(false);

        displayDirectionsToParkingSpot();
        sDisp = null;						// SpotNumberDisplay is gone

        
        if(dDisp.isCanceled())
        {
            garage.removeParkedUser(spot);
            return;
        }
        if(userID.length() > 2)
            duplicates.put(userID, spot);
    }
    
    /**
     * accessor
     * @return true iff there is an user with the same ID already parked
     */
    public boolean getDuplicate()
    {
        return duplicate;
    }
    /**
     * accessor
     * @param ID the duplicate ID
     * @return the parking spot where the car with the stolen id is located
     */
    public ParkingSpot getDuplicateParkingSpot(String ID)
    {
        return duplicates.get(ID);
    }
    /**
     * accessor
     * @return the current user at the main display
     */
    public String getCurrentUserID()
    {
        return userID;
    }
    /**
     * creates the parking user with the information provided by the displays
     */
    private void createUser()
    {
        if(userType.equalsIgnoreCase("guest"))
            user = new GuestUser();
        else if(userType.equalsIgnoreCase("handicap"))
            user = new HandicappedUser();        
        else
        {
            searchFiu(userID);
            if(userType.equalsIgnoreCase("student"))
                user = new StudentUser(userID, userName);
            else if(userType.equalsIgnoreCase("faculty"))
                user = new FacultyUser(userID, userName);
            else if(userType.equalsIgnoreCase("handicapped"))
                user = new HandicappedUser();
            else
                user = new GuestUser(); 
        }
        
    }
    
    /*
     * searches the FIU database for the given user ID	
     * @param ID String containing the userID of the parking user.
     * @return the name of the parking user
     * 
     * It obviously doesn't return anything, what the original
     * developers meant is that this variable sets the instance variable
     * userType to whatever it reads from the text file
     */
    void searchFiu(String ID)
    {
        //read text file
        String line = "";
        //traverses the whole file line by line
        try
        {
            BufferedReader reader = new BufferedReader(
                                            new FileReader("FiuDB.txt"));
            while((line = reader.readLine()) != null)
            {
                String[] tokens = line.split(" ");
                if(tokens[0].equalsIgnoreCase(userID))
                {
                    userType = tokens[1];
                    for(int i = 2; i < tokens.length; i++)
                    {
                        userName += tokens[i];
                        if(i + 1 != tokens.length)
                            userName += " ";
                    }
                    reader.close();
                    return;
                }
            }
            userType = "Invalid";
            reader.close();
        }
                
        //handles any exception caused by reading the file
        catch(IOException e)
        {
            System.out.println("There was an error while reading the file");
        }
    }

    /**
     * generates the message to be displayed by the second display screen 
     * the sequence
     */
    private void generateMessage(boolean f, boolean val, String userID)
    {      
        found = f;
        valid = val;
        boolean duplicate = isDuplicate(userID);
        if (!duplicate) {
	        if(found && valid)
	        {
	            message1 = "Valid Request ";
	            message2 = "Assigning " + user.toString().toLowerCase() 
	                    + " parking spot";
	        } 
	        else if(!found && valid)
	        {
	            message1 = "Valid Request ";
	            message2 = "There are no " 
	                        + user.toString().toLowerCase()  
	                        + " spots available";
	        }
	        else if(found && !valid)
	        {
	            message1 = "Invalid ID! ";
	            message2 = "Assigning guest parking spot";
	        }
	        else
	        {
	            message1 = "Invalid ID! ";
	            message2 = "There are no guest "
	                    + "spots avialable";
	        }
	        
	    } else {
	    	message1 = "Duplicate ID! ";
	    	message2 = "Press next to notify the security officer";
	    }
    }


    public ParkingUser createUserFromTypeAndID()
    {
    	storeIDinformationFromClient();
    	createUser();
    	return this.getUser();
    }
    
    public void storeIDinformationFromClient()
    {
    	userType = wDisp.returnType();
    	userID = wDisp.getID();
    }
    

    public void setUpParkingDisplayNotification( boolean found, String userID, ParkingNotification parkingDisplay)
    {
    	boolean userTypeValid = !userType.equalsIgnoreCase("invalid");
    	generateMessage(found, userTypeValid, userID);
    	parkingDisplay.updateParkingNotification(message1, message2);
    }
    
    /*
     * should be used to test setUpParkingDIsplayNotification service	
     * also part of use case PLI019 because a handicapp spot has to be found for user
     * however it does not need to be tested because this is a server.storage service
     */
    public boolean findSpotForUser() 
    {
    	spot = garage.searchParkingSpot(user);
    	found = (spot != null);
    	return found;
    }
    
    /*
     * Dependendies: SpotnumberDisplay sDisp
     * 				ParkingNotification pDisk
     */
    public String displayParkingSpotAssigned()
    {	
    	String parkingSpotLabel = "Your spot number is " + spot.getParkingNumber();
    	sDisp.updateParkingSpotNumberLabel(parkingSpotLabel);
    	sDisp.runDisplay(pDisp.getLocation());
    	return parkingSpotLabel;
    }
    /*
     * Dependencies: ParkingSpot spot, SpontNumberDisplay sDisp
     */
    public String displayDirectionsToParkingSpot()
    {
    	String directions = spot.createParkingDirections();
    	dDisp.updateDirections(directions);
    	dDisp.runDisplay(sDisp.getLocation());
    	return directions;
    }


////////////// Eclipse Generated Setters and Getters - Helps with Testing /////////////////
	public String getUserID() {
		return userID;
	}


	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public boolean isFound() {
		return found;
	}


	public void setFound(boolean found) {
		this.found = found;
	}


	public boolean isValid() {
		return valid;
	}


	public void setValid(boolean valid) {
		this.valid = valid;
	}


	public String getMessage1() {
		return message1;
	}


	public void setMessage1(String message1) {
		this.message1 = message1;
	}


	public String getMessage2() {
		return message2;
	}


	public void setMessage2(String message2) {
		this.message2 = message2;
	}


	public HashMap<String, ParkingSpot> getDuplicates() {
		return duplicates;
	}


	public void setDuplicates(HashMap<String, ParkingSpot> duplicates) {
		this.duplicates = duplicates;
	}


	public ParkingUser getUser() {
		return user;
	}


	public void setUser(ParkingUser user) {
		this.user = user;
	}


//	public ParkedUsers getGarage() {
//		return garage;
//	}


	public void setGarage(ParkedUsers garage) {
		this.garage = garage;
	}


	public void setDuplicate(boolean duplicate) {
		this.duplicate = duplicate;
	}


	public void setSpot(ParkingSpot spot) {
		this.spot = spot;
	}


	public WelcomeDisplay getwDisp() {
		return wDisp;
	}


	public void setwDisp(WelcomeDisplay wDisp) {
		this.wDisp = wDisp;
	}


	public ParkingNotification getpDisp() {
		return pDisp;
	}


	public void setpDisp(ParkingNotification pDisp) {
		this.pDisp = pDisp;
	}


	public SpotNumberDisplay getsDisp() {
		return sDisp;
	}


	public void setsDisp(SpotNumberDisplay sDisp) {
		this.sDisp = sDisp;
	}


	public DisplayDirections getdDisp() {
		return dDisp;
	}


	public void setdDisp(DisplayDirections dDisp) {
		this.dDisp = dDisp;
	}
    

}

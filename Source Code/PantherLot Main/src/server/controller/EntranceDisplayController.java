
package server.controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import client.maindisplay.DisplayDirections;
import client.maindisplay.ParkingNotification;
import client.maindisplay.SpotNumberDisplay;
import client.maindisplay.WelcomeDisplay;
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
    private Point p;
    
    private ParkedUsers garage = ParkedUsers.getInstance();
    
    /**
     * default constructor that initializes everything to false
     */
    public EntranceDisplayController()
    {
        p = new Point(0, 0);
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
        
        wDisp = new WelcomeDisplay();
        pDisp = new ParkingNotification();
        sDisp = new SpotNumberDisplay();
        dDisp = new DisplayDirections();
        
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
        wDisp.setLocation(p);
        wDisp.setVisible(true);
        while (!wDisp.displayNext())
        {
            try
            {
                Thread.sleep(100);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        
        userType = wDisp.getType();
        userID = wDisp.getID();
            
        createUser();       
        spot = garage.searchParkingSpot(user);
        
        found = (spot != null);
        valid = !userType.equalsIgnoreCase("invalid"); 
        generateMessage(found, valid);
        pDisp.updateParkingNotification(message1, message2);
        duplicate = isDuplicate(userID);
        if(duplicate)
        {
            pDisp.updateParkingNotification("Duplicate ID! ",
                     "Press next to notify the security officer");
        }
        p = wDisp.getLocation();
        pDisp.setLocation(p);
        wDisp = null;
        pDisp.setVisible(true);
        while(!pDisp.displayNext())
        {
            try
            {
                Thread.sleep(100);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        
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
        
        p = pDisp.getLocation();
        pDisp = null;
        sDisp.updateParkingSpotNumberLabel("Your spot number is " + 
                                             spot.getParkingNumber());
        sDisp.setLocation(p);
        sDisp.setVisible(true);
        while(!sDisp.displayNext())
        {
            try
            {
                Thread.sleep(100);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        if(sDisp.isCanceled())
        {
            garage.removeParkedUser(spot);
            return;
        }
        
        p = sDisp.getLocation();
        sDisp = null;
        dDisp.setLocation(p);
        dDisp.updateDirections("1. Go to floor #" 
                + spot.getFloor() + "\n2. Head to the " 
                    + spot.getDirections() + " part." +
                    "\n3. Park on " + spot.getUser().toString() 
                            + " spot labeled #" + spot.getParkingNumber()+ ".");
        dDisp.setVisible(true);
        while(!dDisp.displayNext())
        {
            try
            {
                Thread.sleep(100);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        if(dDisp.isCanceled())
        {
            garage.removeParkedUser(spot);
            return;
        }
        if(userID.length() > 2)
            duplicates.put(userID, spot);
        p = dDisp.getLocation();
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
    private void generateMessage(boolean f, boolean val)
    {      
        found = f;
        valid = val;
        
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
        
    }
    
}

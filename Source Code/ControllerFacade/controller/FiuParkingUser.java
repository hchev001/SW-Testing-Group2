package server.controller;

/* This is an abstract class that defines the parking users that are members of 
 * the FIU community
 */

/**
 * 
 * @author Team5
 */
public abstract class FiuParkingUser extends ParkingUser 
{
    
    /**
     * stores the name of the FIU Parking User
     */
    /**
     * stores the ID of the FIU Parking User
     */
    protected String name, userID;
 
    /**
     * Constructor initializes the variables name and userID for the FIU members
     * @param nm a String containing the first name and last name 
     * @param userID a String that contains the ID of the parking user
     */
    public FiuParkingUser(String nm, String userID) 
    {
        this.name = nm;
        this.userID = userID;
    }

    /**
     * Accessor to get the name of the FIU member parking user
     * @return the first name and last name of the parking user
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Accessor to get the ID of the FIU member parking user
     * @return the ID of the parking user
     */
    public String getUserID() 
    {
        return userID;
    }
}

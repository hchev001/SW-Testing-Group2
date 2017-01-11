
package server.controller;
/*
 * This is an entity class to create a faculty user object who is a parking user
 */

/**
 * @author Team 5
 */
public class FacultyUser extends FiuParkingUser
{
    
    /**
     * Constructor that initializes the faculty parking user object
     * @param name String containing the name of the faculty user
     * @param userID String containing the ID of the faculty user
     */
    public FacultyUser(String name, String userID) 
    {
        super(name, userID);
    }
    
    @Override
    public String toString() 
    {
        return "Faculty";
    }
}

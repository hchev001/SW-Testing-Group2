package server.controller;

import server.controller.FiuParkingUser;

/**
 * This is an entity class to create a student user object who is a parking user
 */
public class StudentUser extends FiuParkingUser 
{   
    /**
     * 
     * @param name String containing the name of the student user
     * @param userID String containing the ID of the student user
     */
    public StudentUser(String name, String userID) 
    {
        super(name, userID);
    }



    
    @Override
    public String toString() 
    {
        return "Student";
    }
}

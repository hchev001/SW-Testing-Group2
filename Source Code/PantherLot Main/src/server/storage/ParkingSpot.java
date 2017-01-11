
package server.storage;

import server.controller.ParkingUser;
import java.io.PrintWriter;
/*
 * This is where the object containing all the information 
 * of each spot is created.
 */
/**
 * @author Team5
 */
public class ParkingSpot implements Comparable
{
    
    int floor;
    String direction;
    PrintWriter pout;
    
    /*
     * String that stores the number of the parking spot
     */
    private String parkingNumber;
    /*
     * String that stores the type of the parking spot
     */
    private String parkingType;

    /*
     * int that stores the parking number position of the parking spot
     */
    private int parkingSpot;
    /*
     * parking user object that stores the user currently parked on the 
     * parking spot
     */
    private ParkingUser user;
    /**
     * Constructor that initializes the parking spot object with the given
     * information
     * @param pSpot Integer that stores the spot number of the specific spot
     * @param type 
     * @param pNumber String storing 
     * @param fl 
     * @param direc  
     */
    public ParkingSpot(int pSpot, String type, 
            int fl, String pNumber, String direc)
    {
        parkingSpot = pSpot;
        parkingType = type;
        floor = fl;
        
        parkingNumber = pNumber;
        direction = direc;
        
        pout = null;
        user = null;

    }
    
    /**
     * Mutator that assigns a parking user to the parking spot
     * @param pu Parking user object storing the information of the person
     * parking
     */
    public void assignParkingSpot(ParkingUser pu)
    {
        user = pu;
    }
    
    /**
     * Mutator that removes a parking user to the parking spot
     * removes the user that is currently parked on this spot
     */
    public void removeAssignedUser()
    {
        user = null;
    }
        
    
    /**
     * Accessor that returns the parking user object
     * @return the user currently parked on this spot
     */
    public ParkingUser getUser()
    {
        return user;
    }
    
    
    /**
     * Accessor that returns the parking spot
     * @return the number of the parking spot
     */
    public int getParkingSpot()
    {
        return parkingSpot;
    }
    
    /**
     * 
     * @return
     */
    public String getParkingNumber()
    {
        return parkingNumber;
    }
    
    /**
     * 
     * @return
     */
    public int getFloor()
    {
        return floor;
    }
    
    /**
     * accessor
     * @return the printwriter of this parking spot
     */
    public PrintWriter getPrintWriter()
    {
        return pout;
    }
    
    /**
     * mutator
     * @param p assigns this printwriter to the parking spot
     */
    public void setPrintWriter(PrintWriter p)
    {
        System.out.println("Setting Print " + p);
        pout = p;
        System.out.println("Print = " + pout);
    }
    
    /**
     * accessor 
     * @return the directions of this parking spot
     */
    public String getDirections()
    {
        return direction;
    }
    /**
     * accessor
     * @return true iff there is a screen connected to this spot
     */
    public boolean isConnected()
     {
         return (pout != null);
     }
     
     
    /**
     * Accessor that returns if the parking spot is available for use
     * @return if the spot is currently taken by an user or not
     */
    public boolean isAvailable()
    {
        return (user == null);
    }
    
    @Override
    public int compareTo(Object o) 
    {         
       return parkingSpot - ((ParkingSpot)o).getParkingSpot();
    }  
    
    @Override
    public String toString()
    {
        String image = parkingSpot + "";
        String ps = format(image, 3);
        String pt = format(parkingType, 11);
        image = floor + "";
        String f = format(image, 2);
        String pn = format(parkingNumber, 4);
        String d = format(direction, 5);
        image = ps + " Parking #" + pn + 
                " Type:" + pt 
                + " Floor:" + f + 
                " Direction:" + d;
        if(isAvailable())
        {
            image += " Status:Free ";
        }
        else
            image += " Status:Taken";
        if(isConnected())
            image += " Connection:On";
        else
            image += " Connection:Off";
        
        return image;
                    
    }
    /*
     * format the string so that it looks better on display
     */
    private String format(String s, int f)
    {
        String formatted = "";
        formatted += s;
        for(int i = s.length(); i < f; i++)
            formatted += " ";
        return formatted;
    }
    
    /**
     * accessor 
     * @return the type of parking this parking spot is for
     */
    public String getParkingType()
    {
        return parkingType;
    }

}

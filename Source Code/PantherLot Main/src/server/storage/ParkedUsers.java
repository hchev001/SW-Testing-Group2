package server.storage;

import server.controller.ParkingUser;
import java.util.HashMap;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is used to assign and remove user from parking spots.
 * It also stores all the parking spots of the garage according to their type
 */
public class ParkedUsers 
{
    private static HashMap<String,TreeSet<ParkingSpot>> garage = null;
    ArrayList<String> fileLines = new ArrayList<String>();
    private static ParkedUsers singleton;
    ArrayList<String> status = new ArrayList<String>();
    /**
     * Constructor that initializes the map and maps the garage from the given
     * text file
     * @param name name of the text file containing the garage information
     */
    protected ParkedUsers(String name)
    {
        garage = new HashMap<String,TreeSet<ParkingSpot>>();
        mapGarage(name);
        
    }
    
    /**
     * singleton initializer and accessor 
     * that returns the instance of the singleton
     * @param name name of the text file containing the garage information
     * @return
     */
    static public ParkedUsers instance(String name)
    {
        if(singleton == null)
            singleton = new ParkedUsers(name);
        return singleton;
    }
    /**
     * accessor
     * also updates the status before returning it
     * @return status to be send to security
     */
    public ArrayList<String> getStatus()
    {
        toString();
        return status;
    }
    /**
     * accessor
     * @return an instance of the singleton
     */
    static public ParkedUsers getInstance()
    {
        return singleton;
    }
    
    /*
     * private helper method that reads a text file to map the parking 
     * garage parking spots
     */
    private void mapGarage(String file)
    {
        //read text file
        String line = "";
        //traverses the whole file line by line
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine()) != null)
                fileLines.add(line);
            reader.close();
        }
                
        //handles any exception caused by reading the file
        catch(IOException e)
        {
            System.out.println("There was an error while reading the file");
        }
        processFile(fileLines);
        
    }
     
    /*
     * private helper method that process the file in order to map the garage
     */
    private void processFile(ArrayList<String> array)
    {
        for(String x: array)
        {
            String[] tokens = x.split(" ");
            int parkingSpot = Integer.parseInt(tokens[0]);
            String parkingType = tokens[1];
            int floor = Integer.parseInt(tokens[2]);
            String parkingNumber = tokens[3];
            String directions = tokens[4];
            ParkingSpot p = new ParkingSpot(parkingSpot, parkingType, 
                floor, parkingNumber, directions);
            
            if(garage.get(parkingType) != null)
                garage.get(parkingType).add(p);

            else
            {
                TreeSet<ParkingSpot> t = new TreeSet<ParkingSpot>();
                t.add(p);
                garage.put(parkingType, t);
            }
        }
        
    }
    
    /**
     * Searches for a free parking spot to assign to the user.
     * @param user the user that is looking for a parking spot
     * @return the spot that will be assigned to the user
     */
    public ParkingSpot searchParkingSpot(ParkingUser user)
    {
        TreeSet<ParkingSpot> userType = garage.get(user.toString());
        ParkingSpot spot = null;
        for(ParkingSpot x: userType)
            if(x.isAvailable() && x.isConnected())
            {
                spot = x;
                break;
            }
                
        return spot;
    }
    
    /**
     * method to add a parking user to an specific spot on the garage
     * @param spot Parking Spot that is free (does not contain a parking user)
     * @param user Parking User that will be assigned a parking spot
     */
    public void addParkingUser(ParkingSpot spot, ParkingUser user)
    {
        spot.assignParkingSpot(user);
    }
    
    
    /**
     * removes a parking user from a parking spot in the garage
     * @param spot Parking Spot that is taken
     */
    public void removeParkedUser(ParkingSpot spot)
    {
        spot.removeAssignedUser();
    }
    
    /**
     * searches the hashmap for a spot with the provided spot number
     * @param number the parking spot number to do the search by
     * @return the spot with the corresponding spot number
     */
    public ParkingSpot searchBySpotNumber(String number)
    {
        ParkingSpot spot = null;
        for(TreeSet<ParkingSpot> t: garage.values())
            for(ParkingSpot p: t)
                if(p.getParkingNumber().equalsIgnoreCase(number))
                    spot = p;
        return spot;
    }
    
    @Override
    public String toString()
    {
        status = new ArrayList<String>();
        String image = "";
        for(TreeSet<ParkingSpot> t: garage.values())
            for(ParkingSpot p: t)
            {
                status.add(p.toString());
                image += p + "\n";
            }
        
        return image;
    }

    /**
     * accessor
     * @return an iterable of the hashmap of the garage
     */
    public Iterable<TreeSet<ParkingSpot>> values() {
        return garage.values();
    }

}

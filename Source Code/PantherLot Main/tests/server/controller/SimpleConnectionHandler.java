package server.controller;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import server.controller.GuestUser;
import server.controller.ParkingUser;
import server.storage.ParkedUsers;
import server.storage.ParkingSpot;

public class SimpleConnectionHandler {
	
	public Socket theSocket;
	public ParkingSpot spot;
	public static ParkedUsers garage = ParkedUsers.instance("garage.txt");
	public PrintWriter sout = null;
	public HashMap<String, PrintWriter> displayConnections = new HashMap<String, PrintWriter>();
	
    public void sendMessage(String msg, PrintWriter pout)
    {        
        pout.println(msg);
    }
    
    public void sendStatus()
    {
        ArrayList<String> stat = garage.getStatus();
        for(int i = 0;i < stat.size(); i++)
            sendMessage(stat.get(i), sout);
    }
    
    public boolean isConnectionAvailable(String key)
    {
        return (displayConnections.get(key) == null);
    }
    
    synchronized private void addDisplay(String key, PrintWriter out, 
            ParkingSpot spot)
    {
        if(!displayConnections.containsKey(key))
            System.out.println( "Invalid spot number.");
        else 
        {
            displayConnections.put(key, out);
            System.out.println( "Connected display to spot successfully.");
            spot.setPrintWriter(out);
        }
    }
    
    synchronized void wrongUserDetected(String msg)
    {
        if(sout == null)
            return;
        sendMessage("wrong", sout);
        sendMessage(msg, sout);
    }
    
	public SimpleConnectionHandler(Socket sock)
    {
        theSocket = sock;
    }
	
	/** TESTING STARTS BELOW ---------------------------------*/
    /*
     * runs the thread for the security display client
     */
    public void runSecurityDisplay(Scanner scan, PrintWriter pout)
    {
        if(sout != null)
        {
            sendMessage("another", pout);
            return;
        }
        sout = pout;
        sout.println("successful connection!");
        
        while(theSocket.isConnected())
        {
            if(theSocket.isClosed())
                break;
            try 
            {
                Thread.sleep(2000);
            } 
            catch (InterruptedException e) 
            {
                System.out.println(e);
            }
            sendStatus();
        }

        sout = null;
        displayConnections.put("security", null);  
    }
    /**
     * runs the displays threads for each of the parking display clients
     */
    public void runParkingDisplay(Scanner scan, PrintWriter pout, 
            String spotNumber)
    {
        
        spot = garage.searchBySpotNumber(spotNumber);
        
        if(!isConnectionAvailable(spotNumber))
        {
            System.out.println("Another display is connected to  spot #" 
                                + spotNumber);
            sendMessage("another", pout);
            return;
        }
        
        addDisplay(spotNumber, pout, spot);
        sendMessage(spot.getParkingType(), pout);
        boolean correct = true;
        while(scan.hasNextLine())
        {
            String oneLine = scan.nextLine();
            
            if(oneLine.equals("leave"))
            {
                if(correct)
                    spot.removeAssignedUser();
                else
                    correct = true;
            }
            
            else if(oneLine.equals("wrong"))
            {
                wrongUserDetected("Wrong user detected on parking spot #" 
                        + spot.getParkingNumber());
                if(spot.isAvailable())
                {
                    ParkingUser user = new GuestUser();
                    spot.assignParkingSpot(user);
                }
                else
                    correct = false;
            }
        }
        
        
    }
}

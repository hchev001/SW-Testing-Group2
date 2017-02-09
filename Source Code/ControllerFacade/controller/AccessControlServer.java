package server.controller;


import server.storage.ParkedUsers;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeSet;
import server.storage.ParkingSpot;

/**
 * This is the server class that controls all data access and flow.
 * It is also responsible of creating all the parking user objects
 * once it searches them in the FIU Database.
 */
public class AccessControlServer extends Thread
{
    private static ParkedUsers garage = ParkedUsers.instance("garage.txt");
    private final int portNum;
    private PrintWriter sout = null;
    private HashMap<String, PrintWriter> displayConnections 
                                    = new HashMap<String, PrintWriter>();
    
    /**
     * constructor that initializes the port number variable
     * @param p integer that contains the port number 
     * to which this server will listen to
     */
    public AccessControlServer(int p)
    {
        portNum = p;
        mapConnections();
    }
    /**
     * helper method that maps the text file into the hashmap
     */
    private void mapConnections()
    {
        
        sout = null;
        displayConnections.put("security", sout);
        for(TreeSet<ParkingSpot> t: garage.values())
            for(ParkingSpot p: t)
            {
                displayConnections.put(p.getParkingNumber(), 
                        p.getPrintWriter());
            }
    }
    
    @Override
    public void run()
    {
        try
        {
            startServer();
        }
        catch(IOException e)
        {
            System.out.println("Can't listen on port " + portNum);
        }
    }
    
    /**
     * initializes the server with the specified port number and 
     * starts listening for connections
     * @throws IOException exception thrown if there is a problem 
     * with the server
     */
    public void startServer() throws IOException
    {
        ServerSocket ss = new ServerSocket(portNum);
        while(true)
        {
            Socket s = ss.accept();
            Thread t = new Thread(new ConnectionHandler(s));
            t.start();
        }
    }    
    
    /**
     * method to send a message to a client
     * @param msg sends a string message to the specified client
     * @param pout the print writer connection to the client
     */
    public void sendMessage(String msg, PrintWriter pout)
    {        
        pout.println(msg);
    }

    /**
     * send the security display the status of all the parking spots
     */
    public void sendStatus()
    {
        ArrayList<String> stat = garage.getStatus();
        for(int i = 0;i < stat.size(); i++)
            sendMessage(stat.get(i), sout);
    }
    
    /**
     * method called to reserve a spot for the user
     * @param spot the spot to be reserved by the user
     * @param id the ID of the user reserving the spot
     */
    synchronized public void reserveSpot(ParkingSpot spot, String id)
    {
        PrintWriter pout = displayConnections.get(spot.getParkingNumber());
        sendMessage("reserve", pout);
        sendMessage(id, pout);
    }
    /**
     * sends the message to the security display that an
     * user has parked on the wrong spot
     * @param msg the message to be sent
     */
    synchronized void wrongUserDetected(String msg)
    {
        if(sout == null)
            return;
        sendMessage("wrong", sout);
        sendMessage(msg, sout);
    }
    
    /**
     * sends the message to the security display that a 
     * duplicate ID was detected
     * @param msg the message to be sent
     */
    synchronized void duplicateIdFound(String msg, String msg2)
    {
        if(sout == null)
            return;
        sendMessage("duplicate", sout);
        sendMessage(msg, sout);
        sendMessage(msg2, sout);
    }


    /**
     * adds the client to the hashmap containing all the connections
     * @param key the key of the hashmap
     * @param out the print writer connection of the server to the client
     * @param spot the parking spot of the corresponding display
     */
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
    
    /**
     * removes the display from the hashmap containing all the connections
     * @param key the key of the hashmap
     * @param spot the parking spot of the corresponding display
     */
    synchronized private void removeDisplay(String key, ParkingSpot spot)
    {
        displayConnections.put(key, null);
        spot.setPrintWriter(null);
    } 
    
    /**
     * accessor
     * @param key the clients spot number
     * @return iff the connection is still alive with the client
     */
    public boolean isConnectionAvailable(String key)
    {
        return (displayConnections.get(key) == null);
    }
    /**
     * private class that handles each connection on a separate thread.
     */
    private class ConnectionHandler implements Runnable
    {
        /*
         * constructor of the private class
         */
        public ConnectionHandler(Socket sock)
        {
            theSocket = sock;
        }

        @Override
        public void run()
        {
            PrintWriter pout = null;
            String spotNumber = "";
            try
            {
                InputStream in = theSocket.getInputStream();
                OutputStream out = theSocket.getOutputStream();
                
                Scanner scan = new Scanner(in);
                pout = new PrintWriter(out, true);
                
                spotNumber = scan.nextLine();

                System.out.println(spotNumber);
                
                if(spotNumber.equalsIgnoreCase("security"))
                    runSecurityDisplay(scan, pout);
                else if(displayConnections.containsKey(spotNumber))
                    runParkingDisplay(scan, pout, spotNumber);
                
 
            }
                    
            catch( IOException e )
            {
                System.out.println( "Socket error: " + e + " " +
                                    theSocket.getRemoteSocketAddress( ) );
            }
            finally
            {   
                //removes the display from the list
                removeDisplay(spot.getParkingNumber(), spot);  
                try
                {
                    if( theSocket != null )
                        theSocket.close( );                    
                }
                catch( IOException e )
                {
                    System.out.println( "Socket error: " + e + " " +
                                    theSocket.getRemoteSocketAddress( ) );
                }

            }
        }
        
        /*
         * runs the thread for the security display client
         */
        private void runSecurityDisplay(Scanner scan, PrintWriter pout)
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
        private void runParkingDisplay(Scanner scan, PrintWriter pout, 
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
        private ParkingSpot spot;
        private Socket theSocket;
    }
}

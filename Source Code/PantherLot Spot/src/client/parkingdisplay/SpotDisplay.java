
package client.parkingdisplay;
/*
 * main class that runs the GUI for the parking spot screen
 */
/**
 *
 * @author Team5
 */
import java.awt.Point;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Spot Display class that handles the screen to be 
 * displayed on the parking spot
 * 
 */
public class SpotDisplay extends Thread
{
    private String userID;
    private boolean reserved;
    private boolean parked;
    private boolean connected;
    private Socket sock;
    private String spotNumber, address, spotType;
    private int port;
    private InputStream in;
    private OutputStream out;
    private Scanner scan;
    private PrintWriter pout;
    private SpotDisplayFrame sDisp;
    private SpotTakenFrame sTake;
    
    /**
     * constructor that creates a spot display object 
     * and connects it to the parking server
     * @param addr network address of the server to 
     * which the display will connect to
     * @param p port number to which this display will connect to
     * @param spot the the spot number to which 
     * this parking display corresponds to
     */
    public SpotDisplay(String addr, int p, String spot)
    {
        address = addr;
        port = p;
        userID = "";   
        spotNumber = spot;
    }
    
    @Override
    public void run()
    {
        sock = null;
        try
        {
            sock = new Socket( address, port );
            
            in = sock.getInputStream();
            out = sock.getOutputStream();
                
            scan = new Scanner(in);
            pout = new PrintWriter(out, true);
            
            
            pout.println(spotNumber);
            System.out.println(spotNumber);
            
            String oneLine = scan.nextLine();
            if(oneLine.equalsIgnoreCase("another"))
            {
                anotherDisplay();
                return;
            }
            else
                spotType = oneLine;
            connected = true;

            runDisplays();
            
            while(scan.hasNextLine())
            {
                oneLine = scan.nextLine();      
                if(oneLine.equalsIgnoreCase("reserve"))
                {
                    oneLine = scan.nextLine(); 
                    reserveSpot(oneLine);
                    sDisp.updateStatus("Parking Spot Status: Reserved");
                    reserved = true;
                }
            }
            
        }
        catch( IOException e )
        {
            String error = "Could not connect to " 
                    + address + " on port #" + port;
            JOptionPane.showMessageDialog(null, error);
        }
        finally
        {
            connected = false;
            
            try
            {
                if( sock != null )
                    sock.close( );
            }
            catch( IOException e )
            {
                System.out.println( e );
            }
        }
    }
    
    /**
     * reserves the parking spot for the given user in the garage
     * @param ID
     */
    public void reserveSpot(String ID)
    {
        userID = ID;
        
    }
    

    /**
     * handles the error message of when another 
     * display is connected to this same spot number
     */
    public void anotherDisplay()
    {
        String error = "Another display is already "
                            + "connected to  spot #" + spotNumber;
         JOptionPane.showMessageDialog(null, error);
    }

    /*
     * method that initializes the internal class to handle 
     * the display on a different thread
     */
    private void runDisplays()
    {
        Thread t = new Thread(new DisplayHandler());
        t.start();
    }
    /*
     * private class that handles the display threads
     */
    private class DisplayHandler implements Runnable
    {
        
        private void resetDisplays()
        {
            sDisp = null;
            sTake = null;
            sDisp = new SpotDisplayFrame();
            sTake = new SpotTakenFrame();
            sDisp.updateNumber("Parking Spot #" + spotNumber + " ");
            if(!reserved)
                sDisp.updateStatus("Parking Spot Status: Unassigned");
            else
                sDisp.updateStatus("Parking Spot Status: Reserved");
            sDisp.updateType("Parking Spot Type: " + spotType);
            sTake.updateParkingSpotNumberLabel("Parking Spot #" + spotNumber
                     + " ");
        }

        @Override
        public void run() 
        {
            int x = 0;
            int y = 500;
            Point p;
            resetDisplays();
            sDisp.setLocation(0, 500);
            p = sDisp.getLocation();
            while(sock.isConnected())
            {
                resetDisplays();
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
                
                if(userID.equalsIgnoreCase("no id"))
                {
                    System.out.println("if");
                    sTake.updateSpotNotificationLabel("Welcome to FIU!");
                    reserved = false;
                }
                else if(sDisp.getID().equalsIgnoreCase(userID) 
                        && !userID.isEmpty())
                {
                    System.out.println("elseif");
                    sTake.updateSpotNotificationLabel("Welcome to FIU!");
                    reserved = false;
                }
                else
                {
                    System.out.println("else");
                    sTake.updateSpotNotificationLabel("Wrong Parking Spot!");
                    pout.println("wrong");
                }
                
                p = sDisp.getLocation();
                sTake.setLocation(p);
                sTake.setVisible(true);
                while(!sTake.displayNext() && sock.isConnected())
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
                
                pout.println("leave");
            }
            
        }
    }
}


package client.security;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.JOptionPane;

/*
 * main class that runs the GUI for the security computer
 */
/**
 *
 * @author Team5
 */
public class SecurityDisplay
{
    private HashMap <String,String> display = new HashMap<String,String>();
    private int port, count;
    private String address;
    private boolean connected;
    private Socket sock;
    private InputStream in;
    private OutputStream out;
    private Scanner scan;
    private PrintWriter pout;
    private SecurityDisplayFrame sDisp;
    private String logMsg;
    private SecurityLog logFile;
    
    
    /**
     * Constructor of security display class
     * @param addr the network address to which the security client will connect
     * @param p the port number to which the security client will connect
     */
    public SecurityDisplay(String addr, int p)
    {
        address = addr;
        port = p;
        count = 0;
        logMsg = "\nThis message will be stored in log file.";
        logFile = new SecurityLog();
        connect();
    }
    /**
     * accessor
     * @return the logfile object from the security display
     */
    public SecurityLog getLog()
    {
        return logFile;
    }
    
    /*
     * private helper method that connects to the server 
     * and displays the screens
     */
    private void connect()
    {
        sock = null;
        try
        {
            sock = new Socket( address, port );
            
            in = sock.getInputStream();
            out = sock.getOutputStream();
                
            scan = new Scanner(in);
            pout = new PrintWriter(out, true);
            
            
            pout.println("security");
            System.out.println("security sent");
            
            String oneLine = scan.nextLine();
            if(oneLine.equalsIgnoreCase("another"))
            {
                anotherDisplay();
                return;
            }
            connected = true;
            sDisp = new SecurityDisplayFrame();
            sDisp.setVisible(true);
                    
            while(scan.hasNextLine())
            {
                oneLine = scan.nextLine();
                
                if(oneLine.equalsIgnoreCase("wrong"))
                {
                    oneLine = scan.nextLine();
                    popUp(oneLine, "Wrong Parking Notification");
                    saveLog(oneLine);
                }
                else if(oneLine.equalsIgnoreCase("duplicate"))
                {
                    oneLine = scan.nextLine();
                    String secondLine = scan.nextLine();
                    String log = oneLine + " " + secondLine;
                    String message = oneLine + "\n" + secondLine;
                    popUp(message, "Duplicate ID Notification");
                    saveLog(log);
                }
                else
                {
                    String key = oneLine.substring(0, 4);
                    String value = oneLine.substring(4);
                    display.put(key, value);
                    refreshDisplay();
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
     *  handles the error message of when another security display 
     *  is already connected to the server, this helps avoid someone 
     *  trying to impersonate a security
     */
    public void anotherDisplay()
    {
        String error = "Another security display is "
                + "already connected to this garage";
        JOptionPane.showMessageDialog(null, error);
    }

    private void refreshDisplay()
    {
        String image = "";
        for(String x: display.values())
            image += x + "\n";
        sDisp.updateTextArea(image);
    }

    /**
     * method invoked when a notification is received by the security display
     * that displays the notification to the security as a popup.
     * @param log a string containing the log that will appear on the popup
     * and later be stored in the security log
     * @param title  
     */
    private void popUp(String log, String title)
    {
        JOptionPane.showMessageDialog(null, log + logMsg, 
                title, 1);
    }
    
    private void saveLog(String log)
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(cal.getTime());
        logFile.storeLogMessage(date + ": " + log);
    }
}

package client.parkingdisplay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Team 5
 */
public class PantherLotSpot {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String spotNumber = JOptionPane.showInputDialog("Please enter "
                + "the parking spot number of the display");
        if(spotNumber.equalsIgnoreCase("all"))
        {
            try
            {
                String line;
                BufferedReader reader = new BufferedReader(
                        new FileReader("all.txt"));
                while((line = reader.readLine()) != null)
                {
                    Thread spot = new SpotDisplay("localhost", 3738, line);
                    spot.start();
                }
                reader.close();
            }
                
            //handles any exception caused by reading the file
            catch(IOException e)
            {
                System.out.println("There was an error while reading the file");
            }
        }
        else
        {
            Thread spot = new SpotDisplay("localhost", 3738, spotNumber);
            spot.start();
        }
        
        
    }
}

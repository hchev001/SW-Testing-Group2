
package client.security;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

/*
 * This is the class that creates and stores all notifications in a log.txt file
 */
/**
 * 
 * @author Team5
 */
public class SecurityLog 
{
    String logImage = "";
    /**
     * Constructor that initializes the log variable and 
     * creates the security log object
     */
    public SecurityLog()
    {
        String fileName = "Log.txt";
        File output = new File(fileName);
        if(output.exists())
            readFile(fileName);
    }
    
    /**
     * method used to store a log message on a text file
     * @param log String containing log data
     */
    void storeLogMessage(String log)
    {
        logImage += log + "\n";
        System.out.println(logImage);
        printFile("Log.txt");
    }    
    
    /**
     * prints the log into a text file
     * @param fileName the name of the file to which the output will be written
     */
    public void printFile(String fileName)
    {
        try
        {
            //takes the output string and writes it in a new file
            //called output.txt
            FileOutputStream out = new FileOutputStream(
                    new File(fileName), true);
            out.write(logImage.getBytes());
            out.flush();
            out.close();  
        }
        //catches all exceptions from writing into the .txt
        catch(Exception e)
        {
            System.out.println("There was an error while writing into file");
        }
    }
    
    /*
     * reads whatever was in the logfile when the object is constructed
     */
    private void readFile(String fileName)
    {
        System.out.println("reading File");
        try
        {
            String line;
            BufferedReader input =  new BufferedReader(
                    new FileReader(fileName));
            while((line = input.readLine()) != null)
                logImage += line + "\n";
        }
        catch(Exception e)
        {
            System.out.println("There was an error while reading the file");
        }
    }
}

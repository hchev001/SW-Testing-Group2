package client.maindisplay;

import java.awt.Point;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Display extends JFrame {
	
	private boolean action = false;
	
    public boolean isAction() {
		return action;
	}

	public static void runDisplay(Display display)
    {
    	display.setLocation(new Point(0, 0));
    	display.setVisible(true);
    	while (!display.displayNext())
    	{
    		try
    		{
    			Thread.sleep(100);
    		}
    		catch (Exception e)
    		{
    			System.out.println(e);
    		}
    	}
    		
    }
    
    synchronized public boolean displayNext()
    {
        if(action)
        {
            this.setVisible(false);
        }
        return action;
    }
}

package client.maindisplay;

import java.awt.Point;

import javax.swing.JFrame;

import server.controller.ControllerFacade;

@SuppressWarnings("serial")
public class Display extends JFrame {
	
	private boolean action = false;
	
    public boolean isAction() {
		return action;
	}

	public boolean runDisplay()
    {
		boolean exceptionDetected = false;
    	this.setLocation(new Point(0, 0));
    	this.setVisible(true);
    	while (!this.displayNext())
    	{
    		try
    		{
    			Thread.sleep(100);
    		}
    		catch (Exception e)
    		{
    			System.out.println(e);
    			exceptionDetected = true;
    		}
    	}
    	
    	return exceptionDetected;
    		
    }
    
    synchronized public boolean displayNext()
    {
        if(action)
        {
            this.setVisible(false);
        }
        return action;
    }
    
    ControllerFacade cFacade;
    
    public void setcFacade(ControllerFacade cFacade) {
		this.cFacade = cFacade;
	}
}

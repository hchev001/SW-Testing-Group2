
package client.maindisplay;
/*
 * Interface used to implement the command pattern by generalizing all
 * the different types of display
 */
/**
 *
 * @author Team5
 */
public interface Form 
{
    /**
     * display method to be uniquely implemented 
     * by the classes using this interface
     * @return iff the next display frame can be shown
     */
    public boolean displayNext();
}

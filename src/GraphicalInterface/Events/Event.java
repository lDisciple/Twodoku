package GraphicalInterface.Events;

/**
 *
 * @author jonathan
 */
/*****************************************************
*A class used to define an action that need to be done
* Much like AWT
*****************************************************/
public abstract class Event {
    protected String id = this.getClass().getSimpleName();

    /*****************
    *Get the events ID
    ******************/
    public String getId() {
        return id;
    }
    
}

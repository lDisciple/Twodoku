package GraphicalInterface.Events;

/**
 *
 * @author jonathan
 */
public class ChangeMenuEvent extends Event{
    private int menuID;
    /************************************
    *An event to prompt a change in menus
    ************************************/
    public ChangeMenuEvent(int menuID){
        this.menuID = menuID;
    }

    /********************
    *Get the new menus ID
    ********************/
    public int getMenuID() {
        return menuID;
    }

}

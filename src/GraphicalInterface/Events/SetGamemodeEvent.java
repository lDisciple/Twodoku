package GraphicalInterface.Events;

/**
 *
 * @author jonathan
 */
public class SetGamemodeEvent extends Event{
    private int gamemode;
    /******************************************
    *An Event for when the gamemode must be set
    ******************************************/
    public SetGamemodeEvent(int gamemode){
        this.gamemode = gamemode;
    }
    /*****************
    *Get the gamemode
    *****************/
    public int getGamemode() {
        return gamemode;
    }
}

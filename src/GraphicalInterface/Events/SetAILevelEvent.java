package GraphicalInterface.Events;

/**
 *
 * @author jonathan
 */
public class SetAILevelEvent extends Event{
    private int aiLevel;
    /******************************************
    *An Event for when the AI level must be set
    ******************************************/
    public SetAILevelEvent(int aiLevel){
        this.aiLevel = aiLevel;
    }
    /*****************
    *Get the AI level
    *****************/
    public int getAILevel() {
        return aiLevel;
    }
}

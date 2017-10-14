package GraphicalInterface.Events;

/**
 *
 * @author jonathan
 */
public class ChangeGridEvent extends Event{
    private int width;
    private int height;
    /*************************************
    *An event to prompt a grid size change
    **************************************/
    public ChangeGridEvent(int w,int h){
        this.width = w;
        this.height = h;
    }

    /***********************
    *Get the new grids width
    ************************/
    public int getWidth() {
        return width;
    }

    /***********************
    *Get the new grids height
    ************************/
    public int getHeight() {
        return height;
    }
}

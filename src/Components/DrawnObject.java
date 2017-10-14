package Components;

/**
 *
 * @author jonathan
 */
public abstract class DrawnObject {
    protected float x,y;
    protected float width, height;

    /*************************************************
    *Creates an object that can be drawn using StdDraw
    *************************************************/
    public DrawnObject(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    /**************************************
    *The draw call for this DrawnObject
    **************************************/
    public abstract void draw();

    /***********************
    *Get the objects x value
    ***********************/
    public float getX() {
        return x;
    }

    /***********************
    *Get the objects y value
    ***********************/
    public float getY() {
        return y;
    }

    /***********************
    *Sets the objects x value
    ***********************/
    public void setX(float x) {
        this.x = x;
    }

    /***********************
    *Sets the objects x value
    ***********************/
    public void setY(float y) {
        this.y = y;
    }

    /***********************
    *Get the objects width
    ***********************/
    public float getWidth() {
        return width;
    }

    /***********************
    *Sets the objects width
    ***********************/
    public void setWidth(float width) {
        this.width = width;
    }

    /***********************
    *Get the objects height
    ***********************/
    public float getHeight() {
        return height;
    }

    /***********************
    *Sets the objects height
    ***********************/
    public void setHeight(float height) {
        this.height = height;
    }
    
    
}

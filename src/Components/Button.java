/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import java.util.HashMap;

/**
 *
 * @author Jonathan Botha
 */
public abstract class Button extends DrawnObject {
    private boolean isVisible;
    
    private final HashMap<Byte,DrawnObject> imageMap;
    protected byte status;
    public byte BTN_NORMAL = 0;
    public byte BTN_CLICKED = 1;
    public byte BTN_HOVER = 2;
    
    /**************************************
    *Create a button of width:height at x:y
    **************************************/
    public Button(float xPos, float yPos, float width, float height, DrawnObject img, boolean isShowing){
        super(xPos,yPos,width,height);
        imageMap = new HashMap();
        imageMap.put((byte)-1,null);
        imageMap.put((byte)0,img);
        imageMap.put((byte)1,img);
        imageMap.put((byte)2,img);
        
        x = xPos;
        y = yPos;
        
        isVisible = isShowing;
    }
    
    @Override
    public void draw(){
        if (getImage() != null) {
            getImage().draw();
        }
    }
    
    /**
     *Checks if the co-ordinates are touching the button
     * @param x X co-ordinate
     * @param y Y co-ordinate
     * @return Whether the button is touching the co-ordinates.
     */
    public boolean inBounds(float x,float y){
        return this.x - getWidth()/2f < x && x < this.x + getWidth()/2f && this.y < y + getHeight()/2f && y < this.y + getHeight()/2f;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Image">
    /**
     *Gets buttons current image based on its status.
     * @return Button image
     */
    public DrawnObject getImage(){
        return imageMap.get(status);
    }
    
    /**
     *Sets the image that will appear when the mouse hovers over the button.
     * @param img Hover image.
     */
    public void setHoverImage(DrawnObject img){
        imageMap.put((byte)2, img);
    }
    
    /**
     *Gets the image that appears when the mouse hovers over the button.
     * @return Hover image.
     */
    public DrawnObject getHoverImage(){
        return imageMap.get((byte)2);
    }
    /**
     *Sets the image that will appear when the button is clicked.
     * @param img Clicked image.
     */
    public void setClickedImage(DrawnObject img){
        imageMap.put((byte)1, img);
    }
    
    /**
     *Gets the image that appears when the button is clicked.
     * @return Clicked image.
     */
    public DrawnObject getClickedImage(){
        return imageMap.get((byte)1);
    }
    /**
     *Sets the image that will appear as default.
     * @param img Default image.
     */
    public void setDefaultImage(DrawnObject img){
        imageMap.put((byte)0, img);
    }
    
    /**
     *Gets the image that appears as default.
     * @return Default image.
     */
    public DrawnObject getDefaultImage(){
        return imageMap.get((byte)0);
    }
    
//</editor-fold>
    
    /**
     *Sets button's visibility.
     * @param b Button visibility
     * */
    public void setVisible(boolean b){
        isVisible = b;
    }
    
    /**
     *Checks if button is visible.
     * @return Button visibility
     */
    public boolean isVisible(){
        return isVisible;
    }
    
    /**************************************
    *Method for when the button is clicked
    **************************************/
    protected abstract  void clickEvent();
    
    /**************************************
    *Set the clicked status of the button
    **************************************/
    public void setStatus(byte s){
        status = s;
    }
    
    //<editor-fold defaultstate="collapsed" desc="Positioning">

    /**
     *Get button's integer X position
     * @return X position of sprite.
     */
        public float getXPosition(){
        return  x;
    }
    
    /**
     *Get button's integer Y position
     * @return Y position of sprite.
     */
    public float getYPosition(){
        return y;
    }
    
    /**
     *Sets the button's X position to a new value.
     * @param newX new X position
     */
    public void setXPosition(float newX){
        x = newX;
    }
    /**
     *Sets the button's Y position to a new value.
     * @param newY new Y position
     */
    public void setYPosition(float newY){
        y = newY;
    }
    
    
    /**
     *Gets the button's current image width according to the buttons status.
     * @return button's current image width
     */
    public float getWidth(){
        return imageMap.get(status).getWidth();
    }
    /**
     *Gets the button's current image height according to the buttons status.
     * @return button's current image height
     */    
    public float getHeight(){
        return imageMap.get(status).getHeight();
    }
//</editor-fold>

    
    /**************************************
    *Method for when the mouse is clicked
    * (Checks bounds)
    **************************************/
    public void mouseClicked(float mX,float mY) {
        if (inBounds(mX,mY) && isVisible) {
            status = 1;
            clickEvent();
        }else{
            status = 0;
        }
    }

    /**************************************
    *Method for when the mouse is moved
    * (Checks bounds)
    **************************************/
    public void mouseMoved(float mX,float mY) {
        if (inBounds(mX,mY) && isVisible) {
            status = 2;
        }else{
            status = 0;
        }
    }

}

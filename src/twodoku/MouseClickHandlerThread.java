/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twodoku;

import edu.princeton.cs.introcs.StdDraw;

/**
 *
 * @author jonathan
 */
public class MouseClickHandlerThread implements Runnable{
    private boolean clicked;
    private double clickX;
    private double clickY;
    private boolean running = true;
    /***************************************************
    *The Runnable's method for checking the Mouse state
    ***************************************************/
    @Override
    public void run() {
        boolean mouseDown = false;
        running = true;
        while (running) {
            boolean curMouseState = StdDraw.mousePressed();
            if (curMouseState != mouseDown && mouseDown) {
                clicked = true;
                clickX = StdDraw.mouseX();
                clickY = StdDraw.mouseY();
            }
            mouseDown = curMouseState;
        }
        
        try {
            Thread.currentThread().sleep(100);
        } catch (InterruptedException ex) {
        }
    }
    /******************************************
    *Check if the mouse was previously clicked
    ******************************************/
    public boolean isClicked() {
        return clicked;
    }

    /************************
    *Get the click's x value.
    ************************/
    public double getClickX() {
        return clickX;
    }

    /************************
    *Get the click's y value.
    ************************/
    public double getClickY() {
        return clickY;
    }

    /***************************************************
    *Check if the mouse handler thread is running still.
    ***************************************************/
    public boolean isRunning() {
        return running;
    }

    /***************
    *Stop the Thread
    *****************/
    public void stopRunning() {
        this.running = false;
    }
    
    /********************************************
    *Set clicked to false and wait for next click
    *********************************************/
    public void reset(){
        clicked = false;
    }
    
    
}

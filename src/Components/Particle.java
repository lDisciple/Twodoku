/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import edu.princeton.cs.introcs.StdDraw;

/**
 *
 * @author jonathan
 */
public class Particle extends DrawnObject{
    private float vx,vy;

    /***********************************************
    *An Object that represents a moving DrawnObject
    ***********************************************/
    public Particle(float x, float y, float vx, float vy) {
        super(x, y, 0.002f, 0.002f);
        this.vx = vx;
        this.vy = vy;
    }

    /********************************
    *The particles default draw call
    ********************************/
    @Override
    public void draw() {
        StdDraw.filledSquare(x, y,0.002f);//Square used as it uses less draw time than line.
    }
    
    /******************************
    *Update the particles position
    ******************************/
    public void update(long timePassed){
        x += vx*timePassed/1000f;
        y += vy*timePassed/1000f;
    }

    /*************************
    *Get the particles x value
    *************************/
    @Override
    public float getX() {
        return x;
    }

    /*************************
    *Set the particles x value
    *************************/
    @Override
    public void setX(float x) {
        this.x = x;
    }

    /*************************
    *Get the particles y value
    *************************/
    @Override
    public float getY() {
        return y;
    }

    /*************************
    *Set the particles y value
    *************************/
    @Override
    public void setY(float y) {
        this.y = y;
    }

    /****************************
    *Get the particles x velocity
    ****************************/
    public float getVx() {
        return vx;
    }

    /****************************
    *Set the particles x velocity
    ****************************/
    public void setVx(float vx) {
        this.vx = vx;
    }

    /****************************
    *Get the particles y velocity
    ****************************/
    public float getVy() {
        return vy;
    }

    /****************************
    *Set the particles y velocity
    ****************************/
    public void setVy(float vy) {
        this.vy = vy;
    }
    
}

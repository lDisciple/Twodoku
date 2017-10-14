/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Components;

import edu.princeton.cs.introcs.StdDraw;
import java.awt.Color;

/**
 *
 * @author jonathan
 */
public class ParticleGenerator extends DrawnObject{
    private final Particle[] particles;
    private int maxParticles;
    private boolean showing = false;
    
    /*******************************************************
    *An Object that manages particle movements and deaths
    ********************************************************/
    public ParticleGenerator(float x, float y, float width, float height, int maxParticles){
        super(x, y, width, height);
        this.maxParticles = maxParticles;
        particles = new Particle[maxParticles];
        for (int i = 0; i < maxParticles; i++) {
            float px = y + (float) (width/2f - Math.random()*width);
            float py = x + (float) (height/2f - Math.random()*height);
            float speed = (float) Math.random()/20f+0.025f;
            float vx = (float) (speed*Math.cos(Math.PI* (Math.random()*0.5f+0.25f)));
            float vy = (float) (speed*Math.sin(Math.PI* (Math.random()+1)));
            particles[i] = new Particle(px,py,vx,vy);
        }
    }

    /***********************************
    *Draw call to display all particles
    ***********************************/
    @Override
    public void draw() {
        if (showing) {
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.setPenRadius(0.005f);
            for (Particle particle : particles) {
                particle.draw();
            }
        }
    }
    
    /****************************
    *Update all particle positions
    ****************************/
    public void update(long timePassed){
        for (int i = 0; i < particles.length; i++) {
            particles[i].update(timePassed);
            if (particles[i].getX() < x - width/2f ||
                    particles[i].getX() > x + width/2f ||
                    particles[i].getY() < y - height/2f ||
                    particles[i].getY() > y + height/2f
                    ) {
                particles[i].setX(y + (float) (width/2f - Math.random()*width));
                particles[i].setY(height);
                //Set the particles velocity to a certain non zero speed range so that it will go off screen.
                float speed = (float) Math.random()/20f+0.025f;
                particles[i].setVx((float) (speed*Math.cos(Math.PI* (Math.random()*0.5f+0.25f))));
                particles[i].setVy((float) (speed*Math.sin(Math.PI* (Math.random()+1))));
            }
        }
    }

    /**********************************
    *Check if the particles are showing
    ***********************************/
    public boolean isShowing() {
        return showing;
    }

    /*************************************
    *Set whether the particles are showing
    **************************************/
    public void setShowing(boolean isShowing) {
        this.showing = isShowing;
    }

    /*********************************************
    *Get the maximum number of particles on screen
    **********************************************/
    public int getMaxParticles() {
        return maxParticles;
    }

    /*********************************************
    *Set the maximum number of particles on screen
    **********************************************/
    public void setMaxParticles(int maxParticles) {
        this.maxParticles = maxParticles;
    }
    
    
}

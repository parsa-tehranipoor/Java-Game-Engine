/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.movement;

import TomHopper.utility.Vector;
import TomHopper.Game;
import java.awt.event.KeyEvent;

/**
 * A class that supports movement in 2 dimensions.
 * 
 * @author cdwan
 */
public class Movement2D extends AbstractMovement {
    
    // Keys for up, left, down, and right motion
    private int w, a, s, d;
    // Boolean values for what keys are being pressed
    private boolean downW, downA, downS, downD;
    // Set speed of motion for speed when moving in a certain direction
    private Vector speed;
    // Actual current velocity based on direction
    private Vector velocity;

    /**
     * Constructs a Movement2D Object.
     * 
     * @param spdX Speed in the x direction
     * @param spdY Speed in the y direction
     * @param g Game
     */
    public Movement2D(float spdX, float spdY, Game g) {
        this(spdX, spdY, KeyEvent.VK_W, KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, g);
    }

    /**
     * Constructs a Movement2D Object with given keys for motion.
     * 
     * @param spdX Speed in the x direction
     * @param spdY Speed in the y direction
     * @param w Key for up movement
     * @param a Key for left movement
     * @param s Key for down movement
     * @param d Key for right movement
     * @param g Game
     */
    public Movement2D(float spdX, float spdY, int w, int a, int s, int d, Game g) {
        this.speed = new Vector(spdX, spdY);
        this.w = w;
        this.a = a;
        this.s = s;
        this.d = d;
        downW = false;
        downA = false;
        downS = false;
        downD = false;
        this.velocity = new Vector(0, 0);
        game = g;
    }

    /**
     * Updates velocity based on what keys are being pressed and what keys have
     * been released.
     */
    @Override
    public void tick() {
        if (!downW && game.keyIsDown(w)) {
            downW = true;
            velocity.setY(-speed.getY());
        }
        if (!downA && game.keyIsDown(a)) {
            downA = true;
            velocity.setX(-speed.getX());
        }
        if (!downS && game.keyIsDown(s)) {
            downS = true;
            velocity.setY(speed.getY());
        }
        if (!downD && game.keyIsDown(d)) {
            downD = true;
            velocity.setX(speed.getX());
        }

        //checks releases
        if (downW && !game.keyIsDown(w)) {
            downW = false;
            if (downS) {
                velocity.setY(speed.getY());
            } else {
                velocity.setY(0);
            }
        }
        if (downA && !game.keyIsDown(a)) {
            downA = false;
            if (downD) {
                velocity.setX(speed.getX());
            } else {
                velocity.setX(0);
            }
        }
        if (downS && !game.keyIsDown(s)) {
            downS = false;
            if (downW) {
                velocity.setY(-speed.getY());
            } else {
                velocity.setY(0);
            }
        }
        if (downD && !game.keyIsDown(d)) {
            downD = false;
            if (downA) {
                velocity.setX(-speed.getX());
            } else {
                velocity.setX(0);
            }
        }
    }

    /**
     * Gets the x component of the current velocity.
     * 
     * @return X component of velocity
     */
    public float getVelX() {
        return velocity.getX();
    }

    /**
     * Gets the y component of the current velocity.
     * 
     * @return Y component of velocity
     */
    public float getVelY() {
        return velocity.getY();
    }

    /**
     * Sets the x component of the current velocity.
     * 
     * @param value Given x component
     */
    public void setVelX(float value) {
        velocity.setX(value);
    }

    /**
     * Sets the y component of the current velocity.
     * 
     * @param value Given y component
     */
    public void setVelY(float value) {
        velocity.setY(value);
    }

    /**
     * Gets the speed in the x direction.
     * 
     * @return X component of speed
     */
    public float getSpeedX() {
        return speed.getX();
    }

    /**
     * Gets the speed in the y direction.
     * 
     * @return Y component of speed
     */
    public float getSpeedY() {
        return speed.getY();
    }

    /**
     * Sets the speed in the x direction.
     * 
     * @param value Given x component of speed
     */
    public void setSpeedX(float value) {
        speed.setX(value);
    }

    /**
     * Sets the speed in the y direction.
     * 
     * @param value Given y component of speed
     */
    public void setSpeedY(float value) {
        speed.setY(value);
    }

    /**
     * Gets the velocity of the motion.
     * 
     * @return Velocity
     */
    @Override
    public Vector getVel() {
        return velocity;
    }
}

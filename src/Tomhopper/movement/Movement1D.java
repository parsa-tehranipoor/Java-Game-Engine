/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.movement;

import TomHopper.utility.Vector;
import TomHopper.Game;
import java.awt.event.KeyEvent;

/**
 * Movement class for 1 directional motion.
 * 
 * @author cdwan
 */
public class Movement1D extends AbstractMovement {
    // Which key is being pressed
    private boolean downL, downR;
    // Base Velocity. Defines the speed that is the same in both directions.
    private Vector vBase;
    // Null Velocity(zero vector), velocity to the lft, velocity to the right
    public Vector v0, vL, vR;
    // The CURRENT velocity. Velocity is different based on left or right motion
    private Vector vCur;
    // Keys for left and right motion to occur
    private int keyA, keyD;
    // Direction object was last moving. Intially is moving to the right
    private boolean rightLast = true;

    /**
     * Constructs a Movement1D Object.
     * 
     * @param vBase Base Velocity
     * @param game Game
     */
    public Movement1D(Vector vBase, Game game) {
        this(vBase, KeyEvent.VK_A, KeyEvent.VK_D, game);
    }

    /**
     * Constructs a Movement1D Object with given keys for when pressed, motion
     * occurs.
     * 
     * @param vBase Base Velocity
     * @param a Key for left movement
     * @param d Key for right movement
     * @param game Game
     */
    public Movement1D(Vector vBase, int a, int d, Game game) {
        this.vBase = vBase;
        downL = false;
        downR = false;
        this.v0 = Vector.getNullVector(vBase.numDim());
        this.vL = vBase.getIfNegated();
        this.vR = vBase.getCopy();
        vCur = v0;
        keyA = a;
        keyD = d;
        this.game = game;
    }

    /**
     * Changes velocity and direction at every tick. Motion is defined by what
     * keys are pressed and if any keys are being pressed at all.
     */
    @Override
    public void tick() {
        if (!downL && game.keyIsDown(keyA)) {
            downL = true;
            vCur = vL;
        }
        if (!downR && game.keyIsDown(keyD)) {
            downR = true;
            vCur = vR;
        }

        //checks releases
        if (downL && !game.keyIsDown(keyA)) {
            downL = false;
            if (downR) {
                vCur = vR;
            } else {
                stop();
            }
        }
        if (downR && !game.keyIsDown(keyD)) {
            downR = false;
            if (downL) {
                vCur = vL;
            } else {
                stop();
            }
        }
    }
    
    /**
     * Stops the motion of the object.
     */
    public void stop() {
        if ((int) Math.abs(vCur.getX()) > 0) {
            rightLast = vCur.getX() > 0;
        }
        vCur = v0;
    }

    /**
     * Gets the velocity of the object.
     * 
     * @return Current velocity
     */
    @Override
    public Vector getVel() {
        return vCur;
    }
    
    /**
     * Checks whether or not the object is moving to the right.
     * 
     * @return True if the object is moving right or false if its moving left
     */
    public boolean movingRight() {
        if (vCur.getX() == 0) {
            return rightLast;
        }
        return vCur.getX() > 0;
    }

    /**
     * Returns the base velocity of the objects movement.
     * 
     * @return Base velocity
     */
    public Vector getBase() {
        return vBase;
    }

    /**
     * Sets the base velocity to a given velocity.
     * 
     * @param newB Given velocity
     */
    public void setBase(Vector newB) {
        vBase = newB;
        updateVs();
    }

    /**
     * Gets the speed of the object.
     * 
     * @return Speed of the object
     */
    public float getSpeed() {
        return vBase.magnitude();
    }

    // Confused by this. Change method name?
    /**
     * Scales the base velocity to the given magnitude.
     * 
     * @param value Given scale factor
     */
    public void scaleBase(float value) {
        vBase.scaleTo(value);
        updateVs();
    }
    
    // Also confused by this. Change method name?
    /**
     * Scales the Base velocity by the given magnitude.
     * 
     * @param value Given scale factor
     */
    public void incBase(float value) {
        vBase.scaleUp(1 + value);
        updateVs();
    }
    
    /**
     * Updates the velocities, including the left, right, and current velocity.
     */
    private void updateVs() {
        Vector vl0 = vL, vr0 = vR;
        this.v0 = Vector.getNullVector(vBase.numDim());
        this.vL = vBase.getIfNegated();
        this.vR = vBase.getCopy();
        if (vCur == vl0) {
            vCur = vL;
        }
        if (vCur == vr0) {
            vCur = vR;
        }
    }
}

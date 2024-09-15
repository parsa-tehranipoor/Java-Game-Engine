/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TomHopper.movement;

import TomHopper.Game;

/**
 * A class for determining how a Game Object can rotate.
 * 
 * Side note: Shouldn't we be using PrinceAngle here?
 * @author cdwan
 */
public abstract class AbstractRotation implements Rotatingable {
    
    // Data field for the Game
    protected Game game;
    
    /**
     * Gets the angle of rotation
     * 
     * @return Angle
     */
    @Override
    public abstract float getOmega();
    
    /**
     * Sets the angle of rotation
     * 
     * @param om Given angle
     */
    @Override
    public abstract void setOmega(float om);
    
    /**
     * Tick method that determines rotation per tick.
     */
    public abstract void tick();
}

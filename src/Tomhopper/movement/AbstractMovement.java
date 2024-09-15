/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TomHopper.movement;

import TomHopper.Game;
import TomHopper.utility.Vector;

/**
 * A parents class for defining the types of motion a Game Object can have.
 * 
 * @author cdwan
 */
public abstract class AbstractMovement {
    
    // Data field for the Game
    protected Game game;
    
    /**
     * Gets the Velocity of the movement.
     * 
     * @return Velocity
     */
    public abstract Vector getVel();
    
    /**
     * Tick method for determining movement per tick.
     */
    public abstract void tick();
    
}

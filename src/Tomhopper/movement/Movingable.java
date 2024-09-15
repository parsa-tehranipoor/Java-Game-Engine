/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TomHopper.movement;

import TomHopper.utility.Vector;

/**
 * Interface for a specific object to have the necessary methods for movement.
 * 
 * @author cdwan
 */
public interface Movingable {
    
    /**
     * Gets the velocity of the Object.
     * 
     * @return Velocity
     */
    public Vector getVel();
    
    /**
     * Sets the velocity of the Object.
     * 
     * @param v Given vector
     */
    public void setVel(Vector v);
    
}

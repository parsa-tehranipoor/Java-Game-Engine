/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TomHopper.general;

import TomHopper.utility.PrinceAngle;

/**
 * Interface that gives the methods necessary for an object to rotate.
 * 
 * @author cdwan
 */
public interface Rotatable {
    
    /**
     * Gets the angle of the object.
     * 
     * @return Angle of object
     */
    public PrinceAngle getAngle();
    
    /**
     * Rotates the object by a given amount counterclockwise.
     * 
     * @param degrees Given degrees of rotation
     * @return This Rotatable Object
     */
    public Rotatable rotate(float degrees);
    
    /**
     * Sets the angle of the object to a Given Angle
     * 
     * @param pa Given angle that will be set
     * @return This Rotatable Object
     */
    public Rotatable setAngle(PrinceAngle pa);
    
}

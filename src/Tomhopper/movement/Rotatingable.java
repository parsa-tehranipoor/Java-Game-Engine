/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TomHopper.movement;

/**
 * A movement specific interface(different from the Rotatable interface) for any
 * object that can rotate.
 * 
 * @author cdwan
 */
interface Rotatingable {

    /**
     * Gets the Angle of rotation
     * 
     * @return Angle
     */
    public float getOmega();

    /**
     * Sets the Angle of rotation
     * 
     * @param om Given angle
     */
    public void setOmega(float om);

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TomHopper.general;

/**
 * Interface which allows an object to "contain" data from the main game. All it
 * does is give an "inner" handler to this smaller subsection of data that we
 * want contained.
 *
 * @author cdwan
 */
public interface Container {
    
    /**
     * Gets the "inner" handler from this Container
     * 
     * @return The internal handlerGT
     */
    public HandlerGT<AbstractGameThing> getInnerHandler();
    
    /**
     * Gets the x value of this Containers location
     * 
     * @return X coordinate
     */
    public float getX();
    
    /**
     * Gets the y value of this Containers location
     * 
     * @return Y coordinate
     */
    public float getY();

}

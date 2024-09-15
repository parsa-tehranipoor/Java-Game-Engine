/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TomHopper.general;

import java.awt.event.MouseEvent;

/**
 * Interface for whether an object is clickable. All calls of the methods below
 * are handled in the Handler.
 * 
 * @author cdwan
 */
public interface Clickable {
    
    /**
     * Checks wether the object was "clicked" on
     * 
     * @param e Given MouseEvent
     * @return True or False based on whether or not the object was clicked on
     */
    public boolean wouldBeHit(MouseEvent e);
    
    /**
     * Will perform a given task when the object is "clicked" on
     * 
     * @param e Given MouseEvent
     */
    public void mousePressed(MouseEvent e);
    
    /**
     * Will perform a given task when the object is "released"(basically when
     * you release your mouse after clicking)
     * 
     * @param e Given MouseEvent
     */
    public void mouseReleased(MouseEvent e);
}

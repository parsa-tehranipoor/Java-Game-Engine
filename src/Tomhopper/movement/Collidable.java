/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TomHopper.movement;

import TomHopper.collision.Shape;

/**
 * Interface for any object that you want to be able to collide.
 * 
 * @author cdwan
 */
interface Collidable {
    
    /**
     * Returns the HitBox of the object.
     * 
     * @return HitBox
     */
    public Shape getHitBox();
    
}

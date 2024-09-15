/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TomHopper.collision;

import TomHopper.utility.Vector;
import TomHopper.general.*;
import TomHopper.Game;

/**
 * The Shape class is meant to act as a super class for any HitBox type that we
 * want this game engine to support. A HitBox is a rough sketch of what the 
 * actual object looks like. Its meant to be used in collision detection in
 * making the code easier to write.
 * 
 * @author ptehr
 * @param <T>
 */
public abstract class Shape<T extends Shape> extends AbstractGameThing<Shape> {

    //position of centroid of Shape    
    //should it be copy of or exactly position?
    /**
     * Constructs the Shape object
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param game Game which the object is in
     */
    public Shape(int x, int y, Game game) {
        super(x, y, game);
    }

    //either have it as abstract or change code to not make it abstract
    /**
     * Updates the position of the HitBox
     * 
     * @param pos Position which the Box will be set too
     */
    public abstract void updatePosition(Vector pos);
    
    // Name should be changed to getAABB or getBoundingBox
    /**
     * Returns the Bounding Box of AABB
     * 
     * @return AABB of the Shape
     */
    public abstract AABB getHurtBox();
    
    /**
     * Gets a copy of the Shape/HitBox
     * 
     * @return Shape copy
     */
    public abstract Shape getCopyOf();
    
}

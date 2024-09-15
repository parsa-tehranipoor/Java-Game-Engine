/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.movement;

import TomHopper.Game;
import TomHopper.collision.Shape;
import TomHopper.general.AbstractGameThing;
import TomHopper.gui.OGraphics;
import TomHopper.utility.PrinceAngle;
import TomHopper.utility.Vector;
import java.awt.image.BufferedImage;

/**
 * An AbstractGameObject is an extension of an AbstractGameThing that also can
 * move, collide, and rotate.
 *
 * @author cdwan
 * @param <T> the subclass it is so it can return that subclass when needed
 */
public abstract class AbstractGameObject<T extends AbstractGameObject> extends AbstractGameThing<T> implements Movingable, Rotatingable, Collidable {
    
    // The type of movement this Object undergoes
    protected AbstractMovement mov;
    // Defines how the object can rotate
    protected AbstractRotation rot;
    // Velocty of the Object
    protected Vector vel;
    // HitBox of the Object
    protected Shape hitbox;

    /**
     * Constructs an AbstractGameObject.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param vel Velocity
     * @param dir Direction
     * @param game Game
     */
    public AbstractGameObject(int x, int y, Vector vel, PrinceAngle dir, Game game) {
        super(x, y, game);
        this.vel = vel;
        this.angle = dir;
    }

    /**
     * Constructs an AbstractGameObject with no direction.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param vel Velocity
     * @param game Game
     */
    public AbstractGameObject(int x, int y, Vector vel, Game game) {
        super(x, y, game);
        this.vel = vel;
    }
    
    /**
     * Constructs an AbstractGameObject with a given HitBox.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param vel Velocity
     * @param hitbox HitBox
     * @param game Game
     */
    public AbstractGameObject(int x, int y, Vector vel, Shape hitbox, Game game) {
        super(x, y, game);
        this.vel = vel;
        this.hitbox = hitbox;
    }
    
    /**
     * Constructs a Game Object without initial velocity.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param game Game
     */
    public AbstractGameObject(int x, int y, Game game) {
        super(x, y, game);
    }

    /**
     * Tick method that moves, rotates, and updates the Object as well as other
     * parameters.
     */
    @Override
    public void tick() {
        if (mov != null) {
            mov.tick();
            setVel(mov.getVel());
        }
        if (angle != null && rot != null) {
            rot.tick();
            angle.rotate(rot.getOmega());
        }
        //add the thing here so the movement registers on the object w extensions
        if (vel != null) {
            pos.add(vel);
        }
        
        if (hitbox != null) {
            hitbox.updatePosition(pos);
        }
    }

    /**
     * Returns the Velocity.
     * 
     * @return Velocity
     */
    @Override
    public Vector getVel() {
        return vel;
    }

    /**
     * Sets the Velocity to a given Vector.
     * 
     * @param v Given Vector
     */
    @Override
    public void setVel(Vector v) {
        vel = v;
    }
    
    /**
     * Returns the angle of the Game Object.
     * 
     * @return Direction
     */
    @Override
    public float getOmega() {
        return rot.getOmega();
    }

    /**
     * Sets the angle of the Game Object.
     * 
     * @param om Given angle
     */
    @Override
    public void setOmega(float om) {
        rot.setOmega(om);
    }
    
    /**
     * Returns the Objects HitBox.
     * 
     * @return HitBox
     */
    @Override
    public Shape getHitBox() {
        return hitbox;
    }

    /**
     * Flips the image of the Object so that it generates based on what direction
     * the Object is "facing". Only supports right to left generation and must 
     * be done manually.
     *
     * @param imageL the left facing/moving image
     * @param imageR the right facing/moving image
     * @param roRelG2D the rotated graphics (to face the angle)
     */
    public void drawImageLR(BufferedImage imageL, BufferedImage imageR, OGraphics roRelG2D) {
        BufferedImage image = imageR;
        if (vel.getX() < -1e-8) {
            image = imageL;
            if (angle != null) {
                roRelG2D.rotate(Math.PI, getROXToX(), getROYToY());
            }
        }
        roRelG2D.drawImage(image, 0, 0);
    }
}

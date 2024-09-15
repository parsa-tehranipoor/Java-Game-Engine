/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.general;

import TomHopper.Game;
import TomHopper.gui.OGraphics;
import TomHopper.utility.PrinceAngle;
import TomHopper.utility.Vector;
import java.awt.event.MouseEvent;

/**
 * The AbstractGameThing class is a class for any object in the game which can
 * be categorized as a "thing"(don't debate this reasoning).It contains all the
 * necessary values for any GameThing to have, specifically a tick and render
 * method as well as its position on the Window
 *
 * @author cdwan
 * @param <T> the subclass it is so it can return that subclass when needed
 */
public abstract class AbstractGameThing<T extends AbstractGameThing> implements Tickable, Renderable, Rotatable {

    // Current position of the object
    protected Vector pos;
    // Position offset to be used in render method if necessary
    protected Vector renderOffset;
    // Angle of the object
    protected PrinceAngle angle;
    // Handler that handles this object
    protected HandlerGT handler;
    // Game which this object is apart of
    protected Game game;

    /**
     * Creates an AbstractGameThing Object
     *
     * @param x x coordinate
     * @param y y coordinate
     * @param game game
     */
    public AbstractGameThing(int x, int y, Game game) {
        this.pos = new Vector(x, y);
        this.game = game;
    }

    /**
     * Gets the x coordinate of this object
     *
     * @return x
     */
    public float getX() {
        return pos.getX();
    }

    /**
     * Gets the y coordinate of this object
     * 
     * @return y
     */
    public float getY() {
        return pos.getY();
    }
    
    /**
     * Gets the x coordinate of the position with render offset
     * 
     * @return x coordinate
     */
    public float getROX() {
        if (renderOffset != null) {
            return pos.getIfAdded(renderOffset).getX();
        } else {
            return pos.getX();
        }
    }
    
    /**
     * Gets the y coordinate of the position with render offset
     * 
     * @return y coordinate
     */
    public float getROY() {
        if (renderOffset != null) {
            return pos.getIfAdded(renderOffset).getY();
        } else {
            return pos.getY();
        }
    }
    
    /**
     * Gets the render offset Vector
     * 
     * @return vector offset
     */
    public Vector getRoffset() {
        return renderOffset;
    }
    
    /**
     * Not sure
     * 
     * @return 
     */
    public float getROXToX() {
        return getX() - getROX();
    }
    
    /**
     * Not sure
     * 
     * @return 
     */
    public float getROYToY() {
        return getY() - getROY();
    }
    
    /**
     * Gets the angle of the object
     * @return angle
     */
    @Override
    public PrinceAngle getAngle() {
        return angle;
    }

    /**
     * Returns the relative x coordinate of the location where the mouse clicked
     * relative to the top left of the button
     *
     * @param e MouseEvent(an object for storing info about a mouse event)
     * @return x coordinate relative
     */
    public int getRelX(MouseEvent e) {
        return e.getX() - (int) getX();
    }

    /**
     * Returns the relative y coordinate of the location where the mouse clicked
     * relative to the top left of the button
     *
     * @param e MouseEvent(an object for storing info about a mouse event)
     * @return y coordinate relative
     */
    public int getRelY(MouseEvent e) {
        return e.getY() - (int) getY();
    }

    /**
     * Sets x to new value
     *
     * @param x
     */
    public void setX(float x) {
        this.pos.setX(x);
    }

    /**
     * Sets y to new value
     *
     * @param y
     */
    public void setY(float y) {
        this.pos.setY(y);
    }
    
    /**
     * Adds a new value to the current x coordinate
     * 
     * @param x X coordinate
     */
    public void addX(float x) {
        setX(getX() + x);
    }
    
    /**
     * Subtracts a new value from the current x coordinate
     * 
     * @param x X coordinate
     */
    public void subX(float x) {
        setX(getX() - x);
    }
    
    /**
     * Adds a new value to the current y coordinate
     * 
     * @param y Y coordinate
     */
    public void addY(float y) {
        setY(getY() + y);
    }
    /**
     * Subtracts a new value from the current y coordinate
     * 
     * @param y Y coordinate
     */
    public void subY(float y) {
        setY(getY() - y);
    }
    
    /**
     * Sets the current angle to a new angle
     * 
     * @param pa Angle which will be set
     * @return New Angle
     */
    @Override
    public T setAngle(PrinceAngle pa) {
        angle = pa;
        return (T) this;
    }

    /**
     * Rotates the current degree by a new value counterclockwise
     * 
     * @param degrees Value which angle will be rotated by
     * @return New rotated Angle
     */
    @Override
    public T rotate(float degrees) {
        angle.rotate(degrees);
        return (T) this;
    }

    /**
     * Gets the Handler which this object is in
     *
     * @return Handler
     */
    public HandlerGT getHandlerGT() {
        return handler;
    }

    /**
     * Class methods w class parameter: so you can have the specific subclass
     * returned instead of a superclass in the superclass methods
     */
    
    /**
     * Adds AbstractGameThing to specified Handler, replacing any old one. This
     * is so you can compact code into one line.
     *
     * @param handler Handler which this object will now be in
     * @return This object
     */
    public T putSelfInHandler(HandlerGT handler) {
        if (this.handler != null) {
            removeSelfFromHandler();
        }
        if (handler != null) {
            handler.add(this);
        }
        this.handler = handler;
        return (T) this;
    }

    /**
     * Removes AbstractGameThing from specified Handler if in one, otherwise
     * this method does nothing. This is so you can compact code into one line.
     *
     * @return This object
     */
    public T removeSelfFromHandler() {
        if (handler != null) {
            handler.remove(this);
            handler = null;
        }
        return (T) this;
    }
    
    /**
     * Method which dictates what the object does in a given game tick. You do
     * not need to call this tick method as it will already be called in the
     * Handler.
     */
    @Override
    public void tick() {

    }
    
    /**
     * Gets the relative Graphics to this objects render position
     * 
     * @param og Given Graphics
     * @return New relative Graphics
     */
    public OGraphics getRelRenOG(OGraphics og) {
        return Game.resetDraw(og.create((int) getROX(), (int) getROY()));
    }
    
    /**
     * Gets the relative Graphics to this objects position
     * 
     * @param og Given Graphics
     * @return New relative Graphics
     */
    public OGraphics getRelObjOG(OGraphics og) {
        return Game.resetDraw(getRelRenOG(og).create((int) - renderOffset.getX(), (int) - renderOffset.getY()));
    }
    
    /**
     * Gets the relative Graphics to this objects render position with rotation?
     * 
     * @param og Given Graphics
     * @return New relative Graphics
     */
    public OGraphics getRoRelOG(OGraphics og) {
        OGraphics ret = og.create();
        if (angle != null) {
            ret.rotate(angle.getInRad(), getX(), getY());
        }
        ret.translate(getROX(), getROY());
        return Game.resetDraw(ret);
    }
}

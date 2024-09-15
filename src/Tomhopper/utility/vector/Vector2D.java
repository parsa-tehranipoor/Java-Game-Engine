/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.utility.vector;

import TomHopper.general.Rotatable;
import TomHopper.utility.PrinceAngle;

/**
 * A class that is an extension of the Vector class, Vector2D is a Vector in 2
 * dimensions.
 * 
 * @author cdwan
 */
public class Vector2D extends Vector<Vector2D> implements Rotatable {
    
    // Final values for directions in relation to degree rotations
    public static final int RIGHT = 0, UP = 90, LEFT = 180, DOWN = 270;

    /**
     * Constructs a Vector2D from a radius and angle.
     * 
     * @param r Radius
     * @param theta PrinceAngle
     */
    protected Vector2D(float r, PrinceAngle theta) {
        super((float) (r * Math.cos(Math.toRadians(theta.getInDeg()))),
                (float) (r * Math.sin(Math.toRadians(theta.getInDeg()))));
    }

    /**
     * Constructs a Vector2D from an x and y component.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     */
    protected Vector2D(float x, float y) {
        super(x, y);
    }

    /**
     * Returns the degree of rotation of this Vector2D relative to the x axis.
     * 
     * @return Angle in degrees
     */
    public float getInDeg() {
        float x = components[0];
        float y = components[1];
        float ret = Math.abs((float) Math.toDegrees(Math.atan(y / x)));
        if (x >= 0) {
            if (y >= 0) {
                return ret;
            } else {
                return 360 - ret;
            }
        } else {
            if (y > 0) {
                return 180 - ret;
            } else {
                return 180 + ret;
            }
        }
    }
    
    // Parsa changed this method. Used to look exactly as above but I changed to what it is now below
    /**
     * Returns the degree of rotation of this Vector2D relative to the y axis.
     * 
     * @return Angle in radians
     */
    public float getInRad() {
        return (float)Math.toRadians(getInDeg());
    }

    /**
     * Rotates this Vector2D by a given angle in degrees.
     * 
     * @param angle Given angle in degrees
     * @return This Vector2D
     */
    @Override
    public Vector2D rotate(float angle) {
        Vector2D trans = getRotated(angle);
        components[0] = trans.getComponent(0);
        components[1] = trans.getComponent(1);
        return this;
    }
    
    /**
     * Rotates a copy of this Vector2D by a given angle in degrees and returns
     * the resultant Vector2D.
     * 
     * @param angle Given angle
     * @return Resultant Vector2D
     */
    public Vector2D getRotated(float angle) {
        return new Vector2D(magnitude(), getInDeg() + angle);
    }

    /**
     * Returns the difference between this Vectors angle from another given
     * Vector.
     * 
     * @param other Given Vector
     * @return Angle difference in degrees
     */
    public float getAngleDif(Vector2D other) {
        return getInDeg() - other.getInDeg();
    }

    /**
     * Gets the angle of rotation of this Vector2D.
     * 
     * @return PrinceAngle
     */
    @Override
    public PrinceAngle getAngle() {
        return new PrinceAngle(getInDeg());
    }

    /**
     * Sets this Vectors angle to a given PrinceAngle.
     * 
     * @param pa PrinceAngle
     * @return This Vector
     */
    @Override
    public Vector2D setAngle(PrinceAngle pa) {
        rotate(pa.rotate(-getInDeg()).getInDeg());
        return this;
    }

    /**
     * Static method that returns a Null Vector2D which is a Vector of all zeros.
     * 
     * @return Null Vector
     */
    public static Vector2D getNullVector2D() {
        return new Vector2D(0, 0);
    }
    
    /**
     * Static method that returns a copy of a given Vector2D.
     * 
     * @param v2d Given Vector2D
     * @return Copy of given Vector2D
     */
    public static Vector2D copyOf(Vector2D v2d) {
        return (Vector2D) Vector.copyOf(v2d);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.utility;

/**
 * Class representing an Angle in radians or degrees. Supports methods for angle
 * manipulation. Rotating angles go counterclockwise.
 * 
 * @author cdwan
 */
public class PrinceAngle {

    // Angle at zero degrees
    public static final float ZERO = 0;
    // Angle for a quarter turn
    public static final float QUARTERTURN = (float) (Math.PI / 2);
    // Angle for a half turn
    public static final float HALFTURN = (float) Math.PI;
    // Angle for a quarter back turn
    public static final float QUARTERBACKTURN = (float) (3 * Math.PI / 2);
    // The mathematical number Pi over 2
    public static final float HALFPI = (float) (Math.PI / 2);
    // The mathematical number Pi
    public static final float PI = (float) Math.PI;
    // The mathematical number negative Pi
    public static final float NEGHALFPI = (float) (3 * Math.PI / 2);
    // Angle of PrinceAngle in DEGREES(very important to know)
    private float theta;

    /**
     * Constructs a PrinceAngle with degrees 0.
     */
    public PrinceAngle() {
        this(0);
    }

    /**
     * Constructs a PrinceAngle with a given angle.
     * 
     * @param theta Angle in degrees
     */
    public PrinceAngle(float theta) {
        this.theta = theta;
    }

    /**
     * Gets the angle in degrees.
     * 
     * @return Angle in degrees
     */
    public float getInDeg() {
        return theta;
    }

    /**
     * Sets the angle to a given angle in degrees.
     * 
     * @param theta Given angle in degrees
     * @return This PrinceAngle
     */
    public PrinceAngle setDeg(float theta) {
        this.theta = princeAngle(theta);
        return this;
    }

    /**
     * Sets the angle to a given angle in radians.
     * 
     * @param theta Given angle in radians
     * @return This PrinceAngle
     */
    public PrinceAngle setRad(float theta) {
        this.theta = (float) Math.toDegrees(theta);
        return this;
    }

    /**
     * Negates the angle of this object.
     * 
     * @return This PrinceAngle
     */
    public PrinceAngle negate() {
        this.theta *= -1;
        return this;
    }

    /**
     * Negates the angle of a copy of this object.
     * 
     * @return New PrinceAngle
     */
    public PrinceAngle getIfNegated() {
        return getCopy().negate();
    }

    /**
     * Rotates the angle by a given angle in degrees.
     * 
     * @param angle Given angle in degrees
     * @return This PrinceAngle
     */
    public PrinceAngle rotate(float angle) {
        theta = princeAngle(theta + angle);
        return this;
    }

    /**
     * Rotates the angle by a given PrinceAngle.
     * 
     * @param angle Given PrinceAngle
     * @return This PrinceAngle
     */
    public PrinceAngle rotate(PrinceAngle angle) {
        return rotate(angle.getInDeg());
    }
    
    /**
     * Adds 180 degrees to this PrinceAngle. On a unit circle, it mirrors the 
     * angle across the origin.
     * 
     * @return This PrinceAngle
     */
    public PrinceAngle mirrorY() {
        theta = -theta + 180;
        return this;
    }
    
    /**
     * Adds 180 to a copy of this PrinceAngle. On a unit circle, it mirrors the
     * angle across the origin.
     * 
     * @return New PrinceAngle
     */
    public PrinceAngle getMirroredY() {
        return getCopy().mirrorY();
    }

    /**
     * Returns this angle when rotated by a given angle in degrees.
     * 
     * @param angle Given angle in degrees
     * @return This rotated angle in degrees
     */
    public float getIfRotated(float angle) {
        return princeAngle(theta + angle);
    }

    /**
     * Returns this angle when rotated by a given PrinceAngle.
     * 
     * @param angle Given PrinceAngle
     * @return This rotated angle in degrees
     */
    public float getIfRotated(PrinceAngle angle) {
        return princeAngle(theta + angle.getInDeg());
    }

    /**
     * Gets this angle in radians.
     * 
     * @return Angle in radians
     */
    public float getInRad() {
        return (float) Math.toRadians(theta);
    }

    /**
     * Gets the copy of this PrinceAngle.
     * 
     * @return Copy of this PrinceAngle
     */
    public PrinceAngle getCopy() {
        return copyOf(this);
    }

    /**
     * Static method that returns a copy of the given PrinceAngle.
     * 
     * @param angle Given PrinceAngle
     * @return Copy of given PrinceAngle
     */
    public static PrinceAngle copyOf(PrinceAngle angle) {
        return new PrinceAngle(angle.getInDeg());
    }

    /**
     * A static method that checks if the given angle is within a restricted 
     * range. If not, it adjusts the angle back into the restricted range.
     * This range is [0, 360] degrees.
     * 
     * @param theta Given angle in degrees
     * @return New angle in degrees
     */
    public static float princeAngle(float theta) {
        theta %= 360;
        if (theta < 0) {
            theta += 360;
        }
        return theta;
    }
}

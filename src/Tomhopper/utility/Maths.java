/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.utility;

/**
 * A math class that supports math functions.
 * 
 * @author cdwan
 */
public class Maths {
    
    /**
     * Returns the square root of the sum of the squares of an arrays components.
     * 
     * @param nums Array of components
     * @return Root mean square
     */
    public static float rootMeanSqare(float[] nums) {
        float ret = 0;
        for (float f : nums) {
            ret += f * f;
        }
        return (float) Math.sqrt(ret);
    }
    
    /**
     * Returns the smallest distance between 2 angles. 
     * 
     * @param a First angle
     * @param b Second angle
     * @return Smallest angle distance
     */
    public static float smallerAngleDist(float a, float b) {
        a = PrinceAngle.princeAngle(a);
        b = PrinceAngle.princeAngle(b);
        return PrinceAngle.princeAngle((float) Math.min(Math.abs(a - b), Math.abs(360 - (a - b))));
    }
    
    /**
     * Returns the smallest distance between 2 angles.
     * 
     * @param a First PrinceAngle
     * @param b Second PrinceAngle
     * @return Smallest angle distance
     */
    public static float smallerAngleDist(PrinceAngle a, PrinceAngle b) {
        return smallerAngleDist(a.getInDeg(), b.getInDeg());
    }
    
    /**
     * Rotates the second angle to the first angle.
     * 
     * @param a First PrinceAngle
     * @param b Second PrinceAngle
     * @return Rotated second PrinceAngle
     */
    public PrinceAngle angleToAngle(PrinceAngle a, PrinceAngle b) {
        return b.rotate(a.getIfNegated());
    }
}

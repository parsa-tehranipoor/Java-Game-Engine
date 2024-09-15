/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.utility.vector;

import TomHopper.utility.Maths;
import TomHopper.utility.PrinceAngle;
import java.util.Arrays;

/**
 * A class for representing Vectors in code.
 * 
 * @author cdwan
 * @param <E> the subclass
 */
public class Vector<E extends Vector> {

    // An array of the components of a Vector
    protected float[] components;

    /**
     * Constructs a 1-dimensional Vector with a given element.
     * 
     * @param a Given component
     */
    protected Vector(float a) {
        this(new float[]{a});
    }

    /**
     * Constructs a 2-dimensional Vector with 2 given elements.
     * 
     * @param a First component
     * @param b Second component
     */
    protected Vector(float a, float b) {
        this(new float[]{a, b});
    }

    /**
     * Constructs a Vector with a given array of components.
     * 
     * @param comps Array of components
     */
    protected Vector(float[] comps) {
        components = Arrays.copyOf(comps, comps.length);
    }
    
    /**
     * A static method that creates a 1-dimensional Vector from a given element.
     * 
     * @param a Given component
     * @return New Vector
     */
    public static Vector makeVector(float a) {
        return new Vector(a);
    }
    
    /**
     * A static method that creates a Vector2D object from 2 given elements.
     * 
     * @param a First element
     * @param b Second element
     * @return New Vector2D
     */
    public static Vector2D makeVector(float a, float b) {
        return new Vector2D(a, b);
    }
    
    /**
     * A static method that creates a Vector from a given array of components.
     * 
     * @param comps Array of components
     * @return New Vector
     */
    public static Vector makeVector(float[] comps) {
        if (comps.length == 2) {
            return new Vector2D(comps[0], comps[1]);
        }
        return new Vector(comps);
    }
    
    /**
     * A static method that creates a Vector2D from a given radius and angle.
     * 
     * @param r Radius
     * @param theta PrinceAngle
     * @return New Vector2D
     */
    public static Vector2D makeVector(float r, PrinceAngle theta) {
        return new Vector2D((float) (r * Math.cos(Math.toRadians(theta.getInDeg()))),
                (float) (r * Math.sin(Math.toRadians(theta.getInDeg()))));
    }

    /**
     * Adds a given Vector to a copy of this Vector and returns the resulting
     * addition.
     * 
     * @param other Given Vector
     * @return Resulting Vector
     */
    public E getIfAdded(E other) {
        float[] sum = new float[Math.max(numDim(), other.numDim())];
        for (int i = 0; i < components.length; ++i) {
            sum[i] += components[i];
        }
        for (int i = 0; i < other.toArray().length; ++i) {
            sum[i] += other.toArray()[i];
        }
        return (E) new Vector(sum);
    }

    /**
     * Adds a given Vector to this Vector.
     * 
     * @param other Given Vector
     * @return This Vector
     */
    public E add(E other) {
        components = getIfAdded(other).toArray();
        return (E) this;
    }

    /**
     * Subtracts a given Vector from a copy of this Vector and returns the
     * resulting Vector.
     * 
     * @param other Given Vector
     * @return Resulting Vector
     */
    public E getIfSubtracted(Vector<E> other) {
        return (E) getIfAdded(other.getIfNegated());
    }

    /**
     * Subtracts a given Vector from this Vector.
     * 
     * @param other Given Vector
     * @return This Vector
     */
    public E subtract(Vector<E> other) {
        add(other.getIfNegated());
        return (E) this;
    }

    /**
     * Negates this Vector's components.
     * 
     * @return This Vector
     */
    public E negate() {
        scaleUp(-1);
        return (E) this;
    }

    /**
     * Negates a copy this Vectors components and returns it.
     * 
     * @return Resulting Vector
     */
    public E getIfNegated() {
        return (E) copyOf(this).negate();
    }

    /**
     * Scales up the components of this Vector by a given factor.
     * 
     * @param factor Given factor
     * @return This Vector
     */
    public E scaleUp(float factor) {
        for (int i = 0; i < components.length; ++i) {
            components[i] *= factor;
        }
        return (E) this;
    }
    /**
     * Scales up the components of a copy of this Vector by a given factor and
     * returns the resulting Vector.
     * 
     * @param factor Given factor
     * @return Resulting Vector 
     */
    public E getIfScaledUp(float factor) {
        return (E) copyOf(this).scaleUp(factor);
    }

    /**
     * Scales down the components of this Vector by a given factor.
     * 
     * @param factor Given factor
     * @return This Vector
     */
    public E scaleDown(float factor) {
        return (E) scaleUp(1f / factor);
    }

    /**
     * Scales down the components of a copy of this Vector by a given factor and
     * returns the resulting Vector.
     * 
     * @param factor Given factor
     * @return Resulting Vector
     */
    public E getIfScaledDown(float factor) {
        return (E) copyOf(this).scaleDown(factor);
    }

    // Why is unit vector method called normalize? Better name?
    /**
     * Scales a copy of this Vectors unit Vector by a factor and returns the
     * resulting Vector.
     * 
     * @param mag Scale magnitude
     * @return Resulting Vector
     */
    public E getIfScaledTo(float mag) {
        return (E) copyOf(this).normalize().scaleUp(mag);
    }

    /**
     * Changes this Vector by a scale factor applied to its unit Vector.
     * 
     * @param mag Scale magnitude
     * @return This Vector
     */
    public E scaleTo(float mag) {
        return (E) this.normalize().scaleUp(mag);
    }

    /**
     * Scales a copy of this Vector into its unit Vector and returns the
     * resulting Vector.
     * 
     * @return Resulting Vector
     */
    public E getIfNormalized() {
        return (E) getIfScaledDown(magnitude());
    }

    /**
     * Scales this Vector into its unit Vector.
     * 
     * @return This Vector
     */
    public E normalize() {
        scaleDown(magnitude());
        return (E) this;
    }

    /**
     * Negates the specific component given of this Vector.
     * 
     * @param component Given component
     */
    public void negateComponent(int component) {
        components[component] *= -1;
    }

    /**
     * Negates the x component of this Vector.
     */
    public void negateX() {
        negateComponent(0);
    }

    /**
     * Negates the y component of this Vector.
     */
    public void negateY() {
        negateComponent(1);
    }

    /**
     * Sets the specific component of this Vector to a given value.
     * 
     * @param component Given component
     * @param value Given value
     */
    public void setComponent(int component, float value) {
        components[component] = value;
    }

    /**
     * Sets the x component of this Vector to a given value.
     * 
     * @param val Given value
     */
    public void setX(float val) {
        setComponent(0, val);
    }

    /**
     * Sets the y component of this Vector to a given value
     * 
     * @param val 
     */
    public void setY(float val) {
        setComponent(1, val);
    }
    
    /**
     * Adds a given value to the x component of this Vector.
     * 
     * @param val Given value
     */
    public void addX(float val) {
        setComponent(0, getX() + val);
    }
    
    /**
     * Adds a given value to the y component of this Vector.
     * 
     * @param val Given value
     */
    public void addY(float val) {
        setComponent(1, getY() + val);
    }
    
    /**
     * Subtracts a given value from the x component of this Vector.
     * 
     * @param val Given value
     */
    public void subX(float val) {
        setComponent(0, getX() - val);
    }
    
    /**
     * Subtracts a given value from the y component of this Vector.
     * 
     * @param val Given value
     */
    public void subY(float val) {
        setComponent(1, getY() - val);
    }
    
    /**
     * Gets the value of given component from the Vector.
     * 
     * @param component Given component
     * @return Value of the component
     */
    public float getComponent(int component) {
        return components[component];
    }

    /**
     * Gets the x component of this Vector.
     * 
     * @return X component
     */
    public float getX() {
        return getComponent(0);
    }

    /**
     * Gets the y component of this Vector.
     * 
     * @return Y component
     */
    public float getY() {
        return getComponent(1);
    }

    /**
     * Returns the array of components of this Vector.
     * 
     * @return Array of components
     */
    public float[] toArray() {
        return components;
    }

    /**
     * Returns the magnitude of this Vector.
     * 
     * @return Vector magnitude
     */
    public float magnitude() {
        return Maths.rootMeanSqare(components);
    }

    /**
     * Returns the number of dimensions of this Vector.
     * 
     * @return Number of dimensions
     */
    public int numDim() {
        return components.length;
    }

    /**
     * Returns a copy of this Vector.
     * 
     * @return Vector copy
     */
    public Vector getCopy() {
        return copyOf(this);
    }

    /**
     * Returns the dot product of this Vector with another Vector
     * 
     * @param other Given Vector
     * @return Dot product
     */
    public float dotProduct(Vector other) {
        float val = 0;
        for (int i = 0; i < Math.min(this.numDim(), other.numDim()); ++i) {
            val = val + other.getComponent(i) * this.getComponent(i);
        }
        return val;
    }

    /**
     * Returns the absolute value of the angle between this Vector and a given 
     * Vector. The angle has a restricted range of [0, 180] degrees.
     *
     * @param other Given Vector
     * @return PrinceAngle between 2 Vectors
     */
    public PrinceAngle getAbsAngle(Vector other) {
        float val = this.dotProduct(other) / (this.magnitude() * other.magnitude());
        float theta = (float) Math.acos(val);
        return new PrinceAngle((float) Math.toDegrees(theta));
    }
    
    /**
     * Static method that returns a copy of the given Vector.
     * 
     * @param v Given Vector
     * @return Copy of given Vector
     */
    public static Vector copyOf(Vector v) {
        return makeVector(Arrays.copyOf(v.toArray(), v.numDim()));
    }
    
    /**
     * Only for 3d vectors
     *
     * @param a
     * @param b
     * @return normal vector (cross product magnitude)
     */
    public static Vector crossProduct(Vector a, Vector b) {
        if (a.numDim() != 3 || b.numDim() != 3) {
            throw new IllegalArgumentException();
        }
        Vector normal = getNullVector(3);
        float num = a.getComponent(1) * b.getComponent(2) - a.getComponent(2) * b.getComponent(1);
        normal.setComponent(0, num);
        num = a.getComponent(0) * b.getComponent(2) - a.getComponent(2) - b.getComponent(0);
        num = -1 * num;
        normal.setComponent(1, num);
        num = a.getComponent(0) * b.getComponent(1) - a.getComponent(1) * b.getComponent(0);
        normal.setComponent(2, num);
        return normal;
    }
    
    /**
     * Static method that creates a null Vector which is a Vector with all zeros
     * as its components.
     * 
     * @param numDim Number of dimensions
     * @return Null Vector
     */
    public static Vector getNullVector(int numDim) {
        return new Vector(new float[numDim]);
    }
    
    /*public Vector2D getAsPolar() {
        if (numDim() != 2) {
            throw new UnsupportedOperationException("not 2D");
        }
        return new Vector2D(getX(), getY());
    }*/
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TomHopper.collision;

import TomHopper.Game;
import TomHopper.general.Tickable;
import TomHopper.general.HandlerGT;
import TomHopper.utility.*;
import TomHopper.movement.AbstractGameObject;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;

/**
 * This is a class for Collision Detection which can be implemented in Segments.
 * It detects collisions among objects in a Handler and then allows the user to
 * decide what happens when a collision occurs.
 * 
 * @author ptehr
 */
public abstract class AbstractCollisionDetection implements Tickable {

    // Handler of the objects
    private HandlerGT handlerGT;
    // Game which the Segment is in
    private Game game;
    // List of AbstractGameObjects in Handler
    private ArrayList<AbstractGameObject> onScreenBoxes;
    // Hashset of potential collisions on the X axis
    private HashSet<ObjectPair> potentialOnX;
    // Hashset of potential collisions on the Y axis
    private HashSet<ObjectPair> potentialOnY;

    /**
     * Constructs the AbstractCollisionDetection class
     * 
     * @param handler HandlerGT
     * @param game Game which the Segment is in
     */
    public AbstractCollisionDetection(HandlerGT handler, Game game) {
        this.handlerGT = handler;
        this.game = game;
    }

    /**
     * Tick method which first gets the AbstractGameObjects and then checks for
     * collisions.
     */
    @Override
    public void tick() {
        produceList();
        sortAndSweep();
    }

    /**
     * Method that produces a list of AbstractGameObjects from the Handler.
     */
    public void produceList() {
        Iterator it = handlerGT.iterator();
        onScreenBoxes = new ArrayList<>();
        while (it.hasNext()) {
            Object obj = it.next();
            if (obj instanceof AbstractGameObject) {
                if (((AbstractGameObject) obj).getHitBox() != null) {
                    onScreenBoxes.add((AbstractGameObject) obj);
                }
            }
        }
    }
    /**
     * Method that sorts the positions of AbstractGameObjects by X and Y 
     * coordinates separately and then checks for potential collisions based on 
     * the Bounding Boxes on each axis. Once it has done so, it checks wether 2 
     * objects were found to have potential collisions on both axis.
     */
    public void sortAndSweep() {
        ArrayList<CoordinateAxisPair> numberLine = new ArrayList<>();
        potentialOnX = new HashSet<>();
        potentialOnY = new HashSet<>();
        for (int i = 0; i < onScreenBoxes.size(); ++i) {
            Shape shape = (onScreenBoxes.get(i)).getHitBox();
            AABB boundedBox = shape.getHurtBox();
            numberLine.add(new CoordinateAxisPair(boundedBox.getX(), onScreenBoxes.get(i)));
            numberLine.add(new CoordinateAxisPair(boundedBox.getX() + boundedBox.getWidth(), onScreenBoxes.get(i)));
        }
        Collections.sort(numberLine);
        HashSet<AbstractGameObject> currSet = new HashSet<>();

        for (int i = 0; i < numberLine.size(); ++i) {
            CoordinateAxisPair obj = numberLine.get(i);
            if (!currSet.contains(obj.getObject())) {
                for (AbstractGameObject other : currSet) {
                    ObjectPair pair = new ObjectPair(other, obj.getObject());
                    potentialOnX.add(pair);
                }
                currSet.add(obj.getObject());
            } else {
                currSet.remove(obj.getObject());
            }
        }
        numberLine = new ArrayList<>();
        for (int i = 0; i < onScreenBoxes.size(); ++i) {
            Shape shape = (onScreenBoxes.get(i)).getHitBox();
            AABB boundedBox = shape.getHurtBox();
            numberLine.add(new CoordinateAxisPair(boundedBox.getY(), onScreenBoxes.get(i)));
            numberLine.add(new CoordinateAxisPair(boundedBox.getY() + boundedBox.getHeight(), onScreenBoxes.get(i)));
        }

        Collections.sort(numberLine);

        currSet = new HashSet<>();
        for (int i = 0; i < numberLine.size(); ++i) {
            CoordinateAxisPair obj = numberLine.get(i);
            if (!currSet.contains(obj.getObject())) {
                for (AbstractGameObject other : currSet) {
                    ObjectPair pair = new ObjectPair(other, obj.getObject());
                    potentialOnY.add(pair);
                }
                currSet.add(obj.getObject());
            } else {
                currSet.remove(obj.getObject());
            }
        }

        for (ObjectPair obj : potentialOnX) {
            if (potentialOnY.contains(obj)) {
                checkCollision(obj.getFirstObject(), obj.getSecondObject());
            }
        }
    }
    
    /**
     * This method physically checks an ObjectPair for whether or not the 2
     * objects had an actual collision or not.
     * 
     * @param object1 First Object
     * @param object2 Second Object
     */
    public void checkCollision(AbstractGameObject object1, AbstractGameObject object2) {
        boolean collide = true;
        
        if (object1.getHitBox() instanceof Box && object2.getHitBox() instanceof Box) {
            
            Box box1 = ((Box)object1.getHitBox()).getCopyOf();
            Box box2 = ((Box)object2.getHitBox()).getCopyOf();
            PrinceAngle angle = box1.getAngle().getCopy();
            angle.negate();
            box1.rotate(angle.getInDeg());
            Vector vector = new Vector(box2.getX() - box1.getX(), box2.getY() - box1.getY());
            vector = rotateVector(vector, angle);
            Vector pos = new Vector(box1.getX(), box1.getY()).getIfAdded(vector);
            Box box3 = new Box((int)(pos.getX()) - (box2.getWidth() / 2), (int)(pos.getY()) - (box2.getHeight() / 2), box2.getWidth(), box2.getHeight(), (int)box2.getAngle().rotate(angle).getInDeg(), game);
            if (box1.getMinX() > box3.getMaxX() || box1.getMaxX() < box3.getMinX()) {
                collide = false;
            }
            box1 = ((Box)object1.getHitBox()).getCopyOf();
            box2 = ((Box)object2.getHitBox()).getCopyOf();
            angle.negate();
            angle = new PrinceAngle(90 - angle.getInDeg());
            box1.rotate(angle.getInDeg());
            vector = new Vector(box2.getX() - box1.getX(), box2.getY() - box1.getY());
            vector = rotateVector(vector, angle);
            pos = new Vector(box1.getX(), box1.getY()).getIfAdded(vector);
            box3 = new Box((int)(pos.getX()) - (box2.getWidth() / 2), (int)(pos.getY()) - (box2.getHeight() / 2), box2.getWidth(), box2.getHeight(), (int)box2.getAngle().rotate(angle).getInDeg(), game);
            if (box1.getMinX() > box3.getMaxX() || box1.getMaxX() < box3.getMinX()) {
                collide = false;
            }
            box1 = ((Box)object1.getHitBox()).getCopyOf();
            box2 = ((Box)object2.getHitBox()).getCopyOf();
            angle = box2.getAngle().getCopy();
            angle.negate();
            box2.rotate(angle.getInDeg());
            vector = new Vector(box1.getX() - box2.getX(), box1.getY() - box2.getY());
            vector = rotateVector(vector, angle);
            pos = new Vector(box2.getX(), box2.getY()).getIfAdded(vector);
            box3 = new Box((int) (pos.getX()) - (box1.getWidth() / 2), (int)(pos.getY()) - (box1.getHeight() / 2), box1.getWidth(), box1.getHeight(), (int)box1.getAngle().rotate(angle).getInDeg(), game);
            if (box2.getMinX() > box3.getMaxX() || box2.getMaxX() < box3.getMinX()) {
                collide = false;
            }
            box1 = ((Box)object1.getHitBox()).getCopyOf();
            box2 = ((Box)object2.getHitBox()).getCopyOf();
            angle.negate();
            angle = new PrinceAngle(90 - angle.getInDeg());
            box2.rotate(angle.getInDeg());
            vector = new Vector(box1.getX() - box2.getX(), box1.getY() - box2.getY());
            vector = rotateVector(vector, angle);
            pos = new Vector(box2.getX(), box2.getY()).getIfAdded(vector);
            box3 = new Box((int)(pos.getX()) - (box1.getWidth() / 2), (int)(pos.getY()) - (box1.getHeight() / 2), box1.getWidth(), box1.getHeight(), (int)box1.getAngle().rotate(angle).getInDeg(), game);
            if (box2.getMinX() > box3.getMaxX() || box2.getMaxX() < box3.getMinX()) {
                collide = false;
            }
        }
        else if (object1.getHitBox() instanceof Circle && object2.getHitBox() instanceof Circle) {
            Circle circle1 = ((Circle)object1.getHitBox()).getCopyOf();
            Circle circle2 = ((Circle)object2.getHitBox()).getCopyOf();
            float distance = (circle1.getX() - circle2.getX()) * (circle1.getX() - circle2.getX()) + (circle1.getY() - circle2.getY()) * (circle1.getY() - circle2.getY());
            distance = (int) Math.sqrt(distance);
            if (distance > circle1.getRadius() + circle2.getRadius()) {
                collide = false;
            }
        }
        else if (object1.getHitBox() instanceof Circle && object2.getHitBox() instanceof Box) {
            Circle circle3 = ((Circle)object1.getHitBox()).getCopyOf();
            Box box4 = ((Box)object2.getHitBox()).getCopyOf();

            PrinceAngle angle = box4.getAngle().getCopy();
            angle.negate();
            Vector centers = new Vector(circle3.getX() - box4.getX(), circle3.getY() - box4.getY());
            centers = rotateVector(centers, angle);
            Vector pos = new Vector(box4.getX(), box4.getY()).getIfAdded(centers);

            box4.rotate(angle.getInDeg());
            
            if (pos.getX() - circle3.getRadius() > box4.getMaxX()) {
                collide = false;
            }
            if (pos.getX() + circle3.getRadius() < box4.getMinX()) {
                collide = false;
            }
            
            box4 = ((Box)object2.getHitBox()).getCopyOf();

            angle = new PrinceAngle(90 - angle.getInDeg());
            centers = new Vector(circle3.getX() - box4.getX(), circle3.getY() - box4.getY());
            centers = rotateVector(centers, angle);
            pos = new Vector(box4.getX(), box4.getY()).getIfAdded(centers);

            box4.rotate(angle.getInDeg());

            if (pos.getX() - circle3.getRadius() > box4.getMaxX()) {
                collide = false;
            }
            if (pos.getX() + circle3.getRadius() < box4.getMinX()) {
                collide = false;
            }
            
            float diag = (float)(180 * Math.atan(1.0 * box4.getHeight() / box4.getWidth()) / Math.PI);
            angle = new PrinceAngle(diag);
            centers = new Vector(circle3.getX() - box4.getX(), circle3.getY() - box4.getY());
            centers = rotateVector(centers, angle);
            pos = new Vector(box4.getX(), box4.getY()).getIfAdded(centers);
            box4.rotate(angle.getInDeg());

            if (pos.getX() - circle3.getRadius() > box4.getMaxX()) {
                collide = false;
            }
            if (pos.getX() + circle3.getRadius() < box4.getMinX()) {
                collide = false;
            }
            
            diag = (float)(180f * Math.atan(1.0 * box4.getHeight() / box4.getWidth()) / Math.PI);
            diag = diag * -1;
            angle = new PrinceAngle(diag);
            centers = new Vector(circle3.getX() - box4.getX(), circle3.getY() - box4.getY());
            centers = rotateVector(centers, angle);
            pos = new Vector(box4.getX(), box4.getY()).getIfAdded(centers);
            box4.rotate(angle.getInDeg());

            if (pos.getX() - circle3.getRadius() > box4.getMaxX()) {
                collide = false;
            }
            if (pos.getX() + circle3.getRadius() < box4.getMinX()) {
                collide = false;
            }

        }
        else if (object1.getHitBox() instanceof Box && object2.getHitBox() instanceof Circle) {
            Circle circle3 = ((Circle)object2.getHitBox()).getCopyOf();
            Box box4 = ((Box)object1.getHitBox()).getCopyOf();

            PrinceAngle angle = box4.getAngle().getCopy();
            angle.negate();
            Vector centers = new Vector(circle3.getX() - box4.getX(), circle3.getY() - box4.getY());
            centers = rotateVector(centers, angle);
            Vector pos = new Vector(box4.getX(), box4.getY()).getIfAdded(centers);
            
            box4.rotate(angle.getInDeg());

            if (pos.getX() - circle3.getRadius() > box4.getMaxX()) {
                collide = false;
            }
            if (pos.getX() + circle3.getRadius() < box4.getMinX()) {
                collide = false;
            }

            box4 = ((Box) object1.getHitBox()).getCopyOf();

            angle = new PrinceAngle(90 - angle.getInDeg());
            centers = new Vector(circle3.getX() - box4.getX(), circle3.getY() - box4.getY());
            centers = rotateVector(centers, angle);
            pos = new Vector(box4.getX(), box4.getY()).getIfAdded(centers);

            box4.rotate(angle.getInDeg());

            if (pos.getX() - circle3.getRadius() > box4.getMaxX()) {
                collide = false;
            }
            if (pos.getX() + circle3.getRadius() < box4.getMinX()) {
                collide = false;
            }
            
            box4 = ((Box) object1.getHitBox()).getCopyOf();

            angle = new PrinceAngle(90 - angle.getInDeg());
            centers = new Vector(circle3.getX() - box4.getX(), circle3.getY() - box4.getY());
            centers = rotateVector(centers, angle);
            pos = new Vector(box4.getX(), box4.getY()).getIfAdded(centers);

            box4.rotate(angle.getInDeg());

            if (pos.getX() - circle3.getRadius() > box4.getMaxX()) {
                collide = false;
            }
            if (pos.getX() + circle3.getRadius() < box4.getMinX()) {
                collide = false;
            }
            
            float diag = (float)(180 * Math.atan(1.0 * box4.getHeight() / box4.getWidth()) / Math.PI);
            angle = new PrinceAngle(diag);
            centers = new Vector(circle3.getX() - box4.getX(), circle3.getY() - box4.getY());
            centers = rotateVector(centers, angle);
            pos = new Vector(box4.getX(), box4.getY()).getIfAdded(centers);
            box4.rotate(angle.getInDeg());

            if (pos.getX() - circle3.getRadius() > box4.getMaxX()) {
                collide = false;
            }
            if (pos.getX() + circle3.getRadius() < box4.getMinX()) {
                collide = false;
            }
            
            diag = (float)(180f * Math.atan(1.0 * box4.getHeight() / box4.getWidth()) / Math.PI);
            diag = diag * -1;
            angle = new PrinceAngle(diag);
            centers = new Vector(circle3.getX() - box4.getX(), circle3.getY() - box4.getY());
            centers = rotateVector(centers, angle);
            pos = new Vector(box4.getX(), box4.getY()).getIfAdded(centers);
            box4.rotate(angle.getInDeg());

            if (pos.getX() - circle3.getRadius() > box4.getMaxX()) {
                collide = false;
            }
            if (pos.getX() + circle3.getRadius() < box4.getMinX()) {
                collide = false;
            }
        }
        if (collide) {
            ifCollision(object1, object2);
        }
    }    
    
    //move this method to Vector class
    /**
     * Rotates a given Vector by a given angle
     * 
     * @param vector Given Vector
     * @param angle Given angle
     * @return Rotated Vector
     */
    public static Vector rotateVector(Vector vector, PrinceAngle angle) {
        PrinceAngle other = vector.getAbsAngle(new Vector(1, 0));
        if (vector.getY() < 0) {
            other.negate();
        }
        
        float mag = vector.magnitude();
        vector = new Vector((float)Math.cos(other.getInRad() + angle.getInRad()), (float)Math.sin(other.getInRad() + angle.getInRad()));
        vector.scaleUp(mag);
        return vector;
    }
    
    /**
     * Method that defines the outcome for when 2 objects have been identified
     * to have collided.
     * @param object1 First Object
     * @param object2 Second Object
     */
    public abstract void ifCollision(AbstractGameObject object1, AbstractGameObject object2);

    /**
     * Private class for an ObjectPair of AbstractGameObjects.
     */
    private class ObjectPair {
        
        //First Object
        private AbstractGameObject obj1;
        // Second Object
        private AbstractGameObject obj2;
        
        /**
         * Constructs an ObjectPair
         * 
         * @param one First Object
         * @param two Second Object
         */
        public ObjectPair(AbstractGameObject one, AbstractGameObject two) {
            this.obj1 = one;
            this.obj2 = two;
        }
        
        /**
         * Gets the first object.
         * 
         * @return First Object
         */
        public AbstractGameObject getFirstObject() {
            return obj1;
        }
        
        /**
         * Gets the second object.
         * 
         * @return Second Object
         */
        public AbstractGameObject getSecondObject() {
            return obj2;
        }

        //dont even need hashcode method for boxes? Not sure tbh
        /**
         * Gets the hashCode of the ObjectPair.
         * 
         * @return HashCode
         */
        @Override
        public int hashCode() {
            return obj1.hashCode() + obj2.hashCode();
        }
        
        /**
         * Checks whether or not this ObjectPair is equal to another ObjectPair.
         * 
         * @param obj Object to be compared to
         * @return True or False based on whether or not they are equal
         */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            ObjectPair other = (ObjectPair) obj;
            if (obj1 == other.obj1 && obj2 == other.obj2) {
                return true;
            }
            if (obj1 == other.obj2 && obj2 == other.obj1) {
                return true;
            }
            return false;
        }
    }
    
    /**
     * Private class that pairs a coordinate to an AbstractGameObject
     */
    private class CoordinateAxisPair implements Comparable<CoordinateAxisPair> {
        
        // Coordinate value(can be X or Y value)
        private float x;
        // Paired Object
        private AbstractGameObject obj;

        /**
         * Constructs the CoordinateAxisPair
         * 
         * @param x Coordinate
         * @param obj Paired Object
         */
        public CoordinateAxisPair(float x, AbstractGameObject obj) {
            this.x = x;
            this.obj = obj;
        }

        /**
         * Compares this CoordinateAxisPair to another one.
         * 
         * @param other CoordinateAxisPair
         * @return Whether or not the 2 are equal or which one is greater
         */
        @Override
        public int compareTo(CoordinateAxisPair other) {
            return Float.compare(this.x, other.x);
        }
        
        /**
         * Gets the coordinate of the Pair.
         * 
         * @return Coordinate
         */
        public float getX() {
            return x;
        }
        
        /**
         * Gets the Object of the Pair.
         * 
         * @return Paired Object
         */
        public AbstractGameObject getObject() {
            return obj;
        }
    }
}

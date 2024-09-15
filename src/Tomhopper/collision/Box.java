/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TomHopper.collision;

import TomHopper.Game;
import TomHopper.gui.OGraphics;
import TomHopper.utility.Vector;
import TomHopper.utility.PrinceAngle;

/**
 * A Box is one of the HitBox types that this game engine supports. Its simply
 * a rectangle that can be rotated.
 * 
 * Side note: Class needs get and set methods for angle.
 * 
 * @author ptehr
 */
public class Box extends Shape<Box> {

    // Vector for position is of the CENTER OF THE RECTANGLE
    // Width of the Box
    private int width;
    // Height of the Box
    private int height;
    
    // Change this input to input of the Center? Right now it inputs the top
    // left corner.
    /**
     * Constructs the Box with no rotation.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width
     * @param height Height
     * @param game Game which the object is in
     */
    public Box(int x, int y, int width, int height, Game game) {
        super(x + width / 2, y + height / 2, game);
        this.width = width;
        this.height = height;
        angle = new PrinceAngle();
    }
    
    /**
     * Constructs the Box with rotation.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width
     * @param height Height
     * @param angle Angle of rotation
     * @param game Game which the object is in
     */
    public Box(int x, int y, int width, int height, int angle, Game game) {
        this(x, y, width, height, game);
        this.angle = new PrinceAngle(angle);
    }

    /**
     * Updates the position of the Box.
     * 
     * @param pos Position that will be set
     */
    @Override
    public void updatePosition(Vector pos) {
        this.pos = pos.getIfAdded(new Vector(width / 2, height / 2));
    }

    /**
     * Gets the width of the Box.
     * 
     * @return Box Width
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Gets the height of the Box.
     * 
     * @return Box Height
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Sets the height of the Box to a given height.
     * 
     * @param height Given height
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * Sets the width of the Box to a given width.
     * 
     * @param width Given width
     */
    public void setWidth(int width) {
        this.width = width;
    }
    
    /**
     * Returns the Vector array of the vertices of the Box. Accounts for rotation
     * @return Vector array of vertices
     */
    public Vector[] getVertices() {
        Vector place = pos.getCopy();
        Vector[] arr = new Vector[4];
        Vector first = place.add(new Vector((float) Math.cos(angle.getInRad()), (float) Math.sin(angle.getInRad())).scaleUp(width / 2));
        first = first.add(new Vector(-1 * (float) Math.sin(angle.getInRad()), (float) Math.cos(angle.getInRad())).scaleUp(height / 2));
        arr[0] = first;
        place = pos.getCopy();
        Vector second = place.subtract(new Vector((float) Math.cos(angle.getInRad()), (float) Math.sin(angle.getInRad())).scaleUp(width / 2));
        second = second.add(new Vector(-1 * (float) Math.sin(angle.getInRad()), (float) Math.cos(angle.getInRad())).scaleUp(height / 2));
        arr[1] = second;
        place = pos.getCopy();
        Vector third = place.subtract(new Vector((float) Math.cos(angle.getInRad()), (float) Math.sin(angle.getInRad())).scaleUp(width / 2));
        third = third.subtract(new Vector(-1 * (float) Math.sin(angle.getInRad()), (float) Math.cos(angle.getInRad())).scaleUp(height / 2));
        arr[2] = third;
        place = pos.getCopy();
        Vector fourth = place.add(new Vector((float) Math.cos(angle.getInRad()), (float) Math.sin(angle.getInRad())).scaleUp(width / 2));
        fourth = fourth.subtract(new Vector(-1 * (float) Math.sin(angle.getInRad()), (float) Math.cos(angle.getInRad())).scaleUp(height / 2));
        arr[3] = fourth;
        return arr;
    }
    
    /**
     * Gets the max X coordinate of the vertices.
     * 
     * @return Max X coordinate
     */
    public int getMaxX() {
        Vector[] arr = getVertices();
        float maxX = Math.max(arr[0].getX(), arr[1].getX());
        maxX = Math.max(maxX, arr[2].getX());
        maxX = Math.max(maxX, arr[3].getX());
        return (int) maxX;
    }
    
    /**
     * Gets the min X coordinate of the vertices.
     * 
     * @return Min X coordinate
     */
    public int getMinX() {
        Vector[] arr = getVertices();
        float minX = Math.min(arr[0].getX(), arr[1].getX());
        minX = Math.min(minX, arr[2].getX());
        minX = Math.min(minX, arr[3].getX());
        return (int) minX;
    }

    /**
     * Gets the max Y coordinate of the vertices.
     * 
     * @return Max Y coordinate
     */
    public int getMaxY() {
        Vector[] arr = getVertices();
        float maxY = Math.max(arr[0].getY(), arr[1].getY());
        maxY = Math.max(maxY, arr[2].getY());
        maxY = Math.max(maxY, arr[3].getY());
        return (int) maxY;
    }
    
    /**
     * Gets the min Y coordinate of the vertices.
     * 
     * @return Min Y coordinate
     */
    public int getMinY() {
        Vector[] arr = getVertices();
        float minY = Math.min(arr[0].getY(), arr[1].getY());
        minY = Math.min(minY, arr[2].getY());
        minY = Math.min(minY, arr[3].getY());
        return (int) minY;
    }

    /**
     * Renders the Box.
     * 
     * @param og Given Graphics
     */
    @Override
    public void render(OGraphics og) {
        Vector[] arr = getVertices();
        for (int i = 0; i < 4; ++i) {
            Vector first = arr[i];
            Vector second = arr[(i + 1) % 4];
            og.drawLine((int) first.getX(), (int) first.getY(), (int) second.getX(), (int) second.getY());
        }
    }

    /**
     * Returns the AABB of the Box.
     * 
     * @return AABB of Box
     */
    @Override
    public AABB getHurtBox() {
        return new AABB(getMaxX(), getMinX(), getMaxY(), getMinY(), game);
    }
    
    /**
     * Gets a copy of the Box.
     * 
     * @return Box copy
     */
    public Box getCopyOf() {
        return new Box((int) this.getX() - (this.width / 2),
                (int) this.getY() - (this.height / 2), this.getWidth(),
                this.getHeight(), (int) getAngle().getInDeg(), game);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TomHopper.collision;

import TomHopper.Game;
import TomHopper.gui.OGraphics;
import TomHopper.utility.Vector;
import java.awt.*;

/**
 * This is another HitBox type that our game engine supports which is simply a 
 * Circle.
 *
 * @author ptehr
 */
public class Circle extends Shape<Circle> {
    
    // Radius of the Circle
    private int radius;
    
    /**
     * Constructs the Circle
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param radius Radius
     * @param game Game which object is in
     */
    public Circle(int x, int y, int radius, Game game) {
        super(x, y, game);
        this.radius = radius;
    }

    /**
     * Gets the radius of the Circle
     * 
     * @return Radius of the circle
     */
    public int getRadius() {
        return radius;
    }
    
    /**
     * Renders the Circle
     * 
     * @param og Given Graphics
     */
    @Override
    public void render(OGraphics og) {
        og.setColor(Color.pink);
        og.fillOval((int)getX() - radius, (int)getY() - radius, 2 * radius, 2 * radius);
    }
    
    /**
     * Updates the position of the Circle
     * 
     * @param pos Given position which will be set
     */
    @Override
    public void updatePosition(Vector pos) {
        this.pos = pos.getCopy();
    }
    
    /**
     * Returns the Bounding Box of the Circle
     * 
     * @return AABB of the Circle
     */
    @Override
    public AABB getHurtBox() {
        return new AABB(radius + (int) getX(), (int) getX() - radius, (int) getY() + radius, (int) getY() - radius, game);
    }
    
    /**
     * Gets a copy of the Circle
     * 
     * @return Circle copy
     */
    @Override
    public Circle getCopyOf() {
        return new Circle((int)this.getX(), (int)this.getY(), this.getRadius(), game);
    }
}

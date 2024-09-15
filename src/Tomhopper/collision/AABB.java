/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TomHopper.collision;

import TomHopper.Game;
import TomHopper.general.Renderable;
import TomHopper.general.Tickable;
import TomHopper.gui.*;
import TomHopper.utility.Vector;
import TomHopper.utility.PrinceAngle;
import java.awt.Rectangle;

/**
 * An AABB is an Axis Aligned Bounding Box. Basically what it is is a rectangle
 * that completely bounds a given object. This is necessary for collision
 * detection.
 * 
 * Side note: For box to AABB, make sure to use getVertices and do the 
 * constructor out or use the second constructor.
 * 
 * @author ptehr
 */
public class AABB extends AbstractWidget {

    /**
     * Constructs a Bounding Box
     * 
     * @param maxX Max X coordinate
     * @param minX Min X coordinate
     * @param maxY Max Y coordinate
     * @param minY Min Y coordinate
     * @param game Given Game
     */
    public AABB(int maxX, int minX, int maxY, int minY, Game game) {
        super(minX, minY, maxX - minX, maxY - minY, game);
    }
    
    /**
     * Renders the bounding box(was used for debugging purposes)
     * @param og Given Graphics
     */
    @Override
    public void render(OGraphics og) {
        /*
        og.setColor(Color.blue);
        og.drawRect((int)getX(), (int)getY(), width, height);
         */
    }

}

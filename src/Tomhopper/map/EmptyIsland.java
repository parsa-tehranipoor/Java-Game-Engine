/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.map;

import TomHopper.Game;
import TomHopper.grid.HexLocation;
import TomHopper.gui.OGraphics;
import TomHopper.utility.Vector;
import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Class for holding data on Islands in the HexGrid.
 *
 * @author ptehr
 * @param <T>
 */
public class EmptyIsland<T extends EmptyIsland> extends AbstractMapSpot<T> {

    // Bridges that go this EmptyIsland
    protected boolean[] bridges;
    // BufferedImage for what this Island looks like on a Map
    protected BufferedImage image;

    /**
     * Creates an EmptyIsland.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param game Game
     */
    public EmptyIsland(int x, int y, Game game) {
        super(x, y, game);
        renderOffset = new Vector(-15, -15);
        bridges = new boolean[6];
    }

    /**
     * Adds a bridge to the EmptyIsland bridges data field.
     *
     * @param degrees Direction of bridge
     */
    public void addBridge(int degrees) {
        int index = (degrees - 30) / 60;
        bridges[index] = true;
    }

    /**
     * Removes a bridge from the EmptyIsland bridges data field.
     *
     * @param degrees Direction of bridge
     */
    public void removeBridge(int degrees) {
        int index = (degrees - 30) / 60;
        bridges[index] = false;
    }
    
    /**
     * Sets the bridges to a given array.
     * 
     * @param arr Array of bridges
     */
    public void setBridges(boolean[] arr) {
        bridges = arr;
    }

    /**
     * Gets the bridges coming out of this EmptyIsland.
     * 
     * @return Array of bridges
     */
    public boolean[] getBridges() {
        return bridges;
    }

    // Whats the purpose of this method? Shouldn't be for THIS location not any?
    /**
     * Gets the location adjacent to the location given at a given angle.
     * 
     * @param r Row index
     * @param c Column index
     * @param degrees Angle
     * @return Location
     */
    public HexLocation getLocation(int r, int c, int degrees) {
        HexLocation loc = new HexLocation(r, c);
        HexLocation next = loc.getAdjacentHexLocation(degrees);
        return next;
    }

    @Override
    /**
     * Renders this EmptyIsland on the Map.
     * 
     * @param g Given Graphics
     */
    public void render(OGraphics og) {
        Game.resetDraw(og);
        og.setColor(Color.yellow);
        og.fillRect(0, 0, 30, 30);
        og.setColor(Color.white);
        if (image != null) {
            og.drawImage(image, 0, 0);
        } else if (getClass() != EmptyIsland.class) {
            og.setColor(Color.black);
            og.drawString(getClass().getSimpleName().substring(0, 1), 10, 15);
            //System.out.println("no image for " + getClass().getSimpleName() + " island");
        }
    }
    
}

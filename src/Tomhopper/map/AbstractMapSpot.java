/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TomHopper.map;

import TomHopper.Game;
import TomHopper.general.AbstractGameThing;
import TomHopper.structure.AbstractSegmentPlay;

/**
 * An object for holding data of location for spots on the Map(HexGrid).
 *
 * @author ptehr
 * @param <T>
 */
public abstract class AbstractMapSpot<T extends AbstractMapSpot> extends AbstractGameThing<T> {

    // Stores the direction(and necessarily the location) of the directions of adjacents
    // 0 -> 30 degrees(up right)
    public static int upRight = 0;
    // 1 -> 90 degrees(up)
    public static int up = 1;
    // 2 -> 150 degrees(up left)
    public static int upLeft = 2;
    // 3 -> 210 degrees(down left)
    public static int downLeft = 3;
    // 4 -> 270 degrees(down)
    public static int down = 4;
    // 5 -> 330 degrees(down right)
    public static int downRight = 5;
    
    /**
     * Constructs an AbstractMapSpot
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param game Game
     */
    public AbstractMapSpot(int x, int y, Game game) {
        super(x, y, game);
    }
}

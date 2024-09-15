/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TomHopper.map;

import TomHopper.Game;
import TomHopper.art.Sprites;

/**
 * A class for the TreasureIsland which is an extension of an EmptyIsland.
 * 
 * Side note: Not really finished or needed right now
 * @author ptehranipoor
 */
public class TreasureIsland extends EmptyIsland {

    // Name of picture of Island
    String file;

    /**
     * Constructs a TreasureIsland object.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param game Game
     */
    public TreasureIsland(int x, int y, Game game) {
        super(x, y, game);
        image = Sprites.getImage("Chest_Island");
    }
}

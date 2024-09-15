/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TomHopper.map;

import TomHopper.Game;

/**
 * A class for the PuzzleIsland which is an extension of an EmptyIsland.
 * 
 * Side note: Not really finished or needed right now
 * @author ptehranipoor
 */
public class PuzzleIsland extends EmptyIsland {

    // Name of picture of Island
    String file;

    /**
     * Constructs a PuzzleIsland object.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param game Game
     */
    public PuzzleIsland(int x, int y, Game game) {
        super(x, y, game);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.structure;

import TomHopper.Game;
import TomHopper.HexWorld;
import TomHopper.segment.SegmentMap;

/**
 * A class for the Map state of the Game(aka showing the world map and other
 * accessories).
 *
 * @author cdwan
 */
public class GameMap extends AbstractGameState {

    // HexWorld data field
    private HexWorld hexworld;

    /**
     * Creates a GameMap object.
     *
     * @param hw Given HexWorld
     * @param game Given Game
     */
    public GameMap(HexWorld hw, Game game) {
        super(game);
        this.hexworld = hw;
        curSeg = new SegmentMap(hw, game);
    }

    /**
     * Returns the HexWorld.
     *
     * @return HexWorld
     */
    public HexWorld getHexWorld() {
        return hexworld;
    }

}

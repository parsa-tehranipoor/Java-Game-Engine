/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.structure;

import TomHopper.Game;

/**
 * To differentiate segments for the Map GameState. 
 * 
 * Side note: currently useless
 * @author cdwan
 */
public abstract class AbstractSegmentMap extends AbstractSegment {
    
    /**
     * Constructs an AbstractSegmentMap object.
     * 
     * @param game Given Game
     */
    public AbstractSegmentMap(Game game) {
        super(game);
    }
}

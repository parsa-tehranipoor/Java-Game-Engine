/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.structure;

import TomHopper.Game;

/**
 * To differentiate segments for the Play GameState
 * 
 * Side note: currently useless
 * @author cdwan
 */
public abstract class AbstractSegmentPlay extends AbstractSegment {
    
    /**
     * Constructs an AbstractSegmentPlay object.
     * 
     * @param game Given Game
     */
    public AbstractSegmentPlay(Game game) {
        super(game);
    }
    
}

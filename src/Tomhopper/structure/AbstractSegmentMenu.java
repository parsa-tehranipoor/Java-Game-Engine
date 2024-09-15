/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.structure;

import TomHopper.Game;

/**
 * To differentiate segments for the Menu GameState.
 * 
 * Side note: useless right now
 * @author cdwan
 */
public abstract class AbstractSegmentMenu extends AbstractSegment {
    
    /**
     * Constructs an AbstractSegmentMenu object.
     * 
     * @param game Given Game
     */
    public AbstractSegmentMenu(Game game) {
        super(game);
    }
    
}

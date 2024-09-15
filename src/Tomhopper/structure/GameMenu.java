/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.structure;

import TomHopper.Game;
import TomHopper.segment.SegmentChooseSegment;

/**
 * A class for the Menu state of the Game.
 *
 * @author cdwan
 */
public class GameMenu extends AbstractGameState {

    /**
     * Creates a GameMenu state.
     * 
     * @param game Given Game
     */
    public GameMenu(Game game) {
        super(game);
        curSeg = new SegmentChooseSegment(game);
    }
}

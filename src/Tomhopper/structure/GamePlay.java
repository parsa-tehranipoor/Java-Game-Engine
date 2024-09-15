/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.structure;

import TomHopper.Game;
import TomHopper.HexWorld;
import TomHopper.input.AbstractKeyControl;
import TomHopper.input.KeyAgent;
import TomHopper.segment.SegmentChooseSegment;
import java.awt.event.KeyEvent;

/**
 * A class for the Play state of the Game.
 *
 * @author cdwan
 */
public class GamePlay extends AbstractGameState {

    /**
     * Creates a GamePlay object.
     *
     * @param hw HexWorld
     * @param game Given Game
     */
    public GamePlay(HexWorld hw, Game game) {
        super(game);
        keyBinds = new KeyBinds();
        curSeg = new SegmentChooseSegment(game);
    }

    /**
     * A class for the purpose of setting up a button that will allow the user
     * to return back to the menu state with a key press.
     */
    private class KeyBinds extends AbstractKeyControl {

        /**
         * Constructs a KeyBind Object.
         */
        public KeyBinds() {
            super(game);
        }

        /**
         * Sets up the KeyAgents. For this KeyBind, its only for the 'R' Key.
         */
        @Override
        protected void keyPressedSetUp() {
            addKeyAgent(new KeyAgent(KeyEvent.VK_R), 25);
        }

        /**
         * If the user presses the key 'R', it returns the user back to the 
         * Menu.
         * 
         * @param keyAgent Given KeyAgent
         */
        @Override
        protected void keyPressed(String keyAgent) {
            if (keyAgent.equals("R")) {
                game.setState("menu");
                return;
            }
        }
    }
    
}

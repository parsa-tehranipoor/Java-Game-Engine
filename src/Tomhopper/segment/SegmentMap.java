/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.segment;

import TomHopper.Game;
import TomHopper.HexWorld;
import TomHopper.gui.OGraphics;
import TomHopper.input.AbstractKeyControl;
import TomHopper.input.KeyAgent;
import TomHopper.structure.AbstractSegmentMap;
import java.awt.event.KeyEvent;

/**
 * The (sole?) map segment displaying the map
 *
 * @author cdwan
 */
public class SegmentMap extends AbstractSegmentMap {

    //world the map is based on
    private HexWorld hexworld;

    /**
     * constructs the SegmentMap from a HexWorld
     *
     * @param hw HexWorld
     * @param game the game
     */
    public SegmentMap(HexWorld hw, Game game) {
        super(game);
        keyBinds = new KeyBinds();
        hexworld = hw;
    }

    @Override
    /**
     * renders the world
     */
    public void render(OGraphics og) {
        hexworld.render(og);
        super.render(og);
    }

    /**
     * gives you the (hex)world<3
     *
     * @return
     */
    public HexWorld getHexWorld() {
        return hexworld;
    }

    private class KeyBinds extends AbstractKeyControl {
        
        public KeyBinds() {
            super(game);
        }

        @Override
        protected void keyPressedSetUp() {
            addKeyAgent(new KeyAgent(KeyEvent.VK_R), 20); //reset button
        }

        @Override
        protected void keyPressed(String keyAgent) {
            if (keyAgent.equals("R")) {
                hexworld = new HexWorld(game); //temporarily will generate more (but doesn't synch w game class!)
                return;
            }
        }
        
    }
}

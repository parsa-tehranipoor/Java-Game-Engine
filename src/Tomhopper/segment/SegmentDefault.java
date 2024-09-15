/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.segment;

import TomHopper.Game;
import TomHopper.gui.OGraphics;
import TomHopper.structure.AbstractSegmentPlay;
import java.awt.Font;

/**
 * The default segment(shouldn't be seen in finished game)
 *
 * @author cdwan
 */
public class SegmentDefault extends AbstractSegmentPlay {

    /**
     * constructs the default segment
     * @param game the game
     */
    public SegmentDefault(Game game) {
        super(game);
    }

    @Override
    /**
     * renders the default segment saying its the default
     */
    public void render(OGraphics og) {
        Game.resetDraw(og);
        og.setFont(new Font("arial", 4, 70));
        og.drawString("default segment", 100, 300);
        og.drawString("replace plz :(", 120, 400);
        super.render(og);
    }
    
}

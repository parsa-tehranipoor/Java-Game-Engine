/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.segment;

import TomHopper.Game;
import TomHopper.gui.ColorPack;
import TomHopper.gui.bandm.MessageContent;
import TomHopper.structure.AbstractSegmentPlay;

/**
 *
 * @author cdwan
 */
public class SegmentWeb extends AbstractSegmentPlay {

    public SegmentWeb(Game game) {
        super(game);
        for (int y = 0; y < 6; ++y) {
            for (int x = 0; x < 3; ++x) {
                new MessageContent("number " + (y + x * 6 + 1), 150 + 150 * x, 60 + 80 * y,
                         100, 50, ColorPack.WonB, game, "hi").putSelfInHandler(handlerGT);
            }
        }
    }

}

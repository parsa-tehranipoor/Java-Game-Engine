/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.segment;

import TomHopper.Game;
import TomHopper.gui.ColorPack;
import TomHopper.gui.OGraphics;
import TomHopper.gui.bandm.AbstractButton;
import TomHopper.gui.bandm.Message;
import TomHopper.input.AbstractKeyControl;
import TomHopper.input.KeyAgent;
import TomHopper.structure.AbstractSegment;
import TomHopper.structure.AbstractSegmentMenu;
import TomHopper.utility.Vector;
import TomHopper.utility.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author cdwan
 */
public class SegmentChooseSegment extends AbstractSegmentMenu {

    public SegmentChooseSegment(Game game) {
        super(game);
        keyBinds = new KeyBinds();
        //initialize data fields and construct objects for game (generally auto-added)
        new Message("choose a segment to play", 280, 80, 200, 50, game).putSelfInHandler(handlerGT);
        new RunSegmentButton("Default", 260, 160, 100, 40, ColorPack.WonB, game).putSelfInHandler(handlerGT);
        new RunSegmentButton("Idle", 260, 230, 100, 40, ColorPack.WonB, game).putSelfInHandler(handlerGT);
        new RunSegmentButton("Template", 260, 300, 100, 40, ColorPack.WonB, game).putSelfInHandler(handlerGT);
        new RunSegmentButton("CollisionTest", 260, 370, 100, 40, ColorPack.WonB, game).putSelfInHandler(handlerGT);
        new RunSegmentButton("PolarPlayerTest", 400, 160, 100, 40, ColorPack.WonB, game).putSelfInHandler(handlerGT);
        new RunSegmentButton("TankBattle", 400, 230, 100, 40, ColorPack.WonB, game).putSelfInHandler(handlerGT);
        new RunSegmentButton("Web", 400, 300, 100, 40, ColorPack.WonB, game).putSelfInHandler(handlerGT);
        new RunSegmentButton("PuzzleIslandGame", 400, 370, 100, 40, ColorPack.WonB, game).putSelfInHandler(handlerGT);
        new RunSegmentButton("Anish", 260, 440, 100, 40, ColorPack.WonB, game).putSelfInHandler(handlerGT);
        new RunSegmentButton("AnimageTest", 400, 440, 100, 40, ColorPack.WonB, game).putSelfInHandler(handlerGT);
    }

    @Override
    public void tick() {
        super.tick();
        //add some tick stuff if needed
    }

    @Override
    public void render(OGraphics og) {
        Game.resetDraw(og);
        //render general stuff here
        
        super.render(og);
    }

    private class KeyBinds extends AbstractKeyControl {

        public KeyBinds() {
            super(game);
        }

        @Override
        public void keyPressedSetUp() {
            addKeyAgent(new KeyAgent(KeyEvent.VK_F), 50);
        }

        @Override
        protected void keyPressed(String keyAgent) {
            if (keyAgent.equals("F")) {
                //do stuff
                return;
            }
        }
    }

    private class RunSegmentButton<T extends AbstractSegment> extends AbstractButton<RunSegmentButton> {

        AbstractSegment seg;

        public RunSegmentButton(String segName, int x, int y, int w, int h, ColorPack cp, Game game) {
            super(segName, x, y, w, h, cp, game);
            seg = Segments.getNewSeg(segName, game);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            game.runSegment(seg);
        }
    }

}

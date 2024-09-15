/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.segment;

import TomHopper.gui.IndicatorDirection;
import TomHopper.gui.ColorPack;
import TomHopper.gui.AbstractWidget;
import TomHopper.movement.Movement2D;
import TomHopper.Game;
import TomHopper.gui.OGraphics;
import TomHopper.input.AbstractKeyControl;
import TomHopper.input.KeyAgent;
import TomHopper.movement.RotationKey360;
import TomHopper.structure.AbstractSegmentPlay;
import TomHopper.things.GravBullet;
import TomHopper.utility.PrinceAngle;
import TomHopper.utility.Vector2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author cdwan
 */
public class SegmentPolarPlayerTest extends AbstractSegmentPlay {

    private ShootinTom tom;

    public SegmentPolarPlayerTest(Game game) {
        super(game);
        keyBinds = new KeyBinds();
        tom = new ShootinTom(50, 50, game).putSelfInHandler(handlerGT);
    }

    @Override
    public void tick() {
        super.tick();
        //add extra tick stuff here
    }

    @Override
    public void render(OGraphics og) {
        Game.resetDraw(og);
        //render stuff here

        super.render(og);
    }

    private class KeyBinds extends AbstractKeyControl {

        public KeyBinds() {
            super(game);
        }

        @Override
        public void keyPressedSetUp() {
            addKeyAgent(new KeyAgent(KeyEvent.VK_SPACE), 50);
        }

        @Override
        protected void keyPressed(String keyAgent) {
            if (keyAgent.equals("Space")) {
                tom.shoot();
                return;
            }
        }

    }

    //add any extra methods
    //make any extra classes if necessary, eg below:
    private class ShootinTom extends AbstractWidget<ShootinTom> {

        //data fields
        static final int WIDTH = 70, HEIGHT = 70;
        static final float SPDX = 7, SPDY = 7;

        //constructor(s)
        public ShootinTom(int x, int y, Game game) {
            super(x, y, WIDTH, HEIGHT, game);
            mov = new Movement2D(SPDX, SPDY, game);
            angle = new PrinceAngle();
            rot = new RotationKey360(3, this, game);
        }

        public void shoot() {
            new GravBullet(new Vector2D(12f, angle),
                    getCenter()[0], getCenter()[1], game).putSelfInHandler(handlerGT);
        }

        @Override
        public void render(OGraphics og) {
            OGraphics relOG = getRelRenOG(og);
            //render stuff
            AbstractWidget.renderMessage(relOG, "P", 0, 0, WIDTH, HEIGHT, ColorPack.WonB);
            int indside = 10;
            IndicatorDirection.drawIndicator(relOG, 15, angle, WIDTH / 2, HEIGHT / 2, indside);
        }
    }
}

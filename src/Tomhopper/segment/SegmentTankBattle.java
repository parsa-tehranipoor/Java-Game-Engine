/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.segment;

import TomHopper.gui.ColorPack;
import TomHopper.Game;
import TomHopper.gui.AbstractWidget;
import TomHopper.gui.IndicatorDirection;
import TomHopper.gui.OGraphics;
import TomHopper.input.AbstractKeyControl;
import TomHopper.input.KeyAgent;
import TomHopper.movement.RotationKey360;
import TomHopper.movement.Movement1D;
import TomHopper.structure.AbstractSegmentPlay;
import TomHopper.things.GravBullet;
import TomHopper.utility.PrinceAngle;
import TomHopper.utility.Vector;
import TomHopper.utility.Vector2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author cdwan
 */
public class SegmentTankBattle extends AbstractSegmentPlay {

    //data fields
    private static final int FLOORY = 500;
    private TomTank tom;

    public SegmentTankBattle(Game game) {
        super(game);
        keyBinds = new KeyBinds();
        //add stuff here (messages, buttons, other gamethings)
        tom = new TomTank("tom", 200, ColorPack.WonB, game).putSelfInHandler(handlerGT);
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
        og.drawLine(0, FLOORY, Game.WIDTH, FLOORY);

        super.render(og);
    }

    //add any extra methods
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

    //make any extra classes if necessary, eg below:
    private class TomTank extends AbstractWidget<TomTank> {

        //data fields
        private static final int WIDTH = 40, HEIGHT = 30;
        private String name;
        private ColorPack cpack;

        //constructor(s)
        public TomTank(String name, int x, ColorPack cp, Game game) {
            super(x, FLOORY - HEIGHT, WIDTH, HEIGHT, cp, game);
            this.name = name;
            this.cpack = cp;
            mov = new Movement1D(new Vector(3, -1.5f), game);
            angle = new PrinceAngle();
            rot = new RotationKey360(3, this, game);
        }

        public void shoot() {
            new GravBullet(new Vector2D(10, angle),
                    getCenter()[0], getCenter()[1], game).putSelfInHandler(handlerGT);
        }

        @Override
        public void render(OGraphics og) {
            super.render(og);
            OGraphics relG = getRelRenOG(og);
            //render stuff
            relG.drawString(name, width / 4, height * 2 / 3);
            IndicatorDirection.drawIndicator(relG, 10, angle, WIDTH / 2, HEIGHT / 2, 5);
        }
    }
}

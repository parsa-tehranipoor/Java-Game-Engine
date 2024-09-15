/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TomHopper.segment;

import TomHopper.movement.AbstractGameObject;
import TomHopper.Game;
import TomHopper.general.*;
import TomHopper.utility.*;
import TomHopper.gui.*;
import TomHopper.input.*;
import TomHopper.structure.*;
import TomHopper.movement.*;
import TomHopper.things.*;
import TomHopper.collision.*;
import TomHopper.gui.bandm.ResetButton;
import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 *
 * @author cdwan
 */
public class SegmentCollisionTest extends AbstractSegmentPlay implements Resettable {

    private Player player;
    private AI ai;

    /**
     * constructs the segment
     *
     * @param game the game
     */
    public SegmentCollisionTest(Game game) {
        super(game);
        keyBinds = new KeyBinds();
        player = new Player(50, 50, game).putSelfInHandler(handlerGT);
        collisions = new Collisions(handlerGT, game);
        ai = new AI(300, 300, game, player, new TickTimer(60)).putSelfInHandler(handlerGT);
        for (int i = 0; i < 10; ++i) {
            new StraightBullet(new Vector2D(5, new PrinceAngle(-90)), 500 + i * 30, 400, game).putSelfInHandler(handlerGT);
        }
        new ResetButton(this, "reset...", 500, 40, 60, 30, game).putSelfInHandler(handlerGT);
        new NewObjects(125, 520, new Box(100, 500, 80, 40, 40, game), game).putSelfInHandler(handlerGT);
        new NewObjects(425, 525, new Circle(225, 525, 45, game), game).putSelfInHandler(handlerGT);

    }

    @Override
    public void reset() {
        handlerGT.clear();
        player = new Player(50, 50, game).putSelfInHandler(handlerGT);
        ai = new AI(300, 300, game, player, new TickTimer(60)).putSelfInHandler(handlerGT);
        new ResetButton(this, "reset...", 500, 40, 60, 30, game).putSelfInHandler(handlerGT);
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
                player.shoot();
                return;
            }
        }
    }

    private class NewObjects extends AbstractGameObject<NewObjects> {

        public NewObjects(int x, int y, Shape shape, Game game) {
            super(x, y, new Vector(0, 0), shape, game);
        }

        @Override
        public void render(OGraphics og) {
            og.setColor(Color.green);
            this.getHitBox().render(og);
        }
    }

    private class Player extends AbstractWidget<Player> {

        static final int WIDTH = 70;
        static final int HEIGHT = 70;

        public Player(int x, int y, Game game) {
            super(x, y, WIDTH, HEIGHT, game);
            mov = new Movement2D(5, 5, game);
            angle = new PrinceAngle();
            rot = new RotationKey360(3, this, game);
        }

        public void shoot() {
            StraightBullet bull = new StraightBullet(new Vector2D(12f, angle),
                    (int) getHitBox().getX(), (int) getHitBox().getY(), game);
            bull.addToIgnoreList(this);
            bull.putSelfInHandler(handler);
        }

        @Override
        public void render(OGraphics og) {
            OGraphics relOG = getRelRenOG(og);
            //render stuff
            AbstractWidget.renderMessage(relOG, "P", 0, 0, WIDTH, HEIGHT, ColorPack.WonB);
            int indside = 10;
            IndicatorDirection.drawIndicator(relOG, 15, angle, WIDTH / 2, HEIGHT / 2, indside);
            /*g.setColor(Color.red);
            hitbox.render(g);*/
        }

    }

    private class AI extends AbstractWidget<AI> {

        static final int WIDTH = 70;
        static final int HEIGHT = 70;

        TickTimer timer;
        Player player;

        public AI(int x, int y, Game game, Player player, TickTimer timer) {
            super(x, y, WIDTH, HEIGHT, game);
            this.timer = timer;
            this.player = player;
            angle = new PrinceAngle(0);
        }

        public void shoot() {
            StraightBullet bull = new StraightBullet(new Vector2D(12f, angle),
                    (int) getHitBox().getX(), (int) getHitBox().getY(), game);
            bull.addToIgnoreList(this);
            bull.putSelfInHandler(handler);
        }

        @Override
        public void tick() {
            super.tick();
            if (timer.interact()) {
                float px = player.getX();
                float py = player.getY();
                Vector v = new Vector2D(px - getX(), py - getY());
                Vector2D vp = (Vector2D) v;
                angle = (vp).getAngle();
                shoot();
            }
        }

        @Override
        public void render(OGraphics og) {
            OGraphics relG = getRelRenOG(og);
            //render stuff
            AbstractWidget.renderMessage(relG, "AI", 0, 0, WIDTH, HEIGHT, ColorPack.WonB);
            int indside = 10;
            IndicatorDirection.drawIndicator(relG, 15, angle, WIDTH / 2, HEIGHT / 2, indside);
        }

    }

    private class Collisions extends AbstractCollisionDetection {

        public Collisions(HandlerGT handler, Game game) {
            super(handler, game);
        }

        @Override
        public void ifCollision(AbstractGameObject obj1, AbstractGameObject obj2) {
            if (obj1 instanceof StraightBullet && obj2 instanceof AI) {
                StraightBullet bull = (StraightBullet) obj1;
                AI ai = (AI) obj2;
                if (!bull.isInIgnoreList(ai)) {
                    obj1.removeSelfFromHandler();
                    obj2.removeSelfFromHandler();
                }
            } else if (obj1 instanceof AI && obj2 instanceof StraightBullet) {
                AI ai = (AI) obj1;
                StraightBullet bull = (StraightBullet) obj2;
                if (!bull.isInIgnoreList(ai)) {
                    obj1.removeSelfFromHandler();
                    obj2.removeSelfFromHandler();
                }
            } else if ((obj1 instanceof StraightBullet && obj2 instanceof Player)) {
                StraightBullet bull = (StraightBullet) obj1;
                Player player = (Player) obj2;
                if (!bull.isInIgnoreList(player)) {
                    obj1.removeSelfFromHandler();
                    obj2.removeSelfFromHandler();
                }
            } else if ((obj1 instanceof Player && obj2 instanceof StraightBullet)) {
                Player player = (Player) obj1;
                StraightBullet bull = (StraightBullet) obj2;
                if (!bull.isInIgnoreList(player)) {
                    obj1.removeSelfFromHandler();
                    obj2.removeSelfFromHandler();
                }
            } else if (obj1 instanceof StraightBullet && obj2 instanceof StraightBullet) {
                obj1.removeSelfFromHandler();
                obj2.removeSelfFromHandler();
            } else if (obj1 instanceof NewObjects && obj2 instanceof Player) {
                Player bro = (Player) obj2;
                bro.setX(10);
                bro.setY(10);
            } else if (obj1 instanceof Player && obj2 instanceof NewObjects) {
                Player bro = (Player) obj1;
                bro.setX(10);
                bro.setY(10);
            }
        }
    }

}

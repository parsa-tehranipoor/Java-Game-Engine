/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.segment;

import TomHopper.Game;
import TomHopper.collision.AbstractCollisionDetection;
import TomHopper.collision.Box;
import TomHopper.general.*;
import TomHopper.utility.*;
import TomHopper.gui.*;
import static TomHopper.gui.IndicatorDirection.drawIndicator;
import TomHopper.gui.bandm.*;
import TomHopper.input.*;
import TomHopper.movement.AbstractGameObject;
import TomHopper.movement.Movement1D;
import TomHopper.art.Sprites;
import TomHopper.structure.*;
import TomHopper.things.BouncingDir;
import TomHopper.things.GravBullet;
import TomHopper.things.StraightBullet;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Demo idle game to show game "engine" structure capabilities and ease of use
 * not tryna comment header all of it tho...
 *
 * @author cdwan
 */
public class SegmentAnish extends AbstractSegmentPlay {

    //data fields here
    private Anish anish1;
    private Anish anish2;
    private TickTimer spawnL, spawnR;
    private static final int SPAWNSPD = 128;
    public final boolean twoPlaying = true;
    private boolean spawning = true;

    public SegmentAnish(Game game) {
        super(game);
        keyBinds = new KeyBinds();
        collisions = new Collisions();
        anish1 = new Anish(1, 100, 500 - 50, game).putSelfInHandler(handlerGT);
        if (twoPlaying) {
            anish2 = new Anish(2, 200, 500 - 50, game).putSelfInHandler(handlerGT);
        }
        new Bird(200, 200, true, game).putSelfInHandler(handlerGT);
        new Bird(200, 160, false, game).putSelfInHandler(handlerGT);
        spawnL = new TickTimer(SPAWNSPD);
        spawnR = new TickTimer(SPAWNSPD);
    }

    //make extra methods
    @Override
    public void tick() {
        super.tick();
        //add some tick stuff if needed
        if (spawning) {
            if (spawnL.interact()) {
                new NPC(700, false, game).putSelfInHandler(handlerGT);
            }
            if (spawnR.interact()) {
                new NPC(0, true, game).putSelfInHandler(handlerGT);
            }
        }
    }

    @Override
    public void render(OGraphics og) {
        Game.resetDraw(og);
        //render general stuff here
        og.drawImage(Sprites.getImage("Anish_Homeland"), 0, 0);
        og.drawLine(0, 500, Game.WIDTH, 500);

        super.render(og);
    }

    //add keybinds in this class
    private class KeyBinds extends AbstractKeyControl {

        public KeyBinds() {
            super(game);
        }

        @Override
        public void keyPressedSetUp() {
            addKeyAgent(new KeyAgent(KeyEvent.VK_O), 15);
            addKeyAgent(new KeyAgent(KeyEvent.VK_W), 35);
            addKeyAgent(new KeyAgent(KeyEvent.VK_S), 15);
            addKeyAgent(new KeyAgent(KeyEvent.VK_UP), 35);
            addKeyAgent(new KeyAgent(KeyEvent.VK_DOWN), 15);
            addKeyAgent(new KeyAgent(KeyEvent.VK_P), 15);
        }

        @Override
        protected void keyPressed(String keyAgent) {
            if (keyAgent.equals("W")) {
                anish1.kick();
                return;
            }
            if (keyAgent.equals("S")) {
                if (anish1.canShoot()) {
                    anish1.shoot();
                }
                return;
            }
            if (keyAgent.equals("P")) {
                anish1.grow();
                if (twoPlaying) {
                    anish2.grow();
                }
                return;
            }
            if (keyAgent.equals("O")) {
                spawning = !spawning;
                return;
            }
            if (twoPlaying) {
                if (keyAgent.equals("Up")) {
                    anish2.kick();
                    return;
                }
                if (keyAgent.equals("Down")) {
                    if (anish2.canShoot()) {
                        anish2.shoot();
                    }
                    return;
                }
            }
            /*if (keyAgent.equals("J")) {
                anish1.setDir(false);
                return;
            }
            if (keyAgent.equals("K")) {
                anish1.setDir(true);
                return;
            }*/
        }
    }

    private class Collisions extends AbstractCollisionDetection {

        public Collisions() {
            super(handlerGT, game);
        }

        @Override
        public void ifCollision(AbstractGameObject object1, AbstractGameObject object2) {
            if (object1 instanceof NPC && object2 instanceof Foot) {
                object1.removeSelfFromHandler();
                ((Foot) object2).getAnish().grow();
            }
            if (object1 instanceof Foot && object2 instanceof NPC) {
                object2.removeSelfFromHandler();
                ((Foot) object1).getAnish().grow();
            }

            if (object1 instanceof Anish && object2 instanceof Foot) {
                Foot foot = (Foot) object2;
                Anish anish = (Anish) object1;
                if (foot.getAnish() != anish) {
                    anish.pushed(foot.facesRight());
                }
            }
            if (object1 instanceof Foot && object2 instanceof Anish) {
                Foot foot = (Foot) object1;
                Anish anish = (Anish) object2;
                if (foot.getAnish() != anish) {
                    anish.pushed(foot.facesRight());
                }
            }

            if (object1 instanceof NPC && object2 instanceof Anish) {
                object1.removeSelfFromHandler();
                ((Anish) object2).reset();
            }
            if (object1 instanceof Anish && object2 instanceof NPC) {
                object2.removeSelfFromHandler();
                ((Anish) object1).reset();
            }

            if (object1 instanceof NPC && object2 instanceof StraightBullet) {
                object1.removeSelfFromHandler();
            }
            if (object1 instanceof StraightBullet && object2 instanceof NPC) {
                object2.removeSelfFromHandler();
            }
        }

    }

    private class Bird extends AbstractWidget<Bird> {

        public static final int WIDTH = 40, HEIGHT = 40;
        private int spdX = 1;
        private int y0;
        private float dyt = 0;
        private boolean right;

        public Bird(int x, int y, boolean r, Game game) {
            super(x, y, WIDTH, HEIGHT, game);
            right = r;
            y0 = y;
        }

        @Override
        public void tick() {
            if (right) {
                addX(spdX);
            } else {
                addX(-spdX);
            }
            dyt += .05;
            setY(y0 + 20 * (float) Math.sin(dyt));
        }

    }

    private class Anish extends AbstractWidget<Anish> implements Container {

        //data fields. w classes in classes, watch out for repeat field 
        //names overriding, eg w handler
        private int number;
        private int spd0 = 1;
        private HandlerGT handlerInternal;
        private TickTimer growTimer;
        private Foot foot;
        private TickTimer footTimer, shootTimer;
        private boolean right = true;
        private BouncingDir duckDir;
        private int yshoot = 350;

        public Anish(int numero, int x, int y, Game game) {
            super(x, y, 40, 50, "Anish", game);
            Vector speed = new Vector(spd0);
            number = numero;
            handlerInternal = new HandlerGT();
            if (numero == 1) {
                mov = new Movement1D(speed, game);
            } else if (numero == 2) {
                mov = new Movement1D(speed, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, game);
            }
            growTimer = new TickTimer(12, 0);
            footTimer = new TickTimer(18, 0);
            shootTimer = new TickTimer(32, 0);
            foot = new Foot(this, width - 12, height - 32, 60, 24, game);
            renderOffset = new Vector(-48, 0);
            duckDir = new BouncingDir();
        }

        @Override
        public void tick() {
            super.tick();
            //stuff here
            right = ((Movement1D) mov).movingRight();
            if (footTimer.check() > 0 && footTimer.interact()) {
                foot.removeSelfFromHandler();
            }
            duckDir.tick();
        }

        //specific methods also
        @Override
        public void render(OGraphics og) {
            OGraphics relOG = getRelObjOG(og);
            relOG.drawString("Anish", width / 4 - 4, height - 6);
            super.render(og);
            relOG.setColor(Color.black);
            relOG.fillRect(width / 2 - 3, 50, 6, height - 60);
            if (growTimer.check() > 0) {
                relOG.setColor(Color.red);
                relOG.drawRect(0, 0, width, height);
                growTimer.tick();
            } else {
                relOG.setColor(Color.white);
                relOG.drawRect(0, 0, width, height);
            }
            relOG.setFont(new Font("Arial", Font.BOLD, 20));
            relOG.drawString("" + number, width / 2 - 4, height / 2);
            if (hasGun()) {
                PrinceAngle pa = duckDir.getCopy();
                if (!right) {
                    pa.mirrorY();
                }
                ColorPack cpind;
                if (hasAmmo()) {
                    cpind = new ColorPack(Color.green, Color.black);
                } else {
                    cpind = new ColorPack(Color.red, Color.black);
                }
                drawIndicator(og, 14, pa, (int) getX() + width / 2, yshoot, 10, cpind);
            }
        }

        public void grow() {
            int hc = 6;
            ((Movement1D) mov).incBase(.035f);
            height += hc;
            subY(hc);
            growTimer.reset();
            hitbox = new Box((int) getX(), (int) getY(), width, height, game);
        }

        public void reset() {
            ((Movement1D) mov).setBase(new Vector(spd0));
            footTimer = new TickTimer(18, 0);
            height = 50;
            setY(500 - 50);
        }

        public void kick() {
            foot.reposition(right);
            footTimer.reset();
            foot.putSelfInHandler(handler);
        }

        public void ult() {
            if (height >= 50 + 12 * 7) {
                new StraightBullet(new Vector2D(5, 0), (int) getX(), 470, game).putSelfInHandler(handler);
                new StraightBullet(new Vector2D(-5, 0), (int) getX(), 470, game).putSelfInHandler(handler);
                reset();
            }
        }

        public boolean canShoot() {
            return hasGun() && hasAmmo();
        }

        public boolean hasGun() {
            return getY() < yshoot - 75;
        }

        public boolean hasAmmo() {
            return shootTimer.tick();
        }

        public void shoot() {
            PrinceAngle pa = duckDir.getCopy();
            if (!right) {
                pa.mirrorY();
            }
            GravBullet gb = new GravBullet(new Vector2D(14, pa),
                    (int) getX() + width / 2, yshoot, game).putSelfInHandler(handler);
            shootTimer.reset();
        }

        public void pushed(boolean right) {
            if (right) {
                addX(6);
            } else {
                addX(-6);
            }
        }

        public void setDir(boolean r) {
            right = r;
        }

        @Override
        public HandlerGT<AbstractGameThing> getInnerHandler() {
            return handlerInternal;
        }
    }

    private class Foot extends AbstractWidget<Foot> {

        private Anish anish;
        private int w;
        private boolean right;

        public Foot(Anish a, int x, int y, int w, int h, Game game) {
            super(x, y, w, h, game);
            anish = a;
            this.w = w;
        }

        public void reposition(boolean r) {
            right = r;
            setY(anish.getY() + anish.getHeight() - 32);
            if (right) {
                setX(anish.getX() + width - 12);
            } else {
                setX(anish.getX() - w + 12);
            }
        }

        @Override
        public void render(OGraphics og) {
            if (right) {
                og.drawImage(Sprites.getImage("Anish_LegR"), (int) getX(), (int) getY());
            } else {
                og.drawImage(Sprites.getImage("Anish_LegL"), (int) getX(), (int) getY());
            }
        }

        public Anish getAnish() {
            return anish;
        }

        public boolean facesRight() {
            return right;
        }
    }

    private class NPC extends AbstractWidget<NPC> {

        public static final int height = 100, width = 50;

        public NPC(int x, boolean r, Game game) {
            super(x, 500 - height, width, height, "MachoMan", game);
            if (r) {
                vel = new Vector(1);
            } else {
                vel = new Vector(-1);
            }
        }
    }

    private class ResetButton extends AbstractButton {

        Anish target;

        public ResetButton(Anish tm, int x, int y, int w, int h, ColorPack cp, Game game) {
            super("upgrade?", x, y, w, h, cp, game);
            target = tm;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //target.upgrade(); or some other method
        }
    }

}

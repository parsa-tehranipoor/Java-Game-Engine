/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.segment;

import TomHopper.Game;
import TomHopper.collision.AbstractCollisionDetection;
import TomHopper.general.*;
import TomHopper.utility.*;
import TomHopper.gui.*;
import TomHopper.gui.bandm.*;
import TomHopper.input.*;
import TomHopper.movement.AbstractGameObject;
import TomHopper.structure.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author cdwan
 */
public class SegmentDemoTemplate extends AbstractSegmentPlay {

    //data fields here
    private int totalToms;

    public SegmentDemoTemplate(Game game) {
        super(game);
        keyBinds = new KeyBinds();
        collisions = new Collisions();
        totalToms = 0;
        //initialize data fields and construct objects for game (generally auto-added)
        new Message("get toms and upgrade for more!", 270, 40, 200, 50, game).putSelfInHandler(handlerGT);
        new Message("and there's fast mode (key F)!", 530, 30, 200, 25, game).putSelfInHandler(handlerGT);
        new Message("R for new game", 50, 50, 120, 25, game).putSelfInHandler(handlerGT);

        //updating messages!
        new MessageDynamic(() -> "toms gotten: " + totalToms, 300, 540, 150, 40, game).putSelfInHandler(handlerGT);
        new MessageDynamic(this::getDummy, 600, 545, 50, 30, game).putSelfInHandler(handlerGT);
    }

    public String getDummy() {
        return "tom";
    }

    //make extra methods
    @Override
    public void tick() {
        super.tick();
        //add some tick stuff if needed
        ++totalToms;
    }

    @Override
    public void render(OGraphics og) {
        Game.resetDraw(og);
        //render general stuff here

        
        super.render(og);
    }

    //add keybinds in this class
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
    
    private class Collisions extends AbstractCollisionDetection {

        public Collisions() {
            super(handlerGT, game);
        }

        @Override
        public void ifCollision(AbstractGameObject object1, AbstractGameObject object2) {
            
        }

    }

    //add some extra classes in for game objects
    private class TomMaker extends AbstractWidget implements Container {

        //data fields. w classes in classes, watch out for repeat field 
        //names overriding, eg w handler
        private String name;
        private TickTimer timer;
        private int interval;
        private int numToms;
        private int tomIncrement;
        private int upgradeIncrement;
        private boolean overdrive;
        private final int overdriveFactor = 5;
        private HandlerGT handlerInternal;

        public TomMaker(String name, int x, int y, Game game) {
            this(name, 100, 1, x, y, game);
        }

        public TomMaker(String name, int period, int increment, int x, int y, Game game) {
            super(x, y, 330, 130, ColorPack.WonB, game);
            this.name = name;
            this.timer = new TickTimer(period);
            this.interval = period;
            this.numToms = 0;
            this.tomIncrement = increment;
            this.upgradeIncrement = period / 20 + 1;
            this.overdrive = false;
            handlerInternal = new HandlerGT();
            new UpgradeButton(this, x + 200, y + 10, 70, 40, ColorPack.WonB, game)
                    .putSelfInHandler(handlerInternal);
        }

        @Override
        public void tick() {
            super.tick();
            //stuff here
        }

        //specific methods also
        @Override
        public void render(OGraphics og) {
            super.render(og);
            OGraphics relOG = getRelRenOG(og);
            //the above gets graphics relative to x, y pos
            //rendering for the object
        }

        @Override
        public HandlerGT<AbstractGameThing> getInnerHandler() {
            return handlerInternal;
        }
    }

    //make custom buttons
    private class UpgradeButton extends AbstractButton {

        TomMaker target;

        public UpgradeButton(TomMaker tm, int x, int y, int w, int h, ColorPack cp, Game game) {
            super("upgrade?", x, y, w, h, cp, game);
            target = tm;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            //target.upgrade(); or some other method
        }
    }

}

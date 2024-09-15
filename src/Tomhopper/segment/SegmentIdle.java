/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.segment;

import TomHopper.Game;
import TomHopper.general.Container;
import TomHopper.utility.TickTimer;
import TomHopper.gui.*;
import TomHopper.general.HandlerGT;
import TomHopper.gui.bandm.*;
import TomHopper.input.*;
import TomHopper.art.Sprites;
import TomHopper.structure.AbstractSegmentPlay;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Demo idle game to show game "engine" structure capabilities and ease of use
 * not tryna comment header all of it tho...
 *
 * @author cdwan
 */
public class SegmentIdle extends AbstractSegmentPlay {

    private HandlerGT<TomMaker> handlerTM;
    private int totalToms = 0;
    private boolean fastMode = false;
    private static final BufferedImage background = Sprites.getImage("Idle_Background");

    public SegmentIdle(Game game) {
        super(game);
        keyBinds = new KeyBinds();
        handlerTM = new HandlerGT();
        new TomMaker("hunter", 100, 1, 60, 120, game).putSelfInHandler(handlerTM);
        new TomMaker("gatherer", 200, 5, 60, 260, game).putSelfInHandler(handlerTM);
        new TomMaker("farmer", 400, 10, 60, 400, game).putSelfInHandler(handlerTM);
        new TomMaker("trader", 800, 25, 450, 120, game).putSelfInHandler(handlerTM);
        new TomMaker("factory", 1500, 50, 450, 260, game).putSelfInHandler(handlerTM);
        new TomMaker("bank", 2500, 80, 450, 400, game).putSelfInHandler(handlerTM);
        new Message("get toms and upgrade for more!", 270, 40, 200, 50, game).putSelfInHandler(handlerGT);
        new Message("and there's fast mode (key F)!", 530, 30, 200, 25, game).putSelfInHandler(handlerGT);
        new Message("R for new game", 50, 50, 120, 25, game).putSelfInHandler(handlerGT);

        //updating messages!
        new MessageDynamic(() -> "toms gotten: " + totalToms, 300, 540, 150, 40, game).putSelfInHandler(handlerGT);
        new MessageDynamic(this::getDummy, 600, 545, 50, 30, game).putSelfInHandler(handlerGT);
    }

    public String getDummy() {
        return "tom!";
    }

    @Override
    public void tick() {
        super.tick();
        handlerTM.tick();
    }

    @Override
    public void render(OGraphics og) {
        Game.resetDraw(og);
        og.drawImage(background, 0, 0);
        og.drawString("fastMode: " + fastMode, 600, 15);
        handlerTM.render(og);
        super.render(og);
    }

    public void setFastMode(boolean b) {
        fastMode = b;
        for (TomMaker tm : handlerTM) {
            tm.setOverdrive(fastMode);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        handlerTM.mousePressed(e);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        handlerTM.mouseReleased(e);
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
                setFastMode(!fastMode);
                return;
            }
        }
        
    }

    private class TomMaker extends AbstractWidget implements Container {

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
            super(x, y, 330, 130, "Tom_Maker", game);
            this.name = name;
            this.timer = new TickTimer(period);
            this.interval = period;
            this.numToms = 0;
            this.tomIncrement = increment;
            this.upgradeIncrement = period / 20 + 1;
            this.overdrive = false;
            handlerInternal = new HandlerGT();
            new UpgradeButton(this, 200, 10, 70, 40, ColorPack.WonB, game).putSelfInHandler(handlerInternal);
            new OverdriveButton(this, 120, 10, 70, 40, ColorPack.WonB, game).putSelfInHandler(handlerInternal);
        }

        @Override
        public void tick() {
            super.tick();
            if (timer.interact()) {
                numToms += tomIncrement;
                totalToms += tomIncrement;
            }
            if (overdrive) {
                for (int i = 0; i < overdriveFactor - 1; ++i) {
                    if (timer.interact()) {
                        numToms += tomIncrement;
                        totalToms += tomIncrement;
                    }
                }
            }
            if (overdrive) {
                interval = timer.length() / 5;
            } else {
                interval = timer.length();
            }
        }

        public void upgrade() {
            if (timer.length() > 10 + upgradeIncrement) {
                timer.setLength(timer.length() - upgradeIncrement);
            } else {
                timer.setLength(5);
            }
        }

        public boolean isOverdrived() {
            return overdrive;
        }

        public void setOverdrive(boolean b) {
            overdrive = b;
        }

        public void switchOverdrive() {
            overdrive = !overdrive;
        }

        @Override
        public void render(OGraphics og) {
            super.render(og);
            OGraphics relOG = getRelRenOG(og);
            relOG.setColor(Color.black);
            relOG.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
            relOG.drawString(name + "!", 25, 40);
            relOG.drawString(numToms + "", 230, 85);
            relOG.drawString("gotten!", 265, 85);
            
            Color barColor = Color.blue;
            if (overdrive) {
                barColor = Color.red;
            }
            Bar.drawBar(25, 60, 200, 40, timer.checkPassed(), timer.length(), relOG, 
                    Color.darkGray, barColor, Color.white);
            
            relOG.setColor(Color.white);
            relOG.drawString(tomIncrement + " tom every " + interval, 75, 85); //75 120
        }

        @Override
        public HandlerGT getInnerHandler() {
            return handlerInternal;
        }
    }
    
    private class UpgradeButton extends AbstractButton<UpgradeButton> {

        TomMaker target;

        public UpgradeButton(TomMaker tm, int x, int y, int w, int h, ColorPack cp, Game game) {
            super("upgrade?", x, y, w, h, cp, game);
            target = tm;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            target.upgrade();
        }
    }

    private class OverdriveButton extends AbstractButton<OverdriveButton> {

        TomMaker target;

        public OverdriveButton(TomMaker tm, int x, int y, int w, int h, ColorPack cp, Game game) {
            super("overdrive!", x, y, w, h, cp, game);
            target = tm;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (!fastMode) {
                target.switchOverdrive();
            }
        }
    }
}

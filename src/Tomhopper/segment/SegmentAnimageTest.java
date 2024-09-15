/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.segment;

import TomHopper.Game;
import TomHopper.art.AnimatedImage;
import TomHopper.art.AnimatedImage.AnimatedImageBuilder;
import TomHopper.art.Sprites;
import TomHopper.collision.AbstractCollisionDetection;
import TomHopper.general.*;
import TomHopper.utility.*;
import TomHopper.gui.*;
import TomHopper.gui.bandm.*;
import TomHopper.input.*;
import TomHopper.movement.AbstractGameObject;
import TomHopper.structure.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author cdwan
 */
public class SegmentAnimageTest extends AbstractSegmentPlay {

    public AnimatedImage ai;
    
    public SegmentAnimageTest(Game game) {
        super(game);
        keyBinds = new KeyBinds();
        collisions = new Collisions();
        ai = new AnimatedImageBuilder().addImage(Sprites.getImage("DUCKL"), 100)
                .addImage(Sprites.getImage("MachoMan"), 100).addImage(Sprites.getImage("Skull2"), 200).build();
    }
    
    @Override
    public void render(OGraphics og) {
        og.drawImage(ai.getImage(), 100, 100);
    }

    private class KeyBinds extends AbstractKeyControl {

        public KeyBinds() {
            super(game);
        }

        @Override
        public void keyPressedSetUp() {
            
        }

        @Override
        protected void keyPressed(String keyAgent) {
            
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

    private class Button extends AbstractButton {

        public Button(int x, int y, int w, int h, ColorPack cp, Game game) {
            super("upgrade?", x, y, w, h, cp, game);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }
    }

}

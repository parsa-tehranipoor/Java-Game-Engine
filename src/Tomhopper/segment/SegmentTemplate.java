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
public class SegmentTemplate extends AbstractSegmentPlay {

    public SegmentTemplate(Game game) {
        super(game);
        keyBinds = new KeyBinds();
        collisions = new Collisions();
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

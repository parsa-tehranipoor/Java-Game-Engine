/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.structure;

import TomHopper.Game;
import TomHopper.collision.AbstractCollisionDetection;
import TomHopper.general.Renderable;
import TomHopper.general.Tickable;
import TomHopper.general.HandlerGT;
import TomHopper.gui.OGraphics;
import TomHopper.input.AbstractKeyControl;
import java.awt.event.MouseEvent;

/**
 * This class is the overarching class for any GameFrame that frames/structures
 * the play. Its purpose is to provide these classes with access to the handlers
 * for buttons and messages.
 *
 * @author cdwan
 */
public abstract class AbstractGameFrame implements Tickable, Renderable {

    // Data field for the Game
    protected Game game;
    // Data field for the Handler
    protected HandlerGT handlerGT;
    // Data field for the key control
    protected AbstractKeyControl keyBinds;
    // Data field for collision detection
    protected AbstractCollisionDetection collisions;

    /**
     * Creates an AbstractGameFrame object.
     * 
     * @param game Given Game
     */
    public AbstractGameFrame(Game game) {
        handlerGT = new HandlerGT();
        this.game = game;
    }

    /**
     * When a mouse is pressed, it checks all game things in its Handler to see
     * if the mouse press affects any game things.
     * 
     * @param e Given MouseEvent
     */
    public void mousePressed(MouseEvent e) {
        handlerGT.mousePressed(e);
    }
    
    /**
     * When a mouse is released, it checks all game things in its Handler to see
     * if the mouse release affects any game things.
     * 
     * @param e Given MouseEvent
     */
    public void mouseReleased(MouseEvent e) {
        handlerGT.mouseReleased(e);
    }
    
    /**
     * Ticks all keys stored in the key control.
     */
    public void doKeyActions() {
        if (keyBinds != null) {
            keyBinds.tick();
        }
    }

    /**
     * Returns HandlerGT.
     *
     * @return HandlerGT
     */
    public HandlerGT getHandlerGT() {
        return handlerGT;
    }

    /**
     * Tick method that ticks the key control, the collision detection, and the 
     * Handler.
     */
    @Override
    public void tick() {
        doKeyActions();
        if (collisions != null) {
            collisions.tick();
        }
        handlerGT.tick();
    }

    /**
     * Renders the objects in Handler with the given graphics.
     *
     * @param og Given OGraphics
     */
    @Override
    public void render(OGraphics og) {
        handlerGT.render(og);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.gui.bandm;

import TomHopper.Game;
import TomHopper.general.AbstractGameThing;
import TomHopper.general.Clickable;
import TomHopper.general.Container;
import TomHopper.general.HandlerGT;
import TomHopper.gui.ColorPack;
import java.awt.event.MouseEvent;

/**
 * A Message object that when clicked, shows its content which is stored in
 * an inner Handler. Its effectively a Button-like Container. It also creates
 * a new message within the Handler which could allow users to potentially move
 * out of the Container.
 * 
 * @author cdwan
 */
public class MessageContent extends Message implements Clickable, Container {
    
    // Boolean value for whether or not content should be shown
    private boolean showContent = false;
    // String message of new Message in Handler
    private Message content;
    // Inner Handler
    private HandlerGT handlerInternal;

    /**
     * Constructs a MessageContent object with default dimensions and position
     * of new Message.
     * 
     * @param front String message
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param cp ColorPack
     * @param game Game
     * @param inside String message of new Message in Handler
     */
    public MessageContent(String front, int x, int y, int w, int h,
            ColorPack cp, Game game, String inside) {
        this(front, x, y, w, h, cp, game, inside, 50, 20, 80, 40, cp);
    }

    /**
     * Constructs a MessageContent object in full.
     * 
     * @param front String Message
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param cp ColorPack
     * @param game Game
     * @param inside String Message of new Message in internal Handler
     * @param rx X coordinate of new Message
     * @param ry Y coordinate of new Message
     * @param iw Width of new Message
     * @param ih Height of new Message
     * @param icp ColorPack of new Message
     */
    public MessageContent(String front, int x, int y, int w, int h,
            ColorPack cp, Game game, String inside, int rx, int ry, int iw, 
            int ih, ColorPack icp) {
        super(front, x, y, w, h, cp, game);
        handlerInternal = new HandlerGT();
        content = new Message<>(inside, rx, ry, iw, ih, icp, game).putSelfInHandler(handlerInternal);
    }

    /**
     * Shows content based on current content shown.
     * 
     * @param e Given MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        showContent = !showContent;
    }

    /**
     * Empty method.
     * 
     * @param e Given MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        //nothing
    }

    /**
     * Returns the internal Handler.
     * 
     * @return Inner Handler
     */
    @Override
    public HandlerGT<AbstractGameThing> getInnerHandler() {
        if (showContent) {
            return handlerInternal;
        }
        return null;
    }
}

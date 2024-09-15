/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.gui.bandm;

import TomHopper.Game;
import TomHopper.gui.AbstractWidget;
import TomHopper.gui.ColorPack;
import TomHopper.gui.OGraphics;

/**
 * Class for creating messages. Has the size, location, and ColorPack used for 
 * the specified message. Only static: MessageDynamic changes based on a
 * specified method.
 *
 * @author cdwan
 * @param <T>
 */
public class Message<T extends Message> extends AbstractWidget<T> {

    // Message to be shown
    protected String text;

    /**
     * Creates a Message Object. Constructor for the location and size of the
     * message but with empty message and default ColorPack
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param game Game
     */
    public Message(int x, int y, int w, int h, Game game) {
        super(x, y, w, h, game);
    }

    /**
     * Creates a Message Object. Constructor which fully creates a Message 
     * Object
     *
     * @param m String message
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param cp ColorPack
     * @param game Game
     */
    public Message(String m, int x, int y, int w, int h, ColorPack cp, Game game) {
        super(x, y, w, h, cp, game);
        text = m;
    }

    /**
     * Creates a Message Object. Constructor for the location, size, and message.
     * Also uses default ColorPack.
     *
     * @param m String message
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param game Game
     */
    public Message(String m, int x, int y, int w, int h, Game game) {
        super(x, y, w, h, game);
        text = m;
    }

    /**
     * Returns the text of the Message Object.
     *
     * @return String of the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text to the given String.
     *
     * @param m Given String
     */
    public void setMessage(String m) {
        text = m;
    }
    
    /**
     * Renders the Message.
     * 
     * @param og Given Graphics
     */
    @Override
    public void render(OGraphics og) {
        render(text, og);
    }
    
}

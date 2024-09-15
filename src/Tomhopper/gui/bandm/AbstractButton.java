/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.gui.bandm;

import TomHopper.Game;
import TomHopper.general.Clickable;
import TomHopper.gui.ColorPack;
import TomHopper.gui.OGraphics;
import java.awt.event.MouseEvent;

/**
 * Class for creating AbstractButton Objects.
 *
 * @author cdwan
 * @param <T>
 */
public abstract class AbstractButton<T extends AbstractButton> extends Message<T> implements Clickable {

    /**
     * Creates an AbstractButton.Completely constructs the Button.
     *
     * @param s Message
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param cp ColorPack
     * @param game Game
     */
    public AbstractButton(String s, int x, int y, int w, int h, ColorPack cp, Game game) {
        super(s, x, y, w, h, cp, game);
    }

    /**
     * Creates an AbstractButton. Constructs the location and size of the button
     * using default ColorPack.
     *
     * @param s Message
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param game Game
     */
    public AbstractButton(String s, int x, int y, int w, int h, Game game) {
        super(s, x, y, w, h, game);
    }

    /**
     * Checks if the spot clicked is on the location of this button (if it would
     * be pressed).
     *
     * @param e Given MouseEvent
     * @return Boolean for whether the location clicked is the button
     */
    @Override
    public boolean wouldBeHit(MouseEvent e) {
        int rx = getRelX(e), ry = getRelY(e);
        return rx >= 0 && rx < width && ry > 0 && ry < height;
    }

    /**
     * Abstract method for a mousePressed method, aka what the button should do
     * when pressed.
     *
     * @param e Given MouseEvent
     */
    @Override
    public abstract void mousePressed(MouseEvent e);

    /**
     * Default method for a mouseReleased method, aka what the button should do
     * when pressed.
     *
     * @param e Given MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    
    @Override
    /**
     * Default render method for rendering the Button.
     */
    public void render(OGraphics og) {
        render(text, og);
    }
    
}

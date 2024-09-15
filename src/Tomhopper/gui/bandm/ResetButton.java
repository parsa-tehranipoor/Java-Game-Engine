/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.gui.bandm;

import TomHopper.Game;
import TomHopper.general.Resettable;
import TomHopper.gui.ColorPack;
import java.awt.event.MouseEvent;

/**
 * An extension of the AbstractButton class, ResetButton is a Button that resets
 * data from a given target/object when clicked.
 * 
 * @author cdwan
 */
public class ResetButton extends AbstractButton {
    
    // Resettable object
    Resettable target;

    /**
     * Constructs a ResetButton Object.
     * 
     * @param target Resettable Object
     * @param s Message on Button
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param cp ColorPack
     * @param game Game
     */
    public ResetButton(Resettable target, String s, int x, int y, int w, int h, ColorPack cp, Game game) {
        super(s, x, y, w, h, cp, game);
        this.target = target;
    }
    
    /**
     * Constructs a ResetButton Object with default ColorPack
     * 
     * @param target Resettable Object
     * @param s Message on Button
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param game Game
     */
    public ResetButton(Resettable target, String s, int x, int y, int w, int h, Game game) {
        super(s, x, y, w, h, game);
        this.target = target;
    }

    /**
     * Resets the target when clicked on.
     * 
     * @param e Given MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        target.reset();
    }
    
}

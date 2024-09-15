/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.gui.bandm;

import TomHopper.Game;
import TomHopper.gui.ColorPack;
import TomHopper.gui.OGraphics;
import java.awt.event.MouseEvent;

/**
 * Test button to show how a button works!
 *
 * @author cdwan
 */
public class TestButton extends AbstractButton {

    /**
     * Constructs a TestButton
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param cp ColorPack
     * @param game Game
     */
    public TestButton(int x, int y, int w, int h, ColorPack cp, Game game) {
        super("test", x, y, w, h, cp, game);
    }

    /**
     * Prints its location and a message when clicked on.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.printf("test! + %d, %d\n", getRelX(e), getRelY(e));
    }

    /**
     * Renders the object.
     * 
     * @param og Given Graphics
     */
    @Override
    public void render(OGraphics og) {
        super.render("test button!", og);
    }

}

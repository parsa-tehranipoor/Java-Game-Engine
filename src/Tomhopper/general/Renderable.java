/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TomHopper.general;

import TomHopper.gui.OGraphics;

/**
 * An interface which provides the render method. The render method allows an 
 * object to "render" an image of its liking onto the actual screen of the game.
 *
 * @author cdwan
 */
public interface Renderable {

    /**
     * Renders the Graphics given. This method is called continuously over and
     * over for a game.
     *
     * @param og Given Graphics
     */
    public void render(OGraphics og);

}

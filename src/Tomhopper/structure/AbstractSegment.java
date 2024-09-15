/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.structure;

import TomHopper.Game;
import TomHopper.general.Renderable;
import TomHopper.general.Tickable;
import TomHopper.gui.OGraphics;

/**
 * A class for a small component "room"/segment of the Game.
 *
 * @author cdwan
 */
public abstract class AbstractSegment extends AbstractGameFrame implements Tickable, Renderable {

    /**
     * Creates an AbstractSegment object
     * 
     * @param game Given Game
     */
    public AbstractSegment(Game game) {
        super(game);
    }
    
    /**
     * Renders the this Segment each tick.
     * 
     * @param og Given Graphics
     */
    @Override
    public void render(OGraphics og) {
        super.render(og);
        Game.resetDraw(og);
        og.drawString("segment: " + getClass().getSimpleName().substring(7), 300, 15);
    }
}

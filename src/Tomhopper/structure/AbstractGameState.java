/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package TomHopper.structure;

import TomHopper.Game;
import TomHopper.general.Renderable;
import TomHopper.general.Tickable;
import TomHopper.gui.OGraphics;
import TomHopper.segment.SegmentDefault;

/**
 * A class for describing a Game State of the Game.
 *
 * @author cdwan
 */
public abstract class AbstractGameState extends AbstractGameFrame implements Tickable, Renderable {

    // Data field for the current segment being loaded/seen on screen
    protected AbstractSegment curSeg;

    /**
     * Creates an AbstractGameState object.
     *
     * @param game Given Game
     */
    public AbstractGameState(Game game) {
        super(game);
        curSeg = new SegmentDefault(game);
    }

    /**
     * A tick method for what the current segment is supposed to do every unit
     * of time.
     */
    @Override
    public void tick() {
        //general
        super.tick();
        //specific segment
        curSeg.tick();
    }

    /**
     * A render method for rendering in the current segment each tick.
     */
    @Override
    public void render(OGraphics og) {
        curSeg.render(og);

        super.render(og);
        Game.resetDraw(og);
        og.drawString("gamestate: " + getClass().getName(), 10, 15);
    }

    /**
     * Runs the segment given.
     * 
     * @param seg Given Segment
     */
    public void runSegment(AbstractSegment seg) {
        curSeg = seg;
    }

    /**
     * Gets the current segment.
     *
     * @return AbstractSegment
     */
    public AbstractSegment getCurrentSegment() {
        return curSeg;
    }
}

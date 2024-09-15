/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper;

import TomHopper.map.AbstractMapSpot;
import TomHopper.grid.HexGrid;
import TomHopper.grid.HexLocation;
import TomHopper.gui.AbstractWidget;
import TomHopper.gui.ColorPack;
import TomHopper.gui.OGraphics;
import TomHopper.map.EmptyIsland;
import java.awt.Color;

/**
 * This is the world map from which the player will traverse islands. Generates
 * world map as well as the location of every type of Island.
 *
 * @author cdwan
 */
public class HexWorld extends AbstractWidget<HexWorld>  {

    //some fields for the rendering pixel distances
    public static final int hexDist = 60; //hexagon side length?
    public static final int brd = hexDist / 2; //between row distance
    public static final int ird = (int) (hexDist * Math.sqrt(3)); //in row distance

    // Hexgrid of the spots of which islands will be located
    private HexGrid<AbstractMapSpot> hexMap;

    /**
     * Creates the HexWorld object with default size of HexGrid
     *
     * @param game game
     */
    public HexWorld(Game game) {
        this(15, 6, game);
    }

    /**
     * Creates the HexWorld Object with given size of HexGrid
     *
     * @param r number of rows
     * @param c number of columns
     * @param game
     */
    public HexWorld(int r, int c, Game game) {
        super(100, 70, c * ird, (r + 1) * brd, ColorPack.WonB, game);
        this.hexMap = new HexGrid(r, c);
        //new HexMapGenerator(hexMap).generateHexGrid();
    }

    @Override
    /**
     * renders the HexGrid onto the screen
     *
     * @param og Graphics
     */
    public void render(OGraphics og) {
        super.render(og);
        OGraphics relOG = getRelRenOG(og);
        for (int r = 0; r < hexMap.getNumRows(); ++r) {
            for (int c = 0; c < hexMap.colsInRow(r); ++c) {
                HexLocation current = new HexLocation(r, c);
                if (hexMap.get(current) != null) {
                    int ix = getIX(current); //pixel cord positions
                    int iy = getIY(current);
                    int ox = -15; //offsets of rendering
                    int oy = -15;
                    OGraphics islandOG = relOG.create(ix + ox, iy + oy);
                    AbstractMapSpot spot = hexMap.get(current);
                    spot.render(islandOG);
                    if (spot instanceof EmptyIsland) {
                        EmptyIsland ei = (EmptyIsland) spot;
                        boolean[] bridges = ei.getBridges();
                        for (int i = 0; i <= 2; ++i) {
                            if (bridges[i]) {
                                HexLocation other = current.getAdjacentHexLocation(
                                        HexLocation.UPRIGHT + HexLocation.CCWONE * i);
                                int oix = getIX(other);
                                int oiy = getIY(other);
                                relOG.setColor(Color.red);
                                relOG.drawLine(ix, iy, oix, oiy);
                            }
                        }
                    }
                } else {
                    relOG.setColor(Color.white);
                    relOG.fillRect(getIX(r, c) - 15, getIY(r) - 15, 30, 30);
                }
            }
        }

    }

    public static int getIX(HexLocation loc) {
        return getIX(loc.getRow(), loc.getCol());
    }

    public static int getIY(HexLocation loc) {
        return getIY(loc.getRow());
    }

    public static int getIX(int r, int c) {
        return 50 + c * ird + (ird / 2) * ((r + 1) % 2);
    }

    public static int getIY(int r) {
        return 30 + r * brd;
    }

    @Override
    /**
     * tick method for hexWorld
     */
    public void tick() {
        super.tick();
        //empty
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.gui;

import java.awt.Color;

/**
 * A Class which packs together the color of the word and the color of the
 * background for messages and potentially other objects. Gives 3 default
 * ColorPacks and access methods
 *
 * @author cdwan
 */
public class ColorPack { //imutable
    
    // Default Color Packs
    public static ColorPack BonW = new ColorPack(Color.black, Color.white);
    public static ColorPack WonB = new ColorPack(Color.white, Color.black);
    public static ColorPack WonG = new ColorPack(Color.white, Color.gray);

    // Color of the top stuff, eg words
    private final Color topColor;
    // Color of the background
    private final Color backColor;

    /**
     * Creates a ColorPack with given top and back color.
     *
     * @param w Color of top
     * @param b Color of background
     */
    public ColorPack(Color w, Color b) {
        topColor = w;
        backColor = b;
    }

    /**
     * Returns the top color.
     *
     * @return Color of the word
     */
    public Color getTopColor() {
        return topColor;
    }

    /**
     * Returns the back color.
     *
     * @return Color of the background
     */
    public Color getBackColor() {
        return backColor;
    }
    
    /**
     * Checks whether this ColorPack is equal to another given ColorPack.
     * 
     * @param cp Given ColorPack
     * @return True or False based on equality
     */
    public boolean equals(ColorPack cp) {
        return cp.topColor.equals(topColor) && cp.backColor.equals(backColor);
    }
}

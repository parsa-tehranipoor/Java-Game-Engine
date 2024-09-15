/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.gui;

import TomHopper.utility.PrinceAngle;

/**
 * This is a class that provides methods for creating an Indicator. An Indicator
 * is a small box that indicates the current direction it is facing relative to
 * wherever the user desires. Its a great feature for observing rotation and 
 * direction of particular data from an object.
 * 
 * @author cdwan
 */
public class IndicatorDirection {
    
    /**
     * Draws an Indicator.
     * 
     * @param og Given Graphics
     * @param r Radius/Distance from a given center
     * @param pa Angle
     * @param x X coordinate of the center of rotation
     * @param y Y coordinate of the center of rotation
     * @param size Side length of the Box representing the Indicator
     */
    public static void drawIndicator(OGraphics og, int r, PrinceAngle pa, int x, int y, int size) {
        AbstractWidget.renderBox(og, x - size / 2 + (int) (r * Math.cos(pa.getInRad())),
                    y - size / 2 + (int) (r * Math.sin(pa.getInRad())), size, size, ColorPack.BonW);
    }
    
    /**
     * Draws an Indicator with a given ColorPack.
     * 
     * @param og Given Graphics
     * @param r Radius/Distance from a given center
     * @param pa Angle
     * @param x X coordinate of the center of rotation
     * @param y Y coordinate of the center of rotation
     * @param size Side length of the Box representing the Indicator
     * @param cp Given ColorPack
     */
    public static void drawIndicator(OGraphics og, int r, PrinceAngle pa, int x, int y, int size, ColorPack cp) {
        AbstractWidget.renderBox(og, x - size / 2 + (int) (r * Math.cos(pa.getInRad())),
                    y - size / 2 + (int) (r * Math.sin(pa.getInRad())), size, size, cp);
    }
    
}

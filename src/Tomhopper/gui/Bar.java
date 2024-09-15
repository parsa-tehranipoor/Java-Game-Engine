/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TomHopper.gui;

import TomHopper.Game;
import java.awt.Color;
import java.awt.Graphics;

/**
 * An object that has a maximum length and a current length and supports methods
 * for adjusting the Bars current and maximum length.
 * 
 * @author ptehr
 */
public class Bar extends AbstractWidget<Bar> {
    // Current length of the Bar
    private int currLength;
    // Maximumm length of the Bar
    private int maxLength;

    /**
     * Constructs a Bar object.
     * 
     * @param curr Current Length
     * @param max Maximum Length
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param game Game
     */
    public Bar(int curr, int max, int x, int y, int w, int h, Game game) {
        super(x, y, w, h, game);
        currLength = curr;
        maxLength = max;
    }
    
    /**
     * Constructs a Bar object with initial length as the maximum.
     * 
     * @param max Maximum Length
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param game Game
     */
    public Bar(int max, int x, int y, int w, int h, Game game) {
        this(max, max, x, y, w, h, game);
    }
    
    /**
     * Increases the current length of the Bar by a set amount.
     * 
     * @param inc Amount length will be increased by
     * @return Whether or not the current length is greater than the maximum length
     */
    public boolean increase(int inc) {
        currLength = currLength + inc;
        return currLength > maxLength;
    }

    /**
     * Decreases the current length of the Bar by a set amount.
     * 
     * @param dec Amount length will be decreased by
     * @return Whether or not the current length is less than 0
     */
    public boolean decrease(int dec) {
        currLength = currLength - dec;
        return currLength <= 0;
    }
    
    /**
     * Sets the current length of the Bar to a given length.
     * 
     * @param num New length
     */
    public void setLength(int num) {
        currLength = num;
    }
    
    /**
     * Increases the maximum length of the bar by a given amount.
     * 
     * @param inc Amount the maximum will be increased by
     */
    public void increaseMax(int inc) {
        maxLength = maxLength + inc;
    }
    
    /**
     * Decreases the maximum length of the Bar by a given amount.
     * 
     * @param dec Amount the maximum will be decreased by
     */
    public void decreaseMax(int dec) {
        maxLength = maxLength - dec;
    }

    /**
     * Returns the current length of the Bar.
     * 
     * @return Current length
     */
    public int getLength() {
        return currLength;
    }

    /**
     * Renders the Bar with its current length and maximum length.
     * 
     * @param og Given Graphics
     */
    @Override
    public void render(OGraphics og) {
        drawBar((int) getX(), (int) getY(), width, height, currLength, maxLength, 
                og, Color.DARK_GRAY, Color.BLUE, Color.white);
    }
    
    /**
     * Draws a Rectangle within a Rectangle that is wider by a certain amount.
     * 
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param curr Current Length
     * @param max Maximum Length
     * @param og Given Graphics
     * @param back Color of the rest of the Rectangle
     * @param fill Color of the Rectangle below the current Length
     * @param outline Color of the outline of the Rectangle
     */
    public static void drawBar(int x, int y, int w, int h, int curr, int max, 
        OGraphics og, Color back, Color fill, Color outline) {
        og.setColor(back);
        og.fillRect(x, y, w, h);
        og.setColor(fill);
        og.fillRect(x, y, w * curr / max, h);
        og.setColor(outline);
        og.drawRect(x, y, w, h);
    }
}

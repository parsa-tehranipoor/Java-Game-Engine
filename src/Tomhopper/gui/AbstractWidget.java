/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.gui;

import TomHopper.Game;
import TomHopper.collision.Box;
import TomHopper.movement.AbstractGameObject;
import TomHopper.art.Sprites;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * An AbstractWidget is an AbstractGameObject. Additional to being a game object,
 * it is a rectangle that can have an image or writing be projected onto it.
 * 
 * @author cdwan
 * @param <T> for if you (prob not) need to return something declared as
 * something in a subclass
 */
public abstract class AbstractWidget<T extends AbstractWidget> extends AbstractGameObject<T> {

    // Data Fields for the width and height of the Widget
    protected int width, height;
    // Data Field for the ColorPack used
    protected ColorPack cpack;
    // Data Field for the image projected on the AbstractWidget
    protected BufferedImage background;

    public AbstractWidget(int x, int y, int w, int h, String spriteName, Game game) {
        super(x, y, game);
        this.width = w;
        this.height = h;
        hitbox = new Box(x, y, w, h, game);
        background = Sprites.getImage(spriteName);
    }

    /**
     * Creates an AbstractWidget object.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param cp ColorPack
     * @param game Game
     */
    public AbstractWidget(int x, int y, int w, int h, ColorPack cp, Game game) {
        super(x, y, game);
        this.width = w;
        this.height = h;
        this.cpack = cp;
        hitbox = new Box(x, y, w, h, game);
    }

    /**
     * Creates an AbstractWidget object. Constructor for creating structure and
     * location of Widget is just using default ColorPack.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param game Game
     */
    public AbstractWidget(int x, int y, int w, int h, Game game) {
        this(x, y, w, h, ColorPack.WonG, game);
    }

    @Override
    /**
     * Default render for a widget. Renders either a rectangle or the given
     * image.
     */
    public void render(OGraphics og) {
        OGraphics relOG;
        if (renderOffset != null) {
            relOG = getRelObjOG(og);
        } else {
            relOG = getRelRenOG(og);
        }
        if (background != null) {
            relOG.drawImage(background, 0, 0);
        } else if (cpack != null) {
            // Should we not use Render Box method here?
            
            relOG.setColor(cpack.getBackColor());
            relOG.fillRect(0, 0, width, height);
            relOG.setColor(cpack.getTopColor());
            relOG.drawRect(0, 0, width, height);
        }
    }

    /**
     * Renders a base for the Widget with a given message.
     *
     * @param message Given message
     * @param og Given Graphics
     */
    public void render(String message, OGraphics og) {
        Game.resetDraw(og);
        if (background != null) {
            render(og);
            addStringCentered(message, og);
        } else if (cpack != null) {
            OGraphics relOG = getRelRenOG(og);
            renderMessage(relOG, message, 0, 0, width, height, cpack);
        }
    }
    
    /**
     * Renders the box with the given message.
     * 
     * @param og Given Graphics
     * @param text Given Message
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width
     * @param height Height
     * @param cp Given ColorPack
     */
    public static void renderMessage(OGraphics og, String text, int x, int y, int width, int height, ColorPack cp) {
        renderBox(og, x, y, width, height, cp);
        // Creates the message with its word color. No message needed
        if (text != null) {
            og.setColor(cp.getTopColor());
            drawStringCentered(text, x, y, width, height, og);
        }
    }
    
    /**
     * Renders the box with given colors.
     * 
     * @param og Given Graphics
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width
     * @param height Height
     * @param cp Given ColorPack
     */
    public static void renderBox(OGraphics og, int x, int y, int width, int height, ColorPack cp) {
        // Creates the background structure with color
        og.setColor(cp.getBackColor());
        og.fillRect(x, y, width, height);
        // Draws the outline of the message in white
        og.setColor(cp.getTopColor());
        og.drawRect(x, y, width, height);
    }
    
    /**
     * Draws a string of text with a given position and dimensions.
     * 
     * @param text Given String
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width
     * @param height Height
     * @param og Given Graphics
     */
    public static void drawStringCentered(String text, int x, int y,
            int width, int height, OGraphics og) {
        OGraphics relG = Game.getRelOG(og, x, y);
        relG.drawString(text, width / 10, height / 2 + 4);
    }
    
    /**
     * Draws a string of text centered at the objects position with 
     * predetermined dimensions.
     * 
     * @param text Given String
     * @param og Given Graphics
     */
    public void addStringCentered(String text, OGraphics og) {
        OGraphics relG = Game.getRelOG(og, getX(), getY());
        relG.drawString(text, width / 10, height / 2 + 4);
    }
    
    /**
     * Checks if a mouse click was on top of the object.
     * 
     * @param e Given MouseEvent
     * @return True or False based on whether or not the object was clicked
     */
    public boolean wouldBeHit(MouseEvent e) {
        int rx = getRelX(e), ry = getRelY(e);
        return rx >= 0 && rx < width && ry >= 0 && ry < height;
    }

    /**
     * Returns the width of the Widget.
     *
     * @return Width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of the Widget.
     *
     * @return Height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Returns the ColorPack of the Widget.
     *
     * @return ColorPack
     */
    public ColorPack getColor() {
        return cpack;
    }

    /**
     * Sets the width of the Widget.
     *
     * @param w Width
     */
    public void setWidth(int w) {
        width = w;
    }

    /**
     * Sets the height of the Widget.
     *
     * @param h Height
     */
    public void setHeight(int h) {
        height = h;
    }

    /**
     * Sets the ColorPack of the Widget.
     *
     * @param cp ColorPack
     */
    public void setColorPack(ColorPack cp) {
        cpack = cp;
    }

    // This should return a Vector, not int array
    /**
     * Gets the center of the Widget.
     * 
     * @return Center of the Widget.
     */
    public int[] getCenter() {
        return new int[]{(int) getX() + width / 2, (int) getY() + height / 2};
    }
}

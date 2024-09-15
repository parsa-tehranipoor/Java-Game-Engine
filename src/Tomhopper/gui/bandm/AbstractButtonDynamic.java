/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.gui.bandm;

import TomHopper.Game;
import TomHopper.gui.ColorPack;

/**
 * An extension of the AbstractButton class where the message can be changed.
 * 
 * @author cdwan
 */
public abstract class AbstractButtonDynamic extends AbstractButton {
    
    //this gets the message based on the method it has
    public interface MessageGetter {public String getMessage();}
    
    //data field for the used MessageGetter
    MessageGetter mg;
    
    /**
     * Constructs the MessageDynamic with a default ColorPack.
     * 
     * @param mg MessageGetter
     * @param x X coordinate
     * @param y Y Coordinate
     * @param w Width
     * @param h Height
     * @param game Game
     */
    public AbstractButtonDynamic(MessageGetter mg, int x, int y, int w, int h, Game game) {
        super(mg.getMessage(), x, y, w, h, game);
        this.mg = mg;
    }
    
    /**
     * Constructs the MessageDynamic in full.
     * 
     * @param mg MessageGetter
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param cp ColorPack
     * @param game Game
     */
    public AbstractButtonDynamic(MessageGetter mg, int x, int y, int w, int h, ColorPack cp, Game game) {
        super(mg.getMessage(), x, y, w, h, cp, game);
        this.mg = mg;
    }
    
    /**
     * A tick method that acts like an AbstractButton's tick but additionally 
     * changes the message.
     */
    @Override
    public void tick() {
        super.tick();
        setMessage(mg.getMessage());
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.gui.bandm;

import TomHopper.Game;
import TomHopper.gui.ColorPack;

/**
 * Message class where the message auto-updates based on a method to get its text.
 * 
 * @author cdwan
 */
public class MessageDynamic extends Message {
    
    // This gets the message based on the method it has
    public interface MessageGetter {public String getMessage();}
    
    // Data field for the used MessageGetter
    MessageGetter mg;
    
    /**
     * Constructs a MessageDynamic with a default ColorPack.
     * 
     * @param mg MessageGetter
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param game Game
     */
    public MessageDynamic(MessageGetter mg, int x, int y, int w, int h, Game game) {
        super(mg.getMessage(), x, y, w, h, game);
        this.mg = mg;
    }
    
    /**
     * Constructs a MessageDynamic in full.
     * 
     * @param mg MessageGetter
     * @param x X coordinate
     * @param y Y coordinate
     * @param w Width
     * @param h Height
     * @param cp ColorPack
     * @param game Game
     */
    public MessageDynamic(MessageGetter mg, int x, int y, int w, int h, 
            ColorPack cp, Game game) {
        super(mg.getMessage(), x, y, w, h, cp, game);
        this.mg = mg;
    }
    
    /**
     * Tick method that ticks like a normal Message Object but also allows for
     * the message to change.
     */
    @Override
    public void tick() {
        super.tick();
        setMessage(mg.getMessage());
    }
}

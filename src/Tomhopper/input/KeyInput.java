package TomHopper.input;

import TomHopper.Game;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.BitSet;

/**
 * A class for interpreting KeyInput.
 *
 * @author cdwan
 */
public class KeyInput extends KeyAdapter {

    // Game which input is being checked for
    private Game game;
    // Set of keys pressed
    private final BitSet keys = new BitSet();

    /**
     * Constructs a KeyInput object.
     *
     * @param game Given Game
     */
    public KeyInput(Game game) {
        this.game = game;
    }

    /**
     * Occurs when a key is pressed. Adds the key pressed to the set of keys
     * being currently pressed.
     * 
     * @param e Given KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        keys.set(e.getKeyCode());
        System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
    }

    /**
     * Occurs when a key is released. Effectively removes the given key from
     * the set/turns it off.
     * 
     * @param e Given KeyEvent
     */
    @Override
    public void keyReleased(KeyEvent e) {
        keys.clear(e.getKeyCode());
    }
    
    /**
     * Returns the set of keys being pressed down.
     * 
     * @return BitSet of keys pressed
     */
    public BitSet getKeysDown() {
        return keys;
    }
}

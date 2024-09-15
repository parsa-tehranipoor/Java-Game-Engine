/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.input;
import java.util.*;
import TomHopper.Game;
import TomHopper.utility.TickTimer;

/**
 * An abstract class for handling Key input in individual Segments. Allows user
 * to decide what happens when certain keys are pressed and released.
 * 
 * @author cdwan
 */
public abstract class AbstractKeyControl {
    
    // Game where key input is occurring
    private Game game;
    // Map of KeyAgent to TickTimer.
    // Think about it as a Key mapped to certain cooldown for it
    private HashMap<KeyAgent, TickTimer> map;
    
    /**
     * Constructs an AbstractKeyControl object.
     * 
     * @param game Given Game
     */
    public AbstractKeyControl(Game game) {
        map = new HashMap<>();
        this.game = game;
        keyPressedSetUp();
    }
    
    /**
     * Tick method that checks each KeyAgent in the map data field and checks
     * whether the keys down and up stored inside each KeyAgent are actually 
     * being pressed or not. It then calls the keyPressed method for that given
     * KeyAgent.
     */
    public void tick() {
        for (KeyAgent ka: map.keySet()) {
            boolean val = map.get(ka).tick();
            if (val) {
                if (game.keyAgentActive(ka)) {
                    keyPressed(ka.getName());
                    map.get(ka).reset();
                }
            }
        }
    }
    
    /**
     * Sets up the KeyAgents used in the specific Segment.
     */
    protected abstract void keyPressedSetUp();
    
    /**
     * Adds a KeyAgent to the KeyControl.
     * 
     * @param ka Given KeyAgent
     * @param cooldown Cool down for the KeyAgent
     */
    protected void addKeyAgent(KeyAgent ka, int cooldown) {
        map.put(ka, new TickTimer(cooldown, 0));
    }
    
    /**
     * Adds a KeyAgent to the KeyControl with default cool down.
     * 
     * @param ka Given KeyAgent
     */
    protected void addKeyAgent(KeyAgent ka) {
        addKeyAgent(ka, 15);
    }
    
    /**
     * Determines what happens when a certain key is pressed.
     * 
     * @param keyAgent Given KeyAgent pressed. 
     */
    protected abstract void keyPressed(String keyAgent);
}

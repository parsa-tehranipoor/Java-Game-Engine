/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.input;

import TomHopper.Game;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * A KeyAgent is a collection of keys. Its effectively the condition of an if 
 * statement. In AbstractKeyControl, KeyAgents are checked for whether its keys
 * which are stored as pressed or not have been actually pressed or released.
 * It then acts on that information.
 * 
 * @author cdwan
 */
public class KeyAgent {

    // Name of the KeyAgent
    private String name;
    // List of keys that will be checked for if they are down
    private ArrayList<Integer> downs;
    // List of keys that will be checked for if they are up
    private ArrayList<Integer> ups;

    /**
     * Constructs a KeyAgent from a single key.
     * 
     * @param key Given key
     */
    public KeyAgent(int key) {
        downs = new ArrayList();
        downs.add(key);
        name = KeyEvent.getKeyText(key);
        //System.out.println(name);
    }

    /**
     * Constructs a KeyAgent in full.
     * 
     * @param name Name of the KeyAgent
     * @param ds List of keys to be checked for being down
     * @param us List of keys to be checked for being up
     */
    public KeyAgent(String name, ArrayList<Integer> ds, ArrayList<Integer> us) {
        this.name = name;
        downs = ds;
        ups = us;
    }

    /**
     * Gets the name of the KeyAgent.
     * 
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the list of keys to be checked for being down.
     * 
     * @return Keys down
     */
    public ArrayList<Integer> getDowns() {
        return downs;
    }

    /**
     * Gets the list of keys to be checked for being up.
     * 
     * @return Keys up 
     */
    public ArrayList<Integer> getUps() {
        return ups;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.general;

import TomHopper.gui.OGraphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The HandlerGT class literally "handles" all objects that are or are the
 * children of the object AbstractGameThing. Stores every AbstractGameThing in
 * an ArrayList and supports methods such as adding, removing, and clearing the
 * ArrayList data field as well as a tick and render method which are
 * implemented from the interfaces Tickable and Renderable.
 *
 * @author cdwan
 * @param <E>
 */
public class HandlerGT<E extends AbstractGameThing> implements Tickable, Renderable, Iterable<E> {

    // Stores every object added into the Handler
    protected ArrayList<E> objects;
    // Stores the objects which will be added
    protected ArrayList<E> toAdd;
    // Stores the objects which will be removed
    protected ArrayList<E> toRemove;

    /**
     * Creates a HandlerGT Object.
     */
    public HandlerGT() {
        // Sets each parameter to any empty list
        objects = new ArrayList();
        toAdd = new ArrayList();
        toRemove = new ArrayList();
    }

    /**
     * For every "clickable" object in the Handlers data field, it checks if it 
     * was pressed or not and if it was pressed, to do what the button is 
     * supposed to do when pressed.
     *
     * @param e MouseEvent
     */
    public void mousePressed(MouseEvent e) {
        for (AbstractGameThing thing : objects) {
            if (thing instanceof Clickable) {
                Clickable ct = (Clickable)thing;
                if (ct.wouldBeHit(e)) {
                    ct.mousePressed(e);
                }
            }
            if (thing instanceof Container) {
                Container con = (Container)thing;
                HandlerGT hand = con.getInnerHandler();
                if (hand != null) {
                    e.translatePoint(-(int) con.getX(), -(int) con.getY());
                    hand.mousePressed(e);
                    e.translatePoint((int) con.getX(), (int) con.getY());
                }
            }
        }
    }

    /**
     * For every "clickable" object in the Handlers data field, it checks if 
     * it was released or not and if it was released, to do what the button is
     * supposed to do when pressed.
     *
     * @param e MouseEvent
     */
    public void mouseReleased(MouseEvent e) {
        for (AbstractGameThing thing : objects) {
            if (thing instanceof Clickable) {
                Clickable ct = (Clickable) thing;
                if (ct.wouldBeHit(e)) {
                    ct.mouseReleased(e);
                }
            }
            if (thing instanceof Container) {
                Container con = (Container)thing;
                HandlerGT hand = con.getInnerHandler();
                if (hand != null) {
                    e.translatePoint(-(int) con.getX(), -(int) con.getY());
                    hand.mouseReleased(e);
                    e.translatePoint((int) con.getX(), (int) con.getY());
                }
            }
        }
    }

    /**
     * For every tick, it calls the tick method of every object in its ArrayList
     * data field.
     */
    @Override
    public void tick() {
        removeTheRemoves();
        addtheAdds();
        for (E e : objects) {
            e.tick();
            if (e instanceof Container) {
                Container con = (Container)e;
                HandlerGT hand = con.getInnerHandler();
                if (hand != null) {
                    hand.tick();
                }
            }
        }
    }

    /**
     * For every tick, it calls the render method of every object in its
     * ArrayList data field.
     */
    @Override
    public void render(OGraphics og) {
        for (E e : objects) {
            e.render(og);
            if (e instanceof Container) {
                Container con = (Container) e;
                HandlerGT hand = con.getInnerHandler();
                if (hand != null) {
                    hand.render(e.getRelRenOG(og));
                }
            }
        }
    }

    /**
     * Adds an object to the "toAdd" ArrayList data field which will be added
     * later(when the user chooses to do so).
     *
     * @param e Object to be added
     */
    public void add(E e) {
        toAdd.add(e);
    }
    
    /**
     * Adds the objects in the "toAdd" ArrayList data field to the "objects"
     * data field, effectively adding them to the actual handler.
     */
    private void addtheAdds() {
        for (E e : toAdd) {
            objects.add(e);
        }
        toAdd.clear();
    }
    
    /**
     * Adds an object to the "toRemove" ArrayList data field which will be
     * removed later(when the user chooses to do so).
     *
     * @param e Object to be removed
     */
    public void remove(E e) {
        toRemove.add(e);
    }
    
    /**
     * Removes the objects in the "toRemove" ArrayList data field from the
     * "objects" data field, effectively removing them from the actual handler.
     */
    private void removeTheRemoves() {
        for (E e : toRemove) {
            objects.remove(e);
        }
        toRemove.clear();
    }

    /**
     * Clears the "objects" ArrayList data field, effectively clearing the 
     * handler itself.
     */
    public void clear() {
        for (E e : objects) {
            toRemove.add(e);
        }
    }
    
    /**
     * Prints the objects in the "objects" ArrayList data field.
     */
    public void printList() {
        for (E e : objects) {
            System.out.println(e);
        }
    }

    /**
     * Returns the Iterator of the "objects" ArrayList data field.
     */
    @Override
    public Iterator<E> iterator() {
        return objects.iterator();
    }

}

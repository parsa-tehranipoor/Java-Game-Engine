/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TomHopper.general;

/**
 * An interface which provides the tick method.
 *
 * @author cdwan
 */
public interface Tickable {

    /**
     * Performs a set of actions every game tick. This means that this method is
     * continuously called over and over for our game.
     */
    public void tick();

}

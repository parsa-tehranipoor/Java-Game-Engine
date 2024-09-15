/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.input;

import TomHopper.Game;
import TomHopper.general.HandlerGT;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class is meant to be the mother class of mouse input where basically it
 * handles all mouse input when a mouse is pressed or released and forces the 
 * handler for our game to handle when a mouse is pressed or released.
 * 
 * @author cdwan
 */
public class MouseInput extends MouseAdapter {
    
    // Game that input is being checked for
    Game game;
    
    /**
     * Constructs a MouseInput class.
     * 
     * @param game Game
     */
    public MouseInput(Game game) {
        this.game = game;
    }
    
    /**
     * Called automatically when a mouse event is detected(basically whenever a
     * mouse press is detected).
     * 
     * @param e Given MouseEvent
     */
    @Override
    public void mousePressed(MouseEvent e) {
        doButtonsPressed(e);
    }
    
    /**
     * Called automatically when a mouse event is detected(basically whenever a
     * mouse release is detected).
     * 
     * @param e Given MouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        doButtonsReleased(e);
    }
    
    /**
     * Called when a mouse press occurs. Goes through the handler of the game
     * and checks if the mouse press is supposed to trigger some code.
     * 
     * @param e Given MouseEvent
     */
    public void doButtonsPressed(MouseEvent e) {
        //buttons of game
        HandlerGT handlerGT = game.getHandlerGT();
        handlerGT.mousePressed(e);
        //buttons of gameState
        game.getCurrState().mousePressed(e);
        //buttons of current gameSegment
        game.getCurrState().getCurrentSegment().mousePressed(e);
    }
    
    /**
     * Called when a mouse press occurs. Goes through the handler of the game
     * and checks if the mouse press is supposed to trigger some code.
     * 
     * @param e Given MouseEvent
     */
    public void doButtonsReleased(MouseEvent e) {
        //buttons of game
        HandlerGT handlerGT = game.getHandlerGT();
        handlerGT.mouseReleased(e);
        //buttons of gameState
        game.getCurrState().getHandlerGT().mouseReleased(e);
        //buttons of current gameSegment
        game.getCurrState().getCurrentSegment().mouseReleased(e);
    }
}

package TomHopper.general;

import TomHopper.Game;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * A class for the actual "GameWindow" of the game
 *
 * @author ptehr
 */
public class GameWindow extends Canvas {

    // Stores the dimensions 
    public static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    /**
     * Constructs a new Game Window for a game to be played on
     * 
     * @param width Width of the screen
     * @param height Height of the screen
     * @param title Title of the screen
     * @param game Game which will be played on this screen
     */
    public GameWindow(int width, int height, String title, Game game) {
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }

}

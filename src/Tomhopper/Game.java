package TomHopper;

import TomHopper.general.*;
import TomHopper.gui.OGraphics;
import TomHopper.input.*;
import TomHopper.structure.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

/**
 * The Game class is the class where the Game itself is created. It extends
 * Canvas which is a class that represents a blank rectangular area of the
 * screen where the user can draw and add stuff too. The Game class also
 * implements Runnable which is simply an interface that has the render method.
 *
 * @author IntDesign
 */
public class Game extends Canvas implements Runnable {
    // Sets the Dimensions of the Window. 
    public static final int WIDTH = 840, HEIGHT = WIDTH / 12 * 9; //(840, 630)
    public static final int VERSIONNUMBER = 26;

    //change this explanation
    /**
     * A Thread executes every program that is added to it. Since the only
     * program that is technically being executed is the 'game', the only object
     * in the thread object is 'game'
     */
    private Thread thread;
    // Boolean expression for if the game is currently running or not
    private boolean running = false;
    // Sets the default font type which will be used eveywhere in the game
    public static Font dfont = new Font("dialogue", Font.PLAIN, 12);

    // A data field for storing the current state of the game
    private AbstractGameState curState;
    // A data field for storing the map state of the game
    private GameMap map;
    // A data field for storing the menu state of the game
    private GameMenu menu;
    // A data field for storing the play state of the game
    private GamePlay play;

    // Data field for the hexWorld(aka the world map)
    private HexWorld hexWorld;

    // This is the Handler which "handles" all game things
    private HandlerGT handlerGT;

    // Data field for the mouse input recieved by the user
    private MouseInput mouseInput;
    // Data field for the key input recieved by the user
    private KeyInput keyInput;
    private AbstractKeyControl keyBinds;

    /**
     * Constructs the Game object. This class also defines and sets up every
     * data field.
     */
    public Game() {
        // All code in this chunk instantiates the data fields
        hexWorld = new HexWorld(this);
        map = new GameMap(hexWorld, this);
        menu = new GameMenu(this);
        play = new GamePlay(hexWorld, this);
        handlerGT = new HandlerGT();
        mouseInput = new MouseInput(this);
        keyInput = new KeyInput(this);
        keyBinds = new KeyBinds();

        // All the code in this chunk basically "turns on" a mouse input and key
        // input detector and feeds it back to their respective data fields
        this.addMouseListener(mouseInput);
        this.addKeyListener(keyInput);
        // Sets the start game state
        curState = menu;

        //something here? forgot
        // Creates the window which the game will be played on
        GameWindow window = new GameWindow(WIDTH, HEIGHT, "tell tom to hop!", this);
        this.requestFocus();
    }

    /**
     * The game tick method that runs everything, and specifically the tick
     * method of the current state.
     */
    private void tick() {
        //local
        keyBinds.tick();
        handlerGT.tick();
        //state
        curState.tick();
    }

    /**
     * Renders the game, specifically the current state of the game
     */
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        OGraphics og = new OGraphics((Graphics2D) g);

        //gen game rendering
        og.setColor(Color.black);
        og.fillRect(0, 0, WIDTH, HEIGHT);

        //specific state rendering
        curState.render(og);
        handlerGT.render(og);

        resetDraw(og);
        og.drawString("version " + VERSIONNUMBER, Game.WIDTH - 100, 15);

        g.dispose();
        bs.show();
    }

    private class KeyBinds extends AbstractKeyControl {

        public KeyBinds() {
            super(Game.this);
        }

        @Override
        protected void keyPressedSetUp() {
            addKeyAgent(new KeyAgent(KeyEvent.VK_ESCAPE), 10);
            addKeyAgent(new KeyAgent(KeyEvent.VK_NUMPAD1), 10);
            addKeyAgent(new KeyAgent(KeyEvent.VK_NUMPAD2), 10);
            addKeyAgent(new KeyAgent(KeyEvent.VK_NUMPAD3), 10);
        }

        @Override
        protected void keyPressed(String keyAgent) {

            if (keyAgent.equals("Escape")) {
                System.exit(1);
                return;
            }

            if (keyAgent.equals("NumPad-1")) {
                curState = map;
                return;
            }
            if (keyAgent.equals("NumPad-2")) {
                curState = menu;
                return;
            }
            if (keyAgent.equals("NumPad-3")) {
                curState = play;
                return;
            }
        }
    }

    public boolean keyAgentActive(KeyAgent ka) {
        boolean ans = true;
        if (ka.getDowns() != null) {
            for (int key : ka.getDowns()) {
                if (!keyIsDown(key)) {
                    ans = false;
                }
            }
        }
        if (ka.getUps() != null) {
            for (int key : ka.getUps()) {
                if (keyIsDown(key)) {
                    ans = false;
                }
            }
        }
        return ans;
    }

    public boolean keyIsDown(int keyCode) {
        return keyInput.getKeysDown().get(keyCode);
    }

    /**
     * Since a Graphics instance keeps its color/font etc, this method resets
     * the instance to default color and font
     *
     * @param g the graphics instance
     * @return the graphics reset, for convenience
     */
    private static Graphics resetDraw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(dfont);
        return g;
    }
    
    public static OGraphics resetDraw(OGraphics og) {
        resetDraw(og.getGraphics2D());
        return og;
    }

    public static OGraphics getRelOG(OGraphics og, int x, int y) {
        return Game.resetDraw(og.create(x, y));
    }

    public static OGraphics getRelOG(OGraphics og, float x, float y) {
        return Game.resetDraw(og.create((int) x, (int) y));
    }

    public void setState(String state) {
        if (state.equals("map")) {
            curState = map;
            return;
        }
        if (state.equals("menu")) {
            curState = menu;
            return;
        }
        if (state.equals("play")) {
            curState = play;
            return;
        }
    }

    public void runSegment(AbstractSegment seg) {
        if (seg instanceof AbstractSegmentMap) {
            //AbstractSegmentMap temSeg = (AbstractSegmentMap) seg;
            curState = map;
            map.runSegment(seg);
            return;
        }
        if (seg instanceof AbstractSegmentMenu) {
            //AbstractSegmentMenu temSeg = (AbstractSegmentMenu) seg;
            curState = menu;
            menu.runSegment(seg);
            return;
        }
        if (seg instanceof AbstractSegmentPlay) {
            //AbstractSegmentPlay temSeg = (AbstractSegmentPlay) seg;
            curState = play;
            play.runSegment(seg);
            return;
        }
    }

    /**
     * returns the Current Game State
     *
     * @return AbstractGameState
     */
    public AbstractGameState getCurrState() {
        return curState;
    }

    /**
     * returns the GameMap
     *
     * @return AbstractGamestate
     */
    public AbstractGameState getGameMap() {
        return map;
    }

    /**
     * returns the GameMenu
     *
     * @return AbstractGameState
     */
    public AbstractGameState getGameMenu() {
        return menu;
    }

    /**
     * returns the GamePlay
     *
     * @return AbstractGameState
     */
    public AbstractGameState getGamePlay() {
        return play;
    }

    /**
     * returns the HandlerGT
     *
     * @return HandlerB
     */
    public HandlerGT getHandlerGT() {
        return handlerGT;
    }

    /**
     * This method starts the Game by adding it to the Thread which executes the
     * Game. Also sets the boolean running to true
     */
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /**
     * This method ends the Game by basically removing it from the thread. Also
     * sets the running boolean to false. Throws an exception if its already
     * done
     */
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * This program LITERALLY runs the entire Game by looping until the game is
     * over. While the game is running, it calls tick and render over and over
     * again.
     */
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    /**
     * The main method which constructs a Game, thus starting the Game.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Game();
    }
}

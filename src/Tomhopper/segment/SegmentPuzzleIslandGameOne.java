/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TomHopper.segment;

import TomHopper.gui.*;
import TomHopper.movement.*;
import TomHopper.collision.*;
import TomHopper.general.*;
import TomHopper.Game;
import TomHopper.input.*;
import TomHopper.structure.*;
import TomHopper.things.*;
import TomHopper.utility.*;
import java.awt.*;

/**
 * HOW TO HAVE RENDER PRIORITIES???? Prioritize player render when on top of
 * other objects
 *
 * @author ptehr
 */
public class SegmentPuzzleIslandGameOne extends AbstractSegmentPlay {

    private Player player;

    public SegmentPuzzleIslandGameOne(Game game) {
        super(game);
        collisions = new Collisions(handlerGT, game);
        keyBinds = new KeyBinds();
        
        player = new Player(10, 10, game).putSelfInHandler(handlerGT);
        
        new Fish(100, 100, new PrinceAngle(40), new TickTimer(40), game).putSelfInHandler(handlerGT);
        new Drain(300, 300, 60, player, game).putSelfInHandler(handlerGT);
        new Wall(300, 300, 100, 100, new PrinceAngle(0), game).putSelfInHandler(handlerGT);
        new Predator(400, 400, 40, 40, player, game).putSelfInHandler(handlerGT);
    }

    private class KeyBinds extends AbstractKeyControl {

        public KeyBinds() {
            super(game);
        }

        @Override
        public void keyPressed(String s) {

        }

        @Override
        public void keyPressedSetUp() {

        }

    }

    private class Player extends AbstractWidget<Player> {

        static final int WIDTH = 30;
        static final int HEIGHT = 30;

        public Player(int x, int y, Game game) {
            super(x, y, WIDTH, HEIGHT, game);
            mov = new Movement2D(3, 3, game);
        }

        public void addToPosition(Vector vel) {
            pos.add(vel);
        }
        
        @Override
        public void render(OGraphics og) {
            og.setColor(Color.green);
            PrinceAngle angle = this.getHitBox().getAngle();
            this.getHitBox().render(og);
        }
        
        //add a render method for specific sprite someday
    }

    private class Wall extends AbstractWidget<Wall> {

        public Wall(int x, int y, int w, int h, PrinceAngle angle, Game game) {
            super(x, y, w, h, game);
            this.getHitBox().setAngle(angle);
        }
        
        @Override
        public void render(OGraphics og) {
            og.setColor(Color.green);
            this.getHitBox().render(og);
        }

    }

    private class Fish extends AbstractWidget<Fish> {

        static final int WIDTH = 40;
        static final int HEIGHT = 40;

        private TickTimer timer;
        private PrinceAngle angle;

        public Fish(int x, int y, PrinceAngle angle, TickTimer timer, Game game) {
            super(x, y, WIDTH, HEIGHT, game);
            this.angle = angle;
            this.timer = timer;
        }

        @Override
        public void tick() {
            super.tick();
            if (timer.interact()) {
                shoot();
            }
        }

        public void shoot() {
            StraightBullet bull = new StraightBullet(new Vector2D(12f, angle),
                    (int) getHitBox().getX(), (int) getHitBox().getY(), game);
            bull.addToIgnoreList(this);
            bull.putSelfInHandler(handler);
        }

        //add render method for specific sprite someday
    }

    private class Current extends AbstractWidget<Current> {

        private Player player;
        private Vector vector;

        public Current(int x, int y, int w, int h, Vector vector, Player player, Game game) {
            super(x, y, w, h, game);
            this.player = player;
            this.vector = vector;
        }

        @Override
        public void tick() {
            if (containsPlayer()) {
                player.addToPosition(vector);
            }
        }

        public boolean containsPlayer() {
            if (player.getX() > this.getX() && player.getX() < this.getX() + this.getWidth()) {
                if (player.getY() > this.getY() && player.getY() < this.getY() + this.getHeight()) {
                    return true;
                }
            }
            return false;
        }

        // create a render method for specific sprite someday
    }

    private class Predator extends AbstractWidget<Predator> {

        private Player player;
        private final int magnitude = 3;
        private boolean seePlayer;

        public Predator(int x, int y, int w, int h, Player player, Game game) {
            super(x, y, w, h, game);
            this.setVel(new Vector(0, 0));
            this.player = player;
            seePlayer = true;
            Sight sight = new Sight(0, 0, new Box(0, 0, distance(), getWidth() / 2, (int)getAnglePlayer().getInDeg(), game), player, this, game);
            sight.putSelfInHandler(handlerGT);
        }

        public void setSeePlayer(boolean val) {
            seePlayer = val;
        }

        public PrinceAngle getAnglePlayer() {
            PrinceAngle angle;
            angle = new Vector(player.getHitBox().getX() - this.getHitBox().getX(), player.getHitBox().getY() - this.getHitBox().getY()).getAbsAngle(new Vector(1, 0));
            if (player.getHitBox().getY() - this.getHitBox().getY() < 0) {
                angle.negate();
            }
            return angle;
        }

        public int distance() {
            Vector vector = new Vector(this.getHitBox().getX() - player.getHitBox().getX(), this.getHitBox().getY() - player.getHitBox().getY());
            return (int) vector.magnitude();
        }

        @Override
        public void tick() {
            if (seesPlayer()) {
                PrinceAngle angle = getAnglePlayer();
                Vector vector = new Vector((float) Math.cos(angle.getInRad()), (float) Math.sin(angle.getInRad()));
                vector.scaleUp(magnitude);
                pos.add(vector);
                //prob need a method for this
                this.getHitBox().updatePosition(pos);
                this.getHitBox().setAngle(angle);
            }
        }

        public boolean seesPlayer() {
            //Only need to make this once?
            CollisionSights collision = new CollisionSights(handlerGT, game);
            
            collision.tick();
            if (seePlayer) {
                return true;
            }
            seePlayer = true;
            return false;
        }
    }

    private class Sight extends AbstractGameObject<Sight> {

        private Predator predator;
        private Player player;

        public Sight(int x, int y, Box box, Player player, Predator predator, Game game) {
            super(x, y, new Vector(0, 0), box, game);
            this.predator = predator;
            this.player = player;
        }
        
        @Override
        public void tick() {
            this.getHitBox().setAngle((predator.getAnglePlayer()));
            ((Box) this.getHitBox()).setWidth(predator.distance());
            //bro make this better
            this.getHitBox().updatePosition(new Vector((predator.getHitBox().getX() + player.getHitBox().getX()) / 2 - ((Box)this.getHitBox()).getWidth() / 2, (predator.getHitBox().getY() + player.getHitBox().getY()) / 2 - ((Box) this.getHitBox()).getHeight() / 2));
        }
        
        @Override
        public void render(OGraphics og) {
            this.getHitBox().render(og);
        }

        public Predator getPredator() {
            return predator;
        }
    }
    
    private class Drain extends AbstractGameObject<Drain> {

        private Player player;
        private int radius;

        public Drain(int x, int y, int radius, Player player, Game game) {
            super(x, y, new Vector(0, 0), new Circle(x, y, radius / 4, game), game);
            this.player = player;
            this.radius = radius;
        }
        
        @Override
        public void tick() {
            if (distance() < radius) {
                float dist = distance();
                float magnitude = 4f;
                PrinceAngle angle = new Vector(player.getX() - this.getX(), player.getY() - this.getY()).getAbsAngle(new Vector(1, 0));
                if (player.getY() < this.getY()) {
                    angle.negate();
                }
                Vector vector = new Vector(-1 * (float) Math.cos(angle.getInRad()), -1 * (float) Math.sin(angle.getInRad()));
                vector.scaleUp(magnitude);
                player.addToPosition(vector);
            }
        }

        @Override
        public void render(OGraphics og) {
            og.setColor(Color.green);
            og.fillOval((int)getX() - radius, (int)getY() - radius, 2 * radius, 2 * radius);
        }

        public float distance() {
            float val = (player.getHitBox().getX() - this.getX()) * (player.getHitBox().getX() - this.getX()) + (player.getHitBox().getY() - this.getY()) * (player.getHitBox().getY() - this.getY());
            return (float) Math.sqrt(val);
        }
    }

    private class Collisions extends AbstractCollisionDetection {

        public Collisions(HandlerGT handler, Game game) {
            super(handler, game);
        }
        
        @Override
        public void ifCollision(AbstractGameObject object1, AbstractGameObject object2) {
            final int xCoord = 10;
            final int yCoord = 10;
            if (object1 instanceof Wall && object2 instanceof Player) {
                Player player = (Player) object2;
                player.setX(xCoord);
                player.setY(yCoord);
            } else if (object1 instanceof Player && object2 instanceof Wall) {
                Player player = (Player) object1;
                player.setX(xCoord);
                player.setY(yCoord);
            } else if (object1 instanceof Fish && object2 instanceof Player) {
                Player player = (Player) object2;
                player.setX(xCoord);
                player.setY(yCoord);
            } else if (object1 instanceof Player && object1 instanceof Fish) {
                Player player = (Player) object1;
                player.setX(xCoord);
                player.setY(yCoord);
            } else if (object1 instanceof StraightBullet && object2 instanceof Player) {
                Player player = (Player) object2;
                player.setX(xCoord);
                player.setY(yCoord);
            } else if (object1 instanceof Player && object2 instanceof StraightBullet) {
                Player player = (Player) object1;
                player.setX(xCoord);
                player.setY(yCoord);
            } else if (object1 instanceof Drain && object2 instanceof Player) {
                //fix
                Player player = (Player) object2;
                player.setX(xCoord);
                player.setY(yCoord);
            } else if (object1 instanceof Player && object2 instanceof Drain) {
                //fix
                Player player = (Player) object1;
                player.setX(xCoord);
                player.setY(yCoord);
            } else if (object1 instanceof Predator && object2 instanceof Player) {
                Player player = (Player) object2;
                player.setX(xCoord);
                player.setY(yCoord);
            } else if (object1 instanceof Player && object2 instanceof Predator) {
                Player player = (Player) object1;
                player.setX(xCoord);
                player.setY(yCoord);
            }
        }
    }

    private class CollisionSights extends AbstractCollisionDetection {

        public CollisionSights(HandlerGT handler, Game game) {
            super(handler, game);
        }

        @Override
        public void ifCollision(AbstractGameObject object1, AbstractGameObject object2) {
            if ((object1 instanceof Wall && object2 instanceof Sight)) {
                Sight sights = (Sight) object2;
                sights.getPredator().setSeePlayer(false);
            } else if (object1 instanceof Sight && object2 instanceof Wall) {
                Sight sights = (Sight) object1;
                sights.getPredator().setSeePlayer(false);
            }
        }
    }

}

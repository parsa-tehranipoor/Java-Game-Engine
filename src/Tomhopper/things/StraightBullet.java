/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TomHopper.things;

import TomHopper.Game;
import TomHopper.movement.AbstractGameObject;
import TomHopper.art.Sprites;
import TomHopper.utility.Vector;
import TomHopper.utility.Vector2D;
import TomHopper.collision.Box;
import TomHopper.gui.OGraphics;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 *
 * @author ptehr
 * @param <T>
 */
public class StraightBullet<T extends StraightBullet> extends AbstractGameObject<T> {

    public static final int SIZE = 18;
    protected BufferedImage imageL, imageR;
    protected ArrayList<Object> ignoreList;

    public StraightBullet(Vector2D vel, int x, int y, Game game) {
        super(x, y, vel, new Box(x + 10, y + 10, 20, 20, game), game);
        angle = vel.getAngle();
        imageL = Sprites.getImage("DUCKL");
        imageR = Sprites.getImage("DUCKR");
        renderOffset = new Vector(-SIZE / 2, -SIZE / 2);
        ignoreList = new ArrayList();
    }

    public void addToIgnoreList(Object obj) {
        ignoreList.add(obj);
    }

    public boolean isInIgnoreList(Object obj) {
        return ignoreList.contains(obj);
    }

    @Override
    public void render(OGraphics og) {
        OGraphics roRelOG2D = getRoRelOG(og);
        roRelOG2D.drawRect(0, 0, 17, 18);
        drawImageLR(imageL, imageR, roRelOG2D);

        /*test:
        roRelG2D.setColor(Color.red);
        roRelG2D.drawRect(0, 0, SIZE, SIZE);
        Game.getRelRenG(g, this).drawRect(0, 0, SIZE, SIZE);*/
    }

}

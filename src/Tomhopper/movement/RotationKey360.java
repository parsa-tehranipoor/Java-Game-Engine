/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.movement;

import TomHopper.Game;
import TomHopper.utility.Maths;
import java.awt.event.KeyEvent;
import java.util.Stack;

/**
 * 
 * 
 * Side note: Does this class need an AbstractGameObject parameter or can we do
 * the necessary stuff in the AbstractGameObject class?
 * @author cdwan
 */
public class RotationKey360 extends AbstractRotation {

    private AbstractGameObject obj;
    private float omega;
    private float omag; //magnitude
    private boolean downW, downA, downS, downD;
    private Stack<Integer> downStack = new Stack();
    private int activeKey;
    private Integer W = KeyEvent.VK_I, A = KeyEvent.VK_J,
            S = KeyEvent.VK_K, D = KeyEvent.VK_L, NONE = -69;

    public RotationKey360(AbstractGameObject o, Game g) {
        this(5, o, g);
    }

    public RotationKey360(float omag, AbstractGameObject o, Game g) {
        this.omag = omag;
        obj = o;
        game = g;
        downW = false;
        downA = false;
        downS = false;
        downD = false;
        downStack.add(NONE);
    }

    @Override
    public void tick() {
        tickStack();
        updateDowns();
        tickActiveKey();
        tickOmega();
    }

    private void tickStack() {
        if (downW && !game.keyIsDown(W)) {
            downStack.remove(W);
        } else if (downS && !game.keyIsDown(S)) {
            downStack.remove(S);
        } else if (downA && !game.keyIsDown(A)) {
            downStack.remove(A);
        } else if (downD && !game.keyIsDown(D)) {
            downStack.remove(D);
        }
        if (!downW && game.keyIsDown(W)) {
            downStack.add(W);
        } else if (!downS && game.keyIsDown(S)) {
            downStack.add(S);
        } else if (!downA && game.keyIsDown(A)) {
            downStack.add(A);
        } else if (!downD && game.keyIsDown(D)) {
            downStack.add(D);
        }
    }

    private void tickActiveKey() {
        activeKey = downStack.peek();
    }

    private void tickOmega() {
        if (activeKey == W) {
            if (Maths.smallerAngleDist(obj.getAngle().getInDeg(), 90) < 1e-5) {

            } else if (Maths.smallerAngleDist(obj.getAngle().getInDeg(), 270) < 1e-5) {

            } else if (obj.getAngle().getInDeg() > 90 && obj.getAngle().getInDeg() < 270) {
                setCW();
            } else {
                setCCW();
            }
        } else if (activeKey == A) {
            if (Maths.smallerAngleDist(obj.getAngle().getInDeg(), 0) < 1e-5) {

            } else if (Maths.smallerAngleDist(obj.getAngle().getInDeg(), 180) < 1e-5) {

            } else if (obj.getAngle().getInDeg() > 0 && obj.getAngle().getInDeg() < 180) {
                setCW();
            } else {
                setCCW();
            }
        } else if (activeKey == S) {
            if (Maths.smallerAngleDist(obj.getAngle().getInDeg(), 90) < 1e-5) {

            } else if (Maths.smallerAngleDist(obj.getAngle().getInDeg(), 270) < 1e-5) {

            } else if (obj.getAngle().getInDeg() > 90 && obj.getAngle().getInDeg() < 270) {
                setCCW();
            } else {
                setCW();
            }
        } else if (activeKey == D) {
            if (Maths.smallerAngleDist(obj.getAngle().getInDeg(), 0) < 1e-5) {

            } else if (Maths.smallerAngleDist(obj.getAngle().getInDeg(), 180) < 1e-5) {

            } else if (obj.getAngle().getInDeg() > 0 && obj.getAngle().getInDeg() < 180) {
                setCCW();
            } else {
                setCW();
            }
        } else if (activeKey == NONE) {
            setZero();
        }
    }

    private void updateDowns() {
        downW = game.keyIsDown(W);
        downA = game.keyIsDown(A);
        downS = game.keyIsDown(S);
        downD = game.keyIsDown(D);
    }

    @Override
    public float getOmega() {
        return omega;
    }

    public void setCW() {
        omega = omag;
    }

    public void setCCW() {
        omega = -omag;
    }

    public void setZero() {
        omega = 0;
    }

    @Override
    public void setOmega(float om) {
        omega = om;
    }
}

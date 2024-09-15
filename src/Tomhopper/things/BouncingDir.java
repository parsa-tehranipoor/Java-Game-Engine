/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.things;

import TomHopper.utility.PrinceAngle;

/**
 *
 * @author cdwan
 */
public class BouncingDir extends PrinceAngle {

    boolean down = true;

    public BouncingDir() {
        this(0);
    }

    public BouncingDir(float theta) {
        super(theta);
    }

    public void tick() {
        if (Math.abs(getInDeg() - 90) < 1e-6) {
            down = false;
            rotate(-2);
        }
        if (Math.abs(getInDeg() - 270) < 1e-6) {
            down = true;
            rotate(2);
        }
        if (down) {
            rotate(2);
        } else {
            rotate(-2);
        }
    }
}

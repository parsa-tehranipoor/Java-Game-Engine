/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.things;

import TomHopper.Game;
import TomHopper.movement.MovementProjectile;
import TomHopper.utility.PrinceAngle;
import TomHopper.utility.Vector;
import TomHopper.utility.Vector2D;

/**
 *
 * @author cdwan
 */
public class GravBullet extends StraightBullet<GravBullet> {

    public GravBullet(Vector2D vel, int x, int y, Game game) {
        super(vel, x, y, game);
        mov = new MovementProjectile(vel, new Vector(0, .3f));
    }

    @Override
    public void tick() {
        super.tick();
        angle = new PrinceAngle(((Vector2D) vel).getInDeg());
    }
    
}

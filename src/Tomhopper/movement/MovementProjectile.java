/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.movement;

import TomHopper.utility.Vector;

/**
 * Movement class for projectiles.
 * 
 * @author cdwan
 */
public class MovementProjectile extends AbstractMovement {

    // Velocity of projectile
    Vector vel;
    // Vector for gravity direction and magnitude
    Vector grav;
    
    /**
     * Constructs a MovementProjectile.
     * 
     * @param vel Initial Velocity
     * @param grav Gravity
     */
    public MovementProjectile(Vector vel, Vector grav) {
        this.vel = vel;
        this.grav = grav;
    }
    
    /**
     * Returns the velocity of the projectile.
     * 
     * @return Velocity
     */
    @Override
    public Vector getVel() {
        return vel;
    }
    
    /**
     * Returns the gravity acting on the projectile.
     * 
     * @return Gravity vector
     */
    public Vector getGraV() {
        return grav;
    }
    
    /**
     * Tick method that allows gravity to act on projectile.
     */
    @Override
    public void tick() {
        vel.add(grav);
    }
    
}

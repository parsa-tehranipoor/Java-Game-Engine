/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.utility;

/**
 * A class for counting down time with data fields for holding time and
 * interacting with it, changing it, and accessing it.
 *
 * @author cdwan
 */
public class TickTimer {

    // Length is how long a time block is/is the maximum time which left can be
    // Left is how much time is left from 0
    int length, left;

    /**
     * Creates a Time Object with the starting value of left being the maximum
     * value
     *
     * @param max length
     */
    public TickTimer(int max) {
        this(max, max);
    }

    /**
     * Creates a Time object
     *
     * @param max length
     * @param start starting time value
     */
    public TickTimer(int max, int start) {
        this.length = max;
        this.left = start;
    }

    /**
     * updates timer (one "tick" of it) w reset
     *
     * @return true if hit zero (and reset)
     */
    public boolean interact() {
        if (left > 0) {
            --left;
        }
        if (left == 0) {
            reset();
            return true;
        }
        return false;
    }

    /**
     * updates timer down (one "tick" of it) wo reset
     *
     * @return true if hit zero (no reset)
     */
    public boolean tick() {
        if (left > 0) {
            --left;
        }
        return left == 0;
    }

    /**
     * resets the time to the length
     */
    public void reset() {
        left = length;
    }

    /**
     * returns the current time remaining
     *
     * @return
     */
    public int check() {
        return left;
    }

    /**
     * No idea (lol it's how much time has passed from timer length)
     *
     * @return
     */
    public int checkPassed() {
        return length - left;
    }

    /**
     * sets the time left to new value
     *
     * @param c
     */
    public void setLeft(int c) {
        left = c;
    }

    /**
     * returns the length of the timer
     *
     * @return length
     */
    public int length() {
        return length;
    }

    /**
     * Sets the length of the timer. If the length is less than left, then left
     * is set to length
     *
     * @param l given length
     */
    public void setLength(int l) {
        length = l;
        if (left > length) {
            left = length;
        }
    }
}

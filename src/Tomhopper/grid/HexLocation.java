/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.grid;


/**
 * A class for the specific type of location represented by a hexagonal pattern.
 * 
 * @author cdwan
 */
public class HexLocation extends AbstractLocation {

    // Row and Column in HexGrid
    private int r, c;
    // Boolean value for if there are an even number of rows
    private boolean evenRow;

    // Static directions that can be used(each are in degrees)
    public static final int UPRIGHT = 30;
    public static final int UP = 90;
    public static final int UPLEFT = 150;
    public static final int DOWNLEFT = 210;
    public static final int DOWN = 270;
    public static final int DOWNRIGHT = 330;

    public static final int BAD = -420;

    public static final int CWONE = -60;
    public static final int CCWONE = 60;
    public static final int TURNAROUND = 180;
    public static final int FULLTURN = 360;

    /**
     * Constructs a HexLocation object.
     *
     * @param r Row index
     * @param i Column index
     */
    public HexLocation(int r, int i) {
        this.r = r;
        this.c = i;
        this.evenRow = r % 2 == 0;
    }

    /**
     * Returns the row of this location.
     *
     * @return Row
     */
    public int getRow() {
        return r;
    }

    /**
     * Returns the column of this location.
     *
     * @return c Column
     */
    public int getCol() {
        return c;
    }

    /**
     * Checks if there are an even number of rows.
     *
     * @return Boolean value depending on the number of rows
     */
    public boolean inEvenRow() {
        return evenRow;
    }

    /**
     * Checks if a given location is at the same location as this location.
     *
     * @param other Given location
     * @return True if both HexLocations are at the same row and column
     */
    public boolean equals(HexLocation other) {
        return r == other.r && c == other.c;
    }

    /**
     * Gets the adjacent HexLocation in a specific direction given in degrees.
     *
     * @param degrees Given direction
     * @return Adjacent HexLocation in given direction
     */
    public HexLocation getAdjacentHexLocation(int degrees) {
        switch ((degrees % FULLTURN + FULLTURN) % FULLTURN) {
            case UPRIGHT: {
                if (evenRow) {
                    return new HexLocation(r - 1, c + 1);
                } else {
                    return new HexLocation(r - 1, c);
                }
                // Good
            }
            case UP: {
                return new HexLocation(r - 2, c);
                // Good
            }
            case UPLEFT: {
                if (evenRow) {
                    return new HexLocation(r - 1, c);
                } else {
                    return new HexLocation(r - 1, c - 1);
                }
                // Good
            }
            case DOWNLEFT: {
                if (evenRow) {
                    return new HexLocation(r + 1, c);
                } else {
                    return new HexLocation(r + 1, c - 1);
                }
                // Changed
            }
            case DOWN: {
                return new HexLocation(r + 2, c);
                // Good
            }
            case DOWNRIGHT: {
                if (evenRow) {
                    return new HexLocation(r + 1, c + 1);
                } else {
                    return new HexLocation(r + 1, c);
                }
                // Good
            }
            default: {
                return null;
            }
        }
    }

    /**
     * Returns direction in form of degrees between a given location and this location.
     * If the 2 locations are not adjacent then this method returns -1.
     *
     * @param other Given location
     * @return Direction given location is from this location
     */
    public int getDirectionTowardsAdjacentLocation(HexLocation other) {
        int difR = other.r - r;
        int difI = other.c - c;
        if (evenRow) {
            switch (difR) {
                case -2: {
                    switch (difI) {
                        case 0:
                            return UP;
                        default:
                            return BAD;
                    }
                    // Good
                }
                case -1: {
                    switch (difI) {
                        case 0:
                            return UPLEFT;
                        case 1:
                            return UPRIGHT;
                        default:
                            return BAD;
                    }
                    // Good
                }
                case 1: {
                    switch (difI) {
                        case 0:
                            return DOWNLEFT;
                        case 1:
                            return DOWNRIGHT;
                        default:
                            return BAD;
                    }
                    // Good
                }
                case 2: {
                    switch (difI) {
                        case 0:
                            return DOWN;
                        default:
                            return BAD;
                    }
                    // Good
                }
                default: {
                    return BAD;
                }
            }
        } else {
            switch (difR) {
                case -2: {
                    switch (difI) {
                        case 0:
                            return UP;
                        default:
                            return BAD;
                    }
                    // Good
                }
                case -1: {
                    switch (difI) {
                        case -1:
                            return UPLEFT;
                        case 0:
                            return UPRIGHT;
                        default:
                            return BAD;
                    }
                    // Good
                }
                case 1: {
                    switch (difI) {
                        case -1:
                            return DOWNLEFT;
                        case 0:
                            return DOWNRIGHT;
                        default:
                            return BAD;
                    }
                    // Good
                }
                case 2: {
                    switch (difI) {
                        case 0:
                            return DOWN;
                        default:
                            return BAD;
                    }
                    // Good
                }
                default: {
                    return BAD;
                }
            }
        }
    }
}

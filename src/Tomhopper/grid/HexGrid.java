/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.grid;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class for a Grid in a hexagonal pattern.
 * 
 * @author cdwangler
 * @param <E> 
 */
public class HexGrid<E> extends AbstractGrid<E> implements Iterable<E> {
    
    // R rows, C - 1 and C columns alternating with C - 1 as the FIRST row column length
    private final int rows, cols;
    // Hex Grid of occupants
    private Object[][] hgridOccs;

    /**
     * Constructs a HexGrid.
     *
     * @param r Rows
     * @param c Columns
     */
    public HexGrid(int r, int c) {
        rows = r;
        cols = c;
        this.hgridOccs = NewHexGrid(r, c);
    }

    /**
     * Returns the grid stored as an array.
     *
     * @return Object[][] hgridOccs
     */
    public Object[][] getArray() {
        return hgridOccs;
    }

    /**
     * Returns the number of rows.
     * 
     * @return Number of Rows
     */
    @Override
    public int getNumRows() {
        return rows;
    }

    /**
     * Returns the longer length of what a column's length could be.
     * 
     * @return Number of Columns
     */
    @Override
    public int getNumCols() {
        return cols;
    }

    /**
     * Returns the number of columns in a specified row.
     *
     * @param r Given row
     * @return The number of columns in the row
     */
    public int colsInRow(int r) {
        return cols - (r + 1) % 2;
    }

    /**
     * Checks if a spot is valid which just means if the location is on the Grid.
     *
     * @param loc Given HexLocation
     * @return Boolean value for validity of given location
     */
    public boolean isValid(HexLocation loc) {
        return loc.getRow() < rows && loc.getCol() < colsInRow(loc.getRow());
    }

    /**
     * Returns the surrounding neighbors of a given location on the Grid.
     *
     * @param loc Given location
     * @return ArrayList of neighboring locations on Grid
     */
    public ArrayList<E> getNeighbors(HexLocation loc) {
        ArrayList<E> neighbors = new ArrayList<>();
        for (HexLocation neighborLoc : getOccupiedAdjacentLocations(loc)) {
            neighbors.add(get(neighborLoc));
        }
        return neighbors;
    }

    /**
     * Returns the surrounding neighbors that are on the Grid/are valid and adjacent to a
     * given location on the Grid.
     *
     * @param loc Given location
     * @return ArrayList of valid adjacent locations
     */
    public ArrayList<HexLocation> getValidAdjacentLocations(HexLocation loc) {
        ArrayList<HexLocation> locs = new ArrayList<>();
        int dir = HexLocation.UPRIGHT;
        for (int i = 0; i < HexLocation.FULLTURN / HexLocation.CCWONE; ++i) {
            HexLocation neighborLoc = loc.getAdjacentHexLocation(dir);
            if (isValid(neighborLoc)) {
                locs.add(neighborLoc);
            }
            dir += HexLocation.CCWONE;
        }
        return locs;
    }

    /**
     * Returns the surrounding neighbors on the Grid that are not taken up/are 
     * empty and valid and are adjacent to a given location on Grid.
     *
     * @param loc Given location
     * @return ArrayList of valid empty adjacent locations
     */
    public ArrayList<HexLocation> getEmptyAdjacentLocations(HexLocation loc) {
        ArrayList<HexLocation> locs = new ArrayList<>();
        for (HexLocation neighborLoc : getValidAdjacentLocations(loc)) {
            if (get(neighborLoc) == null) {
                locs.add(neighborLoc);
            }
        }
        return locs;
    }

    /**
     * Returns the surrounding neighbors on the Grid that are adjacent to a given
     * location and that are occupied and valid on the Grid.
     *
     * @param loc Given location
     * @return ArrayList of valid adjacent occupied locations
     */
    public ArrayList<HexLocation> getOccupiedAdjacentLocations(HexLocation loc) {
        ArrayList<HexLocation> locs = new ArrayList<>();
        for (HexLocation neighborLoc : getValidAdjacentLocations(loc)) {
            if (get(neighborLoc) != null) {
                locs.add(neighborLoc);
            }
        }
        return locs;
    }

    /**
     * Returns a list of occupied locations on the HexGrid.
     *
     * @return ArrayList of occupied locations
     */
    public ArrayList<HexLocation> getOccupiedLocations() {
        ArrayList<HexLocation> occLocs = new ArrayList<>();
        for (int r = 0; r < rows; ++r) {
            for (int c = 0; c < colsInRow(r); ++c) {
                if (hgridOccs[r][c] != null) {
                    occLocs.add(new HexLocation(r, c));
                }
            }
        }
        return occLocs;
    }

    /**
     * Returns the object found at a given location on the Grid.
     *
     * @param loc Given location
     * @return Object found at a given location
     */
    public E get(HexLocation loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("HexLocation " + loc + " is not valid");
        }
        return (E) hgridOccs[loc.getRow()][loc.getCol()];
    }

    /**
     * Puts a given object at a given location on the HexGrid.
     *
     * @param loc Given location
     * @param obj Given object
     * @return E The object that was previously at this location
     */
    public E put(HexLocation loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("HexLocation " + loc + " is not valid");
        }
        if (obj == null) {
            throw new NullPointerException("obj == null");
        }
        Object e = hgridOccs[loc.getRow()][loc.getCol()];
        hgridOccs[loc.getRow()][loc.getCol()] = obj;
        return (E) e;
    }

    /**
     * Removes whatever object is found at a specific location on the HexGrid.
     *
     * @param loc Given location
     * @return E The object that was previously at this location
     */
    public E remove(HexLocation loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("HexLocation " + loc + " is not valid");
        }
        Object e = hgridOccs[loc.getRow()][loc.getCol()];
        hgridOccs[loc.getRow()][loc.getCol()] = null;
        return (E) e;
    }

    /**
     * Creates a new HexGrid Object array.
     * 
     * @param r Number of rows
     * @param c Number of columns
     * @return Object array representing the HexGrid
     */
    private Object[][] NewHexGrid(int r, int c) {
        Object[][] par = new Object[r][];
        for (int i = 0; i < par.length; ++i) {
            if (i % 2 == 0) {
                par[i] = new Object[c - 1];
            } else {
                par[i] = new Object[c];
            }
        }
        return par;
    }

    /**
     * Returns the Iterator for a HexGrid object.
     * 
     * @return HexGridIterator
     */
    @Override
    public Iterator<E> iterator() {
        return new HexGridIterator();
    }

    /**
     * A private class for representing an Iterator for a HexGrid.
     * @param <E> 
     */
    private class HexGridIterator<E> implements Iterator<E> {
        
        // Initial row and column Iterator is on
        private int r = 0, c = 0;

        /**
         * Checks if there is a next element in the HexGrid this Iterator is iterating
         * through.
         * 
         * @return Boolean value for if there is a next value to be iterated on
         */
        @Override
        public boolean hasNext() {
            return r != rows - 1 && c != colsInRow(rows) - 1;
        }

        /**
         * Iterates the Iterator onto the next element of the HexGrid its iterating
         * 
         * @return Object at this next element
         */
        @Override
        public E next() {
            ++c;
            if (c == colsInRow(r)) {
                ++r;
                c = 0;
            }
            return (E) hgridOccs[r][c];
        }

    }

}

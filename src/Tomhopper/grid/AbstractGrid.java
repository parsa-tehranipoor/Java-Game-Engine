/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TomHopper.grid;

/**
 * Abstract Class for any Grid like structures.
 * @author cdwan
 */
public abstract class AbstractGrid<E> {

    /**
     * Gets the number of rows in Grid.
     * 
     * @return Rows
     */
    public abstract int getNumRows();

    /**
     * Gets the number of columns in Grid.
     * 
     * @return Columns
     */
    public abstract int getNumCols();

    //locations weird?
    
    /*public abstract boolean isValid(AbstractLocation loc);

    public abstract E put(AbstractLocation loc, E obj);

    public abstract E remove(AbstractLocation loc);

    public abstract E get(AbstractLocation loc);

    public abstract ArrayList<AbstractLocation> getOccupiedLocations();

    public abstract ArrayList<AbstractLocation> getValidAdjacentLocations(AbstractLocation loc);

    public abstract ArrayList<AbstractLocation> getEmptyAdjacentLocations(AbstractLocation loc);

    public abstract ArrayList<AbstractLocation> getOccupiedAdjacentLocations(AbstractLocation loc);

    public abstract ArrayList<E> getNeighbors(AbstractLocation loc);*/
}

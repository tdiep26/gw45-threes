package info.gridworld.grid;

import java.util.ArrayList;

public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private ArrayList<LinkedList<Object>> occupantArray;

    public SparseBoundedGrid(int rows, int cols) {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new ArrayList<LinkedList<Object>()>(rows);
    }

    public int getNumRows() {
        return occupantArray.size();
    }

    public int getNumCols() {
	return occupantArray.get(0).size();
    }

    public boolean isValid(Location loc) {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> theLocations = new ArrayList<Location>();

         for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumCols(); c++) {
		Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public E get(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) occupantArray.get(loc.getRow()).get(loc.getCol()); 
    }

    public E put(Location loc, E obj) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        E oldOccupant = get(loc);
        occupantArray.get(loc.getRow()).add(loc.getCol()) = obj;
        return oldOccupant;
    }

    public E remove(Location loc) {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
	return occupantArray.get(loc.getRow()).remove(loc.getCol());
    }
}

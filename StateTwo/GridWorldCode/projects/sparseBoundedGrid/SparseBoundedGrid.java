//package info.gridworld.grid;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Grid;

class OccupantIncol 
{
	private Object occupant;
	private int col;

	public OccupantIncol(Object occupant, int col) {
		this.occupant = occupant;
		this.col = col;
	}

	public int getCol() {
		return this.col;
	}

	public Object getOccupant() {
		return this.occupant;
	}

	public void setOccupant(Object newOccupant) {
		this.occupant = newOccupant;
	}
}

public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
	private ArrayList<LinkedList> occupantArray; //the linklist storting the grid element;
	private int rows, cols;
	/**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in BoundedGrid
     * @param cols number of columns in BoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
		this.rows = rows;
		this.cols = cols;
        int i;
		occupantArray = new ArrayList<LinkedList>();
		for(i = 0; i < rows; ++i) {
			occupantArray.add(new LinkedList<OccupantIncol>());
		}
    }

    public int getNumRows()
    {
        return rows; 
    }

    public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return cols; 
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        //return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
		int row = loc.getRow();
		int col = loc.getCol();
		OccupantIncol locOccupant = null ;
		LinkedList<OccupantIncol> locLList = occupantArray.get(row);
		if(locLList != null)
		{
			for(OccupantIncol O : locLList) {
				if(O.getCol() == col) {
					return (E) O.getOccupant();
				}
			}
		}
		return null;
	}

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
		OccupantIncol oldOccupantIncol = new OccupantIncol(obj, loc.getCol());
		//occupantArray[loc.getRow()][loc.getCol()];
        LinkedList<OccupantIncol> curlList = occupantArray.get(loc.getRow());
		curlList.add(oldOccupantIncol);
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
       // occupantArray[loc.getRow()][loc.getCol()] = null;
	   	LinkedList curLlist = occupantArray.get(loc.getRow());
		if(curLlist != null)
		{
			Iterator<OccupantIncol> itr = curLlist.iterator();
			while(itr.hasNext())
			{
				if(itr.next().getCol() == loc.getCol())
				{
					itr.remove();
					break;
				}
			}
		}
        return r;
    }
}

	




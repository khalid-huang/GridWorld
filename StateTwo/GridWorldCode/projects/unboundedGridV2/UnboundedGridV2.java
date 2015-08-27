/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

//package info.gridworld.grid;

import java.util.ArrayList;
import info.gridworld.grid.Location;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Grid;
import java.util.*;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGridV2<E> extends AbstractGrid<E>
{
	private Object[][] occupantArray; //the array storing the grid elements
	private int len; //record the array len*len

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGridV2()
    {
		len = 16;
		occupantArray = new Object[len][len]; 
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
		if(loc.getRow() >= 0 && loc.getCol() >= 0)
			return true;
		return false;
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
		for(int r = 0; r < len; r++)
		{
			for(int c = 0; c < len;c++)
			{
				Location loc = new Location(r, c);
				if(get(loc) != null)
					a.add(loc);
			}
		}
		return a;
    }

    public E get(Location loc)
    {
		if(!isValid(loc))
			throw new IllegalArgumentException("Location " + loc + " is not valid");
		if(loc.getRow() >= len || loc.getCol() >= len)
			return null;
		return (E) occupantArray[loc.getRow()][loc.getCol()];
    }

    public E put(Location loc, E obj)
    {
		if(!isValid(loc))
			throw new IllegalArgumentException("Location " + loc + " is not valid");
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");
		if(loc.getCol() >= len || loc.getRow() >= len)
			resize(loc);
		E oldOccupant = get(loc);
		occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant; 
    }

    public E remove(Location loc)
    {
		if(!isValid(loc))
			throw new IllegalArgumentException("Location " + loc + "is not valid"); 
		if(loc.getRow() >= len || loc.getCol() >= len)
			return null;
		E r = get(loc);
		occupantArray[loc.getRow()][loc.getCol()] = null;
		return r;
    }

	private void resize(Location loc) 
	{
		int tempsize = len;
		while(loc.getRow() >= tempsize || loc.getCol() >= tempsize)
			tempsize *= 2;
		Object[][] newArray = new Object[tempsize][tempsize];
		for(int i = 0; i < len; i++)
			for(int j = 0; j < len; j++)
				newArray[i][j] = occupantArray[i][j];
		len = tempsize;
		occupantArray = newArray;
	}
}

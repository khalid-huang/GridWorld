/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */
//package Mypackage;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;

import java.awt.Color;
import java.util.ArrayList;


public class CrazyCrab extends Critter
{
	public static final int TARGET = 3;
	public static final int TIME = 8;
	private int speed;
	private int time;
	private int eatenNum;
    public CrazyCrab()
    {
		eatenNum = 0;
		speed = 1;
		time = 0;
        setColor(Color.PINK);
    }
    public CrazyCrab(int x) {
        eatenNum = 0;
        speed = x;
        time = 0;
        setColor(Color.PINK);
    }
    /**
     * A crab gets the actors in the three locations immediately in front, to its
     * front-right and to its front-left
     * @return a list of actors occupying these locations
     */
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(1, dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }
		System.out.println("getActor:" +  actors.size());
        return actors;
    }

    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        for (Location loc : getLocationsInDirections(speed, dirs))
            if (getGrid().get(loc) == null || getGrid().get(loc) instanceof Flower)
                locs.add(loc);

        return locs;
    }

    /**
     * If the crab critter doesn't move, it randomly turns left or right.
     */
    public void makeMove(Location loc)
    {
		System.out.println("speed: " + speed);
		System.out.println("eatenNum: " + eatenNum);
		System.out.println("time: " + time);
		if(time <= TIME && eatenNum >= TARGET)
		{
			speed++;
			eatenNum = 0;
			time = 0;

		} else if(time >= TIME && eatenNum < TARGET){
			Grid gr = getGrid();
			Location curloc = getLocation();
			removeSelfFromGrid();
			Rock rock = new Rock();
			rock.putSelfInGrid(gr, curloc);
			System.out.println("speed: " + speed);
			System.out.println("eatenNum: " + eatenNum);
			return;
		}	

		if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < 0.5)
                angle = Location.LEFT;
            else
                angle = Location.RIGHT;
            setDirection(getDirection() + angle);
        }
        else
            super.makeMove(loc);
    }
    
    /**
     * Finds the valid adjacent locations of this critter in different
     * directions.
     * @param directions - an array of directions (which are relative to the
     * current direction)
     * @return a set of valid locations that are neighbors of the current
     * location in the given directions
     */
    public ArrayList<Location> getLocationsInDirections(int step, int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Location loc = getLocation();
    
        for (int d : directions)
        {
           // Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            //if (gr.isValid(neighborLoc))
            //   locs.add(neighborLoc);
			  Location neightborLoc = getNstep(step, getLocation(), getDirection() + d);
			  if(neightborLoc != getLocation())
				  locs.add(neightborLoc);
        }
        return locs;
    }    

	/**
	 * According to the speed to get the Valid postion 
	 * @param step - mean the step the have move;
	 * @param loc -mean the loc object have move;
	 * @param direction - mean the move direction;
	 * */
	public Location getNstep(int step, Location loc, int direction) {
		Location next = loc.getAdjacentLocation(direction);
		Grid gr = getGrid();
		if(step == 0 || !gr.isValid(next) || !canOccupied(next))
			return loc;
		else
			return getNstep(step - 1, next, direction);
	}
	
	/** determine whether the loc can be occupied or not, the Rock and the Critter can't be occupied
	 * @param loc - the specified locatin
	 * */
	public boolean canOccupied(Location loc) {
		Actor a = getGrid().get(loc);
		if((a instanceof Rock) || (a instanceof Critter))
			return false; 
		return true;
	}
	
	/** eat the Bug and if the eatenNum == TARGET(before 5 times) the speed add one, and reset the time = 0; otherwise the CrazyCrab will be a Rock;
	 * */
	public void processActors(ArrayList<Actor> actors) 
	{
		time++;
		for(Actor a : actors)
		{
			if((a instanceof Bug))
			{
				a.removeSelfFromGrid();
				eatenNum++;
			} else if((a instanceof Flower)) {
				a.removeSelfFromGrid();
			}
		}
	}
	
	/* Returns the number of eaten bugs during the period */
	public int getEatenNum()
	{
	    return eatenNum;
	}
	
	/* Returns the passed time */
	public int getTime()
	{
	    return time;
	}
	
	/* Returns the speed */
	public int getSpeed()
	{
	    return speed;
	}
}

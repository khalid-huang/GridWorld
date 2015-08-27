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

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter {

	int directions[] = new int[3];
	int curpostion = 0;
	public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };

		int i = 0;

        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null) {
                actors.add(a);
				directions[curpostion] = dirs[i];
				curpostion++;
			}
			i++;
        }

        return actors;
    }

	public void processActors(ArrayList<Actor> actors) {
		//System.out.println(actors.size());
		Grid<Actor> gr = getGrid();
		int i = 0;
		for(Actor a : actors)
		{
			Location loc = a.getLocation();
			Location back = loc.getAdjacentLocation(directions[i]);
			if(gr.isValid(back))
				a.moveTo(back);
			i++;
		}
		curpostion = 0;
	}

	public void act() {
		if(getGrid() == null)
			return;
		ArrayList<Actor> actors = getActors();
		if(actors.size() == 0)
		{
			super.act();
			return;
		} else {
			processActors(actors);
		}
	}
}

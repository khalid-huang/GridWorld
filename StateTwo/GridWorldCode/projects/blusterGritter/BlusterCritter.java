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

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Color;

/**
 * A <code>ChameleonCritter</code> takes on the color of neighboring actors as
 * it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
	public static final double COLOR_FACTOR=0.05;
	private	int num;
	
	public BlusterCritter(int num) {
		this.num = num;
	}

	public ArrayList<Actor> getActors() {
		ArrayList<Actor> actorsIn = getGrid().getNeighbors(getLocation());
		ArrayList<Actor> actors = new ArrayList<Actor>();
		for(Actor a : actorsIn)
		{
			for(Actor b : getGrid().getNeighbors(a.getLocation()))
			{
				if(b != null && b != this)
					actors.add(b);
			}
		}
		//去重
		HashSet h = new HashSet(actors);
		actors.clear();
		actors.addAll(h);
		return actors;
	}

	
	public void processActors(ArrayList<Actor> actors)
    {
		//System.out.println(""+num);
		if(!isBig(actors))
		{
			Color c = getColor();
			int red = (int)(c.getRed() * (1 + COLOR_FACTOR));
			red = (red > 255 ? 255 :red); 
			int green = (int)(c.getGreen() * (1 + COLOR_FACTOR));
			green = (green > 255 ? 255 : green);
			int blue = (int)(c.getBlue() * (1 + COLOR_FACTOR));
			blue = (blue > 255 ? 255 : blue);
			setColor(new Color(red, green, blue));
			return;
		} else {
			Color c = getColor();
			int red = (int)(c.getRed() * (1 - COLOR_FACTOR));
			int green = (int)(c.getGreen() * (1 - COLOR_FACTOR));
			int blue = (int)(c.getBlue() * (1 - COLOR_FACTOR));
			setColor(new Color(red, green, blue));
			return;
		}
    }

	private boolean isBig(ArrayList<Actor> actors) {
		int numCritter = 0;
		for(Actor a : actors)
		{
			if(a instanceof Critter)
			{
				numCritter++;
			}
		}
		//System.out.println(numCritter);
		return numCritter > num;
	}
}

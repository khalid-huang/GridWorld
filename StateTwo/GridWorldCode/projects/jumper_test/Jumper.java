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
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.Bug;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Bug
{
    /* Jump two step,if it can't move two steps, just move one step,
     if can't move one step, turn */
    public void move() {
        Grid<Actor> gr = getGrid();
        if(gr == null) {
            return;
        }
        Location next = nextLocation();
        if(gr.isValid(next)) {
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
    }
    /* get the next location and if no next location just return null */
    private Location nextLocation() {
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
        Location firstStep = loc.getAdjacentLocation(getDirection());
        Location secondStep = firstStep.getAdjacentLocation(getDirection());
        //move two step
        if(gr.isValid(secondStep)) {
            Actor a = gr.get(secondStep);
            if(a == null || a instanceof Flower) {
                return secondStep;
            }
        } 
        //move one step
        if(gr.isValid(firstStep)) {
            Actor b = gr.get(firstStep);
            if(b == null || b instanceof Flower) {
                return firstStep;
            }
        }
        //stay original location
        return loc; 
    }

    public boolean canMove() {
        Grid<Actor> gr = getGrid();
        if(gr != null && nextLocation() != getLocation()) {
            return true;
        }
        return false;
    }
}

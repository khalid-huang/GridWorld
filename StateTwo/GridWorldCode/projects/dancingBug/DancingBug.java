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

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{
    private int steps;
    private int sideLength;
	private int[] dancing;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public DancingBug(int length, int[] array)
    {
        steps = 0;
        sideLength = length;
		dancing = new int[array.length];
		System.arraycopy(array, 0, dancing, 0, array.length);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
		int num = 0, i;
        if (steps < sideLength && canMove())
        {
            move();
            steps++;
        }
        else
        {
			for(i = 0; i < dancing[num]; ++i)
			{
				turn();
			}
            steps = 0;
			++num;
			if(num == dancing.length)
			{
				num = 0;
			}
        }
    }
}

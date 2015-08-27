import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.Arrays;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next;
	public Location last;
	public boolean isEnd = false;
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	public Integer stepCount = 0;
	boolean hasShown = false;//final message has been shown
	private ArrayList<Location> curPath;
	private int curIndex = -1;
	private int east = 1, north = 1, west = 1, south = 1;
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		/*debug*/
	//	System.out.println("Size:" + curPath.size() + " curpos:" + curIndex);
		boolean willMove = canMove();
		if (isEnd == true) {
		//to show step count when reach the goal		
			if (hasShown == false) {
				lightWay();
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
			return;
		} 
		getValid(getLocation());
		if(willMove)
		{
			move();
			++stepCount;
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> validLoc = new ArrayList<Location>();
		ArrayList<Location> AllValid = gr.getEmptyAdjacentLocations(loc);
		//System.out.println(AllValid.size());
		for(Location lo : AllValid)
		{
			int direction = getLocation().getDirectionToward(lo);
			if(direction == Location.SOUTH || direction == Location.NORTH || direction == Location.WEST || direction == Location.EAST)
			{
				Actor a = gr.get(lo);
				if(a == null)
					validLoc.add(lo);
			}
		}
		//get the next postion
		int size = validLoc.size();
		//System.out.println(size);
		//保存每次走的点全部可能性；
		if(size != 0) {
			next = getNext(validLoc);
			last = getLocation();
			directionAdd();
			validLoc.add(last);//原点
			crossLocation.push(validLoc);
		} else {
			ArrayList<Location> lastPath = crossLocation.pop();
			next = lastPath.get(lastPath.size() - 1); //得到路径的起点
			directionDec();
		}
		return validLoc;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		Grid<Actor> gr = getGrid();
		if(gr == null)
			return false;
		checkEnd();
		if(isEnd == true)
			return false;
		else 
			return true;
		}

	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}

	public void checkEnd() {
		Grid<Actor> gr = getGrid();
		if(gr == null)
			return;
		Location loc = getLocation();
		ArrayList<Location> Occupied = gr.getOccupiedAdjacentLocations(loc);
		for(Location lo : Occupied) {
			if(lo.getDirectionToward(loc) == Location.NORTH || lo.getDirectionToward(loc) == Location.SOUTH || lo.getDirectionToward(loc) == Location.EAST || lo.getDirectionToward(loc) == Location.WEST) {
				Actor neighbor = gr.get(lo);
				if(neighbor.getColor().equals(Color.RED))
				{
					isEnd = true;
					setDirection(getLocation().getDirectionToward(lo));
					break;
				}
			}
		}
	}

	public Location getNext(ArrayList<Location> valid)
	{
		/*
		int size = valid.size();
		int r = (int)(Math.random() * size);
		return valid.get(r);
		*/
		
		int direction = directionCal(valid);
		Location loc = getLocation().getAdjacentLocation(direction);
		return loc;
	
	}

	public void directionAdd()
	{
		int direction = last.getDirectionToward(next);
		switch(direction)
		{
			case Location.SOUTH:
				south++;
				break;
			case Location.NORTH:
				north++;
				break;
			case Location.WEST:
				west++;
				break;
			case Location.EAST:
				east++;
				break;
		}
	}

	public void directionDec() 
	{
		Location loc = getLocation();
		int direction = next.getDirectionToward(loc);
		switch(direction)
		{
			case Location.SOUTH:
				south--;
				break;
			case Location.NORTH:
				north--;
				break;
			case Location.WEST:
				west--;
				break;
			case Location.EAST:
				east--;
				break;
		}
	}
	
	public int directionCal(ArrayList<Location> valid)
	{
		int r = (int)(Math.random() * valid.size());
		Location loc = getLocation();
		int[] array = {south, north, west, east};
		Arrays.sort(array);
		//System.out.println(array[0] + " " + array[1] + " " + array[2]+" " + array[3]);
		int direction = loc.getDirectionToward(valid.get(r));
		for(Location lo : valid)
		{
			int tmpDirection = loc.getDirectionToward(lo);
			if((tmpDirection == Location.SOUTH && south >= array[2]) || 
				(tmpDirection == Location.NORTH && north >= array[2]) ||
				(tmpDirection == Location.WEST && west >= array[2]) ||
				(tmpDirection == Location.EAST && east >= array[2]))
			{
				direction = tmpDirection;
				//System.out.println("make");
				break;
			}
		}
		return direction;
	}

	public void lightWay() 
	{
		Location loc;
		Grid<Actor> gr = getGrid();
		while(!crossLocation.isEmpty()) {
			loc = crossLocation.pop().get(0);
			Actor a = gr.get(loc);
			a.setColor(Color.RED);
		}
	}

}

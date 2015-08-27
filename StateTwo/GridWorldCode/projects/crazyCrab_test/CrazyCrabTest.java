import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Ignore;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Critter;
import java.awt.Color;

public class CrazyCrabTest {
	public CrazyCrab crazy = new CrazyCrab();
	public CrazyCrab superCrazy = new CrazyCrab(9);
	ActorWorld world = new ActorWorld();
	Flower flower  = new Flower();
	@Test
	/* Test when three Bugs are put in front, left front and right front
	 * of the CrazyCrab, what will it do. Actually it will eat all three
	 * of them and its speed will increase by 1. Time passed is 1.
	 */
	public void testGetMethodsOne() {
		world.add(new Location(4, 5), crazy);
		world.add(new Location(3, 4), new Bug());
		world.add(new Location(3, 5), new Bug());
		world.add(new Location(3, 6), new Bug());
		crazy.act();
		assertEquals("EatenNum = 0, success\n", 0, crazy.getEatenNum());
		assertEquals("Time = 0, success\n", 0, crazy.getTime());
		assertEquals("Speed = 2, sucess\n", 2, crazy.getSpeed());
	}
	@Test
	/* Test when two Bugs and a Rock are put in front, left front and right front
	 * of the CrazyCrab, what will it do. Actually it will eat the two Bugs, and
	 * destroy the Rock of them. Its speed remains 1 and time passed is 1.
	 */
	public void testGetMethodsTwo() {
	    world.add(new Location(4, 5), crazy);
		world.add(new Location(3, 4), new Bug());
		world.add(new Location(3, 6), new Bug());
		world.add(new Location(3, 5), new Rock());
		crazy.act();
		assertEquals("EatenNum = 2, success\n", 2, crazy.getEatenNum());
		assertEquals("Time = 1, success\n", 1, crazy.getTime());
		assertEquals("Speed = 1, success\n",1, crazy.getSpeed());
	}
	@Test
	/* Test what's the next step of the CrazyCrab. In this case the speed is 9,
	 * so it can move by 9 steps at once.
	 */
	public void testNextStep() {
	    world.add(new Location(4, 5), superCrazy);
	    superCrazy.act();
	    Location l = superCrazy.getLocation();
	    int col = l.getCol();
	    int row = l.getRow();
	    assertTrue("True", (col == 0 || col == 9) && (row == 4));
	    superCrazy.act();
	    l = superCrazy.getLocation();
	    col = l.getCol();
	    row = l.getRow();
	    assertTrue("True", (col == 0 || col == 9) && (row == 4));
	}
	@Test
	/* Test when the CrazyBug can't eat enough Bugs, will it turn into a Rock.
	   In the first seven steps, it won't turn into a Rock. But after eight steps,
	   things are different.
	 */
	public void testChange() {
	    world.add(new Location(4, 5), crazy);
	    Grid g = crazy.getGrid();
	    for (int i = 0; i < 7; i++) {
	        crazy.act();
	    }
	    Location l = crazy.getLocation();
	    assertTrue("True", g.get(l) instanceof CrazyCrab);
	    crazy.act();
	    // The CrazyBug will be removed from the grid and its original place will be a Rock.
	    assertTrue("True", crazy.getLocation() == null);
	}
}

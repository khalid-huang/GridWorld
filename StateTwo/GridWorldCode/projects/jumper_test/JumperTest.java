import static org.junit.Assert.*;
import org.junit.Test;

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import java.awt.Color;

public class  JumperTest {
	public Jumper jumper = new Jumper();
	ActorWorld world = new ActorWorld();
	Flower flower  = new Flower();
	Actor actor = new Actor();
	Rock rock = new Rock();
	Rock rockOne = new Rock();
	Rock rockTwo = new Rock();
	@Test
	/*When the location in front of the jumper is empty, and the location two cells in front contains a flower, the jumper will occupied the location of the flower and the flower will disapper*/
	public void testCaseOneA() {
		//System.out.println("testCaseOneA");
		world.add(new Location(4, 4), jumper);
		world.add(new Location(2, 4), flower);
		jumper.act();
		Location jumperLoc = jumper.getLocation();
		assertEquals(2, jumperLoc.getRow());
		assertEquals(4, jumperLoc.getCol());
	//	flower.removeSelfFromGrid();
		jumper.removeSelfFromGrid();
	}
	
	/*When the location in front of the jumper is empty, and the location two cells in front contains a Rock, the jumper will move to the location in front of itself*/
	@Test
	public void testCaseOneB() {
		//System.out.println("testCaseOneB");
		world.add(new Location(4, 4), jumper);
		world.add(new Location(2, 4), rock);
		jumper.act();
		Location jumperLoc = jumper.getLocation();
		assertEquals(3, jumperLoc.getRow());
		assertEquals(4, jumperLoc.getCol());
		rock.removeSelfFromGrid();
		jumper.removeSelfFromGrid();
	}

	/*When the locatin two cells in front of the jumper is out of the edge, the jumper will try to stay in the location infront of the jmper*/
	@Test
	public void testCaseTwo() {
		//System.out.println("test: Two celss in front of the jumper is out of edge");
		world.add(new Location(1, 4), jumper);
		jumper.act();
		Location jumperLoc = jumper.getLocation();
		assertEquals(0, jumperLoc.getRow());
		assertEquals(4, jumperLoc.getCol());
		jumper.removeSelfFromGrid();

	}

	/*When the jumper is facing the edge of the grid, the, the  jumper will stay orginal position and turn 45 degrees of it's right*/
	@Test
	public void testCaseThree() {
		//System.out.println("test: can't move and turn");
		world.add(new Location(0, 4), jumper);
		jumper.act();
		Location jumperLoc = jumper.getLocation();
		assertEquals(0, jumperLoc.getRow());
		assertEquals(4, jumperLoc.getCol());
		int jumperDir = jumper.getDirection();
		assertEquals(jumperDir, Location.NORTHEAST);
		jumper.removeSelfFromGrid();
	}

	/*Two cells in front of the jumper is another actor(including jumper), the jumper will move to in front of itself*/
	@Test
	public void testeCaseFour() {
		//System.out.println("Test:two cell in front of the jumper is another actor");
		world.add(new Location(4, 4), jumper);
		world.add(new Location(2, 4), actor);
		jumper.act();
		Location jumperLoc = jumper.getLocation();
		assertEquals(3, jumperLoc.getRow());
		assertEquals(4, jumperLoc.getCol());
		actor.removeSelfFromGrid();
		jumper.removeSelfFromGrid();
	}	
}

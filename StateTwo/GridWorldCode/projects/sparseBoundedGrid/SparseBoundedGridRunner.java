import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Bug;

public class SparseBoundedGridRunner
{
	public static void main(String[] args)
	{
		ActorWorld world = new ActorWorld();
		world.addGridClass("SparseBoundedGrid");
		world.add(new Location(2, 2), new Bug());
		world.add(new Location(4, 4), new Rock());
		world.add(new Location(3, 2), new Flower());
		world.add(new Location(4, 3), new Actor());
		world.show();
	}
}


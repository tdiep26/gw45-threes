import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.util.ArrayList;
import java.awt.Color;

public class ZombieCritter extends Critter {

    //color initialized to green
    public ZombieCritter() {
	setColor(Color.GREEN);
    }

    // removes any non-zombie, flower, or rock actors in front of itself
    public void processActors(ArrayList<Actor> actors) {
	for (Actor a: actors) {	   
	    if (!(a instanceof Rock || a instanceof Flower || a instanceof ZombieCritter)) {
		Location l = a.getLocation();
		a.removeSelfFromGrid();
		ZombieCritter z = new ZombieCritter();
		z.putSelfInGrid(getGrid(), l);
	    }
	}
    }

    //doesn't remove itself if it can't move
    public void makeMove(Location loc)
    {
        if (!(loc == null))
            moveTo(loc);
    }	

    //only sees actors in front of itself directly or peripherally (just like crab)
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT};
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        return actors;
    }

    //stolen from crab
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
        return locs;
    }  
}

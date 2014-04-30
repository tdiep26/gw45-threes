import info.gridworld.actor.Actor; 
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;

public class KingCrab extends CrabCritter{
    public KingCrab(){
	setColor(Color.RED);
    }

    public int distanceFrom(Location loc, Location loc2){
	int x=loc.getRow();
	int y=loc.getCol();
	int x2=loc2.getRow();
	int y2=loc2.getCol();
	double distance=Math.sqrt((x - x2)*(x - x2) + (y - y2)*(y - y2)) + .5;
	return (int)Math.floor(distance);
    }

    public boolean getAway(Actor a){
	ArrayList<Location> locs=getGrid().getEmptyAdjacentLocations(a.getLocation());
	for(Location loc:locs){
	    if(distanceFrom(getLocation(), loc) > 1){
		a.moveTo(loc);
		return true;
	    }
	}
	return false;
    }

    public void processActors(ArrayList<Actor> actors){
	for (Actor a:actors){
	    if (!getAway(a)){
		a.removeSelfFromGrid();
	    }
	}
    }
}

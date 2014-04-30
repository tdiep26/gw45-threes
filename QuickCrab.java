import info.gridworld.actor.Actor; 
import info.gridworld.actor.Critter; 
import info.gridworld.grid.Grid; 
import info.gridworld.grid.Location; 
 
import java.awt.Color; 
import java.util.ArrayList; 

public class QuickCrab extends CrabCritter{ 
 
    public QuickCrab(){ 
	setColor(Color.RED); 
    } 

    public ArrayList<Location> getMoveLocations(){ 
	ArrayList<Location> EGrids = new ArrayList<Location>(); 
	Grid grid = getGrid(); 
	canMove(EGrids,getDirection() + Location.RIGHT); 
	canMove(EGrids,getDirection() + Location.LEFT); 
	if(EGrids.size()==0){
	    return super.getMoveLocations(); 
	}
	return EGrids; 
    }
 
     private void canMove(ArrayList<Location> EGrids,int direction){ 
	Grid grid = getGrid(); 
	Location xy = getLocation().getAdjacentLocation(direction); 
	if(grid.isValid(xy)&&grid.get(xy)==null){ 
		Location x2y2 = xy.getAdjacentLocation(direction); 
		if(grid.isValid(x2y2)&&grid.get(x2y2)==null){
		    EGrids.add(x2y2);
		}
	}
    }
}

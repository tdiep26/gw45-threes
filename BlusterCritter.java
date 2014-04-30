import info.gridworld.actor.*;
import info.gridworld.grid.*;

import java.util.ArrayList;

import java.awt.Color;


public class BlusterCritter extends Critter {

    private static final double DARKENING_FACTOR = 0.05;

    private int courage;

    public BlusterCritter (int c) {
	super();
	courage = c;
    }

    public ArrayList<Actor> getActors() 
    { 
	ArrayList<Actor> actors = new ArrayList<Actor>(); 

	for (int i = getLocation().getRow() - 2; i < getLocation().getRow() + 3; i++ ) 
	    for (int j = getLocation().getCol() - 2; j < getLocation().getCol() + 3; j++) 
		{ 
		    
		    if (getGrid().getNumRows() > i && i >= 0 && getGrid().getNumCols() > j && j >= 0
			&& (i != getLocation().getRow() || j != getLocation().getCol()) ) //if location is in bounds, not the blustercritter itself
			actors.add(getGrid().get(new Location(i,j))); 
		} 
	return actors; 
    } 
    
    public void processActors(ArrayList<Actor> actors) {
	int i = 0;
	for (Actor a: actors) {	   
	    if (a instanceof Critter)
		i++;
	}
	if (i > courage) {
	    Color c = getColor();
	    int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
	    int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
	    int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
	    
	    setColor(new Color(red, green, blue));
	}
	if (i < courage) {
	    Color c = getColor();
	    int red = (int) (c.getRed() * (1 + DARKENING_FACTOR));
	    if (red > 255) {red = 255;}
	    int green = (int) (c.getGreen() * (1 + DARKENING_FACTOR));
	    if (green > 255) {green = 255;}
	    int blue = (int) (c.getBlue() * (1 + DARKENING_FACTOR));
	    if (blue > 255) {blue = 255;}//don't exceed 255
	    
	    setColor(new Color(red, green, blue));
	}
    }
}
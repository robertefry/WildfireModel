package model;

import java.util.Map;

import org.joml.Vector2i;

public class Grid {
	
	private final Map<Vector2i,Cell> cells;
	
	public Grid( Map<Vector2i,Cell> cells ) {
		this.cells = cells;
	}

}

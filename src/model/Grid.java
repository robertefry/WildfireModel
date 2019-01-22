package model;

import java.util.Map;

import org.joml.Vector2f;
import org.joml.Vector2i;

public class Grid {
	
	private final Map<Vector2i,Cell> cells;
	
	public Grid( Map<Vector2i,Cell> cells ) {
		this.cells = cells;
	}
	
	public void generate(
			int width, int height,
			Map<Vector2i,Terrain> terrainmap, Map<Vector2i,Vector2f> windmap, Map<Vector2i,Float> heightmap ) {
		for (int x = 0; x < width; x++) for (int y = 0; y < height; y++) {
			Vector2i position = new Vector2i(x,y);
			Cell cell = new Cell(this,position,terrainmap,windmap,heightmap);
			cells.put(position,cell);
		}
	}

}

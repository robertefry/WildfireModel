package model;

import java.util.Map;

import org.joml.Vector2f;
import org.joml.Vector2i;

public class Cell {
	
	private final Grid grid;		// grid the cell is on
	private Terrain terrain;	
	private Vector2f wind;			// wind vector
	private float height;			// height above sea level
	private float intensity = 0; 	// intensity of fire
	
	public Cell( 
			Grid grid, Vector2i position, 
			Map<Vector2i,Terrain> terrainmap, Map<Vector2i,Vector2f> windmap, Map<Vector2i,Float> heightmap ) {
		this.grid = grid;
		this.terrain = terrainmap.get(position);
		this.wind = windmap.get(position);
		this.height = heightmap.get(position);
	}

	public final Grid getGrid() {
		return grid;
	}

	public final Terrain getTerrain() {
		return terrain;
	}

	public final Vector2f getWind() {
		return wind;
	}

	public final float getHeight() {
		return height;
	}

	public final float getIntensity() {
		return intensity;
	}

}

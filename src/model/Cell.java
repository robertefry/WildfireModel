package model;

import org.joml.Vector2i;

import model.type.Fire;
import model.type.Height;
import model.type.HeightMap;
import model.type.Terrain;
import model.type.TerrainMap;
import model.type.Wind;
import model.type.WindMap;

public class Cell implements Sequencable<Cell> {
	
	private final Grid grid;					// grid the cell is on
	private final Fire fire = new Fire(0); 		// intensity of fire
	private final Terrain terrain;	
	private final Wind wind;					// wind vector
	private final Height height;				// height above sea level
	
	public Cell( 
			Grid grid, Vector2i position, 
			TerrainMap terrainmap, WindMap windmap, HeightMap heightmap ) {
		this.grid = grid;
		this.terrain = terrainmap.get(position);
		this.wind = windmap.get(position);
		this.height = heightmap.get(position);
	}
	
	@Override
	public void next() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Cell getNext() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public final Cell getSequence(int n) {
		throw new RuntimeException("No definition for nth term in sequence"); 
	}

	public final Grid getGrid() {
		return grid;
	}

	public final Terrain getTerrain() {
		return terrain;
	}

	public final Wind getWind() {
		return wind;
	}

	public final Height getHeight() {
		return height;
	}

	public final Fire getFire() {
		return fire;
	}

}

package model;

import org.joml.Vector2i;

import model.map.HeightMap;
import model.map.TerrainMap;
import model.map.WindMap;
import model.type.Fire;
import model.type.Height;
import model.type.Terrain;
import model.type.Wind;
import robertefry.penguin.engine.target.Targetable;

public class Cell implements Sequencable<Cell>, Targetable {
	
	private final Grid grid;
	private final Vector2i position;
	private final Fire fire = new Fire(0);
	private final TerrainMap terrainmap;	
	private final WindMap windmap;
	private final HeightMap heightmap;
	
	public Cell( 
			Grid grid, Vector2i position, 
			TerrainMap terrainmap, WindMap windmap, HeightMap heightmap ) {
		this.grid = grid;
		this.position = position;
		this.terrainmap = terrainmap;
		this.windmap = windmap;
		this.heightmap = heightmap;
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
		return terrainmap.get(position);
	}

	public final Wind getWind() {
		return windmap.get(position);
	}

	public final Height getHeight() {
		return heightmap.get(position);
	}

	public final Fire getFire() {
		return fire;
	}

}

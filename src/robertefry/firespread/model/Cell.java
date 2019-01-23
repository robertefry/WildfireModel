
package robertefry.firespread.model;

import robertefry.firespread.model.map.HeightMap;
import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.WindMap;
import robertefry.firespread.model.type.FireStatus;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

public class Cell implements Sequence<Cell>, Targetable {

	private final Grid grid;
	private final TerrainMap terrainmap;
	private final WindMap windmap;
	private final HeightMap heightmap;
	
	private final FireStatus fire = new FireStatus( 0 );

	public Cell( Grid grid, TerrainMap terrainmap, WindMap windmap, HeightMap heightmap ) {
		this.grid = grid;
		this.terrainmap = terrainmap;
		this.windmap = windmap;
		this.heightmap = heightmap;
	}
	
	@Override
	public void update( Engine engine ) {
		Targetable.super.update( engine );
		// TODO Auto-generated method stub
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

	public final Grid getGrid() {
		return grid;
	}

	public final TerrainMap getTerrainMap() {
		return terrainmap;
	}

	public final WindMap getWindMap() {
		return windmap;
	}

	public final HeightMap getHeight() {
		return heightmap;
	}

	public final FireStatus getFireStatus() {
		return fire;
	}

}


package robertefry.firespread.model;

import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.WindMap;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

public class Cell implements Sequence<Cell>, Targetable {

	private final Grid grid;
	private final TerrainMap terrainmap;
	private final WindMap windmap;

	public Cell( Grid grid, TerrainMap terrainmap, WindMap windmap ) {
		this.grid = grid;
		this.terrainmap = terrainmap;
		this.windmap = windmap;
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

}

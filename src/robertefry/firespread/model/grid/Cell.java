
package robertefry.firespread.model.grid;

import robertefry.firespread.model.Sequence;
import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.WindMap;
import robertefry.firespread.model.type.Fire;

public class Cell implements Sequence<Fire> {

	private final Grid grid;
	private final TerrainMap terrainmap;
	private final WindMap windmap;
	private final Fire fire;

	public Cell( Grid grid, TerrainMap terrainmap, WindMap windmap ) {
		this.grid = grid;
		this.terrainmap = terrainmap;
		this.windmap = windmap;
		this.fire = new Fire( 0 );
	}

	public Cell( Grid grid, TerrainMap terrainmap, WindMap windmap, Fire fire ) {
		this.grid = grid;
		this.terrainmap = terrainmap;
		this.windmap = windmap;
		this.fire = fire;
	}

	@Override
	public void next() {
		fire.update( grid.getGridRefrence( this ), terrainmap, windmap );
	}

	@Override
	public Fire getNext() {
		Fire newfire = new Fire( fire );
		newfire.update( grid.getGridRefrence( this ), terrainmap, windmap );
		return newfire;
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

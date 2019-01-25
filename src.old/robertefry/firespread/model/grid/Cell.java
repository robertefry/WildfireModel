
package robertefry.firespread.model.grid;

import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.WindMap;
import robertefry.firespread.model.type.Fire;
import robertefry.penguin.engine.Engine;

public class Cell {

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

	public Fire getNext( Engine.Clock clock ) {
		Fire newfire = Fire.copy( fire );
		newfire.update( clock, grid.getGridRefrence( this ), terrainmap, windmap );
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

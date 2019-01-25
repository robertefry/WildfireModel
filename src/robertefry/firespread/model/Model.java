
package robertefry.firespread.model;

import robertefry.firespread.model.grid.Grid;
import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.WindMap;
import robertefry.penguin.engine.Engine;

public class Model extends Engine {

	@SuppressWarnings( "unused" )
	private final Grid grid;

	public Model( int width, int height, String terrainMapSource, String windMapSource ) {
		
		TerrainMap terrainmap = new TerrainMap();
		terrainmap.populateFromCSV( terrainMapSource );
		
		WindMap windmap = new WindMap();
		windmap.populateFromCSV( windMapSource );
		
		getTargetManager().add( grid = new Grid( width, height, terrainmap, windmap ) );
		
	}

}

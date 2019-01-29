
package robertefry.firespread.model;

import java.io.File;
import robertefry.firespread.model.grid.Grid;
import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.TypeMap;
import robertefry.firespread.model.type.Terrain;
import robertefry.penguin.engine.Engine;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Model {

	private static final Engine engine = new Engine();
	private static final Grid grid = new Grid();
	
	static {
		engine.getTargetManager().add( grid );
		engine.suspend();
		engine.start();
	}
	
	public static void build( String terrainDataSource ) {
		TypeMap<Terrain> terrainMap = TypeMap.populateFromCSVFile( new TerrainMap(), new File( terrainDataSource ) );
		grid.build( terrainMap );
	}
	
	public static Engine getEngine() {
		return engine;
	}

}

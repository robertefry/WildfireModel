
package robertefry.firespread.model;

import robertefry.firespread.model.grid.Grid;
import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.TypeMap;
import robertefry.firespread.model.terrain.Terrain;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Model {
	
	public static final String SOURCE_MAP_TERRAIN = "res/TerrainMap.csv";
	
	private final Grid grid = new Grid();
	
	public void makeGrid( String terrainDataSource ) {
		
		TypeMap<Terrain> terrainMap = TypeMap.populateFromCSVFile( new TerrainMap(), SOURCE_MAP_TERRAIN );
		grid.make( terrainMap );
		
	}

}

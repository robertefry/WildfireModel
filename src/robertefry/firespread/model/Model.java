
package robertefry.firespread.model;

import robertefry.firespread.model.grid.Grid;
import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.TypeMap;
import robertefry.firespread.model.terrain.Terrain;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Model implements Targetable {

	public static final String SOURCE_MAP_TERRAIN = "res/TerrainMap.csv";

	private final Grid grid = new Grid();

	public void makeGrid( String terrainDataSource ) {
		TypeMap<Terrain> terrainMap = TypeMap.populateFromCSVFile( new TerrainMap(), SOURCE_MAP_TERRAIN );
		grid.make( terrainMap );
	}

	@Override
	public void update( Engine engine ) {
		Targetable.super.update( engine );
		this.next();
	}

	public void next() {
		grid.next();
	}

	@Override
	public String toString() {
		return String.format( "Model[grid=%s]", grid );
	}

}

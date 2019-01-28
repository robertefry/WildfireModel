
package robertefry.firespread.model;

import java.io.File;
import robertefry.firespread.model.grid.Grid;
import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.TypeMap;
import robertefry.firespread.model.type.Terrain;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Model implements Targetable {

	private final Grid grid = new Grid();

	public void build( String terrainDataSource ) {
		TypeMap<Terrain> terrainMap = TypeMap.populateFromCSVFile( new TerrainMap(), new File( terrainDataSource ) );
		grid.build( terrainMap );
	}

	@Override
	public void tick( Engine engine ) {
		Targetable.super.tick( engine );
		next();
	}

	public void next() {
		grid.next();
	}

	@Override
	public String toString() {
		return String.format( "Model[grid=%s]", grid );
	}

}

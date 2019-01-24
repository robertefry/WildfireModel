
package robertefry.firespread.model.map;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVRecord;
import robertefry.firespread.model.grid.GridRefrence;
import robertefry.firespread.model.grid.GridRefrenceFactory;
import robertefry.firespread.model.type.Terrain;
import robertefry.firespread.model.type.TerrainFactory;

public class TerrainMap implements TypeMap {
	
	private final Map<GridRefrence,Terrain> map = new HashMap<>();

	@Override
	public void insert( CSVRecord record ) {
		GridRefrence position = GridRefrenceFactory.fromCSVRecord( record );
		Terrain terrain = TerrainFactory.fromCSVRecord( record );
		map.put( position, terrain );
	}

}

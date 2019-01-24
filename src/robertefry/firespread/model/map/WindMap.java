
package robertefry.firespread.model.map;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVRecord;
import robertefry.firespread.model.grid.GridRefrence;
import robertefry.firespread.model.grid.GridRefrenceFactory;
import robertefry.firespread.model.type.Wind;
import robertefry.firespread.model.type.WindFactory;

public class WindMap implements TypeMap {
	
	private final Map<GridRefrence,Wind> map = new HashMap<>();

	@Override
	public void insert( CSVRecord record ) {
		GridRefrence position = GridRefrenceFactory.fromCSVRecord( record );
		Wind terrain = WindFactory.fromCSVRecord( record );
		map.put( position, terrain );
	}

}

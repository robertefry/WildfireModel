
package robertefry.firespread.model.map;

import java.awt.Point;
import java.util.HashMap;
import org.apache.commons.csv.CSVRecord;
import robertefry.firespread.model.type.TerrainFactory;
import robertefry.firespread.util.PointFactory;
import robertefry.firespread.model.type.Terrain;

public class TerrainMap extends HashMap<Point,Terrain> implements TypeMap<Terrain> {

	private static final long serialVersionUID = 961409346387742053L;

	@Override
	public void insertCSVRecord( CSVRecord record ) {
		Point gridrefrence = PointFactory.fromCSVRecord( record );
		Terrain terrain = TerrainFactory.fromCSVRecord( record );
		this.put( gridrefrence, terrain );
	}

}

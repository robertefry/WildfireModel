
package robertefry.firespread.model.map;

import java.util.HashMap;
import org.apache.commons.csv.CSVRecord;
import org.joml.Vector2i;
import robertefry.firespread.model.terrain.Terrain;
import robertefry.firespread.model.terrain.TerrainFactory;
import robertefry.firespread.util.VectorUtil;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class TerrainMap extends HashMap<Vector2i,Terrain> implements TypeMap<Terrain> {

	private static final long serialVersionUID = 2674492103415221066L;

	@Override
	public void insertCSVRecord( CSVRecord record ) {
		final Vector2i point = VectorUtil.parseVector2iFromCSVRecord( record );
		final Terrain terrain = TerrainFactory.fromCSVRecord( record );
		this.put( point, terrain );
	}
	

}

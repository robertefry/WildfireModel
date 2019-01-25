
package robertefry.firespread.model.terrain;

import static robertefry.firespread.io.IOCSV.IO_TERRAIN_HEIGHT;
import static robertefry.firespread.io.IOCSV.IO_TERRAIN_VOLATILITY;
import org.apache.commons.csv.CSVRecord;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class TerrainFactory {

	public static Terrain fromCSVRecord( CSVRecord record ) {
		float height = Float.parseFloat( record.get( IO_TERRAIN_HEIGHT ) );
		float volatility = Float.parseFloat( record.get( IO_TERRAIN_VOLATILITY ) );
		return new Terrain( height, volatility );
	}

}

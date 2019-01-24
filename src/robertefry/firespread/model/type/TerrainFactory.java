
package robertefry.firespread.model.type;

import org.apache.commons.csv.CSVRecord;

/**
 * @author Robert E Fry
 * @date 24 Jan 2019
 */
public class TerrainFactory {

	public static Terrain fromCSVRecord( CSVRecord record ) {
		float height = Float.parseFloat( record.get( "height" ) );
		float volatility = Float.parseFloat( record.get( "volatility" ) );
		return new Terrain( height, volatility );
	}

}

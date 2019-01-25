
package robertefry.firespread.util;

import static robertefry.firespread.io.IOCSV.IO_GRID_REFRENCE;
import org.apache.commons.csv.CSVRecord;
import org.joml.Vector2i;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class VectorUtil {

	public static final Vector2i parseVector2iFromCSVRecord( CSVRecord record ) {
		return VectorUtil.parseVector2iFromString( record.get( IO_GRID_REFRENCE ) );
	}

	public static final Vector2i parseVector2iFromString( String string ) {
		final String[] coords = string.split( ":" );
		final int x = Integer.parseInt( coords[0] );
		final int y = Integer.parseInt( coords[1] );
		return new Vector2i( x, y );
	}

}

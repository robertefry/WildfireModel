
package robertefry.firespread.util;

import java.awt.Point;
import org.apache.commons.csv.CSVRecord;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class PointFactory {
	
	public static final Point fromCSVRecord( CSVRecord record ) {
		return PointFactory.fromString( record.get( "grid_refrence" ) );
	}

	public static final Point fromString( String string ) {
		final String[] coords = string.split( ":" );
		final int x = Integer.parseInt( coords[0] );
		final int y = Integer.parseInt( coords[1] );
		return new Point( x, y );
	}

}

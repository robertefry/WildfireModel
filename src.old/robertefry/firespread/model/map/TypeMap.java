
package robertefry.firespread.model.map;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVRecord;
import robertefry.firespread.io.IOCSV;

/**
 * @author Robert E Fry
 * @date 24 Jan 2019
 */
public interface TypeMap<T> extends Map<Point,T> {

	public void insertCSVRecord( CSVRecord record );
	
	default void populateFromCSV( String file ) {
		IOCSV.read( file ).forEach( record -> {
			this.insertCSVRecord( record );
		});
	}

	default Map<Point,T> getLocalRegion( Point origin, int xRange, int yRange ) {
		final Map<Point,T> map = new HashMap<>();
		for ( int xDif = -xRange; xDif <= xRange; xDif++ ) for ( int yDif = -yRange; yDif <= yRange; yDif++ ) {
			final Point point = new Point( origin.x + xDif, origin.y + yDif );
			map.put( point, get( point ) );
		}
		return map;
	}

}

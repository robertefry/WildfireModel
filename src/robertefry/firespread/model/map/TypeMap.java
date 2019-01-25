
package robertefry.firespread.model.map;

import java.util.Map;
import org.apache.commons.csv.CSVRecord;
import org.joml.Vector2i;
import robertefry.firespread.io.IOCSV;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public interface TypeMap<T> extends Map<Vector2i,T> {
	
	public void insertCSVRecord( CSVRecord record );

	public static <T> TypeMap<T> populateFromCSVFile( TypeMap<T> map, String source ) {
		IOCSV.read( source ).forEach( record -> {
			map.insertCSVRecord( record );
		} );
		return map;
	}

}

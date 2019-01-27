
package robertefry.firespread.model.map;

import java.io.File;
import java.util.Map;
import org.apache.commons.csv.CSVRecord;
import org.joml.Vector2i;
import robertefry.firespread.io.IOCSV;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public interface TypeMap<T> extends Map<Vector2i,T> {
	
	public void insertFromCSVRecord( CSVRecord record );

	public static <T> TypeMap<T> populateFromCSVFile( TypeMap<T> map, File file ) {
		IOCSV.read( file ).forEach( record -> {
			map.insertFromCSVRecord( record );
		} );
		return map;
	}

}


package robertefry.firespread.model.type;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.csv.CSVRecord;
import org.joml.Vector2f;
import robertefry.firespread.io.ParserVector2f;

/**
 * @author Robert E Fry
 * @date 24 Jan 2019
 */
public class WindFactory {

	public static Wind fromCSVRecord( CSVRecord record ) {
		Map<Integer,Vector2f> timemap = new HashMap<>();
		for ( int time = 0, counted = 0; counted < record.size() - 1; time++ ) {
			String name = "wind " + time;
			if (record.isMapped( name )) {
				Vector2f vector = ParserVector2f.fromString( record.get( name ) );
				timemap.put( time, vector );
				counted++;
			} else {
				System.err.println( "no mapping for " + name );
			}
		}
		return new Wind( timemap );
	}

}

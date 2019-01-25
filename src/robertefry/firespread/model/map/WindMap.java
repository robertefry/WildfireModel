
package robertefry.firespread.model.map;

import java.awt.Point;
import java.util.HashMap;
import org.apache.commons.csv.CSVRecord;
import robertefry.firespread.model.type.WindFactory;
import robertefry.firespread.util.PointFactory;
import robertefry.firespread.model.type.Wind;

public class WindMap extends HashMap<Point,Wind> implements TypeMap<Wind> {

	private static final long serialVersionUID = 2424094589896679454L;

	@Override
	public void insertCSVRecord( CSVRecord record ) {
		Point gridrefrence = PointFactory.fromCSVRecord( record );
		Wind wind = WindFactory.fromCSVRecord( record );
		this.put( gridrefrence, wind );
	}

}

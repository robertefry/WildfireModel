
package robertefry.firespread.model.type;

import org.apache.commons.csv.CSVRecord;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class FireFactory {
	
	public static Fire fromCSVRecord( CSVRecord record ) {
		float intensity = Float.parseFloat( record.get( "fire" ) );
		return new Fire( intensity );
	}

}


package robertefry.firespread.io;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.logging.LogFactory;
import org.joml.Vector2i;
import robertefry.firespread.model.type.Terrain;

/**
 * @author Robert E Fry
 * @date 24 Jan 2019
 */
public class IOCSV {
	
	public static final CSVFormat FORMAT = CSVFormat.EXCEL.withHeader();
	
	public static final String IO_GRID_X = "GridX";
	public static final String IO_GRID_Y = "GridY";
	public static final String IO_TERRAIN_HEIGHT = "Height";
	public static final String IO_TERRAIN_VOLATILITY = "Volatility";

	public static Iterable<CSVRecord> read( File file ) {
		Iterable<CSVRecord> records;
		try {
			records = FORMAT.parse( new FileReader( file ) );
		} catch ( IOException e ) {
			throw new RuntimeException( e.toString() );
		}
		LogFactory.getLog( IOCSV.class ).info( "read csv file from " + file.getAbsolutePath() );
		return records;
	}
	
	public static Terrain parseTerrainFromCSVRecord( CSVRecord record ) {
		float height = Float.parseFloat( record.get( IO_TERRAIN_HEIGHT ) );
		float volatility = Float.parseFloat( record.get( IO_TERRAIN_VOLATILITY ) );
		return new Terrain( height, volatility );
	}

	public static final Vector2i parseVector2iFromCSVRecord( CSVRecord record ) {
		int x = Integer.parseInt( record.get( IO_GRID_X ) );
		int y = Integer.parseInt( record.get( IO_GRID_Y ) );
		return new Vector2i( x, y );
	}

}


package robertefry.firespread.model.map;

import java.util.function.Function;
import org.joml.Vector2i;
import robertefry.firespread.model.grid.Cell;
import robertefry.firespread.model.type.Terrain;

@SuppressWarnings( "serial" )
public class CellMap extends TypeMap<Cell> {

	public CellMap() {
	}
	
	public CellMap(
		int rows, int cols,
		ImageMap heightmap, Function<Integer,Float> heightconversion,
		ImageMap volatilitymap, Function<Integer,Float> volatilityConversion
	) {
		heightmap.scale( cols, rows );
		volatilitymap.scale( cols, rows );
		for ( int x = 0; x < cols; x++ ) for ( int y = 0; y < rows; y++ ) {
			float height = heightconversion.apply( heightmap.getImage().getRGB( x, y ) );
			float volatility = volatilityConversion.apply( volatilitymap.getImage().getRGB( x, y ) );
			Vector2i position = new Vector2i( x, y );
			Cell cell = new Cell( position, new Terrain( height, volatility ) );
			this.put( position, cell );
		}
	}

}

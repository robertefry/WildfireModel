
package robertefry.firespread.model.map;

import java.util.function.Function;
import org.joml.Vector2i;
import robertefry.firespread.model.grid.Cell;
import robertefry.firespread.model.grid.Terrain;

@SuppressWarnings( "serial" )
public class CellMap extends TypeMap<Cell> {
	
	public CellMap() {
	}
	
	public CellMap(
		int rows, int cols,
		ImageMap elevationmap, Function<Integer,Float> elevationconversion,
		ImageMap flamabilitymap, Function<Integer,Float> flamabilityConversion
	) {
		elevationmap.scale( cols, rows );
		flamabilitymap.scale( cols, rows );
		for ( int x = 0; x < cols; x++ ) for ( int y = 0; y < rows; y++ ) {
			float height = elevationconversion.apply( elevationmap.getImage().getRGB( x, y ) );
			float flamability = flamabilityConversion.apply( flamabilitymap.getImage().getRGB( x, y ) );
			Vector2i point = new Vector2i( x, y );
			Cell cell = new Cell( point, new Terrain( height, flamability ) );
			this.put( point, cell );
		}
	}

}

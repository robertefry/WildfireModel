
package robertefry.firespread.model.map;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import robertefry.firespread.model.Conversion;
import robertefry.firespread.model.grid.Cell;
import robertefry.firespread.model.grid.GridSpace;
import robertefry.firespread.model.terrain.Terrain;
import robertefry.firespread.util.ImageUtil;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class CellMap {
	
	// TODO grid aspect ratio equal to that of map

	public static Map< Point, Cell > generate( int rows, int cols, ImageMap elevationMap, ImageMap flamabilityMap ) {
		GridSpace space = new GridSpace( 0, 0, cols, rows );
		BufferedImage elevationImage = ImageUtil
			.redraw( elevationMap.getImage(), space.getSize(), elevationMap.getBounds() );
		BufferedImage flamabilityImage = ImageUtil
			.redraw( flamabilityMap.getImage(), space.getSize(), flamabilityMap.getBounds() );
		Map< Point, Cell > cells = new HashMap<>();
		space.forEach( point -> {
			float elevation = Conversion.getElevationConversion( Conversion.TYPE_INT_ARGB_MONOCHROME_B )
				.apply( elevationImage.getRGB( point.x, point.y ) );
			Terrain terrain = Conversion.getTerrainConversion( Conversion.TYPE_INT_ARGB_MONOCHROME_B )
				.apply( flamabilityImage.getRGB( point.x, point.y ) );
			cells.put( point, new Cell( elevation, terrain ) );
		} );
		return cells;
	}

}
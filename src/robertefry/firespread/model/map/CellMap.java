
package robertefry.firespread.model.map;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import robertefry.firespread.model.Conversion;
import robertefry.firespread.model.grid.Cell;
import robertefry.firespread.model.grid.GridSpace;
import robertefry.firespread.model.terain.Terrain;
import robertefry.firespread.util.ImageUtil;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class CellMap {
	
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
			Terrain terrain = Conversion.getTerrainConversion( Conversion.TYPE_INT_ARGB_MONOCHROME_G )
				.apply( flamabilityImage.getRGB( point.x, point.y ) );
			terrain.setElevation( elevation );
			cells.put( point, new Cell( point, terrain ) );
		} );
		return cells;
	}
	
	public static Map< Point, Cell > generate( int rows, int cols ) {
		GridSpace space = new GridSpace( 0, 0, cols, rows );
		Map< Point, Cell > cells = new HashMap<>();
		space.forEach( point -> {
			cells.put(
				point, new Cell( point, new Terrain( 10, 0 ) )
			);
		} );
		return cells;
	}
	
}

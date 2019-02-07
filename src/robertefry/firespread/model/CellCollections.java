
package robertefry.firespread.model;

import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Set;
import robertefry.firespread.map.ImageMap;
import robertefry.firespread.math.GridSpace;
import robertefry.firespread.model.cell.Cell;
import robertefry.firespread.model.type.Elevation;
import robertefry.firespread.model.type.Terrain;
import robertefry.firespread.util.ImageUtil;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class CellCollections {

	public static Set< Cell > generate( int rows, int cols, ImageMap elevationMap, ImageMap flamabilityMap ) {
		GridSpace space = new GridSpace( 0, 0, cols, rows );
		BufferedImage elevationImage = ImageUtil
			.redraw( elevationMap.getImage(), space.getSize(), elevationMap.getGridSpace().getBounds() );
		BufferedImage flamabilityImage = ImageUtil
			.redraw( flamabilityMap.getImage(), space.getSize(), flamabilityMap.getGridSpace().getBounds() );
		Set< Cell > cells = new HashSet<>();
		space.forEach( point -> {
			Elevation elevation = Conversions.getElevationConversion()
				.apply( elevationImage.getRGB( point.x, point.y ) );
			Terrain terrain = Conversions.getTerrainConversion()
				.apply( flamabilityImage.getRGB( point.x, point.y ) );
			cells.add( new Cell( point, elevation, terrain ) );
		} );
		return cells;
	}

}

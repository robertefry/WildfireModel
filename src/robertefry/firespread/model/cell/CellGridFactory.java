
package robertefry.firespread.model.cell;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import robertefry.firespread.io.map.ImageMap;
import robertefry.firespread.model.Conversion;
import robertefry.firespread.model.terrain.Terrain;
import robertefry.firespread.util.ImageUtil;

/**
 * @author Robert E Fry
 * @date 14 Mar 2019
 */
public class CellGridFactory {
	
	public static CellGrid build( int rows, int cols ) {
		Cell[][] cells = new Cell[rows][cols];
		for ( int i = 0; i < rows; i++ ) for ( int j = 0; j < cols; j++ ) {
			cells[i][j] = new Cell( new Point( i, j ), new Terrain() );
		}
		return new CellGrid( cells );
	}
	
	public static CellGrid build( int rows, int cols, ImageMap elevationMap, ImageMap flamabilityMap ) {
		BufferedImage elevationImage = ImageUtil
			.redraw( elevationMap.getImage(), new Dimension( cols, rows ), elevationMap.getBounds() );
		BufferedImage flamabilityImage = ImageUtil
			.redraw( flamabilityMap.getImage(), new Dimension( cols, rows ), flamabilityMap.getBounds() );
		Cell[][] cells = new Cell[rows][cols];
		for ( int i = 0; i < rows; i++ ) for ( int j = 0; j < cols; j++ ) {
			float elevation = Conversion.getElevationConversion( Conversion.TYPE_INT_ARGB_MONOCHROME_B )
				.apply( elevationImage.getRGB( j, i ) );
			Terrain terrain = Conversion.getTerrainConversion( Conversion.TYPE_INT_ARGB_MONOCHROME_G )
				.apply( flamabilityImage.getRGB( j, i ) );
			terrain.setElevation( elevation );
			cells[i][j] = new Cell( new Point( i, j ), terrain );
		}
		return new CellGrid( cells );
	}
	
}

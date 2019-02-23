
package robertefry.firespread.model.grid;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.Collection;

/**
 * @author Robert E Fry
 * @date 22 Feb 2019
 */
public class GridRenderContext {
	
	private final Dimension canvas = new Dimension();
	private double gridX, gridY;
	private double cellWidth, cellHeight;
	
	public void enforceCellBounds( Dimension grid, Collection< Cell > cells ) {
		double width = canvas.getWidth() / grid.getWidth();
		double height = canvas.getHeight() / grid.getHeight();
		cellWidth = cellHeight = Math.min( width, height );
		gridX = ( canvas.getWidth() - cellWidth * grid.getWidth() ) / 2;
		gridY = ( canvas.getHeight() - cellHeight * grid.getHeight() ) / 2;
		cells.stream().parallel().forEach( cell -> {
			double x = gridX + cellWidth * cell.getPoint().getX();
			double y = gridY + cellHeight * cell.getPoint().getY();
			cell.setBounds( new Rectangle( (int)x, (int)y, (int)cellWidth, (int)cellHeight ) );
		} );
	}
	
	public void setCanvasSize( Dimension size ) {
		canvas.setSize( size );
	}
	
	public double getGridX() {
		return gridX;
	}
	
	public double getGridY() {
		return gridY;
	}
	
	public double getCellWidth() {
		return cellWidth;
	}
	
	public double getCellHeight() {
		return cellHeight;
	}
	
}


package robertefry.firespread.model.grid;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.stream.StreamSupport;
import robertefry.firespread.model.cell.CellGrid;

/**
 * @author Robert E Fry
 * @date 22 Feb 2019
 */
public class GridRenderContext {
	
	private final Dimension canvas = new Dimension();
	private double gridX, gridY;
	private double cellWidth, cellHeight;
	
	public void enforceCellBounds( CellGrid cellgrid ) {
		double width = canvas.getWidth() / cellgrid.cols();
		double height = canvas.getHeight() / cellgrid.rows();
		cellWidth = cellHeight = Math.min( width, height );
		gridX = ( canvas.getWidth() - cellWidth * cellgrid.cols() ) / 2;
		gridY = ( canvas.getHeight() - cellHeight * cellgrid.rows() ) / 2;
		StreamSupport.stream( cellgrid.spliterator(), true ).forEach( cell -> {
			double x = gridX + cellWidth * cell.getLocation().getX();
			double y = gridY + cellHeight * cell.getLocation().getY();
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

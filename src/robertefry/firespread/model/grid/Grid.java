
package robertefry.firespread.model.grid;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import robertefry.firespread.model.Model;
import robertefry.penguin.input.mouse.listener.MouseObjectAdapter;
import robertefry.penguin.target.TargetBlank;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */

// TODO control to show cell borders

public class Grid extends TargetBlank {

	public static final int AFFECT_RADIUS = 1;

	private final GridSpace space = new GridSpace();
	private final Map< Point, Cell > cells = new HashMap<>();

	private Dimension size = new Dimension( 0, 0 );

	public Grid() {
		Model.getMouse().addMouseButtonListener( new GridMouseListener() );
	}

	public void rebuildFromCellMap( Map< Point, Cell > cellset ) {
		this.space.setBounds( 0, 0, 0, 0 );
		this.cells.clear();
		cellset.forEach( ( point, cell ) -> {
			this.space.put( point );
			this.cells.put( point, cell );
		} );
		reboundCells();
	}

	@Override
	public void update() {
		cells.values().stream().forEach( Cell::update );
		cells.entrySet().stream().forEach( entry -> {
			Map< Point, Cell > localcells = new HashMap<>();
			// TODO Grid::tick - make localspace circular
			GridSpace localspace = new GridSpace(
				entry.getKey().x - AFFECT_RADIUS, entry.getKey().y - AFFECT_RADIUS, 2 * AFFECT_RADIUS + 1,
				2 * AFFECT_RADIUS + 1
			);
			space.intersection( localspace ).forEach( localpoint -> {
				Cell localcell = cells.get( localpoint );
				if ( localcell != null ) localcells.put( localpoint, localcell );
			} );
			entry.getValue().prepNext( localcells );
		} );
		cells.values().stream().forEach( cell -> {
			cell.makeNext();
		} );
	}

	@Override
	public void render() {
		cells.values().stream().forEach( Cell::render );
	}

	@Override
	public void reset() {
		super.reset();
		// TODO Grid::reset
	}

	private void reboundCells() {
		GridDrawHints hints = GridDrawHints.generate( size, space );
		cells.entrySet().stream().forEach( entry -> {
			int x = (int)( hints.gridX + entry.getKey().x * hints.cellWidth );
			int y = (int)( hints.gridY + entry.getKey().y * hints.cellHeight );
			Rectangle bounds = new Rectangle( x, y, (int)hints.cellWidth, (int)hints.cellHeight );
			entry.getValue().setBounds( bounds );
		} );
	}

	public void setSize( Dimension size ) {
		this.size = size;
		reboundCells();
	}

	private final class GridMouseListener extends MouseObjectAdapter {

		// TODO GridMouseListener - zoom & move
		// zoom & move grid from mouse movements
		@Override
		public void onButtonClick( MouseEvent e ) {
			//	TODO calculate cell on mouseclick
			//	CellSize cellsize = CellSize.generate( bounds, space );
			//	int x = (int)( ( e.getX() - bounds.x ) / cellsize.width );
			//	int y = (int)( ( e.getY() - bounds.y ) / cellsize.height );
			//	Cell cell = cells.get( new Point( x, y ) );
			//	if ( cell != null ) {
			//		cell.cycle();
			//		Model.getEngine().forceRender();
			//	}
		}

	}

	private static final class GridDrawHints {

		public float gridX, gridY;
		public float cellWidth, cellHeight;

		public static final GridDrawHints generate( Dimension size, GridSpace space ) {
			GridDrawHints hints = new GridDrawHints();

			float segWidth = (float)size.width / (float)space.getWidth();
			float segHeight = (float)size.height / (float)space.getHeight();
			hints.cellWidth = hints.cellHeight = Math.min( segWidth, segHeight );

			float drawWidth = hints.cellWidth * space.getWidth();
			float drawHeight = hints.cellHeight * space.getHeight();
			hints.gridX = (float)( size.width - drawWidth ) / 2;
			hints.gridY = (float)( size.height - drawHeight ) / 2;

			return hints;
		}

	}

}

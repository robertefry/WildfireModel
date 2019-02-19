
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

// TODO control to show cell borders

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */

public class Grid extends TargetBlank {

	public static final int AFFECT_RADIUS = 1;

	private final GridSpace space = new GridSpace();
	private final Map< Point, Cell > cells = new HashMap<>();

	private final Dimension size = new Dimension( 0, 0 );
	private final GridDrawHints drawhints = new GridDrawHints();

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
		redefineBounds();
	}

	@Override
	public void update() {
		cells.values().parallelStream().forEach( Cell::update );
		cells.entrySet().parallelStream().forEach( entry -> {
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
		cells.values().parallelStream().forEach( cell -> {
			cell.makeNext();
		} );
	}

	@Override
	public void render() {
		cells.values().parallelStream().forEach( Cell::render );
	}

	@Override
	public void reset() {
		super.reset();
		// TODO Grid::reset
	}

	private void redefineBounds() {
		drawhints.update( size, space );
		cells.entrySet().parallelStream().forEach( entry -> {
			int x = (int)( drawhints.getGridX() + entry.getKey().x * drawhints.getCellWidth() );
			int y = (int)( drawhints.getGridY() + entry.getKey().y * drawhints.getCellHeight() );
			Rectangle bounds = new Rectangle( x, y, (int)drawhints.getCellWidth(), (int)drawhints.getCellHeight() );
			entry.getValue().setBounds( bounds );
		} );
	}

	public void setSize( Dimension size ) {
		this.size.setSize( size );
		redefineBounds();
	}

	private final class GridMouseListener extends MouseObjectAdapter {

		// TODO GridMouseListener - zoom & move
		// zoom & move grid from mouse movements
		@Override
		public void onButtonClick( MouseEvent e ) {
			// TODO calculate cell on mouseclick
			int x = (int)( ( e.getX() - drawhints.getGridX() ) / drawhints.getCellWidth() );
			int y = (int)( ( e.getY() - drawhints.getGridY() ) / drawhints.getCellHeight() );
			Cell cell = cells.get( new Point( x, y ) );
			if ( cell != null ) {
				cell.cycle();
				Model.getEngine().forceRender();
			}
		}

	}

}

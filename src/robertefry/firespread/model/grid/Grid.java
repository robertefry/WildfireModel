
package robertefry.firespread.model.grid;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import robertefry.firespread.model.Model;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Target;
import robertefry.penguin.input.mouse.listener.MouseObjectAdapter;

public class Grid extends Target {

	public static final int AFFECT_RADIUS = 1;

	private final GridSpace space = new GridSpace();
	private final Map< Point, Cell > cells = new HashMap<>();

	private Rectangle drawspace = new Rectangle( 0, 0, 0, 0 );
	private Dimension cellsize = new Dimension( 0, 0 );

	public Grid() {
		Model.getMouse().addMouseButtonListener( new GridMouseListener() );
	}

	public void rebuildFromCellMap( Map< Point, Cell > cellset ) {
		removeSubTargets( cells.values() );
		this.space.setBounds( 0, 0, 0, 0 );
		this.cells.clear();
		cellset.forEach( ( point, cell ) -> {
			this.space.put( point );
			this.cells.put( point, cell );
		} );
		addSubTargets( cells.values() );
		updateCellBounds();
	}

	@Override
	public void tick( Engine engine ) {
		super.tick( engine );
		Map< Point, Cell > localcells = new HashMap<>();
		cells.forEach( ( point, cell ) -> {
			localcells.clear();
			// TODO make localspace circular
			GridSpace localspace = new GridSpace(
				point.x - AFFECT_RADIUS, point.y - AFFECT_RADIUS, 2 * AFFECT_RADIUS + 1, 2 * AFFECT_RADIUS + 1
			);
			space.intersection( localspace ).forEach( localpoint -> {
				Cell localcell = cells.get( localpoint );
				if ( localcell != null ) localcells.put( localpoint, localcell );
			} );
			cell.prepNext( localcells );
		} );
		cells.values().forEach( cell -> {
			cell.makeNext();
		} );
	}

	@Override
	public void reset() {
		super.reset();
		// TODO reset
	}

	public void updateCellBounds() {
		if ( ( space.getWidth() | space.getHeight() ) == 0 ) return;
		cellsize.width = drawspace.width / space.getWidth();
		cellsize.height = drawspace.height / space.getHeight();
		cells.forEach( ( location, cell ) -> {
			int x = cellsize.width * location.x;
			int y = cellsize.height * location.y;
			cell.setDrawspace( new Rectangle( drawspace.x + x, drawspace.y + y, cellsize.width, cellsize.height ) );
		} );
	}

	public void setBounds( Rectangle drawspace ) {
		this.drawspace = drawspace;
		updateCellBounds();
	}

	public void setBounds( Dimension dimension ) {
		int size = Math.min( dimension.width, dimension.height );
		int x = (int)( ( dimension.width - size ) / 2 );
		int y = (int)( ( dimension.height - size ) / 2 );
		setBounds( new Rectangle( x, y, size, size ) );
	}

	public final class GridMouseListener extends MouseObjectAdapter {

		// TODO zoom & move grid from mouse movements
		@Override
		public void onButtonClick( MouseEvent e ) {
			int x = (int)( ( e.getX() - drawspace.x ) / (float)cellsize.width );
			int y = (int)( ( e.getY() - drawspace.y ) / (float)cellsize.height );
			Cell cell = cells.get( new Point( x, y ) );
			if ( cell != null ) {
				cell.cycleState();
				Model.getEngine().forceRender();
			}
		}

	}

}


package robertefry.firespread.model.grid;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import robertefry.firespread.math.GridSpace;
import robertefry.firespread.model.Model;
import robertefry.firespread.model.cell.Cell;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.TargetAdapter;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class Grid implements TargetAdapter {

	public static final int LOCAL_RADIUS = 1;

	private final GridSpace space = new GridSpace();
	private final Map< Point, Cell > cells = new HashMap<>();

	private final MouseListener mouseListener = new GridMouseListener();
	private Rectangle drawspace = new Rectangle( 0, 0, 0, 0 );
	private Dimension cellsize = new Dimension( 0, 0 );

	public void rebuild( Map< Point, Cell > cells ) {
		this.space.setBounds( 0, 0, 0, 0 );
		this.cells.clear();
		cells.forEach( ( point, cell ) -> {
			this.space.put( point );
			this.cells.put( point, cell );
		} );
		updateCellBounds();
	}

	@Override
	public void render( Engine engine ) {
		TargetAdapter.super.render( engine );
		cells.values().forEach( cell -> {
			cell.render( engine );
		} );
	}

	@Override
	public void tick( Engine engine ) {
		TargetAdapter.super.tick( engine );
		cells.forEach( ( point, cell ) -> {
			cell.prepNext( cells );
		} );
		cells.values().forEach( cell -> {
			cell.makeNext();
		} );
	}

	public void updateCellBounds() {
		if ( space.getWidth() * space.getHeight() == 0 ) return;
		cellsize.width = drawspace.width / space.getWidth();
		cellsize.height = drawspace.height / space.getHeight();
		cells.values().forEach( cell -> {
			int x = cellsize.width * cell.getLocation().x;
			int y = cellsize.height * cell.getLocation().y;
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

	public MouseListener getMouseListener() {
		return mouseListener;
	}

	public final class GridMouseListener extends MouseAdapter {
		// TODO zoom & move grid from mouse movements
		@Override
		public void mouseClicked( MouseEvent e ) {
			int x = (int)( ( e.getX() - drawspace.x ) / (float)cellsize.width );
			int y = (int)( ( e.getY() - drawspace.y ) / (float)cellsize.height );
			cells.get( new Point( x, y ) ).getTerrain().cycleState();
			Model.getEngine().forceRender();
		}
	}

}


package robertefry.firespread.model.grid;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.Model;
import robertefry.firespread.util.GraphicUtil;
import robertefry.penguin.input.mouse.listener.MouseObjectAdapter;
import robertefry.penguin.target.TargetBlank;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Grid extends TargetBlank {

	public static final int AFFECT_RADIUS = 1;

	private final GridSpace space = new GridSpace();
	private final Map< Point, Cell > cells = new HashMap<>();

	private Rectangle bounds = new Rectangle( 0, 0, 0, 0 );

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
	}

	@Override
	public void update() {
		super.update();
		Map< Point, Cell > localcells = new HashMap<>();
		cells.values().forEach( Cell::update );
		cells.forEach( ( point, cell ) -> {
			localcells.clear();
			// TODO Grid::tick - make localspace circular
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
	public void render() {
		super.render();
		Graphics g = Renderer.getGraphics();
		CellSize cellsize = CellSize.generate( bounds, space );
		cells.forEach( ( point, cell ) -> {
			int x = (int)( bounds.x + point.x * cellsize.width );
			int y = (int)( bounds.y + point.y * cellsize.height );
			Rectangle drawspace = new Rectangle( x, y, (int)cellsize.width, (int)cellsize.height );
			GraphicUtil.drawRect( g, drawspace, Color.DARK_GRAY );
		} );
	}

	@Override
	public void reset() {
		super.reset();
		// TODO Grid::reset
	}

	public void fitBounds( Dimension dimension ) {
		int size = Math.min( dimension.width, dimension.height );
		int x = (int)( ( dimension.width - size ) / 2 );
		int y = (int)( ( dimension.height - size ) / 2 );
		setBounds( new Rectangle( x, y, size, size ) );
	}

	public void setBounds( Rectangle bounds ) {
		this.bounds = bounds;
	}

	private final class GridMouseListener extends MouseObjectAdapter {

		// TODO GridMouseListener - zoom & move
		// zoom & move grid from mouse movements
		@Override
		public void onButtonClick( MouseEvent e ) {
			CellSize cellsize = CellSize.generate( bounds, space );
			int x = (int)( ( e.getX() - bounds.x ) / cellsize.width );
			int y = (int)( ( e.getY() - bounds.y ) / cellsize.height );
			Cell cell = cells.get( new Point( x, y ) );
			if ( cell != null ) {
				cell.cycle();
				Model.getEngine().forceRender();
			}
		}

	}

	private static final class CellSize {

		public final float width, height;

		public CellSize( float width, float height ) {
			this.width = width;
			this.height = height;
		}

		public static final CellSize generate( Rectangle bounds, GridSpace space ) {
			float width = (float)bounds.width / (float)space.getWidth();
			float height = (float)bounds.height / (float)space.getHeight();
			return new CellSize( width, height );
		}

	}

}

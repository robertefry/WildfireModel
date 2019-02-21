
package robertefry.firespread.model.grid;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.Model;
import robertefry.firespread.ui.MapEditHints;
import robertefry.penguin.input.mouse.listener.MouseObjectAdapter;
import robertefry.penguin.input.mouse.listener.MouseObjectListener;
import robertefry.penguin.target.TargetBlank;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Grid extends TargetBlank {

	public static final double AFFECT_RADIUS = 1;

	// TODO remove gridspace
	private final GridSpace space = new GridSpace();
	private final Map< Point, Cell > cellmap = new HashMap<>();

	private final Dimension size = new Dimension( 0, 0 );
	private final GridDrawHints gridDrawhints = new GridDrawHints();

	private final MouseObjectListener gridMouseListener = new GridMouseListener();
	private final ComponentListener gridComponentListener = new GridComponentListener();

	public Grid() {
		Model.getMouse().addMouseButtonListener( gridMouseListener );
		Renderer.getComponent().addComponentListener( gridComponentListener );
	}

	public void reconstruct( Map< Point, Cell > cellmap ) {
		this.space.setBounds( 0, 0, 0, 0 );
		this.cellmap.clear();
		cellmap.entrySet().forEach( entry -> {
			this.space.put( entry.getKey() );
			this.cellmap.put( entry.getKey(), entry.getValue() );
		} );
		redefineBounds();
	}

	private void redefineBounds() {
		gridDrawhints.update( size, space );
		cellmap.forEach( ( point, cell ) -> {
			int x = (int)( gridDrawhints.getGridX() + point.x * gridDrawhints.getCellWidth() );
			int y = (int)( gridDrawhints.getGridY() + point.y * gridDrawhints.getCellHeight() );
			Rectangle bounds = new Rectangle(
				x, y, (int)gridDrawhints.getCellWidth(), (int)gridDrawhints.getCellHeight()
			);
			cell.setBounds( bounds );
		} );
	}

	@Override
	public void update() {
		cellmap.entrySet().stream().parallel()
			.filter( entry -> entry.getValue().getTerrain().isBurning() )
			.forEach( entry -> {
				cellmap.entrySet().stream().parallel().filter( local -> {
					Point p1 = entry.getKey(), p2 = local.getKey();
					return Math.hypot( p1.x - p2.x, p1.y - p2.y ) <= AFFECT_RADIUS;
				} ).forEach( local -> {
					entry.trySpread( local );
				} );
			} );
		cellmap.values().stream().parallel().forEach( Cell::makeNext );
	};

	@Override
	public void render() {
		cellmap.values().forEach( Cell::render );
	};

	// TODO zoom & move grid from mouse movements
	private final class GridMouseListener extends MouseObjectAdapter {

		private void processMouseEventOnCell( MouseEvent e ) {
			int x = (int)( ( e.getX() - gridDrawhints.getGridX() ) / gridDrawhints.getCellWidth() );
			int y = (int)( ( e.getY() - gridDrawhints.getGridY() ) / gridDrawhints.getCellHeight() );
			Cell cell = cellmap.get( new Point( x, y ) );
			if ( cell != null ) {
				cell.setState( MapEditHints.getEditSelection() );
				Model.getEngine().forceRender();
			}
		}

		@Override
		public void onButtonPress( MouseEvent e ) {
			processMouseEventOnCell( e );
		}

		@Override
		public void onButtonClick( MouseEvent e ) {
			processMouseEventOnCell( e );
		}

		@Override
		public void onMouseDrag( MouseEvent e ) {
			processMouseEventOnCell( e );
		}

	}

	private final class GridComponentListener extends ComponentAdapter {

		@Override
		public void componentResized( ComponentEvent e ) {
			size.setSize( Renderer.getComponent().getSize() );
			redefineBounds();
			Model.getEngine().forceRender();
		}

	}

}

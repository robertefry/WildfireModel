
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
import java.util.stream.Collectors;
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

	private Map< Point, Cell > getLocalCells( Point point ) {
		return cellmap.entrySet().stream().filter( entry -> {
			Point local = entry.getKey();
			return Math.hypot( local.x - point.x, local.y - point.y ) <= AFFECT_RADIUS;
		} ).collect( Collectors.toMap( Map.Entry::getKey, Map.Entry::getValue ) );
	}

	@Override
	public void update() {
		// generate list of burning cells
		// generate list of cells neibouring burning cell
		// try to burn the neibouring cell
		cellmap.entrySet().stream()
			.filter( entry -> entry.getValue().getTerrain().isBurning() )
			.forEach( entry -> {
				getLocalCells( entry.getKey() ).entrySet().forEach( local -> {
					entry.trySpread( local );
				} );
			} );
		//		cellmap.forEach( ( point, cell ) -> {
		//			Map< Point, Cell > localcells = getLocalCells( point );
		//			cell.prepNext( localcells );
		//		} );
		cellmap.values().stream().parallel().forEach( Cell::makeNext );
	};

	@Override
	public void render() {
		cellmap.values().forEach( Cell::render );
	};

	private final class GridMouseListener extends MouseObjectAdapter {

		// TODO GridMouseListener - zoom & move
		// zoom & move grid from mouse movements
		@Override
		public void onButtonClick( MouseEvent e ) {
			int x = (int)( ( e.getX() - gridDrawhints.getGridX() ) / gridDrawhints.getCellWidth() );
			int y = (int)( ( e.getY() - gridDrawhints.getGridY() ) / gridDrawhints.getCellHeight() );
			Cell cell = cellmap.get( new Point( x, y ) );
			if ( cell != null ) {
				cell.setState( MapEditHints.getEditSelection() );
				Model.getEngine().forceRender();
			}
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

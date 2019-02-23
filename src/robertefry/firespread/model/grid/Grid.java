
package robertefry.firespread.model.grid;

import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.javatuples.Pair;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.Model;
import robertefry.firespread.model.terain.TerrainState;
import robertefry.firespread.ui.MapEditHints;
import robertefry.firespread.util.MathUtil;
import robertefry.penguin.input.mouse.listener.MouseObjectAdapter;
import robertefry.penguin.input.mouse.listener.MouseObjectListener;
import robertefry.penguin.target.TargetBlank;

/**
 * @author Robert E Fry
 * @date 22 Feb 2019
 */
public class Grid extends TargetBlank {
	
	public static final double AFFECT_RADIUS = 1.2;
	
	private final Map< Point, Cell > cellmap = new ConcurrentHashMap<>();
	private final GridRenderContext context = new GridRenderContext();
	private final GridShape shape = new GridShape();
	
	private final MouseObjectListener gridMouseObjectListener = new GridMouseListener();
	private final ComponentListener gridComponentListener = new GridComponentListener();
	
	public Grid() {
		Model.getMouse().addMouseObjectListener( gridMouseObjectListener );
		Renderer.getComponent().addComponentListener( gridComponentListener );
	}
	
	public void reconstruct( Map< Point, Cell > cellmap ) {
		this.cellmap.clear();
		this.shape.setBounds( 0, 0, 0, 0 );
		cellmap.entrySet().stream().parallel().forEach( entry -> {
			this.cellmap.put( entry.getKey(), entry.getValue() );
			this.shape.include( entry.getKey() );
		} );
		context.enforceCellBounds( shape.getSize(), cellmap.values() );
	}
	
	public Set< Cell > getLocalCells( Cell cell ) {
		return cellmap.entrySet().stream().parallel()
			.filter( local -> {
				Point p1 = cell.getPoint(), p2 = local.getKey();
				return Math.hypot( p1.x - p2.x, p1.y - p2.y ) <= AFFECT_RADIUS;
			} )
			.map( Map.Entry::getValue )
			.collect( Collectors.toSet() );
	}
	
	@Override
	public void update() {
		// TODO cache burning cells
		cellmap.values().stream().parallel()
			.filter( cell -> cell.getTerrain().isBurning() )
			.map( cell -> new Pair<>( cell, getLocalCells( cell ) ) )
			.forEach( pair -> {
				pair.getValue1().stream().parallel().forEach( dest -> {
					pair.getValue0().trySpread( dest );
				} );
			} );
		cellmap.values().stream().parallel().forEach( Cell::update );
	};
	
	@Override
	public void render() {
		cellmap.values().forEach( Cell::render );
	};
	
	private final class GridMouseListener extends MouseObjectAdapter {
		
		private void processMouseEvent( MouseEvent e ) {
			Cell cell = getCell( e.getPoint() );
			if ( cell != null ) {
				cell.getTerrain().setState( MapEditHints.getEditSelection() );
				Model.getEngine().forceRender();
			}
		}
		
		private void processWheelEvent( MouseWheelEvent e ) {
			Cell cell = getCell( e.getPoint() );
			if ( cell != null ) {
				int index = (int)MathUtil.clamp(
					cell.getTerrain().getState().ordinal() + e.getWheelRotation(), 0, TerrainState.values().length
				);
				cell.getTerrain().setState( TerrainState.values()[index] );
				Model.getEngine().forceRender();
			}
		}
		
		private Cell getCell( Point p ) {
			int x = (int)( ( p.x - context.getGridX() ) / context.getCellWidth() );
			int y = (int)( ( p.y - context.getGridY() ) / context.getCellHeight() );
			return cellmap.get( new Point( x, y ) );
		}
		
		@Override
		public void onButtonPress( MouseEvent e ) {
			processMouseEvent( e );
		}
		
		@Override
		public void onMouseDrag( MouseEvent e ) {
			processMouseEvent( e );
		}
		
		@Override
		public void onWheelAction( MouseWheelEvent e ) {
			processWheelEvent( e );
		}
		
	}
	
	private final class GridComponentListener extends ComponentAdapter {
		
		@Override
		public void componentResized( ComponentEvent e ) {
			context.setCanvasSize( Renderer.getComponent().getSize() );
			context.enforceCellBounds( shape.getSize(), cellmap.values() );
			Model.getEngine().forceRender();
		}
		
	}
	
}

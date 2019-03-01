
package robertefry.firespread.model.grid;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import robertefry.firespread.cache.Cache;
import robertefry.firespread.cache.ConcurrentHashCache;
import robertefry.firespread.model.Model;
import robertefry.firespread.model.Spread;
import robertefry.firespread.model.terain.TerrainState;
import robertefry.firespread.util.MathUtil;
import robertefry.penguin.input.mouse.listener.MouseObjectAdapter;
import robertefry.penguin.input.mouse.listener.MouseObjectListener;
import robertefry.penguin.target.TargetBlank;

/**
 * @author Robert E Fry
 * @date 22 Feb 2019
 */
public class Grid extends TargetBlank {
	
	private final Map< Point, Cell > cellmap = new ConcurrentHashMap<>();
	private final Cache< Cell, Set< Cell > > localcells = new ConcurrentHashCache<>();
	
	private final GridShape gridshape = new GridShape();
	private final GridRenderContext context = new GridRenderContext();
	
	private final MouseObjectListener gridMouseObjectListener = new GridMouseListener();
	private final ComponentListener gridComponentListener = new GridComponentListener();
	
	public Grid() {
		Model.getMouse().addMouseObjectListener( gridMouseObjectListener );
		Model.getEngine().getRenderer().getComponent().addComponentListener( gridComponentListener );
	}
	
	public void reconstruct( Map< Point, Cell > cellmap ) {
		this.localcells.clear();
		this.cellmap.clear();
		this.gridshape.setBounds( 0, 0, 0, 0 );
		cellmap.entrySet().stream().parallel().forEach( entry -> {
			Point point = entry.getKey();
			Cell cell = entry.getValue();
			this.cellmap.put( point, cell );
			this.gridshape.include( point );
		} );
		context.enforceCellBounds( gridshape.getSize(), cellmap.values() );
	}
	
	public Set< Cell > getLocalCells( Collection< Cell > cellset, Cell cell ) {
		return cellset.stream().parallel()
			.filter( local -> {
				Point p1 = cell.getPoint(), p2 = local.getPoint();
				return Math.hypot( p1.x - p2.x, p1.y - p2.y ) <= Spread.GRID_AFFECT_RADIUS;
			} )
			.collect( Collectors.toSet() );
	}
	
	@Override
	public void update() {
		cellmap.values().stream().parallel()
			.filter( cell -> cell.getTerrain().isBurning() )
			.forEach( cell -> {
				localcells.retrieve( cell, () -> getLocalCells( cellmap.values(), cell ) )
					.forEach( local -> {
						cell.trySpread( local );
					} );
			} );
		cellmap.values().stream().parallel().forEach( Cell::update );
	};
	
	@Override
	public void render( Graphics g ) {
		cellmap.values().forEach( cell -> cell.render( g ) );
	};
	
	private final class GridMouseListener extends MouseObjectAdapter {
		
		private void processMouseEvent( MouseEvent e ) {
			Cell cell = getCell( e.getPoint() );
			if ( cell != null ) {
				cell.getTerrain().setState( GridEditOptions.getSelection() );
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
			context.setCanvasSize( Model.getEngine().getRenderer().getComponent().getSize() );
			context.enforceCellBounds( gridshape.getSize(), cellmap.values() );
			Model.getEngine().forceRender();
		}
		
	}
	
}

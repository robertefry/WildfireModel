
package robertefry.firespread.model.grid;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import robertefry.firespread.model.Model;
import robertefry.firespread.model.spread.Spread;
import robertefry.firespread.model.terain.TerrainState;
import robertefry.firespread.util.MathUtil;
import robertefry.jtoolkit.cache.MapCache;
import robertefry.jtoolkit.cache.SetCache;
import robertefry.penguin.input.mouse.listener.MouseObjectAdapter;
import robertefry.penguin.input.mouse.listener.MouseObjectListener;
import robertefry.penguin.target.TargetBlank;

/**
 * @author Robert E Fry
 * @date 22 Feb 2019
 */
public class Grid extends TargetBlank {
	
	private final Map< Point, Cell > cellmap = new ConcurrentHashMap<>();
	
	private final SetCache< Cell > cacheBurning = new SetCache<>( new HashSet<>() );
	private final MapCache< Cell, Set< Cell > > cacheLocal = new MapCache<>( new HashMap<>() );
	
	private final GridShape gridshape = new GridShape();
	private final GridRenderContext context = new GridRenderContext();
	
	private final MouseObjectListener gridMouseObjectListener = new GridMouseListener();
	private final ComponentListener gridComponentListener = new GridComponentListener();
	
	public Grid() {
		Model.getMouse().addMouseObjectListener( gridMouseObjectListener );
		Model.getEngine().getRenderer().getComponent().addComponentListener( gridComponentListener );
	}
	
	public void reconstruct( Map< Point, Cell > cellmap ) {
		this.cacheBurning.clear();
		this.cacheLocal.clear();
		this.cellmap.clear();
		this.gridshape.setBounds( 0, 0, 0, 0 );
		cellmap.entrySet().stream().parallel().forEach( entry -> {
			Point point = entry.getKey();
			Cell cell = entry.getValue();
			this.cellmap.put( point, cell );
			if ( cell.getTerrain().isBurning() ) this.cacheBurning.cache( cell );
			this.gridshape.include( point );
		} );
		context.enforceCellBounds( gridshape.getSize(), cellmap.values() );
	}
	
	public Set< Cell > getLocalCells( Cell cell ) {
		Point point = cell.getPoint();
		GridShape shape = new GridShape(
			(int)( point.x - Spread.GRID_AFFECT_RADIUS ), (int)( point.y - Spread.GRID_AFFECT_RADIUS ),
			(int)( Spread.GRID_AFFECT_RADIUS * 2 + 1 ), (int)( Spread.GRID_AFFECT_RADIUS * 2 + 1 )
		);
		return shape.stream().map( cellmap::get ).filter( Objects::nonNull ).collect( Collectors.toSet() );
	}
	
	@Override
	public void update() {
		
		Set< Cell > cellsToBurn = new HashSet<>();
		cacheBurning.stream().parallel()
			.forEach( cell -> {
				cacheLocal.retrieve( cell, () -> getLocalCells( cell ) )
					.forEach( local -> {
						boolean ignite = cell.trySpread( local );
						if ( ignite ) cellsToBurn.add( local );
					} );
			} );
		cellsToBurn.forEach( cacheBurning::cache );
		
		cacheBurning.stream().parallel().forEach( Cell::update );
		
		cacheBurning.clean( ( cell ) -> !cell.getTerrain().isBurning() );
		
	};
	
	@Override
	public void render( Graphics g ) {
		cellmap.values().forEach( cell -> cell.render( g ) );
	};
	
	private final class GridMouseListener extends MouseObjectAdapter {
		
		private void process( Cell cell, TerrainState state ) {
			cell.getTerrain().setState( state );
			if ( state.isBurning() ) {
				cacheBurning.cache( cell );
			} else {
				cacheBurning.remove( cell );
			}
			Model.getEngine().forceRender();
		}
		
		private void processMouseEvent( MouseEvent e ) {
			Cell cell = getCell( e.getPoint() );
			if ( cell != null ) {
				process( cell, GridEditOptions.getSelection() );
			}
		}
		
		private void processWheelEvent( MouseWheelEvent e ) {
			Cell cell = getCell( e.getPoint() );
			if ( cell != null ) {
				int index = (int)MathUtil.clamp(
					cell.getTerrain().getState().ordinal() + e.getWheelRotation(), 0, TerrainState.values().length
				);
				process( cell, TerrainState.values()[index] );
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

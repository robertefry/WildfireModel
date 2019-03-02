
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
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
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
	
	private final GridSpace gridshape = new GridSpace();
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
		GridSpace shape = new GridSpace(
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
					.stream()
					.filter( Objects::nonNull )
					.forEach( local -> {
						boolean ignite = cell.trySpread( local );
						if ( ignite ) cellsToBurn.add( local );
					} );
			} );
		cellsToBurn.forEach( cacheBurning::cache );
		
		cacheBurning.stream().parallel().forEach( Cell::update );
		
		cacheBurning.clean( cell -> !cell.getTerrain().isBurning() );
		cacheLocal.clean( elem -> !elem.getKey().getTerrain().isBurning() );
		
	};
	
	@Override
	public void render( Graphics g ) {
		cellmap.values().forEach( cell -> cell.render( g ) );
	};
	
	private final class GridMouseListener extends MouseObjectAdapter {
		
		private final Thread thread = new Thread( new Run() );
		private final Queue< Runnable > queue = new ConcurrentLinkedQueue<>();
		
		public GridMouseListener() {
			thread.setDaemon( true );
			thread.start();
		}
		
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
			getCells( e.getPoint() ).stream().filter( Objects::nonNull ).forEach( cell -> {
				process( cell, GridEditOptions.getSelection() );
			} );
		}
		
		private void processWheelEvent( MouseWheelEvent e ) {
			getCells( e.getPoint() ).stream().filter( Objects::nonNull ).forEach( cell -> {
				int index = (int)MathUtil.clamp(
					cell.getTerrain().getState().ordinal() + e.getWheelRotation(), 0, TerrainState.values().length
				);
				process( cell, TerrainState.values()[index] );
			} );
		}
		
		private Set< Cell > getCells( Point p ) {
			double x = ( p.x - context.getGridX() ) / context.getCellWidth();
			double y = ( p.y - context.getGridY() ) / context.getCellHeight();
			GridSpace space = new GridSpace(
				(int)Math.floor( x - GridEditOptions.getPenSize() / 2 + 1 ), (int)Math.floor( y - GridEditOptions.getPenSize() / 2 + 1 ),
				(int)Math.ceil( 2 * GridEditOptions.getPenSize() / 2 ), (int)Math.ceil( 2 * GridEditOptions.getPenSize() / 2 )
			);
			return space.stream().map( cellmap::get ).collect( Collectors.toSet() );
		}
		
		@Override
		public void onButtonPress( MouseEvent e ) {
			queue.offer( () -> processMouseEvent( e ) );
		}
		
		@Override
		public void onMouseDrag( MouseEvent e ) {
			queue.offer( () -> processMouseEvent( e ) );
		}
		
		@Override
		public void onWheelAction( MouseWheelEvent e ) {
			queue.offer( () -> processWheelEvent( e ) );
		}
		
		private final class Run implements Runnable {
			
			@Override
			public void run() {
				while ( true ) {
					Runnable task = queue.poll();
					if ( task != null ) task.run();
				}
			};
			
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

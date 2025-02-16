
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
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import robertefry.firespread.model.Model;
import robertefry.firespread.model.cell.Cell;
import robertefry.firespread.model.cell.CellGrid;
import robertefry.firespread.model.spread.Spread;
import robertefry.firespread.model.terrain.TerrainState;
import robertefry.firespread.util.MathUtil;
import robertefry.penguin.input.mouse.listener.MouseObjectAdapter;
import robertefry.penguin.input.mouse.listener.MouseObjectListener;
import robertefry.penguin.target.TargetBlank;

/**
 * @author Robert E Fry
 * @date 14 Mar 2019
 */
public class Grid extends TargetBlank {
	
	private CellGrid cellgrid = new CellGrid();
	private final Lock gridlock = new ReentrantLock();
	private final HashSet< Cell > burning = new HashSet<>();
	
	private final GridRenderContext gridRenderContext = new GridRenderContext();
	private final MouseObjectListener gridMouseObjectListener = new GridMouseListener();
	private final ComponentListener gridComponentListener = new GridComponentListener();
	
	public Grid() {
		Model.getMouse().addMouseObjectListener( gridMouseObjectListener );
		Model.getEngine().getRenderer().getComponent().addComponentListener( gridComponentListener );
	}
	
	public void rebuildFrom( CellGrid cellgrid ) {
		gridlock.lock();
		try {
			this.cellgrid = cellgrid;
			this.burning.clear();
			this.cellgrid.forEach( cell -> {
				if ( cell.getTerrain().isBurning() ) this.burning.add( cell );
			} );
			this.gridRenderContext.enforceCellBounds( this.cellgrid );
		} finally {
			gridlock.unlock();
		}
	}
	
	private Set< Cell > getLocalCells( Cell cell ) {
		GridSpace space = new GridSpace(
			(int)Math.floor( cell.getLocation().getX() - Spread.GRID_AFFECT_RADIUS ),
			(int)Math.floor( cell.getLocation().getY() - Spread.GRID_AFFECT_RADIUS ),
			(int)Math.ceil( 2 * Spread.GRID_AFFECT_RADIUS + 1 ),
			(int)Math.ceil( 2 * Spread.GRID_AFFECT_RADIUS + 1 )
		);
		return space.stream().parallel()
			.filter( point -> point.distance( point.x, point.y ) < Spread.GRID_AFFECT_RADIUS )
			.map( point -> cellgrid.get( point.x, point.y ) )
			.filter( Objects::nonNull )
			.collect( Collectors.toSet() );
	}
	
	@Override
	public void update() {
		
		Set< Cell > cellsToBurn = new HashSet<>();
		burning.stream().parallel()
			.forEach( cell -> {
				getLocalCells( cell ).forEach( local -> {
					boolean ignite = cell.trySpread( local );
					if ( ignite ) cellsToBurn.add( local );
				} );
			} );
		cellsToBurn.forEach( burning::add );
		
		burning.stream().parallel().forEach( Cell::update );
		
		burning.removeIf( cell -> !cell.getTerrain().isBurning() );
		
	}
	
	@Override
	public void render( Graphics g ) {
		cellgrid.forEach( cell -> cell.render( g ) );
	}
	
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
				burning.add( cell );
			} else {
				burning.remove( cell );
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
			int x = (int)( ( p.x - gridRenderContext.getGridX() ) / gridRenderContext.getCellWidth() );
			int y = (int)( ( p.y - gridRenderContext.getGridY() ) / gridRenderContext.getCellHeight() );
			GridSpace space = new GridSpace(
				(int)Math.ceil( x - GridEditOptions.getPenSize() / 2 ), (int)Math.ceil( y - GridEditOptions.getPenSize() / 2 ),
				(int)Math.floor( GridEditOptions.getPenSize() ), (int)Math.floor( GridEditOptions.getPenSize() )
			);
			return space.stream()
				.map( point -> cellgrid.get( point.x, point.y ) )
				.collect( Collectors.toSet() );
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
			gridRenderContext.setCanvasSize( Model.getEngine().getRenderer().getComponent().getSize() );
			gridRenderContext.enforceCellBounds( cellgrid );
			Model.getEngine().forceRender();
		}
		
	}
	
}

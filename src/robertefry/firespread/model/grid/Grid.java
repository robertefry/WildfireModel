
package robertefry.firespread.model.grid;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.Model;
import robertefry.firespread.model.map.CellMap;
import robertefry.firespread.model.math.Space;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Target;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Grid extends Target {

	private Space space = new Space();
	private CellMap cells = new CellMap();

	public void build( CellMap cells ) {
		this.cells.forEach( ( point, cell ) -> {
			removeSubTarget( cell );
		} );
		this.cells = cells;
		space.setBounds( 0, 0, 0, 0 );
		cells.forEach( ( point, cell ) -> {
			space.include( point );
			addSubTarget( cell );
		} );
		Model.getEngine().addPreCycleTask( () -> {
			Dimension size = Renderer.getCanvas().getSize();
			Renderer.getGraphics().clearRect( 0, 0, size.width, size.height );
		} );
		Model.getEngine().forceRender();
	}

	@Override
	public void tick( Engine engine ) {
		super.tick( engine );
		Map<Point,Cell> nextcells = new HashMap<>();
		Set<Cell> localcells = new HashSet<>();
		cells.forEach( ( point, cell ) -> {
			localcells.clear();
			space.getLocalRegion( point, 1 ).forEach( localpoint -> {
				localcells.add( cells.get( localpoint ) );
			} );
			Cell nextcell = cell.getNext( localcells );
			nextcells.put( point, nextcell );
		} );
		nextcells.forEach( ( point, cell ) -> {
			cells.put( point, cell );
		} );
	}

	@Override
	public boolean equals( Object obj ) {
		return super.equals( obj );
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder( "Grid[" );
		builder.append( "\n\t" + space.toString() );
		cells.forEach( ( point, cell ) -> {
			builder.append( "\n\t" + cell.toString() );
		} );
		return builder.append( "\n]" ).toString();
	}

}

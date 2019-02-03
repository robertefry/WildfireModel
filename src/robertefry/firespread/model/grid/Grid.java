
package robertefry.firespread.model.grid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.joml.Vector2i;
import robertefry.firespread.model.map.CellMap;
import robertefry.firespread.model.math.Space;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Grid implements Targetable {

	private Space space = new Space();
	private CellMap cells = new CellMap();

	// TODO build method from datamaps
	public void build( CellMap cells ) {
		this.cells = cells;
		space.setBounds( 0, 0, 0, 0 );
		cells.forEach( ( point, cell ) -> {
			space.put( point );
		} );
	}

	@Override
	public void tick( Engine engine ) {
		Targetable.super.tick( engine );
		next();
	}

	public void next() {
		Map<Vector2i,Cell> nextcells = new HashMap<>();
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

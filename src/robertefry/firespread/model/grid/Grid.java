
package robertefry.firespread.model.grid;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import robertefry.firespread.math.GridSpace;
import robertefry.firespread.model.cell.Cell;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.TargetAdapter;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class Grid implements TargetAdapter {

	public static final int LOCAL_RADIUS = 1;

	private final GridSpace space = new GridSpace();
	private final Map< Point, Cell > cells = new HashMap<>();

	public void rebuild( Set< Cell > cells ) {
		this.space.clear();
		this.cells.clear();
		cells.forEach( cell -> {
			Point location = cell.getLocation();
			this.space.add( location );
			this.cells.put( location, cell );
		} );
	}

	public Set< Cell > getLocalRegion( Point origin ) {
		Set< Cell > localcells = new HashSet<>();
		GridSpace localspace = new GridSpace(
			origin.x - LOCAL_RADIUS, origin.y - LOCAL_RADIUS, 2 * LOCAL_RADIUS, 2 * LOCAL_RADIUS
		);
		space.intersection( localspace ).forEach( point -> {
			localcells.add( cells.get( point ) );
		} );
		return localcells;
	}

	@Override
	public void render( Engine engine ) {
		TargetAdapter.super.render( engine );
		cells.values().forEach( cell -> {
			cell.render( engine );
		} );
	}

	@Override
	public void tick( Engine engine ) {
		TargetAdapter.super.tick( engine );
		cells.values().forEach( cell -> {
			cell.prepNext( getLocalRegion( cell.getLocation() ) );
		} );
		cells.values().forEach( cell -> {
			cell.next();
		} );
	}

}

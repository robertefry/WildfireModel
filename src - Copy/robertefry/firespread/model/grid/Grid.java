
package robertefry.firespread.model.grid;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;
import robertefry.firespread.model.Model;
import robertefry.firespread.model.map.CellSet;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Target;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Grid extends Target {

	private final CellSet cells = new CellSet();

	public void build( Set<Cell> cells ) {
		this.cells.forEach( cell -> {
			removeSubTarget( cell );
		} );
		this.cells.clear();
		this.cells.addAll( cells );
		cells.forEach( cell -> {
			addSubTarget( cell );
		} );
		Model.getEngine().forceRender();
	}

	public void interceptClick( Point point ) {
		Cell cell = cells.getCell( point );
		cell.iterateState();
	}

	@Override
	public void tick( Engine engine ) {
		super.tick( engine );
		Set<Cell> nextCells = new HashSet<>();
		cells.forEach( cell -> {
			Rectangle localRegion = cell.getLocalRegion();
			Set<Cell> localCells = new HashSet<>( 9 );
			cells.forEach( localCell -> {
				if (localRegion.contains( localCell.getBounds().getLocation() )) {
					localCells.add( localCell );
				}
			} );
			nextCells.add( cell.getNext( localCells ) );
		} );
		build( nextCells );
	}

}

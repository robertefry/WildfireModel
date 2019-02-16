
package robertefry.firespread.model.grid;

import java.awt.Point;
import java.util.Map;
import robertefry.firespread.model.terrain.Cyclic;
import robertefry.firespread.model.terrain.Terrain;
import robertefry.penguin.target.Updatable;

public class Cell implements Cyclic, Updatable {

	private final float elevation;
	private Terrain terrain, next;

	public Cell( float elevation, Terrain terrain ) {
		this.elevation = elevation;
		this.terrain = terrain;
		this.next = new Terrain( terrain );
	}

	public Cell( Cell cell ) {
		this.elevation = cell.elevation;
		this.terrain = new Terrain( cell.terrain );
		this.next = new Terrain( cell.next );
	}

	@Override
	public void cycle() {
		terrain.cycle();
		next.cycle();
	}

	@Override
	public void update() {
		next.update();
	}

	public void prepNext( Map< Point, Cell > cells ) {
		// TODO Cell::prepNext
		cells.values().forEach( cell -> {
			if ( cell.terrain.isBurning() ) next.tryBurn();
		} );
	}

	public void makeNext() {
		terrain = new Terrain( next );
	}

}


package robertefry.firespread.model.grid;

import java.awt.Point;
import java.util.Set;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Cell implements Targetable {

	private final Point point;
	private final Terrain terrain;

	public Cell( Point point, Terrain terrain ) {
		this.point = point;
		this.terrain = terrain;
	}

	@Override
	public void tick( Engine engine ) {
		Targetable.super.tick( engine );
		terrain.tick( engine );
	}

	public Cell getNext( Set<Cell> localRegion ) {
		//TODO Cell::getNext( Set<Cell> )
		return this;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( !( obj instanceof Cell ) ) return false;
		if ( !( (Cell)obj ).point.equals( point ) ) return false;
		if ( !( (Cell)obj ).terrain.equals( terrain ) ) return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format( "Cell[point=%s,terrain=%s]", point, terrain );
	}

}

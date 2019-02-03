
package robertefry.firespread.model.grid;

import java.util.Set;
import org.joml.Vector2i;
import robertefry.firespread.model.type.Terrain;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Cell implements Targetable {

	private final Vector2i point;
	private final Terrain terrain;

	public Cell( Vector2i point, Terrain terrain ) {
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
		if (!(obj instanceof Cell)) return false;
		if (!((Cell)obj).point.equals( point )) return false;
		if (!((Cell)obj).terrain.equals( terrain )) return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format( "Cell[point=%s,terrain=%s]", point, terrain );
	}

}

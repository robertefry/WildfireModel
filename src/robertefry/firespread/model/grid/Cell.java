
package robertefry.firespread.model.grid;

import java.util.Set;
import org.joml.Vector2i;
import robertefry.firespread.model.terrain.Terrain;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Cell {

	private final Vector2i point;
	private final Terrain terrain;

	public Cell( Vector2i point, Terrain terrain ) {
		this.point = point;
		this.terrain = terrain;
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
		return String.format( "Cell[\n\t├─point=%s\n\t├─terrain=%s\n]", point, terrain );
	}

}


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
	
	public Cell ( Vector2i point, Terrain terrain ) {
		this.point = point;
		this.terrain = terrain;
	}
	
	public Cell getNext( Set<Cell> localRegion ) {
		//TODO Cell::getNext( Set<Cell> )
		return new Cell( point, terrain );
	}

}

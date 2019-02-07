
package robertefry.firespread.model.cell;

import java.awt.Point;
import java.util.Set;
import robertefry.firespread.model.type.Elevation;
import robertefry.firespread.model.type.Terrain;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.TargetAdapter;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class Cell implements TargetAdapter {

	private final Point location;
	private Elevation elevation;
	private Terrain terrain, next;

	public Cell( Point location, Elevation elevation, Terrain terrain ) {
		this.location = location;
		this.elevation = elevation;
		this.terrain = terrain;
	}

	@Override
	public void render( Engine engine ) {
		TargetAdapter.super.render( engine );
		// TODO Cell::render
	}

	public void prepNext( Set< Cell > cells ) {
		// TODO Cell::prepNext
	}

	public void next() {
		terrain = next;
	}

	public Point getLocation() {
		return location;
	}

	public Elevation getElevation() {
		return elevation;
	}

	public Terrain getTerrain() {
		return terrain;
	}

}

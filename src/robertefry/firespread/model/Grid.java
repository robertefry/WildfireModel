
package robertefry.firespread.model;

import java.util.HashMap;
import java.util.Map;
import org.joml.Vector2i;
import robertefry.firespread.model.map.HeightMap;
import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.WindMap;
import robertefry.penguin.engine.target.Targetable;

public class Grid implements Sequencable<Grid>, Targetable {

	private final Map<Vector2i,Cell> cells = new HashMap<>();

	public Grid(
			int width, int height,
			TerrainMap terrainmap, WindMap windmap, HeightMap heightmap
	) {
		for ( int x = 0; x < width; x++ ) for ( int y = 0; y < height; y++ ) {
			Vector2i position = new Vector2i( x, y );
			Cell cell = new Cell( this, position, terrainmap, windmap, heightmap );
			cells.put( position, cell );
		}
	}

	@Override
	public void next() {
		// TODO Auto-generated method stub

	}

	@Override
	public Grid getNext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final Grid getSequence( int n ) {
		throw new RuntimeException( "No definition for nth term in sequence" );
	}

	public final Cell getCell( int x, int y ) {
		for ( Vector2i position : cells.keySet() ) {
			if (position.x == x && position.y == y) return cells.get( position );
		}
		return null;
	}

}

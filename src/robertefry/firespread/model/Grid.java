
package robertefry.firespread.model;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.joml.Vector2i;
import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.WindMap;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

public class Grid implements Sequence<Grid>, Targetable {

	private final BidiMap<Vector2i,Cell> cells = new DualHashBidiMap<>();

	public Grid(
			int width, int height,
			TerrainMap terrainmap, WindMap windmap
	) {
		for ( int x = 0; x < width; x++ ) for ( int y = 0; y < height; y++ ) {
			Vector2i position = new Vector2i( x, y );
			Cell cell = new Cell( this, terrainmap, windmap );
			cells.put( position, cell );
		}
	}
	
	@Override
	public void update( Engine engine ) {
		Targetable.super.update( engine );
		// TODO Auto-generated method stub
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

	public final Cell getCell( Vector2i position ) {
		return cells.get( position );
	}
	
	public final Vector2i getPosition( Cell cell ) {
		return cells.getKey( cell );
	}

}

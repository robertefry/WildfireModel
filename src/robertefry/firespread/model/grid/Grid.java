
package robertefry.firespread.model.grid;

import java.awt.Point;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.WindMap;
import robertefry.firespread.model.type.Fire;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

public class Grid implements Targetable {

	private final BidiMap<Point,Cell> cells = new DualHashBidiMap<>();

	public Grid(
			int width, int height,
			TerrainMap terrainmap, WindMap windmap
	) {
		for ( int x = 0; x < width; x++ ) for ( int y = 0; y < height; y++ ) {
			Point gridrefrence = new Point( x, y );
			Cell cell = new Cell( this, terrainmap, windmap );
			cells.put( gridrefrence, cell );
		}
	}

	@Override
	public void update( Engine engine ) {
		BidiMap<Point,Cell> nextcells = new DualHashBidiMap<>();
		cells.forEach( ( gridrefrence, cell ) -> {
			Fire nextfire = cell.getNext( engine.getClock() );
			Cell nextcell = new Cell( this, cell.getTerrainMap(), cell.getWindMap(), nextfire );
			nextcells.put( gridrefrence, nextcell );
		} );
		cells.clear();
		cells.putAll( nextcells );
	}

	public final Cell getCell( Point gridrefrence ) {
		return cells.get( gridrefrence );
	}

	public final Point getGridRefrence( Cell cell ) {
		return cells.getKey( cell );
	}

}

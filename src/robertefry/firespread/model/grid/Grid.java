
package robertefry.firespread.model.grid;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.joml.Vector2i;
import robertefry.firespread.model.map.TypeMap;
import robertefry.firespread.model.terrain.Terrain;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Grid {
	
	private final Gridspace gridspace = new Gridspace();
	private final Map<Vector2i,Cell> cells = new HashMap<>();
	
	public void make( TypeMap<Terrain> terrainmap ) {
		terrainmap.keySet().forEach( point -> {
			Cell cell = new Cell( point, terrainmap.get( point ) );
			cells.put( point, cell );
			gridspace.include( point );
		});
	}
	
	public void next() {
		Map<Vector2i,Cell> nextcells = new HashMap<>();
		Set<Cell> localcells = new HashSet<>();
		cells.forEach( ( point, cell ) -> {
			localcells.clear();
			gridspace.getLocalRegion( point, 1 ).forEach( localpoint -> {
				localcells.add( cells.get( localpoint ) );
			});
			Cell nextcell = cell.getNext( localcells );
			nextcells.put( point, nextcell );
		});
		nextcells.forEach( ( point, cell ) -> {
			cells.put( point, cell );
		});
	}

}

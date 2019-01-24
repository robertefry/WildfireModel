
package robertefry.firespread.model.type;

import robertefry.firespread.model.grid.GridRefrence;
import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.WindMap;

/**
 * @author Robert E Fry
 * @date 24 Jan 2019
 */
public class Fire {

	private float intensity;

	public Fire( float intensity ) {
		this.intensity = intensity;
	}
	
	public Fire( Fire fire ) {
		this.intensity = fire.intensity;
	}
	
	public void update( GridRefrence refrence, TerrainMap terrainmap, WindMap windmap ) {
		// TODO Fire::update(GridRefrence,TerrainMap,WindMap)
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity( float intensity ) {
		this.intensity = intensity;
	}

}

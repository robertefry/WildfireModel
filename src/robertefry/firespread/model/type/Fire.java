
package robertefry.firespread.model.type;

import java.awt.Point;
import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.WindMap;
import robertefry.penguin.engine.Engine;

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
	
	public void update( Engine.Clock clock, Point gridrefrence, TerrainMap terrainmap, WindMap windmap ) {
		// TODO Placeholder 'Fire::update'
	}

	public float getIntensity() {
		return intensity;
	}

	public void setIntensity( float intensity ) {
		this.intensity = intensity;
	}

}

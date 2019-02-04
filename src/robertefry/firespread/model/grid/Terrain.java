
package robertefry.firespread.model.grid;

import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Terrain implements Targetable {

	private final float elevation;
	private float flamability;
	private boolean burning = false;

	public Terrain( float elevation, float flamability ) {
		this.elevation = elevation;
		this.flamability = flamability;
	}

	@Override
	public void tick( Engine engine ) {
		Targetable.super.tick( engine );
		if ( burning ) {
			if ( flamability > 0 ) flamability--;
		}
	}

	@Override
	public boolean equals( Object obj ) {
		if ( !( obj instanceof Terrain ) ) return false;
		if ( ( (Terrain)obj ).elevation != elevation ) return false;
		if ( ( (Terrain)obj ).flamability != flamability ) return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format( "Terrain[elevation=%s,flamability=%s]", elevation, flamability );
	}

}

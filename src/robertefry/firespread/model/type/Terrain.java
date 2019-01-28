
package robertefry.firespread.model.type;

import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Terrain implements Targetable, Flamable {

	private final float height;
	private float volatility;
	private boolean burning = false;

	public Terrain( float height, float volatility ) {
		this.height = height;
		this.volatility = volatility;
	}

	@Override
	public void tick( Engine engine ) {
		Targetable.super.tick( engine );
		if (canBurn()) burn();
	}

	@Override
	public boolean canBurn() {
		return volatility > 0;
	}

	@Override
	public boolean isBurning() {
		return burning;
	}

	@Override
	public void burn() {
		burning = true;
		// TODO Terrain burning calculation
		// As the terrain burns, it's volatility goes down.
		// Burnt terrain (volatility=0) will not burn again.
		volatility--;
	}

	@Override
	public boolean equals( Object obj ) {
		if (!(obj instanceof Terrain)) return false;
		if (((Terrain)obj).height != height) return false;
		if (((Terrain)obj).volatility != volatility) return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format( "Terrain[height=%s,volatility=%s]", height, volatility );
	}

}

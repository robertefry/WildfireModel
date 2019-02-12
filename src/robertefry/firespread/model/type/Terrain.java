
package robertefry.firespread.model.type;

import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.TargetAdapter;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class Terrain implements TargetAdapter, Flamable {

	private float flamability;
	private boolean burning = false;

	public Terrain( float flamability ) {
		this.flamability = flamability;
	}

	@Override
	public void tick( Engine engine ) {
		TargetAdapter.super.tick( engine );
		if ( canBurn() && isBurning() ) {
			flamability = Math.max( 0, flamability-- );
		}
	}

	@Override
	public boolean canBurn() {
		return flamability > 0;
	}

	@Override
	public boolean tryBurn() {
		burning = canBurn();
		return isBurning();
	}

	@Override
	public boolean isBurning() {
		return burning;
	}

	public float getFlamability() {
		return flamability;
	}

}

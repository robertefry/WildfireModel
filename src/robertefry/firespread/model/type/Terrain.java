
package robertefry.firespread.model.type;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class Terrain implements Flamable {

	private float flamability;
	private boolean burning = false;

	public Terrain( float flamability ) {
		this.flamability = flamability;
	}

	@Override
	public boolean tryBurn() {
		burning = flamability > 0;
		flamability = Math.max( 0, flamability-- );
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

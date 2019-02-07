
package robertefry.firespread.model.grid;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class Terrain implements Flamable {

	private final float elevation;
	private float flamability;
	private boolean burning = false;

	public Terrain( float elevation ) {
		this( elevation, 0 );
	}

	public Terrain( float elevation, float flamability ) {
		this.elevation = elevation;
		this.flamability = flamability;
	}

	@Override
	public void tryBurn() {
		burning = flamability > 0;
		flamability = Math.max( 0, flamability-- );
	}

	@Override
	public boolean isBurning() {
		return burning;
	}

	public float getElevation() {
		return elevation;
	}

	public float getFlamability() {
		return flamability;
	}

}


package robertefry.firespread.model.terrain;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Terrain {

	private final float height;
	private float volatility;
	private float fireIntensity = 0;

	public Terrain( float height, float volatility ) {
		this.height = height;
		this.volatility = volatility;
	}

	public final float getHeight() {
		return height;
	}

	public final float getVolatility() {
		return volatility;
	}

	public final float getFireIntensity() {
		return fireIntensity;
	}

}


package robertefry.firespread.model.type;

public class Terrain {

	private float height;
	private float volatility;
	private float fireintensity = 0;

	public Terrain( float height, float volatility ) {
		this.height = height;
		this.volatility = volatility;
	}
	
	public float getHeight() {
		return height;
	}

	public final float getVolatility() {
		return volatility;
	}
	
	public float getFireIntensity() {
		return fireintensity;
	}

}

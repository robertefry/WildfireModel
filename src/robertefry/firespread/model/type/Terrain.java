
package robertefry.firespread.model.type;

public class Terrain {

	private final float height;
	private final float volatility;
	private float fireintensity = 0;

	public Terrain( float height, float volatility ) {
		this.height = height;
		this.volatility = volatility;
	}
	
	@Override
	public String toString() {
		return String.format( "Terrain[height=%s,volatility=%s,fire_intensity=%s]", height, volatility, fireintensity );
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
	
	public void setFireintensity( float fireintensity ) {
		this.fireintensity = fireintensity;
	}

}

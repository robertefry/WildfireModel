
package robertefry.firespread.model.type;

public class Terrain {

	private final float height;
	private final float volatility;

	public Terrain( float height, float volatility ) {
		this.height = height;
		this.volatility = volatility;
	}
	
	@Override
	public String toString() {
		return String.format( "Terrain[height=%s,volatility=%s]", height, volatility );
	}
	
	public float getHeight() {
		return height;
	}

	public final float getVolatility() {
		return volatility;
	}

}

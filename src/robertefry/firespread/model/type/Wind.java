
package robertefry.firespread.model.type;

import org.joml.Vector2f;

public class Wind {

	private Vector2f vector;

	public Wind( Vector2f vector ) {
		this.vector = vector;
	}
	
	public Wind( float magnitude, float direction ) {
		final float x = (float) ( magnitude * Math.cos( direction ) );
		final float y = (float) ( magnitude * Math.sin( direction ) );
		vector = new Vector2f( x, y );
	}
	
	@Override
	public String toString() {
		return String.format( "Wind[%s]", vector );
	}
	
	public Vector2f getVector() {
		return vector;
	}
	
	public void setVector( Vector2f vector ) {
		this.vector = vector;
	}

}

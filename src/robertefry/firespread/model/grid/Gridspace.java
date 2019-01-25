
package robertefry.firespread.model.grid;

import java.util.Iterator;
import org.joml.Vector2i;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Gridspace implements Iterable<Vector2i> {

	private final Vector2i min, max;

	public Gridspace() {
		min = new Vector2i( 0, 0 );
		max = new Vector2i( 0, 0 );
	}
	
	public Gridspace( Vector2i min, Vector2i max ) {
		this.min = min;
		this.max = max;
	}

	public void include( Vector2i point ) {
		min.min( point, min );
		max.max( point, max );
	}
	
	public Vector2i getMin() {
		return min;
	}

	public Vector2i getMax() {
		return max;
	}
	
	public Gridspace getLocalRegion( Vector2i origin, int range ) {
		Vector2i min, max;
		origin.min( this.min, min = new Vector2i() );
		origin.max( this.max, max = new Vector2i() );
		return new Gridspace( min, max );
	}

	@Override
	public Iterator<Vector2i> iterator() {
		return new Itr();
	}
	
	private final class Itr implements Iterator<Vector2i> {
		
		private final Vector2i vec = new Vector2i( min );

		@Override
		public boolean hasNext() {
			return max.distance( vec ) != 0;
		}

		@Override
		public Vector2i next() {
			vec.x++;
			if ( vec.x > max.x ) {
				vec.x = min.x;
				vec.y++;
			}
			return vec;
		}
		
	}

}

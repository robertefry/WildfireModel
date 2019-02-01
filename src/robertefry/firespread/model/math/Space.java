
package robertefry.firespread.model.math;

import java.util.Iterator;
import org.joml.Vector2i;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class Space implements Iterable<Vector2i> {

	public final Vector2i p1, p2;

	public Space() {
		this.p1 = new Vector2i( 0, 0 );
		this.p2 = new Vector2i( 0, 0 );
	}

	public Space( int p1x, int p1y, int p2x, int p2y ) {
		this.p1 = new Vector2i( p1x, p1y );
		this.p2 = new Vector2i( p2x, p2y );
	}

	public Space( Vector2i p1, Vector2i p2 ) {
		this.p1 = new Vector2i( p1 );
		this.p2 = new Vector2i( p2 );
	}

	public Space( Space space ) {
		this.p1 = new Vector2i( space.p1 );
		this.p2 = new Vector2i( space.p2 );
	}

	public void put( Vector2i point ) {
		this.p1.min( point );
		this.p1.max( point );
		this.p2.min( point );
		this.p2.max( point );
	}

	public Space getLocalRegion( Vector2i origin, int radius ) {
		final int x1 = Math.max( p1.x, origin.x - radius );
		final int y1 = Math.max( p1.y, origin.y - radius );
		final int x2 = Math.min( p2.x, origin.x + radius );
		final int y2 = Math.min( p2.y, origin.y + radius );
		return new Space( x1, y1, x2, y2 );
	}

	@Override
	public Iterator<Vector2i> iterator() {
		return new Itr();
	}

	private final class Itr implements Iterator<Vector2i> {

		private final Vector2i point = new Vector2i( p1 );

		@Override
		public boolean hasNext() {
			return point.distance( 0, 0 ) < p1.distance( 0, 0 );
		}

		@Override
		public Vector2i next() {
			point.x++;
			if (point.x > p2.x) {
				point.x = p1.x;
				point.y++;
			}
			return point;
		}

	}

	public void setBounds( Space space ) {
		this.p1.set( space.p1 );
		this.p2.set( space.p2 );
	}

	public void setBounds( Vector2i p1, Vector2i p2 ) {
		this.p1.set( p1 );
		this.p2.set( p2 );
	}

	public void setBounds( int p1x, int p1y, int p2x, int p2y ) {
		this.p1.set( p1x, p1y );
		this.p2.set( p2x, p2y );
	}

	public int getX() {
		return p1.x;
	}

	public int getY() {
		return p1.y;
	}

	public int getWidth() {
		return p2.x - p1.x;
	}

	public int getHeight() {
		return p2.y - p1.y;
	}

	@Override
	public boolean equals( Object obj ) {
		if (!( obj instanceof Space )) {
			return false;
		} else {
			Space space = (Space)obj;
			return space.p1.equals( this.p1 ) && space.p2.equals( this.p2 );
		}
	}

	@Override
	public String toString() {
		return String.format( "space[x=%s,y=%s,width=%s,height=%s]", getX(), getY(), getWidth(), getHeight() );
	}

}

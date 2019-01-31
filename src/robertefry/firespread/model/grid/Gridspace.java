
package robertefry.firespread.model.grid;

import java.util.Iterator;
import org.joml.Vector2i;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Gridspace implements Iterable<Vector2i> {

	private final Vector2i p1, p2;

	public Gridspace() {
		this.p1 = new Vector2i( 0, 0 );
		this.p2 = new Vector2i( 0, 0 );
	}
	
	public Gridspace( int p1x, int p1y, int p2x, int p2y ) {
		this.p1 = new Vector2i( p1x, p1y );
		this.p2 = new Vector2i( p2x, p2y );
	}

	public Gridspace( Vector2i p1, Vector2i p2 ) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Gridspace( Gridspace gridspace ) {
		this.p1 = new Vector2i( gridspace.p1 );
		this.p2 = new Vector2i( gridspace.p2 );
	}
	
	public Gridspace scale( int p1x, int p1y, int p2x, int p2y ) {
		this.p1.set( p1x, p1y );
		this.p2.set( p2x, p2y );
		return this;
	}
	
	public Gridspace scale( Vector2i p1, Vector2i p2 ) {
		this.p1.set( p1 );
		this.p2.set( p2 );
		return this;
	}
	
	public Gridspace scale( Gridspace gridspace ) {
		this.p1.set( gridspace.p1 );
		this.p2.set( gridspace.p2 );
		return this;
	}

	public Gridspace getLocalRegion( Vector2i origin, int range ) {
		Vector2i p1, p2;
		origin.min( this.p1, p1 = new Vector2i() );
		origin.max( this.p2, p2 = new Vector2i() );
		return new Gridspace( p1, p2 );
	}

	public void put( Vector2i p ) {
		p1.min( p, p1 );
		p2.max( p, p2 );
	}

	@Override
	public Iterator<Vector2i> iterator() {
		return new Itr();
	}
	
	public final Vector2i getP1() {
		return p1;
	}

	public final Vector2i getP2() {
		return p2;
	}

	public final int getWidth() {
		return p2.x - p1.x;
	}
	
	public final int getHeight() {
		return p2.y - p1.y;
	}
	
	public final int getSize() {
		return getWidth() * getHeight();
	}

	private final class Itr implements Iterator<Vector2i> {

		private final Vector2i vec = new Vector2i( p1 );

		@Override
		public boolean hasNext() {
			return p2.distance( vec ) != 0;
		}

		@Override
		public Vector2i next() {
			vec.x++;
			if (vec.x > p2.x) {
				vec.x = p1.x;
				vec.y++;
			}
			return vec;
		}

	}

	@Override
	public boolean equals( Object obj ) {
		if (!(obj instanceof Gridspace)) return false;
		if (!((Gridspace)obj).p1.equals( p1 )) return false;
		if (!((Gridspace)obj).p2.equals( p2 )) return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format( "Gridspace[min=%s,max=%s]", p1, p2 );
	}

}

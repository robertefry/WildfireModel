
package robertefry.firespread.model.math;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class Space extends Rectangle implements Iterable<Point> {
	private static final long serialVersionUID = 4663815306240176855L;

	public Space() {
		super( 0, 0, 0, 0 );
	}

	public Space( Rectangle r ) {
		super( r.x, r.y, r.width, r.height );
	}

	public Space( int x, int y, int width, int height ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Space( int width, int height ) {
		super( 0, 0, width, height );
	}

	public Space( Point p, Dimension d ) {
		super( p.x, p.y, d.width, d.height );
	}

	public Space( Point p ) {
		super( p.x, p.y, 0, 0 );
	}

	public Space( Dimension d ) {
		super( 0, 0, d.width, d.height );
	}

	public Space getLocalRegion( Point point, int radius ) {
		return getLocalRegion( point, radius, radius );
	}

	public Space getLocalRegion( Point point, int xDist, int yDist ) {
		return new Space( point.x - xDist, point.y - yDist, 2 * xDist, 2 * yDist );
	}

	public void include( Point point ) {
		include( point.x, point.y );
	}

	public void include( int x, int y ) {
		this.x = Math.min( x, this.x );
		this.y = Math.min( y, this.y );
		this.width = Math.max( x, this.x + this.width ) - x;
		this.height = Math.max( y, this.y + this.height ) - y;
	}

	public Point getP1() {
		return new Point( x, y );
	}

	public Point getP2() {
		return new Point( x + width, y );
	}

	public Point getP3() {
		return new Point( x, y + height );
	}

	public Point getP4() {
		return new Point( x + width, y + height );
	}

	@Override
	public Iterator<Point> iterator() {
		return new Itr();
	}

	private final class Itr implements Iterator<Point> {

		private final Point point = new Point( x, y );

		@Override
		public boolean hasNext() {
			return point.x <= x + width && point.y <= y + height;
		}

		@Override
		public Point next() {
			point.x++;
			if ( point.x > x + width ) {
				point.x = x;
				point.y++;
			}
			return point;
		}

	}

}

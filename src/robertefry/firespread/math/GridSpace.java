
package robertefry.firespread.math;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class GridSpace implements Collection< Point > {

	private final Rectangle bounds;

	public GridSpace() {
		bounds = new Rectangle( 0, 0, 0, 0 );
	}

	public GridSpace( GridSpace gs ) {
		bounds = new Rectangle( gs.bounds.x, gs.bounds.y, gs.bounds.width, gs.bounds.height );
	}

	public GridSpace( int x, int y, int width, int height ) {
		bounds = new Rectangle( x, y, width, height );
	}

	public GridSpace( Point point, Dimension size ) {
		bounds = new Rectangle( point.x, point.y, size.width, size.height );
	}

	public GridSpace( Point p1, Point p4 ) {
		bounds = new Rectangle( p1.x, p1.y, p1.x + p4.x, p1.y + p4.y );
	}

	public GridSpace( Rectangle rect ) {
		bounds = new Rectangle( rect.x, rect.y, rect.width, rect.height );
	}

	public GridSpace( int width, int height ) {
		bounds = new Rectangle( 0, 0, width, height );
	}

	public GridSpace( Point point ) {
		bounds = new Rectangle( point.x, point.y, 0, 0 );
	}

	public GridSpace( Dimension size ) {
		bounds = new Rectangle( 0, 0, size.width, size.height );
	}

	public int getX() {
		return bounds.x;
	}

	public int getY() {
		return bounds.y;
	}

	public int getWidth() {
		return bounds.width;
	}

	public int getHeight() {
		return bounds.height;
	}

	public void setX( int x ) {
		bounds.x = x;
	}

	public void setY( int y ) {
		bounds.y = y;
	}

	public void setWidth( int width ) {
		bounds.width = width;
	}

	public void setHeight( int height ) {
		bounds.height = height;
	}

	public Point getP1() {
		return new Point( bounds.x, bounds.y );
	}

	public Point getP2() {
		return new Point( bounds.x + bounds.width, bounds.y );
	}

	public Point getP3() {
		return new Point( bounds.x, bounds.y + bounds.height );
	}

	public Point getP4() {
		return new Point( bounds.x + bounds.width, bounds.y + bounds.height );
	}

	public Point getLocation() {
		return bounds.getLocation();
	}

	public Dimension getSize() {
		return bounds.getSize();
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds( GridSpace gs ) {
		setBounds( gs.bounds.x, gs.bounds.y, gs.bounds.width, gs.bounds.height );
	}

	public void setBounds( int x, int y, int width, int height ) {
		bounds.setBounds( x, y, width, height );
	}

	public void setBounds( Point point, Dimension size ) {
		bounds.setBounds( point.x, point.y, size.width, size.height );
	}

	public void setBounds( Point p1, Point p4 ) {
		bounds.setBounds( p1.x, p1.y, p1.x + p4.x, p1.y + p4.y );
	}

	public void setBounds( Rectangle rect ) {
		bounds.setBounds( rect.x, rect.y, rect.width, rect.height );
	}

	public GridSpace union( GridSpace space ) {
		GridSpace union = new GridSpace();
		forEach( point -> union.add( point ) );
		space.forEach( point -> union.add( point ) );
		return union;
	}

	public GridSpace intersection( GridSpace space ) {
		GridSpace intersection = new GridSpace();
		forEach( point -> {
			if ( space.contains( point ) ) intersection.add( point );
		} );
		return intersection;
	}

	@Override
	public int size() {
		return bounds.width * bounds.height;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains( Object o ) {
		if ( !( o instanceof Point ) ) return false;
		Point p = (Point)o;
		return p.x >= bounds.x && p.x <= bounds.x + bounds.width && p.y >= bounds.y && p.y <= bounds.y + bounds.height;
	}

	@Override
	public Iterator< Point > iterator() {
		return new Itr();
	}

	@Override
	public Object[] toArray() {
		return toArray( new Point[size()] );
	}

	@Override
	public < T > T[] toArray( T[] a ) {
		Set< Point > points = new HashSet<>();
		iterator().forEachRemaining( points::add );
		return points.toArray( a );
	}

	public boolean add( int x, int y ) {
		bounds.x = Math.min( bounds.x, x );
		bounds.y = Math.min( bounds.y, y );
		bounds.width = Math.max( bounds.x + bounds.width, x ) - bounds.x;
		bounds.height = Math.max( bounds.y + bounds.height, y ) - bounds.y;
		return true;
	}

	@Override
	public boolean add( Point p ) {
		return add( p.x, p.y );
	}

	@Override
	public boolean remove( Object o ) {
		// TODO GridSpace::remove
		return false;
	}

	@Override
	public boolean containsAll( Collection< ? > c ) {
		for ( Object p : c ) {
			if ( !contains( p ) ) return false;
		}
		return true;
	}

	@Override
	public boolean addAll( Collection< ? extends Point > c ) {
		for ( Point p : c ) {
			if ( !add( p ) ) return false;
		}
		return true;
	}

	@Override
	public boolean removeAll( Collection< ? > c ) {
		// TODO GridSpace::removeAll
		return false;
	}

	@Override
	public boolean retainAll( Collection< ? > c ) {
		// TODO GridSpace::retainAll
		return false;
	}

	@Override
	public void clear() {
		bounds.setBounds( 0, 0, 0, 0 );
	}

	private final class Itr implements Iterator< Point > {

		private int local = 0;

		@Override
		public boolean hasNext() {
			return local < size();
		}

		@Override
		public Point next() {
			Point point = new Point(
				(int)( local % bounds.width ) + bounds.x, (int)( local / bounds.width ) + bounds.y
			);
			local++;
			return point;
		}

	}

}

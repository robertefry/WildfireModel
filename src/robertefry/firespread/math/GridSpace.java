
package robertefry.firespread.math;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

public class GridSpace implements Iterable< Point > {

	private int x, y, width, height;

	public GridSpace() {
	}

	public GridSpace( GridSpace g ) {
		this( g.x, g.y, g.width, g.height );
	}

	public GridSpace( int x, int y, int width, int height ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public GridSpace( Point p, Dimension d ) {
		this( p.x, p.y, d.width, d.height );
	}

	public GridSpace( Rectangle r ) {
		this( r.x, r.y, r.width, r.height );
	}

	public GridSpace( int width, int height ) {
		this( 0, 0, width, height );
	}

	public GridSpace( Point p ) {
		this( p.x, p.y, 0, 0 );
	}

	public GridSpace( Dimension d ) {
		this( 0, 0, d.width, d.height );
	}

	public void put( int x, int y ) {
		// TODO
	}

	public void put( Point p ) {
		put( p.x, p.y );
	}

	public GridSpace intersection( GridSpace g ) {
		return new GridSpace( getBounds().intersection( g.getBounds() ) );
	}

	public GridSpace intersection( Rectangle r ) {
		return new GridSpace( getBounds().intersection( r ) );
	}

	public GridSpace union( GridSpace g ) {
		return new GridSpace( getBounds().union( g.getBounds() ) );
	}

	public GridSpace union( Rectangle r ) {
		return new GridSpace( getBounds().union( r ) );
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX( int x ) {
		this.x = x;
	}

	public void setY( int y ) {
		this.y = y;
	}

	public void setWidth( int width ) {
		this.width = width;
	}

	public void setHeight( int height ) {
		this.height = height;
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

	public void setP1( Point p ) {
		// TODO
	}

	public void setP2( Point p ) {
		// TODO
	}

	public void setP3( Point p ) {
		// TODO
	}

	public void setP4( Point p ) {
		// TODO
	}

	public Point getLocation() {
		return new Point( x, y );
	}

	public Dimension getSize() {
		return new Dimension( width, height );
	}

	public Rectangle getBounds() {
		return new Rectangle( x, y, width, height );
	}

	public void setLocation( Point p ) {
		setLocation( p.x, p.y );
	}

	public void setSize( Dimension d ) {
		setSize( d.width, d.height );
	}

	public void setBounds( Rectangle r ) {
		setBounds( r.x, r.y, r.width, r.height );
	}

	public void setLocation( int x, int y ) {
		this.x = x;
		this.y = y;
	}

	public void setSize( int width, int height ) {
		this.width = width;
		this.height = height;
	}

	public void setBounds( int x, int y, int width, int height ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public Iterator< Point > iterator() {
		return new Itr();
	}

	private final class Itr implements Iterator< Point > {

		private int local = 0;

		@Override
		public boolean hasNext() {
			return local < width * height;
		}

		@Override
		public Point next() {
			int px = (int)( local % width );
			int py = (int)( local / width );
			local++;
			return new Point( x + px, y + py );
		}

	}

}

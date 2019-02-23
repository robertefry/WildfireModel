
package robertefry.firespread.model.grid;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Iterator;

/**
 * @author Robert E Fry
 * @date 22 Feb 2019
 */
public class GridShape implements Iterable< Point > {
	
	private int x, y, width, height;
	
	public GridShape( int x, int y, int width, int height ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public GridShape( int width, int height ) {
		this( 0, 0, width, height );
	}
	
	public GridShape( Rectangle r ) {
		this( r.x, r.y, r.width, r.height );
	}
	
	public GridShape( Point p, Dimension d ) {
		this( p.x, p.y, d.width, d.height );
	}
	
	public GridShape( Point p ) {
		this( p.x, p.y, 0, 0 );
	}
	
	public GridShape( Dimension d ) {
		this( 0, 0, d.width, d.height );
	}
	
	public GridShape( GridShape g ) {
		this( g.x, g.y, g.width, g.height );
	}
	
	public GridShape() {
		this( 0, 0, 0, 0 );
	}
	
	public synchronized void include( int x, int y ) {
		this.width = Math.max( 1, this.width );
		this.height = Math.max( 1, this.height );
		int x1 = Math.min( x, this.x );
		int y1 = Math.min( y, this.y );
		int x2 = Math.max( x + 1, this.x + this.width );
		int y2 = Math.max( y + 1, this.y + this.height );
		setBounds( x1, y1, x2 - x1, y2 - y1 );
	}
	
	public synchronized void include( Point p ) {
		include( p.x, p.y );
	}
	
	public synchronized int getX() {
		return x;
	}
	
	public synchronized int getY() {
		return y;
	}
	
	public synchronized int getWidth() {
		return width;
	}
	
	public synchronized int getHeight() {
		return height;
	}
	
	public synchronized void setX( int x ) {
		this.x = x;
	}
	
	public synchronized void setY( int y ) {
		this.y = y;
	}
	
	public synchronized void setWidth( int width ) {
		this.width = width;
	}
	
	public synchronized void setHeight( int height ) {
		this.height = height;
	}
	
	public synchronized Point getP1() {
		return new Point( x, y );
	}
	
	public synchronized Point getP2() {
		return new Point( x + width, y );
	}
	
	public synchronized Point getP3() {
		return new Point( x, y + height );
	}
	
	public synchronized Point getP4() {
		return new Point( x + width, y + height );
	}
	
	public synchronized void setP1( Point p ) {
		// TODO GridSpace::setP1
	}
	
	public synchronized void setP2( Point p ) {
		// TODO GridSpace::setP2
	}
	
	public synchronized void setP3( Point p ) {
		// TODO GridSpace::setP3
	}
	
	public synchronized void setP4( Point p ) {
		// TODO GridSpace::setP4
	}
	
	public synchronized Rectangle getBounds() {
		return new Rectangle( x, y, width, height );
	}
	
	public synchronized void setBounds( int x, int y, int width, int height ) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public synchronized void setBounds( Rectangle r ) {
		setBounds( r.x, r.y, r.width, r.height );
	}
	
	public synchronized Point getLocation() {
		return new Point( x, y );
	}
	
	public synchronized void setLocation( int x, int y ) {
		this.x = x;
		this.y = y;
	}
	
	public synchronized void setLocation( Point p ) {
		setLocation( p.x, p.y );
	}
	
	public synchronized Dimension getSize() {
		return new Dimension( width, height );
	}
	
	public synchronized void setSize( int width, int height ) {
		this.width = width;
		this.height = height;
	}
	
	public synchronized void setSize( Dimension d ) {
		setSize( d.width, d.height );
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
			int x1 = local / width;
			int y1 = local % width;
			local++;
			return new Point( x + x1, y + y1 );
		}
		
	}
	
	@Override
	public boolean equals( Object obj ) {
		if ( !( obj instanceof GridShape ) ) {
			return false;
		}
		return ( (GridShape)obj ).getBounds().equals( getBounds() );
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "[x=" + x + ",y=" + y + ",width=" + width + ",height=" + height + "]";
	}
	
}


package robertefry.firespread.model.map;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import robertefry.firespread.model.grid.GridShape;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class ImageMap implements Iterable< Color > {
	
	private BufferedImage image;
	private Rectangle bounds;
	
	public ImageMap( BufferedImage image, Rectangle bounds ) {
		this.image = image;
		this.bounds = bounds;
	}
	
	@Override
	public Iterator< Color > iterator() {
		return new Itr();
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	private final class Itr implements Iterator< Color > {
		
		private final Iterator< Point > itr = ( new GridShape( bounds ) ).iterator();
		
		@Override
		public boolean hasNext() {
			return itr.hasNext();
		}
		
		@Override
		public Color next() {
			Point point = itr.next();
			return new Color( image.getRGB( point.x, point.y ) );
		}
		
	}
	
}


package robertefry.firespread.io.map;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Iterator;

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
		
		private int local = 0;
		
		@Override
		public boolean hasNext() {
			return local < bounds.width * bounds.height;
		}
		
		@Override
		public Color next() {
			int x = local / bounds.width;
			int y = local % bounds.width;
			local++;
			return new Color( image.getRGB( bounds.x + x, bounds.y + y ) );
		}
		
	}
	
}

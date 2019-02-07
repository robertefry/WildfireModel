
package robertefry.firespread.map;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import robertefry.firespread.math.GridSpace;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class ImageMap implements Iterable< Color > {

	private BufferedImage image;
	private GridSpace gridspace;

	public ImageMap( BufferedImage image, GridSpace gridspace ) {
		this.image = image;
		this.gridspace = gridspace;
	}

	@Override
	public Iterator< Color > iterator() {
		return new Itr();
	}

	public BufferedImage getImage() {
		return image;
	}

	public GridSpace getGridSpace() {
		return gridspace;
	}

	private final class Itr implements Iterator< Color > {

		private final Iterator< Point > itr = gridspace.iterator();

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

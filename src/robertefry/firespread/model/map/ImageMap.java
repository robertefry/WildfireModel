
package robertefry.firespread.model.map;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import org.joml.Vector2i;
import robertefry.firespread.model.math.Space;

/**
 * @author Robert E Fry
 * @date 31 Jan 2019
 */
public class ImageMap implements Iterable<Color> {

	private BufferedImage image;
	private Space space;

	public ImageMap( BufferedImage image, Space space ) {
		this.image = image;
		this.space = space;
	}

	public ImageMap scale( int width, int height ) {
		BufferedImage scaled = new BufferedImage( width, height, BufferedImage.TYPE_INT_ARGB );
		Graphics g = scaled.getGraphics();
		g.drawImage( image, 0, 0, width, height, space.p1.x, space.p1.y, space.p2.x, space.p2.y, null );
		g.dispose();
		this.image = scaled;
		this.space.setBounds( 0, 0, width, height );
		return this;
	}

	public final BufferedImage getImage() {
		return image;
	}

	public final Space getSpace() {
		return space;
	}

	@Override
	public Iterator<Color> iterator() {
		return new Itr();
	}

	private final class Itr implements Iterator<Color> {

		private final Iterator<Vector2i> itrspace = space.iterator();

		@Override
		public boolean hasNext() {
			return itrspace.hasNext();
		}

		@Override
		public Color next() {
			Vector2i next = itrspace.next();
			return new Color( image.getRGB( next.x, next.y ) );
		}

	}

}
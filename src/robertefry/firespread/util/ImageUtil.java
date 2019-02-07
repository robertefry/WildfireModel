
package robertefry.firespread.util;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class ImageUtil {

	public static final BufferedImage redraw( BufferedImage image, Dimension size, Rectangle bounds ) {
		BufferedImage redrawn = new BufferedImage( size.width, size.height, BufferedImage.TYPE_INT_ARGB );
		Graphics2D g = redrawn.createGraphics();
		g.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
		g.setRenderingHint( RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY );
		g.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		g.drawImage(
			image, 0, 0, redrawn.getWidth(), redrawn.getHeight(),
			bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height, null
		);
		g.dispose();
		return redrawn;
	}

}

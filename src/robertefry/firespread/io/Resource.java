
package robertefry.firespread.io;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.commons.logging.LogFactory;

/**
 * @author Robert E Fry
 * @date 28 Jan 2019
 */
public class Resource {

	public static Image loadImage( String source ) {
		Image image = null;
		try {
			image = ImageIO.read( new File( source ) );
		} catch ( IOException e ) {
			LogFactory.getLog( Resource.class ).warn( "failed to load image " + source, e );
		}
		return image;
	}

	public static Image loadImage( String source, int width, int height ) {
		Image image = Resource.loadImage( source );
		BufferedImage resized = new BufferedImage( width, height, BufferedImage.TYPE_INT_ARGB );
		Graphics2D g2 = resized.createGraphics();
		g2.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
		g2.drawImage( image, 0, 0, width, height, null );
		g2.dispose();
		return resized;
	}

}

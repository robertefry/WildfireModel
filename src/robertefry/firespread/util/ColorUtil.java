
package robertefry.firespread.util;

import java.awt.Color;
import java.util.Random;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public class ColorUtil {

	public static Color newRandomColor() {
		Random rand = new Random( System.nanoTime() );
		int r = rand.nextInt( 255 );
		int g = rand.nextInt( 255 );
		int b = rand.nextInt( 255 );
		return new Color( r, g, b );
	}

}


package robertefry.firespread.util;

import java.awt.Color;
import java.util.Random;

public class ColorUtil {

	private static final Random rand = new Random( System.currentTimeMillis() );

	public static final Color randomColor() {
		int r = rand.nextInt( 255 );
		int g = rand.nextInt( 255 );
		int b = rand.nextInt( 255 );
		return new Color( r, g, b );
	}

}

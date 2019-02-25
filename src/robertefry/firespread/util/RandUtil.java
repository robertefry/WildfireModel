package robertefry.firespread.util;

import java.util.Random;

/**
 * @author Robert E Fry
 * @date 25 Feb 2019
 */
public class RandUtil {

	private static final Random rand = new Random(System.currentTimeMillis());

	public static double nextDouble() {
		return rand.nextDouble();
	}

}

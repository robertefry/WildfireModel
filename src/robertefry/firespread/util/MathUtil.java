
package robertefry.firespread.util;

/**
 * @author Robert E Fry
 * @date 26 Jan 2019
 */
public class MathUtil {

	public static < T extends Comparable<T> > T clamp( T value, T min, T max ) {
		if (value.compareTo( min ) < 0) return min;
		if (value.compareTo( max ) > 0) return max;
		return value;
	}

}

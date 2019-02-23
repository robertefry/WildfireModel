
package robertefry.firespread.util;

/**
 * @author Robert E Fry
 * @date 23 Feb 2019
 */
public class MathUtil {
	
	public static double clamp( double value, double lower, double upper ) {
		if ( value > upper ) value = upper;
		if ( value < lower ) value = lower;
		return value;
	}
	
}

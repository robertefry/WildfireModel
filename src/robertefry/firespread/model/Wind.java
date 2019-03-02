
package robertefry.firespread.model;

/**
 * @author Robert E Fry
 * @date 25 Feb 2019
 */
public class Wind {
	
	private static double magnitude, direction;
	
	public static double getMagnitude() {
		return magnitude;
	}
	
	public static void setMagnitude( double magnitude ) {
		Wind.magnitude = magnitude;
	}
	
	public static double getDirection() {
		return direction;
	}
	
	public static void setDirection( double direction ) {
		Wind.direction = direction;
	}
	
}

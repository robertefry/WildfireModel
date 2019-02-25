
package robertefry.firespread.model;

/**
 * @author Robert E Fry
 * @date 25 Feb 2019
 */
public class Wind {
	
	// TODO implement into model
	
	private static double speed, direction;
	
	public static double getSpeed() {
		return speed;
	}
	
	public static void setSpeed( double speed ) {
		Wind.speed = speed;
	}
	
	public static double getDirection() {
		return direction;
	}
	
	public static void setDirection( double direction ) {
		Wind.direction = direction;
	}
	
}

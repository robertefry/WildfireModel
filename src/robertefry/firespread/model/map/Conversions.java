
package robertefry.firespread.model.map;

import java.util.function.Function;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class Conversions {
	
	public static Function<Integer,Float> getElevationMapConversion() {
		return ( color ) -> {
			// TODO ElevationMapConversion
			return 0.0f;
		};
	}
	
	public static Function<Integer,Float> getFlamabilityMapConversion() {
		return ( color ) -> {
			// TODO FlamabilityMapConversion
			return 0.0f;
		};
	}

}

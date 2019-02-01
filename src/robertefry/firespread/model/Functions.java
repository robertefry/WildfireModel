
package robertefry.firespread.model;

import java.util.function.Function;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class Functions {
	
	public static Function<Integer,Float> getHeightMapConversion() {
		return ( color ) -> {
			// TODO HeightMapConversion
			return 0.0f;
		};
	}
	
	public static Function<Integer,Float> getVolatilityMapConversion() {
		return ( color ) -> {
			// TODO VolatilityMapConversion
			return 0.0f;
		};
	}

}

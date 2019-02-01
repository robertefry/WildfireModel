
package robertefry.firespread.model.map;

import java.util.function.Function;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class Conversions {
	
	public static Function<Integer,Float> getElevationMapConversion() {
		return ( color ) -> {
			// TODO HeightMapConversion
			return 0.0f;
		};
	}
	
	public static Function<Integer,Float> getTerrainMapConversion() {
		return ( color ) -> {
			// TODO VolatilityMapConversion
			return 0.0f;
		};
	}

}

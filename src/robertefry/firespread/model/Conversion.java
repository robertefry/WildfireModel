
package robertefry.firespread.model;

import java.util.function.Function;
import robertefry.firespread.model.terrain.Terrain;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class Conversion {

	public static Function< Integer, Float > getElevationConversion() {
		return ( color ) -> {
			// TODO ElevationMapConversion
			return 0.0f;
		};
	}

	public static Function< Integer, Terrain > getTerrainConversion() {
		return ( color ) -> {
			// TODO FlamabilityMapConversion
			return new Terrain( 1.0f );
		};
	}

}

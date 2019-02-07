
package robertefry.firespread.model;

import java.util.function.Function;
import robertefry.firespread.model.type.Elevation;
import robertefry.firespread.model.type.Terrain;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class Conversions {

	public static Function< Integer, Elevation > getElevationConversion() {
		return ( color ) -> {
			// TODO ElevationMapConversion
			return new Elevation( 0.0f );
		};
	}

	public static Function< Integer, Terrain > getTerrainConversion() {
		return ( color ) -> {
			// TODO FlamabilityMapConversion
			return new Terrain( 0.0f );
		};
	}

}

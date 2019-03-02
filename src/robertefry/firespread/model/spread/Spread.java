
package robertefry.firespread.model.spread;

import robertefry.firespread.model.grid.Cell;
import robertefry.firespread.util.MathUtil;
import robertefry.firespread.util.RandUtil;

/**
 * @author Robert E Fry
 * @date 25 Feb 2019
 */
public class Spread {
	
	public static final double GRID_AFFECT_RADIUS = 1;
	
	private static final CellTest distance = new CellTestDistance();
	private static final CellTest elevation = new CellTestElevation();
	private static final CellTest wind = new CellTestWind();
	
	public static double test( Cell src, Cell dest ) {
		double probability = 1;
		probability *= MathUtil.clamp( distance.test( src, dest ), 0, 1 );
		probability *= MathUtil.clamp( elevation.test( src, dest ), 0, 1 );
		probability *= MathUtil.clamp( wind.test( src, dest ), 0, 1 );
		return probability;
	}
	
	public static boolean pass( Cell src, Cell dest ) {
		return RandUtil.nextDouble() < test( src, dest );
	}
	
	public static CellTestDistance getDistanceTest() {
		return (CellTestDistance)distance;
	}
	
	public static CellTestWind getWindTest() {
		return (CellTestWind)wind;
	}
	
}

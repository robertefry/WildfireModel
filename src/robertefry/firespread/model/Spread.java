package robertefry.firespread.model;

import robertefry.firespread.model.grid.Cell;
import robertefry.firespread.util.RandUtil;

/**
 * @author Robert E Fry
 * @date 25 Feb 2019
 */
public class Spread {
	
	public static final double GRID_AFFECT_RADIUS = Math.sqrt( 2 );
	public static final double SPREAD_STRENGTH_MIN = 0.5f;
	public static final double SPREAD_STRENGTH_MAX = 0.9f;
	
	private static final double getSpreadStrength( double distance ) {
		double grad = - ( SPREAD_STRENGTH_MAX - SPREAD_STRENGTH_MIN ) / GRID_AFFECT_RADIUS;
		return SPREAD_STRENGTH_MAX + grad * distance;
	}
	
	private static final boolean isSpreadSuccessful( double strength ) {
		double rand = RandUtil.nextDouble();
		return rand >= SPREAD_STRENGTH_MIN && rand < SPREAD_STRENGTH_MAX;
	}
	
	public static final boolean passSpreadModel( Cell src, Cell dest ) {
		double distance = src.getPoint().distance( dest.getPoint() );
		double strength = Spread.getSpreadStrength( distance );
		return Spread.isSpreadSuccessful( strength );
	}

}

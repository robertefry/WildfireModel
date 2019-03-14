
package robertefry.firespread.model.terrain;

import java.awt.Color;

/**
 * @author Robert E Fry
 * @date 23 Feb 2019
 */
public enum TerrainState implements Flamable {
	
	WILD( true, false, Color.GREEN ),
	BURNING( true, true, Color.RED ),
	CLEARED( false, false, Color.BLACK );
	
	private final boolean canBurn;
	private final boolean isBurning;
	private final Color color;
	
	TerrainState( boolean canBurn, boolean isBurning, Color color ) {
		this.canBurn = canBurn;
		this.isBurning = isBurning;
		this.color = color;
	}
	
	@Override
	public boolean canBurn() {
		return canBurn;
	}
	
	@Override
	public boolean isBurning() {
		return isBurning;
	}
	
	public Color getDrawColor() {
		return color;
	}
	
	public static TerrainState getDefault() {
		return TerrainState.WILD;
	}
	
}

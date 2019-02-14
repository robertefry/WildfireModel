
package robertefry.firespread.model.terrain;

import java.awt.Color;

public enum EnumTerrain implements Flamable {

	WILD( true, false, new Color( 0, 128, 0 ) ),
	BURNING( true, true, new Color( 128, 0, 0 ) ),
	CLEARED( false, false, Color.DARK_GRAY );

	private final boolean flamable;
	private final boolean burning;
	private final Color color;

	EnumTerrain( boolean flamable, boolean burning, Color color ) {
		this.flamable = flamable;
		this.burning = burning;
		this.color = color;
	}

	@Override
	public boolean canBurn() {
		return flamable;
	}

	@Override
	public boolean tryBurn() {
		return false;
	}

	public boolean isBurning() {
		return burning;
	}

	public Color getColor() {
		return color;
	}

}

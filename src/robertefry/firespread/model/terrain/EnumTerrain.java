
package robertefry.firespread.model.terrain;

import java.awt.Color;
import java.io.Serializable;

public enum EnumTerrain implements Flamable, Serializable {

	NULL( false, false, false, Color.WHITE ),
	WILD( true, true, false, Color.GREEN ),
	BURNING( true, true, true, Color.RED ),
	CLEARED( true, false, false, Color.BLACK ),
	WATER( true, false, false, Color.BLUE );

	private final boolean userOption;

	private final boolean flamable;
	private final boolean burning;
	private final Color color;

	EnumTerrain( boolean userOption, boolean flamable, boolean burning, Color color ) {
		this.userOption = userOption;
		this.flamable = flamable;
		this.burning = burning;
		this.color = color;
	}

	public boolean isUserOption() {
		return userOption;
	}

	@Override
	public boolean canBurn() {
		return flamable;
	}

	@Override
	public boolean tryBurn() {
		return false;
	}

	@Override
	public boolean isBurning() {
		return burning;
	}

	public Color getColor() {
		return color;
	}

	public static EnumTerrain getUserDefault() {
		return EnumTerrain.WILD;
	}

	public static EnumTerrain find( boolean flamable, boolean burning ) {
		for ( EnumTerrain terrain : EnumTerrain.values() ) if ( terrain.isUserOption() ) {
			if ( terrain.flamable == flamable && terrain.burning == burning ) return terrain;
		}
		return EnumTerrain.NULL;
	}

}

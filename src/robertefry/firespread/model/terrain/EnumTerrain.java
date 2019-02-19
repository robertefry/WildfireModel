
package robertefry.firespread.model.terrain;

import java.awt.Color;

public enum EnumTerrain implements Flamable {

	NULL( -1, -1, false, false, Color.WHITE ),
	WILD( 0, 1, true, false, Color.GREEN ),
	BURNING( 1, 2, true, true, Color.RED ),
	CLEARED( 2, 3, false, false, Color.BLACK ),
	WATER( 3, 0, false, false, Color.BLUE );

	private final int id, cycle;
	private final boolean flamable;
	private final boolean burning;
	private final Color color;

	EnumTerrain( int id, int cycle, boolean flamable, boolean burning, Color color ) {
		this.id = id;
		this.cycle = cycle;
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

	@Override
	public boolean isBurning() {
		return burning;
	}

	public Color getColor() {
		return color;
	}

	public static EnumTerrain find( boolean flamable, boolean burning ) {
		for ( EnumTerrain terrain : EnumTerrain.values() ) if ( terrain.id >= 0 ) {
			if ( terrain.flamable == flamable && terrain.burning == burning ) return terrain;
		}
		return EnumTerrain.NULL;
	}

	public static EnumTerrain cycle( EnumTerrain terrain ) {
		for ( EnumTerrain element : EnumTerrain.values() ) {
			if ( element.id == terrain.cycle ) return element;
		}
		return terrain;
	}

}

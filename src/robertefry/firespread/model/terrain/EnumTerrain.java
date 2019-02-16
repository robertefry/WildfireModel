
package robertefry.firespread.model.terrain;

public enum EnumTerrain implements Flamable {

	WILD( true, false ),
	BURNING( true, true ),
	CLEARED( false, false );

	private final boolean flamable;
	private final boolean burning;

	EnumTerrain( boolean flamable, boolean burning ) {
		this.flamable = flamable;
		this.burning = burning;
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

	public static EnumTerrain find( boolean flamable, boolean burning ) {
		for ( EnumTerrain terrain : EnumTerrain.values() ) {
			if ( terrain.flamable == flamable && terrain.burning == burning ) return terrain;
		}
		return null;
	}

	public static EnumTerrain cycle( EnumTerrain terrain ) {
		int index = ( terrain.ordinal() + 1 ) % EnumTerrain.values().length;
		return EnumTerrain.values()[index];
	}

}


package robertefry.firespread.model.grid;

public enum EnumCellState {

	WILD( true, false ),
	BURNING( true, true ),
	CLEARED( false, false );

	public final boolean canBurn;
	public final boolean isBurning;

	EnumCellState( boolean canBurn, boolean isBurning ) {
		this.canBurn = canBurn;
		this.isBurning = isBurning;
	}

}

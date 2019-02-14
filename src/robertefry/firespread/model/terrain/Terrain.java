
package robertefry.firespread.model.terrain;

import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.TargetAdapter;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class Terrain implements TargetAdapter, Flamable {

	private float material;
	private EnumTerrain state;

	public Terrain( float material ) {
		this( material, false );
	}

	public Terrain( float material, boolean burning ) {
		this.material = material;
		this.state = burning ? EnumTerrain.BURNING : material > 0 ? EnumTerrain.WILD : EnumTerrain.CLEARED;
	}

	public Terrain( Terrain terrain ) {
		this.material = terrain.material;
		this.state = terrain.state;
	}

	public void copyfrom( Terrain terrain ) {
		this.material = terrain.material;
		this.state = terrain.state;
	}

	public void cycleState() {
		int index = ( state.ordinal() + 1 ) % EnumTerrain.values().length;
		state = EnumTerrain.values()[index];
	}

	@Override
	public void tick( Engine engine ) {
		TargetAdapter.super.tick( engine );
		if ( isBurning() ) material = Math.max( 0, material-- );
		if ( !canBurn() ) state = EnumTerrain.CLEARED;
	}

	@Override
	public boolean canBurn() {
		return state.canBurn() && material > 0;
	}

	@Override
	public boolean tryBurn() {
		boolean successful = false;
		if ( canBurn() ) {
			state = EnumTerrain.BURNING;
			successful = true;
		}
		return successful;
	}

	@Override
	public boolean isBurning() {
		return state.isBurning();
	}

	public EnumTerrain getState() {
		return state;
	}

	public float getMaterial() {
		return material;
	}

}

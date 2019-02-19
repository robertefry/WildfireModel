
package robertefry.firespread.model.terrain;

import java.awt.Color;
import robertefry.penguin.target.Updatable;

/**
 * @author Robert E Fry
 * @date 19 Feb 2019
 */
public class Terrain implements Cyclic, Updatable, Flamable {

	private float material;
	private EnumTerrain state;

	public Terrain( float material, boolean burning ) {
		this.material = material;
		this.state = EnumTerrain.find( material > 0, burning );
	}

	public Terrain( Terrain terrain ) {
		this.material = terrain.material;
		this.state = terrain.state;
	}

	@Override
	public void cycle() {
		state = EnumTerrain.cycle( state );
	}

	@Override
	public void update() {
		if ( isBurning() ) material = Math.max( 0, material - 1 );
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

	public Color getDrawColor() {
		return state.getColor();
	}

}

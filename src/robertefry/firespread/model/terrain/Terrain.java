
package robertefry.firespread.model.terrain;

import java.awt.Color;
import java.io.Serializable;
import robertefry.penguin.target.api.Updatable;

// TODO rework

/**
 * @author Robert E Fry
 * @date 19 Feb 2019
 */
public class Terrain implements Updatable, Flamable, Serializable {
	private static final long serialVersionUID = 163053639252165241L;

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

	public Terrain() {
		this.material = 1.0f;
		this.state = EnumTerrain.getUserDefault();
	}

	@Override
	public void update() {
		if ( isBurning() ) {
			material = Math.max( 0, material - 1 );
			if ( !canBurn() ) state = EnumTerrain.CLEARED;
		}
		
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

	public void setState( EnumTerrain state ) {
		this.state = state;
	}

	public Color getDrawColor() {
		return state.getColor();
	}

}

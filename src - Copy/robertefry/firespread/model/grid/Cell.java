
package robertefry.firespread.model.grid;

import java.awt.Dimension;
import java.awt.Point;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Cell implements Targetable {

	private final Point location;
	private final Dimension size;
	private final Terrain terrain;

	public Cell( Point location, Dimension size, Terrain terrain ) {
		this.location = location;
		this.size = size;
		this.terrain = terrain;
	}

	//	public void iterateState() {
	//		int index = ( state.ordinal() + 1 >= EnumCellState.values().length ) ? 0 : state.ordinal() + 1;
	//		state = EnumCellState.values()[index];
	//		Model.getEngine().forceRender();
	//	}
	//
	//	@Override
	//	public void tick( Engine engine ) {
	//		Targetable.super.tick( engine );
	//		if ( canBurn() && isBurning() ) {
	//			flamability--;
	//		}
	//		if ( !canBurn() ) {
	//			state = EnumCellState.CLEARED;
	//		}
	//	}
	//
	//	@Override
	//	public void render( Engine engine ) {
	//		Targetable.super.render( engine );
	//		state.drawCall.accept( Renderer.getGraphics(), rect );
	//	}
	//
	//	// TODO change to EnumCellState not Cell
	//	// change grid next() method to update existing cells, not replace
	//	public Cell getNext( Set< Cell > localRegion ) {
	//		// TODO review Cell::getNext( Set<Cell> )
	//		localRegion.forEach( cell -> {
	//			if ( cell.isBurning() ) {
	//				if ( canBurn() ) state = EnumCellState.BURNING;
	//			}
	//		} );
	//		return this;
	//	}

}

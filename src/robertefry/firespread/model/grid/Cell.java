
package robertefry.firespread.model.grid;

import java.awt.Rectangle;
import java.util.Set;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.Model;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Cell implements Targetable {

	private final Rectangle rect;
	private final float elevation;
	private float flamability;
	private EnumCellState state = EnumCellState.WILD;

	public Cell( Rectangle rect, float elevation, float flamability ) {
		this.rect = rect;
		this.elevation = elevation;
		this.flamability = flamability;
	}

	public void iterateState() {
		int index = (state.ordinal() + 1 >= EnumCellState.values().length) ? 0 : state.ordinal() + 1;
		state = EnumCellState.values()[index];
		Model.getEngine().forceRender();
	}

	public boolean canBurn() {
		return state.canBurn && flamability > 0;
	}

	public boolean isBurning() {
		return state.isBurning;
	}

	@Override
	public void tick( Engine engine ) {
		Targetable.super.tick( engine );
		if (canBurn() && isBurning()) {
			flamability--;
		}
		if (!canBurn()) {
			state = EnumCellState.CLEARED;
		}
	}

	@Override
	public void render( Engine engine ) {
		Targetable.super.render( engine );
		state.drawCall.accept( Renderer.getGraphics(), rect );
	}

	public Cell getNext( Set<Cell> localRegion ) {
		// TODO review Cell::getNext( Set<Cell> )
		// TODO fix local region
		localRegion.forEach( cell -> {
			if (cell.isBurning()) {
				if (canBurn()) state = EnumCellState.BURNING;
			}
		} );
		return this;
	}

	public Rectangle getBounds() {
		return rect;
	}

}

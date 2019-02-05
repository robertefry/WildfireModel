
package robertefry.firespread.model.grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Set;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.Model;
import robertefry.firespread.util.RenderUtil;
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
		Graphics g = Renderer.getGraphics();
		g.setColor( new Color( 0.5f, 0.5f, 0.5f, 1.0f ) );
		g.drawRect( rect.x, rect.y, rect.width, rect.height );
		if (isBurning()) {
			g.setColor( new Color( 1.0f, 0.0f, 0.0f, 1.0f ) );
			RenderUtil.drawCross(
				g, new Point( rect.x, rect.y ), new Point( rect.x + rect.width, rect.y ),
				new Point( rect.x, rect.y + rect.height ), new Point( rect.x + rect.width, rect.y + rect.height )
			);
		}
	}

	public Cell getNext( Set<Cell> localRegion ) {
		// TODO review Cell::getNext( Set<Cell> )
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

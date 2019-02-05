
package robertefry.firespread.model.grid;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Set;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.util.Colors;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Cell implements Targetable {

	private final Rectangle rect;
	private final Terrain terrain;

	public Cell( Rectangle rect, Terrain terrain ) {
		this.rect = rect;
		this.terrain = terrain;
	}

	@Override
	public void tick( Engine engine ) {
		Targetable.super.tick( engine );
		terrain.tick( engine );
	}

	@Override
	public void render( Engine engine ) {
		Targetable.super.render( engine );
		Graphics g = Renderer.getGraphics();
		g.setColor( Colors.newRandomColor() );
		g.fillRect( rect.x, rect.y, rect.width, rect.height );
	}

	public Cell getNext( Set<Cell> localRegion ) {
		//TODO Cell::getNext( Set<Cell> )
		return this;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( !( obj instanceof Cell ) ) return false;
		if ( !( (Cell)obj ).rect.equals( rect ) ) return false;
		if ( !( (Cell)obj ).terrain.equals( terrain ) ) return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format( "Cell[rect=%s,terrain=%s]", rect, terrain );
	}

}

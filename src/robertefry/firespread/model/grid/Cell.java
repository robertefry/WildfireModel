
package robertefry.firespread.model.grid;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.terrain.Cyclic;
import robertefry.firespread.model.terrain.Terrain;
import robertefry.firespread.util.GraphicUtil;
import robertefry.penguin.target.TargetBlank;

/**
 * @author Robert E Fry
 * @date 19 Feb 2019
 */
public class Cell extends TargetBlank implements Cyclic {

	private final float elevation;
	private Terrain terrain, next;

	private Rectangle bounds = new Rectangle();

	public Cell( float elevation, Terrain terrain ) {
		this.elevation = elevation;
		this.terrain = terrain;
		this.next = new Terrain( terrain );
	}

	public Cell( Cell cell ) {
		this.elevation = cell.elevation;
		this.terrain = new Terrain( cell.terrain );
		this.next = new Terrain( cell.next );
	}

	@Override
	public void cycle() {
		terrain.cycle();
		next.cycle();
	}

	@Override
	public void update() {
		next.update();
	}

	@Override
	public void render() {
		GraphicUtil.drawRect( Renderer.getGraphics(), bounds, Color.DARK_GRAY );
		GraphicUtil.drawCross( Renderer.getGraphics(), bounds, terrain.getDrawColor() );
	}

	public void prepNext( Map< Point, Cell > cells ) {
		// TODO Cell::prepNext
		cells.values().forEach( cell -> {
			if ( cell.terrain.isBurning() ) next.tryBurn();
		} );
	}

	public void makeNext() {
		terrain = new Terrain( next );
	}

	public void setBounds( Rectangle bounds ) {
		this.bounds = bounds;
	}

}

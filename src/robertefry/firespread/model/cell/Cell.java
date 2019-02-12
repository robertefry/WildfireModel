
package robertefry.firespread.model.cell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.type.Elevation;
import robertefry.firespread.model.type.Terrain;
import robertefry.firespread.util.GraphicUtil;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.TargetAdapter;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class Cell implements TargetAdapter {

	public static final int LOCAL_RADIUS = 1;

	private Point location;
	private Rectangle drawspace = new Rectangle( 0, 0, 0, 0 );
	private Elevation elevation;
	private Terrain terrain, next;

	public Cell( Point location, Elevation elevation, Terrain terrain ) {
		this.location = location;
		this.elevation = elevation;
		this.terrain = terrain;
	}

	public void setDrawspace( Rectangle drawspace ) {
		this.drawspace = drawspace;
	}

	@Override
	public void render( Engine engine ) {
		TargetAdapter.super.render( engine );
		Graphics g = Renderer.getGraphics();
		Color crossColor = terrain.isBurning() ? Color.RED : terrain.canBurn() ? Color.GREEN : Color.GRAY;
		GraphicUtil.drawCross( g, drawspace, crossColor );
		GraphicUtil.drawRect( g, drawspace, Color.GRAY );
	}

	public void prepNext( Map< Point, Cell > cells ) {
		// TODO Cell::prepNext
	}

	public void makeNext() {
		// TODO Cell::makeNext
	}

	public Point getLocation() {
		return location;
	}

	public Elevation getElevation() {
		return elevation;
	}

	public Terrain getTerrain() {
		return terrain;
	}

}

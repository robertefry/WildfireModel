
package robertefry.firespread.model.cell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.terrain.Terrain;
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
	private float elevation;
	private Terrain terrain, next;

	public Cell( Point location, float elevation, Terrain terrain ) {
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
		GraphicUtil.drawRect( g, drawspace, Color.GRAY );
		GraphicUtil.drawCross( g, drawspace, terrain.getState().getColor() );
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

	public float getElevation() {
		return elevation;
	}

	public Terrain getTerrain() {
		return terrain;
	}

}


package robertefry.firespread.model.grid;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.terain.Terrain;
import robertefry.firespread.util.GraphicUtil;
import robertefry.penguin.target.TargetBlank;

/**
 * @author Robert E Fry
 * @date 22 Feb 2019
 */
public class Cell extends TargetBlank {
	
	private final Point point;
	private Terrain terrain;
	
	private final Rectangle bounds = new Rectangle();
	
	public Cell( Point point, Terrain terrain ) {
		this.point = point;
		this.terrain = terrain;
	}
	
	public Cell( Cell cell ) {
		this.point = new Point( cell.point );
		this.terrain = new Terrain( cell.terrain );
	}
	
	public void trySpread( Cell cell ) {
		if ( terrain.isBurning() ) cell.getTerrain().tryIgnite();
	}
	
	@Override
	public void update() {
		terrain.update();
	}
	
	@Override
	public void render() {
		GraphicUtil.drawRect( Renderer.getGraphics(), bounds, Color.DARK_GRAY );
		GraphicUtil.drawCross( Renderer.getGraphics(), bounds, terrain.getState().getDrawColor() );
	}
	
	public Point getPoint() {
		return point;
	}
	
	public Terrain getTerrain() {
		return terrain;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public void setBounds( Rectangle r ) {
		bounds.setBounds( r );
	}
	
}

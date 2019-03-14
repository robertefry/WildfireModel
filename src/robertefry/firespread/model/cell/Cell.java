
package robertefry.firespread.model.cell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import robertefry.firespread.model.spread.Spread;
import robertefry.firespread.model.terrain.Terrain;
import robertefry.firespread.util.GraphicUtil;
import robertefry.penguin.target.TargetBlank;

/**
 * @author Robert E Fry
 * @date 22 Feb 2019
 */
public class Cell extends TargetBlank {
	
	private Point location;
	private Terrain terrain;
	private final Rectangle bounds = new Rectangle();
	
	public Cell( Point location, Terrain terrain ) {
		this.location = location;
		this.terrain = terrain;
	}
	
	public Cell( Cell cell ) {
		this.location = new Point( cell.location );
		this.terrain = new Terrain( cell.terrain );
	}
	
	public boolean trySpread( Cell cell ) {
		boolean ignite = false;
		if ( Spread.pass( this, cell ) ) {
			if ( terrain.isBurning() ) {
				ignite = cell.getTerrain().tryIgnite();
			}
		}
		return ignite;
	}
	
	@Override
	public void update() {
		terrain.update();
	}
	
	@Override
	public void render( Graphics g ) {
		if ( ( bounds.width | bounds.height ) > 2 ) GraphicUtil.drawRect( g, bounds, Color.DARK_GRAY );
		GraphicUtil.drawCross( g, bounds, terrain.getState().getDrawColor() );
	}
	
	public Point getLocation() {
		return location;
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

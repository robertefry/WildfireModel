
package robertefry.firespread.model.grid;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.Map;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.Model;
import robertefry.firespread.model.terrain.EnumTerrain;
import robertefry.firespread.model.terrain.Terrain;
import robertefry.firespread.util.GraphicUtil;
import robertefry.penguin.target.TargetBlank;

// TODO rework

/**
 * @author Robert E Fry
 * @date 19 Feb 2019
 */
public class Cell extends TargetBlank implements Serializable {
	private static final long serialVersionUID = -3964239828345858975L;

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

	public Cell() {
		this.elevation = 0.0f;
		this.terrain = new Terrain();
		this.next = new Terrain();
	}

	@Override
	public void update() {
		next.update();
	}

	@Override
	public void render() {
		if ( Model.CellRenderHints.DrawCellBorder ) {
			GraphicUtil.drawRect( Renderer.getGraphics(), bounds, Color.DARK_GRAY );
			GraphicUtil.drawCross( Renderer.getGraphics(), bounds, terrain.getDrawColor() );
		}
	}
	
	public void trySpread( Cell cell ) {
		
	}

	public void prepNext( Map< Point, Cell > cells ) {
		// TODO Cell::prepNext
		cells.values().forEach( cell -> {
			if ( cell.terrain.isBurning() ) next.tryBurn();
		} );
	}

	public void setState( EnumTerrain state ) {
		terrain.setState( state );
		next.setState( state );
	}

	public void makeNext() {
		terrain = new Terrain( next );
	}

	public void setBounds( Rectangle bounds ) {
		this.bounds = bounds;
	}

	public Terrain getTerrain() {
		return terrain;
	}

}

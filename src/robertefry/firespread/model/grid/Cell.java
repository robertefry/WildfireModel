
package robertefry.firespread.model.grid;

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

public class Cell implements TargetAdapter {

	private float elevation;
	private Terrain terrain, next;

	private Rectangle drawspace = new Rectangle( 0, 0, 0, 0 );

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

	public void cycleState() {
		terrain.cycleState();
		next.cycleState();
	}

	@Override
	public void tick( Engine engine ) {
		TargetAdapter.super.tick( engine );
		next.tick( engine );
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
		cells.values().forEach( cell -> {
			if ( cell.terrain.isBurning() ) next.tryBurn();
		});
	}

	public void makeNext() {
		terrain.copyfrom( next );
	}

	public void setDrawspace( Rectangle drawspace ) {
		this.drawspace = drawspace;
	}

}


package robertefry.firespread.model.grid;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;
import robertefry.firespread.math.GridSpace;
import robertefry.firespread.model.cell.Cell;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.TargetAdapter;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class Grid implements TargetAdapter {

	public static final int LOCAL_RADIUS = 1;

	private final GridSpace space = new GridSpace();
	private final Map< Point, Cell > cells = new HashMap<>();
	private Rectangle drawSpace = new Rectangle( 0, 0, 0, 0 );

	public void rebuild( Map< Point, Cell > cells ) {
		this.space.setBounds( 0, 0, 0, 0 );
		this.cells.clear();
		cells.forEach( ( point, cell ) -> {
			this.space.put( point );
			this.cells.put( point, cell );
		} );
		setCellDrawspace();
	}

	public void setDrawSpace( Rectangle drawSpace ) {
		this.drawSpace = drawSpace;
		setCellDrawspace();
	}

	public void setDrawSpace( Dimension dimension ) {
		int size = Math.min( dimension.width, dimension.height );
		int x = (int)( ( dimension.width - size ) / 2 );
		int y = (int)( ( dimension.height - size ) / 2 );
		setDrawSpace( new Rectangle( x, y, size, size ) );
	}

	public void setCellDrawspace() {
		if ( space.getWidth() * space.getHeight() == 0 ) return;
		int width = drawSpace.width / space.getWidth();
		int height = drawSpace.height / space.getHeight();
		cells.values().forEach( cell -> {
			int x = width * cell.getLocation().x;
			int y = height * cell.getLocation().y;
			cell.setDrawspace( new Rectangle( drawSpace.x + x, drawSpace.y + y, width, height ) );
		} );
	}

	@Override
	public void render( Engine engine ) {
		TargetAdapter.super.render( engine );
		cells.values().forEach( cell -> {
			cell.render( engine );
		} );
	}

	@Override
	public void tick( Engine engine ) {
		TargetAdapter.super.tick( engine );
		cells.forEach( ( point, cell ) -> {
			cell.prepNext( cells );
		} );
		cells.values().forEach( cell -> {
			cell.makeNext();
		} );
	}

}

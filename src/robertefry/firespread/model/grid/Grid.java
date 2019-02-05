
package robertefry.firespread.model.grid;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.Model;
import robertefry.firespread.model.map.CellSet;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Target;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Grid extends Target {

	private CellSet cells = new CellSet();

	public void build( CellSet cells ) {
		this.cells.forEach( cell -> {
			removeSubTarget( cell );
		} );
		this.cells = cells;
		cells.forEach( cell -> {
			addSubTarget( cell );
		} );
		Model.getEngine().addPreCycleTask( () -> {
			Dimension size = Renderer.getCanvas().getSize();
			Renderer.getGraphics().clearRect( 0, 0, size.width, size.height );
		} );
		Model.getEngine().forceRender();
	}

	public void interceptClick( Point point ) {
		Cell cell = cells.getCell( point );
		cell.iterateState();
	}

	@Override
	public void tick( Engine engine ) {
		super.tick( engine );
		Map<Point,Cell> nextcells = new HashMap<>();
		// TODO get next cells
	}

}

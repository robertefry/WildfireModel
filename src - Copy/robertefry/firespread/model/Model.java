
package robertefry.firespread.model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.grid.Grid;
import robertefry.penguin.engine.Engine;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Model {

	private static final Engine engine = new Engine();
	private static final Grid grid = new Grid();

	static {

		Renderer.getCanvas().addMouseListener( new MouseAdapter() {
			public void mouseClicked( MouseEvent e ) {
				grid.interceptClick( e.getPoint() );
			}
		} );

		engine.getTargetManager().add( grid );
		engine.setRefreshRate( 1 );
		engine.suspend();
		engine.start();

	}

	public static Engine getEngine() {
		return engine;
	}

	public static Grid getGrid() {
		return grid;
	}

}

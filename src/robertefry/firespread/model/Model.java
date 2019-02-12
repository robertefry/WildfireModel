
package robertefry.firespread.model;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
				// TODO click intercepts
				// grid.interceptClick( e.getPoint() );
			}
		} );

		Renderer.getCanvas().addComponentListener( new ComponentAdapter() {
			public void componentResized( ComponentEvent e ) {
				Model.grid.setDrawSpace( Renderer.getCanvas().getSize() );
				Model.getEngine().forceRender();
			};
		} );

		Model.engine.getTargetManager().add( grid );
		Model.engine.setRefreshRate( 1 );
		Model.engine.suspend();
		Model.engine.start();

	}

	public static Engine getEngine() {
		return engine;
	}

	public static Grid getGrid() {
		return grid;
	}

}

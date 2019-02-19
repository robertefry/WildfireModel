
package robertefry.firespread.model;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.grid.CellRenderHints;
import robertefry.firespread.model.grid.Grid;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.input.keyboard.Keyboard;
import robertefry.penguin.input.keyboard.Keys;
import robertefry.penguin.input.keyboard.listener.KeyboardAdapter;
import robertefry.penguin.input.mouse.Mouse;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Model {

	public static final CellRenderHints CellRenderHints = new CellRenderHints();

	private static final Engine engine = new Engine();
	private static final Keyboard keyboard = new Keyboard();
	private static final Mouse mouse = new Mouse();

	private static final Grid grid = new Grid();

	static {

		// TODO save grid states for later loading

		Renderer.getComponent().addComponentListener( new ComponentAdapter() {
			@Override
			public void componentResized( ComponentEvent e ) {
				grid.setSize( Renderer.getComponent().getSize() );
				engine.forceRender();
			}
		} );

		keyboard.addKeyboardListener( new KeyboardAdapter() {
			@Override
			public void onKeyPress( KeyEvent e ) {
				if ( e.getID() == Keys.KEY_R ) engine.forceRender();
			}
		} );

		keyboard.register( Renderer.getComponent() );
		Model.engine.getEngineInputRecievers().add( keyboard );

		mouse.register( Renderer.getComponent() );
		Model.engine.getEngineInputRecievers().add( mouse );

		Model.engine.getTargetManager().add( grid );

		Model.engine.setRefreshRate( 1 );
		Model.engine.suspend();
		Model.engine.start();

	}

	public static Grid getGrid() {
		return grid;
	}

	public static Engine getEngine() {
		return engine;
	}

	public static Keyboard getKeyboard() {
		return keyboard;
	}

	public static Mouse getMouse() {
		return mouse;
	}

}

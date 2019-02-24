
package robertefry.firespread.model;

import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.grid.Grid;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.listener.EngineLogicAdapter;
import robertefry.penguin.input.keyboard.Keyboard;
import robertefry.penguin.input.mouse.Mouse;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Model {
	
	private static final Engine engine = new Engine();
	private static final Keyboard keyboard = new Keyboard();
	private static final Mouse mouse = new Mouse();
	
	private static final Grid grid = new Grid();
	
	static {
		
		engine.getEngineLogicListeners().add( new EngineLogicAdapter() {
			@Override
			public void postReset() {
				engine.suspend();
			}
		} );
		
		keyboard.register( Renderer.getCanvas() );
		Model.engine.getEngineInputRecievers().add( keyboard );
		
		mouse.register( Renderer.getCanvas() );
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


package robertefry.firespread.model;

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
		engine.getTargetManager().add( grid );
		engine.suspend();
		engine.start();
	}
	
	public static Engine getEngine() {
		return engine;
	}

}

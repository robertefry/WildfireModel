
package robertefry.firespread;

import robertefry.firespread.model.Model;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.element.FrameCounter;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Main {

	private static final Engine engine = new Engine();
	private static final Model model = new Model();

	public static void main( String[] args ) {
		engine.getTargetManager().add( new FrameCounter( System.out, 1000 ) );
		engine.getTargetManager().add( model );
		engine.start();
	}

}


package robertefry.firespread;

import robertefry.firespread.model.Model;
import robertefry.penguin.engine.Engine;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class Main {
	
	public static void main( String[] args ) {
		Main main = new Main();
		main.startEngine();
	}
	
	private final Engine engine = new Engine();
	private final Model model = new Model();
	
	public Main() {
		engine.getTargetManager().add( model );
	}
	
	public void startEngine() {
		engine.start();
	}

}


package robertefry.firespread.main;

import robertefry.firespread.model.Model;

public class Main {

	private final Model model = new Model( 2, 2, "res/TerrainMap.csv", "res/WindMap.csv" );

	public static void main( String[] args ) {
		Main main = new Main();
		main.initialise();
	}

	public void initialise() {
		model.start();
	}

}

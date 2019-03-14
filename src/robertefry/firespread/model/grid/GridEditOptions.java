
package robertefry.firespread.model.grid;

import java.util.Arrays;
import java.util.List;
import robertefry.firespread.model.terrain.TerrainState;

/**
 * @author Robert E Fry
 * @date 20 Feb 2019
 */
public class GridEditOptions {
	
	private static TerrainState selection = TerrainState.getDefault();
	private static double pensize = 1;
	
	public static List< TerrainState > getOptionSet() {
		return Arrays.asList( TerrainState.values() );
	}
	
	public static TerrainState getSelection() {
		return selection;
	}
	
	public static void setSelection( TerrainState selection ) {
		GridEditOptions.selection = selection;
	}
	
	public static double getPenSize() {
		return pensize;
	}
	
	public static void setPenSize( double pensize ) {
		GridEditOptions.pensize = pensize;
	}
	
}

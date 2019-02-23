
package robertefry.firespread.ui;

import java.util.Arrays;
import java.util.List;
import robertefry.firespread.model.terain.TerrainState;

/**
 * @author Robert E Fry
 * @date 20 Feb 2019
 */
public class MapEditHints {
	
	private static TerrainState selection = TerrainState.getDefault();
	
	public static List< TerrainState > getMapEditOptionSet() {
		return Arrays.asList( TerrainState.values() );
	}
	
	public static TerrainState getEditSelection() {
		return selection;
	}
	
	public static void setEditSelection( TerrainState selection ) {
		MapEditHints.selection = selection;
	}
	
}

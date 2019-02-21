
package robertefry.firespread.ui;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import robertefry.firespread.model.terrain.EnumTerrain;

/**
 * @author Robert E Fry
 * @date 20 Feb 2019
 */
public class MapEditHints {

	private static EnumTerrain selection = EnumTerrain.getUserDefault();

	public static List< EnumTerrain > getMapEditOptionSet() {
		return Stream.of( EnumTerrain.values() ).filter( EnumTerrain::isUserOption ).collect( Collectors.toList() );
	}

	public static EnumTerrain getEditSelection() {
		return selection;
	}

	public static void setEditSelection( EnumTerrain selection ) {
		MapEditHints.selection = selection;
	}

}

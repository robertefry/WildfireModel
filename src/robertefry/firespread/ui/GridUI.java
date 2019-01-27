
package robertefry.firespread.ui;

import java.io.File;
import javax.swing.JPanel;
import org.apache.commons.logging.LogFactory;
import robertefry.firespread.model.grid.Grid;
import robertefry.firespread.model.map.TerrainMap;
import robertefry.firespread.model.map.TypeMap;
import robertefry.firespread.model.type.Terrain;


/**
 * @author Robert E Fry
 * @date 27 Jan 2019
 */
@SuppressWarnings( "serial" )
public class GridUI extends JPanel {
	
	private final Grid grid;

	/**
	 * Create the panel.
	 */
	public GridUI( Grid grid ) {
		this.grid = grid;
	}
	
	public void build( File file ) {
		TypeMap<Terrain> terrainmap = TypeMap.populateFromCSVFile( new TerrainMap(), file );
		grid.build( terrainmap );
		LogFactory.getLog( getClass() ).info( "grid build from " + file.getAbsolutePath() );
	}

}

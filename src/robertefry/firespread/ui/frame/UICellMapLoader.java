
package robertefry.firespread.ui.frame;

import java.awt.Dimension;
import javax.swing.SpringLayout;
import robertefry.firespread.model.map.CellMap;
import robertefry.firespread.ui.dialog.UIDialog;
import robertefry.firespread.ui.element.ICImageMapLoading;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class UICellMapLoader extends UIDialog<CellMap> {
	private static final long serialVersionUID = 1557112471549371181L;

	private final ICImageMapLoading srcElevationMap = new ICImageMapLoading( "Elevation map", true );
	private final ICImageMapLoading srcTerrainMap = new ICImageMapLoading( "Terrain map", true );
	private final ICImageMapLoading srcMapTexture = new ICImageMapLoading( "Map Texture", false );

	public UICellMapLoader() {

		setTitle( "New map" );
		contentPane.setLayout( new SpringLayout() );
		contentPane.setPreferredSize( new Dimension( 446, 116 ) );

		SpringLayout layout = new SpringLayout();
		contentPane.setLayout( layout );

		layout.putConstraint( SpringLayout.NORTH, srcElevationMap, 10, SpringLayout.NORTH, contentPane );
		layout.putConstraint( SpringLayout.WEST, srcElevationMap, 10, SpringLayout.WEST, contentPane );
		layout.putConstraint( SpringLayout.EAST, srcElevationMap, -10, SpringLayout.EAST, contentPane );
		contentPane.add( srcElevationMap );

		layout.putConstraint( SpringLayout.NORTH, srcTerrainMap, 10, SpringLayout.SOUTH, srcElevationMap );
		layout.putConstraint( SpringLayout.WEST, srcTerrainMap, 10, SpringLayout.WEST, contentPane );
		layout.putConstraint( SpringLayout.EAST, srcTerrainMap, -10, SpringLayout.EAST, contentPane );
		contentPane.add( srcTerrainMap );

		layout.putConstraint( SpringLayout.NORTH, srcMapTexture, 10, SpringLayout.SOUTH, srcTerrainMap );
		layout.putConstraint( SpringLayout.WEST, srcMapTexture, 10, SpringLayout.WEST, contentPane );
		layout.putConstraint( SpringLayout.EAST, srcMapTexture, -10, SpringLayout.EAST, contentPane );
		contentPane.add( srcMapTexture );
		
		pack();

	}

	@Override
	protected CellMap getReturn() {
		// TODO UICellMapLoader getReturn()
		return null;
	}

}


package robertefry.firespread.ui.maploader;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import robertefry.firespread.io.Resource;
import robertefry.firespread.model.map.CellMap;
import robertefry.firespread.model.map.Conversions;
import robertefry.firespread.model.map.ImageMap;
import robertefry.firespread.ui.atomic.LabeledComponent;
import robertefry.firespread.ui.dialog.UIDialog;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class UICellMapLoader extends UIDialog<CellMap> {
	private static final long serialVersionUID = 1557112471549371181L;

	private final ICImageMapLoading srcElevationMap = new ICImageMapLoading( "Elevation map", true );
	private final ICImageMapLoading srcFlamabilityMap = new ICImageMapLoading( "Flamability map", true );
	private final ICImageMapLoading srcMapTexture = new ICImageMapLoading( "Map Texture", false );

	LabeledComponent<JSpinner> spnRows = new LabeledComponent<>(
		"rows", new JSpinner( new SpinnerNumberModel( 1, 1, Integer.MAX_VALUE, 1 ) )
	);
	LabeledComponent<JSpinner> spnCols = new LabeledComponent<>(
		"columns", new JSpinner( new SpinnerNumberModel( 1, 1, Integer.MAX_VALUE, 1 ) )
	);

	public UICellMapLoader() {

		setTitle( "New map" );
		contentPane.setPreferredSize( new Dimension( 446, 147 ) );

		SpringLayout layout = new SpringLayout();
		contentPane.setLayout( layout );

		layout.putConstraint( SpringLayout.NORTH, srcElevationMap, 10, SpringLayout.NORTH, contentPane );
		layout.putConstraint( SpringLayout.WEST, srcElevationMap, 10, SpringLayout.WEST, contentPane );
		layout.putConstraint( SpringLayout.EAST, srcElevationMap, -10, SpringLayout.EAST, contentPane );
		contentPane.add( srcElevationMap );

		layout.putConstraint( SpringLayout.NORTH, srcFlamabilityMap, 10, SpringLayout.SOUTH, srcElevationMap );
		layout.putConstraint( SpringLayout.WEST, srcFlamabilityMap, 10, SpringLayout.WEST, contentPane );
		layout.putConstraint( SpringLayout.EAST, srcFlamabilityMap, -10, SpringLayout.EAST, contentPane );
		contentPane.add( srcFlamabilityMap );

		layout.putConstraint( SpringLayout.NORTH, srcMapTexture, 10, SpringLayout.SOUTH, srcFlamabilityMap );
		layout.putConstraint( SpringLayout.WEST, srcMapTexture, 10, SpringLayout.WEST, contentPane );
		layout.putConstraint( SpringLayout.EAST, srcMapTexture, -10, SpringLayout.EAST, contentPane );
		contentPane.add( srcMapTexture );

		JPanel panel = new JPanel();
		panel.setPreferredSize( new Dimension( 10, 22 ) );
		layout.putConstraint( SpringLayout.NORTH, panel, 10, SpringLayout.SOUTH, srcMapTexture );
		layout.putConstraint( SpringLayout.WEST, panel, 10, SpringLayout.WEST, contentPane );
		layout.putConstraint( SpringLayout.EAST, panel, -10, SpringLayout.EAST, contentPane );
		contentPane.add( panel );
		panel.setLayout( new GridLayout( 0, 3, 0, 0 ) );

		spnRows.getLabel().setText( "Rows " );
		spnRows.getLabel().setHorizontalAlignment( SwingConstants.RIGHT );
		spnRows.getLabel().setPreferredSize( new Dimension( 50, 14 ) );
		panel.add( spnRows );

		spnCols.getLabel().setText( "Columns " );
		spnCols.getLabel().setHorizontalAlignment( SwingConstants.RIGHT );
		spnCols.getLabel().setPreferredSize( new Dimension( 50, 14 ) );
		panel.add( spnCols );

		pack();

	}

	@Override
	protected boolean canReturn() {
		return !srcElevationMap.getText().isEmpty() && !srcFlamabilityMap.getText().isEmpty();
	}

	@Override
	protected CellMap getReturn() {
		ImageMap elevationmap = new ImageMap(
			Resource.loadImage( srcElevationMap.getText() ), srcElevationMap.getSelection()
		);
		ImageMap flamabilitymap = new ImageMap(
			Resource.loadImage( srcFlamabilityMap.getText() ), srcFlamabilityMap.getSelection()
		);
		return new CellMap(
			( (Number)spnRows.getComponent().getValue() ).intValue(),
			( (Number)spnCols.getComponent().getValue() ).intValue(),
			elevationmap, Conversions.getElevationMapConversion(),
			flamabilitymap, Conversions.getFlamabilityMapConversion()
		);
	}
}

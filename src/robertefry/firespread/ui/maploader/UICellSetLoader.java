
package robertefry.firespread.ui.maploader;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import robertefry.firespread.io.ImageResource;
import robertefry.firespread.model.grid.Cell;
import robertefry.firespread.model.map.CellMap;
import robertefry.firespread.model.map.ImageMap;
import robertefry.firespread.ui.atomic.ICLabeledComponent;
import robertefry.firespread.ui.dialog.UIDialog;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class UICellSetLoader extends UIDialog< Map< Point, Cell > > {
	private static final long serialVersionUID = 1557112471549371181L;
	
	private final ICLoaderSingle srcElevationMap = new ICLoaderSingle( "Elevation map", true );
	private final ICLoaderSingle srcFlamabilityMap = new ICLoaderSingle( "Flamability map", true );
	
	ICLabeledComponent< JSpinner > spnRows = new ICLabeledComponent<>(
		"rows", new JSpinner( new SpinnerNumberModel( 1, 1, Integer.MAX_VALUE, 1 ) )
	);
	ICLabeledComponent< JSpinner > spnCols = new ICLabeledComponent<>(
		"columns", new JSpinner( new SpinnerNumberModel( 1, 1, Integer.MAX_VALUE, 1 ) )
	);
	
	public UICellSetLoader() {
		
		setTitle( "New map" );
		
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout( layout );
		
		layout.putConstraint( SpringLayout.NORTH, srcElevationMap, 10, SpringLayout.NORTH, contentPane );
		layout.putConstraint( SpringLayout.EAST, srcElevationMap, -10, SpringLayout.EAST, contentPane );
		layout.putConstraint( SpringLayout.WEST, srcElevationMap, 10, SpringLayout.WEST, contentPane );
		contentPane.add( srcElevationMap );
		
		layout.putConstraint( SpringLayout.NORTH, srcFlamabilityMap, 10, SpringLayout.SOUTH, srcElevationMap );
		layout.putConstraint( SpringLayout.EAST, srcFlamabilityMap, -10, SpringLayout.EAST, contentPane );
		layout.putConstraint( SpringLayout.WEST, srcFlamabilityMap, 10, SpringLayout.WEST, contentPane );
		contentPane.add( srcFlamabilityMap );
		
		JPanel pnlSize = new JPanel();
		pnlSize.setLayout( new GridLayout( 0, 3, 0, 0 ) );
		pnlSize.setPreferredSize( new Dimension( 10, 22 ) );
		
		layout.putConstraint( SpringLayout.NORTH, pnlSize, 10, SpringLayout.SOUTH, srcFlamabilityMap );
		layout.putConstraint( SpringLayout.EAST, pnlSize, -10, SpringLayout.EAST, contentPane );
		layout.putConstraint( SpringLayout.WEST, pnlSize, 10, SpringLayout.WEST, contentPane );
		contentPane.add( pnlSize );
		
		spnRows.getLabel().setText( "Rows " );
		spnRows.getLabel().setHorizontalAlignment( SwingConstants.RIGHT );
		spnRows.getLabel().setPreferredSize( new Dimension( 50, 14 ) );
		pnlSize.add( spnRows );
		
		spnCols.getLabel().setText( "Columns " );
		spnCols.getLabel().setHorizontalAlignment( SwingConstants.RIGHT );
		spnCols.getLabel().setPreferredSize( new Dimension( 50, 14 ) );
		pnlSize.add( spnCols );
		
		contentPane.setPreferredSize( new Dimension( 450, 112 ) );
		pack();
		
	}
	
	@Override
	protected boolean canReturn() {
		return !srcElevationMap.getText().isEmpty() && !srcFlamabilityMap.getText().isEmpty();
	}
	
	@Override
	protected Map< Point, Cell > getReturn() {
		ImageMap elevationmap = new ImageMap(
			ImageResource.loadImage( srcElevationMap.getText() ), srcElevationMap.getSelection()
		);
		ImageMap flamabilitymap = new ImageMap(
			ImageResource.loadImage( srcFlamabilityMap.getText() ), srcFlamabilityMap.getSelection()
		);
		int rows = (int)spnRows.getComponent().getValue();
		int cols = (int)spnCols.getComponent().getValue();
		return CellMap.generate( rows, cols, elevationmap, flamabilitymap );
	}
}

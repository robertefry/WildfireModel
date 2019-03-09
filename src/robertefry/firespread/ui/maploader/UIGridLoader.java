
package robertefry.firespread.ui.maploader;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import robertefry.firespread.model.grid.Cell;
import robertefry.firespread.model.map.CellMap;
import robertefry.firespread.ui.atomic.ICLabeledComponent;
import robertefry.firespread.ui.dialog.UIDialog;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class UIGridLoader extends UIDialog< Map< Point, Cell > > {
	private static final long serialVersionUID = 1557112471549371181L;
	
	ICLabeledComponent< JSpinner > spnRows = new ICLabeledComponent<>(
		"rows", new JSpinner( new SpinnerNumberModel( 1, 1, Integer.MAX_VALUE, 1 ) )
	);
	ICLabeledComponent< JSpinner > spnCols = new ICLabeledComponent<>(
		"columns", new JSpinner( new SpinnerNumberModel( 1, 1, Integer.MAX_VALUE, 1 ) )
	);
	
	public UIGridLoader() {
		
		setTitle( "New map" );
		
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout( layout );
		
		JLabel lblText = new JLabel( "Enter the number of rows and columns for the grid." );
		layout.putConstraint( SpringLayout.NORTH, lblText, 10, SpringLayout.NORTH, contentPane );
		layout.putConstraint( SpringLayout.WEST, lblText, 10, SpringLayout.WEST, contentPane );
		contentPane.add( lblText );
		
		spnRows.getLabel().setPreferredSize( new Dimension( 50, 14 ) );
		layout.putConstraint( SpringLayout.NORTH, spnRows, 10, SpringLayout.SOUTH, lblText );
		layout.putConstraint( SpringLayout.WEST, spnRows, 10, SpringLayout.WEST, contentPane );
		spnRows.getLabel().setText( "Rows" );
		contentPane.add( spnRows );
		
		spnCols.getLabel().setPreferredSize( new Dimension( 50, 14 ) );
		layout.putConstraint( SpringLayout.NORTH, spnCols, 10, SpringLayout.SOUTH, spnRows );
		layout.putConstraint( SpringLayout.WEST, spnCols, 10, SpringLayout.WEST, contentPane );
		spnCols.getLabel().setText( "Columns" );
		contentPane.add( spnCols );
		
		contentPane.setPreferredSize( new Dimension( 300, 94 ) );
		pack();
		
	}
	
	@Override
	protected boolean canReturn() {
		return true;
	}
	
	@Override
	protected Map< Point, Cell > getReturn() {
		int rows = (int)spnRows.getComponent().getValue();
		int cols = (int)spnCols.getComponent().getValue();
		return CellMap.generate( rows, cols );
	}
}


package robertefry.firespread.ui.sidebar.gridedit;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import robertefry.firespread.model.grid.GridEditOptions;
import robertefry.firespread.ui.atomic.ICSpinner;

/**
 * @author Robert E Fry
 * @date 24 Feb 2019
 */
public class UIGridEdit extends JPanel {
	private static final long serialVersionUID = 8009175195973692506L;
	
	public UIGridEdit() {
		
		SpringLayout layout = new SpringLayout();
		setLayout( layout );
		
		JPanel pnlTerrainTypes = new ICEditTerrainType();
		layout.putConstraint( SpringLayout.NORTH, pnlTerrainTypes, 10, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.WEST, pnlTerrainTypes, 10, SpringLayout.WEST, this );
		add( pnlTerrainTypes );
		
		JLabel lblPenSize = new JLabel( "Pen Size: " );
		layout.putConstraint( SpringLayout.NORTH, lblPenSize, 10, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.WEST, lblPenSize, 10, SpringLayout.EAST, pnlTerrainTypes );
		add( lblPenSize );
		
		ICSpinner spnPenSize = new ICSpinner();
		spnPenSize.addPropertyChangeTask( () -> {
			double size = Math.max( spnPenSize.getNumberValue().doubleValue(), 1 );
			spnPenSize.setValue( size );
			GridEditOptions.setPenSize( size );
		} );
		layout.putConstraint( SpringLayout.NORTH, spnPenSize, 6, SpringLayout.SOUTH, lblPenSize );
		layout.putConstraint( SpringLayout.WEST, spnPenSize, 10, SpringLayout.EAST, pnlTerrainTypes );
		spnPenSize.setValue( GridEditOptions.getPenSize() );
		spnPenSize.setPreferredSize( new Dimension( 100, 23 ) );
		add( spnPenSize );
		
		setBorder(
			new TitledBorder( new LineBorder( new Color( 0, 0, 0 ), 1, true ), "Grid Edit Options", TitledBorder.LEADING, TitledBorder.TOP, null, null )
		);
		layout.putConstraint( SpringLayout.SOUTH, this, 10, SpringLayout.SOUTH, pnlTerrainTypes );
		layout.putConstraint( SpringLayout.EAST, this, 10, SpringLayout.EAST, spnPenSize );
		
	}
	
}

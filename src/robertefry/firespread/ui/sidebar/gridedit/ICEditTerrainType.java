
package robertefry.firespread.ui.sidebar.gridedit;

import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;

import robertefry.firespread.model.grid.GridEditOptions;
import robertefry.firespread.model.terain.TerrainState;

/**
 * @author Robert E Fry
 * @date 2 Mar 2019
 */
public class ICEditTerrainType extends JPanel {
	private static final long serialVersionUID = -2669854361822073215L;
	
	public ICEditTerrainType() {
		
		SpringLayout springLayout = new SpringLayout();
		setLayout( springLayout );
		
		ButtonGroup group = new ButtonGroup();
		JComponent lastcomponent = this;
		String position = SpringLayout.NORTH;
		for ( TerrainState state : GridEditOptions.getOptionSet() ) {
			String name = state.name().substring( 0, 1 ).toUpperCase() + state.name().substring( 1 ).toLowerCase();
			JRadioButton rdbtnOption = new JRadioButton( name );
			rdbtnOption.addActionListener( event -> {
				GridEditOptions.setSelection( state );
			} );
			springLayout.putConstraint( SpringLayout.NORTH, rdbtnOption, 4, position, lastcomponent );
			springLayout.putConstraint( SpringLayout.WEST, rdbtnOption, 6, SpringLayout.WEST, this );
			springLayout.putConstraint( SpringLayout.EAST, rdbtnOption, -6, SpringLayout.EAST, this );
			group.add( rdbtnOption );
			add( rdbtnOption );
			if ( state == TerrainState.getDefault() ) {
				rdbtnOption.setSelected( true );
			}
			lastcomponent = rdbtnOption;
			position = SpringLayout.SOUTH;
		}
		
	}
	
}

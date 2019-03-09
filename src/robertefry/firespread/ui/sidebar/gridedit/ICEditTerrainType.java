
package robertefry.firespread.ui.sidebar.gridedit;

import java.awt.Dimension;
import javax.swing.ButtonGroup;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;
import robertefry.firespread.model.grid.GridEditOptions;
import robertefry.firespread.model.terain.TerrainState;

/**
 * @author Robert E Fry
 * @date 2 Mar 2019
 */
public class ICEditTerrainType extends JPanel {
	private static final long serialVersionUID = -2669854361822073215L;
	
	public static void main( String[] args ) {
		JFrame frame = new JFrame();
		JPanel panel = new ICEditTerrainType();
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setContentPane( panel );
		frame.pack();
		frame.setLocationRelativeTo( null );
		frame.setVisible( true );
		System.out.println( panel.getSize() );
	}
	
	public ICEditTerrainType() {
		
		SpringLayout layout = new SpringLayout();
		setLayout( layout );
		
		ButtonGroup group = new ButtonGroup();
		JComponent lastcomponent = this;
		String position = SpringLayout.NORTH;
		
		for ( TerrainState state : GridEditOptions.getOptionSet() ) {
			
			String name = state.name().substring( 0, 1 ).toUpperCase() + state.name().substring( 1 ).toLowerCase();
			JRadioButton rdbtnOption = new JRadioButton( name );
			rdbtnOption.addActionListener( event -> {
				GridEditOptions.setSelection( state );
			} );
			layout.putConstraint( SpringLayout.NORTH, rdbtnOption, 5, position, lastcomponent );
			layout.putConstraint( SpringLayout.EAST, rdbtnOption, -5, SpringLayout.EAST, this );
			layout.putConstraint( SpringLayout.WEST, rdbtnOption, 5, SpringLayout.WEST, this );
			rdbtnOption.setPreferredSize( new Dimension( 100, 23 ) );
			group.add( rdbtnOption );
			add( rdbtnOption );
			
			if ( state == TerrainState.getDefault() ) {
				rdbtnOption.setSelected( true );
			}
			lastcomponent = rdbtnOption;
			position = SpringLayout.SOUTH;
			
		}
		
		setBorder( new EtchedBorder( EtchedBorder.LOWERED, null, null ) );
		setPreferredSize( new Dimension( 100, 28 * GridEditOptions.getOptionSet().size() + 9 ) );
		
	}
	
}


package robertefry.firespread.ui.sidebar.control;

import java.awt.Dimension;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import robertefry.firespread.model.Model;
import robertefry.firespread.ui.atomic.ICSpinner;
import robertefry.penguin.targets.SimpleTimer;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public class ICRefreshRate extends JPanel {
	private static final long serialVersionUID = -2128361965375480595L;
	
	private final ICSpinner spinner = new ICSpinner();
	
	public ICRefreshRate() {
		
		SpringLayout layout = new SpringLayout();
		setLayout( layout );
		
		JLabel lblRefreshRate = new JLabel( "Refresh Rate :" );
		layout.putConstraint( SpringLayout.NORTH, lblRefreshRate, 0, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.SOUTH, lblRefreshRate, 0, SpringLayout.SOUTH, this );
		layout.putConstraint( SpringLayout.WEST, lblRefreshRate, 0, SpringLayout.WEST, this );
		add( lblRefreshRate );
		
		JLabel lblSpeed = new JLabel( "0 ms/t", SwingConstants.RIGHT );
		layout.putConstraint( SpringLayout.NORTH, spinner, 0, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.EAST, spinner, -6, SpringLayout.WEST, lblSpeed );
		layout.putConstraint( SpringLayout.SOUTH, spinner, 0, SpringLayout.SOUTH, this );
		layout.putConstraint( SpringLayout.WEST, spinner, 6, SpringLayout.EAST, lblRefreshRate );
		spinner.addPropertyChangeTask( () -> {
			Model.getEngine().setRefreshRate( spinner.getNumberValue().floatValue() );
		} );
		spinner.setValue( 1 );
		add( spinner );
		
		layout.putConstraint( SpringLayout.NORTH, lblSpeed, 0, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.EAST, lblSpeed, 0, SpringLayout.EAST, this );
		layout.putConstraint( SpringLayout.SOUTH, lblSpeed, 0, SpringLayout.SOUTH, this );
		lblSpeed.setPreferredSize( new Dimension( 60, 0 ) );
		add( lblSpeed );
		
		Model.getEngine().getTargetManager().add( new SimpleTimer() {
			@Override
			public void update() {
				super.update();
				lblSpeed.setText( String.format( "%s ms/t", NumberFormat.getInstance().format( getDelta() ) ) );
			}
		} );
		
	}
	
}

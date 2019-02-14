
package robertefry.firespread.ui.controller;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import robertefry.firespread.model.Model;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.targets.SimpleTimer;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public class ICRefreshRate extends JPanel {
	private static final long serialVersionUID = -2128361965375480595L;

	/**
	 * Create the panel.
	 */
	public ICRefreshRate() {

		SpringLayout layout = new SpringLayout();
		setLayout( layout );

		JLabel label = new JLabel( "refresh rate :" );
		label.setPreferredSize( new Dimension( 89, 14 ) );
		layout.putConstraint( SpringLayout.NORTH, label, 0, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.WEST, label, 0, SpringLayout.WEST, this );
		layout.putConstraint( SpringLayout.SOUTH, label, 0, SpringLayout.SOUTH, this );
		add( label );

		JSpinner spinner = new JSpinner(
			new SpinnerNumberModel( Model.getEngine().getRefreshRate(), -1, Integer.MAX_VALUE, 1 )
		);
		spinner.addPropertyChangeListener( new PropertyChangeListener() {
			public void propertyChange( PropertyChangeEvent evt ) {
				Model.getEngine().setRefreshRate( ( (Number)spinner.getValue() ).floatValue() );
			}
		} );
		layout.putConstraint( SpringLayout.NORTH, spinner, 0, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.WEST, spinner, 6, SpringLayout.EAST, label );
		layout.putConstraint( SpringLayout.SOUTH, spinner, 0, SpringLayout.SOUTH, this );
		add( spinner );

		JLabel nspt = new JLabel( "0 ms/t", SwingConstants.RIGHT );
		nspt.setPreferredSize( new Dimension( 89, 14 ) );
		layout.putConstraint( SpringLayout.EAST, spinner, -6, SpringLayout.WEST, nspt );
		layout.putConstraint( SpringLayout.NORTH, nspt, 0, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.SOUTH, nspt, 0, SpringLayout.SOUTH, this );
		layout.putConstraint( SpringLayout.EAST, nspt, 0, SpringLayout.EAST, this );
		add( nspt );

		Model.getEngine().getTargetManager().add( new SimpleTimer() {
			public synchronized void tick( Engine engine ) {
				super.tick( engine );
				nspt.setText( String.format( "%s ms/t", NumberFormat.getInstance().format( getDelta() ) ) );
			}
		} );

	}
}

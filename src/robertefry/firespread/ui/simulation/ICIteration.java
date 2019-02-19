
package robertefry.firespread.ui.simulation;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import robertefry.firespread.io.Resource;
import robertefry.firespread.model.Model;
import robertefry.firespread.ui.animate.RotatingIcon;
import robertefry.penguin.engine.listener.EngineStateListener;
import robertefry.penguin.targets.SimpleCounter;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public class ICIteration extends JPanel {
	private static final long serialVersionUID = 1126678209073859219L;

	/**
	 * Create the panel.
	 */
	public ICIteration() {

		SpringLayout springLayout = new SpringLayout();
		setLayout( springLayout );

		JLabel label = new JLabel( "iteration :" );
		label.setPreferredSize( new Dimension( 89, 14 ) );
		springLayout.putConstraint( SpringLayout.NORTH, label, 0, SpringLayout.NORTH, this );
		springLayout.putConstraint( SpringLayout.WEST, label, 0, SpringLayout.WEST, this );
		springLayout.putConstraint( SpringLayout.SOUTH, label, 0, SpringLayout.SOUTH, this );
		add( label );

		JButton button = new JButton( ">>" );
		button.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				Model.getEngine().forceTick();
			}
		} );
		springLayout.putConstraint( SpringLayout.NORTH, button, 0, SpringLayout.NORTH, this );
		springLayout.putConstraint( SpringLayout.SOUTH, button, 0, SpringLayout.SOUTH, this );
		springLayout.putConstraint( SpringLayout.EAST, button, 0, SpringLayout.EAST, this );
		add( button );

		RotatingIcon working = new RotatingIcon(
			new ImageIcon( Resource.loadImage( "res/icon/working.png", 20, 20 ) ), 0.5f
		);
		working.setPreferredSize( new Dimension( 25, 25 ) );
		springLayout.putConstraint( SpringLayout.NORTH, working, 0, SpringLayout.NORTH, this );
		springLayout.putConstraint( SpringLayout.SOUTH, working, 0, SpringLayout.SOUTH, this );
		springLayout.putConstraint( SpringLayout.WEST, working, 6, SpringLayout.EAST, label );
		add( working );

		JTextField textField = new JTextField();
		springLayout.putConstraint( SpringLayout.EAST, textField, -6, SpringLayout.WEST, button );
		textField.setEditable( false );
		springLayout.putConstraint( SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, this );
		springLayout.putConstraint( SpringLayout.WEST, textField, 6, SpringLayout.EAST, working );
		springLayout.putConstraint( SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, this );
		add( textField );
		textField.setColumns( 10 );

		Model.getEngine().getEngineStateListeners().add( new EngineStateListener() {

			@Override
			public void onSuspend() {
				working.stop();
			};

			@Override
			public void onResume() {
				working.start();
			}

		} );

		Model.getEngine().getTargetManager().add( new SimpleCounter() {

			@Override
			public void update() {
				super.update();
				textField.setText( String.valueOf( getCount() ) );
			}

			@Override
			public void reset() {
				super.reset();
				textField.setText( String.valueOf( 0 ) );
			}

		} );

	}
}

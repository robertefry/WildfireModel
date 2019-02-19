
package robertefry.firespread.ui.simulation;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class UISimulationController extends JFrame {
	private static final long serialVersionUID = -8052116804732676604L;

	private JPanel contentPane = new JPanel();

	/**
	 * Create the frame.
	 */
	public UISimulationController() {

		setTitle( "Controller" );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setContentPane( contentPane );

		SpringLayout layout = new SpringLayout();
		contentPane.setLayout( layout );
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		contentPane.setPreferredSize( new Dimension( 300, 123 ) );

		ICRefreshRate icRefreshRate = new ICRefreshRate();
		icRefreshRate.setPreferredSize( new Dimension( 280, 25 ) );
		layout.putConstraint( SpringLayout.EAST, icRefreshRate, -10, SpringLayout.EAST, contentPane );
		layout.putConstraint( SpringLayout.NORTH, icRefreshRate, 10, SpringLayout.NORTH, contentPane );
		layout.putConstraint( SpringLayout.WEST, icRefreshRate, 10, SpringLayout.WEST, contentPane );
		contentPane.add( icRefreshRate );

		ICIteration icIteration = new ICIteration();
		icIteration.setPreferredSize( new Dimension( 280, 25 ) );
		layout.putConstraint( SpringLayout.EAST, icIteration, -10, SpringLayout.EAST, contentPane );
		layout.putConstraint( SpringLayout.NORTH, icIteration, 10, SpringLayout.SOUTH, icRefreshRate );
		layout.putConstraint( SpringLayout.WEST, icIteration, 10, SpringLayout.WEST, contentPane );
		contentPane.add( icIteration );

		ICControlButtons controlButtons = new ICControlButtons();
		controlButtons.setPreferredSize( new Dimension( 280, 23 ) );
		layout.putConstraint( SpringLayout.SOUTH, controlButtons, -10, SpringLayout.SOUTH, contentPane );
		layout.putConstraint( SpringLayout.EAST, controlButtons, -10, SpringLayout.EAST, contentPane );
		layout.putConstraint( SpringLayout.WEST, controlButtons, 10, SpringLayout.WEST, contentPane );
		contentPane.add( controlButtons );

		pack();

	}
}

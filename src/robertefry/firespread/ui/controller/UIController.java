
package robertefry.firespread.ui.controller;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class UIController extends JFrame {
	private static final long serialVersionUID = -8052116804732676604L;

	private JPanel contentPane = new JPanel();

	/**
	 * Create the frame.
	 */
	public UIController() {

		setTitle( "Controller" );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

		SpringLayout layout = new SpringLayout();
		contentPane.setLayout( layout );
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		contentPane.setPreferredSize( new Dimension( 300, 123 ) );
		setContentPane( contentPane );

		ICRefreshRate icRefreshRate = new ICRefreshRate();
		layout.putConstraint( SpringLayout.EAST, icRefreshRate, -10, SpringLayout.EAST, contentPane );
		icRefreshRate.setPreferredSize( new Dimension( 280, 25 ) );
		layout.putConstraint( SpringLayout.NORTH, icRefreshRate, 10, SpringLayout.NORTH, contentPane );
		layout.putConstraint( SpringLayout.WEST, icRefreshRate, 10, SpringLayout.WEST, contentPane );
		contentPane.add( icRefreshRate );

		ICIteration icIteration = new ICIteration();
		layout.putConstraint( SpringLayout.EAST, icIteration, -10, SpringLayout.EAST, contentPane );
		icIteration.setPreferredSize( new Dimension( 280, 25 ) );
		layout.putConstraint( SpringLayout.NORTH, icIteration, 10, SpringLayout.SOUTH, icRefreshRate );
		layout.putConstraint( SpringLayout.WEST, icIteration, 10, SpringLayout.WEST, contentPane );
		contentPane.add( icIteration );

		ICControlButtons controlButtons = new ICControlButtons();
		layout.putConstraint( SpringLayout.SOUTH, controlButtons, -10, SpringLayout.SOUTH, contentPane );
		layout.putConstraint( SpringLayout.EAST, controlButtons, -10, SpringLayout.EAST, contentPane );
		controlButtons.setPreferredSize( new Dimension( 280, 23 ) );
		layout.putConstraint( SpringLayout.WEST, controlButtons, 10, SpringLayout.WEST, contentPane );
		contentPane.add( controlButtons );

		pack();

	}
}

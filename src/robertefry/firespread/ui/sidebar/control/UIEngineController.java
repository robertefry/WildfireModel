
package robertefry.firespread.ui.sidebar.control;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * @author Robert E Fry
 * @date 24 Feb 2019
 */
public class UIEngineController extends JPanel {
	private static final long serialVersionUID = 7531120222607680773L;
	
	public UIEngineController() {
		
		SpringLayout layout = new SpringLayout();
		setLayout( layout );
		
		JPanel pnlRefreshRate = new ICRefreshRate();
		layout.putConstraint( SpringLayout.NORTH, pnlRefreshRate, 10, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.WEST, pnlRefreshRate, 10, SpringLayout.WEST, this );
		layout.putConstraint( SpringLayout.EAST, pnlRefreshRate, -10, SpringLayout.EAST, this );
		pnlRefreshRate.setPreferredSize( new Dimension( 100, 25 ) );
		add( pnlRefreshRate );
		
		JPanel pnlIteration = new ICIteration();
		layout.putConstraint( SpringLayout.NORTH, pnlIteration, 10, SpringLayout.SOUTH, pnlRefreshRate );
		layout.putConstraint( SpringLayout.WEST, pnlIteration, 10, SpringLayout.WEST, this );
		layout.putConstraint( SpringLayout.EAST, pnlIteration, -10, SpringLayout.EAST, this );
		pnlIteration.setPreferredSize( new Dimension( 100, 25 ) );
		add( pnlIteration );
		
		JPanel pnlButtons = new ICControlButtons();
		layout.putConstraint( SpringLayout.NORTH, pnlButtons, 10, SpringLayout.SOUTH, pnlIteration );
		layout.putConstraint( SpringLayout.WEST, pnlButtons, 10, SpringLayout.WEST, this );
		layout.putConstraint( SpringLayout.EAST, pnlButtons, -10, SpringLayout.EAST, this );
		pnlButtons.setPreferredSize( new Dimension( 100, 25 ) );
		add( pnlButtons );
		
	}
	
}

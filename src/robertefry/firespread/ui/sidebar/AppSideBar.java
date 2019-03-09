
package robertefry.firespread.ui.sidebar;

import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import robertefry.firespread.ui.sidebar.control.UIEngineController;
import robertefry.firespread.ui.sidebar.gridedit.UIGridEdit;
import robertefry.firespread.ui.sidebar.wind.UIWindController;

/**
 * @author Robert E Fry
 * @date 24 Feb 2019
 */
public class AppSideBar extends JPanel {
	private static final long serialVersionUID = -1704228058641960561L;
	
	public AppSideBar() {
		
		SpringLayout layout = new SpringLayout();
		setLayout( layout );
		
		UIEngineController controller = new UIEngineController();
		layout.putConstraint( SpringLayout.NORTH, controller, 5, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.EAST, controller, -5, SpringLayout.EAST, this );
		layout.putConstraint( SpringLayout.WEST, controller, 5, SpringLayout.WEST, this );
		add( controller );
		
		UIGridEdit editer = new UIGridEdit();
		layout.putConstraint( SpringLayout.NORTH, editer, 5, SpringLayout.SOUTH, controller );
		layout.putConstraint( SpringLayout.EAST, editer, -5, SpringLayout.EAST, this );
		layout.putConstraint( SpringLayout.WEST, editer, 5, SpringLayout.WEST, this );
		add( editer );
		
		UIWindController wind = new UIWindController();
		layout.putConstraint( SpringLayout.NORTH, wind, 5, SpringLayout.SOUTH, editer );
		layout.putConstraint( SpringLayout.EAST, wind, -5, SpringLayout.EAST, this );
		layout.putConstraint( SpringLayout.WEST, wind, 5, SpringLayout.WEST, this );
		wind.setPreferredSize( new Dimension( 220, 220 ) );
		add( wind );
		
	}
	
}

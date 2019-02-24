
package robertefry.firespread.ui.sidebar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import robertefry.firespread.ui.sidebar.control.UIEngineController;
import robertefry.firespread.ui.sidebar.gridedit.UIGridEdit;

/**
 * @author Robert E Fry
 * @date 24 Feb 2019
 */
public class AppSideBar extends JPanel {
	private static final long serialVersionUID = -1704228058641960561L;
	
	private final JPanel controller = new UIEngineController();
	private final JPanel editer = new UIGridEdit();
	
	public AppSideBar() {
		
		SpringLayout springLayout = new SpringLayout();
		setLayout( springLayout );
		
		springLayout.putConstraint( SpringLayout.NORTH, controller, 5, SpringLayout.NORTH, this );
		springLayout.putConstraint( SpringLayout.WEST, controller, 5, SpringLayout.WEST, this );
		springLayout.putConstraint( SpringLayout.EAST, controller, -5, SpringLayout.EAST, this );
		controller.setBorder(
			new TitledBorder( new LineBorder( new Color( 0, 0, 0 ), 1, true ), "Engine Controller", TitledBorder.LEADING, TitledBorder.TOP, null, null )
		);
		controller.setPreferredSize( new Dimension( 100, 133 ) );
		add( controller );
		
		springLayout.putConstraint( SpringLayout.NORTH, editer, 5, SpringLayout.SOUTH, controller );
		springLayout.putConstraint( SpringLayout.WEST, editer, 5, SpringLayout.WEST, this );
		springLayout.putConstraint( SpringLayout.EAST, editer, -5, SpringLayout.EAST, this );
		editer.setBorder(
			new TitledBorder( new LineBorder( new Color( 0, 0, 0 ), 1, true ), "Grid Edit Options", TitledBorder.LEADING, TitledBorder.TOP, null, null )
		);
		editer.setPreferredSize( new Dimension( 100, 133 ) );
		add( editer );
		
	}
	
}

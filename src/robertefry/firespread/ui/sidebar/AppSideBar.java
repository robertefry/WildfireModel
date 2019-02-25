
package robertefry.firespread.ui.sidebar;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
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
		
		SpringLayout springLayout = new SpringLayout();
		setLayout( springLayout );
		
		UIEngineController controller = new UIEngineController();
		springLayout.putConstraint( SpringLayout.NORTH, controller, 5, SpringLayout.NORTH, this );
		springLayout.putConstraint( SpringLayout.WEST, controller, 5, SpringLayout.WEST, this );
		springLayout.putConstraint( SpringLayout.EAST, controller, -5, SpringLayout.EAST, this );
		controller.setBorder(
			new TitledBorder( new LineBorder( new Color( 0, 0, 0 ), 1, true ), "Engine Controller", TitledBorder.LEADING, TitledBorder.TOP, null, null )
		);
		controller.setPreferredSize( new Dimension( 100, 133 ) );
		add( controller );
		
		UIGridEdit editer = new UIGridEdit();
		springLayout.putConstraint( SpringLayout.NORTH, editer, 5, SpringLayout.SOUTH, controller );
		springLayout.putConstraint( SpringLayout.WEST, editer, 5, SpringLayout.WEST, this );
		springLayout.putConstraint( SpringLayout.EAST, editer, -5, SpringLayout.EAST, this );
		editer.setBorder(
			new TitledBorder( new LineBorder( new Color( 0, 0, 0 ), 1, true ), "Grid Edit Options", TitledBorder.LEADING, TitledBorder.TOP, null, null )
		);
		editer.setPreferredSize( new Dimension( 100, 133 ) );
		add( editer );
		
		UIWindController wind = new UIWindController();
		springLayout.putConstraint( SpringLayout.NORTH, wind, 5, SpringLayout.SOUTH, editer );
		springLayout.putConstraint( SpringLayout.WEST, wind, 5, SpringLayout.WEST, this );
		springLayout.putConstraint( SpringLayout.EAST, wind, -5, SpringLayout.EAST, this );
		wind.setBorder(
			new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Wind ( speed & direction )", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))
		);
		wind.setPreferredSize( new Dimension(100, 200) );
		add( wind );
		
	}
	
}

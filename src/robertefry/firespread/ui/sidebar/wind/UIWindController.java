
package robertefry.firespread.ui.sidebar.wind;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * @author Robert E Fry
 * @date 25 Feb 2019
 */
public class UIWindController extends JPanel {
	private static final long serialVersionUID = -7732401911536752102L;
	
	private final UIWindGraph wind = new UIWindGraph();
	
	public UIWindController() {
		
		setLayout( new BorderLayout( 0, 0 ) );
		
		wind.setPreferredSize( new Dimension( 100, 100 ) );
		add( wind, BorderLayout.CENTER );
		
	}
	
}


package robertefry.firespread.ui.sidebar.wind;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * @author Robert E Fry
 * @date 25 Feb 2019
 */
public class UIWindController extends JPanel {
	private static final long serialVersionUID = -7732401911536752102L;
	
	private final UIWindGraph wind = new UIWindGraph();
	
	public UIWindController() {
		
		setLayout( new BorderLayout( 0, 0 ) );
		
		add( wind, BorderLayout.CENTER );
		
		setBorder(
			new TitledBorder( new LineBorder( new Color( 0, 0, 0 ), 1, true ), "Wind ( speed & direction )", TitledBorder.LEADING, TitledBorder.TOP, null, new Color( 0, 0, 0 ) )
		);
		
	}
	
}

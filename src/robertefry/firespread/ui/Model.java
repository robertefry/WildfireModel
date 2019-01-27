
package robertefry.firespread.ui;

import java.io.File;
import javax.swing.JPanel;
import robertefry.firespread.model.grid.Grid;
import java.awt.GridLayout;

/**
 * @author Robert E Fry
 * @date 26 Jan 2019
 */
@SuppressWarnings( "serial" )
public class Model extends JPanel {
	
	private final GridUI gridUI;
	
	public Model() {
		gridUI = new GridUI( new Grid() );
		setLayout(new GridLayout(1, 0, 0, 0));
	}
	
	public void build( File file ) {
		gridUI.build( file );
	}

}

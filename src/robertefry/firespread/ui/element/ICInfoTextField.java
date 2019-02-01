
package robertefry.firespread.ui.element;

import javax.swing.JTextField;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
@SuppressWarnings( "serial" )
public class ICInfoTextField extends JTextField {

	@Override
	public void setText( String t ) {
		super.setText( t );
		super.setToolTipText( t );
	}

}

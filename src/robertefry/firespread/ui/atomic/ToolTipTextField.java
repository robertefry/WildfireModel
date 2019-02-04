
package robertefry.firespread.ui.atomic;

import javax.swing.JTextField;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class ToolTipTextField extends JTextField {
	private static final long serialVersionUID = 3941104059132099436L;

	@Override
	public void setText( String text ) {
		super.setText( text );
		super.setToolTipText( text );
	}

}

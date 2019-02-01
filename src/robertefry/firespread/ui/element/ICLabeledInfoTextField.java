
package robertefry.firespread.ui.element;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
@SuppressWarnings( "serial" )
public class ICLabeledInfoTextField extends JPanel {

	private JLabel label;
	private ICInfoTextField textField;

	public ICLabeledInfoTextField( String text ) {
		SpringLayout springLayout = new SpringLayout();
		setLayout( springLayout );
		setPreferredSize( new Dimension( 0, 20 ) );

		label = new JLabel( text );
		springLayout.putConstraint( SpringLayout.NORTH, label, 3, SpringLayout.NORTH, this );
		springLayout.putConstraint( SpringLayout.WEST, label, 0, SpringLayout.WEST, this );
		add( label );

		textField = new ICInfoTextField();
		springLayout.putConstraint( SpringLayout.NORTH, textField, 0, SpringLayout.NORTH, this );
		springLayout.putConstraint( SpringLayout.WEST, textField, 6, SpringLayout.EAST, label );
		springLayout.putConstraint( SpringLayout.EAST, textField, 0, SpringLayout.EAST, this );
		add( textField );
		textField.setColumns( 10 );
	}

	public ICInfoTextField getTextField() {
		return textField;
	}

	public JLabel getLabel() {
		return label;
	}
}

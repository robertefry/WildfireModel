
package robertefry.firespread.ui.atomic;

import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
@SuppressWarnings( "serial" )
public class LabeledComponent< T extends JComponent > extends JPanel {

	private final JLabel label = new JLabel();
	private final T component;

	public LabeledComponent( String text, T component ) {
		
		this.component = component;
		setLayout( new BorderLayout( 2, 2 ) );

		label.setText( text );
		add( label, BorderLayout.WEST );

		add( component, BorderLayout.CENTER );

	}

	public T getComponent() {
		return component;
	}

	public JLabel getLabel() {
		return label;
	}
}

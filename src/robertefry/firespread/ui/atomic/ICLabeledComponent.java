
package robertefry.firespread.ui.atomic;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class ICLabeledComponent< T extends JComponent > extends JPanel {
	private static final long serialVersionUID = -7138900481562102293L;
	
	private final JLabel label = new JLabel();
	private final T component;

	public ICLabeledComponent( String text, T component ) {
		
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

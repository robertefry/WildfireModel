
package robertefry.firespread.ui.element;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;

/**
 * @author Robert E Fry
 * @date 29 Jan 2019
 */
@SuppressWarnings( "serial" )
public class UIResource extends JPanel {

	private final JTextField textField;

	private void open() {
		JFileChooser fileChooser = new JFileChooser();
		int opened = fileChooser.showOpenDialog( this );
		if (opened == JFileChooser.APPROVE_OPTION) {
			textField.setText( fileChooser.getSelectedFile().getAbsolutePath() );
		}
	}

	/**
	 * Create the panel.
	 */
	public UIResource( String description, boolean required ) {

		setPreferredSize( new Dimension(416, 24) );

		JButton button = new JButton( "Open" );
		button.setPreferredSize(new Dimension(89, 23));
		button.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				open();
			}
		} );
		setLayout(new BorderLayout(10, 10));
		add( button, BorderLayout.EAST );

		JLabel label = new JLabel();
		label.setPreferredSize(new Dimension(89, 14));
		label.setText( String.format( "<html>%s%s</html>", description, required ? "<font color=red>*</font>" : "" ) );
		add( label, BorderLayout.WEST );

		textField = new JTextField();
		add( textField );
		textField.setColumns( 10 );

	}
	
	public void setBlankText() {
		textField.setText( "" );
	}

	public boolean hasText() {
		return !textField.getText().isEmpty();
	}

	public String getText() {
		return textField.getText();
	}

}

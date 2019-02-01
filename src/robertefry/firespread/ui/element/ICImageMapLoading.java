
package robertefry.firespread.ui.element;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * @author Robert E Fry
 * @date 29 Jan 2019
 */
@SuppressWarnings( "serial" )
public class ICImageMapLoading extends JPanel {

	private final ICInfoTextField textField;

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
	public ICImageMapLoading( String description, boolean required ) {

		setPreferredSize( new Dimension(416, 25) );
		SpringLayout springLayout = new SpringLayout();
		setLayout( springLayout );

		JLabel label = new JLabel();
		springLayout.putConstraint(SpringLayout.NORTH, label, 1, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, label, 1, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, label, -1, SpringLayout.SOUTH, this);
		label.setPreferredSize( new Dimension( 89, 14 ) );
		label.setText( String.format( "<html>%s%s</html>", description, required ? "<font color=red>*</font>" : "" ) );
		add( label );

		textField = new ICInfoTextField();
		springLayout.putConstraint(SpringLayout.NORTH, textField, 1, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, textField, 1, SpringLayout.EAST, label);
		springLayout.putConstraint(SpringLayout.SOUTH, textField, -1, SpringLayout.SOUTH, this);
		textField.setColumns( 10 );
		add( textField );

		JButton btnProperties = new JButton( "?" );
		springLayout.putConstraint(SpringLayout.EAST, textField, -1, SpringLayout.WEST, btnProperties);
		springLayout.putConstraint(SpringLayout.NORTH, btnProperties, 1, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnProperties, -1, SpringLayout.SOUTH, this);
		add( btnProperties );

		JButton btnOpen = new JButton( "Open" );
		springLayout.putConstraint(SpringLayout.EAST, btnProperties, -1, SpringLayout.WEST, btnOpen);
		springLayout.putConstraint(SpringLayout.NORTH, btnOpen, 1, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnOpen, -1, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnOpen, -1, SpringLayout.EAST, this);
		btnOpen.setPreferredSize( new Dimension( 89, 23 ) );
		btnOpen.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				open();
			}
		} );
		add( btnOpen );

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


package robertefry.firespread.ui.frame;

import java.awt.EventQueue;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import org.apache.commons.logging.LogFactory;
import robertefry.firespread.ui.dialog.UIDialog;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
@SuppressWarnings( "serial" )
public class TestUIDialog extends UIDialog<String> {

	/**
	 * Launch the application.
	 */
	public static void main( String[] args ) {

		TestUIDialog frame = new TestUIDialog();
		
		EventQueue.invokeLater( new Runnable() {
			public void run() {
				frame.setVisible( true );
			}
		} );
		
		try {
			System.out.println( frame.get() );
		} catch ( InterruptedException | ExecutionException e ) {
			LogFactory.getLog( TestUIDialog.class ).fatal( "failed to get UIDialog return", e );
		}
		
	}

	private final JTextField textField = new JTextField();

	/**
	 * Create the frame.
	 */
	public TestUIDialog() {

		setBounds( 100, 100, 450, 300 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

		SpringLayout springLayout = new SpringLayout();
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		contentPane.setLayout( springLayout );

		JLabel lblMessagel = new JLabel( "Message :" );
		springLayout.putConstraint( SpringLayout.WEST, lblMessagel, 10, SpringLayout.WEST, contentPane );
		springLayout.putConstraint( SpringLayout.EAST, lblMessagel, -10, SpringLayout.EAST, contentPane );
		springLayout.putConstraint( SpringLayout.NORTH, lblMessagel, 10, SpringLayout.NORTH, contentPane );
		contentPane.add( lblMessagel );

		springLayout.putConstraint( SpringLayout.EAST, textField, -10, SpringLayout.EAST, contentPane );
		springLayout.putConstraint( SpringLayout.NORTH, textField, 10, SpringLayout.SOUTH, lblMessagel );
		springLayout.putConstraint( SpringLayout.WEST, textField, 0, SpringLayout.WEST, lblMessagel );
		textField.setColumns( 10 );
		contentPane.add( textField );

	}

	@Override
	protected String getReturn() {
		return textField.getText();
	}

}

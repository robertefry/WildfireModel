
package robertefry.firespread.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import robertefry.firespread.io.Resource;
import robertefry.firespread.ui.animate.RotatingIcon;

/**
 * @author Robert E Fry
 * @date 29 Jan 2019
 */
@SuppressWarnings( "serial" )
public class SimulationController extends JFrame {

	private final JPanel contentPane = new JPanel();

	/**
	 * Create the frame.
	 */
	public SimulationController() {

		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setContentPane( contentPane );
		contentPane.setLayout( null );

		JLabel lblNewLabel = new JLabel( "Refresh Rate : ", SwingConstants.RIGHT );
		lblNewLabel.setBounds( 10, 10, 89, 23 );
		contentPane.add( lblNewLabel );

		JSpinner spinner = new JSpinner();
		spinner.setBounds( 109, 11, 89, 21 );
		contentPane.add( spinner );

		JLabel label = new JLabel( "Iteration : ", SwingConstants.RIGHT );
		label.setBounds( 10, 44, 89, 23 );
		contentPane.add( label );

		JTextField textField = new JTextField();
		textField.setEditable( false );
		textField.setBounds( 109, 45, 89, 21 );
		contentPane.add( textField );
		textField.setColumns( 10 );

		JButton button = new JButton( ">>" );
		button.setBounds( 208, 44, 89, 23 );
		contentPane.add( button );

		JButton btnStop = new JButton( "Stop" );
		btnStop.setBounds( 208, 78, 89, 23 );
		contentPane.add( btnStop );

		JButton btnStart = new JButton( "Start" );
		btnStart.setBounds( 109, 78, 89, 23 );
		contentPane.add( btnStart );

		RotatingIcon rotatingIcon = new RotatingIcon(
			new ImageIcon( Resource.loadImage( "res/icons/working.png", 20, 20 ) ), 0.5f
		);
		rotatingIcon.setBounds( 76, 78, 23, 23 );
		contentPane.add( rotatingIcon );

	}
}

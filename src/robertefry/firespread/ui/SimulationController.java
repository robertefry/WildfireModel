
package robertefry.firespread.ui;

import java.awt.Dimension;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import robertefry.firespread.io.Resource;
import robertefry.firespread.ui.animate.RotatingIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * @author Robert E Fry
 * @date 28 Jan 2019
 */
@SuppressWarnings( "serial" )
public class SimulationController extends JFrame {

	private final JPanel contentPane = new JPanel();
	private final RotatingIcon processingIcon;

	/**
	 * Create the frame.
	 * 
	 * @throws IOException
	 */
	public SimulationController() {

		contentPane.setPreferredSize( new Dimension( 326, 128 ) );
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setContentPane( contentPane );
		contentPane.setLayout( null );

		JButton btnNewButton = new JButton( "Start" );
		btnNewButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				processingIcon.start();
			}
		} );
		btnNewButton.setBounds( 128, 94, 89, 23 );
		contentPane.add( btnNewButton );

		JButton btnStop = new JButton( "Stop" );
		btnStop.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				processingIcon.stop();
			}
		} );
		btnStop.setBounds( 227, 94, 89, 23 );
		contentPane.add( btnStop );

		final ImageIcon rotatingImageIcon = new ImageIcon( Resource.loadImage( "res/icons/processing.png", 20, 20 ) );
		processingIcon = new RotatingIcon( rotatingImageIcon, 0.5f );
		processingIcon.setBounds( 9, 95, 25, 25 );
		contentPane.add( processingIcon );

		setTitle( "Simulation Control Panel" ); 
		pack();
		setResizable( false );
		setVisible( false );

	}
}

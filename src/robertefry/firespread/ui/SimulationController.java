
package robertefry.firespread.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import robertefry.firespread.io.Resource;
import robertefry.firespread.model.Model;
import robertefry.firespread.ui.animate.RotatingIcon;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.targets.SimpleCounter;

/**
 * @author Robert E Fry
 * @date 29 Jan 2019
 */
@SuppressWarnings( "serial" )
public class SimulationController extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SimulationController() {
		setResizable( false );
		setBounds( 100, 100, 450, 300 );
		contentPane = new JPanel();
		contentPane.setPreferredSize( new Dimension( 311, 112 ) );
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setContentPane( contentPane );
		contentPane.setLayout( null );

		RotatingIcon icnWorking = new RotatingIcon(
			new ImageIcon( Resource.loadImage( "res/icons/working.png", 25, 25 ) ), 0.5f
		);
		icnWorking.setBounds( 10, 68, 30, 30 );
		contentPane.add( icnWorking );

		JLabel lblRefreshRate = new JLabel( "Refresh Rate : ", SwingConstants.RIGHT );
		lblRefreshRate.setBounds( 10, 14, 89, 14 );
		contentPane.add( lblRefreshRate );

		JSpinner spnRefreshRate = new JSpinner();
		spnRefreshRate.addChangeListener( new ChangeListener() {
			public void stateChanged( ChangeEvent e ) {
				Model.getEngine().setRefreshRate( ((Number)spnRefreshRate.getValue()).floatValue() );
			}
		} );
		spnRefreshRate.setValue( Model.getEngine().getRefreshRate() );
		spnRefreshRate.setBounds( 109, 11, 89, 21 );
		contentPane.add( spnRefreshRate );

		JLabel lblIteration = new JLabel( "Iteration : ", SwingConstants.RIGHT );
		lblIteration.setBounds( 10, 45, 89, 14 );
		contentPane.add( lblIteration );

		JTextField tfIteration = new JTextField( "0" );
		tfIteration.setHorizontalAlignment( SwingConstants.RIGHT );
		tfIteration.setEditable( false );
		tfIteration.setBounds( 109, 42, 89, 21 );
		contentPane.add( tfIteration );
		tfIteration.setColumns( 10 );

		JButton btnIterate = new JButton( ">>" );
		btnIterate.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				Model.getEngine().forceTick();
			}
		} );
		btnIterate.setBounds( 208, 41, 89, 23 );
		contentPane.add( btnIterate );

		JButton btnStop = new JButton( "Stop" );
		btnStop.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				icnWorking.stop();
				Model.getEngine().suspend();
			}
		} );
		btnStop.setBounds( 208, 75, 89, 23 );
		contentPane.add( btnStop );

		JButton btnStart = new JButton( "Start" );
		btnStart.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				icnWorking.start();
				Model.getEngine().resume();
			}
		} );
		btnStart.setBounds( 109, 75, 89, 23 );
		contentPane.add( btnStart );

		pack();

		Model.getEngine().getTargetManager().add( new SimpleCounter() {
			DecimalFormat format = new DecimalFormat( "#,###" );
			@Override
			public void tick( Engine engine ) {
				super.tick( engine );
				tfIteration.setText( format.format( super.getCount() ) );
			}
		} );

	}
}

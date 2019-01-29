
package robertefry.firespread.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import robertefry.firespread.graphic.Renderer;

/**
 * @author Robert E Fry
 * @date 29 Jan 2019
 */
public class Application {

	private JFrame frmWildfireModel = new JFrame( "Wildfire Model" );
	private JFrame frmSimulationController = new SimulationController();

	/**
	 * Launch the application.
	 */
	public static void main( String[] args ) {
		EventQueue.invokeLater( new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frmWildfireModel.setVisible( true );
				} catch ( Exception e ) {
					e.printStackTrace();
				}
			}
		} );
	}

	/**
	 * Create the application.
	 */
	public Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmWildfireModel.setBounds( 100, 100, 800, 600 );
		frmWildfireModel.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frmWildfireModel.getContentPane().add( Renderer.getCanvas(), BorderLayout.CENTER );

		JMenuBar menuBar = new JMenuBar();
		frmWildfireModel.setJMenuBar( menuBar );

		JMenu mnFile = new JMenu( "File" );
		menuBar.add( mnFile );

		JMenuItem mntmNewTerrainMap = new JMenuItem( "New Terrain Map" );
		mnFile.add( mntmNewTerrainMap );

		JMenu mnSimulation = new JMenu( "Simulation" );
		menuBar.add( mnSimulation );

		JMenuItem mntmOpenSimulationController = new JMenuItem( "Open Simulation Controller" );
		mntmOpenSimulationController.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				frmSimulationController.setVisible( true );
			}
		} );
		mnSimulation.add( mntmOpenSimulationController );

	}

}

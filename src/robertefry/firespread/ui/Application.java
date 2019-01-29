
package robertefry.firespread.ui;

import java.awt.BorderLayout;
import java.awt.Canvas;
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
 * @date 28 Jan 2019
 */
public class Application {

	private final JFrame frmWildfireModel = new JFrame();
	private final Canvas canvas = Renderer.getCanvas();
	
	private final SimulationController simulationController = new SimulationController();

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
		frmWildfireModel.setTitle("Wildfire Model");
		frmWildfireModel.setBounds( 100, 100, 800, 600 );
		frmWildfireModel.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frmWildfireModel.getContentPane().add( canvas, BorderLayout.CENTER );
		
		JMenuBar menuBar = new JMenuBar();
		frmWildfireModel.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewTerrain = new JMenuItem("New Terrain");
		mnFile.add(mntmNewTerrain);
		
		JMenu mnSimulation = new JMenu("Simulation");
		menuBar.add(mnSimulation);
		
		JMenuItem mntmOpenControlPanel = new JMenuItem("Open Simulation Control");
		mntmOpenControlPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				simulationController.setVisible( true );
			}
		});
		mnSimulation.add(mntmOpenControlPanel);
	}

}


package robertefry.firespread.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.ui.frame.UICellMapLoader;
import robertefry.firespread.ui.frame.UISimulationController;

/**
 * @author Robert E Fry
 * @date 29 Jan 2019
 */
public class Application {

	private JFrame frmMainModel = new JFrame( "Wildfire Model" );
	private JFrame frmSimulationController = new UISimulationController();

	public Application() {
		initialize();
	}

	public void show() {
		frmMainModel.setVisible( true );
	}

	private void initialize() {

		frmMainModel.setBounds( 100, 100, 800, 600 );
		frmMainModel.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frmMainModel.getContentPane().add( Renderer.getCanvas(), BorderLayout.CENTER );

		JMenuBar menuBar = new JMenuBar();
		frmMainModel.setJMenuBar( menuBar );

		JMenu mnFile = new JMenu( "File" );
		menuBar.add( mnFile );

		JMenuItem mntmNewMap = new JMenuItem( "New Map" );
		mntmNewMap.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				final JFrame frmCellMapLoader = new UICellMapLoader();
				frmCellMapLoader.setLocationRelativeTo( frmMainModel );
				frmCellMapLoader.setVisible( true );
				// TODO get cell map return
			}
		} );
		mnFile.add( mntmNewMap );

		JMenu mnSimulation = new JMenu( "Simulation" );
		menuBar.add( mnSimulation );

		JMenuItem mntmOpenSimulationController = new JMenuItem( "Open Simulation Controller" );
		mntmOpenSimulationController.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				frmSimulationController.setLocationRelativeTo( frmMainModel );
				frmSimulationController.setVisible( true );
			}
		} );
		mnSimulation.add( mntmOpenSimulationController );

	}

}

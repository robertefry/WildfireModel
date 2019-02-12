
package robertefry.firespread.ui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import org.apache.commons.logging.LogFactory;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.Model;
import robertefry.firespread.model.cell.Cell;
import robertefry.firespread.ui.controller.UIController;
import robertefry.firespread.ui.dialog.UIDialog;
import robertefry.firespread.ui.maploader.UICellSetLoader;

/**
 * @author Robert E Fry
 * @date 29 Jan 2019
 */
public class Application {

	private JFrame frmMainModel = new JFrame( "Wildfire Model" );
	private JFrame frmSimulationController = new UIController();

	public Application() {
		initialize();
	}

	public void show() {
		frmMainModel.setVisible( true );
	}

	private void initialize() {

		frmMainModel.setBounds( 100, 100, 800, 600 );
		frmMainModel.setLocationRelativeTo( null );
		frmMainModel.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frmMainModel.getContentPane().add( Renderer.getCanvas(), BorderLayout.CENTER );

		JMenuBar menuBar = new JMenuBar();
		frmMainModel.setJMenuBar( menuBar );

		JMenu mnFile = new JMenu( "File" );
		menuBar.add( mnFile );

		JMenuItem mntmNewMap = new JMenuItem( "New Map" );
		mntmNewMap.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				new Thread( () -> {
					UIDialog< Map< Point, Cell > > frmCellSetLoader = new UICellSetLoader();
					frmCellSetLoader.setLocationRelativeTo( frmMainModel );
					frmCellSetLoader.setVisible( true );
					Model.getEngine().suspend();
					Map< Point, Cell > cellset = null;
					try {
						cellset = frmCellSetLoader.fetch();
					} catch ( CancellationException e1 ) {
					} catch ( InterruptedException | ExecutionException e1 ) {
						LogFactory.getLog( getClass() ).error( "cellset fetch failed", e1 );
					}
					if ( frmCellSetLoader.hasFetched() ) {
						Model.getEngine().suspend();
						Model.getGrid().rebuild( cellset );
						Renderer.clear();
						Model.getEngine().forceRender();
					}
				} ).start();
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

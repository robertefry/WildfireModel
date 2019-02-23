
package robertefry.firespread.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import org.apache.commons.logging.LogFactory;
import robertefry.firespread.graphic.Renderer;
import robertefry.firespread.model.Model;
import robertefry.firespread.model.grid.Cell;
import robertefry.firespread.model.terain.TerrainState;
import robertefry.firespread.ui.dialog.UIDialog;
import robertefry.firespread.ui.maploader.UICellSetLoader;
import robertefry.firespread.ui.maploader.UIGridLoader;
import robertefry.firespread.ui.simulation.UISimulationController;

/**
 * @author Robert E Fry
 * @date 29 Jan 2019
 */
public class Application {
	
	// TODO include a bar on the left containing edit / engine / controller / e.t.c information & settings

	private final JFrame frmMainModel = new JFrame( "Wildfire Model" );

	private final JFrame frmSimulationController = new UISimulationController();

	public Application() {
		initialize();
	}

	public void show() {
		frmMainModel.setVisible( true );
		Renderer.notifyVisible();
	}

	private void initialize() {

		frmMainModel.setBounds( 100, 100, 800, 600 );
		frmMainModel.setLocationRelativeTo( null );
		frmMainModel.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frmMainModel.getContentPane().add( Renderer.getComponent(), BorderLayout.CENTER );

		JMenuBar menuBar = new JMenuBar();
		frmMainModel.setJMenuBar( menuBar );

		JMenu mnFile = new JMenu( "File" );
		menuBar.add( mnFile );

		JMenuItem mntmEmptyNew = new JMenuItem( "New Empty Grid" );
		mntmEmptyNew.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				new Thread( () -> {
					UIDialog< Map< Point, Cell > > frmGridLoader = new UIGridLoader();
					frmGridLoader.setLocationRelativeTo( frmMainModel );
					frmGridLoader.setVisible( true );
					Map< Point, Cell > cellmap = null;
					try {
						cellmap = frmGridLoader.fetch();
					} catch ( CancellationException e1 ) {
					} catch ( InterruptedException | ExecutionException e1 ) {
						LogFactory.getLog( getClass() ).error( "cellset fetch failed", e1 );
					}
					if ( frmGridLoader.hasFetched() ) {
						Model.getEngine().suspend();
						Model.getGrid().reconstruct( cellmap );
						Model.getEngine().forceRender();
					}
				} ).start();
			}
		} );
		mntmEmptyNew.setPreferredSize( new Dimension( 200, 22 ) );
		mnFile.add( mntmEmptyNew );

		JMenuItem mntmNewMapGrid = new JMenuItem( "New Grid from Map" );
		mntmNewMapGrid.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				new Thread( () -> {
					UIDialog< Map< Point, Cell > > frmCellSetLoader = new UICellSetLoader();
					frmCellSetLoader.setLocationRelativeTo( frmMainModel );
					frmCellSetLoader.setVisible( true );
					Map< Point, Cell > cellmap = null;
					try {
						cellmap = frmCellSetLoader.fetch();
					} catch ( CancellationException e1 ) {
					} catch ( InterruptedException | ExecutionException e1 ) {
						LogFactory.getLog( getClass() ).error( "cellset fetch failed", e1 );
					}
					if ( frmCellSetLoader.hasFetched() ) {
						Model.getEngine().suspend();
						Model.getGrid().reconstruct( cellmap );
						Model.getEngine().forceRender();
					}
				} ).start();
			}
		} );
		mnFile.add( mntmNewMapGrid );

		JSeparator separator_1 = new JSeparator();
		mnFile.add( separator_1 );

		JMenuItem mntmSaveModel = new JMenuItem( "Save Model" );
		// TODO mntmSaveModel
		//		mntmSaveModel.addActionListener( new ActionListener() {
		//			public void actionPerformed( ActionEvent e ) {
		//				JFileChooser fileChooser = new JFileChooser();
		//				if ( fileChooser.showOpenDialog( frmMainModel ) == JFileChooser.APPROVE_OPTION ) {
		//					ObjectResource.save( Model.getGrid().toStreamObject(), fileChooser.getSelectedFile() );
		//				}
		//			}
		//		} );
		mnFile.add( mntmSaveModel );

		JMenuItem mntmLoadModel = new JMenuItem( "Load Model" );
		// TODO mntmLoadModel
		//		mntmLoadModel.addActionListener( new ActionListener() {
		//			public void actionPerformed( ActionEvent e ) {
		//				JFileChooser fileChooser = new JFileChooser();
		//				if ( fileChooser.showOpenDialog( frmMainModel ) == JFileChooser.APPROVE_OPTION ) {
		//					Model.getGrid().fromStreamObject( ObjectResource.load( fileChooser.getSelectedFile() ) );
		//					Model.getEngine().forceRender();
		//				}
		//			}
		//		} );
		mnFile.add( mntmLoadModel );

		JMenu mnEdit = new JMenu( "Edit" );
		menuBar.add( mnEdit );

		JCheckBoxMenuItem chckbxDrawCellBorders = new JCheckBoxMenuItem( "Draw Cell Borders?" );
		chckbxDrawCellBorders.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				Model.CellRenderHints.DrawCellBorder = chckbxDrawCellBorders.isSelected();
				Model.getEngine().forceRender();
			}
		} );
		mnEdit.add( chckbxDrawCellBorders );

		JSeparator separator_2 = new JSeparator();
		mnEdit.add( separator_2 );

		ButtonGroup btngCellEditType = new ButtonGroup();
		MapEditHints.getMapEditOptionSet().forEach( state -> {
			JRadioButtonMenuItem rdbtnmntmCellEditType = new JRadioButtonMenuItem( state.name() );
			rdbtnmntmCellEditType.addActionListener( new ActionListener() {
				public void actionPerformed( ActionEvent e ) {
					MapEditHints.setEditSelection( state );
				}
			} );
			mnEdit.add( rdbtnmntmCellEditType );
			btngCellEditType.add( rdbtnmntmCellEditType );
			if ( state == TerrainState.getDefault() ) {
				rdbtnmntmCellEditType.setSelected( true );
			}
		} );

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

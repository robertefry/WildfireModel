
package robertefry.firespread.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JSeparator;
import org.apache.commons.logging.LogFactory;
import robertefry.firespread.model.Model;
import robertefry.firespread.model.grid.Cell;
import robertefry.firespread.ui.dialog.UIDialog;
import robertefry.firespread.ui.maploader.UICellSetLoader;
import robertefry.firespread.ui.maploader.UIGridLoader;
import robertefry.firespread.ui.param.UISpreadParamController;
import robertefry.firespread.ui.sidebar.AppSideBar;

/**
 * @author Robert E Fry
 * @date 29 Jan 2019
 */
public class Application {
	
	// TODO include a bar on the left containing edit / engine / controller / e.t.c
	// information & settings
	
	private final JFrame frmMainModel = new JFrame( "Wildfire Model" );
	private final Component canvas = Model.getEngine().getRenderer().getComponent();
	private final JFrame frmParam = new UISpreadParamController();
	
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
		
		AppSideBar appSideBar = new AppSideBar();
		appSideBar.setPreferredSize( new Dimension( 250, 0 ) );
		frmMainModel.getContentPane().add( appSideBar, BorderLayout.WEST );
		
		frmMainModel.getContentPane().add( canvas, BorderLayout.CENTER );
		
		JMenuBar menuBar = new JMenuBar();
		frmMainModel.setJMenuBar( menuBar );
		
		JMenu mnFile = new JMenu( "File" );
		menuBar.add( mnFile );
		
		JMenuItem mntmCreateEmptyGrid = new JMenuItem( "Create Empty Grid" );
		mntmCreateEmptyGrid.addActionListener( new ActionListener() {
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
					if ( frmGridLoader.isFetched() ) {
						Model.getEngine().suspend();
						Model.getGrid().reconstruct( cellmap );
						Model.getEngine().forceRender();
					}
				} ).start();
			}
		} );
		mnFile.add( mntmCreateEmptyGrid );
		
		JMenuItem mntmPopulateGrid = new JMenuItem( "Populate Grid" );
		mntmPopulateGrid.addActionListener( new ActionListener() {
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
					if ( frmCellSetLoader.isFetched() ) {
						Model.getEngine().suspend();
						Model.getGrid().reconstruct( cellmap );
						Model.getEngine().forceRender();
					}
				} ).start();
			}
		} );
		mntmPopulateGrid.setEnabled( false );
		mnFile.add( mntmPopulateGrid );
		
		JSeparator separator = new JSeparator();
		mnFile.add( separator );
		
		JMenuItem mntmImport = new JMenuItem( "Import" );
		mntmImport.setEnabled( false );
		mnFile.add( mntmImport );
		
		JMenuItem mntmExport = new JMenuItem( "Export" );
		mntmExport.setEnabled( false );
		mnFile.add( mntmExport );
		
		JMenu mnParameter = new JMenu( "Parameter" );
		menuBar.add( mnParameter );
		
		JMenuItem mntmOpenSpreadParameter = new JMenuItem( "Open Spread Parameter Controller" );
		mntmOpenSpreadParameter.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				frmParam.setLocationRelativeTo( frmMainModel );
				frmParam.setVisible( true );
			}
		} );
		mnParameter.add( mntmOpenSpreadParameter );
		
	}
}

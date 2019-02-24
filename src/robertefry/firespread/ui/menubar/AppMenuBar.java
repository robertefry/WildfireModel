
package robertefry.firespread.ui.menubar;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import org.apache.commons.logging.LogFactory;
import robertefry.firespread.model.Model;
import robertefry.firespread.model.grid.Cell;
import robertefry.firespread.ui.dialog.UIDialog;
import robertefry.firespread.ui.menubar.maploader.UICellSetLoader;
import robertefry.firespread.ui.menubar.maploader.UIGridLoader;

/**
 * @author Robert E Fry
 * @date 24 Feb 2019
 */
public class AppMenuBar extends JMenuBar {
	private static final long serialVersionUID = 4898497104749810838L;
	
	public AppMenuBar() {

		JMenu mnFile = new JMenu( "File" );
		add( mnFile );

		JMenuItem mntmEmptyNew = new JMenuItem( "New Empty Grid" );
		mntmEmptyNew.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				new Thread( () -> {
					UIDialog< Map< Point, Cell > > frmGridLoader = new UIGridLoader();
					frmGridLoader.setLocationRelativeTo( getParent() );
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
					frmCellSetLoader.setLocationRelativeTo( getParent() );
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
		mntmNewMapGrid.setEnabled( false );
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
		mntmSaveModel.setEnabled( false );
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
		mntmLoadModel.setEnabled( false );
		mnFile.add( mntmLoadModel );
		
	}
	
}

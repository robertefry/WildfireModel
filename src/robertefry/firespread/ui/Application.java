
package robertefry.firespread.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import robertefry.firespread.model.Model;
import robertefry.firespread.ui.menubar.AppMenuBar;
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
		
		frmMainModel.setJMenuBar( new AppMenuBar() );
		
		AppSideBar appSideBar = new AppSideBar();
		appSideBar.setPreferredSize( new Dimension( 250, 0 ) );
		frmMainModel.getContentPane().add( appSideBar, BorderLayout.WEST );
		
		frmMainModel.getContentPane().add( canvas, BorderLayout.CENTER );
		
	}
	
}

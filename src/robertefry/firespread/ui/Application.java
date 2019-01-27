
package robertefry.firespread.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileSystemView;


/**
 * @author Robert E Fry
 * @date 26 Jan 2019
 */
public class Application {

	private final JFrame frame = new JFrame();
	private final Model model = new Model();

	/**
	 * Launch the application.
	 */
	public static void main( String[] args ) {
		EventQueue.invokeLater( new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.frame.setVisible( true );
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
		
		frame.setContentPane( model );
		frame.setBounds( 100, 100, 800, 600 );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnNew = new JMenu("New");
		mnFile.add(mnNew);
		
		JMenuItem mntmMap = new JMenuItem("Map");
		mntmMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					model.build( jfc.getSelectedFile() );
				}
			}
		});
		mnNew.add(mntmMap);
		
	}

}

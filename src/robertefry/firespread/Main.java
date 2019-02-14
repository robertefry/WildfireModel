
package robertefry.firespread;

import java.awt.EventQueue;
import javax.swing.UIManager;
import org.apache.commons.logging.LogFactory;
import robertefry.firespread.ui.Application;

/**
 * @author Robert E Fry
 * @date 31 Jan 2019
 */
public class Main {

	public static void main( String[] args ) {

		EventQueue.invokeLater( () -> {
			try {
				UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
			} catch ( Exception e ) {
				LogFactory.getLog( Main.class ).warn( "failed to set default system UI look and feel", e );
			}
			Application application = new Application();
			application.show();

		} );

	}

}

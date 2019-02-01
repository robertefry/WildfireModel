
package robertefry.firespread.ui.element.animate;

import java.awt.BorderLayout;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import tips4java.AnimatedIcon;
import tips4java.RotatedIcon;

/**
 * @author Robert E Fry
 * @date 28 Jan 2019
 */
@SuppressWarnings( "serial" )
public class RotatingIcon extends JComponent {

	private final JLabel label = new JLabel();
	private final AnimatedIcon animation;

	public RotatingIcon( Icon icon, float speed ) {
		setLayout( new BorderLayout() );
		animation = new AnimatedIcon( label, (int)(1 / speed) );
		for ( int angle = 0; angle < 360; angle++ ) {
			animation.addIcon( new RotatedIcon( icon, angle ) );
		}
		label.setIcon( animation );
		add( label, BorderLayout.CENTER );
	}

	public void start() {
		animation.start();
	}

	public void stop() {
		animation.stop();
	}

	public void pause() {
		animation.pause();
	}

	public void restart() {
		animation.restart();
	}

}

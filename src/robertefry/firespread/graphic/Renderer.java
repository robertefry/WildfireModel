
package robertefry.firespread.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * @author Robert E Fry
 * @date 28 Jan 2019
 */
public class Renderer {

	private static final Canvas canvas = new Canvas();

	static {
		canvas.setBackground( Color.BLACK );
	}

	public static Canvas getCanvas() {
		return canvas;
	}

	public static Graphics getGraphics() {
		return canvas.getGraphics();
	}

	public static void clear() {
		Dimension size = canvas.getSize();
		getGraphics().setColor( canvas.getBackground() );
		getGraphics().fillRect( 0, 0, size.width, size.height );
	}

}

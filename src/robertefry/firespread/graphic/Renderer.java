
package robertefry.firespread.graphic;

import java.awt.Canvas;
import java.awt.Color;
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

}

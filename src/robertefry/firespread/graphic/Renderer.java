
package robertefry.firespread.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import robertefry.firespread.model.Model;
import robertefry.penguin.engine.listener.EngineLogicListener;

/**
 * @author Robert E Fry
 * @date 28 Jan 2019
 */
public class Renderer {

	private static final Canvas canvas = new Canvas();

	static {
		canvas.setBackground( Color.BLACK );
		Model.getEngine().addLogicListener( new EngineLogicListener() {
			public void onEngineRender() {
				EngineLogicListener.super.onEngineRender();
				Dimension size = Renderer.getCanvas().getSize();
				Renderer.getGraphics().clearRect( 0, 0, size.width, size.height );
			}
		} );
	}

	public static Canvas getCanvas() {
		return canvas;
	}

	public static Graphics getGraphics() {
		return canvas.getGraphics();
	}

}

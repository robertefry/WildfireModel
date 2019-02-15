
package robertefry.firespread.graphic;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import robertefry.firespread.model.Model;
import robertefry.penguin.engine.listener.EngineLogicAdapter;

/**
 * @author Robert E Fry
 * @date 28 Jan 2019
 */
public class Renderer {

	private static Color clearcolor = Color.BLACK;
	private static final Component component = new Canvas();
	private static BufferedImage buffer = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB );

	static {

		component.setIgnoreRepaint( true );
		component.setBackground( clearcolor );

		Model.getEngine().addLogicListener( new EngineLogicAdapter() {
			@Override
			public void preRender() {
				renewBuffer();
				Graphics g = getGraphics();
				g.setColor( clearcolor );
				g.fillRect( 0, 0, buffer.getWidth(), buffer.getHeight() );
			}
		} );

		Model.getEngine().addLogicListener( new EngineLogicAdapter() {
			@Override
			public void postRender() {
				component.getGraphics().drawImage(
					buffer, 0, 0, component.getWidth(), component.getHeight(),
					0, 0, buffer.getWidth(), buffer.getHeight(), null
				);
			}
		} );

	}

	private static final void renewBuffer() {
		buffer = new BufferedImage( component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_ARGB );
	}

	public static final Component getComponent() {
		return component;
	}

	public static final BufferedImage getBuffer() {
		return buffer;
	}

	public static final Graphics getGraphics() {
		return buffer.getGraphics();
	}

	public static final Color getClearColor() {
		return clearcolor;
	}

	public static final void setClearColor( Color clearcolor ) {
		Renderer.clearcolor = clearcolor;
		component.setBackground( clearcolor );
	}

}


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

	private static final Component component = new Canvas();
	private static BufferedImage buffer = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB );

	private static Color clearcolor = Color.BLACK;
	private static boolean visable = false;

	static {

		component.setIgnoreRepaint( true );
		component.setBackground( clearcolor );

		Model.getEngine().getEngineLogicListeners().add( new EngineLogicAdapter() {

			@Override
			public void preRender() {
				Graphics g = buffer.getGraphics();
				g.setColor( clearcolor );
				g.fillRect( 0, 0, buffer.getWidth(), buffer.getHeight() );
			}

			@Override
			public void postRender() {
				if ( visable ) component.getGraphics().drawImage(
					buffer, 0, 0, component.getWidth(), component.getHeight(),
					0, 0, buffer.getWidth(), buffer.getHeight(), null
				);
			}

		} );

	}

	public static final void notifyVisible() {
		buffer = new BufferedImage( component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_ARGB );
		visable = true;
	}

	public static final Component getComponent() {
		return component;
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

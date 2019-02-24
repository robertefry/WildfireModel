
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
	
	private static final Component canvas = new Canvas();
	private static BufferedImage buffer = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB );
	
	private static Color clearcolor = Color.BLACK;
	private static boolean visable = false;
	
	static {
		
		canvas.setIgnoreRepaint( true );
		canvas.setBackground( clearcolor );
		
		Model.getEngine().getEngineLogicListeners().add( new EngineLogicAdapter() {
			
			@Override
			public void preRender() {
				if ( canvas.getWidth() != buffer.getWidth() || canvas.getHeight() != buffer.getHeight() ) {
					buffer = new BufferedImage( canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB );
				}
				Graphics g = buffer.getGraphics();
				g.setColor( clearcolor );
				g.fillRect( 0, 0, buffer.getWidth(), buffer.getHeight() );
			}
			
			@Override
			public void postRender() {
				if ( visable ) {
					canvas.getGraphics().setColor( clearcolor );
					canvas.getGraphics().fillRect( 0, 0, canvas.getWidth(), canvas.getHeight() );
					canvas.getGraphics().drawImage(
						buffer, 0, 0, canvas.getWidth(), canvas.getHeight(),
						0, 0, buffer.getWidth(), buffer.getHeight(), null
					);
				}
			}
			
		} );
		
	}
	
	public static final void notifyVisible() {
		visable = true;
	}
	
	public static final Component getCanvas() {
		return canvas;
	}
	
	public static final Graphics getGraphics() {
		return buffer.getGraphics();
	}
	
	public static final Color getClearColor() {
		return clearcolor;
	}
	
	public static final void setClearColor( Color clearcolor ) {
		Renderer.clearcolor = clearcolor;
		canvas.setBackground( clearcolor );
	}
	
}


package robertefry.firespread.graphic;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import robertefry.firespread.model.Model;
import robertefry.penguin.engine.listener.EngineLogicAdapter;

/**
 * @author Robert E Fry
 * @date 28 Jan 2019
 */
public class Renderer {
	
	private static final JPanel canvas = new InstanceCanvas();
	private static BufferedImage buffer = new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB );
	
	private static Color clearcolor = Color.BLACK;
	
	static {
		
		canvas.setIgnoreRepaint( true );
		canvas.setBackground( clearcolor );
		
		Model.getEngine().getEngineLogicListeners().add( new EngineLogicAdapter() {
			
			@Override
			public void preRender() {
				buffer = new BufferedImage( canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_ARGB );
			}
			
			@Override
			public void postRender() {
				canvas.repaint();
			}
			
		} );
		
	}
	
	public static final Component getComponent() {
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
	
	private static final class InstanceCanvas extends JPanel {
		private static final long serialVersionUID = 692123362907724199L;
		
		@Override
		protected void paintComponent( Graphics g ) {
			super.paintComponent( g );
			
			g.setColor( clearcolor );
			g.fillRect( 0, 0, canvas.getWidth(), canvas.getHeight() );
			g.drawImage(
				buffer, 0, 0, canvas.getWidth(), canvas.getHeight(),
				0, 0, buffer.getWidth(), buffer.getHeight(), null
			);
			
		}
		
	}
	
}

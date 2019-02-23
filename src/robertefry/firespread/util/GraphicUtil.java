
package robertefry.firespread.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class GraphicUtil {

	public static final void drawCross( Graphics g, Rectangle2D bounds, Color color ) {
		g.setColor( color );
		g.drawLine(
			(int)bounds.getX(), (int)bounds.getY(), (int)bounds.getX() + (int)bounds.getWidth(),
			(int)bounds.getY() + (int)bounds.getHeight()
		);
		g.drawLine(
			(int)bounds.getX() + (int)bounds.getWidth(), (int)bounds.getY(), (int)bounds.getX(),
			(int)bounds.getY() + (int)bounds.getHeight()
		);
	}

	public static final void drawRect( Graphics g, Rectangle2D bounds, Color color ) {
		g.setColor( color );
		g.drawRect( (int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight() );
	}

	public static final void fillRect( Graphics g, Rectangle2D bounds, Color color ) {
		g.setColor( color );
		g.fillRect( (int)bounds.getX(), (int)bounds.getY(), (int)bounds.getWidth(), (int)bounds.getHeight() );
	}

}

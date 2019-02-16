
package robertefry.firespread.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class GraphicUtil {

	public static final void drawCross( Graphics g, Rectangle bounds, Color color ) {
		g.setColor( color );
		g.drawLine( bounds.x, bounds.y, bounds.x + bounds.width, bounds.y + bounds.height );
		g.drawLine( bounds.x + bounds.width, bounds.y, bounds.x, bounds.y + bounds.height );
	}

	public static final void drawRect( Graphics g, Rectangle bounds, Color color ) {
		g.setColor( color );
		g.drawRect( bounds.x, bounds.y, bounds.width, bounds.height );
	}

	public static final void fillRect( Graphics g, Rectangle bounds, Color color ) {
		g.setColor( color );
		g.fillRect( bounds.x, bounds.y, bounds.width, bounds.height );
	}

}

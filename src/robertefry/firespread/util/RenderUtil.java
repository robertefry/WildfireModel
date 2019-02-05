
package robertefry.firespread.util;

import java.awt.Graphics;
import java.awt.Point;

/**
 * @author Robert E Fry
 * @date 5 Feb 2019
 */
public class RenderUtil {
	
	public static final void drawCross( Graphics g, Point tl, Point tr, Point bl, Point br) {
		g.drawLine( tl.x, tl.y, br.x, br.y );
		g.drawLine( tr.x, tr.y, bl.x, bl.y );
	}

}

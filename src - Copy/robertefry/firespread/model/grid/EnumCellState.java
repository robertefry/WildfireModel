
package robertefry.firespread.model.grid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.function.BiConsumer;

public enum EnumCellState {

	WILD( true, false, ( g, rect ) -> drawWild( g, rect ) ),
	BURNING( true, true, ( g, rect ) -> drawBurning( g, rect ) ),
	CLEARED( false, false, ( g, rect ) -> drawCleared( g, rect ) );

	public final boolean canBurn;
	public final boolean isBurning;
	public final BiConsumer<Graphics,Rectangle> drawCall;

	EnumCellState( boolean canBurn, boolean isBurning, BiConsumer<Graphics,Rectangle> drawCall ) {
		this.canBurn = canBurn;
		this.isBurning = isBurning;
		this.drawCall = drawCall;
	}

	private static final void drawWild( Graphics g, Rectangle rect ) {
		drawBorder( g, rect );
	}

	private static final void drawBurning( Graphics g, Rectangle rect ) {
		drawBorder( g, rect );
		g.setColor( Color.RED );
		drawCross( g, rect );
	}

	private static final void drawCleared( Graphics g, Rectangle rect ) {
		drawBorder( g, rect );
		g.setColor( Color.GRAY );
		drawCross( g, rect );
	}

	private static final void drawBorder( Graphics g, Rectangle rect ) {
		g.setColor( Color.GRAY );
		g.drawRect( rect.x, rect.y, rect.width, rect.height );
	}

	private static final void drawCross( Graphics g, Rectangle rect ) {
		g.drawLine( rect.x, rect.y, rect.x + rect.width, rect.y + rect.height );
		g.drawLine( rect.x + rect.width, rect.y, rect.x, rect.y + rect.height );
	}

}

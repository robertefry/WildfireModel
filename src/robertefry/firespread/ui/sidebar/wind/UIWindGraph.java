
package robertefry.firespread.ui.sidebar.wind;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import robertefry.firespread.model.Wind;
import robertefry.penguin.input.mouse.Mouse;
import robertefry.penguin.input.mouse.listener.MouseObjectAdapter;

/**
 * @author Robert E Fry
 * @date 26 Feb 2019
 */
public class UIWindGraph extends JPanel {
	private static final long serialVersionUID = -9076896224049449558L;
	
	private final Mouse mouse = new Mouse();
	
	private final Rectangle bounds = new Rectangle();
	private final Point center = new Point();
	private final Point pointer = new Point();
	
	public UIWindGraph() {
		mouse.register( this );
		mouse.addMouseObjectListener( new InstanceMouseAdapter() );
		this.addComponentListener( new InstanceComponentAdapter() );
	}
	
	@Override
	protected void paintComponent( Graphics g ) {
		super.paintComponent( g );
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		
		g2.setColor( Color.BLACK );
		g2.fillArc( center.x - 2, center.y - 2, 4, 4, 0, 360 );
		g2.drawArc( bounds.x, bounds.y, bounds.width, bounds.height, 0, 360 );
		
		g2.setColor( Color.RED );
		g2.drawLine( center.x, center.y, ( center.x + pointer.x ), ( center.y + pointer.y ) );
		g2.fillArc( ( center.x + pointer.x ) - 2, ( center.y + pointer.y ) - 2, 4, 4, 0, 360 );
		
	}
	
	private void setWind( double max, double mag, double dir ) {
		double scale = ( (double)bounds.width / 2 ) / max;
		this.pointer.x = (int)( scale * mag * +Math.sin( dir ) );
		this.pointer.y = (int)( scale * mag * -Math.cos( dir ) );
		Wind.setMagnitude( mag / max );
		Wind.setDirection( dir );
	}
	
	private final class InstanceMouseAdapter extends MouseObjectAdapter {
		
		private void process( MouseEvent e ) {
			Point point = new Point( e.getX() - center.x, center.y - e.getY() );
			double max = (double)bounds.width / 2;
			double mag = Math.min( max, Math.sqrt( point.x * point.x + point.y * point.y ) );
			double dir = Math.atan2( point.x, point.y );
			setWind( max, mag, dir );
			repaint();
		}
		
		@Override
		public void onButtonPress( MouseEvent e ) {
			process( e );
		}
		
		@Override
		public void onMouseDrag( MouseEvent e ) {
			process( e );
		}
		
	}
	
	private final class InstanceComponentAdapter extends ComponentAdapter {
		
		private void process() {
			bounds.width = bounds.height = Math.min( getWidth() - 1, getHeight() - 1 );
			bounds.x = ( getWidth() - bounds.width ) / 2;
			bounds.y = ( getHeight() - bounds.height ) / 2;
			center.x = getWidth() / 2;
			center.y = getHeight() / 2;
		}
		
		@Override
		public void componentShown( ComponentEvent e ) {
			process();
		}
		
		@Override
		public void componentResized( ComponentEvent e ) {
			process();
		}
		
	}
	
}

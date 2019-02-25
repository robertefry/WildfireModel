
package robertefry.firespread.ui.sidebar.wind;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import robertefry.firespread.model.Wind;

/**
 * @author Robert E Fry
 * @date 25 Feb 2019
 */
public class UIWindController extends JPanel {
	private static final long serialVersionUID = -7732401911536752102L;
	
	private static final int MAX_SPEED = 100;
	
	private final WindCanvas canvas = new WindCanvas();
	private final JSlider sldrSpeed = new JSlider();
	private final JSlider sldrDirection = new JSlider();
	
	public UIWindController() {
		
		setLayout( new BorderLayout( 0, 0 ) );
		
		sldrSpeed.addChangeListener( new ChangeListener() {
			public void stateChanged( ChangeEvent e ) {
				Wind.setSpeed( sldrSpeed.getValue() );
				canvas.setWind( sldrSpeed.getValue(), sldrDirection.getValue() );
			}
		} );
		sldrSpeed.setValue( 0 );
		sldrSpeed.setMaximum( MAX_SPEED );
		sldrSpeed.setPaintLabels( true );
		sldrSpeed.setMajorTickSpacing( 20 );
		sldrSpeed.setMinorTickSpacing( 5 );
		sldrSpeed.setPaintTicks( true );
		sldrSpeed.setOrientation( SwingConstants.VERTICAL );
		add( sldrSpeed, BorderLayout.WEST );
		
		sldrDirection.addChangeListener( new ChangeListener() {
			public void stateChanged( ChangeEvent e ) {
				Wind.setDirection( sldrDirection.getValue() );
				canvas.setWind( sldrSpeed.getValue(), sldrDirection.getValue() );
			}
		} );
		sldrDirection.setValue( 0 );
		sldrDirection.setMaximum( 360 );
		sldrDirection.setPaintLabels( true );
		sldrDirection.setMajorTickSpacing( 45 );
		sldrDirection.setMinorTickSpacing( 10 );
		sldrDirection.setPaintTicks( true );
		add( sldrDirection, BorderLayout.SOUTH );
		
		canvas.setPreferredSize( new Dimension( 100, 100 ) );
		add( canvas, BorderLayout.CENTER );
		
	}
	
	private final class WindCanvas extends Canvas {
		private static final long serialVersionUID = 1L;
		
		private final Rectangle bounds = new Rectangle();
		private final Point center = new Point();
		private final Point windpoint = new Point();
		
		public WindCanvas() {
			addComponentListener( new ComponentAdapter() {
				public void componentResized( ComponentEvent e ) {
					setBounds();
				}
			} );
		}
		
		@Override
		public void paint( Graphics g ) {
			super.paint( g );
			
			g.setColor( Color.BLACK );
			g.fillArc( center.x - 2, center.y - 2, 4, 4, 0, 360 );
			g.drawArc( bounds.x, bounds.y, bounds.width, bounds.height, 0, 360 );
			
			g.setColor( Color.RED );
			g.drawLine( center.x, center.y, center.x + windpoint.x, center.y + windpoint.y );
			g.fillArc( ( center.x + windpoint.x ) - 2, ( center.y + windpoint.y ) - 2, 4, 4, 0, 360 );
			
		}
		
		public void setWind( double speed, double direction ) {
			double radians = Math.toRadians( direction );
			double hypot = ( bounds.width == 0 ) ? 0 : speed * ( bounds.width / 2 ) / MAX_SPEED;
			windpoint.x = (int)( hypot * +Math.sin( radians ) );
			windpoint.y = (int)( hypot * -Math.cos( radians ) );
			repaint();
		}
		
		public void setBounds() {
			bounds.width = bounds.height = Math.min( getWidth() - 1, getHeight() - 1 );
			bounds.x = ( getWidth() - bounds.width ) / 2;
			bounds.y = ( getHeight() - bounds.height ) / 2;
			center.x = getWidth() / 2;
			center.y = getHeight() / 2;
		}
		
	}
	
}

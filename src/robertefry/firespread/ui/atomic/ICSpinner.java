
package robertefry.firespread.ui.atomic;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 * @author Robert E Fry
 * @date 8 Mar 2019
 */
public class ICSpinner extends JSpinner {
	private static final long serialVersionUID = 8377331952994699407L;
	
	private final Set< Runnable > tasks = new HashSet<>();
	
	public ICSpinner() {
		this( new SpinnerNumberModel() );
	}
	
	public ICSpinner( SpinnerModel model ) {
		
		addPropertyChangeListener( new InstancePropertyChangeListener() );
		( (JSpinner.DefaultEditor)getEditor() ).getTextField()
			.addKeyListener( new InstanceKeyListener() );
		( (JSpinner.DefaultEditor)getEditor() ).getTextField()
			.addMouseWheelListener( new InstanceMouseWheelListener() );
		
	}
	
	public Number getNumberValue() {
		return (Number)getValue();
	}
	
	protected void onPropertyChange() {
	}
	
	public void addPropertyChangeTask( Runnable runnable ) {
		tasks.add( runnable );
	}
	
	public void removePropertyChangeTask( Runnable runnable ) {
		tasks.remove( runnable );
	}
	
	private class InstancePropertyChangeListener implements PropertyChangeListener {
		
		@Override
		public void propertyChange( PropertyChangeEvent evt ) {
			tasks.forEach( Runnable::run );
			onPropertyChange();
		}
		
	}
	
	private class InstanceKeyListener implements KeyListener {
		
		@Override
		public void keyTyped( KeyEvent e ) {
			tasks.forEach( Runnable::run );
			onPropertyChange();
		}
		
		@Override
		public void keyPressed( KeyEvent e ) {
			tasks.forEach( Runnable::run );
			onPropertyChange();
		}
		
		@Override
		public void keyReleased( KeyEvent e ) {
			tasks.forEach( Runnable::run );
			onPropertyChange();
		}
		
	}
	
	private class InstanceMouseWheelListener implements MouseWheelListener {
		
		@Override
		public void mouseWheelMoved( MouseWheelEvent e ) {
			setValue( getNumberValue().doubleValue() - e.getWheelRotation() );
			tasks.forEach( Runnable::run );
			onPropertyChange();
		}
		
	}
	
}

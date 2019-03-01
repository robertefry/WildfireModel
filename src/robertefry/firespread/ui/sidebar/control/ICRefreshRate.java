
package robertefry.firespread.ui.sidebar.control;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import robertefry.firespread.model.Model;
import robertefry.penguin.targets.SimpleTimer;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public class ICRefreshRate extends JPanel {
	private static final long serialVersionUID = -2128361965375480595L;
	
	private final JLabel label = new JLabel( "Refresh Rate :" );
	private final JLabel nspt = new JLabel( "0 ms/t", SwingConstants.RIGHT );
	private final JSpinner spinner = new JSpinner();
	
	public ICRefreshRate() {
		
		SpringLayout layout = new SpringLayout();
		setLayout( layout );
		
		layout.putConstraint( SpringLayout.NORTH, label, 0, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.WEST, label, 0, SpringLayout.WEST, this );
		layout.putConstraint( SpringLayout.SOUTH, label, 0, SpringLayout.SOUTH, this );
		add( label );
		
		spinner.addPropertyChangeListener( new PropertyChangeListener() {
			public void propertyChange( PropertyChangeEvent evt ) {
				process();
			}
		} );
		( (JSpinner.DefaultEditor)spinner.getEditor() ).getTextField().addKeyListener( new KeyAdapter() {
			public void keyReleased( KeyEvent e ) {
				process();
			}
		} );
		( (JSpinner.DefaultEditor)spinner.getEditor() ).getTextField().addMouseWheelListener( new MouseWheelListener() {
			public void mouseWheelMoved( MouseWheelEvent e ) {
				spinner.setValue( ( (Number)spinner.getValue() ).doubleValue() - e.getWheelRotation() );
				process();
			}
		} );
		layout.putConstraint( SpringLayout.NORTH, spinner, 0, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.WEST, spinner, 6, SpringLayout.EAST, label );
		layout.putConstraint( SpringLayout.SOUTH, spinner, 0, SpringLayout.SOUTH, this );
		spinner.setValue( 1 );
		add( spinner );
		
		layout.putConstraint( SpringLayout.EAST, spinner, -6, SpringLayout.WEST, nspt );
		layout.putConstraint( SpringLayout.NORTH, nspt, 0, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.SOUTH, nspt, 0, SpringLayout.SOUTH, this );
		layout.putConstraint( SpringLayout.EAST, nspt, 0, SpringLayout.EAST, this );
		nspt.setPreferredSize( new Dimension( 60, 0 ) );
		add( nspt );
		
		Model.getEngine().getTargetManager().add( new SimpleTimer() {
			@Override
			public void update() {
				super.update();
				nspt.setText( String.format( "%s ms/t", NumberFormat.getInstance().format( getDelta() ) ) );
			}
		} );
		
	}
	
	private void process() {
		Model.getEngine().setRefreshRate( ( (Number)spinner.getValue() ).floatValue() );
	}
	
	public JLabel getLabel() {
		return label;
	}
	
	public JLabel getNspt() {
		return nspt;
	}
	
	public JSpinner getSpinner() {
		return spinner;
	}
	
}

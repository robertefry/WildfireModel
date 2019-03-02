
package robertefry.firespread.ui.sidebar.gridedit;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

import robertefry.firespread.model.grid.GridEditOptions;
import robertefry.firespread.ui.atomic.LabeledComponent;

/**
 * @author Robert E Fry
 * @date 24 Feb 2019
 */
public class UIGridEdit extends JPanel {
	private static final long serialVersionUID = 8009175195973692506L;
	
	private final LabeledComponent< JSpinner > lblcmpPenSize = new LabeledComponent<>(
		(String)"Pen size;", new JSpinner()
	);
	
	public UIGridEdit() {
		
		SpringLayout springLayout = new SpringLayout();
		setLayout( springLayout );
		
		JPanel icEditTerrainType = new ICEditTerrainType();
		icEditTerrainType.setBorder( new EtchedBorder( EtchedBorder.LOWERED, null, null ) );
		springLayout.putConstraint( SpringLayout.NORTH, icEditTerrainType, 4, SpringLayout.NORTH, this );
		springLayout.putConstraint( SpringLayout.WEST, icEditTerrainType, 4, SpringLayout.WEST, this );
		springLayout.putConstraint( SpringLayout.SOUTH, icEditTerrainType, -4, SpringLayout.SOUTH, this );
		icEditTerrainType.setPreferredSize( new Dimension( 80, 0 ) );
		add( icEditTerrainType );
		
		lblcmpPenSize.getComponent().addPropertyChangeListener( new PropertyChangeListener() {
			public void propertyChange( PropertyChangeEvent evt ) {
				processPenSize();
			}
		} );
		( (JSpinner.DefaultEditor)lblcmpPenSize.getComponent().getEditor() ).getTextField()
			.addKeyListener( new KeyAdapter() {
				public void keyReleased( KeyEvent e ) {
					processPenSize();
				}
			} );
		( (JSpinner.DefaultEditor)lblcmpPenSize.getComponent().getEditor() ).getTextField()
			.addMouseWheelListener( new MouseWheelListener() {
				public void mouseWheelMoved( MouseWheelEvent e ) {
					double size = ( (Number)lblcmpPenSize.getComponent().getValue() ).doubleValue();
					size = Math.max( size - e.getWheelRotation(), 1 );
					lblcmpPenSize.getComponent().setValue( size );
					processPenSize();
				}
			} );
		lblcmpPenSize.getComponent().setValue( GridEditOptions.getPenSize() );
		lblcmpPenSize.getComponent().setPreferredSize( new Dimension( 80, 20 ) );
		lblcmpPenSize.getLabel().setText( "Pen size:" );
		lblcmpPenSize.getLabel().setPreferredSize( new Dimension( 50, 14 ) );
		springLayout.putConstraint( SpringLayout.NORTH, lblcmpPenSize, 6, SpringLayout.NORTH, this );
		springLayout.putConstraint( SpringLayout.WEST, lblcmpPenSize, 6, SpringLayout.EAST, icEditTerrainType );
		add( lblcmpPenSize );
		
	}
	
	private void processPenSize() {
		GridEditOptions.setPenSize( ( (Number)lblcmpPenSize.getComponent().getValue() ).doubleValue() );
	}
	
}

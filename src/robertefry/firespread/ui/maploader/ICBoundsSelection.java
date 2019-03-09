
package robertefry.firespread.ui.maploader;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import robertefry.firespread.ui.atomic.ICLabeledComponent;
import robertefry.firespread.ui.dialog.UIDialog;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class ICBoundsSelection extends UIDialog< Rectangle > {
	private static final long serialVersionUID = 3254004359139003291L;
	
	private final ICLabeledComponent< JSpinner > litfSelectionX;
	private final ICLabeledComponent< JSpinner > litfSelectionY;
	private final ICLabeledComponent< JSpinner > litfSelectionWid;
	private final ICLabeledComponent< JSpinner > litfSelectionHei;
	
	public ICBoundsSelection( Rectangle bounds ) {
		
		litfSelectionX = new ICLabeledComponent<>(
			"Selection X",
			new JSpinner( new SpinnerNumberModel( bounds.x, 0, Integer.MAX_VALUE, 1 ) )
		);
		litfSelectionY = new ICLabeledComponent<>(
			"Selection Y",
			new JSpinner( new SpinnerNumberModel( bounds.y, 0, Integer.MAX_VALUE, 1 ) )
		);
		litfSelectionWid = new ICLabeledComponent<>(
			"Selection Width",
			new JSpinner( new SpinnerNumberModel( bounds.width, 0, Integer.MAX_VALUE, 1 ) )
		);
		litfSelectionHei = new ICLabeledComponent<>(
			"Selection Height",
			new JSpinner( new SpinnerNumberModel( bounds.height, 0, Integer.MAX_VALUE, 1 ) )
		);
		
		setTitle( "Selection" );
		
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout( layout );
		
		JLabel lblMapSettings = new JLabel( "Select region to use." );
		layout.putConstraint( SpringLayout.NORTH, lblMapSettings, 10, SpringLayout.NORTH, contentPane );
		layout.putConstraint( SpringLayout.WEST, lblMapSettings, 10, SpringLayout.WEST, contentPane );
		layout.putConstraint( SpringLayout.EAST, lblMapSettings, -10, SpringLayout.EAST, contentPane );
		contentPane.add( lblMapSettings );
		
		litfSelectionX.getLabel().setPreferredSize( new Dimension( 89, 14 ) );
		layout.putConstraint( SpringLayout.WEST, litfSelectionX, 10, SpringLayout.WEST, contentPane );
		layout.putConstraint( SpringLayout.EAST, litfSelectionX, -10, SpringLayout.EAST, contentPane );
		layout.putConstraint( SpringLayout.NORTH, litfSelectionX, 6, SpringLayout.SOUTH, lblMapSettings );
		contentPane.add( litfSelectionX );
		
		litfSelectionY.getLabel().setPreferredSize( new Dimension( 89, 14 ) );
		layout.putConstraint( SpringLayout.WEST, litfSelectionY, 10, SpringLayout.WEST, contentPane );
		layout.putConstraint( SpringLayout.EAST, litfSelectionY, -10, SpringLayout.EAST, contentPane );
		layout.putConstraint( SpringLayout.NORTH, litfSelectionY, 6, SpringLayout.SOUTH, litfSelectionX );
		contentPane.add( litfSelectionY );
		
		litfSelectionWid.getLabel().setPreferredSize( new Dimension( 89, 14 ) );
		layout.putConstraint( SpringLayout.WEST, litfSelectionWid, 10, SpringLayout.WEST, contentPane );
		layout.putConstraint( SpringLayout.EAST, litfSelectionWid, -10, SpringLayout.EAST, contentPane );
		layout.putConstraint( SpringLayout.NORTH, litfSelectionWid, 6, SpringLayout.SOUTH, litfSelectionY );
		contentPane.add( litfSelectionWid );
		
		litfSelectionHei.getLabel().setPreferredSize( new Dimension( 89, 14 ) );
		layout.putConstraint( SpringLayout.WEST, litfSelectionHei, 10, SpringLayout.WEST, contentPane );
		layout.putConstraint( SpringLayout.EAST, litfSelectionHei, -10, SpringLayout.EAST, contentPane );
		layout.putConstraint( SpringLayout.NORTH, litfSelectionHei, 6, SpringLayout.SOUTH, litfSelectionWid );
		contentPane.add( litfSelectionHei );
		
		contentPane.setPreferredSize( new Dimension( 220, 140 ) );
		pack();
		
	}
	
	@Override
	protected boolean canReturn() {
		return true;
	}
	
	@Override
	protected Rectangle getReturn() {
		final int x = ( (Number)litfSelectionX.getComponent().getValue() ).intValue();
		final int y = ( (Number)litfSelectionY.getComponent().getValue() ).intValue();
		final int w = ( (Number)litfSelectionWid.getComponent().getValue() ).intValue();
		final int h = ( (Number)litfSelectionHei.getComponent().getValue() ).intValue();
		return new Rectangle( x, y, w, h );
	}
}

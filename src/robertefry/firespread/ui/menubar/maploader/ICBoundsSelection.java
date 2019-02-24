
package robertefry.firespread.ui.menubar.maploader;

import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import robertefry.firespread.ui.atomic.LabeledComponent;
import robertefry.firespread.ui.dialog.UIDialog;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public class ICBoundsSelection extends UIDialog< Rectangle > {
	private static final long serialVersionUID = 3254004359139003291L;

	private final LabeledComponent< JSpinner > litfSelectionX;
	private final LabeledComponent< JSpinner > litfSelectionY;
	private final LabeledComponent< JSpinner > litfSelectionWid;
	private final LabeledComponent< JSpinner > litfSelectionHei;

	/**
	 * Create the frame.
	 */
	public ICBoundsSelection( Rectangle bounds ) {

		litfSelectionX = new LabeledComponent<>(
			"Selection X",
			new JSpinner( new SpinnerNumberModel( bounds.x, 0, Integer.MAX_VALUE, 1 ) )
		);
		litfSelectionY = new LabeledComponent<>(
			"Selection Y",
			new JSpinner( new SpinnerNumberModel( bounds.y, 0, Integer.MAX_VALUE, 1 ) )
		);
		litfSelectionWid = new LabeledComponent<>(
			"Selection Width",
			new JSpinner( new SpinnerNumberModel( bounds.width, 0, Integer.MAX_VALUE, 1 ) )
		);
		litfSelectionHei = new LabeledComponent<>(
			"Selection Height",
			new JSpinner( new SpinnerNumberModel( bounds.height, 0, Integer.MAX_VALUE, 1 ) )
		);

		setTitle( "Selection" );
		contentPane.setPreferredSize( new Dimension( 250, 138 ) );

		SpringLayout springLayout = new SpringLayout();
		contentPane.setLayout( springLayout );

		JLabel lblMapSettings = new JLabel( "Select region to use." );
		springLayout.putConstraint( SpringLayout.NORTH, lblMapSettings, 10, SpringLayout.NORTH, contentPane );
		springLayout.putConstraint( SpringLayout.WEST, lblMapSettings, 10, SpringLayout.WEST, contentPane );
		springLayout.putConstraint( SpringLayout.EAST, lblMapSettings, -10, SpringLayout.EAST, contentPane );
		contentPane.add( lblMapSettings );

		litfSelectionX.getLabel().setPreferredSize( new Dimension( 89, 14 ) );
		springLayout.putConstraint( SpringLayout.WEST, litfSelectionX, 10, SpringLayout.WEST, contentPane );
		springLayout.putConstraint( SpringLayout.EAST, litfSelectionX, -10, SpringLayout.EAST, contentPane );
		springLayout.putConstraint( SpringLayout.NORTH, litfSelectionX, 6, SpringLayout.SOUTH, lblMapSettings );
		contentPane.add( litfSelectionX );

		litfSelectionY.getLabel().setPreferredSize( new Dimension( 89, 14 ) );
		springLayout.putConstraint( SpringLayout.WEST, litfSelectionY, 10, SpringLayout.WEST, contentPane );
		springLayout.putConstraint( SpringLayout.EAST, litfSelectionY, -10, SpringLayout.EAST, contentPane );
		springLayout.putConstraint( SpringLayout.NORTH, litfSelectionY, 6, SpringLayout.SOUTH, litfSelectionX );
		contentPane.add( litfSelectionY );

		litfSelectionWid.getLabel().setPreferredSize( new Dimension( 89, 14 ) );
		springLayout.putConstraint( SpringLayout.WEST, litfSelectionWid, 10, SpringLayout.WEST, contentPane );
		springLayout.putConstraint( SpringLayout.EAST, litfSelectionWid, -10, SpringLayout.EAST, contentPane );
		springLayout.putConstraint( SpringLayout.NORTH, litfSelectionWid, 6, SpringLayout.SOUTH, litfSelectionY );
		contentPane.add( litfSelectionWid );

		litfSelectionHei.getLabel().setPreferredSize( new Dimension( 89, 14 ) );
		springLayout.putConstraint( SpringLayout.WEST, litfSelectionHei, 10, SpringLayout.WEST, contentPane );
		springLayout.putConstraint( SpringLayout.EAST, litfSelectionHei, -10, SpringLayout.EAST, contentPane );
		springLayout.putConstraint( SpringLayout.NORTH, litfSelectionHei, 6, SpringLayout.SOUTH, litfSelectionWid );
		contentPane.add( litfSelectionHei );

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

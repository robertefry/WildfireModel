
package robertefry.firespread.ui.render;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import robertefry.firespread.model.Model;
import robertefry.firespread.ui.atomic.LabeledComponent;

/**
 * @author Robert E Fry
 * @date 19 Feb 2019
 */
public class UIRenderController extends JFrame {
	private static final long serialVersionUID = -7385128713761195076L;

	private JPanel contentPane = new JPanel();

	public UIRenderController() {

		setTitle( "Rendering" );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		setContentPane( contentPane );

		SpringLayout springLayout = new SpringLayout();
		contentPane.setPreferredSize( new Dimension( 220, 45 ) );
		contentPane.setLayout( springLayout );

		LabeledComponent< JCheckBox > labeledComponent = new LabeledComponent<>( "draw cell border", new JCheckBox() );
		labeledComponent.getComponent().addPropertyChangeListener( new PropertyChangeListener() {
			public void propertyChange( PropertyChangeEvent evt ) {
				Model.CellRenderHints.DrawCellBorder = labeledComponent.getComponent().isSelected();
				Model.getEngine().forceRender();
			}
		} );
		labeledComponent.setPreferredSize( new Dimension( 200, 25 ) );
		labeledComponent.getLabel().setPreferredSize( new Dimension( 100, 14 ) );
		springLayout.putConstraint( SpringLayout.SOUTH, labeledComponent, -10, SpringLayout.SOUTH, contentPane );
		springLayout.putConstraint( SpringLayout.NORTH, labeledComponent, 10, SpringLayout.NORTH, contentPane );
		springLayout.putConstraint( SpringLayout.WEST, labeledComponent, 10, SpringLayout.WEST, contentPane );
		springLayout.putConstraint( SpringLayout.EAST, labeledComponent, -10, SpringLayout.EAST, contentPane );
		getContentPane().add( labeledComponent );

		pack();

	}
}

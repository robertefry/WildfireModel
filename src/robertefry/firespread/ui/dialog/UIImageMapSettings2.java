
package robertefry.firespread.ui.dialog;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import robertefry.firespread.ui.element.ICLabeledInfoTextField;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
@SuppressWarnings( "serial" )
public class UIImageMapSettings2 extends JFrame {

	private JPanel contentPane;

	// TODO UIImageMapSettings
	// Convert to UIDialog
	
	public UIImageMapSettings2() {

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setBounds( 100, 100, 450, 300 );
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setContentPane( contentPane );
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout( sl_contentPane );

		JLabel lblNewLabel = new JLabel( "New label" );
		sl_contentPane.putConstraint( SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane );
		sl_contentPane.putConstraint( SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, contentPane );
		sl_contentPane.putConstraint( SpringLayout.EAST, lblNewLabel, -10, SpringLayout.EAST, contentPane );
		contentPane.add( lblNewLabel );

		ICLabeledInfoTextField labeledInfoTextField = new ICLabeledInfoTextField( "Selection X" );
		labeledInfoTextField.getLabel().setPreferredSize( new Dimension( 89, 14 ) );
		sl_contentPane.putConstraint( SpringLayout.NORTH, labeledInfoTextField, 6, SpringLayout.SOUTH, lblNewLabel );
		sl_contentPane.putConstraint( SpringLayout.WEST, labeledInfoTextField, 10, SpringLayout.WEST, contentPane );
		sl_contentPane.putConstraint( SpringLayout.EAST, labeledInfoTextField, -10, SpringLayout.EAST, contentPane );
		contentPane.add( labeledInfoTextField );

		ICLabeledInfoTextField labeledInfoTextField_1 = new ICLabeledInfoTextField( "Selection Y" );
		sl_contentPane
			.putConstraint( SpringLayout.NORTH, labeledInfoTextField_1, 6, SpringLayout.SOUTH, labeledInfoTextField );
		sl_contentPane.putConstraint( SpringLayout.WEST, labeledInfoTextField_1, 0, SpringLayout.WEST, lblNewLabel );
		sl_contentPane.putConstraint( SpringLayout.EAST, labeledInfoTextField_1, -10, SpringLayout.EAST, contentPane );
		labeledInfoTextField_1.getLabel().setPreferredSize( new Dimension( 89, 14 ) );
		contentPane.add( labeledInfoTextField_1 );

		ICLabeledInfoTextField labeledInfoTextField_2 = new ICLabeledInfoTextField( "Selection Width" );
		sl_contentPane
			.putConstraint( SpringLayout.NORTH, labeledInfoTextField_2, 6, SpringLayout.SOUTH, labeledInfoTextField_1 );
		sl_contentPane.putConstraint( SpringLayout.WEST, labeledInfoTextField_2, 0, SpringLayout.WEST, lblNewLabel );
		sl_contentPane.putConstraint( SpringLayout.EAST, labeledInfoTextField_2, -10, SpringLayout.EAST, contentPane );
		labeledInfoTextField_2.getLabel().setPreferredSize( new Dimension( 89, 14 ) );
		contentPane.add( labeledInfoTextField_2 );

		ICLabeledInfoTextField labeledInfoTextField_3 = new ICLabeledInfoTextField( "Selection Height" );
		sl_contentPane
			.putConstraint( SpringLayout.NORTH, labeledInfoTextField_3, 6, SpringLayout.SOUTH, labeledInfoTextField_2 );
		sl_contentPane.putConstraint( SpringLayout.WEST, labeledInfoTextField_3, 0, SpringLayout.WEST, lblNewLabel );
		sl_contentPane.putConstraint( SpringLayout.EAST, labeledInfoTextField_3, -10, SpringLayout.EAST, contentPane );
		labeledInfoTextField_3.getLabel().setPreferredSize( new Dimension( 89, 14 ) );
		contentPane.add( labeledInfoTextField_3 );

	}
}

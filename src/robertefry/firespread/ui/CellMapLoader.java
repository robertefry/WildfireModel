
package robertefry.firespread.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import robertefry.firespread.ui.element.UIResource;

/**
 * @author Robert E Fry
 * @date 29 Jan 2019
 */
@SuppressWarnings( "serial" )
public class CellMapLoader extends JFrame {

	private JPanel contentPane;

	private UIResource srcElevationMap = new UIResource( "Elevation map", true );
	private UIResource srcTerrainMap = new UIResource( "Terrain map", true );
	private UIResource srcMapTexture = new UIResource( "Map Texture", false );

	/**
	 * Create the frame.
	 */
	public CellMapLoader() {
		setBounds( 100, 100, 450, 300 );
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		contentPane.setLayout( new BorderLayout( 0, 0 ) );
		setContentPane( contentPane );

		JPanel buttonpanel = new JPanel();
		buttonpanel.setPreferredSize( new Dimension( 10, 23 ) );
		contentPane.add( buttonpanel, BorderLayout.SOUTH );
		SpringLayout sl_buttonpanel = new SpringLayout();
		buttonpanel.setLayout( sl_buttonpanel );

		JButton btnCancel = new JButton( "Cancel" );
		btnCancel.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				dispose();
			}
		} );
		sl_buttonpanel.putConstraint( SpringLayout.SOUTH, btnCancel, 0, SpringLayout.SOUTH, buttonpanel );
		sl_buttonpanel.putConstraint( SpringLayout.EAST, btnCancel, 0, SpringLayout.EAST, buttonpanel );
		btnCancel.setPreferredSize( new Dimension( 89, 23 ) );
		buttonpanel.add( btnCancel );

		JButton btnOk = new JButton( "OK" );
		btnOk.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				// TODO create CellMap
				dispose();
			}
		} );
		sl_buttonpanel.putConstraint( SpringLayout.SOUTH, btnOk, 0, SpringLayout.SOUTH, buttonpanel );
		sl_buttonpanel.putConstraint( SpringLayout.EAST, btnOk, -6, SpringLayout.WEST, btnCancel );
		btnOk.setPreferredSize( new Dimension( 89, 23 ) );
		buttonpanel.add( btnOk );

		JPanel panel = new JPanel();
		panel.setPreferredSize( new Dimension( 446, 120 ) );
		contentPane.add( panel, BorderLayout.CENTER );
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout( sl_panel );

		sl_panel.putConstraint( SpringLayout.NORTH, srcElevationMap, 10, SpringLayout.NORTH, panel );
		sl_panel.putConstraint( SpringLayout.WEST, srcElevationMap, 10, SpringLayout.WEST, panel );
		sl_panel.putConstraint( SpringLayout.EAST, srcElevationMap, -10, SpringLayout.EAST, panel );
		panel.add( srcElevationMap );

		sl_panel.putConstraint( SpringLayout.NORTH, srcTerrainMap, 10, SpringLayout.SOUTH, srcElevationMap );
		sl_panel.putConstraint( SpringLayout.WEST, srcTerrainMap, 10, SpringLayout.WEST, panel );
		sl_panel.putConstraint( SpringLayout.EAST, srcTerrainMap, -10, SpringLayout.EAST, panel );
		panel.add( srcTerrainMap );

		sl_panel.putConstraint( SpringLayout.NORTH, srcMapTexture, 10, SpringLayout.SOUTH, srcTerrainMap );
		sl_panel.putConstraint( SpringLayout.WEST, srcMapTexture, 10, SpringLayout.WEST, panel );
		sl_panel.putConstraint( SpringLayout.EAST, srcMapTexture, -10, SpringLayout.EAST, panel );
		panel.add( srcMapTexture );

		pack();

	}

	@Override
	public void dispose() {
		super.dispose();
		srcElevationMap.setBlankText();
		srcTerrainMap.setBlankText();
		srcMapTexture.setBlankText();
	}

}

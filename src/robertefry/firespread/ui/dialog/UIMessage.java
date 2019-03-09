
package robertefry.firespread.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;

/**
 * @author Robert E Fry
 * @date 9 Mar 2019
 */
public class UIMessage extends JFrame {
	private static final long serialVersionUID = -8877674377262803116L;
	
	protected final JPanel contentPane = new JPanel();
	
	public UIMessage() {
		
		addWindowListener( new WindowAdapter() {
			public void windowClosing( WindowEvent e ) {
				cancel();
			}
		} );
		
		getContentPane().setLayout( new BorderLayout() );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
		
		JPanel buttonPane = new JPanel();
		getContentPane().add( contentPane, BorderLayout.CENTER );
		getContentPane().add( buttonPane, BorderLayout.SOUTH );
		buttonPane.setPreferredSize( new Dimension( 196, 35 ) );
		buttonPane.setMinimumSize( new Dimension( 196, 35 ) );
		
		SpringLayout sl_buttonPane = new SpringLayout();
		buttonPane.setLayout( sl_buttonPane );
		
		JSeparator separator = new JSeparator();
		sl_buttonPane.putConstraint( SpringLayout.WEST, separator, 10, SpringLayout.WEST, buttonPane );
		sl_buttonPane.putConstraint( SpringLayout.EAST, separator, -10, SpringLayout.EAST, buttonPane );
		separator.setBorder( new EtchedBorder( EtchedBorder.LOWERED, null, null ) );
		buttonPane.add( separator );
		
		JButton btnOK = new JButton( "OK" );
		btnOK.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				confirm();
			}
		} );
		sl_buttonPane.putConstraint( SpringLayout.NORTH, btnOK, 6, SpringLayout.NORTH, buttonPane );
		sl_buttonPane.putConstraint( SpringLayout.SOUTH, btnOK, -6, SpringLayout.SOUTH, buttonPane );
		sl_buttonPane.putConstraint( SpringLayout.EAST, btnOK, -6, SpringLayout.EAST, buttonPane );
		btnOK.setPreferredSize( new Dimension( 89, 23 ) );
		buttonPane.add( btnOK );
		
		JButton btnCancel = new JButton( "Cancel" );
		btnCancel.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				cancel();
			}
		} );
		sl_buttonPane.putConstraint( SpringLayout.NORTH, btnCancel, 6, SpringLayout.NORTH, buttonPane );
		sl_buttonPane.putConstraint( SpringLayout.SOUTH, btnCancel, -6, SpringLayout.SOUTH, buttonPane );
		btnCancel.setPreferredSize( new Dimension( 89, 23 ) );
		sl_buttonPane.putConstraint( SpringLayout.EAST, btnCancel, -6, SpringLayout.WEST, btnOK );
		buttonPane.add( btnCancel );
		
		pack();
		
	}
	
	protected void confirm() {
		dispose();
	}
	
	protected void cancel() {
		dispose();
	}
	
}


package robertefry.firespread.ui.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SpringLayout;
import javax.swing.border.EtchedBorder;
import org.apache.commons.logging.LogFactory;

/**
 * @author Robert E Fry
 * @date 1 Feb 2019
 */
public abstract class UIDialog< T > extends JFrame {
	private static final long serialVersionUID = -3013368826748650387L;

	protected final JPanel contentPane = new JPanel();
	private final JPanel buttonPane = new JPanel();

	private final FutureTask<T> future = new FutureTask<>( () -> {
		return getReturn();
	} );

	protected abstract T getReturn();

	public T fetch() throws InterruptedException, ExecutionException {
		try {
			return future.get();
		} catch ( CancellationException e ) {
			LogFactory.getLog( getClass() ).warn( "return fetch task cancelled", e );
		}
		return null;
	}

	public T get( long timeout, TimeUnit unit ) throws InterruptedException, ExecutionException, TimeoutException {
		return future.get( timeout, unit );
	}

	public UIDialog() {

		getContentPane().setLayout( new BorderLayout() );
		setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );

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
				future.run();
				dispose();
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
				future.cancel( false );
				dispose();
			}
		} );
		sl_buttonPane.putConstraint( SpringLayout.NORTH, btnCancel, 6, SpringLayout.NORTH, buttonPane );
		sl_buttonPane.putConstraint( SpringLayout.SOUTH, btnCancel, -6, SpringLayout.SOUTH, buttonPane );
		btnCancel.setPreferredSize( new Dimension( 89, 23 ) );
		sl_buttonPane.putConstraint( SpringLayout.EAST, btnCancel, -6, SpringLayout.WEST, btnOK );
		buttonPane.add( btnCancel );

		pack();

	}

}

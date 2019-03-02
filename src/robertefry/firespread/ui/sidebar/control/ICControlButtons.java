
package robertefry.firespread.ui.sidebar.control;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import robertefry.firespread.model.Model;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public class ICControlButtons extends JPanel {
	private static final long serialVersionUID = 3938882238925642865L;
	
	/**
	 * Create the panel.
	 */
	public ICControlButtons() {
		
		setLayout( new GridLayout( 0, 3, 2, 2 ) );
		
		JButton btnReset = new JButton( "Reset" );
		btnReset.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				Model.getEngine().reset();
			}
		} );
		btnReset.setEnabled( false );
		add( btnReset );
		
		JButton btnStop = new JButton( "Stop" );
		btnStop.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				Model.getEngine().suspend();
			}
		} );
		add( btnStop );
		
		JButton btnStart = new JButton( "Start" );
		btnStart.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				Model.getEngine().resume();
			}
		} );
		add( btnStart );
		
	}
}

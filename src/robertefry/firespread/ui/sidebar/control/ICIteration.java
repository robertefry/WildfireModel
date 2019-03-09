
package robertefry.firespread.ui.sidebar.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import robertefry.firespread.io.ImageResource;
import robertefry.firespread.model.Model;
import robertefry.firespread.ui.animate.RotatingIcon;
import robertefry.penguin.engine.listener.EngineStateListener;
import robertefry.penguin.targets.SimpleCounter;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public class ICIteration extends JPanel {
	private static final long serialVersionUID = 1126678209073859219L;
	
	public ICIteration() {
		
		SpringLayout layout = new SpringLayout();
		setLayout( layout );
		
		JLabel lblIteration = new JLabel( "Iteration :" );
		layout.putConstraint( SpringLayout.NORTH, lblIteration, 0, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.SOUTH, lblIteration, 0, SpringLayout.SOUTH, this );
		layout.putConstraint( SpringLayout.WEST, lblIteration, 0, SpringLayout.WEST, this );
		add( lblIteration );
		
		RotatingIcon icnWorking = new RotatingIcon(
			new ImageIcon( ImageResource.loadImage( "res/icon/working.png", 20, 20 ) ), 0.5f
		);
		layout.putConstraint( SpringLayout.NORTH, icnWorking, 0, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.SOUTH, icnWorking, 0, SpringLayout.SOUTH, this );
		layout.putConstraint( SpringLayout.WEST, icnWorking, 6, SpringLayout.EAST, lblIteration );
		add( icnWorking );
		
		JButton btnNext = new JButton( ">>" );
		btnNext.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				Model.getEngine().forceTick();
			}
		} );
		layout.putConstraint( SpringLayout.NORTH, btnNext, 0, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.EAST, btnNext, 0, SpringLayout.EAST, this );
		layout.putConstraint( SpringLayout.SOUTH, btnNext, 0, SpringLayout.SOUTH, this );
		add( btnNext );
		
		JTextField tfIteration = new JTextField();
		layout.putConstraint( SpringLayout.NORTH, tfIteration, 0, SpringLayout.NORTH, this );
		layout.putConstraint( SpringLayout.EAST, tfIteration, -6, SpringLayout.WEST, btnNext );
		layout.putConstraint( SpringLayout.SOUTH, tfIteration, 0, SpringLayout.SOUTH, this );
		layout.putConstraint( SpringLayout.WEST, tfIteration, 6, SpringLayout.EAST, icnWorking );
		tfIteration.setColumns( 10 );
		tfIteration.setEditable( false );
		add( tfIteration );
		
		Model.getEngine().getEngineStateListeners().add( new EngineStateListener() {
			
			@Override
			public void onSuspend() {
				icnWorking.stop();
			};
			
			@Override
			public void onResume() {
				icnWorking.start();
			}
			
		} );
		
		Model.getEngine().getTargetManager().add( new SimpleCounter() {
			
			@Override
			public void update() {
				super.update();
				tfIteration.setText( String.valueOf( getCount() ) );
			}
			
			@Override
			public void reset() {
				super.reset();
				tfIteration.setText( String.valueOf( 0 ) );
			}
			
		} );
		
	}
	
}

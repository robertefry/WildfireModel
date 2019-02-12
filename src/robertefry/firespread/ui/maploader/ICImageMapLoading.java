
package robertefry.firespread.ui.maploader;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import org.apache.commons.logging.LogFactory;
import robertefry.firespread.ui.atomic.LabeledComponent;
import robertefry.firespread.ui.atomic.ToolTipTextField;
import robertefry.firespread.ui.dialog.UIDialog;

/**
 * @author Robert E Fry
 * @date 29 Jan 2019
 */
public class ICImageMapLoading extends JPanel {
	private static final long serialVersionUID = 739973722131778461L;

	private final LabeledComponent<
		ToolTipTextField > textComponent = new LabeledComponent<>( "", new ToolTipTextField() );
	private final Rectangle selection = new Rectangle( 0, 0, 0, 0 );

	private void open() {
		JFileChooser fileChooser = new JFileChooser();
		int opened = fileChooser.showOpenDialog( this );
		if ( opened == JFileChooser.APPROVE_OPTION ) {
			textComponent.getComponent().setText( fileChooser.getSelectedFile().getAbsolutePath() );
		}
	}

	/**
	 * Create the panel.
	 */
	public ICImageMapLoading( String description, boolean required ) {

		setPreferredSize( new Dimension( 416, 25 ) );
		SpringLayout springLayout = new SpringLayout();
		springLayout.putConstraint( SpringLayout.NORTH, textComponent, 1, SpringLayout.NORTH, this );
		springLayout.putConstraint( SpringLayout.WEST, textComponent, 1, SpringLayout.WEST, this );
		springLayout.putConstraint( SpringLayout.SOUTH, textComponent, -1, SpringLayout.SOUTH, this );
		setLayout( springLayout );

		textComponent.getLabel()
			.setText( String.format( "<html>%s%s</html>", description, required ? "<font color=red>*</font>" : "" ) );
		textComponent.getComponent().setColumns( 10 );
		textComponent.getLabel().setPreferredSize( new Dimension( 89, 14 ) );
		add( textComponent );

		JButton btnProperties = new JButton( "?" );
		btnProperties.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				new Thread( () -> {
					UIDialog< Rectangle > settings = new UIImageMapSettings( selection );
					settings.setLocationRelativeTo( textComponent );
					settings.setVisible( true );
					Rectangle space = null;
					try {
						space = settings.fetch();
					} catch ( CancellationException e1 ) {
					} catch ( InterruptedException | ExecutionException e1 ) {
						LogFactory.getLog( getClass() ).error( "failed to set selection space", e1 );
					}
					if ( settings.hasFetched() ) selection.setBounds( space );
				} ).start();
			}
		} );
		springLayout.putConstraint( SpringLayout.EAST, textComponent, -1, SpringLayout.WEST, btnProperties );
		springLayout.putConstraint( SpringLayout.NORTH, btnProperties, 1, SpringLayout.NORTH, this );
		springLayout.putConstraint( SpringLayout.SOUTH, btnProperties, -1, SpringLayout.SOUTH, this );
		add( btnProperties );

		JButton btnOpen = new JButton( "Open" );
		springLayout.putConstraint( SpringLayout.EAST, btnProperties, -1, SpringLayout.WEST, btnOpen );
		springLayout.putConstraint( SpringLayout.NORTH, btnOpen, 1, SpringLayout.NORTH, this );
		springLayout.putConstraint( SpringLayout.SOUTH, btnOpen, -1, SpringLayout.SOUTH, this );
		springLayout.putConstraint( SpringLayout.EAST, btnOpen, -1, SpringLayout.EAST, this );
		btnOpen.setPreferredSize( new Dimension( 89, 23 ) );
		btnOpen.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				open();
			}
		} );
		add( btnOpen );

	}

	public void setBlankText() {
		textComponent.getComponent().setText( "" );
	}

	public boolean hasText() {
		return !textComponent.getComponent().getText().isEmpty();
	}

	public String getText() {
		return textComponent.getComponent().getText();
	}

	public Rectangle getSelection() {
		return selection;
	}

}

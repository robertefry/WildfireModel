
package robertefry.firespread.ui.atomic;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;

/**
 * @author Robert E Fry
 * @date 8 Mar 2019
 */
public class ICFunction extends JPanel {
	private static final long serialVersionUID = 2248728054483869455L;
	
	private final JLabel lblX;
	private final JLabel lblY;
	
	private final JSpinner spnM = new ICSpinner();
	private final JSpinner spnC = new ICSpinner();
	
	public ICFunction( String label, String x, String y ) {
		
		lblX = new JLabel( String.format( " %s + ", x ) );
		lblY = new JLabel( String.format( "%s = ", y ) );
		
		lblX.setHorizontalTextPosition( SwingConstants.CENTER );
		lblY.setHorizontalTextPosition( SwingConstants.CENTER );
		
		setLayout( new BorderLayout( 0, 0 ) );
		
		JLabel lblLabel = new JLabel( label );
		add( lblLabel, BorderLayout.NORTH );
		
		JPanel panel = new JPanel();
		add( panel, BorderLayout.CENTER );
		panel.setLayout( new GridLayout( 0, 4, 0, 0 ) );
		
		panel.add( lblY );
		panel.add( spnM );
		panel.add( lblX );
		panel.add( spnC );
		
	}
	
	public JLabel getComponentX() {
		return lblX;
	}
	
	public JLabel getComponentY() {
		return lblY;
	}
	
	public JSpinner getComponentM() {
		return spnM;
	}
	
	public JSpinner getComponentC() {
		return spnC;
	}
	
	public double getM() {
		return ( (Number)spnM.getValue() ).doubleValue();
	}
	
	public void setM( double value ) {
		spnM.setValue( value );
	}
	
	public double getC() {
		return ( (Number)spnC.getValue() ).doubleValue();
	}
	
	public void setC( double value ) {
		spnC.setValue( value );
	}
	
}

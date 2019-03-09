
package robertefry.firespread.ui.atomic.func;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import robertefry.firespread.ui.atomic.ICSpinner;

/**
 * @author Robert E Fry
 * @date 8 Mar 2019
 */
public class ICLinearFunction extends JPanel {
	private static final long serialVersionUID = 2248728054483869455L;
	
	private final JLabel lblX;
	private final JLabel lblY;
	
	private final JSpinner spnM = new ICSpinner();
	private final JSpinner spnC = new ICSpinner();
	
	public ICLinearFunction( String title, String x, String y ) {
		
		lblX = new JLabel( String.format( " %s + ", x ) );
		lblY = new JLabel( String.format( "%s = ", y ) );
		
		setLayout( new BorderLayout( 5, 5 ) );
		
		JLabel lblTitle = new JLabel( title );
		add( lblTitle, BorderLayout.NORTH );
		
		JPanel pnlFunc = new JPanel();
		pnlFunc.setLayout( new GridLayout( 1, 4, 0, 0 ) );
		
		pnlFunc.add( lblY );
		pnlFunc.add( spnM );
		pnlFunc.add( lblX );
		pnlFunc.add( spnC );
		
		add( pnlFunc, BorderLayout.CENTER );
		
		setBorder(
			new CompoundBorder(
				new LineBorder( new Color( 192, 192, 192 ), 1, true ),
				new EmptyBorder( 5, 5, 5, 5 )
			)
		);
		
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

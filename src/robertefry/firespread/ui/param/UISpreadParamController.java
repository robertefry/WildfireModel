
package robertefry.firespread.ui.param;

import javax.swing.SpringLayout;
import robertefry.firespread.model.spread.Spread;
import robertefry.firespread.ui.atomic.func.ICLinearFunction;
import robertefry.firespread.ui.dialog.UIMessage;

/**
 * @author Robert E Fry
 * @date 8 Mar 2019
 */
public class UISpreadParamController extends UIMessage {
	private static final long serialVersionUID = 4438663642399166090L;
	
	private final ICLinearFunction fnDistSpread = new ICLinearFunction( "Spread probability function", "distance", "probability" );
	
	public UISpreadParamController() {
		
		SpringLayout layout = new SpringLayout();
		contentPane.setLayout( layout );
		
		layout.putConstraint( SpringLayout.NORTH, fnDistSpread, 10, SpringLayout.NORTH, contentPane );
		layout.putConstraint( SpringLayout.WEST, fnDistSpread, 10, SpringLayout.WEST, contentPane );
		contentPane.add( fnDistSpread );
		
		layout.putConstraint( SpringLayout.SOUTH, contentPane, 10, SpringLayout.SOUTH, fnDistSpread );
		layout.putConstraint( SpringLayout.EAST, contentPane, 10, SpringLayout.EAST, fnDistSpread );
		pack();
		
	}
	
	@Override
	protected void confirm() {
		Spread.getDistanceTest().setGradient( fnDistSpread.getM() );
		Spread.getDistanceTest().setConstant( fnDistSpread.getC() );
		super.confirm();
	}
	
	@Override
	public void setVisible( boolean b ) {
		fnDistSpread.setM( Spread.getDistanceTest().getGradient() );
		fnDistSpread.setC( Spread.getDistanceTest().getConstant() );
		super.setVisible( b );
	}
	
}

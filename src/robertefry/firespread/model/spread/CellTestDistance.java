
package robertefry.firespread.model.spread;

import robertefry.firespread.model.cell.Cell;

/**
 * @author Robert E Fry
 * @date 2 Mar 2019
 */
public class CellTestDistance extends CellTest {
	
	private double gradient = -0.5, constant = 1;
	
	@Override
	public double apply( Cell src, Cell dest ) {
		double distance = src.getLocation().distance( dest.getLocation() );
		return gradient * distance + constant;
	}
	
	public double getGradient() {
		return gradient;
	}
	
	public void setGradient( double gradient ) {
		this.gradient = gradient;
	}
	
	public double getConstant() {
		return constant;
	}
	
	public void setConstant( double constant ) {
		this.constant = constant;
	}
	
}


package robertefry.firespread.model.spread;

import robertefry.firespread.model.grid.Cell;
import robertefry.firespread.util.MathUtil;

/**
 * @author Robert E Fry
 * @date 2 Mar 2019
 */
public abstract class CellTest {
	
	private double min = 0, max = 1;
	
	public abstract double apply( Cell src, Cell dest );
	
	public double test( Cell src, Cell dest ) {
		return MathUtil.clamp( apply( src, dest ), min, max );
	}
	
	public double getClampMin() {
		return min;
	}
	
	public void setClampMin( double min ) {
		this.min = min;
	}
	
	public double getClampMax() {
		return max;
	}
	
	public void setClampMax( double max ) {
		this.max = max;
	}
	
}

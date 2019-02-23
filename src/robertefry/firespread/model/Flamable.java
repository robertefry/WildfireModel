
package robertefry.firespread.model;

/**
 * @author Robert E Fry
 * @date 22 Feb 2019
 */
public interface Flamable {
	
	public void tryIgnite();
	
	public boolean canBurn();
	
	public boolean isBurning();
	
}

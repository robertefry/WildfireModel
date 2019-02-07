
package robertefry.firespread.model.type;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public interface Flamable {

	/**
	 * Try to burn the instance
	 * @return true if burning
	 */
	public boolean tryBurn();

	/**
	 * Test if the instance is burning
	 * @return true if the instance is burning
	 */
	public boolean isBurning();

}

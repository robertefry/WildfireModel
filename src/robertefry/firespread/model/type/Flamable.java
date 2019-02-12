
package robertefry.firespread.model.type;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public interface Flamable {

	public boolean canBurn();

	public boolean tryBurn();

	public boolean isBurning();

}


package robertefry.firespread.model.type;

/**
 * @author Robert E Fry
 * @date 26 Jan 2019
 */
public interface Flamable {
	
	public boolean canBurn();
	
	public boolean isBurning();
	
	public void burn();
	
}

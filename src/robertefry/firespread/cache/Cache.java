
package robertefry.firespread.cache;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Robert E Fry
 * @date 24 Feb 2019
 */
public interface Cache< K, V > extends Map< K, V > {
	
	public V get( K key, Supplier< V > supplier );
	
}

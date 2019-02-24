
package robertefry.firespread.cache;

import java.util.HashMap;
import java.util.function.Supplier;

/**
 * @author Robert E Fry
 * @date 24 Feb 2019
 */
public class SimpleCache< K, V > extends HashMap< K, V > implements Cache< K, V > {
	private static final long serialVersionUID = -6589858970940075722L;
	
	@Override
	public V get( K key, Supplier< V > supplier ) {
		if ( !containsKey( key ) ) {
			put( key, supplier.get() );
		}
		return get( key );
	}
	
}

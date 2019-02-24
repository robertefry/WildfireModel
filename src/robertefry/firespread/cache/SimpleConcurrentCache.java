
package robertefry.firespread.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * @author Robert E Fry
 * @date 24 Feb 2019
 */
public class SimpleConcurrentCache< K, V > extends ConcurrentHashMap< K, V > implements Cache< K, V > {
	private static final long serialVersionUID = -6589858970940075722L;
	
	@Override
	public V get( K key, Supplier< V > supplier ) {
		if ( !containsKey( key ) ) {
			put( key, supplier.get() );
		}
		return get( key );
	}
	
}

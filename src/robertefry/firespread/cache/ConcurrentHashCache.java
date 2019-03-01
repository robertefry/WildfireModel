
package robertefry.firespread.cache;

import java.beans.IntrospectionException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * @author Robert E Fry
 * @date 24 Feb 2019
 */
public class ConcurrentHashCache< K, V > extends ConcurrentHashMap< K, V > implements Cache< K, V > {
	private static final long serialVersionUID = -6589858970940075722L;
	
	@Override
	public V retrieve( K key ) throws IntrospectionException {
		if ( !containsKey( key ) ) throw new IntrospectionException( "no cache mapping found" );
		return get( key );
	}
	
	@Override
	public V retrieve( K key, Supplier< V > supplier ) {
		V value = null;
		if ( containsKey( key ) ) {
			value = get( key );
		} else {
			value = supplier.get();
			put( key, value );
		}
		return value;
	}
	
}

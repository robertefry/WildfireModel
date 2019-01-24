
package robertefry.firespread.model.type;

import java.util.Map;
import java.util.TreeSet;
import org.joml.Vector2f;

public class Wind {

	private Map<Integer,Vector2f> vectors;

	public Wind( Map<Integer,Vector2f> vectors ) {
		this.vectors = vectors;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder( "Wind[ " );
		(new TreeSet<>( vectors.keySet() )).forEach( key -> {
			builder.append( String.format( "(%s,%s) ", key, vectors.get( key ) ) );
		} );
		return builder.append( "]" ).toString();
	}

	public Map<Integer,Vector2f> getVectors() {
		return vectors;
	}

}

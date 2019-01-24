
package robertefry.firespread.io;

import org.joml.Vector2f;

/**
 * @author Robert E Fry
 * @date 24 Jan 2019
 */
public class ParserVector2f {
	
	public static final Vector2f fromString( String string ) {
		String[] values = string.split( ":" );
		float x = Float.parseFloat( values[0] );
		float y = Float.parseFloat( values[1] );
		return new Vector2f( x, y );
	}

}

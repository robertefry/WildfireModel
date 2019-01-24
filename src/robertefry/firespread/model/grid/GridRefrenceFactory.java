
package robertefry.firespread.model.grid;

import org.joml.Vector2i;

/**
 * @author Robert E Fry
 * @date 24 Jan 2019
 */
public class GridRefrenceFactory {

	public static final GridRefrence fromString( String string ) {
		final String[] coords = string.split( ":" );
		final int x = Integer.parseInt( coords[0] );
		final int y = Integer.parseInt( coords[1] );
		return new GridRefrence( new Vector2i( x, y ) );
	}
	
	public static final GridRefrence fromCoords( int x, int y ) {
		return new GridRefrence( x, y );
	}
	
	public static final GridRefrence fromVector( Vector2i vec ) {
		return new GridRefrence( vec );
	}

}

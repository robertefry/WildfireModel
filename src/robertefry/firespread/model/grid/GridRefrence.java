
package robertefry.firespread.model.grid;

import org.joml.Vector2i;

/**
 * @author Robert E Fry
 * @date 24 Jan 2019
 */
public class GridRefrence {
	
	private final Vector2i position;
	
	public GridRefrence( Vector2i position ) {
		this.position = position;
	}
	
	public GridRefrence( int x, int y ) {
		this.position = new Vector2i( x, y );
	}
	
	public Vector2i getPosition() {
		return position;
	}

}

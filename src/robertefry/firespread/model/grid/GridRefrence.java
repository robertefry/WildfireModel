
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

	public Vector2i getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return String.format( "GridRefrence[%s]", position );
	}

	@Override
	public boolean equals( Object obj ) {
		if (!(obj instanceof GridRefrence)) {
			return false;
		} else {
			return ((GridRefrence)obj).position.equals( position );
		}
	}

}

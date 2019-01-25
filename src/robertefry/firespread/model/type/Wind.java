
package robertefry.firespread.model.type;

import java.util.Map;
import org.joml.Vector2f;

public class Wind {

	private Map<Integer,Vector2f> vectors;

	public Wind( Map<Integer,Vector2f> vectors ) {
		this.vectors = vectors;
	}

	public Map<Integer,Vector2f> getVectors() {
		return vectors;
	}

}

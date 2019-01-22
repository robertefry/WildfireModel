package model.type;

import org.joml.Vector2f;

public class Wind {
	
	public Vector2f vector;
	
	public Wind( Vector2f vector ) {
		this.vector = vector;
	}
	
	public Vector2f getVector() {
		return vector;
	}

}

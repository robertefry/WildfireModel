package model.map;

import java.util.HashMap;

import org.joml.Vector2i;

import model.type.Height;
import robertefry.penguin.engine.target.Targetable;

public class HeightMap extends HashMap<Vector2i,Height> implements Targetable {
	private static final long serialVersionUID = -8942421652671594992L;

}

package model.map;

import java.util.HashMap;

import org.joml.Vector2i;

import model.type.Wind;
import robertefry.penguin.engine.target.Targetable;

public class WindMap extends HashMap<Vector2i,Wind> implements Targetable {
	private static final long serialVersionUID = -475924196444980996L;

}

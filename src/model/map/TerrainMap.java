package model.map;

import java.util.HashMap;

import org.joml.Vector2i;

import model.type.Terrain;
import robertefry.penguin.engine.target.Targetable;

public class TerrainMap extends HashMap<Vector2i,Terrain> implements Targetable {
	private static final long serialVersionUID = 2979242382778524838L;

}

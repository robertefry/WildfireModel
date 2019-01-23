package model.map;

import java.util.HashMap;
import java.util.Map;

import robertefry.penguin.engine.target.Targetable;

public class VariableMap<K,V> implements Targetable {
	
	protected Map<K,V> map = new HashMap<>();
	
	public V get(K key) {
		return map.get(key);
	}

}

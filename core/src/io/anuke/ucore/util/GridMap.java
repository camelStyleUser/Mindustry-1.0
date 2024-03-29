package io.anuke.ucore.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**A 2-dimensional hashmap that stores objects using an x/y coordinate.*/
public class GridMap<T>{
	protected HashMap<Long, T> map = new HashMap<Long, T>();
	
	public T get(int x, int y){
		return map.get(getHash(x,y));
	}
	
	public boolean containsKey(int x, int y){
		return map.containsKey(getHash(x,y));
	}
	
	public void put(int x, int y, T t){
		map.put(getHash(x,y), t);
	}
	
	public Collection<T> values(){
		return map.values();
	}
	
	public Set<Long> keys(){
		return map.keySet();
	}
	
	public void clear(){
		map.clear();
	}
	
	public int size(){
		return map.size();
	}
	
	private long getHash(int x, int y){
		return (((long)x) << 32) | (y & 0xffffffffL);
	}
}

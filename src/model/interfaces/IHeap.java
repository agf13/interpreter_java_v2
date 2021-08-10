package model.interfaces;

import java.util.Collection;
import java.util.Set;
import java.util.Map;

public interface IHeap<K,V> {
	V getValue(K key);
	public void setValue(K key, V newValue);
	V put(K key, V value);
	int size();
	boolean containsValue(V value);
	boolean containsKey(K key);
	void remove(K key);
	Collection<V> values();
	Set<K> keySet();
	Set<Map.Entry<K, V>> entrySet();
	void setContent(Set<Map.Entry<K, V>> set);
	K getKey(V value);
	MyIDictionary<K,V> clone_dict();
	String toString();
	Map<String, Value> getContent();
	
	public Integer getFreeAddr();
	
	MyIDictionary<Integer, Value> getAll();
}

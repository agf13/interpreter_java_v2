package model.interfaces;

import java.util.Collection;
import java.util.Set;
import java.util.Map;

public interface MyIDictionary<K,V> {
	V getValue(K key);
	V lookup(K id);
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
	MyIDictionary<String, Value> getAll();
}

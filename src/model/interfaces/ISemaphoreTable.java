package model.interfaces;

import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public interface ISemaphoreTable<K,V> {

	K getKey(V value);
	V getValue(K key);
	
	boolean isEmpty();
	boolean containsKey(K key);
	boolean containsValue(V val);

	void remove(K key);
	void put(K key, V val);
	void update(K key, V val);
    void setContent(HashMap<K, V> m);

    Map<K,V> getContent();
    HashMap<K,V> getAll();
	
	int getSize();
	
	String toString();
	
}

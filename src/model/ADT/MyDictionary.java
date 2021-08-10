package model.ADT;

import model.interfaces.*;

import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class MyDictionary<K,V> implements MyIDictionary<K,V> {
	HashMap<K,V> dict;
	
	//constructor
	public MyDictionary() {
		dict = new HashMap<K,V>();
	}
	
	//insert a new pair into the HashMap
	public V put(K key, V value) {
		return dict.put(key, value);
	}
	
	
	//returns the element at the given key
	public V getValue(K key) {
		return dict.get(key);
	}
	//as above, but with name found in teacher's pdfs
	public V lookup(K id) {
		return dict.get(id);
	}
	
	public K getKey(V value) {
		for(K key : dict.keySet()) {
			if(dict.get(key).equals(value)) {
				return key;
			}
		}
		return null;
	}
	
	
	public int size() {
		return dict.size();
	}
	
	public boolean containsValue(V element) {
		return dict.containsValue(element);
	}
	
	public void remove(K key) {
		dict.remove(key);
	}
	
	public Collection<V> values(){
		return dict.values();
	}
	
	public boolean containsKey(K key) {
		return dict.containsKey(key);
	}
	
	public Set<K> keySet(){
		return dict.keySet();
	}
	
	public Set<Map.Entry<K, V>> entrySet(){
		return dict.entrySet();
	}
	
	public void setContent(Set<Map.Entry<K, V>> set) {
		dict.clear();
		for(Map.Entry<K, V> element : set) {
			dict.put(element.getKey(), element.getValue());
		}
	}
	
	public Map<String, Value> getContent(){
		return (Map<String, Value>) this.dict;
	}
	
	public MyIDictionary<String, Value> getAll(){
		return (MyIDictionary<String, Value>) this;
	}
	
	public MyIDictionary<K,V> clone_dict(){
		MyIDictionary<K,V> di = new MyDictionary<K,V>();
		for(K key : dict.keySet()) {
			di.put(key, dict.get(key));
		}
		return di;
	}
	
	public String toString() {
		return dict.toString();
	}
	
	
	
	
}

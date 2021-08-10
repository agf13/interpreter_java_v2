package model.ADT;

import model.interfaces.*;

import java.util.Collection;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;

public class Heap<K,V> implements IHeap<K,V> {
	HashMap<K,V> dict;
	Integer addr;
	
	//constructor
	public Heap() {
		dict = new HashMap<K,V>();
		addr = 1;
	}
	
	public Integer getFreeAddr() {
		Integer freeAddr = this.addr;
		this.addr = addr + 1;
		return freeAddr;
	}
	
	//insert a new pair into the HashMap
	public V put(K key, V value) {
		return dict.put(key, value);
	}
	
	
	//returns the element at the given key
	public V getValue(K key) {
		return dict.get(key);
	}
	
	public void setValue(K key, V newValue) {
		dict.replace(key, newValue);
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
	
	public MyIDictionary<Integer, Value> getAll(){
		return (MyIDictionary<Integer, Value>) dict;
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

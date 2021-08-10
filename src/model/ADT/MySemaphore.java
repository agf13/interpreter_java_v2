package model.ADT;

									////////////////////
									///NOT FINISHED !!!!
									////////////////////

import model.interfaces.*;

import java.util.HashMap;
import java.util.Map;

public class MySemaphore<K,V> implements ISemaphoreTable<K,V>{
	HashMap<K,V> dict;
	
	public MySemaphore(){
		dict = new HashMap<K,V>();
	}
	
	public synchronized K getKey(V value) {
		for(K key : dict.keySet()) {
			if(dict.get(key).equals(value)) {
				return key;
			}
		}
		return null;
	}
	
	public synchronized V getValue(K key) {
		return dict.get(key);
	}
	
	public synchronized boolean isEmpty() {
		return dict.isEmpty();
	}
	
	public synchronized boolean containsKey(K key) {
		return dict.containsKey(key);
	}
	
	public synchronized boolean containsValue(V val) {
		return dict.containsValue(val);
	}
	
	public synchronized void remove(K key) {
		dict.remove(key);
	}
	
	public synchronized void put(K key, V val) {
		dict.put(key,  val);
	}
	
	public synchronized int getSize() {
		return dict.size();
	}
	
    public synchronized void setContent(HashMap<K,V> m) {
    	this.dict =  m;
    }

    public synchronized Map<K,V> getContent(){
    	return dict;
    }
    
    public synchronized void update(K key, V val) {
    	dict.remove(key);
    	dict.put(key,  val);
    }
    
    public synchronized HashMap<K,V> getAll(){
    	return dict;
    }
	
	public String toString() {
		return dict.toString();
	}
}

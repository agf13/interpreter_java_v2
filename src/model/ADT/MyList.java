package model.ADT;

import model.interfaces.*;

import java.util.ArrayList;

public class MyList<T> implements MyIList<T>{
	ArrayList<T> list;
	
	public MyList() {
		list = new ArrayList<T>();
	}
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	public boolean add(T val) {
		return list.add(val);
	}
	
	public T get(int index) {
		return list.get(index);
	}
	
	public void clear() {
		list.clear();
	}
	
	public ArrayList<T> getAll(){
		return this.list;
	}
	
	public String toString() {
		return list.toString();
	}
}

package model.interfaces;

import java.util.ArrayList;

public interface MyIList<T> {
	int size();
	boolean isEmpty();
	boolean add(T val);
	void clear();
	T get(int index);
	public ArrayList<T> getAll();
	String toString();
	
}

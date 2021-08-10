package model.interfaces;

import java.util.Stack;

public interface MyIStack<T> {
	T pop();
	void push(T val);
	boolean isEmpty();
	public Stack<T> getAll();
	String toString();
}

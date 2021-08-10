package model.ADT;

import model.interfaces.*;

import java.util.Stack;


public class MyStack<T> implements MyIStack<T>{
	Stack<T> stack;
	
	public MyStack() {
		stack = new Stack<T>();
	}
	
	public T pop() {
		return stack.pop();
	}
	
	public void push(T val) {
		stack.push(val);
	}
	
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	public Stack<T> getAll(){
		return stack;
	}
	
	public String toString() {
		return stack.toString();
	}
}

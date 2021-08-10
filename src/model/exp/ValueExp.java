package model.exp;

import model.exceptions.*;
import model.ADT.*;
import model.values.*;
import model.interfaces.*;

public class ValueExp implements Exp{
	Value val;


	public Type typecheck(MyIDictionary<String,Value> symList, IHeap<Integer, Value> heap) throws MyException, VarNotFound, ADTEmpty{
		return val.getType();
	}
	
	public ValueExp(Value val) {
		this.val = val;
	}
	

	public Value eval(MyIDictionary<String,Value> tbl, IHeap<Integer, Value> heap) throws ADTEmpty, MyException, VarNotFound {
		return val;
	}
	
	public String toString() {
		return val.toString();
	}
}
